package com.example.TextAdventure;

import java.awt.*;
import java.io.Console;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Console console = System.console();

        // If there is no open console, then one is launched to run the game
        if (console == null && !GraphicsEnvironment.isHeadless()) {
            String fileName = Main.class.getProtectionDomain().getCodeSource().getLocation().toString().substring(6);
            Runtime.getRuntime().exec(new String[]{"cmd","/c","start","cmd","/k","java -jar \"" + fileName + "\""});
        }

        World.runGame();
    }
}
