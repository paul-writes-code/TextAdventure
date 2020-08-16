package com.example.TextAdventure.Character;

import com.example.TextAdventure.Common.EnemyInfo;
import com.example.TextAdventure.Common.Strings;

import static com.example.TextAdventure.UserInterface.Output.output;

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

        if (player.isAlive())
            output(Strings.COMMAND_ATTACK_ENEMY_RETALIATES, getDisplayName(), damageInflicted, player.getHealth(), player.getHitpoints());
        else {
            output(Strings.COMMAND_ATTACK_ENEMY_RETALIATES_DEFEAT, getDisplayName(), damageInflicted);
            output(Strings.COMBAT_PLAYER_DEFEATED);
            output("");
        }
    }

    public static Enemy createEnemy(EnemyType enemyType) {
        return createEnemy(enemyType, 0);
    }

    public static Enemy createEnemy(EnemyType enemyType, int enemyNumber) {
        switch (enemyType) {
            case SKELETON:
                return new Enemy(EnemyInfo.SKELETON_NAME, EnemyInfo.SKELETON_HITPOINTS, EnemyInfo.SKELETON_DAMAGE, EnemyInfo.SKELETON_EXPERIENCE, EnemyInfo.SKELETON_HEALTH_POTION_DROP_CHANCE, enemyNumber);
            case BANDIT:
                return new Enemy(EnemyInfo.BANDIT_NAME, EnemyInfo.BANDIT_HITPOINTS, EnemyInfo.BANDIT_DAMAGE, EnemyInfo.BANDIT_EXPERIENCE, EnemyInfo.BANDIT_HEALTH_POTION_DROP_CHANCE, enemyNumber);
            case LIZARD:
                return new Enemy(EnemyInfo.LIZARD_NAME, EnemyInfo.LIZARD_HITPOINTS, EnemyInfo.LIZARD_DAMAGE, EnemyInfo.LIZARD_EXPERIENCE, EnemyInfo.LIZARD_HEALTH_POTION_DROP_CHANCE, enemyNumber);
            case DARKELF:
                return new Enemy(EnemyInfo.DARK_ELF_NAME, EnemyInfo.DARK_ELF_HITPOINTS, EnemyInfo.DARK_ELF_DAMAGE, EnemyInfo.DARK_ELF_EXPERIENCE, EnemyInfo.DARK_ELF_HEALTH_POTION_DROP_CHANCE, enemyNumber);
            case UNDEAD:
                return new Enemy(EnemyInfo.UNDEAD_NAME, EnemyInfo.UNDEAD_HITPOINTS, EnemyInfo.UNDEAD_DAMAGE, EnemyInfo.UNDEAD_EXPERIENCE, EnemyInfo.UNDEAD_HEALTH_POTION_DROP_CHANCE, enemyNumber);
            case NECROMANCER:
                if (!necromancerCreated) {
                    necromancerCreated = true;
                    return new Enemy(EnemyInfo.NECROMANCER_NAME, EnemyInfo.NECROMANCER_HITPOINTS, EnemyInfo.NECROMANCER_DAMAGE, EnemyInfo.NECROMANCER_EXPERIENCE, EnemyInfo.NECROMANCER_HEALTH_POTION_DROP_CHANCE, enemyNumber);
                }

                break;
        }

        return null;
    }
}
