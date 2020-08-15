package com.example.TextAdventure.Common;

public class Utility {

    private static final int BASE_EXPERIENCE = 250;
    private static final double EXPONENT = 1.5;

    // Computes how much experience is needed to reach the next level
    public static int getExperienceForNextLevel(int currentLevel) {
        return (int)(BASE_EXPERIENCE * Math.pow(EXPONENT, currentLevel - 1));
    }
}
