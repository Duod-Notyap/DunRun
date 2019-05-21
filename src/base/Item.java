package base;

import java.util.Random;

public class Item {
	public static final int NUM_OF_TIERS = 2;
	/*
	 * tier 1 items start at 300
	 * 2 at 400
	 * 3 at 500 so on
	 */
	//Tier 2
	public static int HEAL = 0;
	public static int SUPPORT_DMG = 1;
	public static int SUPPORT_DEF = 2;
	public static int DRUGS = 3;
	public static int PRDF_DAGGER = 300;
	public static int PRDF_SMALLKNIFE = 301;
	public static int PRDF_SWORD = 302;
	public static int PRDF_ROCK = 303;
	public static int PRDF_SMALLHEAL = 304;
	public static int PRDF_LEATHERARMOR = 305;
	public static int PRDF_FIREBALL = 306;
	public static int PRDF_SMALLAXE = 307;
	public static int PRDF_BATTLEAXE = 308;
	public static int PRDF_SNOODLESTICK = 309;
	public static int PRDF_SLING = 310;
	public static int PRDF_IRONARMOR = 311;
	public static int PRDF_CAVEDUST = 312;
	public static int NUM_OF_TIER_1_PDRF_ITEMS = 12;
	//Tier 2
	public static int PRDF_LARGEHEAL = 400;
	public static int PRDF_BIGSWORD = 401;
	public static int NUM_OF_TIER_2_PDRF_ITEMS = 2;
	
	public int type;
	public int factor;
	public String name;
	public String desc;
	
	/**
	 * create a new non PRDF Item 
	 * @param name    name of item
	 * @param type    whether it is a weapon, heal, armor, or defense or damage boost
	 * @param factor  the amount it affects the value
	 */
	public Item(String name, int type, int factor){
		this.name = name;
		this.type = type;
		this.factor = factor;
	}
	
	/**
	 * create a new non PRDF Item 
	 * @param name    name of item
	 * @param type    whether it is a weapon, heal, armor, or defense or damage boost
	 * @param factor  the amount it affects the value
	 * @param desc    description of the object
	 */
	public Item(String name, int type, int factor, String desc){
		this.name = name;
		this.type = type;
		this.factor = factor;
		this.desc = desc;
	}
	
	/**
	 * Create an item with predefined stats for easier setup
	 * @param type  your input as params will be Item.PRDF_<insertitemhere>
	 */
	public Item(int type) {
		defType(type);
	}
	
	/**
	 * makes a random item frmo PRDF items
	 * @param player  just a signifier for it to know it's random
	 */
	public Item(Character player) {
		Random r = new Random();
		defType(r.nextInt(7)+300);
	}

	/**
	 * used to create PRDF items
	 * @param type the item id
	 */
	public void defType(int type) {
		switch(type) {
		case 300: this.name = "Dagger";
				  this.type = 1;
				  this.factor = 7;
				  break;
		case 301: this.name = "Small Knife";
				  this.type = 1;
				  this.factor = 5;
				  break;
		case 302: this.name = "Sword";
		  		  this.type = 1;
		  	      this.factor = 10;
				  break;
		case 303: this.name = "Rock";
		  		  this.type = 1;
		  		  this.factor = 2;
				  break;
		case 304: this.name = "Small Healing Potion";
				  this.type = 0;
				  this.factor = 20;
				  this.desc = "A small healing potion to raise your current health 20 points";
				  break;
		case 305: this.name = "Leather Armor";
				  this.type = 2;
				  this.factor = 10;
				  break;
		case 306: this.name = "Fireball";
				  this.type = 1;
				  this.factor = 10;
				  break;
		case 307: this.name = "Small Axe";
				  this.type = 1;
				  this.factor = 9;
				  break;
		case 308: this.name = "Battle Axe";
				  this.type = 1;
				  this.factor = 16;
				  break;
		case 309: this.name = "Snoodle Stick";
				  this.type = 1;
				  this.factor = 13;
				  break;		
		case 310: this.name = "Sling";
				  this.type = 1;
				  this.factor = 12;
				  break;  
		case 311: this.name = "Iron Armor";
				  this.type = 0;
				  this.factor = 15;
				  break;
		case 312: this.name = "Cave Dust";
				  this.desc = "(drugs make everything fun)";
				  this.type = 3;
				  this.factor = 15;
				  break;
		case 400: this.name = "Big Sword";
				  this.type = 1;
				  this.factor = 16;
				  break;
		case 401: this.name = "Large Heal";
				  this.type = 0;
				  this.factor = 35;
				  break;
		case 402: this.name = "Elven Dagger";
				  this.type = 1;
				  this.factor = 12;
		}
	
	}
	/**
	 * function to use the item
	 * @param entity   the player
	 * @return
	 */
	public boolean use(Character entity, Enemy e) {
		System.out.println(this.desc);
		System.out.printf("Are you sure you want to use the %1$s?(y/n)\n", this.name);
		String response = Util.getInput();;
		if(response.equals("y")) {
			switch(this.type) {
			case 0: entity.curhp += (entity.curhp+factor>entity.basehp) ? entity.basehp-entity.curhp : this.factor;
					System.out.printf("You used the %1$s and healed for %2$s hp.\n", this.name, this.factor);
					entity.inventory.remove(this);
					break;
			case 1: entity.attack += factor;
					if(!checkSlotFilled(entity)) {return false;}
					System.out.printf("You equipped the %1$s and got %2$s extra damage.\n", this.name, this.factor);
					entity.equippedItems.add(this);
					entity.inventory.remove(this);
					break;
			case 2: entity.defense += factor;
					if(!checkSlotFilled(entity)) {return false;}
					System.out.printf("You equipped the %1$s and got %2$s extra defense.\n", this.name, this.factor);
					entity.equippedItems.add(this);
					entity.inventory.remove(this);
					break;
			case 3: drug(entity, e);
					entity.inventory.remove(this);
					break;
			}
			return true;
		}else if(response.equals("n")) { 
			return false;
		}else {
			return false;
		}
	}
	
	private void drug(Character p, Enemy e) {
		if(this.name.equals("Cave Dust")) {
			caveDust(p, e);
		}
	}
	private void caveDust(Character p, Enemy e) {
		Special caveDust = new Special("Cave Dust", 0) {
			public void use(Character p, Enemy e) {
				p.damagemlt *= 2.0;
				System.out.println("Your damage is doubled!");
				timer = -3;
			}
			public void revert(Character p, Enemy e) {
				System.out.println("Cave Dust wore off!");
				p.curhp -= 7;
				p.damagemlt = 1.0;
			}
		};
		caveDust.use(p, e);
	}
	/**
	 * the Enemy class needed its own item method for ai(not implemented)
	 * @param entity    The target enemy
	 */
	public void use(Enemy entity) {
		switch(this.type) {
		case 0: entity.curhp += (entity.curhp+factor>entity.basehp) ? entity.basehp-entity.curhp : this.factor;
				System.out.printf("You used the %1$s and healed for %2$s hp.\n", this.name, this.factor);
				break;
		case 1: entity.attack += factor;
				System.out.printf("You used the %1$s and got %2$s extra damage.\n", this.name, this.factor);
				break;
		case 2: entity.defense += factor;
				System.out.printf("You used the %1$s and got %2$s extra defense.\n", this.name, this.factor);
				break;

		}
		
	}
	
	/**
	 * checks if the player has an item of this type equipped
	 * @param entity the player/entity to check
	 * @return
	 */
	public boolean checkSlotFilled(Character entity) {
		for(Item i : entity.equippedItems) {
			if(i.type == this.type) {
				System.out.printf("You already have %1$s equipped!\n", (this.type==1)?"a weapon":"armor");
				return true;
			}
		}
		return false;
	}
	
	public static int getNumOfTieredItems(int t) {
		switch(t) {
		case 1: return NUM_OF_TIER_1_PDRF_ITEMS;
		case 2: return NUM_OF_TIER_2_PDRF_ITEMS;
		}
		return 1;
	}
	
	public static int getTierPrefix(int t) {
		switch(t) {
		case 1: return 300;
		case 2: return 400;
		}
		return 0;
	}
}

