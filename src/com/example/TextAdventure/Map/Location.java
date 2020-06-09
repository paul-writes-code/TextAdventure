package com.example.TextAdventure.Map;

import com.example.TextAdventure.Character.Enemy;
import com.example.TextAdventure.World;

import java.util.ArrayList;

public class Location {

    public static class LocationNeighbour {

        public enum MovementType {
            INIT,
            LOCATION,
            AREA,
            REGION,
            TERRITORY
        }

        private Location location;
        private String displayName;
        private MovementType movementType;

        public LocationNeighbour(Location location, String displayName, MovementType movementType) {
            this.location = location;
            this.displayName = displayName;
            this.movementType = movementType;
        }

        public Location getLocation() { return location; }
        public String getDisplayName() { return displayName; }
        public MovementType getMovementType() { return movementType; }
    }

    private String locationName;
    private Area area;
    private LocationNeighbour[] neighbours;

    private ArrayList<Enemy> enemies;

    public Location(String locationName, Area area) {
        this.locationName = locationName;
        this.area = area;
        neighbours = null;

        this.area.addLocation(this);
    }

    public String getLocationName() { return locationName; }
    public Area getArea() { return area; }
    public ArrayList<Enemy> getEnemies() { return enemies; }
    public Enemy getEnemy(String enemyName) {
        for (Enemy enemy : enemies)
            if (enemy.getName().equals(enemyName))
                return enemy;

        return null;
    }

    public LocationNeighbour getNeighbour(String displayName) {
        for (LocationNeighbour locationNeighbour : neighbours)
            if (locationNeighbour.displayName.equals(displayName))
                return locationNeighbour;

        return null;
    }
    public LocationNeighbour[] getNeighbours() { return neighbours; }
    public void setNeighbours(Location[] neighbours) {
        if (neighbours == null || neighbours.length == 0)
            return;

        this.neighbours = new LocationNeighbour[neighbours.length];

        Area currentArea = getArea();
        Region currentRegion = currentArea.getRegion();
        Territory currentTerritory = currentRegion.getTerritory();

        for (int i = 0; i < neighbours.length; i++) {

            Location neighbour = neighbours[i];
            Area neighbourArea = neighbour.getArea();
            Region neighbourRegion = neighbourArea.getRegion();
            Territory neighbourTerritory = neighbourRegion.getTerritory();

            String neighbourDisplayName = "";
            LocationNeighbour.MovementType movementType;

            if (!neighbourTerritory.getTerritoryName().equals(currentTerritory.getTerritoryName())) {
                neighbourDisplayName = neighbourTerritory.getTerritoryName();
                movementType = LocationNeighbour.MovementType.TERRITORY;
            }

            else if (!neighbourRegion.getRegionName().equals(currentRegion.getRegionName())) {
                neighbourDisplayName = neighbourRegion.getRegionName();
                movementType = LocationNeighbour.MovementType.REGION;
            }

            else if (!neighbourArea.getAreaName().equals(currentArea.getAreaName())) {
                neighbourDisplayName = neighbourArea.getAreaName();
                movementType = LocationNeighbour.MovementType.AREA;
            }

            else {
                neighbourDisplayName = neighbour.getLocationName();
                movementType = LocationNeighbour.MovementType.LOCATION;
            }

            this.neighbours[i] = new LocationNeighbour(neighbour, neighbourDisplayName, movementType);
        }
    }
    public void addEnemy(Enemy enemy) {
        if (enemy == null)
            return;

        if (enemies == null)
            enemies = new ArrayList<>();

        enemies.add(enemy);
    }
    public void removeEnemy(String enemyName) {
        for (Enemy enemy : enemies)
            if (enemy.getName().equals(enemyName)) {
                enemies.remove(enemy);
                return;
            }
    }

    public void enter(LocationNeighbour.MovementType movementType) {
        String printName = "";

        switch (movementType) {
            case LOCATION:
                printName = locationName;
                break;
            case INIT:
            case AREA:
                printName = locationName + " of " + area.getAreaName();
                break;
            case REGION:
                printName = locationName + ", of " + area.getRegion().getRegionName() + " Region";
            case TERRITORY:
                printName = locationName + ", of " + area.getRegion().getTerritory().getTerritoryName() + " Territory";
                break;
        }

        World.viewLocation(true);
    }
    public void leave() {
        if (enemies == null || enemies.size() == 0)
            return;

        for (Enemy enemy : enemies)
            if (enemy.getTarget() != null)
                enemy.setTarget(null);
    }

    // This gets called every time a command has been input (every turn).
    public void attackCycle() {
        if (enemies == null || enemies.size() == 0)
            return;

        for (Enemy enemy : enemies)
            if (enemy.isAlive() && enemy.getTarget() != null)
                enemy.attackTarget();
    }
}
