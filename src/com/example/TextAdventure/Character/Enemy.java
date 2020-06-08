package com.example.TextAdventure.Character;

import com.example.TextAdventure.Item.Inventory;
import com.example.TextAdventure.Item.Item;

public class Enemy extends Character {

    public Enemy(String name, int currentHealth, int maxHealth, int damage, int defence, Inventory inventory) {
        super(name, currentHealth, maxHealth, damage, defence, inventory);
        getInventory().addGold(2);
        getInventory().addItem(new Item("Apple", 1));
    }

    public boolean canBeAttacked() { return isAlive(); }
    public boolean canBeLooted() { return !isAlive(); }

    public Inventory beLooted() {
        Inventory ret = getInventory();
        emptyInventory();
        return ret;
    }
}
