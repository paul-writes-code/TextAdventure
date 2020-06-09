package com.example.TextAdventure.Character;

import com.example.TextAdventure.Combat.Combat;
import com.example.TextAdventure.Equipment.Equipment;
import com.example.TextAdventure.Equipment.EquipmentSet;
import com.example.TextAdventure.Item.Inventory;

public abstract class Character {

    private static final int CHARACTER_BASE_HEALTH = 1;
    private static final int CHARACTER_BASE_DAMAGE = 1;
    private static final int CHARACTER_BASE_DEFENCE = 1;

    private String name;
    private int currentHealth;
    private int maxHealth;
    private int damage;
    private int defence;

    private Character target;
    private Inventory inventory;
    private EquipmentSet equipmentSet;

    public Character(String name) {
        initCharacter(name, CHARACTER_BASE_HEALTH, CHARACTER_BASE_HEALTH, CHARACTER_BASE_DAMAGE, CHARACTER_BASE_DEFENCE, null, null);
    }
    public Character(String name, int currentHealth, int maxHealth, int damage, int defence, Inventory inventory, EquipmentSet equipmentSet) {
        initCharacter(name, currentHealth, maxHealth, damage, defence, inventory, equipmentSet);
    }
    private void initCharacter(String name, int currentHealth, int maxHealth, int damage, int defence, Inventory inventory, EquipmentSet equipmentSet) {
        this.name = name;
        this.currentHealth = currentHealth;
        this.maxHealth = maxHealth;
        this.damage = damage;
        this.defence = defence;

        this.inventory = new Inventory(inventory);
        this.equipmentSet = new EquipmentSet(equipmentSet);

        target = null;
    }

    public String getName() { return name; }
    public int getCurrentHealth() { return currentHealth; }
    public int getMaxHealth() { return maxHealth + equipmentSet.getMaxHealthBonus(); }
    public int getDamage() { return damage + equipmentSet.getDamageBonus(); }
    public int getDefence() { return defence + equipmentSet.getDefenceBonus(); }

    public Character getTarget() { return target; }
    public Inventory getInventory() { return inventory; }
    public EquipmentSet getEquipmentSet() { return equipmentSet; }

    public abstract boolean canBeAttacked();
    public abstract boolean canBeLooted();

    // COMBAT MANAGEMENT
    public void refresh() { currentHealth = getMaxHealth(); }
    public boolean isAlive() { return currentHealth > 0; }
    public void setTarget(Character target) { this.target = target; }
    public void clearTarget() { this.target = null; }
    public int attackTarget() {
        if (target == null)
            return -1;

        return Combat.attack(this, target);
    }
    public int takeDamage(int damage) {
        int actualDamage = Math.min(damage, currentHealth);

        currentHealth -= actualDamage;

        if (currentHealth == 0)
            die();

        return actualDamage;
    }
    public boolean consumeHealthPotion() {
        boolean ret = inventory.consumeHealthPotion();

        if (ret)
            refresh();

        return ret;
    }
    public void die() { clearTarget(); }

    // INVENTORY MANAGEMENT
    public void lootEnemy(Character enemy) { Combat.loot(this, enemy); }
    public abstract Inventory beLooted();
    public void emptyInventory() { inventory = new Inventory(); }
    public void addInventory(Inventory inventory) { this.inventory.addInventory(inventory); }

    // EQUIPMENT MANAGEMENT
    public void equip(Equipment equipment) {
        inventory.addItem(equipmentSet.equip(equipment));
    }
    public void unequip(Equipment.EquipmentType equipmentType) {
        inventory.addItem(equipmentSet.unequip(equipmentType));
    }
    public boolean equipFromInventory(int invSlotIndex) {
        if (invSlotIndex < 0 || invSlotIndex >= inventory.getItems().size() || !(inventory.getItems().get(invSlotIndex) instanceof Equipment))
            return false;

        equip((Equipment)(inventory.getItems().remove(invSlotIndex)));
        return true;
    }
}
