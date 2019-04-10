package base;

public class Character extends Entity {
	public static int level;
	public static int gold;
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
	@SuppressWarnings("static-access")
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
	


	/**
	 * reutrns sum of attack value and equipped weapons
	 * @param a  the enemy to adjust values for
	 * @return   the damage total
	 */
	public double getDamageTot(Enemy a) {
		int dmgtot = 0;
		dmgtot += this.attack;
		for(int i = 0;i<equippedItems.size();i++) {
			dmgtot += (equippedItems.get(i).type==1) ? (double)equippedItems.get(i).factor/(double)a.getDefense()*(Math.random()+1) : 0;
		}
		return (double)dmgtot;
	}
}