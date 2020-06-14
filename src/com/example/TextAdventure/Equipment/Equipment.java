package com.example.TextAdventure.Equipment;

import com.example.TextAdventure.Item.Item;

public class Equipment extends Item {

    public enum EquipmentType {
        ARMOUR,
        SWORD,
        SHIELD
    }

    private int maxHealthBonus;
    private int damageBonus;
    private int defenceBonus;

    private EquipmentType equipmentType;
    private boolean isEquipped;

    // Initialization
    public Equipment(EquipmentType equipmentType, String itemName, int itemId, int maxHealthBonus, int damageBonus, int defenceBonus) {
        super(itemName, itemId, 25, 25);
        this.maxHealthBonus = maxHealthBonus;
        this.damageBonus = damageBonus;
        this.defenceBonus = defenceBonus;
        this.equipmentType = equipmentType;
        isEquipped = false;
    }

    public int getMaxHealthBonus() { return maxHealthBonus; }
    public int getDamageBonus() { return damageBonus; }
    public int getDefenceBonus() { return defenceBonus; }

    public EquipmentType getEquipmentType() { return equipmentType; }
    public boolean isEquipped(){ return isEquipped; }

    public void equip() { isEquipped = true; }
    public void unequip() { isEquipped = false; }
}
