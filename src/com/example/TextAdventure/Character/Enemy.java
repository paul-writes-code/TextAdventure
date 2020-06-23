package com.example.TextAdventure.Character;

import com.example.TextAdventure.Combat.Combat;
import com.example.TextAdventure.Item.Inventory;
import com.example.TextAdventure.Item.ItemList;

public class Enemy extends Character {

    public Enemy(int id, String name, int experience, int level, int currentHealth, int maxHealth, int damage, int defence, Inventory inventory) {
        super(id, name, experience, level, currentHealth, maxHealth, damage, defence, inventory, null);

        addGold((int)(Math.random() * 6));

        // Randomly generate some test items
        if ((Math.random() * 2) < 1.2)
            addItem(ItemList.getItem(12));
        else
            addItem(ItemList.getItem(13));
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
