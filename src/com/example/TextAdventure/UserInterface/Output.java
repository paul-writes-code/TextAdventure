package com.example.TextAdventure.UserInterface;

import com.example.TextAdventure.Character.Enemy;
import com.example.TextAdventure.Character.Player;
import com.example.TextAdventure.Common.Strings;
import com.example.TextAdventure.Map.Room;
import com.example.TextAdventure.World;

import java.util.ArrayList;

public class Output {

    private static ArrayList<String> outputBuffer;

    public static void addOutputToBuffer(String output, Object... args) {
        if (outputBuffer == null)
            outputBuffer = new ArrayList<>();

        outputBuffer.add(String.format(output, args));
    }

    public static void displayOutputBuffer() {
        if (outputBuffer == null)
            return;

        for (String outputString : outputBuffer)
            displayText(outputString);

        outputBuffer = null;
    }

    private static void displayText(String text, Object... args) {
        System.out.println(String.format(text, args));
    }

    public static void reloadMainUI() {
        Room playerRoom = World.getPlayerRoom();
        Player player = World.getPlayer();

        displayText("");
        displayText(Strings.MAIN_UI_DISPLAY_HEALTH, player.getHealth(), player.getHitpoints());

        if (playerRoom.getLevelNumber() == 0)
            displayText(Strings.MAIN_UI_DISPLAY_LOCATION, playerRoom.getAreaName());
        else
            displayText(Strings.MAIN_UI_DISPLAY_LOCATION, playerRoom.getAreaName() + " level " + playerRoom.getLevelNumber());

        displayText(Strings.MAIN_UI_DISPLAY_COMMAND_LIST);
        displayText(Strings.MAIN_UI_DISPLAY_COMMAND_CHARACTER);
        displayText(Strings.MAIN_UI_DISPLAY_COMMAND_HEAL, player.getNumHealthPotions());

        for (Room.AdjacentRoom adjacentRoom : playerRoom.getAdjacentRooms())
            if (adjacentRoom.getMovementType() == Room.MovementType.ROOM)
                displayText(Strings.MAIN_UI_DISPLAY_COMMAND_GO_ROOM, adjacentRoom.getDisplayName(), adjacentRoom.getDisplayName());
            else
                displayText(Strings.MAIN_UI_DISPLAY_COMMAND_GO_LEVEL, adjacentRoom.getDisplayName(), adjacentRoom.getDisplayName());

        for (Enemy enemy : playerRoom.getEnemies())
            displayText(Strings.MAIN_UI_DISPLAY_COMMAND_ATTACK, enemy.getDisplayName(), enemy.getHealth(), enemy.getHitpoints());

        displayText("");
    }
}
