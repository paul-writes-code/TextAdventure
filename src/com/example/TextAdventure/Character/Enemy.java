package com.example.TextAdventure.Character;

import com.example.TextAdventure.Item.Inventory;
import com.example.TextAdventure.Item.Item;

public class Enemy extends Character {

    public Enemy(String name, int currentHealth, int maxHealth, int damage, int defence, Inventory inventory) {
        super(name, currentHealth, maxHealth, damage, defence, inventory, null);
        getInventory().addGold((int)(Math.random() * 6));

        // Randomly generate some test items
        if ((Math.random() * 2) < 1.2)
            getInventory().addItem(new Item("Apple", 1));
        else
            getInventory().addItem(new Item("Teapot", 2, true, 3));
    }

    public boolean canBeAttacked() { return isAlive(); }
    public boolean canBeLooted() { return !isAlive(); }

    public Inventory beLooted() {
        Inventory ret = getInventory();
        emptyInventory();
        return ret;
    }
}
