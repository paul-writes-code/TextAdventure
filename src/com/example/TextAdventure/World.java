package com.example.TextAdventure;

import com.example.TextAdventure.Character.*;
import com.example.TextAdventure.Common.EnemyInfo;
import com.example.TextAdventure.Common.Strings;
import com.example.TextAdventure.Map.*;
import com.example.TextAdventure.UserInterface.Command;
import com.example.TextAdventure.UserInterface.Input;

import static com.example.TextAdventure.UserInterface.Output.output;

public abstract class World {

    private static Room spawnRoom;

    private static Player player;
    private static Room playerRoom;

    private static boolean gameInitialized = false;
    private static boolean gameRunning = true;
    private static boolean playerInCombat = false;

    public static void enterWorld() {
        output(Strings.WELCOME_MESSAGE);

        initGame();
        World.spawnPlayer();
        reloadDisplay();

        // TODO: "quit" command?
        while (gameRunning) {
            output(Strings.COMMAND_PROMPT);
            executeCommand(Input.nextCommand());
        }
    }

    private static void initGame() {
        if (gameInitialized)
            return;

        spawnRoom = WorldMap.getSpawnRoom();
        player = new Player(Input.getPlayerName());

        output(Strings.INTRO_MESSAGE, player.getDisplayName(), player.getLevel());

        gameInitialized = true;
    }

    // Player Functions
    public static void spawnPlayer() {
        if (playerRoom != null)
            playerRoom.leave();

        playerRoom = spawnRoom;
        player.fillHealth();
    }

    public static void reloadDisplay() {
        output(Strings.MAIN_UI_DISPLAY_HEALTH, player.getHitpoints(), player.getHitpoints());
        output(Strings.MAIN_UI_DISPLAY_ROOM, playerRoom.getAreaName(), playerRoom.getLevelNumber());
        output(Strings.MAIN_UI_DISPLAY_COMMAND_LIST);
        output(Strings.MAIN_UI_DISPLAY_COMMAND_CHARACTER);
        output(Strings.MAIN_UI_DISPLAY_COMMAND_HEAL, player.getNumHealthPotions());
        playerRoom.viewRoom();
        output("");
    }

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

        return true;
    }

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
        }

        return true;
    }

    public static boolean consumeHealthPotion() {
        if (player.consumeHealthPotion()) {
            output(Strings.COMMAND_HEAL_DRINK_HEALTH_POTION, player.getHealth(), player.getHitpoints(), player.getNumHealthPotions());
            return true;
        }
        else {
            output(Strings.COMMAND_HEAL_INSUFFICIENT_HEALTH_POTIONS);
            return false;
        }
    }

    public static void viewCharacter() {
        player.viewCharacter();
    }

    // TODO: move to UI package
    public static void executeCommand(Command command) {
        if (command != null) {
            boolean validCommand = true;

            output("");

            switch (command.getCommandType()) {
                case GO:
                    validCommand = movePlayer(command.getArgument());
                    break;
                case EXAMINE:
                    if (playerInCombat)
                        reloadDisplay();

                    break;
                case ATTACK:
                    validCommand = attackEnemy(command.getArgument());
                    break;
                case HEAL:
                    validCommand = consumeHealthPotion();

                    if (!playerInCombat)
                        output("");

                    break;
                case CHARACTER:
                    viewCharacter();

                    break;
            }

            if (validCommand)
                postCommand();
        }

        if (!playerInCombat)
            reloadDisplay();
    }

    // Called after each valid command
    private static void postCommand() {
        playerInCombat = playerRoom.attackCycle(player);

        if (playerInCombat)
            output("");

        if (!player.isAlive()) {
            spawnPlayer();
        }
    }
}
