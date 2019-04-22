package base;
import java.util.Random;
import java.util.ArrayList;


public class Dungeon {

	public int seed;
	public int dunLvl;
	Random randomizer = new Random();
	public int PRDF_GOBLINS = 0;
	public int PRDF_DEMONS = 1;
	public int PRDF_SNOODLE = 1;
	public int enemType;
	public String envAdj;
	public String envConds;
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	boolean bossenabled = false;
	public int CLOCK = 0;
	
	/**
	 * 
	 * @param lvl the level of the dungeon you want
	 */
	public Dungeon(int lvl) {
		this.seed = (int)(randomizer.nextDouble()*500000.0);
		this.dunLvl = lvl;
		this.enemType = randomizer.nextInt(3);		
		this.bossenabled = true;
	}
	
	/**
	 * 
	 * @param lvl    the level of the dungeon you want
	 * @param seed   the seed you want to generate a specific dungeon
	 */
	public Dungeon(int lvl, int seed) {
		this.seed = seed;
		this.dunLvl = lvl;
		this.enemType = randomizer.nextInt(3);		
		this.bossenabled = true;
	}
	
	/**
	 * 
	 * @param lvl      the level of the dungeon you want
	 * @param seed     the seed you want to generate a specific dungeon
	 * @param enemType if you want to specify an enemy type PRDF_GOBLINS/DEMONS/SNOODLE
	 */
	public Dungeon(int lvl, int seed, int enemType) {
		this.seed = seed;
		this.dunLvl = lvl;
		this.enemType = enemType;	
		this.bossenabled = true;
	}
	
	/**
	 * 
	 * @param lvl      the level of the dungeon you want
	 * @param seed     the seed you want to generate a specific dungeon
	 * @param enemType if you want to specify an enemy type PRDF_GOBLINS/DEMONS/SNOODLE
	 * @param boss     boss is enabled (default=true)
	 */
	public Dungeon(int lvl, int seed, int enemType, boolean boss) {
		this.seed = seed;
		this.dunLvl = lvl;
		this.enemType = enemType;	
		this.bossenabled = boss;
	}
	
	/**
	 * generates the required variables and objects for the dungeon
	 * @return  the enemy arraylist
	 */
	public ArrayList<Enemy> generate() {
		int enemyCount = this.seed%4+1;
		Enemy demLord = new Enemy("Demon Lord", 250*((double)this.dunLvl/10.0), 80*((double)this.dunLvl/10.0), 35*((double)this.dunLvl/10.0), this.dunLvl, new Item[] {}, new Item[] {});
		Enemy gobKing = new Enemy("Goblin King", 300*((double)this.dunLvl/10.0), 60*((double)this.dunLvl/10.0), 40*((double)this.dunLvl/10.0), this.dunLvl, new Item[] {}, new Item[] {});
		Enemy larSnoodle = new Enemy("The Large And Extra Snoodly Snoodle", 300*((double)this.dunLvl/10.0), 45*((double)this.dunLvl/10.0), 40*((double)this.dunLvl/10.0), this.dunLvl, new Item[] {}, new Item[] {});
		if(this.bossenabled) {
			switch(enemType) {
			case 0: this.enemies.add(gobKing);
					this.envAdj = "Swampy";
					this.envConds = "wet/muddy";
					break;
			case 1: this.enemies.add(demLord);
					this.envAdj = "Hellish";
					this.envConds = "hot/dry";
					break;

			case 2: this.enemies.add(larSnoodle);
					this.envAdj = "Rocky";
					this.envConds = "hot/dry";
					break;
			}
		}
		Enemy demonling = new Enemy("Demonling", 100*((double)this.dunLvl/10.0), 25*((double)this.dunLvl/10.0), 15*((double)this.dunLvl/10.0), this.dunLvl, new Item[] {}, new Item[] {});
		Enemy goblin = new Enemy("Goblin", 150*((double)this.dunLvl/10.0), 20*((double)this.dunLvl/10.0), 15*((double)this.dunLvl/10.0), this.dunLvl, new Item[] {}, new Item[] {});
		Enemy smallSnoodle = new Enemy("Little Snoodle", 160*((double)this.dunLvl/10.0), 30*((double)this.dunLvl/10.0), 20*((double)this.dunLvl/10.0), this.dunLvl, new Item[] {}, new Item[] {new Item(Item.PRDF_SMALLKNIFE)});
		Enemy rat = new Enemy("Mutant Rat", 90*((double)this.dunLvl/10.0), 10*((double)this.dunLvl/10.0), 15*((double)this.dunLvl/10.0), this.dunLvl, new Item[] {}, new Item[] {});
		
		for(int i = 0;i<enemyCount;i++) {
			switch(enemType) {
			case 0: this.enemies.add(goblin.copy());
					break;
			case 1: this.enemies.add(demonling.copy());
					break;
			case 2: this.enemies.add(smallSnoodle.copy());
					break;
			case 3: this.enemies.add(rat.copy());
					break;
			}
		}
		return this.enemies;
	}
	
	/**
	 * the method called to start the dungeon encounter
	 * @param player  the player
	 */
	public void play(Character player) {
		this.enemies = generate();
		Enemy enemy;
		int enemchoice;
		Shop dun = new Shop();
		int tvar = 0;
		while(this.enemies.size() != 0) {
			System.out.printf("There are %d enemies\n", enemies.size());
			System.out.println("Which would you like to fight?");
			for(int i = 0;i<enemies.size();i++) {
				System.out.printf("%1$d. %2$s, lvl: %3$d\n", i+1, enemies.get(i).name, enemies.get(i).level); 
				tvar = i;
			}
			
			System.out.printf("%1$d. Shop\n", tvar+2);
			enemchoice = Util.getInputasInt(tvar+2);
			if(enemchoice==tvar+2) {
				dun.transaction(player);
				continue;
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
			
			initBattle(player, enemy);
		}
		
	}
	public Boolean initBattle(Character player, Enemy enemy) { 
		player.curhp = player.basehp; //reset health after each battle the enemies are too difficult to not do this.
		boolean ree = battle(player, enemy);
		return ree;
	}
	
	/**
	 * the battle method
	 * @param player  the player
	 * @param enemy   the chosen enemy
	 * @return        did they win?
	 */
	public Boolean battle(Character player, Enemy enemy) {
		player.spec.time(player, enemy);
		this.CLOCK += 1;
		System.out.println(player.spec.timer);
		int actionChoice;
		actionChoice = 0;
		System.out.printf("Player HP is at %1$f\n%2$s HP is at %3$f\n", player.curhp, enemy.name, enemy.curhp);
		System.out.println("What would you like to do?\n1.Fight\n2.Items\n3.Equipped Items\n4.Special\n5.Retreat");
		actionChoice = Util.getInputasInt(5);
		switch(actionChoice) {
		case 1: fight(player, enemy);
				break;
		case 2: inventory(player, enemy);
				break;
		case 3: equipped(player);
				break;
		
		case 4: if(player.spec.timer  >= player.spec.cooldown) {
					player.spec.use(player,  enemy);
				}else {
					player.spec.timer -= 1;
					System.out.println("You cant use that yet");
				}
				break;
		case 5: if(retreat(player)) {
					return false;
				}
				break;

		default:
		}
		
		if(player.curhp > 0 && enemy.curhp > 0) {
			battle(player, enemy);
		}else if(player.curhp > 0 && enemy.curhp <= 0){
			endBattle(player, enemy);
			System.out.printf("You have defeated %1$s!", enemy.name);
			this.enemies.remove(enemies.indexOf(enemy));
			
		}else if(player.curhp <=0 ) {
			System.out.println("You lost");
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			System.exit(0);
		}
		
		return true;
	}
	
	/**
	 * handles damage tradeoff
	 * @param player   the player
	 * @param enemy    the enemy
	 */
	public void fight(Character player, Enemy enemy) {
		double edmgdiff = player.getDamageTot(enemy);
		double pdmgdiff = enemy.getDamageTot(player);
		System.out.printf("You hit the enemy for %1$f damage!\n", edmgdiff);
		enemy.curhp -= edmgdiff;
		System.out.printf("%1$s hit you for %2$f damage!\n", enemy.name, pdmgdiff);
		player.curhp -= pdmgdiff;
	}
	
	/**
	 * menu to handle use of consumables and equipping ofitems
	 * @param player   the player
	 */
	public void inventory(Character player, Enemy enemy) {
		boolean used = false;
		while(used != true) {
			System.out.println("What item would you like to use: ");
			int lastNum = 0;
			for(int i = 0;i<player.inventory.size();i++) {
				System.out.printf("%1$d. %2$s\n", i+1, player.inventory.get(i).name);
				lastNum = i;
			}
			System.out.printf("%d. Back\n", lastNum+2);
			int menchoice=0;	
			menchoice = Util.getInputasInt(lastNum+2);
			if(lastNum + 2==menchoice) {
			used = true;
			}else{
				used = player.inventory.get(menchoice-1).use(player, enemy);
				used = true;
			}
		}
	}
	
	/**
	 * level handling
	 * @param player   the player
	 * @return         the level
	 */
	public static int checkLevel( Character player) {
		//checks the amount of experience and then levels up accordingly
		double expAmount = Math.pow(3, player.level);
		if (player.exp >= expAmount) {
			player.level += 1;
			player.attack += 5;
			player.defense += 3;
			player.mp += 4;
			player.basehp += 10;
			expAmount = Math.pow(3, player.level);
			System.out.println("Congrats! you've leveled up to level " + player.level);
			System.out.println("exp till next level: " + (expAmount - player.exp));
			}
		else {
			player.level = player.level;
			System.out.println("exp till next level: " + (expAmount - player.exp));
		}
		return player.level;	
			
	}
/*	public static int checkEveryLevel(Character player){
		for (int i = 1; i < 100; i ++) {
			 CheckLevel(Math.pow(i,3),i, player);
			}
		return player.level;
		} 
*/

		
	/**
	 * handles levelling
	 * @param player  the player
	 * @param enemy   the enemy defeated
	 */
	public void battleGain(Character player, Enemy enemy){
		//If the player is still alive run this method after battle
	 player.exp += Math.pow(enemy.level,3);
	System.out.println("You have: " + player.exp + " Exp");
	 player.gold += enemy.level;
	 System.out.println("You now have: " +  + player.gold  + " Gold");
	 
	}

	/**
	 * handles end of battle and loot
	 * @param player
	 * @param enemy
	 */
	public void endBattle(Character player, Enemy enemy){
		//updates gain at the end of a battle and determine 
		if ((player.curhp >= 0) && (enemy.curhp <= 0)){
			battleGain(player, enemy);
			loot(player);
			checkLevel(player); 
			System.out.println("your current level is: " + player.level);

		}
		else if((player.curhp <= 0) && (enemy.curhp >= 0)){
			System.out.println("You have died");
		}
		else {
			System.out.println("");
		}
	}

	/**
	 * menu option to remove an equipped item
	 * @param player  the player to edit
	 */
	public void equipped(Character player) {
		System.out.println("Dequip an item here");
		System.out.println("Your equipped items are:");
		int tvar = 0;
		for(int i = 0;i<player.equippedItems.size();i++){
			System.out.printf("%1$d.%2$s\n", i+1, player.equippedItems.get(i).name);
			tvar = i;
		}
		System.out.printf("%1$d.Back\n", tvar+2);
		int choice = Util.getInputasInt();
		if(choice==tvar+2) {
			return;
		}
		System.out.printf("Are you sure you want to dequip the %1$s?(y/n)\n", player.equippedItems.get(choice-1).name);
		if(Util.getInput().toLowerCase().equals("y")) {
			player.inventory.add(player.equippedItems.get(choice-1));
			player.equippedItems.remove(player.equippedItems.get(choice-1));
		}else {
			equipped(player);
		}
	}
	
	/**
	 * handles retreat chance its 50/50
	 * @param player the player idk why prob need it later and to keep formatting with other menu options
	 * @return       did the retreat succeed
	 */
	public boolean retreat(Character player) {
		if(Math.random() > 0.5) {
			System.out.println("Excape succeeded");
			return true;
		}else {
			System.out.println("Escape failed!");
			return false;
		}
	}
	
	public void special(Character player) {
		
	}
	
	/**
	 * random item given to player when battle over
	 * @param player   the player
	 */
	public void loot(Character player) {
		player.inventory.add(new Item(player));
	}
	
}