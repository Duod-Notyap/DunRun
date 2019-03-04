package base;
import java.util.ArrayList;
public class Enemy extends Entity {
	
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

	/*
	 * returns sum of current attack value + equipped weapons
	 */
	public double getDamageTot(Character a) {
		int dmgtot = 0;
		dmgtot += this.attack;
		for(int i = 0;i<equippedItems.size();i++) {
			dmgtot += (equippedItems.get(i).type==1) ? (double)equippedItems.get(i).factor/(double)a.defense : 0;
		}
		return (double)dmgtot;
	}
}
