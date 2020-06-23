package com.example.TextAdventure.Equipment;

import com.example.TextAdventure.Common.Strings;
import com.example.TextAdventure.Equipment.Equipment.EquipmentType;

import static com.example.TextAdventure.UserInterface.Output.output;

public class EquipmentSet {

    private int maxHealthBonus = 0;
    private int damageBonus = 0;
    private int defenceBonus = 0;

    private Equipment armour = null;
    private Equipment sword = null;
    private Equipment shield = null;

    // Initialization
    public EquipmentSet() {
        initEquipmentSet(null, null, null);
    }
    public EquipmentSet(Equipment armour, Equipment sword, Equipment shield) {
        initEquipmentSet(armour, sword, shield);
    }
    public EquipmentSet(EquipmentSet equipmentSet) {
        if (equipmentSet == null)
            initEquipmentSet(null, null, null);
        else
            initEquipmentSet(equipmentSet.armour, equipmentSet.sword, equipmentSet.shield);
    }
    private void initEquipmentSet(Equipment armour, Equipment sword, Equipment shield) {
        equip(armour);
        equip(sword);
        equip(shield);
    }

    public int getMaxHealthBonus() { return maxHealthBonus; }
    public int getDamageBonus() { return damageBonus; }
    public int getDefenceBonus() { return defenceBonus; }

    public Equipment getArmour() { return armour; }
    public Equipment getSword() { return sword; }
    public Equipment getShield() { return shield; }

    // Equip/Unequip Equipment
    public Equipment equip(Equipment equipment) {
        if (equipment == null || equipment.isEquipped())
            return null;

        Equipment oldEquipment = null;

        if (equipment.getEquipmentType() == EquipmentType.ARMOUR) {
            oldEquipment = armour;
            armour = equipment;
        }
        else if (equipment.getEquipmentType() == EquipmentType.SWORD) {
            oldEquipment = sword;
            sword = equipment;
        }
        else if (equipment.getEquipmentType() == EquipmentType.SHIELD) {
            oldEquipment = shield;
            shield = equipment;
        }

        onEquip(equipment);
        onUnequip(oldEquipment);

        return oldEquipment;
    }
    public Equipment unequip(EquipmentType equipmentType) {

        Equipment oldEquipment = null;

        if (equipmentType == EquipmentType.ARMOUR) {
            oldEquipment = armour;
            armour = null;
        }
        else if (equipmentType == EquipmentType.SWORD) {
            oldEquipment = sword;
            sword = null;
        }
        else if (equipmentType == EquipmentType.SHIELD) {
            oldEquipment = shield;
            shield = null;
        }

        onUnequip(oldEquipment);

        return oldEquipment;
    }

    // Add/Remove Equipment Attributes Upon Equip/Unequip
    private void onEquip(Equipment equipment) {
        maxHealthBonus += equipment.getMaxHealthBonus();
        damageBonus += equipment.getDamageBonus();
        defenceBonus += equipment.getDefenceBonus();
        equipment.equip();
    }
    private void onUnequip(Equipment equipment) {
        if (equipment != null) {
            maxHealthBonus -= equipment.getMaxHealthBonus();
            damageBonus -= equipment.getDamageBonus();
            defenceBonus -= equipment.getDefenceBonus();
            equipment.unequip();
        }
    }

    public void viewEquipmentSet() {
        output(Strings.EQUIPMENT_DISPLAY_TITLE);

        output(Strings.EQUIPMENT_DISPLAY_ARMOUR, getArmour() == null ? Strings.EQUIPMENT_DISPLAY_EMPTY : getArmour().getItemName());
        output(Strings.EQUIPMENT_DISPLAY_SWORD, getSword() == null ? Strings.EQUIPMENT_DISPLAY_EMPTY : getSword().getItemName());
        output(Strings.EQUIPMENT_DISPLAY_SHIELD + "\n", getShield() == null ? Strings.EQUIPMENT_DISPLAY_EMPTY : getShield().getItemName());

        output(Strings.EQUIPMENT_DISPLAY_HEALTH_BONUS, getMaxHealthBonus());
        output(Strings.EQUIPMENT_DISPLAY_DAMAGE_BONUS, getDamageBonus());
        output(Strings.EQUIPMENT_DISPLAY_DEFENCE_BONUS, getDefenceBonus());
    }
}
