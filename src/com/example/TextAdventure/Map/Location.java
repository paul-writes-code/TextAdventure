package com.example.TextAdventure.Map;

import com.example.TextAdventure.Character.Enemy;
import com.example.TextAdventure.Character.Merchant;
import com.example.TextAdventure.Common.Strings;
import com.example.TextAdventure.World;

import java.util.ArrayList;

import static com.example.TextAdventure.UserInterface.Output.output;

public class Location {

    public enum MovementType {
        INIT,
        LOCATION,
        AREA,
        REGION,
        TERRITORY
    }
    private static class LocationNeighbour {

        private int locationId;
        private String displayName;
        private MovementType movementType;

        private LocationNeighbour(int locationId) {
            this.locationId = locationId;
        }
    }

    private int locationId;
    private String locationName;
    private String areaName;
    private String regionName;
    private String territoryName;
    private LocationNeighbour[] neighbours;

    private ArrayList<Enemy> enemies;
    private ArrayList<Merchant> merchants;

    public Location(int locationId, String locationName, String areaName, String regionName, String territoryName, int[] neighbourIds) {
        this.locationId = locationId;
        this.locationName = locationName;
        this.areaName = areaName;
        this.regionName = regionName;
        this.territoryName = territoryName;

        neighbours = new LocationNeighbour[neighbourIds.length];
        for (int i = 0; i < neighbourIds.length; i++)
            neighbours[i] = new LocationNeighbour(neighbourIds[i]);

        enemies = new ArrayList<>();
        merchants = new ArrayList<>();
    }
    public void initNeighbours() {
        for (LocationNeighbour neighbour : neighbours) {
            Location location = WorldMap.getLocation(neighbour.locationId);

            if (!location.territoryName.equals(territoryName)) {
                neighbour.displayName = location.territoryName;
                neighbour.movementType = MovementType.TERRITORY;
            }
            else if (!location.regionName.equals(regionName)) {
                neighbour.displayName = location.regionName;
                neighbour.movementType = MovementType.REGION;
            }
            else if (!location.areaName.equals(areaName)) {
                neighbour.displayName = location.areaName;
                neighbour.movementType = MovementType.AREA;
            }
            else {
                neighbour.displayName = location.locationName;
                neighbour.movementType = MovementType.LOCATION;
            }
        }
    }

    public int getLocationId() { return locationId; }
    public String getLocationName() { return locationName; }
    public String getAreaName() { return areaName; }
    public String getRegionName() { return regionName; }
    public String getTerritoryName() { return territoryName; }
    public Location getNeighbour(String displayName) {
        for (LocationNeighbour locationNeighbour : neighbours)
            if (locationNeighbour.displayName.equals(displayName))
                return WorldMap.getLocation(locationNeighbour.locationId);

        return null;
    }
    public MovementType getMovementType(String displayName) {
        for (LocationNeighbour locationNeighbour : neighbours)
            if (locationNeighbour.displayName.equals(displayName))
                return locationNeighbour.movementType;

        return null;
    }

    public Enemy getEnemy(String enemyName) {
        for (Enemy enemy : enemies)
            if (enemy.getName().equals(enemyName))
                return enemy;

        return null;
    }
    public Merchant getMerchant(String merchantName) {
        for (Merchant merchant : merchants)
            if (merchant.getName().equals(merchantName))
                return merchant;

        return null;
    }
    public ArrayList<Enemy> getEnemies() { return enemies; }
    public ArrayList<Merchant> getMerchants() { return merchants; }

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

    public void addMerchant(Merchant merchant) {
        if (merchant == null)
            return;

        if (merchants == null)
            merchants = new ArrayList<>();

        merchants.add(merchant);
    }
    public void removeMerchant(String merchantName) {
        for (Merchant merchant : merchants)
            if (merchant.getName().equals(merchantName)) {
                merchants.remove(merchant);
                return;
            }
    }

    public void enter(MovementType movementType, boolean enteringGame) {
        String printName = "";

        if (enteringGame || movementType == MovementType.AREA)
            printName = String.format(Strings.WORLD_AREA_NAME, locationName, areaName);
        else if (movementType == MovementType.TERRITORY)
            printName = String.format(Strings.WORLD_TERRITORY_NAME, locationName, territoryName);
        else if (movementType == MovementType.REGION)
            printName = String.format(Strings.WORLD_REGION_NAME, locationName, regionName);
        else
            printName = locationName;

        World.viewLocation(true);
    }
    public void enter(MovementType movementType) {
        enter(movementType, false);
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

    public void viewLocation(boolean entering) {
        ArrayList<Enemy> attackList = new ArrayList<>();
        ArrayList<Enemy> lootList = new ArrayList<>();

        if (entering)
            output(Strings.LOCATION_DISPLAY_TITLE_ENTER, locationName, areaName);
        else
            output(Strings.LOCATION_DISPLAY_TITLE_EXAMINE, locationName, areaName);

        // Display local map
        if (neighbours != null)
            for (Location.LocationNeighbour neighbour : neighbours)
                output(Strings.LOCATION_DISPLAY_OBJECT_GO, neighbour.displayName);

        // Display local merchants
        if (merchants != null)
            for (Merchant merchant : merchants)
                output(Strings.LOCATION_DISPLAY_OBJECT_TRADE, merchant.getName());

        // Display local enemies
        if (enemies != null)
            for (Enemy enemy : enemies)
                if (enemy.getCurrentHealth() > 0)
                    attackList.add(enemy);
                else
                    lootList.add(enemy);

        for (Enemy enemy : attackList)
            output(Strings.LOCATION_DISPLAY_OBJECT_ATTACK, enemy.getName(), enemy.getLevel(), enemy.getCurrentHealth(), enemy.getMaxHealth());

        for (Enemy enemy : lootList)
            output(Strings.LOCATION_DISPLAY_OBJECT_LOOT, enemy.getName(), enemy.inventoryToString());
    }
}
