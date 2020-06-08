package com.example.TextAdventure.Item;

public class Item {

    private String itemName;
    private int itemId;
    private boolean isStackable;
    private int quantity;

    public String getItemName(){ return itemName; }
    public int getItemId(){ return itemId; }
    public int getQuantity(){ return quantity; }
    public boolean isStackable() { return isStackable; }

    // INITIALIZATION
    public Item(String itemName, int itemId) {
        initItem(itemName, itemId, false, 1);
    }
    public Item(String itemName, int itemId, boolean isStackable, int quantity){
        initItem(itemName, itemId, isStackable, quantity);
    }
    private void initItem(String itemName, int itemId, boolean isStackable, int quantity) {
        this.itemName = itemName;
        this.itemId = itemId;
        this.isStackable = isStackable;
        this.quantity = quantity;

        if (!isStackable)
            this.quantity = 1;
    }

    // MODIFY QUANTITY
    public int addQuantity(int quantity) {
        if (quantity < 0 || !isStackable)
            return -1;

        this.quantity += quantity;
        return quantity;
    }
    public int removeQuantity(int quantity) {
        if (quantity > this.quantity || !isStackable)
            return -1;

        this.quantity -= quantity;
        return quantity;
    }
    public int setQuantity(int quantity) {
        if (quantity < 0 || !isStackable)
            return -1;

        this.quantity = quantity;
        return quantity;
    }

    @Override
    public String toString(){
        if (isStackable)
            return quantity + " x " + itemName;

        return itemName;
    }

    @Override
    public boolean equals(Object object){
        if (object == this)
            return true;

        if (!(object instanceof Item))
            return false;

        Item item = (Item)object;

        return itemId == item.itemId;
    }
}
