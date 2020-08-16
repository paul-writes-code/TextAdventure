package com.example.TextAdventure.Common;

import com.example.TextAdventure.UserInterface.Input;

public class Strings {

    public static final String COMMAND_SPLIT_TOKEN = " ";
    public static final String INDENTATION = "  ";

    // World
    public static final String GAME_NAME = "Undead Temple";
    public static final String WELCOME_MESSAGE = "\nWelcome to " + GAME_NAME + ".";
    public static final String CHARACTER_NAME_PROMPT = "Enter your character's name: ";
    public static final String INTRO_MESSAGE = "\nYou are %s, level %d undead warrior.\nTravel to the depths of the undead temple, and defeat the necromancer.\n";

    // Interface
    public static final String COMMAND_PROMPT = "Enter command: ";
    public static final String COMMAND_PROMPT_LAST_COMMAND = "Enter command (or press enter to %s): ";
    public static final String COMMAND_INVALID = "\nInvalid command: %s.\n";

    // Main UI
    public static final String MAIN_UI_DISPLAY_HEALTH = "health: %d/%d";
    public static final String MAIN_UI_DISPLAY_ROOM = "room: ";
    public static final String MAIN_UI_DISPLAY_COMMAND_LIST = "commands: ";
    public static final String MAIN_UI_DISPLAY_COMMAND_CHARACTER = INDENTATION + Input.COMMAND_CHARACTER + ": view character details";
    public static final String MAIN_UI_DISPLAY_COMMAND_HEAL = INDENTATION + Input.COMMAND_HEAL + ": drink a health potion (%d)";
    public static final String MAIN_UI_DISPLAY_COMMAND_GO = INDENTATION + Input.COMMAND_GO + " %s: go to this room";
    public static final String MAIN_UI_DISPLAY_COMMAND_ATTACK = INDENTATION + Input.COMMAND_ATTACK + " %s: %d/%d health";

    // Character UI
    public static final String CHARACTER_UI_DISPLAY_CHARACTER = "%s, level %d undead warrior";
    public static final String CHARACTER_UI_DISPLAY_DAMAGE = INDENTATION + "damage: %d";
    public static final String CHARACTER_UI_DISPLAY_HITPOINTS = INDENTATION + "hitpoints: %d";
    public static final String CHARACTER_UI_DISPLAY_EXPERIENCE = INDENTATION + "experience: %d/%d\n";

    // Heal Command
    public static final String COMMAND_HEAL_DRINK_HEALTH_POTION = "You drink a health potion. You have %d/%d health and %d health potions remaining.";
    public static final String COMMAND_HEAL_INSUFFICIENT_HEALTH_POTIONS = "You do not have any health potions.";

    // Go Command
    public static final String COMMAND_GO_MOVE_ROOM = "You enter the room to the %s.\n";
    public static final String COMMAND_GO_MOVE_LEVEL = "You enter %s level %d.\n";
    public static final String COMMAND_GO_MOVE_AREA = "You enter %s.\n";
    public static final String COMMAND_GO_USAGE = "\nEnter 'go room' to enter that room.\n";
    public static final String COMMAND_GO_INVALID_INPUT = "There is no '%s' to go to.\n";

    // Attack Command
    public static final String COMMAND_ATTACK_ATTACK_ENEMY = "You attack %s, dealing %d damage. %s has %d/%d health.";
    public static final String COMMAND_ATTACK_ATTACK_ENEMY_DEFEAT = "You attack %s, dealing %d damage.";
    public static final String COMMAND_ATTACK_ENEMY_RETALIATES = "%s attacks you, dealing %d damage. You have %d/%d health.";
    public static final String COMMAND_ATTACK_ENEMY_RETALIATES_DEFEAT = "%s attacks you, dealing %d damage.";
    public static final String COMMAND_ATTACK_USAGE = "Enter 'attack enemy' to attack that enemy.\n";
    public static final String COMMAND_ATTACK_INVALID_INPUT = "There is no '%s' to attack.\n";

    // Combat Results
    public static final String COMBAT_DEFEAT_ENEMY = "You defeat %s.";
    public static final String COMBAT_DEFEAT_ENEMY_HEALTH_POTION = "You defeat %s and find a health potion.";
    public static final String COMBAT_DEFEAT_NECROMANCER = "You defeat the necromancer, completing your adventure.";
    public static final String COMBAT_DEFEAT_ENEMY_EXPERIENCE = "You gain %d experience.";
    public static final String COMBAT_DEFEAT_ENEMY_EXPERIENCE_LEVEL_UP = "You gain %d experience, increasing your level to %d.";
    public static final String COMBAT_PLAYER_DEFEATED = "You have been defeated.";
}
