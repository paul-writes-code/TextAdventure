package com.example.TextAdventure.Character;

import com.example.TextAdventure.Common.Strings;
import com.example.TextAdventure.Common.Utility;

import static com.example.TextAdventure.UserInterface.Output.output;

public class Player extends Character {

    public static final int PLAYER_BASE_HEALTH = 15;
    public static final int PLAYER_BASE_DAMAGE = 6;

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

        output(Strings.COMBAT_PLAYER_ATTACK_ENEMY, enemy.getDisplayName(), damageInflicted, enemy.getDisplayName(), enemy.getHealth(), enemy.getHitpoints());
    }

    // DISPLAY FUNCTIONS
    public void viewCharacter() {
        output(getDisplayName() + ", level " + getLevel() + " undead warrior");
        output(Strings.CHARACTER_DISPLAY_HEALTH, getHealth(), getHitpoints());
        output(Strings.CHARACTER_DISPLAY_DAMAGE, getDamage());
        output(Strings.CHARACTER_DISPLAY_EXPERIENCE, getExperience(), Utility.getExperienceForNextLevel(level));
        output(Strings.CHARACTER_DISPLAY_HEALTH_POTIONS, getNumHealthPotions());
        output("");
    }

    // EXPERIENCE MANAGEMENT
    public void gainXp(int xp) {
        if (xp <= 0)
            return;

        int experienceForNextLevel = Utility.getExperienceForNextLevel(level);
        experience += xp;

        while (experience >= experienceForNextLevel) {
            experience -= experienceForNextLevel;
            levelUp();
            experienceForNextLevel = Utility.getExperienceForNextLevel(level);
        }
    }

    private void levelUp() {
        level++;
        damage += 2;
        hitpoints += 5;

        switch(level) {
            case 5: // add 3 damage instead of 2
                damage++;
                break;
            case 6: // add 5 damage instead of 2
                damage += 3;
                break;
        }

        fillHealth();
    }
}
