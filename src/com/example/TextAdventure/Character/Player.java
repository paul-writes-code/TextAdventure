package com.example.TextAdventure.Character;

import com.example.TextAdventure.Equipment.EquipmentSet;
import com.example.TextAdventure.Item.Inventory;

public class Player extends Character {

    public static final int PLAYER_BASE_HEALTH = 10;
    public static final int PLAYER_BASE_DAMAGE = 3;
    public static final int PLAYER_BASE_DEFENCE = 1;

    public Player(String name) {
        super(name, PLAYER_BASE_HEALTH, PLAYER_BASE_HEALTH, PLAYER_BASE_DAMAGE, PLAYER_BASE_DEFENCE, null, null);
        getInventory().addHealthPotions(3);
    }
    public Player(String name, int currentHealth, int maxHealth, int damage, int defence, Inventory inventory, EquipmentSet equipmentSet) {
        super(name, currentHealth, maxHealth, damage, defence, inventory, equipmentSet);
    }

    public boolean canBeAttacked() { return isAlive(); }
    public boolean canBeLooted() { return false; }

    public Inventory beLooted() { return null; }
}
