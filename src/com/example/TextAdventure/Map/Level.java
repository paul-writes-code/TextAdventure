package com.example.TextAdventure.Map;

import java.util.ArrayList;

public class Level {

    private String areaName;
    private int levelNumber;
    private Room[] rooms;
    private Room startRoom; // the beginning of this room, used to chain levels together
    private Room endRoom; // the end of this room, used to chain levels together
    private ArrayList<Room> areaExitRooms; // these are exits to other areas outside of the chain of levels; set by Area

    private Level(String areaName, int levelNumber, Room[] rooms, Room startRoom, Room endRoom, ArrayList<Room> areaExitRooms) {
        this.areaName = areaName;
        this.levelNumber = levelNumber;
        this.rooms = rooms;
        this.startRoom = startRoom;
        this.endRoom = endRoom;
        this.areaExitRooms = areaExitRooms;
    }

    public Room getStartRoom() { return startRoom; }
    public Room getEndRoom() { return endRoom; }
    public ArrayList<Room> getAreaExitRooms() { return areaExitRooms; }

    public static void connectLevels(Level firstLevel, Level secondLevel) {
        firstLevel.endRoom.connectToRoom(secondLevel.startRoom);
        secondLevel.startRoom.connectToRoom(firstLevel.endRoom);
    }

    public static Level generateLevel(String areaName, int levelNumber, String levelString) {

        String[] roomLines = levelString.split("'"); // each line separated by apostrophe
        Room[] rooms = new Room[roomLines.length];
        ArrayList<Room> exitRooms = new ArrayList<>();
        Room startRoom = null;
        Room endRoom = null;

        // Create each room
        for (int i = 0; i < roomLines.length; i++) {
            String[] roomInfo = roomLines[i].split(";"); // separate room ID from adjacent room list

            String roomId = roomInfo[0];
            String[] adjacentRoomsString = roomInfo[1].split(","); // separate all the adjacent room IDs

            switch (roomId.charAt(0)) {
                case '[':
                    rooms[i] = new Room(roomId.substring(1), areaName, levelNumber, adjacentRoomsString); // remove [ from roomId
                    startRoom = rooms[i];
                    break;
                case ']':
                    rooms[i] = new Room(roomId.substring(1), areaName, levelNumber, adjacentRoomsString); // remove ] from roomId
                    endRoom = rooms[i];
                    break;
                case '#':
                    rooms[i] = new Room(roomId.substring(1), areaName, levelNumber, adjacentRoomsString); // remove # from roomId
                    exitRooms.add(rooms[i]);
                    break;
                case 'x':
                    rooms[i] = new Room(roomId.substring(1), areaName, levelNumber, adjacentRoomsString); // remove x from roomId
                    WorldMap.setSpawnRoom(rooms[i]);
                    break;
                default:
                    rooms[i] = new Room(roomId, areaName, levelNumber, adjacentRoomsString);
                    break;
            }
        }

        // Once every room has been created, they can be connected
        for (Room room : rooms)
            room.setAdjacentRooms(rooms);

        return new Level(areaName, levelNumber, rooms, startRoom, endRoom, exitRooms);
    }
}
