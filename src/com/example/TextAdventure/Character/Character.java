package com.example.TextAdventure.Character;

import com.example.TextAdventure.Combat.Combat;
import com.example.TextAdventure.Common.Strings;
import com.example.TextAdventure.Common.Utility;
import com.example.TextAdventure.Equipment.Equipment;
import com.example.TextAdventure.Equipment.EquipmentSet;
import com.example.TextAdventure.Item.Inventory;
import com.example.TextAdventure.Item.Item;

import static com.example.TextAdventure.UserInterface.Output.output;

public abstract class Character {

    protected static final int CHARACTER_BASE_HEALTH = 1;
    protected static final int CHARACTER_BASE_DAMAGE = 1;
    protected static final int CHARACTER_BASE_DEFENCE = 1;

    protected int id;
    protected String name;
    protected int experience;
    protected int level;
    protected int currentHealth;
    protected int maxHealth;
    protected int damage;
    protected int defence;

    protected Character target;
    protected Inventory inventory;
    protected EquipmentSet equipmentSet;

    public Character(int id, String name) {
        initCharacter(id, name, 0, 1, CHARACTER_BASE_HEALTH, CHARACTER_BASE_HEALTH, CHARACTER_BASE_DAMAGE, CHARACTER_BASE_DEFENCE, null, null);
    }
    public Character(int id, String name, Inventory inventory) {
        initCharacter(id, name, 0, 1, CHARACTER_BASE_HEALTH, CHARACTER_BASE_HEALTH, CHARACTER_BASE_DAMAGE, CHARACTER_BASE_DEFENCE, inventory, null);
    }
    public Character(int id, String name, int experience, int level, int currentHealth, int maxHealth, int damage, int defence, Inventory inventory, EquipmentSet equipmentSet) {
        initCharacter(id, name, experience, level, currentHealth, maxHealth, damage, defence, inventory, equipmentSet);
    }
    private void initCharacter(int id, String name, int experience, int level, int currentHealth, int maxHealth, int damage, int defence, Inventory inventory, EquipmentSet equipmentSet) {
        this.id = id;
        this.name = name;
        this.experience = experience;
        this.level = level;
        this.currentHealth = currentHealth;
        this.maxHealth = maxHealth;
        this.damage = damage;
        this.defence = defence;

        this.inventory = new Inventory(inventory);
        this.equipmentSet = new EquipmentSet(equipmentSet);
        target = null;
    }

    public String getName() { return name; }
    public int getExperience() { return experience; }
    public int getLevel() { return level; }
    public int getCurrentHealth() { return currentHealth; }
    public int getMaxHealth() { return maxHealth + equipmentSet.getMaxHealthBonus(); }
    public int getDamage() { return damage + equipmentSet.getDamageBonus(); }
    public int getDefence() { return defence + equipmentSet.getDefenceBonus(); }

    public Character getTarget() { return target; }

    public abstract boolean canBeAttacked();
    public abstract boolean canBeLooted();
    public abstract boolean canBeTraded();

    // COMBAT MANAGEMENT
    public void refresh() { currentHealth = getMaxHealth(); }
    public boolean isAlive() { return currentHealth > 0; }
    public void setTarget(Character target) { this.target = target; }
    public void clearTarget() { this.target = null; }
    public abstract int attackTarget();
    public int takeDamage(int damage) {
        int actualDamage = Math.min(damage, currentHealth);

        currentHealth -= actualDamage;

        if (currentHealth == 0)
            die();

        return actualDamage;
    }
    public boolean consumeHealthPotion() {
        boolean ret = inventory.consumeHealthPotion();

        if (ret)
            refresh();

        return ret;
    }
    public void die() { clearTarget(); }

    // INVENTORY MANAGEMENT
    public String inventoryToString() {
        return inventory.toString();
    }
    public void lootCharacter(Character lootee) { Combat.loot(this, lootee); }
    public abstract Inventory beLooted();
    public Inventory emptyInventory() {
        Inventory ret = inventory;
        inventory = new Inventory();
        return ret;
    }
    public void addInventory(Inventory inventory) { this.inventory.addInventory(inventory); }
    public boolean isInventoryEmpty() { return inventory.isEmpty(); }

    public void addGold(int gold) {
        inventory.addGold(gold);
    }
    public int getNumHealthPotions() { return inventory.getNumHealthPotions(); }
    public boolean hasGold(int amount) {
        return inventory.getGold() >= amount;
    }
    public void addItem(Item item) {
        if (item != null)
            inventory.addItem(item);
    }
    public Item getItem(String itemName) {
        for (Item item : inventory.getItems())
            if (item.getItemName().equals(itemName))
                return item;

        return null;
    }
    public void buyItem(Item item) {
        if (item == null)
            return;

        inventory.removeGold(item.getPrice());
        inventory.addItem(item);
    }
    public void sellItem(Item item) {
        if (item == null)
            return;

        inventory.addGold(item.getPrice());
        inventory.removeItem(item);
    }

    // EQUIPMENT MANAGEMENT
    public void equip(Equipment equipment) {
        int oldMaxHealth = getMaxHealth();
        inventory.addItem(equipmentSet.equip(equipment));

        currentHealth += getMaxHealth() - oldMaxHealth;
    }
    public void unequip(Equipment.EquipmentType equipmentType) {
        int oldMaxHealth = getMaxHealth();
        inventory.addItem(equipmentSet.unequip(equipmentType));

        currentHealth += getMaxHealth() - oldMaxHealth;
    }
    public boolean equipFromInventory(String itemName) {
        for (Item item : inventory.getItems())
            if (item.getItemName().equals(itemName)) {
                if (item instanceof Equipment) {
                    equip((Equipment) item);
                    inventory.getItems().remove(item);
                    return true;
                } else
                    return false;
            }

        return false;
    }
    public boolean unequipFromEquipmentSet(String itemName) {
        if (equipmentSet.getArmour() != null && equipmentSet.getArmour().getItemName().equals(itemName)) {
            unequip(Equipment.EquipmentType.ARMOUR);
            return true;
        }
        else if (equipmentSet.getSword() != null && equipmentSet.getSword().getItemName().equals(itemName)) {
            unequip(Equipment.EquipmentType.SWORD);
            return true;
        }
        else if (equipmentSet.getShield() != null && equipmentSet.getShield().getItemName().equals(itemName)) {
            unequip(Equipment.EquipmentType.SHIELD);
            return true;
        }
        else
            return false;
    }

    // DISPLAY FUNCTIONS
    public void viewCharacter() {
        output(Strings.CHARACTER_DISPLAY_TITLE);
        output(Strings.CHARACTER_DISPLAY_NAME, getName());
        output(Strings.CHARACTER_DISPLAY_HEALTH, getCurrentHealth(), getMaxHealth());
        output(Strings.CHARACTER_DISPLAY_LEVEL, getLevel());
        output(Strings.CHARACTER_DISPLAY_DAMAGE, getDamage());
        output(Strings.CHARACTER_DISPLAY_DEFENCE, getDefence());

        if (getLevel() == 5)
            output(Strings.CHARACTER_DISPLAY_EXPERIENCE_MAX_LEVEL, getExperience());
        else
            output(Strings.CHARACTER_DISPLAY_EXPERIENCE, getExperience(), Utility.getExperienceForLevel(getLevel() + 1));
    }
    public void viewInventory() {
        inventory.viewInventory();
    }
    public void viewEquipmentSet() {
        equipmentSet.viewEquipmentSet();
    }
    public boolean viewTradeBuyer() {
        output(Strings.TRADE_DISPLAY_GOLD, inventory.getGold());

        for (Item item : inventory.getItems())
            output(Strings.TRADE_DISPLAY_OBJECT_SELL, item.getItemName(), item.getPrice());

        return inventory.getItems().size() > 0;
    }
    public boolean viewTradeSeller() {

        for (Item item : inventory.getItems())
            output(Strings.TRADE_DISPLAY_OBJECT_BUY, item.getItemName(), item.getPrice());

        return inventory.getItems().size() > 0;
    }
}
