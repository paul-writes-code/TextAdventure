package com.example.TextAdventure.Character;

public class Enemy extends Character {

    public Enemy(String name, int currentHealth, int maxHealth, int damage, int defence) {
        super(name, currentHealth, maxHealth, damage, defence);
    }

    public boolean canBeAttacked() { return isAlive(); }
}
