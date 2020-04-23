package com.example.TextAdventure.Map;

public class Area {

    private String areaName;
    private Region region;
    private Sector[] sectors;
    private int numSectors;

    public Area(String areaName, Region region, int numSectors) {
        this.areaName = areaName;
        this.region = region;
        this.numSectors = 0;
        sectors = new Sector[numSectors];

        this.region.addArea(this);
    }

    public String getAreaName() { return areaName; }
    public Region getRegion() { return region; }

    public Sector[] getSectors() { return sectors; }
    public void addSector(Sector sector) {
        if (sector != null && numSectors < sectors.length)
            sectors[numSectors++] = sector;
    }
}
