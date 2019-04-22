package base;

import java.io.IOException;

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
				"                                                                                                                                                                        \r\n" + 
				"                                                                                                                                                                        \r\n" + 
				"");
		
		//intro and name
		System.out.println("Greetings, Welcome to DunRun!");
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
				player.damagemlt *= 2.0;
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
				player.pronoun = "man";
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
				player.pronoun = "man";
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
				player.pronoun = "woman";
				break;
		case 4: break;		
		}
		Util u = new Util();
		System.out.printf("One day a young %s was walking in the woods.\n", player.pronoun);
		u.sleep(100);
		System.out.println("Then suddenly...");
		try {
			u.playSound("./assets/sounds/fall.wav");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println("They fell down a long and dark pit.");
		u.sleep(7000);
		System.out.println("Immediately they notice they are surrounded by a group of goblins");
		u.createPitchedFile("./assets/sounds/fall.wav", 1.34);
	}
	
}
