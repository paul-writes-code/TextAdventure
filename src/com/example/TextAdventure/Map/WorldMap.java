package com.example.TextAdventure.Map;

import com.example.TextAdventure.Character.Enemy;

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
    // TODO: add some explanation of what the level strings contain
    private static Area generateCrypt() {

        ArrayList<String> levelStrings = new ArrayList<>();

        levelStrings.add(
                "[03;02'" +
                        "02;01,03;1'" +
                        "01;00,02;2'" +
                        "00;10,01;1'" +
                        "10;00,11;1'" +
                        "]11;10;2");

        levelStrings.add(
                "[11;10;1'" +
                        "10;11,20;2'" +
                        "20;10,21'" +
                        "21;20,22;1'" +
                        "22;21,23;2'" +
                        "]23;22;1");

        levelStrings.add(
                "[23;22,13;2'" +
                        "22;23,12;2'" +
                        "13;23,12;1'" +
                        "12;13,22,11;1'" +
                        "11;12,21;1'" +
                        "x21;11");

        return Area.generateArea("crypt", levelStrings, Enemy.EnemyType.SKELETON);
    }

    // Generates the Dark Forest area
    private static Area generateDarkForest() {

        ArrayList<String> levelStrings = new ArrayList<>();

        levelStrings.add(
                "#11;12'"+
                "12;11,13'" +
                "13;12,23;1'" +
                "23;13,22'" +
                "22;23,32;1'" +
                "]32;22;1");

        levelStrings.add(
                "[01;02'" +
                "02;01,12;1'" +
                "12;02,22'" +
                "22;12,21,32;1'" +
                "#21;22'" +
                "]32;22;1");

        levelStrings.add(
                "[03;13;1'" +
                "13;03,23;1'" +
                "]23;13");

        return Area.generateArea("dark forest", levelStrings, Enemy.EnemyType.BANDIT);
    }

    // Generates the Mountain area
    private static Area generateMountain() {

        ArrayList<String> levelStrings = new ArrayList<>();

        levelStrings.add(
                "[32;22'" +
                "22;32,21'" +
                "21;22,20;1'" +
                "20;21,30;1'" +
                "]30;20");

        levelStrings.add(
                "[00;10'" +
                "10;00,11;1'" +
                "11;10,12'" +
                "12;11,22'" +
                "22;12,21,32'" +
                "21;20,22;1'" +
                "#20;21;1'" +
                "]32;22");

        levelStrings.add(
                "[00;01'" +
                "01;00,11'" +
                "11;01,21;1'" +
                "21;11,22'" +
                "22;21,12;1'" +
                "12;22,13'" +
                "]13;12");

        return Area.generateArea("mountain", levelStrings, Enemy.EnemyType.LIZARD);
    }

    // Generates the Enchanted Swamp area
    private static Area generateEnchantedSwamp() {

        ArrayList<String> levelStrings = new ArrayList<>();

        levelStrings.add(
                "[21;11'" +
                "11;01,21,12;1'" +
                "01;11,02'" +
                "02;01;1'" +
                "12;11,22;1'" +
                "22;12,32'" +
                "32;22,31;1'" +
                "]31;32");

        levelStrings.add(
                "[02;12'" +
                "12;02,22,11;1'" +
                "#22;12'" +
                "11;12,10;1'" +
                "10;11,20'" +
                "20;10,30;2'" +
                "]30;20");

        levelStrings.add(
                "[01;02'" +
                "02;01,12;1'" +
                "12;02,22;1'" +
                "22;12,21'" +
                "21;22,20,31;2'" +
                "20;21,10'" +
                "10;20;1'" +
                "31;21,30'" +
                "]30;31");

        return Area.generateArea("enchanted swamp", levelStrings, Enemy.EnemyType.DARKELF);
    }

    // Generates the Undead Temple area
    private static Area generateUndeadTemple() {

        ArrayList<String> levelStrings = new ArrayList<>();

        levelStrings.add(
                "[11;12'" +
                "12;11,02,13;1'" +
                "02;12,03'" +
                "03;02;2'" +
                "13;12,23;1'" +
                "23;13,33;1'" +
                "33;23,32'" +
                "]32;33;1");

        levelStrings.add(
                "[32;33;1'" +
                "33;32,23'" +
                "23;33,22;1'" +
                "22;23,21;2'" +
                "21;11,31,22'" +
                "31;21,30;1'" +
                "30;31,20'" +
                "20;30;2'" +
                "11;21,10'" +
                "]10;11;1");

        levelStrings.add(
                "[10;00,11,20'" +
                "00;01,10'" +
                "01;00;1'" +
                "11;10,12;2'" +
                "12;11,22;1'" +
                "22;12,21;1'" +
                "20;10,30;1'" +
                "30;20,31;1'" +
                "31;30,21;2'" +
                "]21;22,31");

        levelStrings.add(
                "[21;11'" +
                "11;01,21;1'" +
                "01;11,02;1'" +
                "02;01,03'" +
                "]03;02");

        return Area.generateArea("undead temple", levelStrings, Enemy.EnemyType.UNDEAD);
    }

    // Generates the Bandit Hideout area
    private static Area generateBanditHideout() {

        ArrayList<String> levelStrings = new ArrayList<>();

        levelStrings.add(
                "[20;21;1'" +
                "21;20,31,22;1'" +
                "31;21'" +
                "22;12,21'" +
                "12;22,13;1'" +
                "13;03,23,12'" +
                "]23;13;1'" +
                "03;13,02;2'" +
                "02;03,01;1'" +
                "01;02;1");

        return Area.generateArea("bandit hideout", levelStrings, Enemy.EnemyType.BANDIT);
    }

    // Generates the Lizard Cave area
    private static Area generateLizardCave() {

        ArrayList<String> levelStrings = new ArrayList<>();

        levelStrings.add(
                "[02;12;1'" +
                "12;02,11,13;2'" +
                "11;12,21'" +
                "13;12,23;1'" +
                "23;13;2'" +
                "21;11,20;1'" +
                "20;21,30'" +
                "]30;20;2");

        return Area.generateArea("lizard cave", levelStrings, Enemy.EnemyType.LIZARD);
    }

    // Generates the Dark Elf Cave area
    private static Area generateDarkElfCave() {
        ArrayList<String> levelStrings = new ArrayList<>();

        levelStrings.add(
                "[12;11,22'" +
                "11;12;2'" +
                "22;12,23,32;1'" +
                "23;22,33;1'" +
                "33;23;1'" +
                "32;22,31'" +
                "31;32,30;1'" +
                "30;31,20'" +
                "]20;30;2");

        return Area.generateArea("dark elf cave", levelStrings, Enemy.EnemyType.DARKELF);
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

        // Generate the side objectives
        Area banditHideout = generateBanditHideout();
        Area lizardCave = generateLizardCave();
        Area darkElfCave = generateDarkElfCave();

        // Connect the side objectives
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

        undeadTemple.getEndRoom().addEnemy(Enemy.createEnemy(Enemy.EnemyType.NECROMANCER));

        mapInitialized = true;
    }
}
