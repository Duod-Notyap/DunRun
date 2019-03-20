package base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Shop {
	
	public HashMap<Item, Integer> itemsForSale = new HashMap<Item, Integer>();
	
	public Shop() {
		this.itemsForSale.put(new Item(Item.PRDF_ROCK), 10);
		
	}
	
	
	public int transaction(Character player) {
		int price = itemsForSale.get(itemsForSale.keySet().toArray()[0]);
		Item item = (Item)itemsForSale.keySet().toArray()[0];
		System.out.println("Items for sale: " );
		for(int i = 0; i <= itemsForSale.size()-1; i++) {
			price = itemsForSale.get(itemsForSale.keySet().toArray()[i]);
			item = (Item)itemsForSale.keySet().toArray()[i];
			System.out.printf("%1$d. %2$s, %3$d\n", i+1, item.name, price);
			
		}
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Which would you like?");
		int choice = sc.nextInt();
		if (player.gold > price){
			player.inventory.add((Item)itemsForSale.keySet().toArray()[choice-1]);
			player.gold -= price;
			
		}
		else {
			System.out.println("you have insufficient funds");
		}
		return player.gold;
	}
	
}
