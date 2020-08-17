package com.example.TextAdventure.Character;

import com.example.TextAdventure.Common.Strings;

import static com.example.TextAdventure.UserInterface.Output.addOutputToBuffer;

public class Player extends Character {

    public static final int PLAYER_BASE_HEALTH = 15;
    public static final int PLAYER_BASE_DAMAGE = 6;

    private static final int EXPERIENCE_BASE = 250;
    private static final double EXPERIENCE_EXPONENT = 1.5;

    protected int experience;
    protected int level;
    protected int numHealthPotions;

    public Player(String name) {
        super(name, PLAYER_BASE_HEALTH, PLAYER_BASE_HEALTH, PLAYER_BASE_DAMAGE);
        numHealthPotions = 0;
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

        addOutputToBuffer(Strings.COMMAND_ATTACK_ATTACK_ENEMY, enemy.getDisplayName(), enemy.takeDamage(generateDamageRoll()));
    }

    // Display character information
    public void viewCharacter() {
        addOutputToBuffer(Strings.CHARACTER_UI_DISPLAY_CHARACTER, getDisplayName(), getLevel());
        addOutputToBuffer(Strings.CHARACTER_UI_DISPLAY_DAMAGE, getDamage());
        addOutputToBuffer(Strings.CHARACTER_UI_DISPLAY_HITPOINTS, getHitpoints());
        addOutputToBuffer(Strings.CHARACTER_UI_DISPLAY_EXPERIENCE, getExperience(), getExperienceForNextLevel(level));
    }

    // Experience Management
    // When the player levels up, the experience depletes rather than accumulating over multiple levels
    public void gainXp(int xp) {
        if (xp <= 0)
            return;

        int experienceForNextLevel = getExperienceForNextLevel(level);
        experience += xp;

        while (experience >= experienceForNextLevel) {
            experience -= experienceForNextLevel;
            levelUp();
            experienceForNextLevel = getExperienceForNextLevel(level);
        }
    }

    private void levelUp() {
        level++;
        hitpoints += 5;

        switch(level) {
            case 2:
            case 3:
            case 4:
                damage += 2;
                break;
            case 5:
                damage += 3;
                break;
            case 6:
                damage += 5;
                break;
        }

        fillHealth();
    }

    // Computes how much experience is needed to reach the next level
    // Player starts at level 1 with 0 experience
    // Level x requires (120 * (1.5)^(x - 1)) experience to reach from the previous level
    public static int getExperienceForNextLevel(int currentLevel) {
        return (int)(EXPERIENCE_BASE * Math.pow(EXPERIENCE_EXPONENT, currentLevel - 1));
    }
}
