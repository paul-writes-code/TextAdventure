package com.example.TextAdventure.Item;

public class Item {

    private String itemName;
    private int itemId;
    private int unitPrice;
    private boolean isStackable;
    private int quantity;

    public String getItemName() { return itemName; }
    public int getItemId() { return itemId; }
    public int getPrice() { return unitPrice * quantity; }
    public int getQuantity() { return quantity; }
    public boolean isStackable() { return isStackable; }

    // INITIALIZATION
    public Item(String itemName, int itemId, int unitPrice) {
        initItem(itemName, itemId, false, 1, unitPrice);
    }
    public Item(String itemName, int itemId, boolean isStackable, int quantity, int unitPrice){
        initItem(itemName, itemId, isStackable, quantity, unitPrice);
    }
    public Item(Item item) {
        initItem(item.itemName, item.itemId, item.isStackable, item.quantity, item.unitPrice);
    }
    private void initItem(String itemName, int itemId, boolean isStackable, int quantity, int unitPrice) {
        this.itemName = itemName;
        this.itemId = itemId;
        this.unitPrice = unitPrice;
        this.isStackable = isStackable;
        this.quantity = isStackable ? quantity : 1;
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
