package com.example.TextAdventure.Trade;

import com.example.TextAdventure.Character.Character;
import com.example.TextAdventure.Character.Merchant;
import com.example.TextAdventure.Item.Item;
import com.example.TextAdventure.UserInterface.Output;

public class Trade {

    public static boolean buyFromMerchant(Character buyer, Merchant merchant, String itemName) {
        Item itemBuying = null;

        for (Item item : merchant.getInventory().getItems())
            if (item.getItemName().equals(itemName))
                itemBuying = item;

        if (itemBuying == null) {
            Output.output(merchant.getName() + " does not have " + itemName + ".");
            return false;
        }

        if (buyer.getInventory().getGold() < itemBuying.getSellPrice()) {
            Output.output("You do not have enough gold to purchase " + itemName + ".");
            return false;
        }

        buyer.getInventory().removeGold(itemBuying.getSellPrice());
        buyer.getInventory().addItem(merchant.getInventory().removeItem(itemBuying));

        Output.output("Bought " + itemName + " for " + itemBuying.getSellPrice() + " gold.");
        return true;
    }
    public static boolean sellToMerchant(Character seller, Merchant merchant, String itemName) {
        Item itemSelling = null;

        for (Item item : seller.getInventory().getItems())
            if (item.getItemName().equals(itemName))
                itemSelling = item;

        if (itemSelling == null) {
            Output.output("You do not have " + itemName + " in your inventory.");
            return false;
        }

        seller.getInventory().addGold(itemSelling.getBuyPrice());
        merchant.getInventory().addItem(seller.getInventory().removeItem(itemSelling));

        Output.output("Sold " + itemName + " for " + itemSelling.getBuyPrice() + " gold.");
        return true;
    }
}