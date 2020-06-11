package com.example.TextAdventure.Map;

import com.example.TextAdventure.Character.Enemy;
import com.example.TextAdventure.Character.Merchant;
import com.example.TextAdventure.Equipment.Equipment;
import com.example.TextAdventure.Item.Inventory;

import java.util.ArrayList;

public abstract class WorldMap {

    public static final String TUTORIAL_TERRITORY = "Tutorial";
    public static final String TUTORIAL_REGION = "Tutorial";
    public static final String TUTORIAL_AREA = "tutorial";
    public static final String TUTORIAL_LOCATION_1 = "forest1";
    public static final String TUTORIAL_LOCATION_2 = "forest2";

    private static final String TERRITORY_TERRITORY1 = "territory1";
    private static final String REGION_REGION1 = "region1";

    private static final String AREA_TOWN1 = "town1";
    private static final String AREA_TOWN2 = "town2";
    private static final String AREA_CAVE1 = "cave1";
    private static final String AREA_CAVE2 = "cave2";
    private static final String AREA_PATH1 = "path1";
    private static final String AREA_PATH2 = "path2";
    private static final String AREA_PATH3 = "path3";

    private static final String LOCATION_ENTRANCE = "entrance";
    private static final String LOCATION_TOWN_CENTRE = "town-centre";
    private static final String LOCATION_MARKET = "market";
    private static final String LOCATION_BARRACKS = "barracks";

    private static final String LOCATION_CAVE1_1 = "cave1-1";
    private static final String LOCATION_CAVE1_2 = "cave1-2";

    private static final String LOCATION_CAVE2_1 = "cave2-1";
    private static final String LOCATION_CAVE2_2 = "cave2-2";
    private static final String LOCATION_CAVE2_3 = "cave2-3";

    private static final String LOCATION_PATH1_1 = "path1-1";
    private static final String LOCATION_PATH1_2 = "path1-2";
    private static final String LOCATION_PATH1_3 = "path1-3";
    private static final String LOCATION_PATH1_4 = "path1-4";
    private static final String LOCATION_PATH1_5 = "path1-5";

    private static final String LOCATION_PATH2_1 = "path2-1";
    private static final String LOCATION_PATH2_2 = "path2-2";
    private static final String LOCATION_PATH2_3 = "path2-3";

    private static final String LOCATION_PATH3_1 = "path3-1";
    private static final String LOCATION_PATH3_2 = "path3-2";
    private static final String LOCATION_PATH3_3 = "path3-3";

    private static final int NUM_REGIONS_TERRITORY1 = 1;
    private static final int NUM_AREAS_REGION1 = 7;

    private static final int NUM_LOCATIONS_TOWN1 = 4;
    private static final int NUM_LOCATIONS_TOWN2 = 3;
    private static final int NUM_LOCATIONS_CAVE1 = 2;
    private static final int NUM_LOCATIONS_CAVE2 = 3;
    private static final int NUM_LOCATIONS_PATH1 = 5;
    private static final int NUM_LOCATIONS_PATH2 = 3;
    private static final int NUM_LOCATIONS_PATH3 = 3;

    private static ArrayList<Territory> territories = null;
    private static Location startingLocation = null;
    private static Location startingLocationTutorial = null;

    public static ArrayList<Territory> getTerritories() { return territories; }
    public static Location getStartingLocation() { return startingLocation; }
    public static Location getStartingLocationTutorial() { return startingLocationTutorial; }

    private static boolean initialized = false;

    public static void initWorldMap() {
        if (initialized)
            return;

        // Create Territories
        Territory territory1 = new Territory(TERRITORY_TERRITORY1, NUM_REGIONS_TERRITORY1);

        // Create Regions
        Region region1 = new Region(REGION_REGION1, territory1, NUM_AREAS_REGION1);

        // Create Areas
        Area town1 = new Area(AREA_TOWN1, region1, NUM_LOCATIONS_TOWN1);
        Area town2 = new Area(AREA_TOWN2, region1, NUM_LOCATIONS_TOWN2);
        Area cave1 = new Area(AREA_CAVE1, region1, NUM_LOCATIONS_CAVE1);
        Area cave2 = new Area(AREA_CAVE2, region1, NUM_LOCATIONS_CAVE2);
        Area path1 = new Area(AREA_PATH1, region1, NUM_LOCATIONS_PATH1);
        Area path2 = new Area(AREA_PATH2, region1, NUM_LOCATIONS_PATH2);
        Area path3 = new Area(AREA_PATH3, region1, NUM_LOCATIONS_PATH3);

        // Create Locations

        // town1:
        Location town1_entrance = new Location(LOCATION_ENTRANCE, town1);
        Location town1_townCentre = new Location(LOCATION_TOWN_CENTRE, town1);
        Location town1_market = new Location(LOCATION_MARKET, town1);
        Location town1_barracks = new Location(LOCATION_BARRACKS, town1);

        // town2:
        Location town2_entrance = new Location(LOCATION_ENTRANCE, town2);
        Location town2_townCentre = new Location(LOCATION_TOWN_CENTRE, town2);
        Location town2_market = new Location(LOCATION_MARKET, town2);

        // cave1:
        Location cave1_1 = new Location(LOCATION_CAVE1_1, cave1);
        Location cave1_2 = new Location(LOCATION_CAVE1_2, cave1);

        // cave2:
        Location cave2_1 = new Location(LOCATION_CAVE2_1, cave2);
        Location cave2_2 = new Location(LOCATION_CAVE2_2, cave2);
        Location cave2_3 = new Location(LOCATION_CAVE2_3, cave2);

        // path1:
        Location path1_1 = new Location(LOCATION_PATH1_1, path1);
        Location path1_2 = new Location(LOCATION_PATH1_2, path1);
        Location path1_3 = new Location(LOCATION_PATH1_3, path1);
        Location path1_4 = new Location(LOCATION_PATH1_4, path1);
        Location path1_5 = new Location(LOCATION_PATH1_5, path1);

        // path2:
        Location path2_1 = new Location(LOCATION_PATH2_1, path2);
        Location path2_2 = new Location(LOCATION_PATH2_2, path2);
        Location path2_3 = new Location(LOCATION_PATH2_3, path2);

        // path3:
        Location path3_1 = new Location(LOCATION_PATH3_1, path3);
        Location path3_2 = new Location(LOCATION_PATH3_2, path3);
        Location path3_3 = new Location(LOCATION_PATH3_3, path3);

        // Connect Neighbouring Locations

        // town1:
        town1_entrance.setNeighbours(new Location[] { town1_townCentre, path1_1 });
        town1_townCentre.setNeighbours(new Location[] { town1_entrance, town1_market, town1_barracks });
        town1_market.setNeighbours(new Location[] { town1_townCentre });
        town1_barracks.setNeighbours(new Location[] { town1_townCentre });

        // town2:
        town2_entrance.setNeighbours(new Location[] { town2_townCentre, path1_5 });
        town2_townCentre.setNeighbours(new Location[] { town2_entrance, town2_market });
        town2_market.setNeighbours(new Location[] { town2_townCentre });

        // cave1:
        cave1_1.setNeighbours(new Location[] { path2_2, cave1_2 });
        cave1_2.setNeighbours(new Location[] { cave1_1 });

        // cave2:
        cave2_1.setNeighbours(new Location[] { path3_3, cave2_2 });
        cave2_2.setNeighbours(new Location[] { cave2_1, cave2_3 });
        cave2_3.setNeighbours(new Location[] { cave2_2 });

        // path1:
        path1_1.setNeighbours(new Location[] { town1_entrance, path1_2 });
        path1_2.setNeighbours(new Location[] { path1_1, path1_3 });
        path1_3.setNeighbours(new Location[] { path1_2, path1_4, path2_1 });
        path1_4.setNeighbours(new Location[] { path1_3, path1_5 });
        path1_5.setNeighbours(new Location[] { town2_entrance, path1_4, path3_1 });

        // path2:
        path2_1.setNeighbours(new Location[] { path1_3, path2_2 });
        path2_2.setNeighbours(new Location[] { cave1_1, path2_1, path2_3 });
        path2_3.setNeighbours(new Location[] { path2_2 });

        // path3:
        path3_1.setNeighbours(new Location[] { path1_5, path3_2 });
        path3_2.setNeighbours(new Location[] { path3_1, path3_3 });
        path3_3.setNeighbours(new Location[] { cave2_1, path3_2 });

        // Add Enemies

        // cave1:
        cave1_1.addEnemy(new Enemy("goblin1", 25, 1, 5, 5, 1, 1, null));
        cave1_1.addEnemy(new Enemy("goblin2",25, 1, 5, 5, 1, 1, null));
        cave1_1.addEnemy(new Enemy("goblin3", 25, 1, 5, 5, 1, 1, null));

        cave1_2.addEnemy(new Enemy("scorpion1", 100, 3, 12, 12, 6, 4, null));
        cave1_2.addEnemy(new Enemy("scorpion2", 100, 3, 12, 12, 6, 4, null));

        // Add Merchants

        // town1:
        town1_market.addMerchant(new Merchant("merchy"));
        town1_market.getMerchants().get(0).getInventory().addItem(new Equipment(Equipment.EquipmentType.SHIELD, "shield1", 106, 10, 3, 20));

        // Create Tutorial Map
        Territory tutorialTerritory = new Territory(TUTORIAL_TERRITORY, 1);
        Region tutorialRegion = new Region(TUTORIAL_REGION, tutorialTerritory, 1);
        Area tutorialArea = new Area(TUTORIAL_AREA, tutorialRegion, 2);

        Location tutorialForest1 = new Location(TUTORIAL_LOCATION_1, tutorialArea);
        Location tutorialForest2 = new Location(TUTORIAL_LOCATION_2, tutorialArea);

        tutorialForest1.setNeighbours(new Location[] { tutorialForest2 });
        tutorialForest2.setNeighbours(new Location[] { tutorialForest1 });

        tutorialForest2.addEnemy(new Enemy("bandit1", 25, 1,  6, 6, 1, 1, null));
        tutorialForest2.addEnemy(new Enemy("bandit2", 25, 1,  6, 6, 1, 1, null));
        tutorialForest2.getEnemies().get(0).getInventory().addItem(new Equipment(Equipment.EquipmentType.SWORD, "sword1", 103, 0, 5, 2));

        // Add Territories to World Map
        territories = new ArrayList<>();
        territories.add(territory1);
        territories.add(tutorialTerritory);

        // Set Starting Locations
        startingLocationTutorial = tutorialForest1;
        startingLocation = town1_townCentre;
        startingLocation = cave1_1;

        initialized = true;
    }
}
