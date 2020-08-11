package com.example.TextAdventure.Map;

import com.example.TextAdventure.Common.Strings;

import java.util.ArrayList;

import static com.example.TextAdventure.UserInterface.Output.output;

public class Room {

    public enum MovementType {
        ROOM, // the player moves between rooms within the same level
        LEVEL, // the player moves between levels
        AREA // the player moves from one area to another
    }

    public static class AdjacentRoom {
        private Room room;
        private String displayName; // The name of the room the player sees.
        private MovementType movementType; // Whether this adjacent room is in a different level or area.

        // First pass stores the room adjacency information in temporary placeholder Room objects, as not all room objects have been created at this point.
        private AdjacentRoom(String areaName, int levelNumber, int xCoordinate, int yCoordinate, Room originalRoom) {
            room = new Room(areaName, levelNumber, xCoordinate, yCoordinate);
            initMovementInfo(originalRoom);
        }

        private AdjacentRoom(Room adjacentRoom, Room originalRoom) {
            room = adjacentRoom;
            initMovementInfo(originalRoom);
        }

        public Room getRoom() { return room; }
        public MovementType getMovementType() { return movementType; }
        public String getDisplayName() { return displayName; }

        private void initMovementInfo(Room originalRoom) {
            if (!room.areaName.equals(originalRoom.areaName)) { // two rooms in different areas
                movementType = MovementType.AREA;
                displayName = room.areaName;
            }
            else if (room.levelNumber != originalRoom.levelNumber) { // same area, different levels
                movementType = MovementType.LEVEL;
                displayName = room.areaName + " level " + room.levelNumber;
            }
            else { // same level, different room
                movementType = MovementType.ROOM;

                if (originalRoom.x < room.x)
                    displayName = "east";
                else if (originalRoom.x > room.x)
                    displayName = "west";
                else if (originalRoom.y < room.y)
                    displayName = "north";
                else
                    displayName = "south";
            }
        }
    }

    private String areaName;
    private int levelNumber;
    private int x; // x-coordinate in level
    private int y; // y-coordinate in level

    public AdjacentRoom getAdjacentRoom(String displayName) {
        for (AdjacentRoom room : adjacentRooms)
            if (room.displayName.equals(displayName))
                return room;

        return null;
    }

    private ArrayList<AdjacentRoom> adjacentRooms;

    private Room(String areaName, int levelNumber, int x, int y) {
        initRoomInfo(areaName, levelNumber, x, y);
    }

    public Room(String areaName, int levelNumber, int x, int y, String[] adjacentRoomStrings) {
        initRoomInfo(areaName, levelNumber, x, y);

        adjacentRooms = new ArrayList<>();

        // These are the adjacent rooms within the same level
        for (int i = 0; i < adjacentRoomStrings.length; i++) {

            // adjacentRoomStrings[i] is "xy"
            int xAdj = Integer.parseInt(adjacentRoomStrings[i].substring(0, 1));
            int yAdj = Integer.parseInt(adjacentRoomStrings[i].substring(1));

            // Create a placeholder room to store adjacency information (first pass).
            adjacentRooms.add(new AdjacentRoom(areaName, levelNumber, xAdj, yAdj, this));
        }
    }

    private void initRoomInfo(String areaName, int levelNumber, int x, int y) {
        this.areaName = areaName;
        this.levelNumber = levelNumber;
        this.x = x;
        this.y = y;
    }

    public String getAreaName() { return areaName; }
    public int getLevelNumber() { return levelNumber; }
    public int getX() { return x; }
    public int getY() { return y; }

    // Second pass replaces temporary Room objects with pointers to real rooms, once all the rooms have been created.
    public void finalizeAdjacentRooms(Level level) {
        for (AdjacentRoom adjacentRoom : adjacentRooms)
            for (Room room : level.getRooms())
                if (adjacentRoom.room.equals(room))
                    adjacentRoom.room = room;
    }

    public void connectToRoom(Room room) {
        adjacentRooms.add(new AdjacentRoom(room, this));
    }

    @Override
    public boolean equals(Object object) {
        if (object == null)
            return false;

        return object instanceof Room
                && ((Room) object).areaName.equals(areaName)
                && ((Room) object).levelNumber == levelNumber
                && ((Room) object).x == x
                && ((Room) object).y == y;
    }

    public void viewRoom() {

        // Display local map
        if (adjacentRooms != null)
            for (AdjacentRoom adjacentRoom : adjacentRooms)
                output(Strings.LOCATION_DISPLAY_OBJECT_GO, adjacentRoom.displayName);

    }

    // TODO
    public void leave() {
     /*   if (enemies == null || enemies.size() == 0)
            return;

        for (Enemy enemy : enemies)
            if (enemy.getTarget() != null)
                enemy.setTarget(null);*/
    }

    // TODO: tidy this up after implementing combat
    // This gets called every time a command has been input (every turn).
 /*   public void attackCycle() {
        if (enemies == null || enemies.size() == 0)
            return;

        for (Enemy enemy : enemies)
            if (enemy.isAlive() && enemy.getTarget() != null)
                enemy.attackTarget();
    }*/
 /*   public void viewRoom() {
        ArrayList<Enemy> attackList = new ArrayList<>();
        ArrayList<Enemy> lootList = new ArrayList<>();

        if (entering)
            output(Strings.LOCATION_DISPLAY_TITLE_ENTER, locationName, areaName);
        else
            output(Strings.LOCATION_DISPLAY_TITLE_EXAMINE, locationName, areaName);

        // Display local map
        if (neighbours != null)
            for (Location.LocationNeighbour neighbour : neighbours)
                output(Strings.LOCATION_DISPLAY_OBJECT_GO, neighbour.displayName);

        // Display local merchants
        if (merchants != null)
            for (Merchant merchant : merchants)
                output(Strings.LOCATION_DISPLAY_OBJECT_TRADE, merchant.getName());

        // Display local enemies
        if (enemies != null)
            for (Enemy enemy : enemies)
                if (enemy.getCurrentHealth() > 0)
                    attackList.add(enemy);
                else
                    lootList.add(enemy);

        for (Enemy enemy : attackList)
            output(Strings.LOCATION_DISPLAY_OBJECT_ATTACK, enemy.getName(), enemy.getLevel(), enemy.getCurrentHealth(), enemy.getMaxHealth());

        for (Enemy enemy : lootList)
            output(Strings.LOCATION_DISPLAY_OBJECT_LOOT, enemy.getName(), enemy.inventoryToString());
    }*/
}
