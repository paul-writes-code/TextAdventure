package com.example.TextAdventure.Character;

import com.example.TextAdventure.Item.Inventory;

public class Enemy extends Character {

    public Enemy(String name, int currentHealth, int maxHealth, int damage, int defence, Inventory inventory) {
        super(name, currentHealth, maxHealth, damage, defence, inventory);
    }

    public boolean canBeAttacked() { return isAlive(); }
}
