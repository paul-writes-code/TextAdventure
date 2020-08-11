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
    private boolean aggressive; // TODO: once an enemy is attacked, it becomes aggressive and attacks the player every turn while in the same room.

    private Enemy(String name, int hitpoints, int damage, int experienceGiven) {
        super(name, hitpoints, hitpoints, damage);
        this.experienceGiven = experienceGiven;
        aggressive = false;
    }

    public int getExperienceGiven() { return experienceGiven; }
    public boolean isAggressive() { return aggressive; }
    public void setAggressive(boolean aggressive) { this.aggressive = aggressive; }

    public void attackPlayer(Player player) {
        if (player == null)
            return;

        int damageInflicted = player.takeDamage(generateDamageRoll());

        Output.output(Strings.COMBAT_ENEMY_ATTACK_PLAYER, getDisplayName(), damageInflicted, player.getHealth(), player.getHitpoints());
    }

    public static Enemy createEnemy(EnemyType enemyType) {
        switch (enemyType) {
            case SKELETON:
                return new Enemy("skeleton", 5, 2, 10);
            case BANDIT:
                return new Enemy("bandit", 12, 5, 20);
            case LIZARD:
                return new Enemy("lizard", 20, 9, 30);
            case DARKELF:
                return new Enemy("dark elf", 28, 13, 40);
            case UNDEAD:
                return new Enemy("undead", 38, 18, 50);
        }

        return null;
    }
}
