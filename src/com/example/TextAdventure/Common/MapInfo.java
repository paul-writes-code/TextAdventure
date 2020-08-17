package com.example.TextAdventure.Common;

// The map is stored in Strings which contain information about each room in a level, in the following order:
//      -Some rooms begin with a marker:
//          [ - start room of a level,
//          ] - end room of a level,
//          # - area exit room of a level (can lead to side areas other than the main path)
//          x - spawn room of the game
//      -The next two digits represent the (x, y) coordinates of the room within the floor, followed by a semi-colon
//      -Then there is a list of two-digit numbers, separated by commas. Each two-digit number represents the (x, y) coordinates of an adjacent room.
//      -If a room has enemies, then next is a semi-colon followed by the number of enemies in the room.
//
//      Each room is separated from the next with an apostrophe.
//
//      So "[11;12,01,21;1" means that the start room of this level has coordinates (1,1), is adjacent to the rooms with coordinates (1,2), (0,1) and (2,1), and has 1 enemy.
//         "12;11" means there is a room with coordinates (1,2) which is adjacent to room (1,1) and has no enemies.

public class MapInfo {

    // CRYPT
    public static final String CRYPT_LEVEL_1_STRING =
                "[03;02'" +
                "02;01,03;1'" +
                "01;00,02;2'" +
                "00;10,01;1'" +
                "10;00,11;1'" +
                "]11;10;2";

    public static final String CRYPT_LEVEL_2_STRING =
                "[11;10;1'" +
                "10;11,20;2'" +
                "20;10,21'" +
                "21;20,22;1'" +
                "22;21,23;2'" +
                "]23;22;1";

    public static final String CRYPT_LEVEL_3_STRING =
                "[23;22,13;2'" +
                "22;23,12;2'" +
                "13;23,12;1'" +
                "12;13,22,11;1'" +
                "11;12,21;1'" +
                "x21;11";


    // DARK FOREST
    public static final String DARK_FOREST_LEVEL_1_STRING =
             "#11;12'"+
             "12;11,13'" +
             "13;12,23;1'" +
             "23;13,22'" +
             "22;23,32;1'" +
             "]32;22;1";

    public static final String DARK_FOREST_LEVEL_2_STRING =
                "[01;02'" +
                "02;01,12;1'" +
                "12;02,22'" +
                "22;12,21,32;1'" +
                "#21;22'" +
                "]32;22;1";

    public static final String DARK_FOREST_LEVEL_3_STRING =
                "[03;13;1'" +
                "13;03,23;1'" +
                "]23;13";


    // MOUNTAIN
    public static final String MOUNTAIN_LEVEL_1_STRING =
                "[32;22'" +
                "22;32,21'" +
                "21;22,20;1'" +
                "20;21,30;1'" +
                "]30;20";

    public static final String MOUNTAIN_LEVEL_2_STRING =
                "[00;10'" +
                "10;00,11;1'" +
                "11;10,12'" +
                "12;11,22'" +
                "22;12,21,32'" +
                "21;20,22;1'" +
                "#20;21;1'" +
                "]32;22";

    public static final String MOUNTAIN_LEVEL_3_STRING =
                "[00;01'" +
                "01;00,11'" +
                "11;01,21;1'" +
                "21;11,22'" +
                "22;21,12;1'" +
                "12;22,13'" +
                "]13;12";


    // ENCHANTED SWAMP
    public static final String ENCHANTED_SWAMP_LEVEL_1_STRING =
                "[21;11'" +
                "11;01,21,12;1'" +
                "01;11,02'" +
                "02;01;1'" +
                "12;11,22;1'" +
                "22;12,32'" +
                "32;22,31;1'" +
                "]31;32";

    public static final String ENCHANTED_SWAMP_LEVEL_2_STRING =
                "[02;12'" +
                "12;02,22,11;1'" +
                "#22;12'" +
                "11;12,10;1'" +
                "10;11,20'" +
                "20;10,30;2'" +
                "]30;20";

    public static final String ENCHANTED_SWAMP_LEVEL_3_STRING =
                "[01;02'" +
                "02;01,12;1'" +
                "12;02,22;1'" +
                "22;12,21'" +
                "21;22,20,31;2'" +
                "20;21,10'" +
                "10;20;1'" +
                "31;21,30'" +
                "]30;31";


    // UNDEAD TEMPLE
    public static final String UNDEAD_TEMPLE_LEVEL_1_STRING =
                "[11;12'" +
                "12;11,02,13;1'" +
                "02;12,03'" +
                "03;02;2'" +
                "13;12,23;1'" +
                "23;13,33;1'" +
                "33;23,32'" +
                "]32;33;1";

    public static final String UNDEAD_TEMPLE_LEVEL_2_STRING =
                "[32;33;1'" +
                "33;32,23'" +
                "23;33,22;1'" +
                "22;23,21;2'" +
                "21;11,31,22'" +
                "31;21,30;1'" +
                "30;31,20'" +
                "20;30;2'" +
                "11;21,10'" +
                "]10;11;1";

    public static final String UNDEAD_TEMPLE_LEVEL_3_STRING =
                "[10;00,11,20'" +
                "00;01,10'" +
                "01;00;1'" +
                "11;10,12;2'" +
                "12;11,22;1'" +
                "22;12,21;1'" +
                "20;10,30;1'" +
                "30;20,31;1'" +
                "31;30,21;2'" +
                "]21;22,31";

    public static final String UNDEAD_TEMPLE_LEVEL_4_STRING =
                "[21;11'" +
                "11;01,21;1'" +
                "01;11,02;1'" +
                "02;01,03'" +
                "]03;02";


    // BANDIT HIDEOUT
    public static final String BANDIT_HIDEOUT_STRING =
                "[20;21;1'" +
                "21;20,22;1'" +
                "22;12,21'" +
                "12;22,13;1'" +
                "13;03,23,12'" +
                "]23;13;1'" +
                "03;13,02;2'" +
                "02;03,01;1'" +
                "01;02;1";


    // LIZARD CAVE
    public static final String LIZARD_CAVE_STRING =
                "[02;12;1'" +
                "12;02,11,13;2'" +
                "11;12,21'" +
                "13;12,23;1'" +
                "23;13;2'" +
                "21;11,20;1'" +
                "20;21,30'" +
                "]30;20;2";


    // DARK ELF CAVE
    public static final String DARK_ELF_CAVE_STRING =
                "[12;11,22'" +
                "11;12;2'" +
                "22;12,23,32;1'" +
                "23;22,33;1'" +
                "33;23;1'" +
                "32;22,31'" +
                "31;32,30;1'" +
                "30;31,20'" +
                "]20;30;2";
}
