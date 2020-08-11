package com.example.TextAdventure.Map;

import java.util.ArrayList;

public class Area {
    private String areaName;
    private Room startRoom;
    private Room endRoom;
    private ArrayList<Level> levels;
    private ArrayList<Room> areaExitRooms;

    private Area(String areaName, ArrayList<Level> levels, ArrayList<Room> areaExitRooms) {
        this.areaName = areaName;
        this.levels = levels;
        startRoom = levels.get(0).getStartRoom();
        endRoom = levels.get(levels.size() - 1).getEndRoom();
        this.areaExitRooms = areaExitRooms;
    }

    public Room getStartRoom() { return startRoom; }
    public Room getEndRoom() { return endRoom; }
    public ArrayList<Room> getAreaExitRooms() { return areaExitRooms; }

    // chains two areas together
    public static void connectAreas(Area firstArea, Area secondArea) {
        firstArea.endRoom.connectToRoom(secondArea.startRoom);
        secondArea.startRoom.connectToRoom(firstArea.endRoom);
    }

    // chains adjacentArea to areaExitRoom
    public static void connectAreaToExitRoom(Room areaExitRoom, Area adjacentArea) {
        areaExitRoom.connectToRoom(adjacentArea.startRoom);
        adjacentArea.startRoom.connectToRoom(areaExitRoom);
    }

    public static Area generateArea(String areaName, String[] levelStrings) {
        ArrayList<Level> levels = new ArrayList<>();
        ArrayList<Room> areaExitRooms = new ArrayList<>();

        Level previousLevel = Level.generateLevel(areaName, 1, levelStrings[0]);
        Level nextLevel;

        for (Room room : previousLevel.getAreaExitRooms())
            areaExitRooms.add(room);

        levels.add(previousLevel);

        for (int i = 1; i < levelStrings.length; i++) {
            nextLevel = Level.generateLevel(areaName, i + 1, levelStrings[i]);

            for (Room room : nextLevel.getAreaExitRooms())
                areaExitRooms.add(room);

            Level.connectLevels(previousLevel, nextLevel);
            levels.add(nextLevel);

            previousLevel = nextLevel;
        }

        return new Area(areaName, levels, areaExitRooms);
    }
}
