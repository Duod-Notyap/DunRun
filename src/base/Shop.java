package base;

import java.util.HashMap;

public class Shop {
	
	public HashMap<Item, Integer> itemsForSale = new HashMap<Item, Integer>();
	
	/**
	 * Creates a new Shop
	 */
	public Shop() {
		addItems();
	}
	
	/**
	 * completes the transaction lists items checks funds changes balance
	 * @param player    the player
	 * @return          returns players gold after transaction
	 */
	public int transaction(Character player) {
		System.out.println("you walk into a shop");
		//door sound effect
		System.out.println("[shop Keeper] What Can I Get Fur Ya?");
		System.out.println("Players Gold:" + player.gold);
		int price = itemsForSale.get(itemsForSale.keySet().toArray()[0]);
		Item item = (Item)itemsForSale.keySet().toArray()[0];
		
		System.out.println("Items for sale: " );
		for(int i = 0; i <= itemsForSale.size()-1; i++) {
			price = itemsForSale.get(itemsForSale.keySet().toArray()[i]);
			item = (Item)itemsForSale.keySet().toArray()[i];
			System.out.printf("%1$d. %2$s, %3$d\n", i+1, item.name, price);
			
		}
		System.out.printf("%1$d.Back\n", itemsForSale.size()+1);
		System.out.println("Which would you like?");
		int choice = Util.getInputasInt(itemsForSale.size()+1);
		if(choice == itemsForSale.size()+1) {
			return player.gold;
		}
		if (player.gold > price){
			player.inventory.add((Item)itemsForSale.keySet().toArray()[choice-1]);
			player.gold -= price;
			
		}
		else {
			System.out.println("you have insufficient funds");
		}
		transaction(player);
		return player.gold;
	}
	
	/**
	 * adds items to the shop
	 */
	public void addItems() {
		this.itemsForSale.put(new Item(Item.PRDF_ROCK), 3);
		this.itemsForSale.put(new Item(Item.PRDF_SNOODLESTICK), 20);
		this.itemsForSale.put(new Item(Item.PRDF_SMALLAXE), 10);
		this.itemsForSale.put(new Item(Item.PRDF_DAGGER), 8);
		this.itemsForSale.put(new Item(Item.PRDF_LEATHERARMOR), 15);
		this.itemsForSale.put(new Item(Item.PRDF_BATTLEAXE), 25 );
		this.itemsForSale.put(new Item(Item.PRDF_CAVEDUST), 7 );
	}
	
}
