package base;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.sound.sampled.*;
import sun.audio.*;
import org.apache.commons.lang3.math.NumberUtils;
import java.io.*;

public class Util {
	
	private static Scanner sc = new Scanner(System.in);
	
	public static String getInput() {
		String inp;	
		while(true) {
			try {
				inp = sc.nextLine();
				return inp;
			}catch(InputMismatchException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static int getInputasInt() {
		int inp = 0;
		while(true) {
			try {
				inp = NumberUtils.toInt(sc.nextLine());
				
			}catch(InputMismatchException e) {
				e.printStackTrace();
			}
			if(inp <= 0) {
				System.out.println("Please input an available option");
				continue;
			}
			return inp;
		}
	}
	
	public static int getInputasInt(int max) {
		int inp = 0;
		while(true) {
			try {
				inp = NumberUtils.toInt(sc.nextLine());
				if(inp > max) {
					System.out.println("Pick one in range please");
					continue;
				}
			}catch(InputMismatchException e) {
				e.printStackTrace();
			}
			if(inp <= 0) {
				System.out.println("Please input an available option");
				continue;
			}
			return inp;
		}
	}
	
	public void playSound(String file) throws IOException {
		InputStream in = new FileInputStream(file);
		AudioStream a = new AudioStream(in);
		AudioPlayer.player.start(a);
	}
	
	public void sleep(int t) {
		try {
			Thread.sleep(t);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}
