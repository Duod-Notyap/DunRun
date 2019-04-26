package base;

public class DunRunMain {
	public static String name;
	public static int attack;
	public static int defense;
	public static int hp;
	public static int level;
	public static double plrExp;
	
	static Character player = new Character(name,hp,attack,defense,level,plrExp, new Item[] {}, new Item[] {new Item (Item.PRDF_SWORD)});

	/**
	 * main method
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("                                                                                                                                                                        \r\n" + 
				"                                                                                                                                                                        \r\n" + 
				"                                                                                                                                                .                       \r\n" + 
				" `###:,,,,,;++++              `##+              :#+        ,#                  ##:         `###,,,:+++             ###'             `##.        +                  .#'  \r\n" + 
				"  ,++       `,,,++`            ,++              ,,+        ,;#                 ,+           ,+#     ,,##           ,,+               ,+         ,#                 ,.+  \r\n" + 
				"  ,++          ,,,##           ,++              ,,+        ,`#+                :+           ,'+      .`##          ,,+               ,+         ,#+                ..;  \r\n" + 
				"  :++            .`##          ,#+              ,,+        ,:.++               :#           ,'+       ``#          ,,+               :+         ,.#+                .;  \r\n" + 
				"  :++             ``##         :##              .,#        ,#..++              :#           :'+       ..#+         :,#               :#         ,.,++               .;  \r\n" + 
				"  :++              ..++        :##              .,#        ,+ ,.++             :#           :'+        ,++         :,#               :#         , ,,++              .;  \r\n" + 
				"  :++               ,,+        :#+              .,+        ,+  :.++            :#           :'+        ,++         :,+               :+         ,  ::++             .;  \r\n" + 
				"  :++               ,,++       :#+              .,+        :#   :.+'           :#           :'+        ,++         :,+               :+         ,   :,++            .;  \r\n" + 
				"  :+#                ,:+       :#+              .,+        :#    ,.+.          :#           :'+        ,+          :,+               :+         ,    ,,++           ..  \r\n" + 
				"  :+#                ,,+       :#+              `,+        :#    ,,:+          ,#           :'#        :+          :,+               :+         ,     ,,++          ..  \r\n" + 
				"  :+#                ::+       :#+              `,+        :#     ,,++         ,#           ,+#       :'           :,+               ,+         ,,     ,.++         ..  \r\n" + 
				"  ,++                ::+:      ,##              `:#        :#      ,,++        ,#           ,'#       ;'           ,,#               ,#         ,;      ,.++        ..  \r\n" + 
				"  ,++                ,:+:      ,#+              .:+        :#       ,,#+       :+           ,'+      ';            ,,+               ,+         ,'      `,.+'       ..  \r\n" + 
				"  ,++                ,:+       ,++              .,+        :#        ,,#+      :+           :'###++++:             ,,+               ,+         ,+       ,,.+`      ,.  \r\n" + 
				"  :++                ;;+       ,++              `;+        :#         ,,#+     :#           :'+    ,,#;            ,,+               ,+         ,+        ,,,+      ,.  \r\n" + 
				"  :++                ;;+       ,++              ,';        :#          ,,++    ,#           :'+     ,,+            ,,+               ,+         ,+         ,,;+     ,.  \r\n" + 
				"  :++                ;;+       ,'+              ,+         :#           ,,++   ,#           :'+      ,++           ,,+               ,+         ,+          ,,++    ,`  \r\n" + 
				"  :++                '+`       ,,+              :+         :#            ::++  .#           :'+       ,++          ,,+               :+         ,+           ::++   ,`  \r\n" + 
				"  :++               ;''        ,,+              :+         :#             ::++ `+           :'+       ,,++          ,++             :+          ,+            ::++  ,`  \r\n" + 
				"  :+#               ''          ,+:             ;+         :#              :,++ +           :+#        ,,+          ,'+             ;+          ,+             ::++ ,`  \r\n" + 
				"  :+#              ++'          ,:+            ;+          :+               :,+;#           :'+         ,:+         ,,+             ;+          ,+              ::++,`  \r\n" + 
				"  :++             ++;           .,+'           ''          :+`               :.+#           ,'+          ,++         ,++           ''           ,+               :,+.   \r\n" + 
				"  ,++            +';             ,,+`         ';           ,'+                ,.#           ,'+          ,,##         ,+#         ';            ,+                ,,#   \r\n" + 
				"  ,'++         ++:                ,,##      '+;            ,'+                 .#           ,'+           ,,##         ,+#       +;            `,+                 .,   \r\n" + 
				" :..,,+##++++'::                    ``+#+++;:              ..,                  `          :..,#            .`##        .`,#++++::             ..,#                 `   \r\n" + 
				"                                        ``                                                                                  ``                                          \r\n" + 
				" Payton Doud & Thomas Shetler                                                                                                                                           \r\n" + 
				" 3 months work of trail and error... Not anyone cares, please help us... " +
				" We suffer from serve depression and our brains are so unstimulated, this game is the only thing our exsistence depends on\r\n" + 
				
				"");
		System.out.println("Greetings, Welcome to DunRun!");
		//intro and name
		System.out.println("What tis your name?: ");
		player.name = Util.getInput();
		System.out.println("What tis your path of combat?");
		System.out.println("[1]:Mage, has the ablity to heal itself using MP");
		System.out.println("[2]:Warrior, has the ability of combo attacking special but no magic ability ");
		System.out.println("[3]:Rogue, you walk alone and betrayed your people ");
		
		int choice;
		// |
		// |
		// |
		// 
		//
		
		
		
		choice = Util.getInputasInt();
		Special mageSpecial = new Special("Spell", 4) {
			public void use(Character p, Enemy e) {
				System.out.println("You used your spell and drained "+(double)75*((double)player.level/10.0));
				e.curhp -= 75*((double)player.level/10.0);
				p.curhp += 75*((double)player.level/10.0);
				timer = 0;
			}
			public void revert(Character p, Enemy e) {
				
			}
		};
		
		Special warriorSpecial = new Special("Berserk", 4) {
			public void use(Character p, Enemy e) {
				player.damagemlt += 1.0;
				System.out.println("Your damage is doubled!");
				timer = -2;
			}
			public void revert(Character p, Enemy e) {
				System.out.println("Berserk is over!");
				player.damagemlt = 1.0;
			}
		};
		
		Special rogueSpecial = new Special("Backstab", 5) {
			public void use(Character p, Enemy e) {
				e.curhp -= 200*(player.level/10);
			}
			public void revert(Character p, Enemy e) {
			}
		};
		switch(choice) {
		case 1: player.level = 2;
				player.attack = 5;
			    player.defense = 3;
			    player.basehp = 50.0;
			    player.curhp = 50.0;
			    player.mp = 10;
			    player.exp = 0.0;
			    player.spec = mageSpecial;
			    player.equippedItems.add(new Item(Item.PRDF_DAGGER));
				player.inventory.add(new Item(Item.PRDF_SMALLHEAL));
				player.inventory.add(new Item(Item.PRDF_SMALLHEAL));
				player.inventory.add(new Item(Item.PRDF_SMALLHEAL));
				player.type = 1;
				player.pronoun = "man/he";
				break;
		case 2: player.level = 2;
				player.attack = 8;
				player.basehp = 50.0;
				player.curhp = 50.0;
				player.defense = 7;
				player.exp = 0.0;
				player.spec = warriorSpecial;
				player.equippedItems.add(new Item(Item.PRDF_SWORD));
				player.inventory.add(new Item(Item.PRDF_SMALLHEAL));
				player.inventory.add(new Item(Item.PRDF_SMALLHEAL));
				player.inventory.add(new Item(Item.PRDF_SMALLHEAL));
				player.type = 2;
				player.pronoun = "man/he";
				break;
		case 3: player.level = 1;
				player.level = 2;
				player.attack = 8;
				player.basehp = 50.0;
				player.curhp = 50.0;
				player.defense = 7;
				player.exp = 0.0;
				player.spec = rogueSpecial;
				player.equippedItems.add(new Item(Item.PRDF_SWORD));
				player.inventory.add(new Item(Item.PRDF_SMALLHEAL));
				player.inventory.add(new Item(Item.PRDF_SMALLHEAL));
				player.inventory.add(new Item(Item.PRDF_SMALLHEAL));
				player.type = 3;
				player.pronoun = "woman/she";
				break;
		case 4: break;
		}
		story();
//		Dungeon d = null;
//		while(true) {
//			d = new Dungeon(player.level);
//			d.play(player);
//		}
	}
	
	public static void story() {
		System.out.printf("One day a young %s was walking in the woods.\n", player.pronoun);
		Util.sleep(100);
		System.out.println("Then suddenly...");
		Util.playSound("./assets/sounds/fall.wav");
		System.out.println("They fell down a long and dark pit.");
		Util.sleep(7000);
		System.out.println("Immediately they notice they are surrounded by a group of goblins");
		Dungeon d = new Dungeon(1, 3, Dungeon.PRDF_GOBLINS, false, false);
		d.play(player);
		System.out.println("After defeating the goblins, the adventurer had no choice but to continue onward through the cave system.");
		Util.sleep(2000);
		System.out.println("Eventually the adventurer stumbled upon a giant ornate door. ");
		Util.sleep(2000);
		System.out.println("Of course the door had to be blocked by a group of demons, One of which seemed particularly powerful");
		d = new Dungeon(player.level, 2, Dungeon.PRDF_DEMONS, true, false);
		d.play(player);
		System.out.printf("After the battle was over the adventurer stopped and admired the beautiful ornate doors. %1$s noticed a scrawl of text in the bottom of the door. it reads:\n\t\"Onward through the void,\n\tThe silence speaks my name\"\n", player.pronoun.split("/")[1].toUpperCase());
		Util.sleep(2000);
		System.out.println("Would you like to open them?(y/n)");
		String sho = Util.getInput();
		if(sho.equals("y")) {
			Shop s = new Shop();
			s.transaction(player);
		}else{
			System.out.println("You decide to leave the doors alone. The writing must have been a little too creepy");
		}
		Util.sleep(2000);
		System.out.println("After that last encounter you feel tired. You decide to take a rest for a moment.");
		Util.playSound("./assets/sounds/snor.wav");
		Util.sleep(7500);
		System.out.println("You wake up, bound in chains.");
		Util.sleep(2000);
		System.out.println("Upon looking up, you see your captor. A large snake person?");
		Util.sleep(2000);
		System.out.println("He looks back at you and says, \"Hey you. You're finally awake.\"");
		Util.sleep(2000);
		System.out.println("\"You are going to make me a lot of money.\"");
		Util.sleep(2000);
		System.out.println("Its ransom then.");
		Util.sleep(2000);
		System.out.println("You need to find a way out somehow.");
		Util.sleep(2000);
		System.out.println("You try to get the captor's attention. You find a rock and throw it. ITS A HIT!! But now he's pissed and begins storming in your direction.");
		Util.sleep(2000);
		System.out.println("He screams, \"ARE YOU TRYING TO GET YOURSELF KILLED YOU MORON?!\"");
		Util.sleep(2000);
		System.out.println("He puts his face right up against the cage. Perfect. Your arms are thin enough so you quickly reach between the bars and behind his head.");
		Util.sleep(2000);
		System.out.println("One sharp yank forward, his skull into a bar, and hes out cold");
		Util.sleep(2000);
		System.out.println("Lucky for you his keys are attached to his waist. You grab them, unlock your cage and you are free.");
		Util.sleep(2000);
		System.out.println("Your luck didnt last long because you made a bit of a racket with that smack.");
		Util.sleep(2000);
		System.out.println("Suddenly 3 more snake people come along. Get ready to fight.");
		d = new Dungeon(player.level, 3, Dungeon.PRDF_SNOODLE, false, false);
		Util.sleep(4500);
		d.play(player);
		Util.sleep(2000);
		System.out.println("You walk away from the battle covered head to toe in the blood of your enemies.");
		Util.sleep(2000);
		System.out.println("You need to clean yourself.");
		Util.sleep(2000);
		System.out.println("Eventually you find yourself standing in front of a waterfall.");
		Util.sleep(2000);
		System.out.println("You take off your clothes and decide to bathe in the running water.");
		Util.playSound("./assets/souds/spider.wav");
		Util.sleep(2000);
		System.out.println("You immediately regret that decision as a couple of spiders descend upon your undefended body.");
		Util.sleep(2000);
		d = new Dungeon(player.level, 1, Dungeon.PRDF_SPIDER, false, false, false);
	}
}