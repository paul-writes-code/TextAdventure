package com.example.TextAdventure;

import com.example.TextAdventure.Character.*;
import com.example.TextAdventure.Common.EnemyInfo;
import com.example.TextAdventure.Common.Strings;
import com.example.TextAdventure.Map.*;
import com.example.TextAdventure.UserInterface.Command;
import com.example.TextAdventure.UserInterface.Input;

import static com.example.TextAdventure.UserInterface.Output.*;

public abstract class World {

    private static Room spawnRoom;

    private static Player player;
    private static Room playerRoom;

    private static boolean gameInitialized = false;
    private static boolean gameRunning = true;

    private static boolean playerInCombat = false;
    private static String lastEnemyAttackedDisplayName = "";

    public static Player getPlayer() { return player; }
    public static Room getPlayerRoom() { return playerRoom; }

    public static String getLastEnemyAttackedDisplayName() { return lastEnemyAttackedDisplayName; }

    // Run the game world
    public static void runGame() {

        initGame();
        World.spawnPlayer();

        while (gameRunning) {
            Command.executeCommand(Input.nextCommand());
            World.postCommand();
        }
    }

    // Initialize the game world
    private static void initGame() {
        if (gameInitialized)
            return;

        addOutputToBuffer(Strings.INTRO_MESSAGE);

        spawnRoom = WorldMap.getSpawnRoom();
        player = new Player(Input.getPlayerName());

        addOutputToBuffer(Strings.CHARACTER_MESSAGE, player.getDisplayName(), player.getLevel());
        displayOutputBuffer();

        gameInitialized = true;
    }

    public static void spawnPlayer() {
        if (playerRoom != null)
            playerRoom.leave();

        playerRoom = spawnRoom;
        player.fillHealth();
        player.addHealthPotions(5);
        lastEnemyAttackedDisplayName = "";
    }

    // Player Functions

    // Displays the player's character information
    public static void viewCharacter() {
        player.viewCharacter();

        if (playerInCombat)
            addOutputToBuffer("");
    }

    // The player moves from playerRoom to the room denoted by displayName
    public static void movePlayer(String displayName) {
        Room.AdjacentRoom adjacentRoom = playerRoom.getAdjacentRoom(displayName);

        if (adjacentRoom == null) {
            addOutputToBuffer(Strings.COMMAND_GO_INVALID_INPUT, displayName);
            return;
        }

        playerRoom.leave();
        playerRoom = adjacentRoom.getRoom();

        switch (adjacentRoom.getMovementType()) {
            case ROOM:
                addOutputToBuffer(Strings.COMMAND_GO_MOVE_ROOM, displayName);
                break;
            case LEVEL:
                addOutputToBuffer(Strings.COMMAND_GO_MOVE_LEVEL, playerRoom.getAreaName(), playerRoom.getLevelNumber());
                break;
            case AREA:
                addOutputToBuffer(Strings.COMMAND_GO_MOVE_AREA, playerRoom.getAreaName());
                break;
        }

        // Can no longer attack this enemy from another room
        lastEnemyAttackedDisplayName = "";
    }

    // The player attacks the enemy denoted by enemyName
    public static void attackEnemy(String enemyName) {
        Enemy enemy = playerRoom.getEnemy(enemyName);

        // Enemy not found
        if (enemy == null) {
            if (enemyName.equals(""))
                addOutputToBuffer(Strings.COMMAND_ATTACK_USAGE);
            else
                addOutputToBuffer(Strings.COMMAND_ATTACK_INVALID_INPUT, enemyName);

            return;
        }

        player.attackEnemy(enemy);
        enemy.setAggressive(true);

        if (!enemy.isAlive()) {

            int oldLevel = player.getLevel();
            int experienceGained = enemy.getExperienceGiven();

            player.gainXp(experienceGained);

            // Display enemy defeated, includes finding health potion
            if (enemy.getDisplayName().equals(EnemyInfo.NECROMANCER_NAME))
                addOutputToBuffer(Strings.COMBAT_DEFEAT_NECROMANCER);

            // Check for health potion
            else if (Math.random() < enemy.getHealthPotionDropChance()) {
                player.addHealthPotions(1);
                addOutputToBuffer(Strings.COMBAT_DEFEAT_ENEMY_HEALTH_POTION, enemy.getDisplayName());
            }
            else
                addOutputToBuffer(Strings.COMBAT_DEFEAT_ENEMY, enemy.getDisplayName());

            // Display experience gained, includes leveling up
            if (experienceGained > 0)
                if (player.getLevel() > oldLevel)
                    addOutputToBuffer(Strings.COMBAT_DEFEAT_ENEMY_EXPERIENCE_LEVEL_UP, experienceGained, player.getLevel());
                else
                    addOutputToBuffer(Strings.COMBAT_DEFEAT_ENEMY_EXPERIENCE, experienceGained);

            // Remove defeated enemy from the game
            playerRoom.removeEnemy(enemy);
            lastEnemyAttackedDisplayName = "";
        }
        else
            lastEnemyAttackedDisplayName = enemy.getDisplayName();
    }

    // The player consumes a health potion, replenishing their health
    public static void consumeHealthPotion() {
        if (player.consumeHealthPotion())
            addOutputToBuffer(Strings.COMMAND_HEAL_DRINK_HEALTH_POTION);
        else
            addOutputToBuffer(Strings.COMMAND_HEAL_INSUFFICIENT_HEALTH_POTIONS);
    }

    // Called after every command
    private static void postCommand() {
        playerInCombat = playerRoom.attackCycle(player);
        addOutputToBuffer("");

        if (!player.isAlive()) {
            addOutputToBuffer(Strings.COMBAT_PLAYER_DEFEATED);
            addOutputToBuffer("");
            spawnPlayer();
        }
    }
}
