package com.example.TextAdventure.Map;

import java.util.ArrayList;

public abstract class WorldMap {

    private static final String TERRITORY_TERRITORY1 = "territory1";
    private static final String REGION_REGION1 = "region1";

    private static final String AREA_TOWN1 = "town1";
    private static final String AREA_TOWN2 = "town2";
    private static final String AREA_CAVE1 = "cave1";
    private static final String AREA_CAVE2 = "cave2";
    private static final String AREA_PATH1 = "path1";
    private static final String AREA_PATH2 = "path2";
    private static final String AREA_PATH3 = "path3";

    private static final String SECTOR_ENTRANCE = "entrance";
    private static final String SECTOR_TOWN_CENTRE = "town-centre";
    private static final String SECTOR_MARKET = "market";
    private static final String SECTOR_BARRACKS = "barracks";

    private static final String SECTOR_CAVE1_1 = "cave1-1";
    private static final String SECTOR_CAVE1_2 = "cave1-2";

    private static final String SECTOR_CAVE2_1 = "cave2-1";
    private static final String SECTOR_CAVE2_2 = "cave2-2";
    private static final String SECTOR_CAVE2_3 = "cave2-3";

    private static final String SECTOR_PATH1_1 = "path1-1";
    private static final String SECTOR_PATH1_2 = "path1-2";
    private static final String SECTOR_PATH1_3 = "path1-3";
    private static final String SECTOR_PATH1_4 = "path1-4";
    private static final String SECTOR_PATH1_5 = "path1-5";

    private static final String SECTOR_PATH2_1 = "path2-1";
    private static final String SECTOR_PATH2_2 = "path2-2";
    private static final String SECTOR_PATH2_3 = "path2-3";

    private static final String SECTOR_PATH3_1 = "path3-1";
    private static final String SECTOR_PATH3_2 = "path3-2";
    private static final String SECTOR_PATH3_3 = "path3-3";

    private static final int NUM_REGIONS_TERRITORY1 = 1;
    private static final int NUM_AREAS_REGION1 = 7;

    private static final int NUM_SECTORS_TOWN1 = 4;
    private static final int NUM_SECTORS_TOWN2 = 3;
    private static final int NUM_SECTORS_CAVE1 = 2;
    private static final int NUM_SECTORS_CAVE2 = 3;
    private static final int NUM_SECTORS_PATH1 = 5;
    private static final int NUM_SECTORS_PATH2 = 3;
    private static final int NUM_SECTORS_PATH3 = 3;

    private static ArrayList<Territory> territories = null;
    private static Sector startingSector = null;

    public static ArrayList<Territory> getTerritories() { return territories; }
    public static Sector getStartingSector() { return startingSector; }

    private static boolean initialized = false;

    public static void initWorldMap() {
        if (initialized)
            return;

        // Create Territories
        Territory territory1 = new Territory(TERRITORY_TERRITORY1, NUM_REGIONS_TERRITORY1);

        // Create Regions
        Region region1 = new Region(REGION_REGION1, territory1, NUM_AREAS_REGION1);

        // Create Areas
        Area town1 = new Area(AREA_TOWN1, region1, NUM_SECTORS_TOWN1);
        Area town2 = new Area(AREA_TOWN2, region1, NUM_SECTORS_TOWN2);
        Area cave1 = new Area(AREA_CAVE1, region1, NUM_SECTORS_CAVE1);
        Area cave2 = new Area(AREA_CAVE2, region1, NUM_SECTORS_CAVE2);
        Area path1 = new Area(AREA_PATH1, region1, NUM_SECTORS_PATH1);
        Area path2 = new Area(AREA_PATH2, region1, NUM_SECTORS_PATH2);
        Area path3 = new Area(AREA_PATH3, region1, NUM_SECTORS_PATH3);

        // Create Sectors

        // town1:
        Sector town1_entrance = new Sector(SECTOR_ENTRANCE, town1);
        Sector town1_townCentre = new Sector(SECTOR_TOWN_CENTRE, town1);
        Sector town1_market = new Sector(SECTOR_MARKET, town1);
        Sector town1_barracks = new Sector(SECTOR_BARRACKS, town1);

        // town2:
        Sector town2_entrance = new Sector(SECTOR_ENTRANCE, town2);
        Sector town2_townCentre = new Sector(SECTOR_TOWN_CENTRE, town2);
        Sector town2_market = new Sector(SECTOR_MARKET, town2);

        // cave1:
        Sector cave1_1 = new Sector(SECTOR_CAVE1_1, cave1);
        Sector cave1_2 = new Sector(SECTOR_CAVE1_2, cave1);

        // cave2:
        Sector cave2_1 = new Sector(SECTOR_CAVE2_1, cave2);
        Sector cave2_2 = new Sector(SECTOR_CAVE2_2, cave2);
        Sector cave2_3 = new Sector(SECTOR_CAVE2_3, cave2);

        // path1:
        Sector path1_1 = new Sector(SECTOR_PATH1_1, path1);
        Sector path1_2 = new Sector(SECTOR_PATH1_2, path1);
        Sector path1_3 = new Sector(SECTOR_PATH1_3, path1);
        Sector path1_4 = new Sector(SECTOR_PATH1_4, path1);
        Sector path1_5 = new Sector(SECTOR_PATH1_5, path1);

        // path2:
        Sector path2_1 = new Sector(SECTOR_PATH2_1, path2);
        Sector path2_2 = new Sector(SECTOR_PATH2_2, path2);
        Sector path2_3 = new Sector(SECTOR_PATH2_3, path2);

        // path3:
        Sector path3_1 = new Sector(SECTOR_PATH3_1, path3);
        Sector path3_2 = new Sector(SECTOR_PATH3_2, path3);
        Sector path3_3 = new Sector(SECTOR_PATH3_3, path3);

        // Connect Neighbouring Sectors

        // town1:
        town1_entrance.setSectorNeighbours(new Sector[] { town1_townCentre, path1_1 });
        town1_townCentre.setSectorNeighbours(new Sector[] { town1_entrance, town1_market, town1_barracks });
        town1_market.setSectorNeighbours(new Sector[] { town1_townCentre });
        town1_barracks.setSectorNeighbours(new Sector[] { town1_townCentre });

        // town2:
        town2_entrance.setSectorNeighbours(new Sector[] { town2_townCentre, path1_5 });
        town2_townCentre.setSectorNeighbours(new Sector[] { town2_entrance, town2_market });
        town2_market.setSectorNeighbours(new Sector[] { town2_townCentre });

        // cave1:
        cave1_1.setSectorNeighbours(new Sector[] { path2_2, cave1_2 });
        cave1_2.setSectorNeighbours(new Sector[] { cave1_1 });

        // cave2:
        cave2_1.setSectorNeighbours(new Sector[] { path3_3, cave2_2 });
        cave2_2.setSectorNeighbours(new Sector[] { cave2_1, cave2_3 });
        cave2_3.setSectorNeighbours(new Sector[] { cave2_2 });

        // path1:
        path1_1.setSectorNeighbours(new Sector[] { town1_entrance, path1_2 });
        path1_2.setSectorNeighbours(new Sector[] { path1_1, path1_3 });
        path1_3.setSectorNeighbours(new Sector[] { path1_2, path1_4, path2_1 });
        path1_4.setSectorNeighbours(new Sector[] { path1_3, path1_5 });
        path1_5.setSectorNeighbours(new Sector[] { town2_entrance, path1_4, path3_1 });

        // path2:
        path2_1.setSectorNeighbours(new Sector[] { path1_3, path2_2 });
        path2_2.setSectorNeighbours(new Sector[] { cave1_1, path2_1, path2_3 });
        path2_3.setSectorNeighbours(new Sector[] { path2_2 });

        // path3:
        path3_1.setSectorNeighbours(new Sector[] { path1_5, path3_2 });
        path3_2.setSectorNeighbours(new Sector[] { path3_1, path3_3 });
        path3_3.setSectorNeighbours(new Sector[] { cave2_1, path3_2 });

        // Add Territories to World Map
        territories = new ArrayList<>();
        territories.add(territory1);

        // Set Starting Location
        startingSector = town1_townCentre;

        initialized = true;
    }
}
