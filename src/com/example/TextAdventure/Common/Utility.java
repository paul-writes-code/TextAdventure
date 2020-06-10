package com.example.TextAdventure.Common;

public class Utility {

    // Experience Management
    public static int getLevelFromExperience(int experience) {
        for (int i = 2; i <= 5; i++)
            if (experience < getExperienceForLevel(i))
                return i - 1;

        if (experience >= getExperienceForLevel(5))
            return 5;

        return -1;
    }
    public static int getExperienceForLevel(int level) {
        switch (level) {
            case 2:
                return 50;
            case 3:
                return 120;
            case 4:
                return 210;
            case 5:
                return 320;
            case 1:
                return 0;
            default:
                return -1;
        }
    }
}
