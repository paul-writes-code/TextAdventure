package com.example.TextAdventure.UserInterface;

import com.example.TextAdventure.Character.Merchant;
import com.example.TextAdventure.Common.Strings;
import com.example.TextAdventure.Common.Utility;
import com.example.TextAdventure.Equipment.Equipment;
import com.example.TextAdventure.Equipment.EquipmentSet;
import com.example.TextAdventure.Item.Inventory;
import com.example.TextAdventure.Character.Character;
import com.example.TextAdventure.Item.Item;

import java.util.ArrayList;

import static com.example.TextAdventure.UserInterface.Output.output;

public class DisplayViews {

    public static void viewEquipmentSet(EquipmentSet equipmentSet) {
        output(Strings.EQUIPMENT_DISPLAY_TITLE);

        output(Strings.EQUIPMENT_DISPLAY_ARMOUR, equipmentSet.getArmour() == null ? Strings.EQUIPMENT_DISPLAY_EMPTY : equipmentSet.getArmour().getItemName());
        output(Strings.EQUIPMENT_DISPLAY_SWORD, equipmentSet.getSword() == null ? Strings.EQUIPMENT_DISPLAY_EMPTY : equipmentSet.getSword().getItemName());
        output(Strings.EQUIPMENT_DISPLAY_SHIELD + "\n", equipmentSet.getShield() == null ? Strings.EQUIPMENT_DISPLAY_EMPTY : equipmentSet.getShield().getItemName());

        output(Strings.EQUIPMENT_DISPLAY_HEALTH_BONUS, equipmentSet.getMaxHealthBonus());
        output(Strings.EQUIPMENT_DISPLAY_DAMAGE_BONUS, equipmentSet.getDamageBonus());
        output(Strings.EQUIPMENT_DISPLAY_DEFENCE_BONUS, equipmentSet.getDefenceBonus());
    }
    public static void viewInventory(Inventory inventory) {
        ArrayList<Item> equipList = new ArrayList<>();
        ArrayList<Item> otherList = new ArrayList<>();

        output(Strings.INVENTORY_DISPLAY_TITLE);
        output(Strings.INVENTORY_DISPLAY_GOLD, inventory.getGold());
        output(Strings.INVENTORY_DISPLAY_HEALTH_POTIONS, inventory.getNumHealthPotions());

        for (Item item : inventory.getItems())
            if (item instanceof Equipment)
                equipList.add(item);
            else
                otherList.add(item);

        for (Item item : equipList)
            output(Strings.INVENTORY_DISPLAY_ITEM_EQUIP, item.getItemName());

        for (Item item : otherList)
            output(Strings.INVENTORY_DISPLAY_ITEM, item.getItemName());
    }
    public static void viewCharacter(Character character) {
        output(Strings.CHARACTER_DISPLAY_TITLE);
        output(Strings.CHARACTER_DISPLAY_NAME, character.getName());
        output(Strings.CHARACTER_DISPLAY_HEALTH, character.getCurrentHealth(), character.getMaxHealth());
        output(Strings.CHARACTER_DISPLAY_LEVEL, character.getLevel());
        output(Strings.CHARACTER_DISPLAY_DAMAGE, character.getDamage());
        output(Strings.CHARACTER_DISPLAY_DEFENCE, character.getDefence());

        if (character.getLevel() == 5)
            output(Strings.CHARACTER_DISPLAY_EXPERIENCE_MAX_LEVEL, character.getExperience());
        else
            output(Strings.CHARACTER_DISPLAY_EXPERIENCE, character.getExperience(), Utility.getExperienceForLevel(character.getLevel() + 1));
    }
    public static void viewTrade(Character character, Merchant merchant) {
        output(Strings.TRADE_DISPLAY_TITLE, merchant.getName());
        output(Strings.TRADE_DISPLAY_GOLD, character.getInventory().getGold());

        for (Item item : merchant.getInventory().getItems())
            output(Strings.TRADE_DISPLAY_OBJECT_BUY, item.getItemName(), item.getSellPrice());

        for (Item item : character.getInventory().getItems())
            output(Strings.TRADE_DISPLAY_OBJECT_SELL, item.getItemName(), item.getBuyPrice());

        if (merchant.getInventory().getItems().size() == 0 && character.getInventory().getItems().size() == 0)
            output(Strings.TRADE_DISPLAY_EMPTY);
    }
}
