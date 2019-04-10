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
	@SuppressWarnings(value="static-access")
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
		Shop shop = new Shop();
		player.gold = 15000;
		shop.transaction(player);
		
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
		
		switch(choice) {
		case 1: player.level = 2;
				player.attack = 5;
			    player.defense = 3;
			    player.basehp = 50.0;
			    player.curhp = 50.0;
			    player.mp = 10;
			    player.exp = 0.0;
			    player.equippedItems.add(new Item(Item.PRDF_DAGGER));
				player.inventory.add(new Item(Item.PRDF_SMALLHEAL));
				player.inventory.add(new Item(Item.PRDF_SMALLHEAL));
				player.inventory.add(new Item(Item.PRDF_SMALLHEAL));
				break;
		case 2: player.level = 2;
				player.attack = 8;
				player.basehp = 50.0;
				player.curhp = 50.0;
				player.defense = 7;
				player.exp = 0.0;
				player.equippedItems.add(new Item(Item.PRDF_SWORD));
				player.inventory.add(new Item(Item.PRDF_SMALLHEAL));
				player.inventory.add(new Item(Item.PRDF_SMALLHEAL));
				player.inventory.add(new Item(Item.PRDF_SMALLHEAL));
				break;
		case 3: player.level = 1;
				player.level = 2;
				player.attack = 8;
				player.basehp = 50.0;
				player.curhp = 50.0;
				player.defense = 7;
				player.exp = 0.0;
				player.equippedItems.add(new Item(Item.PRDF_SWORD));
				player.inventory.add(new Item(Item.PRDF_SMALLHEAL));
				player.inventory.add(new Item(Item.PRDF_SMALLHEAL));
				player.inventory.add(new Item(Item.PRDF_SMALLHEAL));
				break;
		case 4: break;
		
				
		}
		
		System.out.println("Great!, so "+ player.name+ "lets give you a quick battle practice");
	
		Dungeon testdun = new Dungeon(player.level);
		
		testdun.play(player);
	}

	
	
}
