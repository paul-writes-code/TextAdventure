package com.example.TextAdventure.Map;

import com.example.TextAdventure.Character.Enemy;

import java.util.ArrayList;

public class WorldMap {
    private static boolean locationsInitialized = false;

    private static ArrayList<Area> areas;

    private static Room spawnRoom = null;

    public static Room getSpawnRoom() {
        initMap();
        return spawnRoom;
    }
    public static void setSpawnRoom(Room room) {
        spawnRoom = room;
    }

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
                "22;12,32'" +
                "]32;22;2");

        levelStrings.add(
                "[02;12;1'" +
                "12;02,11;2'" +
                "11;12,21;2'" +
                "21;11,20,22;2'" +
                "22;21,23;3'" +
                "#23;22;3'" +
                "20;21,30;2'" +
                "]30;20;1");

        levelStrings.add(
                "[03;13;1'" +
                "13;03,23;1'" +
                "]23;13");

        return Area.generateArea("dark forest", levelStrings, Enemy.EnemyType.BANDIT);
    }
    private static Area generateMountain() {

        ArrayList<String> levelStrings = new ArrayList<>();

        levelStrings.add(
                "[32;22'" +
                "22;32,21;1'" +
                "21;22,20;1'" +
                "20;21,30;2'" +
                "]30;20;1");

        levelStrings.add(
                "[00;10'" +
                "10;00,11;1'" +
                "11;10,12;2'" +
                "12;11,13'" +
                "13;12,23;1'" +
                "23;13,22;1'" +
                "22;21,23,32;2'" +
                "21;20,22;2'" +
                "#20;21,30;3'" +
                "30;20;2'" +
                "32;22,33;1'" +
                "]33;32;1");

        levelStrings.add(
                "[00;01;1'" +
                "01;00,11;1'" +
                "11;01,21;2'" +
                "21;11,20,22;2'" +
                "20;21;1'" +
                "22;21,12;0'" +
                "12;22,13;1'" +
                "]13;12");

        return Area.generateArea("mountain", levelStrings, Enemy.EnemyType.LIZARD);
    }
    private static Area generateEnchantedSwamp() {

        ArrayList<String> levelStrings = new ArrayList<>();

        levelStrings.add(
                "[21;11'" +
                "11;01,21,12;1'" +
                "01;11,02;2'" +
                "02;01;1'" +
                "12;11,22;1'" +
                "22;12,32;2'" +
                "32;22,31;1'" +
                "]31;32");

        levelStrings.add(
                "[02;12;1'" +
                "12;02,22,11;2'" +
                "#22;12;3'" +
                "11;12,10;3'" +
                "10;11,20;2'" +
                "20;10,30;1'" +
                "]30;20;1");

        levelStrings.add(
                "[01;02;1'" +
                "02;01,12;1'" +
                "12;02,22;2'" +
                "22;12,21'" +
                "21;22,20,31;1'" +
                "20;21,10;2'" +
                "10;20;1'" +
                "31;21,30;2'" +
                "]30;31;1");

        return Area.generateArea("enchanted swamp", levelStrings, Enemy.EnemyType.DARKELF);
    }
    private static Area generateUndeadTemple() {

        ArrayList<String> levelStrings = new ArrayList<>();

        levelStrings.add(
                "[12;13;2'" +
                "13;03,12,23;2'" +
                "03;13,02;3'" +
                "02;03;2'" +
                "23;13,22;2'" +
                "22;23,21;3'" +
                "21;22,20,31;3'" +
                "20;21,10;2'" +
                "10;00,20;2'" +
                "00;10;3'" +
                "31;21,32;2'" +
                "32;31,33;2'" +
                "]33;32");

        levelStrings.add(
                "[33;23;2'" +
                "23;13,33,22;2'" +
                "13;23,03;3'" +
                "03;13;3'" +
                "22;23,21;3'" +
                "21;11,22,31;1'" +
                "31;21,30'" +
                "30;20,31'" +
                "]20;10,30;2'" +
                "10;00,11,20;3'" +
                "11;10,21;2'" +
                "00;10,01;1'" +
                "01;00,02;2'" +
                "02;01,12'" +
                "12;02;1");

        levelStrings.add(
                "[20;30;2'" +
                "30;20,31;2'" +
                "31;30,32;3'" +
                "32;31,33;2'" +
                "33;32,23'" +
                "23;22,33;1'" +
                "22;12,21,23;2'" +
                "21;11,22;1'" +
                "11;01,10,21;1'" +
                "10;11'" +
                "01;11,02;3'" +
                "]02;01,03;3'" +
                "03;02,13;2'" +
                "13;03,12;3'" +
                "12;13,22;2");

        levelStrings.add(
                "[02;03,01,12;2'" +
                "03;02,13;3'" +
                "13;03,23;2'" +
                "23;13,22;3'" +
                "12;02,22;3'" +
                "22;12,23,32;2'" +
                "32;22;1'" +
                "01;02,11;2'" +
                "11;01,21;3'" +
                "21;11,20,31;2'" +
                "31;21;3'" +
                "20;21,10;2'" +
                "]10;20");

        levelStrings.add(
                "[10;11'" +
                "11;10,21'" +
                "21;11,22'" +
                "22;21,23'" +
                "]23;22");

        return Area.generateArea("undead temple", levelStrings, Enemy.EnemyType.UNDEAD);
    }

    private static Area generateCrypt() {

        ArrayList<String> levelStrings = new ArrayList<>();

        levelStrings.add(
                "[03;02'" +
                "02;01,03;1'" +
                "01;00,02;2'" +
                "00;10,01;2'" +
                "10;00,11;2'" +
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
    private static Area generateBanditHideout() {

        ArrayList<String> levelStrings = new ArrayList<>();

        levelStrings.add(
                "[20;21;1'" +
                "21;20,31,22;1'" +
                "31;21,32'" +
                "32;22,31;2'" +
                "22;12,21,32;3'" +
                "12;22,13;1'" +
                "13;03,23,12'" +
                "]23;13;2'" +
                "03;13,02;2'" +
                "02;03,01;1'" +
                "01;02,00'" +
                "00;01;2");

        levelStrings.add(
                "[10;00,20,11;2'" +
                "00;01,10;2'" +
                "01;00;2'" +
                "11;10;3'" +
                "20;10,21;2'" +
                "21;20,22;2'" +
                "22;21,12;2'" +
                "12;22;3");

        return Area.generateArea("bandit hideout", levelStrings, Enemy.EnemyType.BANDIT);
    }
    private static Area generateLizardCave() {

        ArrayList<String> levelStrings = new ArrayList<>();

        levelStrings.add(
                "[02;12;1'" +
                "12;02,11,13;2'" +
                "11;12,21;1'" +
                "13;12,23;1'" +
                "23;13,22;2'" +
                "22;23,21'" +
                "21;11,22,20;1'" +
                "20;21,30;1'" +
                "]30;20;2");

        levelStrings.add(
                "[11;12;2'" +
                "12;11,13;2'" +
                "13;12,23;3'" +
                "23;13,33;2'" +
                "33;23,32;3'" +
                "32;33;3");

        return Area.generateArea("lizard cave", levelStrings, Enemy.EnemyType.LIZARD);
    }
    private static Area generateDarkElfCave() {
        ArrayList<String> levelStrings = new ArrayList<>();

        levelStrings.add(
                "[12;02,11,22;2'" +
                "02;12,01;2'" +
                "01;02,11;2'" +
                "11;01,12;2'" +
                "22;12,23,32;3'" +
                "23;22,33;3'" +
                "33;23;3'" +
                "32;22,31;2'" +
                "31;32,30;2'" +
                "30;31,20;1'" +
                "]20;30;1");

        levelStrings.add(
                "[13;12;2'" +
                "12;13,22;2'" +
                "22;12,21;3'" +
                "21;11,20,22,31;4'" +
                "31;21,30;3'" +
                "30;31;1'" +
                "11;21,10;3'" +
                "10;11,20;2'" +
                "20;10,21");

        return Area.generateArea("dark elf cave", levelStrings, Enemy.EnemyType.DARKELF);
    }

    private static void initMap() {
        if (locationsInitialized)
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

        locationsInitialized = true;
    }
}
