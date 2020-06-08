package com.example.TextAdventure.Character;

import com.example.TextAdventure.Combat.Combat;
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

    public Character(String name) {
        initCharacter(name, CHARACTER_BASE_HEALTH, CHARACTER_BASE_HEALTH, CHARACTER_BASE_DAMAGE, CHARACTER_BASE_DEFENCE, null);
    }
    public Character(String name, int currentHealth, int maxHealth, int damage, int defence, Inventory inventory) {
        initCharacter(name, currentHealth, maxHealth, damage, defence, inventory);
    }

    private void initCharacter(String name, int currentHealth, int maxHealth, int damage, int defence, Inventory inventory) {
        this.name = name;
        this.currentHealth = currentHealth;
        this.maxHealth = maxHealth;
        this.damage = damage;
        this.defence = defence;

        target = null;
        initInventory(inventory);
    }
    private void initInventory(Inventory inventory) {
        if (inventory == null)
            this.inventory = new Inventory();
        else
            this.inventory = new Inventory(inventory.getItems(), inventory.getGold(), inventory.getNumHealthPotions());
    }

    public String getName() { return name; }
    public int getCurrentHealth() { return currentHealth; }
    public int getMaxHealth() { return maxHealth; }
    public int getDamage() { return damage; }
    public int getDefence() { return defence; }

    public Character getTarget() { return target; }
    public Inventory getInventory() { return inventory; }

    public abstract boolean canBeAttacked();
    public abstract boolean canBeLooted();

    public void refresh() { currentHealth = maxHealth; }
    public boolean isAlive() { return currentHealth > 0; }
    public void setTarget(Character target) { this.target = target; }
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
    public void consumeHealthPotion() {
        refresh();
    }
    public void die() {
        target = null;
    }
    public abstract Inventory beLooted();
    public void emptyInventory() { initInventory(null); }
}
