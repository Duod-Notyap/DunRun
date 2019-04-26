package base;
import java.util.InputMismatchException;
import java.util.Scanner;
import sun.audio.*;
import org.apache.commons.lang3.math.NumberUtils;
import java.io.*;

public class Util {
	
	private static Scanner sc = new Scanner(System.in);
	
	/**
	 * gets input as string
	 * @return
	 */
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
	
	/**
	 * gets input and returns as an int
	 * @return
	 */
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
	
	/**
	 * gets input and returns as an int will as again if above max
	 * @param max  specified maximum
	 * @return
	 */
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
	
	/**
	 * plays the .wav at file path
	 * @param file  the file path
	 * @throws IOException
	 */
	public static void playSound(String file) {
		InputStream in;
		try {
			in = new FileInputStream(file);
			AudioStream a = new AudioStream(in);
			AudioPlayer.player.start(a);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * i was tired of writing try-catch
	 * @param t
	 */
	public static void sleep(int t) {
		try {
			Thread.sleep(t);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}
