package com.example.TextAdventure.Character;

import com.example.TextAdventure.Common.Strings;
import com.example.TextAdventure.Common.Utility;
import com.example.TextAdventure.UserInterface.Output;

import static com.example.TextAdventure.UserInterface.Output.output;

public class Player extends Character {

    public static final int PLAYER_ID = 0;
    public static final int PLAYER_BASE_HEALTH = 10;
    public static final int PLAYER_BASE_DAMAGE = 2;

    protected int experience;
    protected int level;
    protected int numHealthPotions;

    public Player(String name) {
        super(PLAYER_ID, name, PLAYER_BASE_HEALTH, PLAYER_BASE_HEALTH, PLAYER_BASE_DAMAGE);
        numHealthPotions = 5;
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

        output(Strings.COMBAT_PLAYER_ATTACK_ENEMY, enemy.getDisplayName(), damageInflicted, enemy.getDisplayName(), enemy.getHealth(), enemy.getHitpoints());
    }

    // DISPLAY FUNCTIONS
    public void viewCharacter() {
        output(Strings.CHARACTER_DISPLAY_TITLE);
        output(getDisplayName() + ", level " + getLevel() + " undead warrior");
        output(Strings.CHARACTER_DISPLAY_HEALTH, getHealth(), getHitpoints());
        output(Strings.CHARACTER_DISPLAY_DAMAGE, getDamage());

        if (getLevel() == 5)
            output(Strings.CHARACTER_DISPLAY_EXPERIENCE_MAX_LEVEL, getExperience());
        else
            output(Strings.CHARACTER_DISPLAY_EXPERIENCE, getExperience(), Utility.getExperienceForLevel(getLevel() + 1));
    }

    // EXPERIENCE MANAGEMENT
    public void gainXp(int xp) {
        if (xp <= 0)
            return;

        experience += xp;
        levelUp();
    }
    private void levelUp() {
        while (Utility.getLevelFromExperience(experience) - level > 0) {
            level++;
            hitpoints += level % 2 == 0 ? 2 : 3;
            damage += level == 5 ? 2 : 1;
            fillHealth();
        }
    }
}
