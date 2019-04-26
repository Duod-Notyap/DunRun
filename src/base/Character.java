package base;

public class Character extends Entity {
	public int level;
	public int gold;
	public int type;
	public double damagemlt = 1.0;
	public String pronoun;
	public Special spec = new Special(name, (int)damagemlt) {
		public void use(Character p, Enemy e) {}
		public void revert(Character p, Enemy e) {}
	};
	/**
	 * 
	 * @param n   name
	 * @param hp  base hp
	 * @param att base attack
	 * @param d   base defense
	 * @param lev base level
	 * @param ex  base exp
	 */
	
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
	 * returns sum of attack value and equipped weapons
	 * @param a  the enemy to adjust values for
	 * @return   the damage total
	 */
	public double getDamageTot(Enemy a) {
		int dmgtot = 0;
		for(int i = 0;i<equippedItems.size();i++) {
			dmgtot += (equippedItems.get(i).type==1) ? (1/(double)equippedItems.get(i).factor)*a.defense : 0;
		}
		dmgtot++;
		return Math.round(this.attack+((double)dmgtot*this.damagemlt*(Math.random()+1))*(1+a.defense/this.defense));
	}
	
	public double getAttack(Enemy e) {
		return (this.attack*this.damagemlt)/(e.defense/2);
	}
}