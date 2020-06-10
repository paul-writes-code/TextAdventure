package com.example.TextAdventure;

import com.example.TextAdventure.Character.*;
import com.example.TextAdventure.Character.Character;
import com.example.TextAdventure.Equipment.Equipment;
import com.example.TextAdventure.Item.Item;
import com.example.TextAdventure.Map.*;
import com.example.TextAdventure.UserInterface.Command;
import com.example.TextAdventure.UserInterface.DisplayViews;
import com.example.TextAdventure.UserInterface.Input;
import com.example.TextAdventure.UserInterface.Output;

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

        output("\nWelcome to " + worldName + ".");
        beginTutorial();

        playerLocation = startingLocation;
        playerLocation.enter(Location.LocationNeighbour.MovementType.INIT);

        while (playerAlive) {
            output("Enter command: ");
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
        output("We will review some basic commands to interact with " + worldName + ".");

        // VIEW MAP AND MOVE LOCATIONS
        executeCommand(Input.forceCommand(Command.CommandType.EXAMINE));
        executeCommand(Input.forceCommand(Command.CommandType.GO, WorldMap.TUTORIAL_LOCATION_2, true));

        // COMBAT, ATTACK, HEAL
        executeCommand(Input.forceCommand(Command.CommandType.ATTACK, playerLocation.getEnemies().get(0).getName(),true));
        output("You can attack your target with 'attack'.");

        while (playerLocation.getEnemies().get(0).isAlive())
            executeCommand(Input.forceCommand(Command.CommandType.ATTACK, playerLocation.getEnemies().get(0).getName(),false));

        executeCommand(Input.forceCommand(Command.CommandType.HEAL));
        output("You can enter any command during combat; try drinking health potions or running away when necessary.");

        // LOOT ENEMY AND VIEW INVENTORY
        output("Defeating enemies gives experience and sometimes loot.");
        output("You can enter 'loot' or 'loot bandit1' to loot bandit1.");
        executeCommand(Input.forceCommand(Command.CommandType.LOOT, playerLocation.getEnemies().get(0).getName(), false));
        executeCommand(Input.forceCommand(Command.CommandType.INVENTORY));

        // EQUIP SWORD AND VIEW EQUIPMENT
        executeCommand(Input.forceCommand(Command.CommandType.EQUIP, "sword1", true));
        executeCommand(Input.forceCommand(Command.CommandType.EQUIPMENT));


        executeCommand(Input.forceCommand(Command.CommandType.ATTACK, playerLocation.getEnemies().get(0).getName(),true));

        while (playerLocation.getEnemies().get(0).isAlive())
            executeCommand(Input.forceCommand(Command.CommandType.ATTACK, playerLocation.getEnemies().get(0).getName(),false));

        executeCommand(Input.forceCommand(Command.CommandType.CHARACTER));

        executeCommand(Input.forceCommand(Command.CommandType.UNEQUIP, "sword1", true));

        output("This concludes the tutorial.\n");

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

        output("Unknown location: " + displayName + ".");
    }
    public static void attackEnemy(String enemyName) {
        Character target;

        if (enemyName.equals(""))
            target = player.getTarget();
        else
            target = playerLocation.getEnemy(enemyName);

        if (target == null) {
            output(enemyName.equals("") ? "You have no target." : "Unknown entity: " + enemyName + ".");
            return;
        }

        if (!target.isAlive()) {
            output(target.getName() + " has already been defeated.");
            return;
        }

        if (!enemyName.equals("") && ((player.getTarget() == null)||(!enemyName.equals(player.getTarget().getName())))) {
            output("Setting target: " + enemyName + ".");
            player.setTarget(target);
        }

        player.attackTarget();

        if (!player.getTarget().isAlive()) {
            Output.output(player.getName() + " has defeated " + player.getTarget().getName() + ".");

            int oldLevel = player.getLevel();
            int experienceGained = player.getTarget().getExperience();

            player.gainXp(experienceGained);
            output("You gain " + experienceGained + " experience.");

            if (player.getLevel() > oldLevel)
                output("You have reached level " + player.getLevel() + "!");

            // Remove the enemy from the game if it has no loot
            if (player.getTarget().getInventory().isEmpty())
                playerLocation.removeEnemy(player.getTarget().getName());
        }
    }
    public static void consumeHealthPotion() {
        if (player.consumeHealthPotion())
            Output.output("You drink a health potion and now have " + player.getCurrentHealth() + "/" + player.getMaxHealth() + " health;" +
                    " You have " + player.getInventory().getNumHealthPotions() + " health potions remaining.");
        else
            Output.output("You do not have any health potions.");
    }
    public static void lootEnemy(String enemyName) {
        Character lootee;

        if (enemyName.equals(""))
            lootee = player.getTarget();
        else
            lootee = playerLocation.getEnemy(enemyName);

        if (lootee == null) {
            output(enemyName.equals("") ? "You have no target." : "Unknown entity: " + enemyName + ".");
            return;
        }

        if (lootee.isAlive()) {
            output(lootee.getName() + " has not been defeated yet.");
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
                    output("You equip " + item.getItemName() + ".");
                    player.getInventory().getItems().remove(item);
                } else
                    output("You cannot equip that item.");

                return;
            }

        output("You do not have " + itemName + ".");
    }
    public static void unequipFromEquipmentSet(String itemName) {
        if (player.getEquipmentSet().getArmour() != null && player.getEquipmentSet().getArmour().getItemName().equals(itemName)) {
            player.unequip(Equipment.EquipmentType.ARMOUR);
            output("You unequip "  + itemName + ".");
        }
        else if (player.getEquipmentSet().getSword() != null && player.getEquipmentSet().getSword().getItemName().equals(itemName)) {
            player.unequip(Equipment.EquipmentType.SWORD);
            output("You unequip " + itemName + ".");
        }
        else if (player.getEquipmentSet().getShield() != null && player.getEquipmentSet().getShield().getItemName().equals(itemName)) {
            player.unequip(Equipment.EquipmentType.SHIELD);
            output("You unequip " + itemName + ".");
        }
        else
            output(itemName + " is not equipped.");
    }

    public static boolean executeCommand(Command command) {
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
            case INVENTORY:
                viewInventory();
                break;
            case EQUIPMENT:
                viewEquipment();
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
        playerLocation.attackCycle();
        output("");

        if (!player.isAlive()) {
            output("You have been defeated.");
            playerAlive = false;
        }
    }
}
