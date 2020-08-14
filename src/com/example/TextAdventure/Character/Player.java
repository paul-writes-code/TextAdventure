package com.example.TextAdventure.Character;

import com.example.TextAdventure.Common.Strings;
import com.example.TextAdventure.Common.Utility;

import static com.example.TextAdventure.UserInterface.Output.output;

public class Player extends Character {

    public static final int PLAYER_BASE_HEALTH = 15;
    public static final int PLAYER_BASE_DAMAGE = 8;

    protected int experience;
    protected int level;
    protected int numHealthPotions;

    public Player(String name) {
        super(name, PLAYER_BASE_HEALTH, PLAYER_BASE_HEALTH, PLAYER_BASE_DAMAGE);
        numHealthPotions = 5;
        experience = 0;
        level = 1;
    }

    public int getExperience() { return experience; }
    public int getLevel() { return level; }

    public int getNumHealthPotions() { return numHealthPotions; }
    public void addHealthPotions(int quantity) { numHealthPotions += quantity; }
    public boolean consumeHealthPotion() {
        if (numHealthPotions <= 0)
            return false;

        numHealthPotions--;
        fillHealth();
        return true;
    }

    public void attackEnemy(Enemy enemy) {
        if (enemy == null)
            return;

        int damageInflicted = enemy.takeDamage(generateDamageRoll());

        // TODO: instead of "you attack x and deal 1 damage; x has 0/5 health \n you have defeated x", remove 0/5 health and put in a single line.
        // TODO: "you have defeated x, gaining y experience" instead of two separate sentences on two separate lines. its choppy.
        output(Strings.COMBAT_PLAYER_ATTACK_ENEMY, enemy.getDisplayName(), damageInflicted, enemy.getDisplayName(), enemy.getHealth(), enemy.getHitpoints());
    }

    // DISPLAY FUNCTIONS
    public void viewCharacter() {
        output(getDisplayName() + ", level " + getLevel() + " undead warrior");
        output(Strings.CHARACTER_DISPLAY_HEALTH, getHealth(), getHitpoints());
        output(Strings.CHARACTER_DISPLAY_DAMAGE, getDamage());

        if (getLevel() == 5)
            output(Strings.CHARACTER_DISPLAY_EXPERIENCE_MAX_LEVEL, getExperience());
        else
            output(Strings.CHARACTER_DISPLAY_EXPERIENCE, getExperience(), Utility.getExperienceForLevel(getLevel() + 1));

        output(Strings.CHARACTER_DISPLAY_HEALTH_POTIONS, getNumHealthPotions());
        output("");
    }

    // EXPERIENCE MANAGEMENT
    public void gainXp(int xp) {
        if (xp <= 0)
            return;

        experience += xp;
        levelUp();
    }
    private void levelUp() { // TODO: find a better way
        while (Utility.getLevelFromExperience(experience) - level > 0) {
            level++;
            hitpoints += 3; //level % 2 == 0 ? 2 : 3;
            damage += (level - 1) % 4 == 0 ? 2 : 1;
            fillHealth();
        }
    }
}
