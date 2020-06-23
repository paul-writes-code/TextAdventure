package com.example.TextAdventure.Character;

import com.example.TextAdventure.Combat.Combat;
import com.example.TextAdventure.Common.Utility;
import com.example.TextAdventure.Equipment.EquipmentSet;
import com.example.TextAdventure.Item.Inventory;
import com.example.TextAdventure.Item.ItemList;

public class Player extends Character {

    public static final int PLAYER_ID = -1;
    public static final int PLAYER_BASE_HEALTH = 10;
    public static final int PLAYER_BASE_DAMAGE = 2;
    public static final int PLAYER_BASE_DEFENCE = 2;

    public Player(String name) {
        super(PLAYER_ID, name, 0, 1, PLAYER_BASE_HEALTH, PLAYER_BASE_HEALTH, PLAYER_BASE_DAMAGE, PLAYER_BASE_DEFENCE, null, null);
        initPlayer(0);
    }
    public Player(String name, int experience, int currentHealth, int maxHealth, int damage, int defence, Inventory inventory, EquipmentSet equipmentSet) {
        super(PLAYER_ID, name, 0, 1, currentHealth, maxHealth, damage, defence, inventory, equipmentSet);
        initPlayer(experience);
    }
    private void initPlayer(int experience) {
        gainXp(experience);
        inventory.addHealthPotions(3);
        inventory.addItem(ItemList.getItem(7));
        inventory.addGold(500);
    }

    public boolean canBeAttacked() { return isAlive(); }
    public boolean canBeLooted() { return false; }
    public boolean canBeTraded() { return true; }

    public Inventory beLooted() { return null; }

    public int attackTarget() {
        if (getTarget() == null)
            return -1;

        return Combat.attack(this, getTarget(), true, getTarget() instanceof Player);
    }

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
