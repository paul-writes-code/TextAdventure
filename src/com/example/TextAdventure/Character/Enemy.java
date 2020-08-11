package com.example.TextAdventure.Character;

import com.example.TextAdventure.Common.Strings;
import com.example.TextAdventure.UserInterface.Output;

public class Enemy extends Character {

    public enum EnemyType {
        SKELETON,
        BANDIT,
        LIZARD,
        DARKELF,
        UNDEAD
    }

    private int experienceGiven;
    private double healthPotionDropChance;
    private boolean aggressive;

    private Enemy(String name, int hitpoints, int damage, int experienceGiven, double healthPotionDropChance, int enemyNumber) {
        super(enemyNumber > 0 ? name + enemyNumber : name, hitpoints, hitpoints, damage);
        this.experienceGiven = experienceGiven;
        this.healthPotionDropChance = healthPotionDropChance;
        aggressive = false;
    }

    public int getExperienceGiven() { return experienceGiven; }
    public double getHealthPotionDropChance() { return healthPotionDropChance; }
    public boolean isAggressive() { return aggressive; }
    public void setAggressive(boolean aggressive) { this.aggressive = aggressive; }

    public void attackPlayer(Player player) {
        if (player == null)
            return;

        int damageInflicted = player.takeDamage(generateDamageRoll());

        Output.output(Strings.COMBAT_ENEMY_ATTACK_PLAYER, getDisplayName(), damageInflicted, player.getHealth(), player.getHitpoints());
    }

    public static Enemy createEnemy(EnemyType enemyType, int enemyNumber) {
        switch (enemyType) {
            case SKELETON:
                return new Enemy("skeleton", 5, 2, 10, 0.15, enemyNumber);
            case BANDIT:
                return new Enemy("bandit", 12, 5, 20, 0.20, enemyNumber);
            case LIZARD:
                return new Enemy("lizard", 20, 9, 30, 0.25, enemyNumber);
            case DARKELF:
                return new Enemy("dark elf", 28, 13, 40, .30, enemyNumber);
            case UNDEAD:
                return new Enemy("undead", 38, 18, 50, 0.35, enemyNumber);
        }

        return null;
    }
}
