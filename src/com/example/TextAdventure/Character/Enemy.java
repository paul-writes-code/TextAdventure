package com.example.TextAdventure.Character;

import com.example.TextAdventure.Common.Strings;
import com.example.TextAdventure.UserInterface.Output;

public class Enemy extends Character {

    public Enemy(int id, String name, int currentHealth, int maxHealth, int damage) {
        super(id, name, currentHealth, maxHealth, damage);
    }

    public void attackPlayer(Player player) {
        if (player == null)
            return;

        int damageInflicted = player.takeDamage(generateDamageRoll());

        Output.output(Strings.COMBAT_ENEMY_ATTACK_PLAYER, getDisplayName(), damageInflicted, player.getHealth(), player.getHitpoints());
    }
}
