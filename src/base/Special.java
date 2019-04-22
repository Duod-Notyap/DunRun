package base;

public abstract class Special {
	public String name;
	public int cooldown;
	public int timer = 0;
	private boolean revert;
	public int duration = 1;
	public Special(String name, int duration, int cooldown) {
		this.name = name;
		this.duration = duration;
		this.cooldown = cooldown;
	}
	
	public Special(String name, int cooldown) {
		this.name = name;
		this.cooldown = cooldown;
	}
	
	public abstract void use(Character p, Enemy e); 
	public abstract void revert(Character p, Enemy e); 
	
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
