package com.example.TextAdventure.Item;

import com.example.TextAdventure.Equipment.Equipment;

public class ItemList {

    private static boolean initialized = false;
    private static Item[] items;

    private static final String equipmentString = "{" +
            "0;armour1;25;armour;4;0;4'" +
            "1;armour2;50;armour;7;0;8'" +
            "2;armour3;75;armour;10;0;12'" +
            "3;armourX;100;armour;20;0;20'" +
            "4;sword1;25;sword;0;5;0'" +
            "5;sword2;50;sword;0;8;0'" +
            "6;sword3;75;sword;0;10;0'" +
            "7;sword of despair;100;sword;0;25;0'" +
            "8;shield1;25;shield;4;3;3'" +
            "9;shield2;50;shield;8;4;6'" +
            "10;shield3;75;shield;12;6;9'" +
            "11;shieldX;100;shield;20;10;20" +
            "}";
    private static final String itemString = "{" +
            "12;Apple seeds;3;0;1'" +
            "13;Tea pot;1;1;3" +
            "}";

    public static void initItemList() {
        if (initialized)
            return;

        String[] itemLines = itemString.substring(1, itemString.length() - 1) // remove '{' and '}'
                .split("'"); // each line separated by apostrophe
        String[] equipmentLines = equipmentString.substring(1, equipmentString.length() - 1)
                .split("'");

        items = new Item[itemLines.length + equipmentLines.length];

        for (int i = 0; i < equipmentLines.length; i++) {
            String[] equipmentInfo = equipmentLines[i].split(";");

            int itemId = Integer.parseInt(equipmentInfo[0]);
            String itemName = equipmentInfo[1];
            int unitPrice = Integer.parseInt(equipmentInfo[2]);
            Equipment.EquipmentType equipmentType = equipmentInfo[3].equals("armour") ? Equipment.EquipmentType.ARMOUR :
                    equipmentInfo[3].equals("sword") ? Equipment.EquipmentType.SWORD : Equipment.EquipmentType.SHIELD;
            int maxHealthBonus = Integer.parseInt(equipmentInfo[4]);
            int damageBonus = Integer.parseInt(equipmentInfo[5]);
            int defenceBonus = Integer.parseInt(equipmentInfo[6]);

            items[i] = new Equipment(equipmentType, itemName, itemId, maxHealthBonus, damageBonus, defenceBonus, unitPrice);
        }

        for (int i = equipmentLines.length; i < equipmentLines.length + itemLines.length; i++) {
            String[] itemInfo = itemLines[i - equipmentLines.length].split(";");

            int itemId = Integer.parseInt(itemInfo[0]);
            String itemName = itemInfo[1];
            int unitPrice = Integer.parseInt(itemInfo[2]);
            boolean isStackable = itemInfo[3].equals("1");
            int quantity = Integer.parseInt(itemInfo[4]);

            items[i] = new Item(itemName, itemId, isStackable, quantity, unitPrice);
        }

        initialized = true;
    }

    public static Item getItem(int itemId) {
        initItemList();

        if (itemId < 0 || itemId >= items.length)
            return null;

        Item ret = items[itemId];

        if (items[itemId] instanceof Equipment) {
            Equipment equipment = (Equipment) items[itemId];
            return new Equipment(equipment.getEquipmentType(), equipment.getItemName(), equipment.getItemId(), equipment.getMaxHealthBonus(), equipment.getDamageBonus(), equipment.getDefenceBonus(), equipment.getPrice());
        }
        else
            return new Item(ret);
    }
}
