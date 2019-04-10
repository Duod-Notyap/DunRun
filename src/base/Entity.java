package base;

import java.util.ArrayList;

public class Entity {
	public double attack;
	public double defense;
	public String name;
	public double basehp;
	public double curhp;
	public static double exp;
	public int level;
	public int mp;
	public ArrayList<Item> inventory =  new ArrayList<Item>();
	public ArrayList<Item> equippedItems =  new ArrayList<Item>();
	
	public Enemy copy() {
		return new Enemy(this.name, this.curhp, this.attack, this.defense, this.level, conArLtoItemArr(this.inventory), conArLtoItemArr(this.equippedItems));
	}

	/**
	 * converts an ArrayList<Item> to an Item[]
	 * @param a   the arraylist to convert
	 * @return    the Item[]
	 */
	public Item[] conArLtoItemArr(ArrayList<Item> a) {
		Item[] retarr = new Item[a.size()];
		for(int i = 0;i<a.size();i++) {
			retarr[i] = a.get(i);
		}
		return retarr;
	}
	
	/**
	 * returns total defense so base def + armor.factor in equippedItems
	 * @return
	 */
	public double getDefense() {
		int tot = 0;
		for(Item i : this.equippedItems) {
			tot += (i.type == Item.SUPPORT_DEF) ? i.factor : 0;
		}
		return this.defense + (double)tot;
	}

}
