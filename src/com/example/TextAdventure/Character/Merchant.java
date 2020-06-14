package com.example.TextAdventure.Character;

import com.example.TextAdventure.Item.Inventory;

public class Merchant extends Character {

    public Merchant(String name) {
        super(name);
    }
    public Merchant(String name, Inventory inventory) {
        super(name, inventory);
    }

    public boolean canBeAttacked() { return false; }
    public boolean canBeLooted() { return false; }
    public boolean canBeTraded() { return true; }

    public Inventory beLooted() { return null; }

    public int attackTarget() {
        return 0;
    }
}
