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
    public static final String COMMAND_INVENTORY = "inventory"; // display inventory

    private static final Scanner scanner = new Scanner(System.in);
    private static Command lastCommand = null;

    public static Command nextCommand(String prompt) {
        if (prompt != null)
            Output.output(prompt);

        String[] input = scanner.nextLine().split(" ");

        switch (input[0]) {
            case COMMAND_BLANK:
                return lastCommand;
            case COMMAND_GO:
                if (input.length < 2)
                    return null;
                lastCommand = new Command(CommandType.GO, input[1]);
                break;
            case COMMAND_EXAMINE:
                lastCommand = new Command(CommandType.EXAMINE, "");
                break;
            case COMMAND_ATTACK:
                lastCommand = new Command(CommandType.ATTACK, input.length > 1 ? input[1] : "");
                break;
            case COMMAND_HEAL:
                lastCommand = new Command(CommandType.HEAL, "");
                break;
            case COMMAND_LOOT:
                lastCommand = new Command(CommandType.LOOT, input.length > 1 ? input[1] : "");
                break;
            case COMMAND_INVENTORY:
                lastCommand = new Command(CommandType.INVENTORY, "");
                break;
        }

        return lastCommand;
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
            case INVENTORY:
                commandString = COMMAND_INVENTORY;
                actionString = "view your inventory";
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
