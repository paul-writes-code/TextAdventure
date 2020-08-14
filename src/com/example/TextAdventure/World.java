package com.example.TextAdventure;

import com.example.TextAdventure.Character.*;
import com.example.TextAdventure.Common.EnemyInfo;
import com.example.TextAdventure.Common.Strings;
import com.example.TextAdventure.Common.Utility;
import com.example.TextAdventure.Map.*;
import com.example.TextAdventure.UserInterface.Command;
import com.example.TextAdventure.UserInterface.Input;

import static com.example.TextAdventure.UserInterface.Output.output;

public abstract class World {

    private static String worldName = "Undead Temple";
    private static String playerName = "PLAYER";

    private static Room spawnRoom;

    private static Player player;
    private static Room playerRoom;

    private static boolean initialized = false;
    private static boolean gameRunning = true;
    private static boolean playerInCombat = false;

    public static void enterWorld() {
        output("\n" + Strings.WORLD_WELCOME, worldName);

        initGame();
        World.spawnPlayer();
        reloadDisplay();

        // TODO: "quit" command?
        while (gameRunning) {
            output(Strings.INPUT_COMMAND);
            executeCommand(Input.nextCommand());
        }
    }

    private static void initGame() {
        if (initialized)
            return;

        playerName = Input.getPlayerName();
        spawnRoom = WorldMap.getSpawnRoom();
        player = new Player(playerName);

        output("\nYou are " + player.getDisplayName() + ", level " + player.getLevel() + " undead warrior. Travel to the undead temple and defeat the necromancer.\n");

        initialized = true;
    }

    // Player Functions
    public static void spawnPlayer() {
        if (playerRoom != null)
            playerRoom.leave();

        playerRoom = spawnRoom;
        player.fillHealth();

        // TODO: respawn enemies on defeat?

      //  reloadDisplay();
    }

    public static void reloadDisplay() {
        output(Strings.DISPLAY_LEVEL, playerRoom.getAreaName(), playerRoom.getLevelNumber());
        output(Strings.DISPLAY_COMMANDS);
        output(Strings.COMMAND_CHARACTER_DISPLAY);
        output(Strings.COMMAND_HEAL_DISPLAY, player.getNumHealthPotions());
        playerRoom.viewRoom();
        output("");
    }

    public static boolean movePlayer(String displayName) {
        Room.AdjacentRoom adjacentRoom = playerRoom.getAdjacentRoom(displayName);

        if (adjacentRoom == null) {
            output(Strings.WORLD_UNKNOWN_LOCATION, displayName);
            return false;
        }

        playerRoom.leave();
        playerRoom = adjacentRoom.getRoom();

        return true;
    }

    public static boolean attackEnemy(String enemyName) {
        Enemy enemy = playerRoom.getEnemy(enemyName);

        // Enemy not found
        if (enemy == null) {
            if (enemyName.equals(""))
                output(Strings.COMBAT_ATTACK_ENEMY);
            else
                output(Strings.UNKNOWN_ENTITY, enemyName);

            return false;
        }

        player.attackEnemy(enemy);
        enemy.setAggressive(true);

        if (!enemy.isAlive()) {

            int oldLevel = player.getLevel();
            int experienceGained = enemy.getExperienceGiven();

            if (experienceGained > 0)
                output(Strings.COMBAT_PLAYER_VICTORY_XP, enemy.getDisplayName(), experienceGained);
            else if (!enemy.getDisplayName().equals(EnemyInfo.NECROMANCER_NAME))
                output(Strings.COMBAT_PLAYER_VICTORY_NO_XP, enemy.getDisplayName());
            else
                output(Strings.COMBAT_PLAYER_VICTORY_NECROMANCER);

            // Gain experience, possibly leveling up
            player.gainXp(experienceGained);

            if (player.getLevel() > oldLevel)
                output(Strings.COMBAT_LEVEL_UP, player.getLevel());

            // check if the enemy has a health potion, and take it
            if (Math.random() < enemy.getHealthPotionDropChance()) {
                output(Strings.COMBAT_FIND_HEALTH_POTION);
                player.addHealthPotions(1);
            }

            // Remove defeated enemy from the game
            playerRoom.removeEnemy(enemy);

            output("");
        }

        return true;
    }

    public static boolean consumeHealthPotion() {
        if (player.consumeHealthPotion()) {
            output(Strings.COMBAT_PLAYER_HEALTH_POTION, player.getHealth(), player.getHitpoints(), player.getNumHealthPotions());
            return true;
        }
        else {
            output(Strings.COMBAT_INSUFFICIENT_HEALTH_POTIONS);
            return false;
        }
    }

    public static void viewCharacter() {
        player.viewCharacter();
    }

    public static void executeCommand(Command command) {
        if (command == null)
            return;

        boolean validCommand = true;

        output("");

        switch (command.getCommandType()) {
            case GO:
                validCommand = movePlayer(command.getArgument());
                break;
            case EXAMINE:
                reloadDisplay();
                break;
            case ATTACK:
                validCommand = attackEnemy(command.getArgument());
                break;
            case HEAL:
                validCommand = consumeHealthPotion();
                break;
            case CHARACTER:
                viewCharacter();
                break;
        }

        if (validCommand)
            postCommand();
    }

    // called after each valid command
    private static void postCommand() {

        playerInCombat = playerRoom.attackCycle(player);

        if (!playerInCombat)
            output("");

        if (!player.isAlive()) {
            output(Strings.COMBAT_PLAYER_DEFEATED);
            spawnPlayer();
        }

        if (!playerInCombat)
            reloadDisplay();
    }
}
