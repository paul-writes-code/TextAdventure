package com.example.TextAdventure.Trade;

import com.example.TextAdventure.Character.Character;
import com.example.TextAdventure.Character.Merchant;
import com.example.TextAdventure.Character.Player;
import com.example.TextAdventure.Common.Strings;
import com.example.TextAdventure.Item.Item;
import com.example.TextAdventure.UserInterface.Output;

import static com.example.TextAdventure.UserInterface.Output.output;

public class Trade {

    public static void buyItem(Player buyer, Merchant seller, String itemName) {
        Item item = seller.getItem(itemName);

        if (item == null) {
            Output.output(Strings.MERCHANT_NO_ITEM, seller.getName(), itemName);
            return;
        }

        if (!buyer.hasGold(item.getPrice())) {
            Output.output(Strings.INSUFFICIENT_GOLD, itemName);
            return;
        }

        buyer.buyItem(item);
        seller.sellItem(item);

        Output.output(Strings.BOUGHT_FOR_GOLD, itemName, item.getPrice());
    }

    public static void sellItem(Player seller, Merchant buyer, String itemName) {
        Item item = seller.getItem(itemName);

        if (item == null) {
            Output.output(Strings.PLAYER_NO_ITEM, itemName);
            return;
        }

        buyer.buyItem(item);
        seller.sellItem(item);

        Output.output(Strings.SOLD_FOR_GOLD, itemName, item.getPrice());
    }

    public static void viewTrade(Character character, Merchant merchant) {
        output(Strings.TRADE_DISPLAY_TITLE, merchant.getName());

        boolean buyerEmpty = !character.viewTradeBuyer();
        boolean sellerEmpty = !merchant.viewTradeSeller();

        if (buyerEmpty && sellerEmpty)
            output(Strings.TRADE_DISPLAY_EMPTY);
    }
}