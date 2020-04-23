package com.example.TextAdventure.UserInterface;

import com.example.TextAdventure.World;

import java.util.Scanner;

public class Input {

    private final static String COMMAND_GO = "go"; // move from one sector to another
    private final static String COMMAND_MAP = "map"; // displays neighbouring sectors

    private final static Scanner scanner = new Scanner(System.in);

    public static void nextCommand() {
        String[] input = scanner.nextLine().split(" ");

        if (input.length == 0) {
            Output.output("Enter a command: ");
            nextCommand();
            return;
        }

        switch (input[0]) {
            case COMMAND_GO:
                World.movePlayer(input[1]);
                break;
            case COMMAND_MAP:
                World.displayLocalMap();
                break;
            default:
                Output.output("Unknown command.");
                break;
        }

        nextCommand();
    }
}
