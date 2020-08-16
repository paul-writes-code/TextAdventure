package com.example.TextAdventure;

import com.example.TextAdventure.Character.*;
import com.example.TextAdventure.Common.EnemyInfo;
import com.example.TextAdventure.Common.Strings;
import com.example.TextAdventure.Map.*;
import com.example.TextAdventure.UserInterface.Command;
import com.example.TextAdventure.UserInterface.Input;

import static com.example.TextAdventure.UserInterface.Output.output;
import static com.example.TextAdventure.UserInterface.Output.reloadDisplay;

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

    // Setup Game

    public static void enterWorld() {
        output(Strings.WELCOME_MESSAGE);

        initGame();
        World.spawnPlayer();
        reloadDisplay();

        while (gameRunning) {
            if (Command.executeCommand(Input.nextCommand()))
                World.postCommand();

            if (!playerInCombat)
                reloadDisplay();

        }
    }

    private static void initGame() {
        if (gameInitialized)
            return;

        output(Strings.INTRO_MESSAGE);

        spawnRoom = WorldMap.getSpawnRoom();
        player = new Player("t"); //Input.getPlayerName());

        output(Strings.CHARACTER_MESSAGE, player.getDisplayName(), player.getLevel());

        gameInitialized = true;
    }

    public static void spawnPlayer() {
        if (playerRoom != null) {
            playerRoom.leave();
        }

        playerRoom = spawnRoom;
        player.fillHealth();
        player.addHealthPotions(5);
        lastEnemyAttackedDisplayName = "";
    }

    // Player Functions

    // Reloads the UI if you wish to see it during combat
    // Otherwise it is displayed automatically every command
    public static void examineRoom() {
        if (playerInCombat)
            reloadDisplay();
    }

    // Displays the player's character information
    public static void viewCharacter() {
        player.viewCharacter();
    }

    // The player moves from playerRoom to the room denoted by displayName
    public static boolean movePlayer(String displayName) {
        Room.AdjacentRoom adjacentRoom = playerRoom.getAdjacentRoom(displayName);

        if (adjacentRoom == null) {
            output(Strings.COMMAND_GO_INVALID_INPUT, displayName);
            return false;
        }

        playerRoom.leave();
        playerRoom = adjacentRoom.getRoom();

        switch (adjacentRoom.getMovementType()) {
            case ROOM:
                output(Strings.COMMAND_GO_MOVE_ROOM, displayName);
                break;
            case LEVEL:
                output(Strings.COMMAND_GO_MOVE_LEVEL, playerRoom.getAreaName(), playerRoom.getLevelNumber());
                break;
            case AREA:
                output(Strings.COMMAND_GO_MOVE_AREA, playerRoom.getAreaName());
                break;
        }

        // Can no longer attack this enemy from another room
        lastEnemyAttackedDisplayName = "";
        return true;
    }

    // The player attacks the enemy denoted by enemyName
    public static boolean attackEnemy(String enemyName) {
        Enemy enemy = playerRoom.getEnemy(enemyName);

        // Enemy not found
        if (enemy == null) {
            if (enemyName.equals(""))
                output(Strings.COMMAND_ATTACK_USAGE);
            else
                output(Strings.COMMAND_ATTACK_INVALID_INPUT, enemyName);

            return false;
        }

        player.attackEnemy(enemy);
        enemy.setAggressive(true);

        if (!enemy.isAlive()) {

            int oldLevel = player.getLevel();
            int experienceGained = enemy.getExperienceGiven();

            player.gainXp(experienceGained);

            // Display enemy defeated, includes finding health potion
            if (enemy.getDisplayName().equals(EnemyInfo.NECROMANCER_NAME))
                output(Strings.COMBAT_DEFEAT_NECROMANCER);

            // Check for health potion
            else if (Math.random() < enemy.getHealthPotionDropChance()) {
                player.addHealthPotions(1);
                output(Strings.COMBAT_DEFEAT_ENEMY_HEALTH_POTION, enemy.getDisplayName());
            }
            else
                output(Strings.COMBAT_DEFEAT_ENEMY, enemy.getDisplayName());

            // Display experience gained, includes leveling up
            if (experienceGained > 0)
                if (player.getLevel() > oldLevel)
                    output(Strings.COMBAT_DEFEAT_ENEMY_EXPERIENCE_LEVEL_UP, experienceGained, player.getLevel());
                else
                    output(Strings.COMBAT_DEFEAT_ENEMY_EXPERIENCE, experienceGained);


            output("");

            // Remove defeated enemy from the game
            playerRoom.removeEnemy(enemy);
            lastEnemyAttackedDisplayName = "";
        }
        else
            lastEnemyAttackedDisplayName = enemy.getDisplayName();

        return true;
    }

    // The player consumes a health potion, replenishing their health
    public static boolean consumeHealthPotion() {
        if (player.consumeHealthPotion()) {
            output(Strings.COMMAND_HEAL_DRINK_HEALTH_POTION + (playerInCombat ? "" : "\n"), player.getHealth(), player.getHitpoints(), player.getNumHealthPotions());
            return true;
        }
        else {
            output(Strings.COMMAND_HEAL_INSUFFICIENT_HEALTH_POTIONS + (playerInCombat ? "" : "\n"));
            return false;
        }
    }

    // Called after each valid command
    private static void postCommand() {
        playerInCombat = playerRoom.attackCycle(player);

        if (playerInCombat)
            output("");

        if (!player.isAlive())
            spawnPlayer();

        // here is where you output the output strings from the command execution.
    }
}
