package base;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Util {
	
	private static Scanner sc = new Scanner(System.in);
	
	/**
	 * one method to handle all input throughout the code
	 * @return  returns user input as String
	 */
	public static String getInput() {
		String inp;
		while(true) {
			try {
				inp = sc.nextLine();
				return inp;
			}catch(InputMismatchException e) {
				e.printStackTrace();
			}catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * takes input as an int
	 * @return  returns user input as int
	 */
	public static int getInputasInt() {
		int inp;
		while(true) {
			try {
				inp = Integer.parseInt(sc.nextLine());
				return inp;
			}catch(NumberFormatException e) {
				e.printStackTrace();
			}catch(InputMismatchException e) {
				e.printStackTrace();
			}
		}
	}
}
