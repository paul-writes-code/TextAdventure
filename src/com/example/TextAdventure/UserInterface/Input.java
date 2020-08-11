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
    public static final String COMMAND_CHARACTER = "character"; // display character info

    private static final Scanner scanner = new Scanner(System.in);
    private static Command lastCommand = null;

    public static Command nextCommand(String prompt) {
        if (prompt != null && !prompt.equals(""))
            Output.output(prompt);

        // Remove excess white space
        String[] input = scanner.nextLine().trim().replaceAll(" +", " ").split(TOKEN_SPLIT);

        // First token is always the command
        String command = input[0];

        // Zero-argument commands
        switch (command) {
            case COMMAND_BLANK: // Entering nothing will execute the most recently executed command
                return lastCommand;
            case COMMAND_EXAMINE:
                lastCommand = new Command(CommandType.EXAMINE);
                return lastCommand;
            case COMMAND_HEAL:
                lastCommand = new Command(CommandType.HEAL);
                return lastCommand;
            case COMMAND_CHARACTER:
                lastCommand = new Command(CommandType.CHARACTER);
                return lastCommand;
        }

        // One-argument commands
        if (input.length > 1) {
            String argument = input[1];

            for (int i = 2; i < input.length; i++)
                argument += " " + input[i];

            switch (command) {
                case COMMAND_GO:
                    lastCommand = new Command(CommandType.GO, argument);
                    return lastCommand;
                case COMMAND_ATTACK:
                    lastCommand = new Command(CommandType.ATTACK, argument);
                    return lastCommand;
                default:
                    return null;
            }
        }
        else {
            switch (command) {
                case COMMAND_GO:
                    Output.output(Strings.INPUT_COMMAND_ARGUMENT_LOCATION);
                    return null;
                case COMMAND_ATTACK:
                    lastCommand = new Command(CommandType.ATTACK);
                    return lastCommand;
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
            case CHARACTER:
                commandString = COMMAND_CHARACTER;
                actionString = Strings.INPUT_ACTION_CHARACTER;
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
