package com.example.TextAdventure.Map;

import com.example.TextAdventure.Character.Enemy;
import com.example.TextAdventure.Common.MapInfo;
import com.example.TextAdventure.Common.Strings;

import java.util.ArrayList;

public class WorldMap {
    private static boolean mapInitialized = false;

    private static ArrayList<Area> areas;
    private static Room spawnRoom = null;

    public static Room getSpawnRoom() {
        initMap();
        return spawnRoom;
    }

    public static void setSpawnRoom(Room room) {
        spawnRoom = room;
    }

    // Generates the Crypt area
    private static Area generateCrypt() {
        ArrayList<String> levelStrings = new ArrayList<>();

        levelStrings.add(MapInfo.CRYPT_LEVEL_1_STRING);
        levelStrings.add(MapInfo.CRYPT_LEVEL_2_STRING);
        levelStrings.add(MapInfo.CRYPT_LEVEL_3_STRING);

        return Area.generateArea(Strings.NAME_CRYPT, levelStrings, Enemy.EnemyType.SKELETON);
    }

    // Generates the Dark Forest area
    private static Area generateDarkForest() {
        ArrayList<String> levelStrings = new ArrayList<>();

        levelStrings.add(MapInfo.DARK_FOREST_LEVEL_1_STRING);
        levelStrings.add(MapInfo.DARK_FOREST_LEVEL_2_STRING);
        levelStrings.add(MapInfo.DARK_FOREST_LEVEL_3_STRING);

        return Area.generateArea(Strings.NAME_DARK_FOREST, levelStrings, Enemy.EnemyType.BANDIT);
    }

    // Generates the Mountain area
    private static Area generateMountain() {
        ArrayList<String> levelStrings = new ArrayList<>();

        levelStrings.add(MapInfo.MOUNTAIN_LEVEL_1_STRING);
        levelStrings.add(MapInfo.MOUNTAIN_LEVEL_2_STRING);
        levelStrings.add(MapInfo.MOUNTAIN_LEVEL_3_STRING);

        return Area.generateArea(Strings.NAME_MOUNTAIN, levelStrings, Enemy.EnemyType.LIZARD);
    }

    // Generates the Enchanted Swamp area
    private static Area generateEnchantedSwamp() {
        ArrayList<String> levelStrings = new ArrayList<>();

        levelStrings.add(MapInfo.ENCHANTED_SWAMP_LEVEL_1_STRING);
        levelStrings.add(MapInfo.ENCHANTED_SWAMP_LEVEL_2_STRING);
        levelStrings.add(MapInfo.ENCHANTED_SWAMP_LEVEL_3_STRING);

        return Area.generateArea(Strings.NAME_ENCHANTED_SWAMP, levelStrings, Enemy.EnemyType.DARKELF);
    }

    // Generates the Undead Temple area
    private static Area generateUndeadTemple() {
        ArrayList<String> levelStrings = new ArrayList<>();

        levelStrings.add(MapInfo.UNDEAD_TEMPLE_LEVEL_1_STRING);
        levelStrings.add(MapInfo.UNDEAD_TEMPLE_LEVEL_2_STRING);
        levelStrings.add(MapInfo.UNDEAD_TEMPLE_LEVEL_3_STRING);
        levelStrings.add(MapInfo.UNDEAD_TEMPLE_LEVEL_4_STRING);

        return Area.generateArea(Strings.NAME_UNDEAD_TEMPLE, levelStrings, Enemy.EnemyType.UNDEAD);
    }

    // Generates the Bandit Hideout area
    private static Area generateBanditHideout() {
        ArrayList<String> levelStrings = new ArrayList<>();

        levelStrings.add(MapInfo.BANDIT_HIDEOUT_STRING);

        return Area.generateArea(Strings.NAME_BANDIT_HIDEOUT, levelStrings, Enemy.EnemyType.BANDIT);
    }

    // Generates the Lizard Cave area
    private static Area generateLizardCave() {
        ArrayList<String> levelStrings = new ArrayList<>();

        levelStrings.add(MapInfo.LIZARD_CAVE_STRING);

        return Area.generateArea(Strings.NAME_LIZARD_CAVE, levelStrings, Enemy.EnemyType.LIZARD);
    }

    // Generates the Dark Elf Cave area
    private static Area generateDarkElfCave() {
        ArrayList<String> levelStrings = new ArrayList<>();

        levelStrings.add(MapInfo.DARK_ELF_CAVE_STRING);

        return Area.generateArea(Strings.NAME_DARK_ELF_CAVE, levelStrings, Enemy.EnemyType.DARKELF);
    }

    private static void initMap() {
        if (mapInitialized)
            return;

        areas = new ArrayList<>();

        // Generate the main map
        Area crypt = generateCrypt();
        Area darkForest = generateDarkForest();
        Area mountain = generateMountain();
        Area enchantedSwamp = generateEnchantedSwamp();
        Area undeadTemple = generateUndeadTemple();

        // Connect the main map
        Area.connectAreaToExitRoom(darkForest.getAreaExitRooms().get(0), crypt);
        Area.connectAreas(darkForest, mountain);
        Area.connectAreas(mountain, enchantedSwamp);
        Area.connectAreas(enchantedSwamp, undeadTemple);

        // Generate the side areas
        Area banditHideout = generateBanditHideout();
        Area lizardCave = generateLizardCave();
        Area darkElfCave = generateDarkElfCave();

        // Connect the side areas
        Area.connectAreaToExitRoom(darkForest.getAreaExitRooms().get(1), banditHideout);
        Area.connectAreaToExitRoom(mountain.getAreaExitRooms().get(0), lizardCave);
        Area.connectAreaToExitRoom(enchantedSwamp.getAreaExitRooms().get(0), darkElfCave);

        areas.add(crypt);
        areas.add(darkForest);
        areas.add(mountain);
        areas.add(enchantedSwamp);
        areas.add(undeadTemple);
        areas.add(banditHideout);
        areas.add(lizardCave);
        areas.add(darkElfCave);

        // Add the final boss to the last room of the undead temple
        undeadTemple.getEndRoom().addEnemy(Enemy.createEnemy(Enemy.EnemyType.NECROMANCER));

        mapInitialized = true;
    }
}
