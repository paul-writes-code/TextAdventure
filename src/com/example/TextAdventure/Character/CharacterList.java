package com.example.TextAdventure.Character;

import com.example.TextAdventure.Item.Inventory;
import com.example.TextAdventure.Item.ItemList;

public class CharacterList {

    private static boolean initialized = false;
    private static Enemy[] enemies;

    private static final String enemyString = "{" +
            "0;goblin test ;1;25;5;5;1;1;[0,0,0]'" +
            "1;bandit;1;25;6;6;1;1;[4]'" +
            "2;scorpion;3;100;120;120;6;4;[0]" + // 12
            "}";

    private static void initCharacterLists() {
        if (initialized)
            return;

        initEnemyList();

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
            int currentHealth = Integer.parseInt(enemyInfo[4]);
            int maxHealth = Integer.parseInt(enemyInfo[5]);
            int damage = Integer.parseInt(enemyInfo[6]);

            String[] inventoryString = enemyInfo[8].substring(1, enemyInfo[8].length() - 1).split(",");
            Inventory inventory = new Inventory();

            for (String itemIdString : inventoryString)
                inventory.addItem(ItemList.getItem(Integer.parseInt(itemIdString)));

            enemies[i] = new Enemy(id, name, currentHealth, maxHealth, damage);
        }
    }

    public static Enemy getEnemy(int enemyId, int increment) {
        initCharacterLists();

        if (enemyId < 0 || enemyId >= enemies.length)
            return null;

        Enemy enemy = enemies[enemyId];
        return new Enemy(enemyId, enemy.getDisplayName() + increment, enemy.getHealth(), enemy.getHitpoints(), enemy.getDamage());
    }

}
