package com.example.TextAdventure.UserInterface;

import com.example.TextAdventure.Common.Strings;
import com.example.TextAdventure.UserInterface.Command.CommandType;

import java.util.Scanner;

import static com.example.TextAdventure.UserInterface.Output.output;

public class Input {

    private static final String COMMAND_BLANK = ""; // execute most recently entered command
    public static final String COMMAND_GO = "go"; // move to an adjacent location
    public static final String COMMAND_EXAMINE = "examine"; // examine the current location
    public static final String COMMAND_ATTACK = "attack"; // attack a character
    public static final String COMMAND_HEAL = "heal"; // drink a health potion
    public static final String COMMAND_CHARACTER = "character"; // display character info

    private static final Scanner scanner = new Scanner(System.in);
    private static Command lastCommand = null;

    public static String getPlayerName() {
        output("Enter your character's name: ");

        return scanner.nextLine().trim();
    }

    public static Command nextCommand() {

        // Remove excess white space from input
        String input = scanner.nextLine().trim().replaceAll(" +", " ");

        String command = "";
        String argument = "";

        if (input.contains(Strings.COMMAND_SPLIT_TOKEN)) {
            int breakPosition = input.indexOf(Strings.COMMAND_SPLIT_TOKEN);
            command = input.substring(0, breakPosition);
            argument = input.substring(breakPosition + 1);
        }
        else {
            command = input;
        }

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
            case COMMAND_CHARACTER:
                lastCommand = new Command(CommandType.CHARACTER);
                return lastCommand;
        }

        // One-argument commands
        if (!argument.equals("")) {
            switch (command) {
                case COMMAND_GO:
                    lastCommand = new Command(CommandType.GO, argument);
                    return lastCommand;
                case COMMAND_ATTACK:
                    lastCommand = new Command(CommandType.ATTACK, argument);
                    return lastCommand;
                default:
                    Output.output(Strings.INPUT_COMMAND_UNKNOWN, command);
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
}
