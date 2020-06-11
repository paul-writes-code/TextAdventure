package com.example.TextAdventure.UserInterface;

public class Command {

    public enum CommandType {
        GO,
        EXAMINE,
        ATTACK,
        HEAL,
        LOOT,
        EQUIP,
        UNEQUIP,
        BUY,
        SELL,
        INVENTORY,
        EQUIPMENT,
        CHARACTER,
        TRADE
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


}
