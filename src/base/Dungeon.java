package base;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class Dungeon {
	public int seed;
	public int dunLvl;
	Random randomizer = new Random();
	public int PRDF_GOBLINS = 0;
	public int PRDF_DEMONS = 1;
	public int enemType;
	public String envAdj;
	public String envConds;
	public HashMap<String, Double> LCTNL_DMG_GRID = new HashMap<String, Double>();
	public HashMap<String, Double> LCTNL_HIT_CHANCE_GRID = new HashMap<String, Double>();
	
	public Dungeon(int lvl) {
		this.seed = (int)(randomizer.nextDouble()*500000.0);
		this.dunLvl = lvl;
		this.enemType = randomizer.nextInt(1);	
		defineLCTNL();
	}
	
	public Dungeon(int lvl, int seed) {
		this.seed = seed;
		this.dunLvl = lvl;
		this.enemType = randomizer.nextInt(2);	
		defineLCTNL();
	}
	
	public Dungeon(int lvl, int seed, int enemType) {
		this.seed = seed;
		this.dunLvl = lvl;
		this.enemType = enemType;	
		defineLCTNL(); 
	}
	
	public ArrayList<Enemy> generate() {
		int enemyCount = this.seed%4;
		ArrayList<Enemy> enemies = new ArrayList();
		Enemy demLord = new Enemy("Demon Lord", 250*((double)this.dunLvl/10.0), 80*((double)this.dunLvl/10.0), 35*((double)this.dunLvl/10.0), this.dunLvl, new Item[] {}, new Item[] {});
		Enemy gobKing = new Enemy("Goblin King", 300*((double)this.dunLvl/10.0), 60*((double)this.dunLvl/10.0), 40*((double)this.dunLvl/10.0), this.dunLvl, new Item[] {}, new Item[] {});
		switch(enemType) {
		case 0: enemies.add(gobKing.copy());
				this.envAdj = "Swampy";
				this.envConds = "wet/muddy";
		case 1: enemies.add(demLord.copy());
				this.envAdj = "Hellish";
				this.envConds = "hot/dry";
		}
		
		Enemy demonling = new Enemy("Demonling", 100*((double)this.dunLvl/10.0), 25*((double)this.dunLvl/10.0), 15*((double)this.dunLvl/10.0), this.dunLvl, new Item[] {}, new Item[] {});
		Enemy goblin = new Enemy("Goblin", 150*((double)this.dunLvl/10.0), 20*((double)this.dunLvl/10.0), 15*((double)this.dunLvl/10.0), this.dunLvl, new Item[] {}, new Item[] {});
		
		switch(enemType) {
		case 0: for(int i = 0;i<enemyCount;i++) {
					enemies.add(goblin.copy());
				}
		case 1: for(int i = 0;i<enemyCount;i++) {
					enemies.add(demonling.copy());
				}
		default:
		}
		return enemies;
	}
	
	public void play(Character player) {
		ArrayList<Enemy> enemies = generate();		
		Scanner sc = new Scanner(System.in);
		Enemy enemy;
		while(enemies.size() != 0) {
			System.out.printf("There are %d enemies\n", enemies.size());
			System.out.println("Which would you like to fight?");
			for(int i = 0;i<enemies.size();i++) {
				System.out.printf("%1$d. %2$s, lvl: %3$d\n", i+1, enemies.get(i).name, enemies.get(i).level); 
			}
			String enemPick = sc.next();
			int enemchoice = Integer.parseInt(enemPick);
			enemy = enemies.get(enemchoice-1);
			System.out.println("your statis are "
								+ " Name: " + player.name
								+ " level: " + player.level
								+ " Attack: " + player.attack
								+ " Defense: " + player.defense
								+ " HP: " + player.curhp);
			
			
			System.out.println("You have been challenged by an enemy "
								+ " Name: " + enemy.name
								+ " level: " + enemy.level
								+ " Attack: " + enemy.attack
								+ " Defense: " + enemy.defense
								+ " HP: " + enemy.curhp);
			battle(player, enemy);
		}
	}
	
	public Boolean battle(Character player, Enemy enemy) {
		Scanner sc = new Scanner(System.in);
		String actionSelect;
		int actionChoice;
		player.curhp = player.basehp; //reset health after each battle the enemies are too difficult to not do this.
		for(int i = 0;(player.curhp>0)&&(enemy.curhp>0);i++) {
			System.out.printf("Round %d\n", i);
			System.out.printf("Player HP is at %1$f\n%2$s HP is at %3$f\n", player.curhp, enemy.name, enemy.curhp);
			System.out.println("What would you like to do?\n1.Fight\n2.Items\n3.Retreat");
			actionSelect = sc.next();
			actionChoice = Integer.parseInt(actionSelect);
			switch(actionChoice) {
			case 1: fight(player, enemy);
					break;
			case 2: inventory(player);
					break;
			case 3: retreat(player);
					break;
			default:
			}
		
		}
		return true;
	}
	
	public void fight(Character player, Enemy enemy) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Would you like to go for an aimed shot?(y/n)");
		String aimedShot = sc.next();
		Boolean aimed = (aimedShot.equals("y")) ? true : false;
		HashMap<String, Double> pdmgmult = new HashMap<String, Double>();
		HashMap<String, Double> edmgmult = new HashMap<String, Double>();
		if(aimed) {
			System.out.println("Which body part do you want to aim for?");
			Object firstKey;
			Object valueForKey;
			for(int i = 0;i<this.LCTNL_DMG_GRID.size();i++) {
				firstKey= this.LCTNL_DMG_GRID.keySet().toArray()[i];
				valueForKey = this.LCTNL_DMG_GRID.get(firstKey);
				System.out.printf("%1$d. %2$s\n", i+1, firstKey);
			}
			int bodychoice = sc.nextInt();
			pdmgmult = getHitLCTN((String)this.LCTNL_DMG_GRID.keySet().toArray()[bodychoice-1]);
			edmgmult = getHitLCTN();
		}else {
			pdmgmult = getHitLCTN();
			edmgmult = getHitLCTN();
		}
		System.out.printf("You hit the enemy in the %1$s for %2$f damage!\n", pdmgmult.keySet().toArray()[0], player.getDamageTot(enemy)*pdmgmult.get(pdmgmult.keySet().toArray()[0]));
		enemy.curhp -= player.getDamageTot(enemy)*pdmgmult.get(edmgmult.keySet().toArray()[0]);
		System.out.printf("%1$s hit you in the %3$s for %2$f damage!\n", enemy.name, enemy.getDamageTot(player)*edmgmult.get(edmgmult.keySet().toArray()[0]), edmgmult.keySet().toArray()[0]);
		player.curhp -= enemy.getDamageTot(player)*edmgmult.get(edmgmult.keySet().toArray()[0]);
	}
	
	public HashMap<String, Double> getHitLCTN(String goal_location) {
		HashMap<String, Double> retval =new HashMap<String, Double>();
		HashMap<String, Double> hit_chance_editable = (HashMap<String, Double>) this.LCTNL_HIT_CHANCE_GRID.clone();
		hit_chance_editable.replace(goal_location, this.LCTNL_HIT_CHANCE_GRID.get(goal_location)+1.0/this.LCTNL_DMG_GRID.get(goal_location));
		double sum = 0;
		for(Object i : hit_chance_editable.keySet().toArray()) {
			sum += this.LCTNL_HIT_CHANCE_GRID.get(i);
		}
		double tempsum = 0;
		double hit = new java.util.Random().nextDouble()*sum;
		for(Object i : hit_chance_editable.keySet().toArray()) {
			tempsum += this.LCTNL_HIT_CHANCE_GRID.get(i);
			if(tempsum>hit) {
				retval.put((String)i, this.LCTNL_DMG_GRID.get(i));
			}
		}
		return retval;
	}
	
	public HashMap<String, Double> getHitLCTN() {
		HashMap<String, Double> retval =new HashMap<String, Double>();
		double sum = 0;
		for(Object i : this.LCTNL_HIT_CHANCE_GRID.keySet().toArray()) {
			sum += this.LCTNL_HIT_CHANCE_GRID.get(i);
		}
		double tempsum = 0;
		double hit = new java.util.Random().nextDouble()*sum;
		for(Object i : this.LCTNL_HIT_CHANCE_GRID.keySet().toArray()) {
			tempsum += this.LCTNL_HIT_CHANCE_GRID.get(i);
			if(tempsum>hit) {
				retval.put((String)i, this.LCTNL_DMG_GRID.get(i));
			}
		}
		return retval;
	}
	
	public void inventory(Character player) {
		Scanner sc = new Scanner(System.in);
		System.out.println("What item would you like to use: ");
		int lastNum = 0;
		for(int i = 0;i<player.inventory.size();i++) {
			System.out.printf("%1$d. %2$s\n", i+1, player.inventory.get(i).name);
			lastNum = i;
		}
		System.out.printf("%d. Back\n", lastNum+1);
		int menchoice = sc.nextInt();
		if(lastNum + 1==menchoice) {
		}else{
			player.inventory.get(menchoice-1).use(player);
		}
	}
	
	public void battleGain(Character player){
		//If the player is still alive run this method after battle
	 player.exp += Math.pow(Enemy.level,3);
	 player.gold += Enemy.level;
	}
	public void retreat(Character player) {
		
	}
	
	public void defineLCTNL() {
		this.LCTNL_DMG_GRID.put("Head", 3.00);
		this.LCTNL_DMG_GRID.put("Neck", 2.5);
		this.LCTNL_DMG_GRID.put("Upper Body", 2.00);
		this.LCTNL_DMG_GRID.put("Lower Body", 1.50);
		this.LCTNL_DMG_GRID.put("Upper Leg", 1.00);
		this.LCTNL_DMG_GRID.put("Lower Leg", 0.900);
		this.LCTNL_DMG_GRID.put("Upper Arms", 0.90);
		this.LCTNL_DMG_GRID.put("Lower Arms", 0.80);
		this.LCTNL_DMG_GRID.put("Hand", 0.25);
		this.LCTNL_DMG_GRID.put("Foot", 0.40);
		this.LCTNL_HIT_CHANCE_GRID.put("Head", .1);
		this.LCTNL_HIT_CHANCE_GRID.put("Neck", .2);
		this.LCTNL_HIT_CHANCE_GRID.put("Upper Body", .4);
		this.LCTNL_HIT_CHANCE_GRID.put("Lower Body", .4);
		this.LCTNL_HIT_CHANCE_GRID.put("Upper Leg", .2);
		this.LCTNL_HIT_CHANCE_GRID.put("Lower Leg", .2);
		this.LCTNL_HIT_CHANCE_GRID.put("Upper Arms", .2);
		this.LCTNL_HIT_CHANCE_GRID.put("Lower Arms", .2);
		this.LCTNL_HIT_CHANCE_GRID.put("Hand", .4);
		this.LCTNL_HIT_CHANCE_GRID.put("Foot", .5);
	}
}
 