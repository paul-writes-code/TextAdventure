package com.example.TextAdventure.UserInterface;

import com.example.TextAdventure.World;

public class Command {

    public enum CommandType {
        GO,
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

    public static void executeCommand(Command command) {
        if (command != null) {
            switch (command.getCommandType()) {
                case GO:
                    World.movePlayer(command.getArgument());
                    break;
                case ATTACK:
                    World.attackEnemy(command.getArgument());
                    break;
                case HEAL:
                    World.consumeHealthPotion();
                    break;
                case CHARACTER:
                    World.viewCharacter();
                    break;
            }
        }
        else
            World.invalidCommand();
    }
}
