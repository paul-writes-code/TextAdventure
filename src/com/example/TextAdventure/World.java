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

    public static void enterWorld() {
        initGame();

        output("\n" + Strings.WORLD_WELCOME, worldName);

        World.spawnPlayer();

        // TODO: ??
        while (true) {
            output(Strings.INPUT_COMMAND);
            executeCommand(Input.nextCommand());
        }
    }

    private static void initGame() {
        if (initialized)
            return;

        spawnRoom = WorldMap.getSpawnRoom();
        player = new Player(playerName);

        initialized = true;
    }

    // Player Functions
    public static void spawnPlayer() {
        if (playerRoom != null)
            playerRoom.leave();

        playerRoom = spawnRoom;
        player.fillHealth();

        // TODO: respawn enemies on death?

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

    public static void attackEnemy(String enemyName) {
        Enemy enemy = playerRoom.getEnemy(enemyName);

        // Enemy not found
        if (enemy == null) {
            if (enemyName.equals(""))
                output(Strings.COMBAT_ATTACK_ENEMY);
            else
                output(Strings.UNKNOWN_ENTITY, enemyName);
            return;
        }

        player.attackEnemy(enemy);
        enemy.setAggressive(true);

        if (!enemy.isAlive()) {

            output(Strings.COMBAT_PLAYER_VICTORY, enemy.getDisplayName());

            // check if the enemy has a health potion, and take it
            if (Math.random() < enemy.getHealthPotionDropChance()) {
                output(Strings.COMBAT_FIND_HEALTH_POTION);
                player.addHealthPotions(1);
            }

            int oldLevel = player.getLevel();
            int experienceGained = enemy.getExperienceGiven();

            // Gain experience, possibly leveling up
            player.gainXp(experienceGained);
            output(Strings.COMBAT_GAIN_XP, experienceGained);

            if (player.getLevel() > oldLevel)
                output(Strings.COMBAT_LEVEL_UP, player.getLevel());

            // Remove the enemy from the game if it has no loot
            playerRoom.removeEnemy(enemy);

            output("");
            viewRoom();
        }
    }

    public static void consumeHealthPotion() {
        if (player.consumeHealthPotion())
            output(Strings.COMBAT_PLAYER_HEALTH_POTION, player.getHealth(), player.getHitpoints(), player.getNumHealthPotions());
        else
            output(Strings.COMBAT_INSUFFICIENT_HEALTH_POTIONS);
    }


    // View Functions
    public static void viewRoom() {
        output("You are in " + playerRoom.getAreaName() + " level " + playerRoom.getLevelNumber() + ".");
        playerRoom.viewRoom();
    }

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

        playerRoom.attackCycle(player);
        output("");

        if (!player.isAlive()) {
            output(Strings.COMBAT_PLAYER_DEFEATED);
            spawnPlayer();
        }
    }
}
