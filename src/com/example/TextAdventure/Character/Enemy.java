package com.example.TextAdventure.Character;

import com.example.TextAdventure.Combat.Combat;
import com.example.TextAdventure.Item.Inventory;
import com.example.TextAdventure.Item.Item;

public class Enemy extends Character {

    public Enemy(String name, int experience, int level, int currentHealth, int maxHealth, int damage, int defence, Inventory inventory) {
        super(name, experience, level, currentHealth, maxHealth, damage, defence, inventory, null);

        getInventory().addGold((int)(Math.random() * 6));

        // Randomly generate some test items
        if ((Math.random() * 2) < 1.2)
            getInventory().addItem(new Item("Apple", 1, 3, 3));
        else
            getInventory().addItem(new Item("Teapot", 2, true, 3, 1, 1));
    }

    public boolean canBeAttacked() { return isAlive(); }
    public boolean canBeLooted() { return !isAlive(); }
    public boolean canBeTraded() { return false; }

    public Inventory beLooted() { return emptyInventory(); }

    public int attackTarget() {
        if (getTarget() == null)
            return -1;

        return Combat.attack(this, getTarget(), false, getTarget() instanceof Player);
    }
}
