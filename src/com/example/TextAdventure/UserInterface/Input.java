package com.example.TextAdventure.UserInterface;

import com.example.TextAdventure.Common.Strings;
import com.example.TextAdventure.UserInterface.Command.CommandType;
import com.example.TextAdventure.World;

import java.util.Scanner;

import static com.example.TextAdventure.UserInterface.Output.*;

public class Input {

    private static final Scanner scanner = new Scanner(System.in);

    public static String getPlayerName() {
        addOutputToBuffer(Strings.CHARACTER_NAME_PROMPT);
        displayOutputBuffer();
        return scanner.nextLine().trim();
    }

    public static Command nextCommand() {
        String lastEnemyAttackedDisplayName = World.getLastEnemyAttackedDisplayName();

        // If the last attacked enemy is alive, player can press enter to attack it again
        if (!lastEnemyAttackedDisplayName.equals(""))
            addOutputToBuffer(Strings.COMMAND_PROMPT_LAST_COMMAND, Strings.COMMAND_ATTACK + " " + lastEnemyAttackedDisplayName);
        else
            addOutputToBuffer(Strings.COMMAND_PROMPT);

        reloadMainUI();
        displayOutputBuffer();

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
            case Strings.COMMAND_BLANK:
                if (!lastEnemyAttackedDisplayName.equals(""))
                    return new Command(CommandType.ATTACK, lastEnemyAttackedDisplayName);
                else {
                    addOutputToBuffer(Strings.COMMAND_BLANK_USAGE);
                    return null;
                }
            case Strings.COMMAND_HEAL:
                return new Command(CommandType.HEAL);
            case Strings.COMMAND_CHARACTER:
                return new Command(CommandType.CHARACTER);
        }

        // One-argument commands
        if (!argument.equals("")) {
            switch (command) {
                case Strings.COMMAND_GO:
                    return new Command(CommandType.GO, argument);
                case Strings.COMMAND_ATTACK:
                    return new Command(CommandType.ATTACK, argument);
            }
        }
        else {
            switch (command) {
                case Strings.COMMAND_GO:
                    addOutputToBuffer(Strings.COMMAND_GO_USAGE);
                    return null;
                case Strings.COMMAND_ATTACK:
                    addOutputToBuffer(Strings.COMMAND_ATTACK_USAGE);
                    return null;
            }
        }

        // Invalid input
        Output.addOutputToBuffer(Strings.COMMAND_INVALID, command);
        return null;
    }
}
