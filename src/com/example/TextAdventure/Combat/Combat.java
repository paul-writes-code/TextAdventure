package com.example.TextAdventure.Combat;

import com.example.TextAdventure.Character.Character;
import com.example.TextAdventure.UserInterface.Output;

public class Combat {

    private static final int HIT_CHANCE = 80;
    private static final int BLOCK_CHANCE = 15;

    // Returns a random number between 1 and 100
    private static int roll() {
        return (int)(100 * Math.random() + 1);
    }

    private static boolean rollHit() {
        return roll() < HIT_CHANCE;
    }
    private static boolean rollBlock() {
        return roll() < BLOCK_CHANCE;
    }

    // The offensive roll will be between 2/3 and 4/3 of attacker's damage, or 0 if it does not land
    private static int generateDamageRoll(Character attacker, Character opponent) {
        if (!rollHit() || rollBlock())
            return 0;

        int damage = attacker.getDamage();
        double randFactor = (Math.random() + 1) * 2/3;
        double damageReductionFactor = (100 - opponent.getDefence() * 0.5) / 100;

        return (int)(damage * randFactor * damageReductionFactor);
    }

    public static int attack(Character attacker, Character opponent) {
        if (attacker == null || opponent == null)
            return -1;

        if (opponent.getTarget() == null)
            opponent.setTarget(attacker);

        int damage = opponent.takeDamage(generateDamageRoll(attacker, opponent));

        Output.output(attacker.getName() + " attacks " + opponent.getName() + " and deals " + damage + " damage; " + opponent.getName() + " has " + opponent.getCurrentHealth() + "/" + opponent.getMaxHealth() + " health.");

        return damage;
    }
}
