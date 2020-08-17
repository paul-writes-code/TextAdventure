package com.example.TextAdventure.Common;

public class Strings {

    public static final String COMMAND_SPLIT_TOKEN = " ";
    public static final String INDENTATION = "  ";

    // Introduction
    public static final String CHARACTER_NAME_PROMPT = "Enter your character's name: ";
    public static final String CHARACTER_MESSAGE = "\nYou are %s, level %d undead warrior.";
    public static final String INTRO_MESSAGE =
        "\nAfter a heavy night of celebrating, you wake up in a tomb at the bottom of a crypt." +
        "\nThe necromancer is creating an undead army, and has turned you into an undead." +
        "\nAdventure out of the crypt, through the dark forest, over the mountain, and across the enchanted swamp." +
        "\nThere, you will find the undead temple. Descend its depths, and defeat the necromancer to turn back.\n";

    // Interface
    public static final String COMMAND_PROMPT = "Enter command: ";
    public static final String COMMAND_PROMPT_LAST_COMMAND = "Enter command (or press enter to %s): ";
    public static final String COMMAND_INVALID = "Invalid command: %s.";
    public static final String COMMAND_BLANK_USAGE = "Enter one of the above commands to perform that action.";

    // Commands
    public static final String COMMAND_BLANK = ""; // repeat last attack command, if the enemy exists
    public static final String COMMAND_GO = "go"; // move to an adjacent location
    public static final String COMMAND_ATTACK = "attack"; // attack a character
    public static final String COMMAND_HEAL = "heal"; // drink a health potion
    public static final String COMMAND_CHARACTER = "character"; // display character info

    // Main UI
    public static final String MAIN_UI_DISPLAY_HEALTH = "health: %d/%d";
    public static final String MAIN_UI_DISPLAY_LOCATION = "location: %s";
    public static final String MAIN_UI_DISPLAY_COMMAND_LIST = "commands: ";
    public static final String MAIN_UI_DISPLAY_COMMAND_CHARACTER = INDENTATION + COMMAND_CHARACTER + " (view character details)";
    public static final String MAIN_UI_DISPLAY_COMMAND_HEAL = INDENTATION + COMMAND_HEAL + " (drink a health potion; %d remaining)";
    public static final String MAIN_UI_DISPLAY_COMMAND_GO_ROOM = INDENTATION + COMMAND_GO + " %s"; // (move one room %s)";
    public static final String MAIN_UI_DISPLAY_COMMAND_GO_LEVEL = INDENTATION + COMMAND_GO + " %s"; // (enter %s)";
    public static final String MAIN_UI_DISPLAY_COMMAND_ATTACK = INDENTATION + COMMAND_ATTACK + " %s (%d/%d health)";

    // Character UI
    public static final String CHARACTER_UI_DISPLAY_CHARACTER = "%s, level %d undead warrior";
    public static final String CHARACTER_UI_DISPLAY_DAMAGE = INDENTATION + "damage: %d";
    public static final String CHARACTER_UI_DISPLAY_HITPOINTS = INDENTATION + "hitpoints: %d";
    public static final String CHARACTER_UI_DISPLAY_EXPERIENCE = INDENTATION + "experience: %d/%d";

    // Heal Command
    public static final String COMMAND_HEAL_DRINK_HEALTH_POTION = "You drink a health potion.";
    public static final String COMMAND_HEAL_INSUFFICIENT_HEALTH_POTIONS = "You do not have any health potions.";

    // Go Command
    public static final String COMMAND_GO_NORTH = "north";
    public static final String COMMAND_GO_EAST = "east";
    public static final String COMMAND_GO_SOUTH = "south";
    public static final String COMMAND_GO_WEST = "west";
    public static final String COMMAND_GO_MOVE_ROOM = "You enter the room to the %s.";
    public static final String COMMAND_GO_MOVE_LEVEL = "You enter %s level %d.";
    public static final String COMMAND_GO_MOVE_AREA = "You enter %s.";
    public static final String COMMAND_GO_USAGE = "Enter 'go room' to enter that room.";
    public static final String COMMAND_GO_INVALID_INPUT = "There is no '%s' to go to.";

    // Attack Command
    public static final String COMMAND_ATTACK_ATTACK_ENEMY = "You attack %s, dealing %d damage.";
    public static final String COMMAND_ATTACK_ENEMY_RETALIATES = "%s attacks you, dealing %d damage.";
    public static final String COMMAND_ATTACK_USAGE = "Enter 'attack enemy' to attack that enemy.";
    public static final String COMMAND_ATTACK_INVALID_INPUT = "There is no '%s' to attack.";

    // Combat Results
    public static final String COMBAT_DEFEAT_ENEMY = "You defeat %s.";
    public static final String COMBAT_DEFEAT_ENEMY_HEALTH_POTION = "You defeat %s and find a health potion.";
    public static final String COMBAT_DEFEAT_NECROMANCER = "You defeat the necromancer, and turn back to normal.";
    public static final String COMBAT_DEFEAT_ENEMY_EXPERIENCE = "You gain %d experience.";
    public static final String COMBAT_DEFEAT_ENEMY_EXPERIENCE_LEVEL_UP = "You gain %d experience, increasing your level to %d.";
    public static final String COMBAT_PLAYER_DEFEATED = "You have been defeated.";

    // Names
    public static final String NAME_CRYPT = "crypt";
    public static final String NAME_DARK_FOREST = "dark forest";
    public static final String NAME_MOUNTAIN = "mountain";
    public static final String NAME_ENCHANTED_SWAMP = "enchanted swamp";
    public static final String NAME_UNDEAD_TEMPLE = "undead temple";
    public static final String NAME_BANDIT_HIDEOUT = "bandit hideout";
    public static final String NAME_LIZARD_CAVE = "lizard cave";
    public static final String NAME_DARK_ELF_CAVE = "dark elf cave";
}
