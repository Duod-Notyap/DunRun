package base;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.lang3.math.NumberUtils;

public class Shop {
	
	public HashMap<Item, Integer> itemsForSale = new HashMap<Item, Integer>();
	public int tier;
	/**
	 * Creates a new Shop
	 */
	public Shop() {
		addItems();
		tier = 1;
	}

	public Shop(int t){
		tier = t;
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
			Util.sleep(100);
		}
		System.out.printf("%1$d.Back\n", itemsForSale.size()+1);
		System.out.println("Which would you like?");
		int choice = getShopInput(itemsForSale.size()+1);
		if(choice == itemsForSale.size()+1) {
			return player.gold;
		}
		if (player.gold > price){
			player.inventory.add((Item)itemsForSale.keySet().toArray()[choice-1]);
			player.gold -= price;
			System.out.printf("You purchased the %s\n", ((Item)(itemsForSale.keySet().toArray()[choice-1])).name);
			itemsForSale.remove(itemsForSale.keySet().toArray()[choice-1]);
		}
		else {
			System.out.println("you have insufficient funds");
		}
		transaction(player, true);
		return player.gold;
	}
	
	/**
	 * completes the transaction lists items checks funds changes balance
	 * @param player    the player
	 * @return          returns players gold after transaction
	 */
	public int transaction(Character player, boolean skip) {
		for(int i = 0; i <= itemsForSale.size()-1; i++) {
			int price = itemsForSale.get(itemsForSale.keySet().toArray()[i]);
			Item item = (Item)itemsForSale.keySet().toArray()[i];
			System.out.printf("%1$d. %2$s, %3$d\n", i+1, item.name, price);
			Util.sleep(100);
		}
		System.out.printf("%1$d.Back\n", itemsForSale.size()+1);
		Util.sleep(1000);
		System.out.printf("Player gold: %s\n", player.gold);
		int price = itemsForSale.get(itemsForSale.keySet().toArray()[0]);
		System.out.println("Which would you like?");
		int choice = getShopInput(itemsForSale.size()+1);
		if(choice == itemsForSale.size()+1) {
			return player.gold;
		}
		if (player.gold > price){
			player.inventory.add((Item)itemsForSale.keySet().toArray()[choice-1]);
			player.gold -= price;
			System.out.printf("You purchased the %1$s\n", ((Item)(itemsForSale.keySet().toArray()[choice-1])).name);
			itemsForSale.remove(itemsForSale.keySet().toArray()[choice-1]);
		}
		else {
			System.out.println("you have insufficient funds");
		}
		transaction(player, true);
		return player.gold;
	}
	
	/**
	 * adds items to the shop
	 */
	public void addItems() {
		int temp = tier;
		Random r = new Random();
		for(int i = 0;i<10;i++) {
			temp = r.nextInt(tier);
			this.itemsForSale.put(new Item((int)(r.nextInt(Item.getNumOfTieredItems(temp)))+Item.getTierPrefix(temp)), (int)(r.nextDouble()*100)*(temp));
		}
	}
	
private int getShopInput(int max) {
		Scanner sc = new Scanner(System.in);
		int inp = 0;
		Timer timer = new Timer();
		TimerTask task = new ShopThread();
		while(true) {
			try {
				System.out.println("You gonna buy something?");
				timer.scheduleAtFixedRate(task, 5000, 3000);
				inp = NumberUtils.toInt(sc.nextLine());
				timer.cancel();
			}catch(InputMismatchException e) {
				e.printStackTrace();
			}
			if(inp <= 0 || inp > max) {
				System.out.println("Please input an available option");
				timer.cancel();
				sc.close();
				continue;
			}
			timer.cancel();
			sc.close();
			return inp;
		}
	}
	

}