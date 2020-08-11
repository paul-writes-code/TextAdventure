package com.example.TextAdventure.Map;

import com.example.TextAdventure.Character.Enemy;
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

    private ArrayList<AdjacentRoom> adjacentRooms;
    private ArrayList<Enemy> enemies;

    // First pass stores the room adjacency information in temporary placeholder Room objects, as not all Room objects have been created at this point.
    private Room(String areaName, int levelNumber, int x, int y) {
        initRoomInfo(areaName, levelNumber, x, y);
    }

    public Room(String areaName, int levelNumber, int x, int y, String[] adjacentRoomStrings, ArrayList<Enemy> enemies) {
        initRoomInfo(areaName, levelNumber, x, y);

        adjacentRooms = new ArrayList<>();
        this.enemies = enemies;

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

    public AdjacentRoom getAdjacentRoom(String displayName) {
        for (AdjacentRoom room : adjacentRooms)
            if (room.displayName.equals(displayName))
                return room;

        return null;
    }

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
        for (AdjacentRoom adjacentRoom : adjacentRooms)
            output(Strings.LOCATION_DISPLAY_OBJECT_GO, adjacentRoom.displayName);

        // Display local enemies
        for (Enemy enemy : enemies)
            output(Strings.LOCATION_DISPLAY_OBJECT_ATTACK, enemy.getDisplayName(), enemy.getHealth(), enemy.getHitpoints());
    }

    public void addEnemy(Enemy enemy) {
        if (enemy != null)
            enemies.add(enemy);
    }

    public void removeEnemy(String displayName) {
        for (Enemy enemy : enemies)
            if (enemy.getDisplayName().equals(displayName))
                enemies.remove(enemy);
    }

    public void leave() {
        // set all enemies to be non-aggressive
    }
}
