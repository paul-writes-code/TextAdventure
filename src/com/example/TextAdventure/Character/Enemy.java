package com.example.TextAdventure.Character;

import com.example.TextAdventure.Common.Strings;
import com.example.TextAdventure.UserInterface.Output;

public class Enemy extends Character {

    public enum EnemyType {
        SKELETON,
        BANDIT,
        LIZARD,
        DARKELF,
        UNDEAD,
        NECROMANCER
    }

    private static boolean necromancerCreated = false;

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

    public static Enemy createEnemy(EnemyType enemyType) {
        return createEnemy(enemyType, 0);
    }

    public static Enemy createEnemy(EnemyType enemyType, int enemyNumber) {
        switch (enemyType) {
            case SKELETON:
                return new Enemy(Strings.SKELETON_NAME, 5, 2, 25, 0.15, enemyNumber);
            case BANDIT:
                return new Enemy(Strings.BANDIT_NAME, 12, 5, 20, 0.20, enemyNumber);
            case LIZARD:
                return new Enemy(Strings.LIZARD_NAME, 20, 9, 30, 0.25, enemyNumber);
            case DARKELF:
                return new Enemy(Strings.DARK_ELF_NAME, 28, 13, 40, .30, enemyNumber);
            case UNDEAD:
                return new Enemy(Strings.UNDEAD_NAME, 38, 18, 50, 0.35, enemyNumber);
            case NECROMANCER:
                if (!necromancerCreated) {
                    necromancerCreated = true;
                    return new Enemy(Strings.NECROMANCER_NAME, 120, 25, 0, 0, 0);
                }

                break;
        }

        return null;
    }
}
