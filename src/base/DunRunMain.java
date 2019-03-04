package base;
import java.util.ArrayList;
import java.util.Scanner;

public class DunRunMain {
	public static String name;
	public static int attack;
	public static int defense;
	public static int hp;
	public static int level;
	public static double plrExp;
	
	static Character player = new Character(name,hp,attack,defense,level,plrExp, new Item[] {new Item (Item.PRDF_SWORD)}, new Item[] {new Item (Item.PRDF_SWORD)});


	
	
	public static void battleStart(Enemy[] enemies){		
		Scanner sc = new Scanner(System.in);
		System.out.printf("There are %d enemies\n", enemies.length);
		System.out.println("Which would you like to fight?");
		for(int i = 0;i<enemies.length;i++) {
			System.out.printf("%1$d. %2$s, lvl: %3$d\n", i+1, enemies[i].name, enemies[i].level);
		}
		String enemPick = sc.next();
		int enemchoice = Integer.parseInt(enemPick);
		Enemy enemy = enemies[enemchoice-1];
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
		for (int i = 0; (player.curhp > 0) && (enemy.curhp > 0); i++) {
			System.out.println("Round " + i);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			
				
			if (i % 2 == 0) {
			
				
				System.out.println(player.name + " attacks");
				double d = player.getDamageTot(enemy);
				System.out.println("You hit "+enemy.name + "for damage of " + d);
				enemy.curhp = enemy.curhp - d;
			} 
			
			else {
				System.out.println("Enemy attacks");
				double d = enemy.getDamageTot(player);
				System.out.println("Enemy hit you for a damage of " + d);
				player.curhp = player.curhp - d;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Player HP " + player.curhp + " Enemy HP " + ((enemy.curhp<=0) ? "0.0" : enemy.curhp));
		}
		if(player.curhp > enemy.curhp) {
			System.out.println("Congrats, you won!");
		}
		else {
			System.out.println("Unfortunatly, " + enemy.name + " Has beaten you to death, Sorry...");
		}
	}

//	public static int damage(double level, int weapon, double AttackBase, double DefenseBase){
//	
//		
//		double Luck = Math.random() + 0.5;
//
//		
//		double Damage = Luck * (((2*level+10)/25) * (weapon * AttackBase/DefenseBase));
//		
//		System.out.println(Damage);
//		
//		return (int) Damage;
//	}

		
	public void optionFight() {
		//Option Fight
		
		System.out.println("");
	}
	
	public double experienceGain() {
		
		 
		return player.exp;
		
	}
	
	public static void main(String[] args) {
		//intro and name
		System.out.println("Greetings, Welcome to DunRun!");
		System.out.println("What tis your name?: ");
		Scanner sc = new Scanner(System.in);
		player.name = sc.nextLine();
		System.out.println("What tis your path of combat?");
		System.out.println("[1]:Mage, has the ablity to heal itself using MP");
		System.out.println("[2]:Warrior, has the ability of combo attacking special but no magic ability ");
		System.out.println("[3]:Rogue, you walk alone and betrayed your people ");
		
		int choice;
		
		choice = sc.nextInt();
		
		switch(choice) {
		case 1:player.level = 2;
				player.attack = 5;
			   player.defense = 3;
			   player.basehp = 50.0;
			   player.curhp = 50.0;
			   player.mp = 10;
			   player.exp = 33.0;
			   player.equippedItems.add(new Item(Item.PRDF_DAGGER));
		case 2: player.level = 2;
				player.attack = 8;
				player.basehp = 50.0;
				player.curhp = 50.0;
				player.defense = 7;
				player.exp = 33.0;
				player.equippedItems.add(new Item(Item.PRDF_SWORD));
		case 3: player.level = 1;
				player.level = 2;
				player.attack = 8;
				player.basehp = 50.0;
				player.curhp = 50.0;
				player.defense = 7;
				player.exp = 33.0;
				player.equippedItems.add(new Item(Item.PRDF_SWORD));
		
				
		}
		
		System.out.println("Great!, so "+ player.name+ "lets give you a quick battle practice");
	
		Enemy bandit = new Enemy("Bandit", 20.0, 5, 2, 1, new Item[] {}, new Item[] {new Item (Item.PRDF_SWORD)});

		
		Dungeon testdun = new Dungeon(player.level);
		testdun.play(player);

	}

	
	
}
