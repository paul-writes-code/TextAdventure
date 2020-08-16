package com.example.TextAdventure.UserInterface;

public class Command {

    public enum CommandType {
        GO,
        EXAMINE,
        ATTACK,
        HEAL,
        CHARACTER,
    }

    private CommandType commandType;
    private String commandString;
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

        switch (commandType) {
            case GO:
                commandString = Input.COMMAND_GO;
                break;
            case EXAMINE:
                commandString = Input.COMMAND_EXAMINE;
                break;
            case ATTACK:
                commandString = Input.COMMAND_ATTACK;
                break;
            case HEAL:
                commandString = Input.COMMAND_HEAL;
                break;
            case CHARACTER:
                commandString = Input.COMMAND_CHARACTER;
                break;
        }
    }

    public CommandType getCommandType() { return commandType; }
    public String getArgument() { return argument; }
    public String getCommandString() { return commandString; }
}
