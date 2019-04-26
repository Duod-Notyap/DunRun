package base;

public abstract class Special {
	public String name;
	public int cooldown;
	public int timer = 0;
	private boolean revert;
	public int duration = 1;
	
	/**
	 * 
	 * @param name
	 * @param duration
	 * @param cooldown
	 */
	public Special(String name, int duration, int cooldown) {
		this.name = name;
		this.duration = duration;
		this.cooldown = cooldown;
	}
	
	/**
	 * 
	 * @param name
	 * @param cooldown
	 */
	public Special(String name, int cooldown) {
		this.name = name;
		this.cooldown = cooldown;
	}
	
	/**
	 * defined on a new instantiation
	 * @param p   the player
	 * @param e   the enemy
	 */
	public abstract void use(Character p, Enemy e); 
	
	/**
	 * activated when timer goes negative to positive
	 * @param p  player
	 * @param e  enemy
	 */
	public abstract void revert(Character p, Enemy e); 
	
	/**
	 * the timing function called in Dungeon.fight()
	 * @param p
	 * @param e
	 */
	public void time(Character p, Enemy e) {
		timer += 1;
		if(timer > 0 && this.revert) {
			revert(p, e);
			this.revert = false;
		}else if(this.timer <= 0) {
			this.revert = true;
		}else if(timer >= 0) {
			this.timer += 1;
		}
		
	}
}
