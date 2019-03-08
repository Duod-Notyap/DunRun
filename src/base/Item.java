package base;

public class Item {
	public static int HEAL = 0;
	public static int SUPPORT_DMG = 1;
	public static int SUPPORT_DEF = 2;
	public static int PRDF_DAGGER = 300;
	public static int PRDF_SMALLKNIFE = 301;
	public static int PRDF_SWORD = 302;
	public static int PRDF_ROCK = 303;
	public static int PRDF_SMALLHEAL = 304;
	public static int PRDF_LEATHERARMOR = 305;
	public static int PRDF_FIREBALL = 306;
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
		}
	}
	
	public boolean use(Character entity) {
		System.out.println(this.desc);
		System.out.println("Are you sure you want to use this item?(y/n)");
		java.util.Scanner sc = new java.util.Scanner(System.in);
		String response = sc.next();
		sc.close();
		if(response.equals("y")) {
			switch(this.type) {
			case 0: entity.curhp += (entity.curhp+factor>entity.basehp) ? entity.basehp-entity.curhp : this.factor;
					System.out.printf("You used the %1$s and healed for %2$s hp.\n", this.name, this.factor);
			case 1: entity.attack += factor;
					System.out.printf("You used the %1$s and got %2$s extra damage.\n", this.name, this.factor);
			case 2: entity.defense += factor;
					System.out.printf("You used the %1$s and got %2$s extra defense.\n", this.name, this.factor);
			}
			return true;
		}else if(response.equals("n")) { 
			return false;
		}else {
			return false;
		}
	}
	
	public void use(Enemy entity) {
		switch(this.type) {
		case 0: entity.curhp += (entity.curhp+factor>entity.basehp) ? entity.basehp-entity.curhp : this.factor;
				System.out.printf("You used the %1$s and healed for %2$s hp.\n", this.name, this.factor);
		case 1: entity.attack += factor;
				System.out.printf("You used the %1$s and got %2$s extra damage.\n", this.name, this.factor);
		case 2: entity.defense += factor;
				System.out.printf("You used the %1$s and got %2$s extra defense.\n", this.name, this.factor);
		}
		
	}
}

