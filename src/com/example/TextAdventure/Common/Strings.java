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
    private static final String DISPLAY_TITLE_INVENTORY = "INVENTORY";
    private static final String DISPLAY_TITLE_EQUIPMENT = "EQUIPMENT";
    private static final String DISPLAY_TITLE_TRADE = "TRADING WITH";

    private static final String DISPLAY_NAME = "name";
    private static final String DISPLAY_HEALTH = "health";
    private static final String DISPLAY_LEVEL = "level";
    private static final String DISPLAY_DAMAGE = "damage";
    private static final String DISPLAY_DEFENCE = "defence";
    private static final String DISPLAY_EXPERIENCE = "experience";
    private static final String DISPLAY_BONUS = "bonus";

    private static final String DISPLAY_ARMOUR = "armour";
    private static final String DISPLAY_SWORD = "sword";
    private static final String DISPLAY_SHIELD = "shield";

    private static final String DISPLAY_HEALTH_BONUS = DISPLAY_HEALTH + " " + DISPLAY_BONUS;
    private static final String DISPLAY_DAMAGE_BONUS = DISPLAY_DAMAGE + " " + DISPLAY_BONUS;
    private static final String DISPLAY_DEFENCE_BONUS = DISPLAY_DEFENCE + " " + DISPLAY_BONUS;

    public static final String EMPTY = "<empty>";
    public static final String X_GOLD = "%d gold";
    public static final String X_HEALTH_POTIONS = "%d health potions";

    // World
    public static final String WORLD_WELCOME = "Welcome to %s.";
    public static final String WORLD_UNKNOWN_LOCATION = "Unknown location: %s.";
    public static final String UNKNOWN_ENTITY = "Unknown entity" + DISPLAY_STRING + ".";
    public static final String WORLD_AREA_NAME = "%s of %s";
    public static final String WORLD_REGION_NAME = "%s, of %s Region";
    public static final String WORLD_TERRITORY_NAME = "%s, of %s Territory";

    // Combat
    public static final String COMBAT_PLAYER_ATTACK_ENEMY = "You attack %s and deal %d damage; %s has %d/%d health.";
    public static final String COMBAT_ENEMY_ATTACK_PLAYER = "%s attacks you and deals %d damage; you have %d/%d health.";
    public static final String COMBAT_PLAYER_HEALTH_POTION = "You drink a health potion and now have %d/%d health; %d health potions remaining.";
    public static final String COMBAT_PLAYER_LOOT_ENEMY = "You loot %s and receive %s.";
    public static final String COMBAT_PLAYER_DEFEATED = "You have been defeated.";

    public static final String COMBAT_SETTING_TARGET = "Setting target" + DISPLAY_STRING + ".";
    public static final String COMBAT_PLAYER_VICTORY = "You have defeated %s.";
    public static final String COMBAT_GAIN_XP = "You gain %d experience.";
    public static final String COMBAT_LEVEL_UP = "You have reached level %d!";

    public static final String COMBAT_ATTACK_ENEMY = "Enter 'attack enemy' to select a target to attack.";
    public static final String COMBAT_LOOT_ENEMY = "Enter 'loot enemy' to select a target to loot.";
    public static final String COMBAT_TARGET_ALREADY_DEFEATED = "%s has already been defeated.";
    public static final String COMBAT_TARGET_NOT_DEFEATED = "%s has not been defeated yet.";
    public static final String COMBAT_INSUFFICIENT_HEALTH_POTIONS = "You do not have any health potions.";

    // Equipment
    public static final String EQUIPMENT_EQUIP_ITEM = "You equip %s.";
    public static final String EQUIPMENT_UNEQUIP_ITEM = "You unequip %s.";
    public static final String EQUIPMENT_CANNOT_EQUIP = "You cannot equip that item.";
    public static final String EQUIPMENT_DO_NOT_HAVE = "You do not have %s in your inventory.";
    public static final String EQUIPMENT_NOT_EQUIPPED = "You do not have %s equipped.";

    // Trade
    public static final String BOUGHT_FOR_GOLD = "Bought %s for %d gold.";
    public static final String SOLD_FOR_GOLD = "Sold %s for %d gold.";
    public static final String INSUFFICIENT_GOLD = "You do not have enough gold to purchase %s.";
    public static final String MERCHANT_NO_ITEM = "%s does not have %s.";
    public static final String PLAYER_NO_ITEM = "You do not have %s in your inventory.";

    public static final String TRADE_TRADE_MERCHANT = "Enter 'trade merchant' to select a merchant to trade with.";
    public static final String TRADE_CANNOT_TRADE = "You cannot trade with %s.";
    public static final String TRADE_MUST_TRADE_FIRST = "You must 'trade merchant' first";

    // View Location
    public static final String LOCATION_DISPLAY_TITLE_ENTER = "Entering " + DISPLAY_TITLE_LOCATION + ".";
    public static final String LOCATION_DISPLAY_TITLE_EXAMINE = "Your current location is " + DISPLAY_TITLE_LOCATION + ".";
    public static final String LOCATION_DISPLAY_OBJECT_GO = INDENTATION + Input.COMMAND_GO + DISPLAY_STRING;
    public static final String LOCATION_DISPLAY_OBJECT_TRADE = INDENTATION + Input.COMMAND_TRADE + DISPLAY_STRING;
    public static final String LOCATION_DISPLAY_OBJECT_ATTACK = INDENTATION + Input.COMMAND_ATTACK + DISPLAY_STRING
            + DISPLAY_DETAILS + DISPLAY_LEVEL + " " + "%d" + DISPLAY_DETAILS_SEPARATOR + "%d/%d " + DISPLAY_HEALTH;
    public static final String LOCATION_DISPLAY_OBJECT_LOOT = INDENTATION + Input.COMMAND_LOOT + DISPLAY_STRING + DISPLAY_DETAILS + "%s";

    // View Character
    public static final String CHARACTER_DISPLAY_TITLE = DISPLAY_TITLE_CHARACTER + DISPLAY;
    public static final String CHARACTER_DISPLAY_NAME = INDENTATION + DISPLAY_NAME + DISPLAY_STRING;
    public static final String CHARACTER_DISPLAY_HEALTH = INDENTATION + DISPLAY_HEALTH + DISPLAY_INT_OUT_OF_INT;
    public static final String CHARACTER_DISPLAY_LEVEL = INDENTATION + DISPLAY_LEVEL + DISPLAY_INT;
    public static final String CHARACTER_DISPLAY_DAMAGE = INDENTATION + DISPLAY_DAMAGE + DISPLAY_INT;
    public static final String CHARACTER_DISPLAY_DEFENCE = INDENTATION + DISPLAY_DEFENCE + DISPLAY_INT;
    public static final String CHARACTER_DISPLAY_EXPERIENCE = INDENTATION + DISPLAY_EXPERIENCE + DISPLAY_INT_OUT_OF_INT;
    public static final String CHARACTER_DISPLAY_EXPERIENCE_MAX_LEVEL = INDENTATION + DISPLAY_EXPERIENCE + DISPLAY_INT;

    // View Inventory
    public static final String INVENTORY_DISPLAY_TITLE = DISPLAY_TITLE_INVENTORY + DISPLAY;
    public static final String INVENTORY_DISPLAY_GOLD = INDENTATION + X_GOLD;
    public static final String INVENTORY_DISPLAY_HEALTH_POTIONS = INDENTATION + X_HEALTH_POTIONS;
    public static final String INVENTORY_DISPLAY_ITEM = INDENTATION + "%s";
    public static final String INVENTORY_DISPLAY_ITEM_EQUIP = INDENTATION + Input.COMMAND_EQUIP + DISPLAY_STRING;

    // View Equipment
    public static final String EQUIPMENT_DISPLAY_TITLE = DISPLAY_TITLE_EQUIPMENT + DISPLAY;
    public static final String EQUIPMENT_DISPLAY_HEALTH_BONUS = INDENTATION + DISPLAY_HEALTH_BONUS + DISPLAY_STRING;
    public static final String EQUIPMENT_DISPLAY_DAMAGE_BONUS = INDENTATION + DISPLAY_DAMAGE_BONUS + DISPLAY_STRING;
    public static final String EQUIPMENT_DISPLAY_DEFENCE_BONUS = INDENTATION + DISPLAY_DEFENCE_BONUS + DISPLAY_STRING;
    public static final String EQUIPMENT_DISPLAY_ARMOUR = INDENTATION + DISPLAY_ARMOUR + DISPLAY_STRING;
    public static final String EQUIPMENT_DISPLAY_SWORD = INDENTATION + DISPLAY_SWORD + DISPLAY_STRING;
    public static final String EQUIPMENT_DISPLAY_SHIELD = INDENTATION + DISPLAY_SHIELD + DISPLAY_STRING;
    public static final String EQUIPMENT_DISPLAY_EMPTY = EMPTY;

    // View Trade
    public static final String TRADE_DISPLAY_TITLE = DISPLAY_TITLE_TRADE + DISPLAY_STRING;
    public static final String TRADE_DISPLAY_GOLD = INDENTATION + "You have " + X_GOLD;
    public static final String TRADE_DISPLAY_EMPTY = INDENTATION + EMPTY;
    public static final String TRADE_DISPLAY_OBJECT_BUY = INDENTATION + Input.COMMAND_BUY + DISPLAY_STRING + DISPLAY_DETAILS + X_GOLD;
    public static final String TRADE_DISPLAY_OBJECT_SELL = INDENTATION + Input.COMMAND_SELL + DISPLAY_STRING + DISPLAY_DETAILS + X_GOLD;

    // Input / Actions
    public static final String INPUT_COMMAND = "Enter command" + DISPLAY;
    public static final String INPUT_COMMAND_ARGUMENT_ITEM = "Enter '%s item' to perform that action.";
    public static final String INPUT_COMMAND_ARGUMENT_LOCATION = "Enter '%s location' to perform that action.";
    public static final String INPUT_COMMAND_UNKNOWN = "Unknown command: '%s'.";
    public static final String INPUT_ACTION_PROMPT = "Enter '%s' to %s" + DISPLAY;
    public static final String INPUT_ACTION_GO = "move to %s";
    public static final String INPUT_ACTION_EXAMINE = "view your current location";
    public static final String INPUT_ACTION_ATTACK = "attack %s";
    public static final String INPUT_ACTION_HEAL = "drink a health potion";
    public static final String INPUT_ACTION_LOOT = "loot %s";
    public static final String INPUT_ACTION_EQUIP = "equip %s";
    public static final String INPUT_ACTION_UNEQUIP = "unequip %s";
    public static final String INPUT_ACTION_BUY = "buy %s from the merchant";
    public static final String INPUT_ACTION_SELL = "sell %s to the merchant";
    public static final String INPUT_ACTION_INVENTORY = "view your inventory";
    public static final String INPUT_ACTION_EQUIPMENT = "view your equipment set";
    public static final String INPUT_ACTION_CHARACTER = "view your character details";
    public static final String INPUT_ACTION_TRADE = "establish trade with %s";

    // Tutorial
    public static final String TUTORIAL_BEGIN = "This tutorial will cover some basic commands to interact with %s.";
    public static final String TUTORIAL_ATTACK_TARGET = "Now you can attack your target with 'attack'.";
    public static final String TUTORIAL_DEFEAT_ENEMY = "Defeating enemies gives experience and loot.";
    public static final String TUTORIAL_LOOT_TARGET = "You can enter 'loot' or 'loot bandit1' to loot bandit1.";
    public static final String TUTORIAL_ITEMS_MERCHANT = "Items in your inventory can be sold to merchants; first, a merchant must be targeted.";
    public static final String TUTORIAL_HEALTH_ZERO = "If your health reaches zero, the game will end. This concludes the tutorial.";
}
