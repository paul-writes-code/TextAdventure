package com.example.TextAdventure.Map;

public class Territory {

    private String territoryName;
    private Region[] regions;
    private int numRegions;

    public Territory(String territoryName, int numRegions) {
        this.territoryName = territoryName;
        this.numRegions = 0;
        regions = new Region[numRegions];
    }

    public String getTerritoryName() { return territoryName; }

    public Region[] getRegions() { return regions; }
    public void addRegion(Region region) {
        if (region != null && numRegions < regions.length)
            regions[numRegions++] = region;
    }
}
