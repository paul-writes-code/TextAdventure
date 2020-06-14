package com.example.TextAdventure;

import com.example.TextAdventure.Character.*;
import com.example.TextAdventure.Character.Character;
import com.example.TextAdventure.Common.Strings;
import com.example.TextAdventure.Equipment.Equipment;
import com.example.TextAdventure.Item.Item;
import com.example.TextAdventure.Map.*;
import com.example.TextAdventure.Trade.Trade;
import com.example.TextAdventure.UserInterface.Command;
import com.example.TextAdventure.UserInterface.DisplayViews;
import com.example.TextAdventure.UserInterface.Input;

import static com.example.TextAdventure.UserInterface.Output.output;
import static com.example.TextAdventure.UserInterface.Output.pause;

public abstract class World {

    private static String worldName = "WORLD";
    private static String playerName = "PLAYER";

    private static Location startingLocation;
    private static Location startingLocationTutorial;

    private static Player player;
    private static Location playerLocation;

    private static boolean initialized = false;
    private static boolean playerAlive = true;

    public static void enterWorld() {
        WorldMap.initWorldMap();
        initGame();

        output("\n" + Strings.WORLD_WELCOME, worldName);
        beginTutorial();

        playerLocation = startingLocation;
        playerLocation.enter(Location.LocationNeighbour.MovementType.INIT);

        while (playerAlive) {
            output(Strings.INPUT_COMMAND);
            executeCommand(Input.nextCommand());
        }
    }

    private static void initGame() {
        if (initialized)
            return;

        startingLocation = WorldMap.getStartingLocation();
        startingLocationTutorial = WorldMap.getStartingLocationTutorial();
        initPlayer();

        initialized = true;
    }
    private static void initPlayer() {
        player = new Player(playerName);
        playerLocation = startingLocationTutorial;
     //   player.getInventory().addItem(new Equipment(Equipment.EquipmentType.SWORD, "sword", 100, 0, 2, 0));
     //   player.getInventory().addItem(new Equipment(Equipment.EquipmentType.SHIELD, "shield", 101, 3, 0, 6));
    }

    private static void beginTutorial() {
        output(Strings.TUTORIAL_BEGIN, worldName);

        // VIEW MAP, MOVE LOCATIONS
        executeCommand(Input.forceCommand(Command.CommandType.EXAMINE));
        executeCommand(Input.forceCommand(Command.CommandType.GO, WorldMap.TUTORIAL_LOCATION_2, true));

        // COMBAT, ATTACK, HEAL
        executeCommand(Input.forceCommand(Command.CommandType.ATTACK, playerLocation.getEnemies().get(0).getName(),true));
        output(Strings.TUTORIAL_ATTACK_TARGET);

        while (playerLocation.getEnemies().get(0).isAlive())
            executeCommand(Input.forceCommand(Command.CommandType.ATTACK, playerLocation.getEnemies().get(0).getName(),false));

        executeCommand(Input.forceCommand(Command.CommandType.HEAL));

        // LOOT ENEMY, VIEW INVENTORY
        output(Strings.TUTORIAL_DEFEAT_ENEMY);
        output(Strings.TUTORIAL_LOOT_TARGET);
        executeCommand(Input.forceCommand(Command.CommandType.LOOT, playerLocation.getEnemies().get(0).getName(), false));
        executeCommand(Input.forceCommand(Command.CommandType.INVENTORY));

        // EQUIP SWORD, VIEW EQUIPMENT
        executeCommand(Input.forceCommand(Command.CommandType.EQUIP, "sword1", true));
        executeCommand(Input.forceCommand(Command.CommandType.EQUIPMENT));

        executeCommand(Input.forceCommand(Command.CommandType.ATTACK, playerLocation.getEnemies().get(0).getName(),true));

        while (playerLocation.getEnemies().get(0).isAlive())
            executeCommand(Input.forceCommand(Command.CommandType.ATTACK, playerLocation.getEnemies().get(0).getName(),false));

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
        pause();
    }

    // Character Functions
    public static void movePlayer(String displayName) {
        Location.LocationNeighbour newLocation = playerLocation.getNeighbour(displayName);

        if (newLocation != null) {
            playerLocation.leave();
            player.setTarget(null);
            playerLocation = newLocation.getLocation();
            playerLocation.enter(newLocation.getMovementType());
            return;
        }

        output(Strings.WORLD_UNKNOWN_LOCATION, displayName);
    }
    public static void attackEnemy(String enemyName) {
        Character target;

        if (enemyName.equals(""))
            target = player.getTarget();
        else
            target = playerLocation.getEnemy(enemyName);

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
            if (player.getTarget().getInventory().isEmpty())
                playerLocation.removeEnemy(player.getTarget().getName());
        }
    }
    public static void consumeHealthPotion() {
        if (player.consumeHealthPotion())
            output(Strings.COMBAT_PLAYER_HEALTH_POTION, player.getCurrentHealth(), player.getMaxHealth(), player.getInventory().getNumHealthPotions());
        else
            output(Strings.COMBAT_INSUFFICIENT_HEALTH_POTIONS);
    }
    public static void lootEnemy(String enemyName) {
        Character lootee;

        if (enemyName.equals(""))
            lootee = player.getTarget();
        else
            lootee = playerLocation.getEnemy(enemyName);

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
        playerLocation.removeEnemy(lootee.getName());
    }

    // View Functions
    public static void viewLocation(boolean entering) {
        DisplayViews.viewLocation(playerLocation, entering);
    }
    public static void viewCharacter() {
        DisplayViews.viewCharacter(player);
    }
    public static void viewInventory() {
        DisplayViews.viewInventory(player.getInventory());
    }
    public static void viewEquipment() {
        DisplayViews.viewEquipmentSet(player.getEquipmentSet());
    }

    // Equipment Functions
    public static void equipFromInventory(String itemName) {
        for (Item item : player.getInventory().getItems())
            if (item.getItemName().equals(itemName)) {
                if (item instanceof Equipment) {
                    player.equip((Equipment) item);
                    output(Strings.EQUIPMENT_EQUIP_ITEM, item.getItemName());
                    player.getInventory().getItems().remove(item);
                } else
                    output(Strings.EQUIPMENT_CANNOT_EQUIP);

                return;
            }

        output(Strings.EQUIPMENT_DO_NOT_HAVE, itemName);
    }
    public static void unequipFromEquipmentSet(String itemName) {
        if (player.getEquipmentSet().getArmour() != null && player.getEquipmentSet().getArmour().getItemName().equals(itemName)) {
            player.unequip(Equipment.EquipmentType.ARMOUR);
            output(Strings.EQUIPMENT_UNEQUIP_ITEM, itemName);
        }
        else if (player.getEquipmentSet().getSword() != null && player.getEquipmentSet().getSword().getItemName().equals(itemName)) {
            player.unequip(Equipment.EquipmentType.SWORD);
            output(Strings.EQUIPMENT_UNEQUIP_ITEM, itemName);
        }
        else if (player.getEquipmentSet().getShield() != null && player.getEquipmentSet().getShield().getItemName().equals(itemName)) {
            player.unequip(Equipment.EquipmentType.SHIELD);
            output(Strings.EQUIPMENT_UNEQUIP_ITEM, itemName);
        }
        else
            output(Strings.EQUIPMENT_NOT_EQUIPPED, itemName);
    }

    // Trade Functions
    public static void tradeMerchant(String merchantName) {
        Merchant merchant;

        if (merchantName.equals(""))
            if (player.getTarget() == null || player.getTarget() instanceof Merchant)
                merchant = (Merchant) player.getTarget();
            else {
                output(Strings.TRADE_CANNOT_TRADE, player.getTarget().getName());
                return;
            }
        else {
            merchant = playerLocation.getMerchant(merchantName);

            if (merchant != null) {
                player.setTarget(merchant);
            }
        }

        if (merchant == null) {
            output(merchantName.equals("") ? Strings.TRADE_TRADE_MERCHANT : Strings.UNKNOWN_ENTITY, merchantName);
            return;
        }

        DisplayViews.viewTrade(player, merchant);
    }
    public static void buyFromMerchant(String itemName) {
        if (player.getTarget() == null || !(player.getTarget() instanceof Merchant)) {
            output(Strings.TRADE_MUST_TRADE_FIRST);
            return;
        }

        Trade.buyFromMerchant(player, (Merchant) player.getTarget(), itemName);

        // Re-display updated trade window
        tradeMerchant("");
    }
    public static void sellToMerchant(String itemName) {
        if (player.getTarget() == null || !(player.getTarget() instanceof Merchant)) {
            output(Strings.TRADE_MUST_TRADE_FIRST);
            return;
        }

        Trade.sellToMerchant(player, (Merchant) player.getTarget(), itemName);

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
                viewLocation(false);
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
        playerLocation.attackCycle();
        output("");

        if (!player.isAlive()) {
            output(Strings.COMBAT_PLAYER_DEFEATED);
            playerAlive = false;
        }
    }
}
