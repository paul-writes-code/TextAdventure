package com.example.TextAdventure.UserInterface;

import com.example.TextAdventure.UserInterface.Command.CommandType;

import java.util.Scanner;

public class Input {

    private static final String COMMAND_BLANK = "";
    public static final String COMMAND_GO = "go"; // move to an adjacent location
    public static final String COMMAND_EXAMINE = "examine"; // examine the current location
    public static final String COMMAND_ATTACK = "attack"; // attack a character
    public static final String COMMAND_HEAL = "heal"; // drink a health potion
    public static final String COMMAND_LOOT = "loot"; // loot a character
    public static final String COMMAND_EQUIP = "equip"; // equip an item from inventory
    public static final String COMMAND_UNEQUIP = "unequip"; // unequip an item from equipment set
    public static final String COMMAND_BUY = "buy"; // buy an item from merchant
    public static final String COMMAND_SELL = "sell"; // sell an item to merchant
    public static final String COMMAND_INVENTORY = "inventory"; // display inventory
    public static final String COMMAND_EQUIPMENT = "equipment"; // display equipment set
    public static final String COMMAND_CHARACTER = "character"; // display character info
    public static final String COMMAND_TRADE = "trade"; // establish trade with merchant

    private static final Scanner scanner = new Scanner(System.in);
    private static Command lastCommand = null;

    public static Command nextCommand(String prompt) {
        if (prompt != null && !prompt.equals(""))
            Output.output(prompt);

        String[] input = scanner.nextLine().split(" ");
        String command = input[0];

        // Zero-argument commands
        switch (command) {
            case COMMAND_BLANK:
                return lastCommand;
            case COMMAND_EXAMINE:
                lastCommand = new Command(CommandType.EXAMINE);
                return lastCommand;
            case COMMAND_HEAL:
                lastCommand = new Command(CommandType.HEAL);
                return lastCommand;
            case COMMAND_INVENTORY:
                lastCommand = new Command(CommandType.INVENTORY);
                return lastCommand;
            case COMMAND_EQUIPMENT:
                lastCommand = new Command(CommandType.EQUIPMENT);
                return lastCommand;
            case COMMAND_CHARACTER:
                lastCommand = new Command(CommandType.CHARACTER);
                return lastCommand;
        }

        // One-argument commands
        if (input.length > 1) {
            String argument = input[1];

            switch (command) {
                case COMMAND_GO:
                    lastCommand = new Command(CommandType.GO, argument);
                    return lastCommand;
                case COMMAND_ATTACK:
                    lastCommand = new Command(CommandType.ATTACK, argument);
                    return lastCommand;
                case COMMAND_LOOT:
                    lastCommand = new Command(CommandType.LOOT, argument);
                    return lastCommand;
                case COMMAND_EQUIP:
                    lastCommand = new Command(CommandType.EQUIP, argument);
                    return lastCommand;
                case COMMAND_UNEQUIP:
                    lastCommand = new Command(CommandType.UNEQUIP, argument);
                    return lastCommand;
                case COMMAND_BUY:
                    lastCommand = new Command(CommandType.BUY, argument);
                    return lastCommand;
                case COMMAND_SELL:
                    lastCommand = new Command(CommandType.SELL, argument);
                    return lastCommand;
                case COMMAND_TRADE:
                    lastCommand = new Command(CommandType.TRADE, argument);
                    return lastCommand;
                default:
                    return null;
            }
        }
        else {
            switch (command) {
                case COMMAND_ATTACK:
                    lastCommand = new Command(CommandType.ATTACK);
                    return lastCommand;
                case COMMAND_LOOT:
                    lastCommand = new Command(CommandType.LOOT);
                    return lastCommand;
                case COMMAND_TRADE:
                    lastCommand = new Command(CommandType.TRADE);
                    return lastCommand;
                case COMMAND_GO:
                case COMMAND_EQUIP:
                case COMMAND_UNEQUIP:
                case COMMAND_BUY:
                case COMMAND_SELL:
                    Output.output("Enter '" + command + " object' to perform that action.");
                    return null;
                default:
                    Output.output("Unknown command: " + command + ".");
                    return null;
            }
        }
    }
    public static Command nextCommand() {
        return nextCommand(null);
    }

    public static Command forceCommand(CommandType requiredCommandType, String requiredCommandArgument, boolean argumentRequired) {
        Command ret = null;
        String commandString = "";
        String actionString = "";

        if (requiredCommandType == null || (argumentRequired && requiredCommandArgument == null))
            return null;

        switch (requiredCommandType) {
            case GO:
                commandString = COMMAND_GO;
                actionString = "move there";
                break;
            case EXAMINE:
                commandString = COMMAND_EXAMINE;
                actionString = "view your current location";
                break;
            case ATTACK:
                commandString = COMMAND_ATTACK;
                actionString = "attack " + requiredCommandArgument;
                break;
            case HEAL:
                commandString = COMMAND_HEAL;
                actionString =  "drink a health potion";
                break;
            case LOOT:
                commandString = COMMAND_LOOT;
                actionString = "loot " + requiredCommandArgument;
                break;
            case EQUIP:
                commandString = COMMAND_EQUIP;
                actionString = "equip that item";
                break;
            case UNEQUIP:
                commandString = COMMAND_UNEQUIP;
                actionString = "unequip that item";
                break;
            case BUY:
                commandString = COMMAND_BUY;
                actionString = "buy item from merchant";
                break;
            case SELL:
                commandString = COMMAND_SELL;
                actionString = "sell item to merchant";
                break;
            case INVENTORY:
                commandString = COMMAND_INVENTORY;
                actionString = "view your inventory";
                break;
            case EQUIPMENT:
                commandString = COMMAND_EQUIPMENT;
                actionString = "view your equipment set";
                break;
            case CHARACTER:
                commandString = COMMAND_CHARACTER;
                actionString = "view your character details";
                break;
            case TRADE:
                commandString = COMMAND_TRADE;
                actionString = "establish trade with merchant";
                break;
            default:
                return null;
        }

        if (argumentRequired)
            commandString += " " + requiredCommandArgument;

        String prompt = "Enter '" + commandString +"' to " + actionString + ": ";

        while (ret == null || !ret.getCommandType().equals(requiredCommandType) || (!ret.getArgument().equals(requiredCommandArgument) && argumentRequired))
            ret = Input.nextCommand(prompt);

        return ret;
    }
    public static Command forceCommand(CommandType requiredCommandType) {
        return forceCommand(requiredCommandType, null, false);
    }
}
