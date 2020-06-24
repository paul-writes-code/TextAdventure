package com.example.TextAdventure.Item;

import java.util.ArrayList;

public class ItemManager {

    private ArrayList<Item> items;

    // INITIALIZATION
    public ItemManager() {
        initItemManager(null);
    }
    public ItemManager(ArrayList<Item> items) {
        initItemManager(items);
    }
    private void initItemManager(ArrayList<Item> items) {
        this.items = new ArrayList<>();

        if (items != null)
            for (Item item : items)
                addItem(item);
    }

    // ITEM MANAGEMENT
    public int getNumItems() { return items.size(); }
    public ArrayList<Item> getItems() { return items; }
    public int getQuantity(int itemId) {
        for (Item inventoryItem : items)
            if (inventoryItem.getItemId() == itemId)
                return inventoryItem.getQuantity();

        return -1;
    }
    public boolean hasItem(Item item) {
        if (item != null && items != null)
            for (Item inventoryItem : items)
                if (inventoryItem.equals(item))
                    return inventoryItem.getQuantity() >= item.getQuantity();

        return false;
    }
    public void addItem(Item item) {
        if (item == null)
            return;

        if (items == null)
            items = new ArrayList<>();

        if (!item.isStackable()) {
            items.add(item);
            return;
        }

        for (Item inventoryItem : items)
            if (item.equals(inventoryItem)) {
                inventoryItem.addQuantity(item.getQuantity());
                return;
            }

        items.add(item);
    }
    public void addItems(ArrayList<Item> items) {
        if (items != null)
            for (Item item : items)
                addItem(item);
    }
    public Item removeItem(Item item) {
        if (item != null && items != null)
            for (Item inventoryItem : items)
                if (inventoryItem.equals(item)) {

                    if (inventoryItem.getQuantity() == item.getQuantity()) {
                        items.remove(inventoryItem);
                        return inventoryItem;
                    }

                    else if (inventoryItem.getQuantity() > item.getQuantity()) {
                        Item ret = inventoryItem;
                        ret.setQuantity(inventoryItem.removeQuantity(item.getQuantity()));
                        return ret;
                    }

                    // this should never be reached
                    return null;
                }

        return null;
    }
    public ArrayList<Item> removeItems(ArrayList<Item> items) {
        if (items == null || items.size() == 0)
            return null;

        ArrayList<Item> itemsRemoved = new ArrayList<>();

        for (Item item : items) {
            if (!hasItem(item)) {
                addItems(itemsRemoved);
                return null;
            }

            itemsRemoved.add(removeItem(item));
        }

        return itemsRemoved;
    }

    @Override
    public String toString() {
        if (items == null || items.size() == 0)
            return "";

        String ret = items.get(0).toString();

        for (int i = 1; i < items.size(); i++)
            ret += ", " + items.get(i).toString();

        return ret;
    }
}
