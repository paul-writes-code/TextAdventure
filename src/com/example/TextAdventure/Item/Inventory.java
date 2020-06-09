package com.example.TextAdventure.Item;

import java.util.ArrayList;

import static com.example.TextAdventure.UserInterface.Output.output;

public class Inventory {
    private ItemManager itemManager;
    private int gold;
    private int numHealthPotions;

    // INITIALIZATION
    public Inventory() {
        initInventory(null, 0, 0);
    }
    public Inventory(ArrayList<Item> items, int gold, int numHealthPotions) {
        initInventory(items, gold, numHealthPotions);
    }
    public Inventory(Inventory inventory) {
        if (inventory == null)
            initInventory(null, 0, 0);
        else
            initInventory(inventory.itemManager.getItems(), inventory.gold, inventory.numHealthPotions);
    }
    private void initInventory(ArrayList<Item> items, int gold, int numHealthPotions) {
        itemManager = new ItemManager(items);
        this.gold = gold;
        this.numHealthPotions = numHealthPotions;
    }

    // ITEM MANAGEMENT
    public ArrayList<Item> getItems() { return itemManager.getItems(); }
    public int getItemQuantity(int itemId) { return itemManager.getQuantity(itemId); }
    public boolean hasItem(Item item) { return itemManager.hasItem(item); }
    public void addItem(Item item) { itemManager.addItem(item); }
    public void addItems(ArrayList<Item> items) { itemManager.addItems(items); }
    public Item removeItem(Item item) { return itemManager.removeItem(item); }
    public ArrayList<Item> removeItems(ArrayList<Item> items) { return itemManager.removeItems(items); }

    public int getGold() { return gold; }
    public void addGold(int gold) { if (gold > 0) this.gold += gold; }
    public void removeGold(int gold) { if (gold > 0) this.gold -= gold; }

    public int getNumHealthPotions() { return numHealthPotions; }
    public void addHealthPotions(int numHealthPotions) { if (numHealthPotions > 0) this.numHealthPotions += numHealthPotions; }
    public boolean consumeHealthPotion() {
        if (numHealthPotions <= 0)
            return false;

        numHealthPotions--;
        return true;
    }

    // INVENTORY MANAGEMENT
    public void addInventory(Inventory inventory) {
        if (inventory == null)
            return;

        addItems(inventory.getItems());
        addGold(inventory.gold);
        addHealthPotions(inventory.numHealthPotions);
    }
    public Inventory removeInventory(Inventory inventory) {
        if (inventory == null || gold < inventory.gold || numHealthPotions < inventory.numHealthPotions)
            return null;

        ArrayList<Item> itemsRemoved = removeItems(inventory.getItems());

        if ((itemsRemoved != null || inventory.getItems() == null)) {
            gold -= inventory.gold;
            numHealthPotions -= inventory.numHealthPotions;

            return new Inventory(itemsRemoved, inventory.gold, inventory.numHealthPotions);
        }

        return null;
    }
    public void emptyInventory() {
        initInventory(null, 0, 0);
    }
    public boolean isEmpty() { return itemManager.getNumItems() == 0 && gold == 0 && numHealthPotions == 0; }

    @Override
    public String toString() {
        String ret = "";

        if (gold > 0) {
            if (!ret.equals(""))
                ret += ", ";

            ret += gold + " Gold";
        }

        if (numHealthPotions > 0) {
            if (!ret.equals(""))
                ret += ", ";

            ret += numHealthPotions + " Health Potions";
        }

        if (itemManager != null && itemManager.getNumItems() > 0) {
            if (!ret.equals(""))
                ret += ", ";

            return ret + itemManager.toString();
        }

        return ret;
    }
}
