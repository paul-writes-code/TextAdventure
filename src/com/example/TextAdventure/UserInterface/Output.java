package com.example.TextAdventure.UserInterface;

public class Output {

    private static final int PAUSE_TIMER = 1000;

    public static void output(String output) {
        System.out.println(output);
    }

    // Pause program execution for a given amount of time
    public static void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        }
        catch (InterruptedException e) { }
    }
    public static void pause() {
        pause(PAUSE_TIMER);
    }
}
