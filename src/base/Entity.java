package base;

import java.util.ArrayList;
import java.util.HashMap;

public class Entity {
	public double attack;
	public double defense;
	public String name;
	public double basehp;
	public double curhp;
	public static double exp;
	public int level;
	public int mp;
	public ArrayList<Item> inventory =  new ArrayList();
	public ArrayList<Item> equippedItems =  new ArrayList();
	
	public Enemy copy() {
		return new Enemy(this.name, this.curhp, this.attack, this.defense, this.level, conArLtoItemArr(this.inventory), conArLtoItemArr(this.equippedItems));
	}


	public Item[] conArLtoItemArr(ArrayList<Item> a) {
		Item[] retarr = new Item[a.size()];
		for(int i = 0;i<a.size();i++) {
			retarr[i] = a.get(i);
		}
		return retarr;
	}

}
