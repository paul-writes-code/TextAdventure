package com.example.TextAdventure.Character;

import com.example.TextAdventure.Common.Strings;
import com.example.TextAdventure.UserInterface.Output;

public class Enemy extends Character {

    private int experienceGiven;

    private Enemy(int id, String name, int hitpoints, int damage, int experienceGiven) {
        super(id, name, hitpoints, hitpoints, damage);
        this.experienceGiven = experienceGiven;
    }

    public void attackPlayer(Player player) {
        if (player == null)
            return;

        int damageInflicted = player.takeDamage(generateDamageRoll());

        Output.output(Strings.COMBAT_ENEMY_ATTACK_PLAYER, getDisplayName(), damageInflicted, player.getHealth(), player.getHitpoints());
    }

    public static Enemy createSkeleton() {
        return new Enemy(1, "skeleton", 5, 2, 10);
    }
    public static Enemy createBandit() {
        return new Enemy(2, "bandit", 12, 5, 20);
    }
    public static Enemy createLizard() {
        return new Enemy(3, "lizard", 20, 9, 30);
    }
    public static Enemy createDarkElf() {
        return new Enemy(4, "dark elf", 28, 13, 40);
    }
    public static Enemy createUndead() {
        return new Enemy(5, "undead", 38, 18, 50);
    }
}
