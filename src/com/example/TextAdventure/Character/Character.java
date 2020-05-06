package com.example.TextAdventure.Character;

import com.example.TextAdventure.Combat.Combat;

public abstract class Character {

    private String name;
    private int currentHealth;
    private int maxHealth;
    private int damage;
    private int defence;

    private Character target;

    public Character(String name, int currentHealth, int maxHealth, int damage, int defence) {
        this.name = name;
        this.currentHealth = currentHealth;
        this.maxHealth = maxHealth;
        this.damage = damage;
        this.defence = defence;

        target = null;
    }

    public String getName() { return name; }
    public int getCurrentHealth() { return currentHealth; }
    public int getMaxHealth() { return maxHealth; }
    public int getDamage() { return damage; }
    public int getDefence() { return defence; }
    public Character getTarget() { return target; }

    public abstract boolean canBeAttacked();
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
}
