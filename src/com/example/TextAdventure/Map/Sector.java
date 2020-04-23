package com.example.TextAdventure.Map;

import com.example.TextAdventure.UserInterface.Output;

public class Sector {

    public static class SectorNeighbour {

        public enum MovementType {
            INIT,
            SECTOR,
            AREA,
            REGION,
            TERRITORY
        }

        private Sector sector;
        private String displayName;
        private MovementType movementType;

        public SectorNeighbour(Sector sector, String displayName, MovementType movementType) {
            this.sector = sector;
            this.displayName = displayName;
            this.movementType = movementType;
        }

        public Sector getSector() { return sector; }
        public String getDisplayName() { return displayName; }
        public MovementType getMovementType() { return movementType; }
    }

    private String sectorName;
    private Area area;
    private SectorNeighbour[] sectorNeighbours;

    public Sector(String sectorName, Area area) {
        this.sectorName = sectorName;
        this.area = area;
        sectorNeighbours = null;

        this.area.addSector(this);
    }

    public String getSectorName() { return sectorName; }
    public Area getArea() { return area; }

    public SectorNeighbour getSectorNeighbour(String displayName) {
        for (SectorNeighbour sectorNeighbour : sectorNeighbours)
            if (sectorNeighbour.displayName.equals(displayName))
                return sectorNeighbour;

        return null;
    }
    public SectorNeighbour[] getSectorNeighbours() { return sectorNeighbours; }
    public void setSectorNeighbours(Sector[] neighbours) {
        if (neighbours == null || neighbours.length == 0)
            return;

        sectorNeighbours = new SectorNeighbour[neighbours.length];

        Area currentArea = getArea();
        Region currentRegion = currentArea.getRegion();
        Territory currentTerritory = currentRegion.getTerritory();

        for (int i = 0; i < neighbours.length; i++) {

            Sector neighbour = neighbours[i];
            Area neighbourArea = neighbour.getArea();
            Region neighbourRegion = neighbourArea.getRegion();
            Territory neighbourTerritory = neighbourRegion.getTerritory();

            String neighbourDisplayName = "";
            SectorNeighbour.MovementType movementType;

            if (!neighbourTerritory.getTerritoryName().equals(currentTerritory.getTerritoryName())) {
                neighbourDisplayName = neighbourTerritory.getTerritoryName();
                movementType = SectorNeighbour.MovementType.TERRITORY;
            }
            else if (!neighbourRegion.getRegionName().equals(currentRegion.getRegionName())) {
                neighbourDisplayName = neighbourRegion.getRegionName();
                movementType = SectorNeighbour.MovementType.REGION;
            }
            else if (!neighbourArea.getAreaName().equals(currentArea.getAreaName())) {
                neighbourDisplayName = neighbourArea.getAreaName();
                movementType = SectorNeighbour.MovementType.AREA;
            }
            else {
                neighbourDisplayName = neighbour.getSectorName();
                movementType = SectorNeighbour.MovementType.SECTOR;
            }

            sectorNeighbours[i] = new SectorNeighbour(neighbour, neighbourDisplayName, movementType);
        }
    }

    public void enter(SectorNeighbour.MovementType movementType) {
        String printName = "";

        switch (movementType) {
            case SECTOR:
                printName = sectorName;
                break;
            case INIT:
            case AREA:
                printName = sectorName + " of " + area.getAreaName();
                break;
            case REGION:
                printName = sectorName + ", of " + area.getRegion().getRegionName() + " Region";
            case TERRITORY:
                printName = sectorName + ", of " + area.getRegion().getTerritory().getTerritoryName() + " Territory";
                break;
        }

        Output.output("Entering " + printName + ".");
    }
}
