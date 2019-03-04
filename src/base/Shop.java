package base;

import java.util.ArrayList;
import java.util.HashMap;

public class Shop {
	
	public HashMap<Item, Integer> itemsForSale = new HashMap<Item, Integer>();
	
	
	
	public int checkFunds(Character player, int choice) {
		int price = itemsForSale.get(itemsForSale.keySet().toArray()[choice - 1]);
		Item item = (Item)itemsForSale.keySet().toArray()[choice - 1];
		if (player.gold > price){
			player.inventory.add(item);
			player.gold -= price;
			
		}
		else {
		System.out.println("you have insignificant funds");
		}
		return player.gold;
	}
	
}
