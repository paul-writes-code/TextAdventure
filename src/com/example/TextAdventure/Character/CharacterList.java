package com.example.TextAdventure.Character;

import com.example.TextAdventure.Item.Inventory;
import com.example.TextAdventure.Item.ItemList;

public class CharacterList {

    private static boolean initialized = false;
    private static Enemy[] enemies;
    private static Merchant[] merchants;

    private static final String enemyString = "{" +
            "0;goblin;1;25;5;5;1;1;[0,0,0]'" +
            "1;bandit;1;25;6;6;1;1;[4]'" +
            "2;scorpion;3;100;12;12;6;4;[0]" +
            "}";
    private static final String merchantString = "{" +
            "100;merchy;[8,8,8]'" +
            "101;merchant2;[10,9]" +
            "}";

    private static void initCharacterLists() {
        if (initialized)
            return;

        initEnemyList();
        initMerchantList();

        initialized = true;
    }
    private static void initEnemyList() {
        String[] enemyLines = enemyString.substring(1, enemyString.length() - 1) // remove '{' and '}'
                .split("'");

        enemies = new Enemy[enemyLines.length];

        for (int i = 0; i < enemyLines.length; i++) {
            String[] enemyInfo = enemyLines[i].split(";");

            int id = Integer.parseInt(enemyInfo[0]);
            String name = enemyInfo[1];
            int level = Integer.parseInt(enemyInfo[2]);
            int experience = Integer.parseInt(enemyInfo[3]);
            int currentHealth = Integer.parseInt(enemyInfo[4]);
            int maxHealth = Integer.parseInt(enemyInfo[5]);
            int damage = Integer.parseInt(enemyInfo[6]);
            int defence = Integer.parseInt(enemyInfo[7]);

            String[] inventoryString = enemyInfo[8].substring(1, enemyInfo[8].length() - 1).split(",");
            Inventory inventory = new Inventory();

            for (String itemIdString : inventoryString)
                inventory.addItem(ItemList.getItem(Integer.parseInt(itemIdString)));

            enemies[i] = new Enemy(id, name, experience, level, currentHealth, maxHealth, damage, defence, inventory);
        }
    }
    private static void initMerchantList() {
        String[] merchantLines = merchantString.substring(1, merchantString.length() - 1).split("'");
        merchants = new Merchant[merchantLines.length];

        for (int i = 0; i < merchantLines.length; i++) {
            String[] merchantInfo = merchantLines[i].split(";");

            int id = Integer.parseInt(merchantInfo[0]);
            String merchantName = merchantInfo[1];
            String[] inventoryString = merchantInfo[2].substring(1, merchantInfo[2].length() - 1).split(",");
            Inventory inventory = new Inventory();

            for (String itemIdString : inventoryString)
                inventory.addItem(ItemList.getItem(Integer.parseInt(itemIdString)));

            merchants[i] = new Merchant(id, merchantName, inventory);
        }
    }

    public static Enemy getEnemy(int enemyId, int increment) {
        initCharacterLists();

        if (enemyId < 0 || enemyId >= enemies.length)
            return null;

        Enemy enemy = enemies[enemyId];
        return new Enemy(enemyId, enemy.getName() + increment, enemy.getExperience(), enemy.getLevel(), enemy.getCurrentHealth(), enemy.getMaxHealth(), enemy.getDamage(), enemy.getDefence(), enemy.inventory);
    }
    public static Merchant getMerchant(int merchantId) {
        initCharacterLists();

        int offsetId = merchantId - 100;

        if (offsetId < 0 || offsetId >= merchants.length)
            return null;

        return merchants[offsetId];
    }
}
