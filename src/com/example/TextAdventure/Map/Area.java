package com.example.TextAdventure.Map;

import com.example.TextAdventure.Character.Enemy;

import java.util.ArrayList;

public class Area {

    private String areaName;
    private ArrayList<Level> levels;
    private Room startRoom;
    private Room endRoom;
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

    // Chain two areas together sequentially.
    public static void connectAreas(Area firstArea, Area secondArea) {
        firstArea.endRoom.connectToRoom(secondArea.startRoom);
        secondArea.startRoom.connectToRoom(firstArea.endRoom);
    }

    // Chain adjacentArea on to areaExitRoom
    public static void connectAreaToExitRoom(Room areaExitRoom, Area adjacentArea) {
        areaExitRoom.connectToRoom(adjacentArea.startRoom);
        adjacentArea.startRoom.connectToRoom(areaExitRoom);
    }

    public static Area generateArea(String areaName, ArrayList<String> levelStrings, Enemy.EnemyType enemyType) {
        ArrayList<Level> levels = new ArrayList<>();
        ArrayList<Room> areaExitRooms = new ArrayList<>();

        Level previousLevel = Level.generateLevel(areaName, levelStrings.size() == 1 ? 0 : 1, levelStrings.get(0), enemyType);
        Level nextLevel;

        for (Room room : previousLevel.getAreaExitRooms())
            areaExitRooms.add(room);

        levels.add(previousLevel);

        // Generates each level and chains them together sequentially, from endRoom of previousLevel to startRoom of nextLevel
        for (int i = 1; i < levelStrings.size(); i++) {
            nextLevel = Level.generateLevel(areaName, i + 1, levelStrings.get(i), enemyType);

            for (Room room : nextLevel.getAreaExitRooms())
                areaExitRooms.add(room);

            Level.connectLevels(previousLevel, nextLevel);
            levels.add(nextLevel);

            previousLevel = nextLevel;
        }

        return new Area(areaName, levels, areaExitRooms);
    }
}
