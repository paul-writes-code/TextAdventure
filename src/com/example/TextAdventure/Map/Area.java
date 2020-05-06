package com.example.TextAdventure.Map;

public class Area {

    private String areaName;
    private Region region;
    private Location[] locations;
    private int numLocations;

    public Area(String areaName, Region region, int numLocations) {
        this.areaName = areaName;
        this.region = region;
        this.numLocations = 0;
        locations = new Location[numLocations];

        this.region.addArea(this);
    }

    public String getAreaName() { return areaName; }
    public Region getRegion() { return region; }

    public Location[] getLocations() { return locations; }
    public void addLocation(Location location) {
        if (location != null && numLocations < locations.length)
            locations[numLocations++] = location;
    }
}
