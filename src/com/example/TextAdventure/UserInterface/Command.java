package com.example.TextAdventure.UserInterface;

public class Command {

    public enum CommandType {
        GO,
        EXAMINE,
        ATTACK,
        HEAL,
        LOOT,
        INVENTORY
    }

    private CommandType commandType;
    private String argument;

    public Command(CommandType commandType, String argument) {
        this.commandType = commandType;
        this.argument = argument;
    }

    public CommandType getCommandType() { return commandType; }
    public String getArgument() { return argument; }


}
