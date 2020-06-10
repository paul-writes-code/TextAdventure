package com.example.TextAdventure.Character;

import com.example.TextAdventure.Common.Utility;
import com.example.TextAdventure.Equipment.Equipment;
import com.example.TextAdventure.Equipment.EquipmentSet;
import com.example.TextAdventure.Item.Inventory;

public class Player extends Character {

    public static final int PLAYER_BASE_HEALTH = 10;
    public static final int PLAYER_BASE_DAMAGE = 2;
    public static final int PLAYER_BASE_DEFENCE = 2;

    public Player(String name) {
        super(name, 0, 1, PLAYER_BASE_HEALTH, PLAYER_BASE_HEALTH, PLAYER_BASE_DAMAGE, PLAYER_BASE_DEFENCE, null, null);
        initPlayer(0);
    }
    public Player(String name, int experience, int currentHealth, int maxHealth, int damage, int defence, Inventory inventory, EquipmentSet equipmentSet) {
        super(name, 0, 1, currentHealth, maxHealth, damage, defence, inventory, equipmentSet);
        initPlayer(experience);
    }
    private void initPlayer(int experience) {
        gainXp(experience);
        getInventory().addHealthPotions(3);
        getInventory().addItem(new Equipment(Equipment.EquipmentType.SWORD, "swordX", -9, 16,24,16));
    }

    public boolean canBeAttacked() { return isAlive(); }
    public boolean canBeLooted() { return false; }

    public Inventory beLooted() { return null; }

    // EXPERIENCE MANAGEMENT
    public void gainXp(int xp) {
        if (xp <= 0)
            return;

        experience += xp;
        levelUp();
    }
    private void levelUp() {
        while (Utility.getLevelFromExperience(experience) - level > 0) {
            level++;
            maxHealth += level % 2 == 0 ? 2 : 3;
            damage += level == 5 ? 2 : 1;
            defence += 4;
            refresh();
        }
    }
}
