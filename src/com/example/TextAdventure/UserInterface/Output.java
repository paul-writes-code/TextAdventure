package com.example.TextAdventure.UserInterface;

import com.example.TextAdventure.Character.Player;
import com.example.TextAdventure.Common.Strings;
import com.example.TextAdventure.Map.Room;
import com.example.TextAdventure.World;

public class Output {

    public static void output(String output, Object... args) {
        System.out.println(String.format(output, args));
    }

    public static void reloadDisplay() {
        Room playerRoom = World.getPlayerRoom();
        Player player = World.getPlayer();

        output(Strings.MAIN_UI_DISPLAY_HEALTH, player.getHealth(), player.getHitpoints());

        if (playerRoom.getLevelNumber() == 0)
            output(playerRoom.getAreaName());
        else
            output("location: " + playerRoom.getAreaName() + " level " + playerRoom.getLevelNumber());

        output(Strings.MAIN_UI_DISPLAY_COMMAND_LIST);
     //   playerRoom.viewRoom();
        output(Strings.MAIN_UI_DISPLAY_COMMAND_CHARACTER);
        output(Strings.MAIN_UI_DISPLAY_COMMAND_HEAL, player.getNumHealthPotions());
      //  output(Strings.MAIN_UI_DISPLAY_ROOM);
        playerRoom.viewRoom();
        output("");
    }

}
