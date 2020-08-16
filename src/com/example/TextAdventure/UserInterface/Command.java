package com.example.TextAdventure.UserInterface;

import com.example.TextAdventure.World;

import static com.example.TextAdventure.UserInterface.Output.output;

public class Command {

    public enum CommandType {
        GO,
        EXAMINE,
        ATTACK,
        HEAL,
        CHARACTER,
    }

    private CommandType commandType;
    private String argument;

    public Command(CommandType commandType) {
        initCommand(commandType, "");
    }

    public Command(CommandType commandType, String argument) {
        initCommand(commandType, argument);
    }

    private void initCommand(CommandType commandType, String argument) {
        this.commandType = commandType;
        this.argument = argument;
    }

    public CommandType getCommandType() { return commandType; }
    public String getArgument() { return argument; }

    public static boolean executeCommand(Command command) {
        if (command != null) {
            boolean validCommand = true;

            output("");

            switch (command.getCommandType()) {
                case GO:
                    validCommand = World.movePlayer(command.getArgument());
                    break;
                case EXAMINE:
                    World.examineRoom();
                    break;
                case ATTACK:
                    validCommand = World.attackEnemy(command.getArgument());
                    break;
                case HEAL:
                    validCommand = World.consumeHealthPotion();
                    break;
                case CHARACTER:
                    World.viewCharacter();
                    break;
            }

            return validCommand;
        }

        return false;
    }
}
