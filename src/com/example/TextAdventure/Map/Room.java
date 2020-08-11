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
        private String displayName; // the name the player sees.
        private MovementType movementType; // whether this adjacent room is in a different level or area.

        private AdjacentRoom(Room adjacentRoom, Room originalRoom) {
            room = adjacentRoom;
            initMovementInfo(originalRoom);
        }

        // first pass just stores the room information in these temporary Room objects
        // second pass will replace these Room objects with pointers to real rooms.
        private AdjacentRoom(String roomId, String areaName, int levelNumber, Room originalRoom) {
            room = new Room(roomId, areaName, levelNumber);
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

                if (originalRoom.xCoord < room.xCoord)
                    displayName = "east";
                else if (originalRoom.xCoord > room.xCoord)
                    displayName = "west";
                else if (originalRoom.yCoord < room.yCoord)
                    displayName = "north";
                else
                    displayName = "south";
            }
        }
    }

    private String areaName;
    private int levelNumber;
    private int xCoord;
    private int yCoord;

    public AdjacentRoom getAdjacentRoom(String displayName) {
        for (AdjacentRoom room : adjacentRooms)
            if (room.displayName.equals(displayName))
                return room;

        return null;
    }

    private ArrayList<AdjacentRoom> adjacentRooms;

    private Room(String roomId, String areaName, int levelNumber) {
        initRoomInfo(roomId, areaName, levelNumber);
    }
    public Room(String roomId, String areaName, int levelNumber, String[] adjacentRoomStrings) {
        initRoomInfo(roomId, areaName, levelNumber);

        adjacentRooms = new ArrayList<AdjacentRoom>();

        for (int i = 0; i < adjacentRoomStrings.length; i++)
            adjacentRooms.add(new AdjacentRoom(adjacentRoomStrings[i], areaName, levelNumber, this));
    }

    //  roomId is xy, the (x, y) position of the room within its level // TODO: change this to take in coords
    private void initRoomInfo(String roomId, String areaName, int levelNumber) {
        this.areaName = areaName;
        this.levelNumber = levelNumber;
        xCoord = Integer.parseInt(roomId.substring(0,1));
        yCoord = Integer.parseInt(roomId.substring(1));
    }

    public void setAdjacentRooms(Room[] rooms) {
        for (AdjacentRoom adjacentRoom : adjacentRooms)
            for (Room room : rooms)
                if (adjacentRoom.room.equals(room))
                    adjacentRoom.room = room;
    }

    public void connectToRoom(Room room) {
        adjacentRooms.add(new AdjacentRoom(room, this));
    }

    public String getAreaName() { return areaName; }
    public int getLevelNumber() { return levelNumber; }
    public int getxCoord() { return xCoord; }
    public int getyCoord() { return yCoord; }

    @Override
    public boolean equals(Object object) {
        if (object == null)
            return false;

        return object instanceof Room
                && ((Room) object).areaName.equals(areaName)
                && ((Room) object).levelNumber == levelNumber
                && ((Room) object).xCoord == xCoord
                && ((Room) object).yCoord == yCoord;
    }


    public void leave() {
     /*   if (enemies == null || enemies.size() == 0)
            return;

        for (Enemy enemy : enemies)
            if (enemy.getTarget() != null)
                enemy.setTarget(null);*/
    }

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

    public void viewRoom() {

        // Display local map
        if (adjacentRooms != null)
            for (AdjacentRoom adjacentRoom : adjacentRooms)
                output(Strings.LOCATION_DISPLAY_OBJECT_GO, adjacentRoom.displayName);

    }
}
