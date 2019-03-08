package base;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.lang.NumberFormatException;

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
				break;
		case 1: enemies.add(demLord.copy());
				this.envAdj = "Hellish";
				this.envConds = "hot/dry";
				break;
		}
		
		Enemy demonling = new Enemy("Demonling", 100*((double)this.dunLvl/10.0), 25*((double)this.dunLvl/10.0), 15*((double)this.dunLvl/10.0), this.dunLvl, new Item[] {}, new Item[] {});
		Enemy goblin = new Enemy("Goblin", 150*((double)this.dunLvl/10.0), 20*((double)this.dunLvl/10.0), 15*((double)this.dunLvl/10.0), this.dunLvl, new Item[] {}, new Item[] {});
		
		switch(enemType) {
		case 0: for(int i = 0;i<enemyCount;i++) {
					enemies.add(goblin.copy());
				}
				break;
		case 1: for(int i = 0;i<enemyCount;i++) {
					enemies.add(demonling.copy());
				}
				break;
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
			String enemPick = "";
			boolean selectedSyntax = false;
			int enemchoice = 0;
			while(selectedSyntax != true) {
				try {
					enemPick = sc.next();
					selectedSyntax = true;
					enemchoice = Integer.parseInt(enemPick);
				}catch(NumberFormatException e) {
					System.out.println("Bad format try again");
					e.printStackTrace();
				}catch(NoSuchElementException e) {
					System.out.println("Bad format try again");
					e.printStackTrace();
				}
			}
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
		sc.close();
	}
	
	public Boolean battle(Character player, Enemy enemy) {
		Scanner sc = new Scanner(System.in);
		String actionSelect = "";
		int actionChoice;
		player.curhp = player.basehp; //reset health after each battle the enemies are too difficult to not do this.
		for(int i = 0;(player.curhp>0)||(enemy.curhp>0);i++) {
			System.out.printf("Round %d\n", i);
			System.out.printf("Player HP is at %1$f\n%2$s HP is at %3$f\n", player.curhp, enemy.name, enemy.curhp);
			System.out.println("What would you like to do?\n1.Fight\n2.Items\n3.Retreat");
			actionSelect = (sc.hasNextInt()) ? sc.next() : actionSelect;
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
		endBattle(player,enemy);
		sc.close();
		return true;
	}
	public void fight2 (Character player, Enemy enemy) {
		double edmgdiff = player.getDamageTot(enemy);
		double pdmgdiff = enemy.getDamageTot(player);
		player.curhp -= pdmgdiff;
		System.out.println("you gave enemy " + edmgdiff + " Damage");
		enemy.curhp -= edmgdiff;
		System.out.println(pdmgdiff);
	}
	
	public void fight(Character player, Enemy enemy) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Would you like to go for an aimed shot?(y/n)");
		String aimedShot = "";
		try {
			aimedShot = sc.next();
		} catch( NoSuchElementException e ) {
			e.printStackTrace();
		}
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
		//by far the ugliest code here
		System.out.println(""+(String)edmgmult.keySet().toArray()[0]+" "+edmgmult.get((String)edmgmult.keySet().toArray()[0]));
		double edmgdiff = player.getDamageTot(enemy)*pdmgmult.get(pdmgmult.keySet().toArray()[0]);
		double pdmgdiff = enemy.getDamageTot(player)*edmgmult.get((String)edmgmult.keySet().toArray()[0])*player.body_status.get((String)edmgmult.keySet().toArray()[0]);
		System.out.printf("You hit the enemy in the %1$s for %2$f damage!\n", pdmgmult.keySet().toArray()[0], edmgdiff);
		//enemy hp drop (player att base + any weapon bonus)/enemy defense*lctnl mult
		enemy.curhp -= edmgdiff;
		System.out.printf("%1$s hit you in the %2$s for %3$f damage!\n", enemy.name, edmgmult.keySet().toArray()[0], pdmgdiff);
		//enemy hp drop (enemy att base + any weapon bonus)/your defense*lctnl mult
		player.curhp -= pdmgdiff;
		//update body status with the current value + this edmgmult/10
		player.body_status.replace((String)edmgmult.keySet().toArray()[0], player.body_status.get(edmgmult.keySet().toArray()[0])+(edmgmult.get(edmgmult.keySet().toArray()[0]))/10);
		sc.close();
	}
	
	public HashMap<String, Double> getHitLCTN(String goal_location) {
	//Gets hit location of body parts.
		HashMap<String, Double> retval =new HashMap<String, Double>();
		HashMap<String, Double> hit_chance_editable = (HashMap<String, Double>) this.LCTNL_HIT_CHANCE_GRID.clone();
		hit_chance_editable.replace(goal_location, this.LCTNL_HIT_CHANCE_GRID.get(goal_location)+0.4);
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
		HashMap<String, Double> retval = new HashMap<String, Double>();
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
		boolean used = false;
		while(used != true) {
			System.out.println("What item would you like to use: ");
			int lastNum = 0;
			for(int i = 0;i<player.inventory.size();i++) {
				System.out.printf("%1$d. %2$s\n", i+1, player.inventory.get(i).name);
				lastNum = i;
			}
			System.out.printf("%d. Back\n", lastNum+1);
			int menchoice = sc.nextInt();
			if(lastNum + 1==menchoice) {
				used = true;
			}else{
				used = player.inventory.get(menchoice-1).use(player);
			}
		}
		sc.close();
	}
	
	public void battleGain(Character player, Enemy enemy){
		//If the player is still alive run this method after battle
	 player.exp += Math.pow(enemy.level,3);
	 player.gold += enemy.level;
	}

	public void endBattle(Character player, Enemy enemy){
		//updates gain at the end of a battle and determine 
		if ((player.curhp > 0) && (enemy.curhp <= 0)){
			player.checkEveryLevel(); 
			battleGain(player, enemy);
		}
		else if((player.curhp <= 0) && (enemy.curhp >= 0)){
			System.out.println("You have died");
		}
	}


	public void retreat(Character player) {
		
	}
	
	public void defineLCTNL() {
		//Body part set up for aimed specials
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
 