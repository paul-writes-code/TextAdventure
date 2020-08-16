package com.example.TextAdventure.UserInterface;

import com.example.TextAdventure.Common.Strings;
import com.example.TextAdventure.UserInterface.Command.CommandType;
import com.example.TextAdventure.World;

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

    public static String getPlayerName() {
        output(Strings.CHARACTER_NAME_PROMPT);
        return scanner.nextLine().trim();
    }

    public static Command nextCommand() {
        String lastEnemyAttackedDisplayName = World.getLastEnemyAttackedDisplayName();

        // If the last attacked enemy is alive, player can press enter to attack it again
        if (!lastEnemyAttackedDisplayName.equals(""))
            output(Strings.COMMAND_PROMPT_LAST_COMMAND, COMMAND_ATTACK + " " + lastEnemyAttackedDisplayName);
        else
            output(Strings.COMMAND_PROMPT);

        // Remove excess white space from input
        String input = scanner.nextLine().trim().replaceAll(" +", " ");

        String command = "";
        String argument = "";

        if (input.contains(Strings.COMMAND_SPLIT_TOKEN)) {
            int breakPosition = input.indexOf(Strings.COMMAND_SPLIT_TOKEN);
            command = input.substring(0, breakPosition);
            argument = input.substring(breakPosition + 1);
        }
        else
            command = input;

        // Zero-argument commands
        switch (command) {

            // Can enter blank command to repeat last attack command, if the enemy exists
            case COMMAND_BLANK:
                if (!lastEnemyAttackedDisplayName.equals(""))
                    return new Command(CommandType.ATTACK, lastEnemyAttackedDisplayName);
                else
                    return null;
            case COMMAND_EXAMINE:
                return new Command(CommandType.EXAMINE);
            case COMMAND_HEAL:
                return new Command(CommandType.HEAL);
            case COMMAND_CHARACTER:
                return new Command(CommandType.CHARACTER);
        }

        // One-argument commands
        if (!argument.equals("")) {
            switch (command) {
                case COMMAND_GO:
                    return new Command(CommandType.GO, argument);
                case COMMAND_ATTACK:
                    return new Command(CommandType.ATTACK, argument);
            }
        }
        else {
            switch (command) {
                case COMMAND_GO:
                    output(Strings.COMMAND_GO_USAGE);
                    return null;
                case COMMAND_ATTACK:
                    output(Strings.COMMAND_ATTACK_USAGE);
                    return null;
            }
        }

        // Invalid input
        Output.output(Strings.COMMAND_INVALID, command);
        return null;
    }
}
