package base;
import java.util.ArrayList;
import java.util.HashMap;

public class Character extends Entity {
	/**
	 * 
	 * @param n   name
	 * @param hp  base hp
	 * @param att base attack
	 * @param d   base defense
	 * @param lev base level
	 * @param ex  base exp
	 * @param w   
	 * @param mp
	 */
	public static int level;
	public static int gold;
	public Character(String n, double hp, double att, double d, int lev, double ex, Item[] i, Item[] ei) {
	
		this.name = n;
		
		this.basehp = hp;
		this.curhp = hp;
		
		this.defense = d;
		
		this.attack = att;
		
		this.level = lev;
		
		for(Item a : i){this.inventory.add(a);}
		for(Item a : ei){this.equippedItems.add(a);}
		
	}
	


	/*
	 * returns sum of current attack value + equipped weapons
	 */
	public double getDamageTot(Enemy a) {
		int dmgtot = 0;
		dmgtot += this.attack;
		for(int i = 0;i<equippedItems.size();i++) {
			dmgtot += (equippedItems.get(i).type==1) ? (double)equippedItems.get(i).factor/(double)a.defense : 0;
		}
		return (double)dmgtot;
	}
	
	public static int CheckLevel(int a, int b) {
		//checks the amount of experience and then levels up accordingly
		if (exp == a) {
			level = b;
		}
		else {
			level = level;
		}
		return level;	
			
	}


	public static void checkEveryLevel(){
		//runs through a loop to determine the characters level
		
		for (int i = 2; i > 100;i++) {
		CheckLevel(3*i,i);
		}
	}
}