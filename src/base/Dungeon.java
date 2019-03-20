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
	ArrayList<Enemy> enemies = new ArrayList();
	
	public Dungeon(int lvl) {
		this.seed = (int)(randomizer.nextDouble()*500000.0);
		this.dunLvl = lvl;
		this.enemType = randomizer.nextInt(1);	
	}
	
	public Dungeon(int lvl, int seed) {
		this.seed = seed;
		this.dunLvl = lvl;
		this.enemType = randomizer.nextInt(2);	
	}
	
	public Dungeon(int lvl, int seed, int enemType) {
		this.seed = seed;
		this.dunLvl = lvl;
		this.enemType = enemType;	
	}
	
	public ArrayList<Enemy> generate() {
		int enemyCount = this.seed%4;
		Enemy demLord = new Enemy("Demon Lord", 250*((double)this.dunLvl/10.0), 80*((double)this.dunLvl/10.0), 35*((double)this.dunLvl/10.0), this.dunLvl, new Item[] {}, new Item[] {});
		Enemy gobKing = new Enemy("Goblin King", 300*((double)this.dunLvl/10.0), 60*((double)this.dunLvl/10.0), 40*((double)this.dunLvl/10.0), this.dunLvl, new Item[] {}, new Item[] {});
		switch(enemType) {
		case 0: this.enemies.add(gobKing.copy());
				this.envAdj = "Swampy";
				this.envConds = "wet/muddy";
				break;
		case 1: this.enemies.add(demLord.copy());
				this.envAdj = "Hellish";
				this.envConds = "hot/dry";
				break;
		}
		
		Enemy demonling = new Enemy("Demonling", 100*((double)this.dunLvl/10.0), 25*((double)this.dunLvl/10.0), 15*((double)this.dunLvl/10.0), this.dunLvl, new Item[] {}, new Item[] {});
		Enemy goblin = new Enemy("Goblin", 150*((double)this.dunLvl/10.0), 20*((double)this.dunLvl/10.0), 15*((double)this.dunLvl/10.0), this.dunLvl, new Item[] {}, new Item[] {});
		
		switch(enemType) {
		case 0: for(int i = 0;i<enemyCount;i++) {
					this.enemies.add(goblin.copy());
				}
				break;
		case 1: for(int i = 0;i<enemyCount;i++) {
					this.enemies.add(demonling.copy());
				}
				break;
		}
		return this.enemies;
	}
	
	public void play(Character player) {
		ArrayList<Enemy> enemies = generate();		
		Scanner sc = new Scanner(System.in);
		Enemy enemy;
		String enemPick = "";
		int enemchoice;
		boolean selectedSyntax;
		while(this.enemies.size() != 0) {
			System.out.printf("There are %d enemies\n", enemies.size());
			System.out.println("Which would you like to fight?");
			for(int i = 0;i<enemies.size();i++) {
				System.out.printf("%1$d. %2$s, lvl: %3$d\n", i+1, enemies.get(i).name, enemies.get(i).level); 
			}
			enemPick = "";
			selectedSyntax= false;
			enemchoice = 0;
			while(selectedSyntax != true) {
				try {
					enemPick = sc.next();
					selectedSyntax = true;
					enemchoice = Integer.parseInt(enemPick);
				}catch(NumberFormatException e) {
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
			initBattle(player, enemy);
		}
	}
	public Boolean initBattle(Character player, Enemy enemy) { 
		player.curhp = player.basehp; //reset health after each battle the enemies are too difficult to not do this.
		boolean ree = battle(player, enemy);
		return ree;
	}
	
	public Boolean battle(Character player, Enemy enemy) {
		Scanner sc = new Scanner(System.in);
		String actionSelect = "0";
		int actionChoice;
		actionChoice = 0;
		System.out.printf("Player HP is at %1$f\n%2$s HP is at %3$f\n", player.curhp, enemy.name, enemy.curhp);
		System.out.println("What would you like to do?\n1.Fight\n2.Items\n3.Retreat");
		actionSelect = (sc.hasNextInt()) ? sc.next() : "0";
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
		
		if(player.curhp > 0 && enemy.curhp > 0) {
			battle(player, enemy);
		}else if(player.curhp > 0 && enemy.curhp <= 0){
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
	
	public void fight2 (Character player, Enemy enemy) {
		double edmgdiff = player.getDamageTot(enemy);
		double pdmgdiff = enemy.getDamageTot(player);
		player.curhp -= pdmgdiff;
		System.out.println("you gave enemy " + edmgdiff + " Damage");
		enemy.curhp -= edmgdiff;
		System.out.println(pdmgdiff);
	}
	
	public void fight(Character player, Enemy enemy) {
		double edmgdiff = player.getDamageTot(enemy);
		double pdmgdiff = enemy.getDamageTot(player);
		System.out.printf("You hit the enemy for %1$f damage!\n", edmgdiff);
		enemy.curhp -= edmgdiff;
		System.out.printf("%1$s hit you for %2$f damage!\n", enemy.name, pdmgdiff);
		player.curhp -= pdmgdiff;
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
	}
	
	public void battleGain(Character player, Enemy enemy){
		//If the player is still alive run this method after battle
	 player.exp += Math.pow(enemy.level,3);
	 System.out.println("You now have"+ player.gold);
	 player.gold += enemy.level;
	}

	public void endBattle(Character player, Enemy enemy){
		//updates gain at the end of a battle and determine 
		if ((player.curhp >= 0) && (enemy.curhp <= 0)){
			player.checkEveryLevel(); 
			System.out.println("your current level is: " + player.level);
			battleGain(player, enemy);
		}
		else if((player.curhp <= 0) && (enemy.curhp >= 0)){
			System.out.println("You have died");
		}
		else {
			System.out.println("");
		}
	}


	public void retreat(Character player) {
		
	}
}
 