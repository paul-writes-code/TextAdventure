package com.example.TextAdventure;

import com.example.TextAdventure.Equipment.EquipmentSet;
import com.example.TextAdventure.Item.Inventory;
import com.example.TextAdventure.Character.Character;

import static com.example.TextAdventure.UserInterface.Output.output;

public class Common {

    public static boolean tryParseInt(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void viewEquipmentSet(EquipmentSet equipmentSet) {
        output("EQUIPMENT:");
        output("\tHealth Bonus: " + equipmentSet.getMaxHealthBonus());
        output("\tDamage Bonus: " + equipmentSet.getDamageBonus());
        output("\tDefence Bonus: " + equipmentSet.getDefenceBonus());

        output("\n\t1. Armour: " + (equipmentSet.getArmour() == null ? "empty" : equipmentSet.getArmour().getItemName()));
        output("\t2. Sword: " + (equipmentSet.getSword() == null ? "empty" : equipmentSet.getSword().getItemName()));
        output("\t3. Shield: " + (equipmentSet.getShield() == null ? "empty" : equipmentSet.getShield().getItemName()));

    }

    public static void viewInventory(Inventory inventory) {
        output("INVENTORY:");
        output("\t" + inventory.getGold() + " Gold");
        output("\t" + inventory.getNumHealthPotions() + " Health Potions");

        for (int i = 1; i <= inventory.getItems().size(); i++)
            output("\t" + i + ". " + inventory.getItems().get(i - 1).toString());
    }

    public static void viewCharacter(Character character) {
        output("CHARACTER:");
        output("\tName: " + character.getName());
        output("\tHealth: " + character.getCurrentHealth() + "/" + character.getMaxHealth());
        output("\tDamage: " + character.getDamage());
        output("\tDefence: " + character.getDefence());
    }
}
