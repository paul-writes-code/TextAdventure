package com.example.TextAdventure.Character;

public abstract class Character {

    protected String displayName;
    protected int health;
    protected int hitpoints;
    protected int damage;

    public Character(String displayName, int health, int hitpoints, int damage) {
        this.displayName = displayName;
        this.health = health;
        this.hitpoints = hitpoints;
        this.damage = damage;
    }

    public String getDisplayName() { return displayName; }
    public int getHealth() { return health; }
    public int getHitpoints() { return hitpoints; }
    public int getDamage() { return damage; }

    public int generateDamageRoll() {
        double randFactor = (Math.random() + 1) * 2/3;

        return (int)(damage * randFactor);
    }

    // Cannot take more damage than remaining health.
    public int takeDamage(int damage) {
        int actualDamage = Math.min(damage, health);

        health -= actualDamage;

        return actualDamage;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void fillHealth() {
        health = getHitpoints();
    }
}
