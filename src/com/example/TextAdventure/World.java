package com.example.TextAdventure;

import com.example.TextAdventure.Character.Player;
import com.example.TextAdventure.Map.*;
import com.example.TextAdventure.UserInterface.Input;

import static com.example.TextAdventure.UserInterface.Output.output;

public abstract class World {

    private static String worldName = "Game";

    private static Sector startingLocation;
    private static Sector playerLocation;

    private static Player player;

    private static boolean initialized = false;

    public static void enterWorld() {
        initGame();

        output("Welcome to " + worldName + ".");
        playerLocation.enter(Sector.SectorNeighbour.MovementType.INIT);
        Input.nextCommand();
    }

    private static void initGame() {
        if (initialized)
            return;

        startingLocation = WorldMap.getStartingSector();
        initPlayer();

        initialized = true;
    }
    private static void initPlayer() {
        player = new Player();
        playerLocation = startingLocation;
    }

    public static void movePlayer(String displayName) {
        Sector.SectorNeighbour newSector = playerLocation.getSectorNeighbour(displayName);

        if (newSector != null) {
            playerLocation = newSector.getSector();
            playerLocation.enter(newSector.getMovementType());
            return;
        }

        output("You cannot access '" + displayName + "' right now.");
    }
    public static void displayLocalMap() {
        output("You are standing in the " + playerLocation.getSectorName() + " sector of " + playerLocation.getArea().getAreaName() + ".");
        output("Region: " + playerLocation.getArea().getRegion().getRegionName() + ", Territory: " + playerLocation.getArea().getRegion().getTerritory().getTerritoryName());
        output("You can currently move to: ");

        for (Sector.SectorNeighbour neighbour : playerLocation.getSectorNeighbours())
            output("\t" + neighbour.getDisplayName());
    }
}
