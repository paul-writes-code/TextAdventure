package com.example.TextAdventure.Common;

import com.example.TextAdventure.UserInterface.Input;

public class Strings {

    private static final String INDENTATION = "  ";
    private static final String DISPLAY = ": ";
    private static final String DISPLAY_DETAILS = "; ";
    private static final String DISPLAY_DETAILS_SEPARATOR = ", ";
    private static final String DISPLAY_STRING = DISPLAY + "%s";
    private static final String DISPLAY_INT = DISPLAY + "%d";
    private static final String DISPLAY_INT_OUT_OF_INT = DISPLAY + "%d/%d";

    private static final String DISPLAY_TITLE_LOCATION = "%s of %s";
    private static final String DISPLAY_TITLE_CHARACTER = "CHARACTER";

    private static final String DISPLAY_HEALTH = "health";
    private static final String DISPLAY_LEVEL = "level";
    private static final String DISPLAY_DAMAGE = "damage";
    private static final String DISPLAY_EXPERIENCE = "experience";


    // World
    public static final String WORLD_WELCOME = "Welcome to %s.";
    public static final String WORLD_UNKNOWN_LOCATION = "Unknown location: %s.";
    public static final String UNKNOWN_ENTITY = "Unknown entity" + DISPLAY_STRING + ".";

    // Combat
    public static final String COMBAT_PLAYER_ATTACK_ENEMY = "You attack %s and deal %d damage; %s has %d/%d health.";
    public static final String COMBAT_ENEMY_ATTACK_PLAYER = "%s attacks you and deals %d damage; you have %d/%d health.";
    public static final String COMBAT_PLAYER_HEALTH_POTION = "You drink a health potion and now have %d/%d health; %d health potions remaining.";
    public static final String COMBAT_PLAYER_DEFEATED = "You have been defeated.";

    public static final String COMBAT_SETTING_TARGET = "Setting target" + DISPLAY_STRING + ".";
    public static final String COMBAT_PLAYER_VICTORY = "You have defeated %s.";
    public static final String COMBAT_GAIN_XP = "You gain %d experience.";
    public static final String COMBAT_LEVEL_UP = "You have reached level %d!";

    public static final String COMBAT_ATTACK_ENEMY = "Enter 'attack enemy' to select an enemy to attack.";
    public static final String COMBAT_TARGET_ALREADY_DEFEATED = "%s has already been defeated.";
    public static final String COMBAT_TARGET_NOT_DEFEATED = "%s has not been defeated yet.";
    public static final String COMBAT_INSUFFICIENT_HEALTH_POTIONS = "You do not have any health potions.";

    // View Location
    public static final String LOCATION_DISPLAY_OBJECT_GO = INDENTATION + Input.COMMAND_GO + DISPLAY_STRING;
    public static final String LOCATION_DISPLAY_OBJECT_ATTACK = INDENTATION + Input.COMMAND_ATTACK + DISPLAY_STRING
            + DISPLAY_DETAILS + DISPLAY_LEVEL + " " + "%d" + DISPLAY_DETAILS_SEPARATOR + "%d/%d " + DISPLAY_HEALTH;

    // View Character
    public static final String CHARACTER_DISPLAY_TITLE = DISPLAY_TITLE_CHARACTER + DISPLAY;
    public static final String CHARACTER_DISPLAY_HEALTH = INDENTATION + DISPLAY_HEALTH + DISPLAY_INT_OUT_OF_INT;
    public static final String CHARACTER_DISPLAY_DAMAGE = INDENTATION + DISPLAY_DAMAGE + DISPLAY_INT;
    public static final String CHARACTER_DISPLAY_EXPERIENCE = INDENTATION + DISPLAY_EXPERIENCE + DISPLAY_INT_OUT_OF_INT;
    public static final String CHARACTER_DISPLAY_EXPERIENCE_MAX_LEVEL = INDENTATION + DISPLAY_EXPERIENCE + DISPLAY_INT;

    // Input / Actions
    public static final String INPUT_COMMAND = "Enter command" + DISPLAY;
    public static final String INPUT_COMMAND_ARGUMENT_LOCATION = "Enter '%s location' to perform that action.";
    public static final String INPUT_COMMAND_UNKNOWN = "Unknown command: '%s'.";
    public static final String INPUT_ACTION_PROMPT = "Enter '%s' to %s" + DISPLAY;
    public static final String INPUT_ACTION_GO = "move to %s";
    public static final String INPUT_ACTION_EXAMINE = "view your current location";
    public static final String INPUT_ACTION_ATTACK = "attack %s";
    public static final String INPUT_ACTION_HEAL = "drink a health potion";
    public static final String INPUT_ACTION_CHARACTER = "view your character details";
}
