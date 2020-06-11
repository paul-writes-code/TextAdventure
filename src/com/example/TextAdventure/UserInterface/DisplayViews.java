package com.example.TextAdventure.UserInterface;

import com.example.TextAdventure.Character.Enemy;
import com.example.TextAdventure.Character.Merchant;
import com.example.TextAdventure.Common.Utility;
import com.example.TextAdventure.Equipment.Equipment;
import com.example.TextAdventure.Equipment.EquipmentSet;
import com.example.TextAdventure.Item.Inventory;
import com.example.TextAdventure.Character.Character;
import com.example.TextAdventure.Item.Item;
import com.example.TextAdventure.Map.Location;

import java.util.ArrayList;

import static com.example.TextAdventure.UserInterface.Output.output;

public class DisplayViews {
    public static void viewLocation(Location location, boolean entering) {
        String displayName = location.getLocationName() + " of " + location.getArea().getAreaName();
        ArrayList<Enemy> attackList = new ArrayList<>();
        ArrayList<Enemy> lootList = new ArrayList<>();

        if (entering)
            Output.output("Entering " + displayName + ".");
        else
            Output.output("Your current location is " + displayName + ".");

        // Display local map
        if (location.getNeighbours() != null)
            for (Location.LocationNeighbour neighbour : location.getNeighbours())
                output("  " + Input.COMMAND_GO + ": " + neighbour.getDisplayName());

        if (location.getMerchants() != null)
            for (Merchant merchant : location.getMerchants())
                output("  " + Input.COMMAND_TRADE + ": " + merchant.getName());

        // Display local enemies
        if (location.getEnemies() != null)
            for (Enemy enemy : location.getEnemies())
                if (enemy.getCurrentHealth() > 0)
                    attackList.add(enemy);
                else
                    lootList.add(enemy);

        for (Enemy enemy : attackList)
            output("  " + Input.COMMAND_ATTACK + ": " + enemy.getName() + "; level " + enemy.getLevel() + ", " + enemy.getCurrentHealth() + "/" + enemy.getMaxHealth() + " health");

        for (Enemy enemy : lootList)
            output("  " + Input.COMMAND_LOOT + ": " + enemy.getName() + "; " + enemy.getInventory().toString());
    }
    public static void viewEquipmentSet(EquipmentSet equipmentSet) {
        output("EQUIPMENT:");
        output("\thealth bonus: " + equipmentSet.getMaxHealthBonus());
        output("\tdamage bonus: " + equipmentSet.getDamageBonus());
        output("\tdefence bonus: " + equipmentSet.getDefenceBonus());

        output("\n\tarmour: " + (equipmentSet.getArmour() == null ? "empty" : equipmentSet.getArmour().getItemName()));
        output("\tsword: " + (equipmentSet.getSword() == null ? "empty" : equipmentSet.getSword().getItemName()));
        output("\tshield: " + (equipmentSet.getShield() == null ? "empty" : equipmentSet.getShield().getItemName()));
    }
    public static void viewInventory(Inventory inventory) {
        ArrayList<Item> equipList = new ArrayList<>();
        ArrayList<Item> otherList = new ArrayList<>();

        output("INVENTORY:");
        output("\t" + inventory.getGold() + " gold");
        output("\t" + inventory.getNumHealthPotions() + " health potions");

        for (Item item : inventory.getItems())
            if (item instanceof Equipment)
                equipList.add(item);
            else
                otherList.add(item);

        for (Item item : equipList)
            output("\t" + Input.COMMAND_EQUIP + ": " + item.getItemName());

        for (Item item : otherList)
            output("\t" + item.getItemName());
    }
    public static void viewCharacter(Character character) {
        output("CHARACTER:");
        output("\tname: " + character.getName());
        output("\thealth: " + character.getCurrentHealth() + "/" + character.getMaxHealth());
        output("\tlevel: " + character.getLevel());
        output("\tdamage: " + character.getDamage());
        output("\tdefence: " + character.getDefence());
        output("\texperience: " + character.getExperience() + "/" + (character.getLevel() == 5 ? "-" : Utility.getExperienceForLevel(character.getLevel() + 1)));
    }
    public static void viewTrade(Character character, Merchant merchant) {

        output("INVENTORY:");
        output("\t" + character.getInventory().getGold() + " gold");
        if (character.getInventory().getItems().size() == 0)
            output("\t<empty>");
        for (Item item : character.getInventory().getItems())
            output("\t" + Input.COMMAND_SELL + ": " + item.getItemName() + "; " + item.getBuyPrice() + " gold");

        output("MERCHANT:");
        if (merchant.getInventory().getItems().size() == 0)
            output("\t<empty>");
        for (Item item : merchant.getInventory().getItems())
            output("\t" + Input.COMMAND_BUY + ": " + item.getItemName() + "; " + item.getSellPrice() + " gold");
    }
}
