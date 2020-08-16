package com.example.TextAdventure.Common;

import com.example.TextAdventure.UserInterface.Input;

public class Strings {

    public static final String COMMAND_SPLIT_TOKEN = " ";
    private static final String INDENTATION = "  ";

    // World
    public static final String WORLD_WELCOME = "Welcome to %s.";
    public static final String INPUT_COMMAND = "Enter command: ";

    // Movement
    public static final String GO_MOVE_ROOM = "You exit the room, heading %s.\n";
    public static final String GO_MOVE_LEVEL = "You enter %s level %d.\n";
    public static final String GO_MOVE_AREA = "You enter %s.\n";

    // Combat
    public static final String COMBAT_PLAYER_ATTACK_ENEMY = "You attack %s, dealing %d damage. %s has %d/%d health.";
    public static final String COMBAT_ENEMY_ATTACK_PLAYER = "%s attacks you, dealing %d damage. You have %d/%d health.";
    public static final String COMBAT_FIND_HEALTH_POTION = "You find a health potion.";
    public static final String COMBAT_PLAYER_HEALTH_POTION = "You drink a health potion.";
    public static final String COMBAT_PLAYER_DEFEATED = "You have been defeated.";
    public static final String COMBAT_PLAYER_VICTORY_XP = "You defeat %s and gain %d experience.";
    public static final String COMBAT_PLAYER_VICTORY_NO_XP = "You defeat %s.";
    public static final String COMBAT_PLAYER_VICTORY_NECROMANCER = "You have defeated the necromancer and completed the game.";
    public static final String COMBAT_LEVEL_UP = "\nLevel up! Enter 'character' to view character details.";

    // Display Room
    public static final String DISPLAY_HEALTH = "health: %d/%d";
    public static final String DISPLAY_LEVEL = "room: %s level %d";
    public static final String DISPLAY_COMMANDS = "commands: ";
    public static final String COMMAND_CHARACTER_DISPLAY = INDENTATION + Input.COMMAND_CHARACTER + ": view character details";
    public static final String COMMAND_HEAL_DISPLAY = INDENTATION + Input.COMMAND_HEAL + ": drink a health potion (%d)";
    public static final String LOCATION_DISPLAY_OBJECT_GO = INDENTATION + Input.COMMAND_GO + " %s: go to this room";
    public static final String LOCATION_DISPLAY_OBJECT_ATTACK = INDENTATION + Input.COMMAND_ATTACK + " %s: %d/%d health";

    // Display Character
    public static final String CHARACTER_DISPLAY_DAMAGE = INDENTATION + "damage: %d";
    public static final String CHARACTER_DISPLAY_HITPOINTS = INDENTATION + "hitpoints: %d";
    public static final String CHARACTER_DISPLAY_EXPERIENCE = INDENTATION + "experience: %d/%d";

    // Invalid Input
    public static final String INPUT_COMMAND_UNKNOWN = "Unknown command: '%s'.";
    public static final String WORLD_UNKNOWN_LOCATION = "Unknown location: %s.";
    public static final String UNKNOWN_ENTITY = "Unknown entity: %s.";
    public static final String COMBAT_ATTACK_ENEMY = "Enter 'attack enemy' to select an enemy to attack.";
    public static final String INPUT_COMMAND_ARGUMENT_LOCATION = "Enter 'go direction' to enter that room.";
    public static final String COMBAT_INSUFFICIENT_HEALTH_POTIONS = "You do not have any health potions.";
}
