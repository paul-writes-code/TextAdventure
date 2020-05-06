package com.example.TextAdventure.Character;

public class Player extends Character {

    public static final int PLAYER_BASE_HEALTH = 10;
    public static final int PLAYER_BASE_DAMAGE = 3;
    public static final int PLAYER_BASE_DEFENCE = 1;

    public Player(String name) {
        super(name, PLAYER_BASE_HEALTH, PLAYER_BASE_HEALTH, PLAYER_BASE_DAMAGE, PLAYER_BASE_DEFENCE);
    }
    public Player(String name, int currentHealth, int maxHealth, int damage, int defence) {
        super(name, currentHealth, maxHealth, damage, defence);
    }

    public boolean canBeAttacked() { return isAlive(); }
}
