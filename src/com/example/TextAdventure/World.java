package com.example.TextAdventure;

import com.example.TextAdventure.Character.*;
import com.example.TextAdventure.Common.Strings;
import com.example.TextAdventure.Map.*;
import com.example.TextAdventure.Trade.Trade;
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
      //  beginTutorial();

        World.spawnPlayer();

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

    private static void beginTutorial() {
 /*       output(Strings.TUTORIAL_BEGIN, worldName);

        // VIEW MAP, MOVE LOCATIONS
        executeCommand(Input.forceCommand(Command.CommandType.EXAMINE));
        executeCommand(Input.forceCommand(Command.CommandType.GO, "forest2", true));

        // COMBAT, ATTACK, HEAL
        executeCommand(Input.forceCommand(Command.CommandType.ATTACK, playerRoom.getEnemies().get(0).getName(),true));
        output(Strings.TUTORIAL_ATTACK_TARGET);

        while (playerRoom.getEnemies().get(0).isAlive())
            executeCommand(Input.forceCommand(Command.CommandType.ATTACK, playerRoom.getEnemies().get(0).getName(),false));

        executeCommand(Input.forceCommand(Command.CommandType.HEAL));

        // LOOT ENEMY, VIEW INVENTORY
        output(Strings.TUTORIAL_DEFEAT_ENEMY);
        output(Strings.TUTORIAL_LOOT_TARGET);
        executeCommand(Input.forceCommand(Command.CommandType.LOOT, playerRoom.getEnemies().get(0).getName(), false));
        executeCommand(Input.forceCommand(Command.CommandType.INVENTORY));

        // EQUIP SWORD, VIEW EQUIPMENT
        executeCommand(Input.forceCommand(Command.CommandType.EQUIP, "sword1", true));
        executeCommand(Input.forceCommand(Command.CommandType.EQUIPMENT));

        executeCommand(Input.forceCommand(Command.CommandType.ATTACK, playerRoom.getEnemies().get(0).getName(),true));

        while (playerRoom.getEnemies().get(0).isAlive())
            executeCommand(Input.forceCommand(Command.CommandType.ATTACK, playerRoom.getEnemies().get(0).getName(),false));

        // VIEW CHARACTER
        executeCommand(Input.forceCommand(Command.CommandType.CHARACTER));
        executeCommand(Input.forceCommand(Command.CommandType.UNEQUIP, "sword1", true));

        // TRADE, BUY, SELL
        output("\n" + Strings.TUTORIAL_ITEMS_MERCHANT);
        executeCommand(Input.forceCommand(Command.CommandType.TRADE, "merchant2", true));
        executeCommand(Input.forceCommand(Command.CommandType.BUY, "shield3", true));
        executeCommand(Input.forceCommand(Command.CommandType.SELL, "sword1", true));

        output(Strings.TUTORIAL_HEALTH_ZERO + "\n");

        player.setTarget(null);
        pause();*/
    }

    // Character Functions
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
        player.setTarget(null);
        playerRoom = adjacentRoom.getRoom();

        viewRoom();
    }
    public static void attackEnemy(String enemyName) {
      /*  Character target;

        if (enemyName.equals(""))
            target = player.getTarget();
        else
            target = playerRoom.getEnemy(enemyName);

        if (target == null) {
            if (enemyName.equals(""))
                output(Strings.COMBAT_ATTACK_ENEMY);
            else
                output(Strings.UNKNOWN_ENTITY, enemyName);
            return;
        }

        if (!target.isAlive()) {
            output(Strings.COMBAT_TARGET_ALREADY_DEFEATED, target.getName());
            return;
        }

        if (!enemyName.equals("") && ((player.getTarget() == null)||(!enemyName.equals(player.getTarget().getName())))) {
            output(Strings.COMBAT_SETTING_TARGET, enemyName);
            player.setTarget(target);
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
    } // TODO
    public static void consumeHealthPotion() {
        if (player.consumeHealthPotion())
            output(Strings.COMBAT_PLAYER_HEALTH_POTION, player.getCurrentHealth(), player.getMaxHealth(), player.getNumHealthPotions());
        else
            output(Strings.COMBAT_INSUFFICIENT_HEALTH_POTIONS);
    }
    public static void lootEnemy(String enemyName) {
   /*     Character lootee;

        if (enemyName.equals(""))
            lootee = player.getTarget();
        else
            lootee = playerRoom.getEnemy(enemyName);

        if (lootee == null) {
            if (enemyName.equals(""))
                output(Strings.COMBAT_LOOT_ENEMY);
            else
                output(Strings.UNKNOWN_ENTITY, enemyName);
            return;
        }

        if (lootee.isAlive()) {
            output(Strings.COMBAT_TARGET_NOT_DEFEATED, lootee.getName());
            return;
        }

        player.lootCharacter(lootee);

        if (player.getTarget() != null && player.getTarget().getName().equals(enemyName))
            player.setTarget(null);

        // Remove the enemy from the game
        playerRoom.removeEnemy(lootee.getName());*/
    } // TODO

    // View Functions
    public static void viewRoom() {
        output("You are in " + playerRoom.getAreaName() + ", level " + playerRoom.getLevelNumber() + ".");
        playerRoom.viewRoom();
    }
    public static void viewCharacter() {
        player.viewCharacter();
    }
    public static void viewInventory() {
        player.viewInventory();
    }
    public static void viewEquipment() {
        player.viewEquipmentSet();
    }

    // Equipment Functions
    public static void equipFromInventory(String itemName) {
        if (player.equipFromInventory(itemName))
            output(Strings.EQUIPMENT_EQUIP_ITEM, itemName);
        else
            output(Strings.EQUIPMENT_CANNOT_EQUIP);
    }
    public static void unequipFromEquipmentSet(String itemName) {
        if (player.unequipFromEquipmentSet(itemName))
            output(Strings.EQUIPMENT_UNEQUIP_ITEM, itemName);
        else
            output(Strings.EQUIPMENT_NOT_EQUIPPED, itemName);
    }

    // Trade Functions
    public static void tradeMerchant(String merchantName) {
      /*  Merchant merchant;

        if (merchantName.equals(""))
            if (player.getTarget() == null || player.getTarget() instanceof Merchant)
                merchant = (Merchant) player.getTarget();
            else {
                output(Strings.TRADE_CANNOT_TRADE, player.getTarget().getName());
                return;
            }
        else {
            merchant = playerRoom.getMerchant(merchantName);

            if (merchant != null) {
                player.setTarget(merchant);
            }
        }

        if (merchant == null) {
            output(merchantName.equals("") ? Strings.TRADE_TRADE_MERCHANT : Strings.UNKNOWN_ENTITY, merchantName);
            return;
        }

        Trade.viewTrade(player, merchant);*/
    }
    public static void buyFromMerchant(String itemName) {
        if (player.getTarget() == null || !(player.getTarget() instanceof Merchant)) {
            output(Strings.TRADE_MUST_TRADE_FIRST);
            return;
        }

        Trade.buyItem(player, (Merchant) player.getTarget(), itemName);

        // Re-display updated trade window
        tradeMerchant("");
    }
    public static void sellToMerchant(String itemName) {
        if (player.getTarget() == null || !(player.getTarget() instanceof Merchant)) {
            output(Strings.TRADE_MUST_TRADE_FIRST);
            return;
        }

        Trade.sellItem(player, (Merchant) player.getTarget(), itemName);

        // Re-display updated trade window
        tradeMerchant("");
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
            case LOOT:
                lootEnemy(command.getArgument());
                break;
            case EQUIP:
                equipFromInventory(command.getArgument());
                break;
            case UNEQUIP:
                unequipFromEquipmentSet(command.getArgument());
                break;
            case BUY:
                buyFromMerchant(command.getArgument());
                break;
            case SELL:
                sellToMerchant(command.getArgument());
                break;
            case INVENTORY:
                viewInventory();
                break;
            case EQUIPMENT:
                viewEquipment();
                break;
            case CHARACTER:
                viewCharacter();
                break;
            case TRADE:
                tradeMerchant(command.getArgument());
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
