package com.example.TextAdventure;

import com.example.TextAdventure.Character.*;
import com.example.TextAdventure.Common.Strings;
import com.example.TextAdventure.Map.*;
import com.example.TextAdventure.UserInterface.Command;
import com.example.TextAdventure.UserInterface.Input;

import static com.example.TextAdventure.UserInterface.Output.output;

public abstract class World {

    private static String worldName = "WORLD";
    private static String playerName = "PLAYER";

    private static Room spawnRoom;

    private static Player player;
    private static Room playerRoom;

    private static boolean initialized = false;
    private static boolean playerAlive = true;

    public static void enterWorld() {
        initGame();

        output("\n" + Strings.WORLD_WELCOME, worldName);

        World.spawnPlayer();

        // TODO
        while (playerAlive) {
            output(Strings.INPUT_COMMAND);
            executeCommand(Input.nextCommand());
        }
    }

    private static void initGame() {
        if (initialized)
            return;

        spawnRoom = WorldMap.getSpawnRoom();
        initPlayer();

        initialized = true;
    }
    private static void initPlayer() {
        player = new Player(playerName);
    }

    // Character Functions

    // TODO: exit old room if necessary
    public static void spawnPlayer() {
        playerRoom = spawnRoom;
        viewRoom();
    }

    public static void movePlayer(String displayName) {
        Room.AdjacentRoom adjacentRoom = playerRoom.getAdjacentRoom(displayName);

        if (adjacentRoom == null) {
            output(Strings.WORLD_UNKNOWN_LOCATION, displayName);
            return;
        }

        playerRoom.leave();
        playerRoom = adjacentRoom.getRoom();

        viewRoom();
    }

    // TODO
    public static void attackEnemy(String enemyName) {
   /*     Enemy enemy = playerRoom.getEnemy(enemyName);

        if (enemy == null) {
            if (enemyName.equals(""))
                output(Strings.COMBAT_ATTACK_ENEMY);
            else
                output(Strings.UNKNOWN_ENTITY, enemyName);
            return;
        }

        if (!enemy.isAlive()) {
            output(Strings.COMBAT_TARGET_ALREADY_DEFEATED, enemy.getDisplayName());
            return;
        }

        if (!enemyName.equals("") && ((player.getTarget() == null)||(!enemyName.equals(player.getTarget().getName())))) {
            output(Strings.COMBAT_SETTING_TARGET, enemyName);
            player.setTarget(enemy);
        }

        player.attackTarget();

        if (!player.getTarget().isAlive()) {
            output(Strings.COMBAT_PLAYER_VICTORY, player.getTarget().getName());

            int oldLevel = player.getLevel();
            int experienceGained = player.getTarget().getExperience();

            player.gainXp(experienceGained);
            output(Strings.COMBAT_GAIN_XP, experienceGained);

            if (player.getLevel() > oldLevel)
                output(Strings.COMBAT_LEVEL_UP, player.getLevel());

            // Remove the enemy from the game if it has no loot
            if (player.getTarget().isInventoryEmpty())
                playerRoom.removeEnemy(player.getTarget().getName());
        }*/
    }

    // TODO
    public static void consumeHealthPotion() { // TODO
        if (player.consumeHealthPotion())
            output(Strings.COMBAT_PLAYER_HEALTH_POTION, player.getHealth(), player.getHitpoints(), player.getNumHealthPotions());
        else
            output(Strings.COMBAT_INSUFFICIENT_HEALTH_POTIONS);
    } // TODO


    // View Functions
    // TODO
    public static void viewRoom() {
        output("You are in " + playerRoom.getAreaName() + " level " + playerRoom.getLevelNumber() + ".");
        playerRoom.viewRoom();
    }

    // TODO
    public static void viewCharacter() {
        player.viewCharacter();
    }

    public static boolean executeCommand(Command command) {
        if (command == null)
            return false;

        output("");

        switch (command.getCommandType()) {
            case GO:
                movePlayer(command.getArgument());
                break;
            case EXAMINE:
                viewRoom();
                break;
            case ATTACK:
                attackEnemy(command.getArgument());
                break;
            case HEAL:
                consumeHealthPotion();
                break;
            case CHARACTER:
                viewCharacter();
                break;
            default:
                return false;
        }

        postCommand();
        return true;
    }

    // called after each valid command
    private static void postCommand() {
      //  playerRoom.attackCycle();
        output("");

        if (!player.isAlive()) {
            output(Strings.COMBAT_PLAYER_DEFEATED);
            playerAlive = false;
        }
    }
}
