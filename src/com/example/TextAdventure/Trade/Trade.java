package com.example.TextAdventure.Trade;

import com.example.TextAdventure.Character.Character;
import com.example.TextAdventure.Character.Merchant;
import com.example.TextAdventure.Common.Strings;
import com.example.TextAdventure.Item.Item;
import com.example.TextAdventure.UserInterface.Output;

public class Trade {

    public static boolean buyFromMerchant(Character buyer, Merchant merchant, String itemName) {
        Item itemBuying = null;

        for (Item item : merchant.getInventory().getItems())
            if (item.getItemName().equals(itemName))
                itemBuying = item;

        if (itemBuying == null) {
            Output.output(Strings.MERCHANT_NO_ITEM, merchant.getName(), itemName);
            return false;
        }

        if (buyer.getInventory().getGold() < itemBuying.getSellPrice()) {
            Output.output(Strings.INSUFFICIENT_GOLD, itemName);
            return false;
        }

        buyer.getInventory().removeGold(itemBuying.getSellPrice());
        buyer.getInventory().addItem(merchant.getInventory().removeItem(itemBuying));

        Output.output(Strings.BOUGHT_FOR_GOLD, itemName, itemBuying.getSellPrice());
        return true;
    }
    public static boolean sellToMerchant(Character seller, Merchant merchant, String itemName) {
        Item itemSelling = null;

        for (Item item : seller.getInventory().getItems())
            if (item.getItemName().equals(itemName))
                itemSelling = item;

        if (itemSelling == null) {
            Output.output(Strings.PLAYER_NO_ITEM, itemName);
            return false;
        }

        seller.getInventory().addGold(itemSelling.getBuyPrice());
        merchant.getInventory().addItem(seller.getInventory().removeItem(itemSelling));

        Output.output(Strings.SOLD_FOR_GOLD, itemName, itemSelling.getBuyPrice());
        return true;
    }
}