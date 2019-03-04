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
	public int type;
	public int factor;
	public String name;
	
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
	 * Create an item with predefined stats for easier setup
	 * @param type  your input as params will be Item.PRDF_<insertitemhere>
	 */
	public Item(int type) {
		switch(type) {
		case 300: this.name = "Dagger";
				  this.type = 1;
				  this.factor = 7;
		case 301: this.name = "Small Knife";
				  this.type = 1;
				  this.factor = 5;
		case 302: this.name = "Sword";
		  		  this.type = 1;
		  	      this.factor = 10;
		case 303: this.name = "Rock";
		  		  this.type = 1;
		  		  this.factor = 2;
		case 304: this.name = "Small Healing Potion";
				  this.type = 0;
				  this.factor = 20;
		case 305: this.name = "Leather Armor";
				  this.type = 2;
				  this.factor = 10;
		}
	}
	
	public void use(Character entity) {
		switch(this.type) {
		case 0: entity.curhp += (entity.curhp+factor>entity.basehp) ? entity.basehp-entity.curhp : this.factor;
				System.out.printf("You used the %1$s and healed for %2$s hp.\n", this.name, this.factor);
		case 1: entity.attack += factor;
				System.out.printf("You used the %1$s and got %2$s extra damage.\n", this.name, this.factor);
		case 2: entity.defense += factor;
				System.out.printf("You used the %1$s and got %2$s extra defense.\n", this.name, this.factor);
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

