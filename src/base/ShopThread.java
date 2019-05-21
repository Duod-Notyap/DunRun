package base;
import java.util.TimerTask;

public class ShopThread extends TimerTask {
	public ShopThread() {
		super();
	}
	@Override
	public void run() {
		System.out.println("You gonna buy somethin?");
	}
}
