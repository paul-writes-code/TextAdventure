package com.example.TextAdventure.Map;

import com.example.TextAdventure.Character.CharacterList;

public class WorldMap {
    private static boolean locationsInitialized = false;
    private static Location[] locations;

    private static Location startingLocation = null;
    private static Location startingLocationTutorial = null;

    public static Location getStartingLocation() {
        initMap();
        return startingLocation;
    }
    public static Location getStartingLocationTutorial() {
        initMap();
        return startingLocationTutorial;
    }

    private static String mapString = "{" +

            // town1
            "0;territory1/region1/town1/entrance;[1,12];[];[]'" +
            "1;territory1/region1/town1/town centre;[0,2,3];[];[]'" +
            "2;territory1/region1/town1/market;[1];[];[100]'" +
            "3;territory1/region1/town1/barracks;[1];[];[]'" +

            // town2
            "4;territory1/region1/town2/entrance;[5,16];[];[]'" +
            "5;territory1/region1/town2/town centre;[4,6];[];[]'" +
            "6;territory1/region1/town2/market;[5];[];[]'" +

            // cave1
            "7;territory1/region1/cave1/cave1-1;[8,18];[0/3];[]'" +
            "8;territory1/region1/cave1/cave1-2;[7];[2/2];[]'" +

            // cave2
            "9;territory1/region1/cave2/cave2-1;[10,22];[];[]'" +
            "10;territory1/region1/cave2/cave2-2;[9,11];[];[]'" +
            "11;territory1/region1/cave2/cave2-3;[10];[];[]'" +

            // path1
            "12;territory1/region1/path1/path1-1;[0,13];[];[]'" +
            "13;territory1/region1/path1/path1-2;[12,14];[];[]'" +
            "14;territory1/region1/path1/path1-3;[13,15,17];[];[]'" +
            "15;territory1/region1/path1/path1-4;[14,16];[];[]'" +
            "16;territory1/region1/path1/path1-5;[4,15,20];[];[]'" +

            // path2
            "17;territory1/region1/path2/path2-1;[14,18];[];[]'" +
            "18;territory1/region1/path2/path2-2;[7,17,19];[];[]'" +
            "19;territory1/region1/path2/path2-3;[18];[];[]'" +

            // path3
            "20;territory1/region1/path3/path3-1;[16,21];[];[]'" +
            "21;territory1/region1/path3/path3-2;[20,22];[];[]'" +
            "22;territory1/region1/path3/path3-3;[9,21];[];[]'" +

            // Tutorial
            "23;Tutorial/Tutorial/tutorial/forest1;[24];[];[]'" +
            "24;Tutorial/Tutorial/tutorial/forest2;[23];[1/2];[101]" +

            "}";

    public static Location getLocation(int locationId) {
        initMap();
        return locations[locationId];
    }

    private static void initMap() {
        if (locationsInitialized)
            return;

        String[] locationLines = mapString.substring(1, mapString.length() - 1) // remove '{' and '}'
                                        .split("'"); // each line separated by apostrophe
        locations = new Location[locationLines.length];

        // Initialize all Locations
        for (int i = 0; i < locationLines.length; i++) {
            String[] locationInfo = locationLines[i].split(";");
            String[] locationPositionInfo = locationInfo[1].split("/");
            String[] locationNeighbourIdStrings = locationInfo[2].substring(1, locationInfo[2].length() - 1).split(",");
            String[] locationEnemyIdStrings = locationInfo[3].substring(1, locationInfo[3].length() - 1).split(",");
            String[] locationMerchantIdStrings = locationInfo[4].substring(1, locationInfo[4].length() - 1).split(",");

            int[] locationNeighbourIds = new int[locationNeighbourIdStrings.length];

            int locationId = Integer.parseInt(locationInfo[0]);
            String locationName = locationPositionInfo[3];
            String areaName = locationPositionInfo[2];
            String regionName = locationPositionInfo[1];
            String territoryName = locationPositionInfo[0];

            for (int j = 0; j < locationNeighbourIdStrings.length; j++)
                locationNeighbourIds[j] = Integer.parseInt(locationNeighbourIdStrings[j]);

            locations[i] = new Location(locationId, locationName, areaName, regionName, territoryName, locationNeighbourIds);

            // Add Enemies
            if (!locationEnemyIdStrings[0].equals(""))
                for (int j = 0; j < locationEnemyIdStrings.length; j++) {
                    String[] enemyInfo = locationEnemyIdStrings[j].split("/");
                    int enemyId = Integer.parseInt(enemyInfo[0]);
                    int enemyQuantity = Integer.parseInt(enemyInfo[1]);

                    for (int k = 1; k <= enemyQuantity; k++)
                        locations[i].addEnemy(CharacterList.getEnemy(enemyId, k));
                }

            // Add Merchants
            if (!locationMerchantIdStrings[0].equals(""))
                for (int j = 0; j < locationMerchantIdStrings.length; j++)
                    locations[i].addMerchant(CharacterList.getMerchant(Integer.parseInt(locationMerchantIdStrings[j])));

        }

        locationsInitialized = true;

        startingLocation = locations[1];
        startingLocationTutorial = locations[23];

        // Once Locations are initialized, connect neighbours
        for (Location location : locations)
            location.initNeighbours();
    }
}
