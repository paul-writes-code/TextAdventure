package com.example.TextAdventure.UserInterface;

import com.example.TextAdventure.Common.Strings;
import com.example.TextAdventure.UserInterface.Command.CommandType;

import java.util.Scanner;

public class Input {

    private static final String TOKEN_SPLIT = " ";
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

        String[] input = scanner.nextLine().split(TOKEN_SPLIT);
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
                    Output.output(Strings.INPUT_COMMAND_ARGUMENT_LOCATION);
                    return null;
                case COMMAND_EQUIP:
                case COMMAND_UNEQUIP:
                case COMMAND_BUY:
                case COMMAND_SELL:
                    Output.output(Strings.INPUT_COMMAND_ARGUMENT_ITEM, command);
                    return null;
                default:
                    Output.output(Strings.INPUT_COMMAND_UNKNOWN, command);
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
                actionString = String.format(Strings.INPUT_ACTION_GO, requiredCommandArgument);
                break;
            case EXAMINE:
                commandString = COMMAND_EXAMINE;
                actionString = Strings.INPUT_ACTION_EXAMINE;
                break;
            case ATTACK:
                commandString = COMMAND_ATTACK;
                actionString = String.format(Strings.INPUT_ACTION_ATTACK, requiredCommandArgument);
                break;
            case HEAL:
                commandString = COMMAND_HEAL;
                actionString = Strings.INPUT_ACTION_HEAL;
                break;
            case LOOT:
                commandString = COMMAND_LOOT;
                actionString = String.format(Strings.INPUT_ACTION_LOOT, requiredCommandArgument);
                break;
            case EQUIP:
                commandString = COMMAND_EQUIP;
                actionString = String.format(Strings.INPUT_ACTION_EQUIP, requiredCommandArgument);
                break;
            case UNEQUIP:
                commandString = COMMAND_UNEQUIP;
                actionString = String.format(Strings.INPUT_ACTION_UNEQUIP, requiredCommandArgument);
                break;
            case BUY:
                commandString = COMMAND_BUY;
                actionString = String.format(Strings.INPUT_ACTION_BUY, requiredCommandArgument);
                break;
            case SELL:
                commandString = COMMAND_SELL;
                actionString = String.format(Strings.INPUT_ACTION_SELL, requiredCommandArgument);
                break;
            case INVENTORY:
                commandString = COMMAND_INVENTORY;
                actionString = Strings.INPUT_ACTION_INVENTORY;
                break;
            case EQUIPMENT:
                commandString = COMMAND_EQUIPMENT;
                actionString = Strings.INPUT_ACTION_EQUIPMENT;
                break;
            case CHARACTER:
                commandString = COMMAND_CHARACTER;
                actionString = Strings.INPUT_ACTION_CHARACTER;
                break;
            case TRADE:
                commandString = COMMAND_TRADE;
                actionString = String.format(Strings.INPUT_ACTION_TRADE, requiredCommandArgument);
                break;
            default:
                return null;
        }

        if (argumentRequired)
            commandString += TOKEN_SPLIT + requiredCommandArgument;

        while (ret == null || !ret.getCommandType().equals(requiredCommandType) || (!ret.getArgument().equals(requiredCommandArgument) && argumentRequired))
            ret = Input.nextCommand(String.format(Strings.INPUT_ACTION_PROMPT, commandString, actionString));

        return ret;
    }
    public static Command forceCommand(CommandType requiredCommandType) {
        return forceCommand(requiredCommandType, null, false);
    }
}
