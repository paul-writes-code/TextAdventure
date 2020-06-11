package com.example.TextAdventure.Item;

public class Item {

    private String itemName;
    private int itemId;
    private int buyPriceUnit;
    private int sellPriceUnit;
    private boolean isStackable;
    private int quantity;

    public String getItemName() { return itemName; }
    public int getItemId() { return itemId; }
    public int getBuyPrice() { return buyPriceUnit * quantity; }
    public int getSellPrice() { return sellPriceUnit * quantity; }
    public int getQuantity() { return quantity; }
    public boolean isStackable() { return isStackable; }

    // INITIALIZATION
    public Item(String itemName, int itemId, int buyPriceUnit, int sellPriceUnit) {
        initItem(itemName, itemId, false, 1, buyPriceUnit, sellPriceUnit);
    }
    public Item(String itemName, int itemId, boolean isStackable, int quantity, int buyPriceUnit, int sellPriceUnit){
        initItem(itemName, itemId, isStackable, quantity, buyPriceUnit, sellPriceUnit);
    }
    private void initItem(String itemName, int itemId, boolean isStackable, int quantity, int buyPriceUnit, int sellPriceUnit) {
        this.itemName = itemName;
        this.itemId = itemId;
        this.buyPriceUnit = buyPriceUnit;
        this.sellPriceUnit = sellPriceUnit;
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
