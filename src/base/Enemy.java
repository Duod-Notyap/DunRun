package base;

public class Enemy extends Entity {
	
	/**
	 * generate an enemy
	 * @param n           name
	 * @param hp          base hp
	 * @param att         base attack
	 * @param d           base defense
	 * @param lev         starting level
	 * @param items       basic inventory
	 * @param equipItems  equipped items
	 */
	public Enemy(String n, double hp, double att, double d, int lev, Item[] items, Item[] equipItems) {
		this.name = n;
		this.basehp = hp;
		this.curhp = hp;
		this.attack = att;
		this.defense = d;
		this.level = lev;
 		for(Item a : items) {
 			this.inventory.add(a);
		}
		for(Item a : equipItems) {
 			this.equippedItems.add(a);
		}
		
	}

	/**
	 * returns sum of base damage and weapoans in equippedItems
	 * @param a  the character to modify value based on player.getDefense()
	 * @return
	 */
	public double getDamageTot(Character a) {
		int dmgtot = 0;
		dmgtot += this.attack;
		for(int i = 0;i<equippedItems.size();i++) {
			dmgtot += (equippedItems.get(i).type==Item.SUPPORT_DMG) ? (double)equippedItems.get(i).factor/(double)a.getDefense()*(Math.random()+1) : 0;
		}
		return (double)dmgtot;
	}
}
