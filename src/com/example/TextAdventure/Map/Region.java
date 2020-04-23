package com.example.TextAdventure.Map;

public class Region {

    private String regionName;
    private Territory territory;
    private Area[] areas;
    private int numAreas;

    public Region(String regionName, Territory territory, int numAreas) {
        this.regionName = regionName;
        this.territory = territory;
        this.numAreas = 0;
        areas = new Area[numAreas];

        this.territory.addRegion(this);
    }

    public String getRegionName() { return regionName; }
    public Territory getTerritory() { return territory; }

    public Area[] getAreas() { return areas; }
    public void addArea(Area area) {
        if (area != null && numAreas < areas.length)
            areas[numAreas++] = area;
    }
}
