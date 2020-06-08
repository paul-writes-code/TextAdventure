package com.example.TextAdventure;

import com.example.TextAdventure.Character.*;
import com.example.TextAdventure.Character.Character;
import com.example.TextAdventure.Map.*;
import com.example.TextAdventure.UserInterface.Command;
import com.example.TextAdventure.UserInterface.Input;
import com.example.TextAdventure.UserInterface.Output;

import java.util.ArrayList;

import static com.example.TextAdventure.UserInterface.Output.output;
import static com.example.TextAdventure.UserInterface.Output.pause;

public abstract class World {

    private static String worldName = "world";
    private static String playerName = "player";

    private static Location startingLocation;
    private static Location startingLocationTutorial;

    private static Player player;
    private static Location playerLocation;

    private static boolean initialized = false;
    private static boolean playerAlive = true;

    public static void enterWorld() {
        WorldMap.initWorldMap();
        initGame();

        output("Welcome to " + worldName + ".");
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
    }

    private static void beginTutorial() {
        output("This tutorial will teach you commands to interact with the world.\n");

        executeCommand(Input.forceCommand(Command.CommandType.EXAMINE));
        executeCommand(Input.forceCommand(Command.CommandType.GO, WorldMap.TUTORIAL_LOCATION_2, true));
        executeCommand(Input.forceCommand(Command.CommandType.ATTACK, playerLocation.getEnemies().get(0).getName(),true));

        pause();

        output("When you attack an enemy, it becomes aggressive and attacks you every turn until you defeat it or run away.");
        output("The enemy becomes your target, and you become the enemy's target.");
        output("You can change your target by attacking a different enemy.");
        output("Once your target has been set, you can attack it by just entering 'attack'.\n");

        while (playerLocation.getEnemies().get(0).isAlive())
            executeCommand(Input.forceCommand(Command.CommandType.ATTACK, playerLocation.getEnemies().get(0).getName(),false));

        executeCommand(Input.forceCommand(Command.CommandType.HEAL));

        output("You can enter any command during combat; try drinking health potions or running away when necessary.");
        output("This concludes the tutorial.\n");

        player.setTarget(null);
    }

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
    public static void examineLocation(boolean entering) {
        String displayName = playerLocation.getLocationName() + " of " + playerLocation.getArea().getAreaName();
        ArrayList<Enemy> attackList = new ArrayList<>();
        ArrayList<Enemy> lootList = new ArrayList<>();

        if (entering)
            Output.output("Entering " + displayName + ".");
        else
            Output.output("Your current location is " + displayName + ".");

        // Display local map
        if (playerLocation.getNeighbours() != null)
            for (Location.LocationNeighbour neighbour : playerLocation.getNeighbours())
                output("  " + Input.COMMAND_GO + ": " + neighbour.getDisplayName());

        // Display local enemies
        if (playerLocation.getEnemies() != null)
            for (Enemy enemy : playerLocation.getEnemies())
                if (enemy.getCurrentHealth() > 0)
                    attackList.add(enemy);
                else
                    lootList.add(enemy);

            for (Enemy enemy : attackList)
                output("  " + Input.COMMAND_ATTACK + ": " + enemy.getName() + "; " + enemy.getCurrentHealth() + "/" + enemy.getMaxHealth() + " health");

            for (Enemy enemy : lootList)
                output("  " + Input.COMMAND_LOOT + ": " + enemy.getName());
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
            output("Setting target to: " + enemyName + ".");
            player.setTarget(target);
        }

        player.attackTarget();

        if (!player.getTarget().isAlive()) {
            Output.output(player.getName() + " has defeated " + player.getTarget().getName() + ".");
        }
    }
    public static void consumeHealthPotion() {
        player.consumeHealthPotion();
        Output.output("You drink a health potion and now have " + player.getCurrentHealth() + "/" + player.getMaxHealth() + " health.");
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

        player.lootEnemy(lootee);
        playerLocation.removeEnemy(lootee.getName());

        if (player.getTarget() != null && player.getTarget().getName().equals(enemyName))
            player.setTarget(null);
    }

    public static boolean executeCommand(Command command) {
        output("");

        switch (command.getCommandType()) {
            case GO:
                movePlayer(command.getArgument());
                break;
            case EXAMINE:
                examineLocation(false);
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
