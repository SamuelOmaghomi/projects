import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class contains attributes and properties of a preserve
 * Student Name: Samuel Omaghomi
 * Student Number: 041012180
 * Course: CST8130 - Data Structures
 * CET-CS-Level 3
 * Due Date: 20/02/2022
 * Professor: James Mwangi 
 * 
 */
public class Preserve extends FoodItem {
	/**
	 * Size of Jar
	 */
	private int jarSize;
	
	/**
	 * Default Constructor
	 */
	Preserve(){}
	
	/**
	 * Outputs a String of attributes of a Preserve object
	 */
	@Override
	public String toString() {
		super.toString();
		System.out.printf("size %smL \n",Integer.toString(jarSize));
		return null;
	}
	
	/**
	 * Fills in the attributes of a Preserve food item
	 * @param scanner User input
	 * @param fromFile Input From File
	 */
	@Override 
	public boolean addItem(Scanner scanner, boolean fromFile) {
		super.addItem(scanner,fromFile);
		do {
			try {
				if(fromFile == false) {
					System.out.print("Enter the size of the jar in milliliters: ");
				}
				jarSize = scanner.nextInt();
				if(jarSize <= 0) {	//checks if item price is greater than 0
					System.out.println("Invalid size entered ...");
				}
			} catch(InputMismatchException ime) {
				System.out.println("Invalid size entered ...");
				scanner.nextLine(); //clears input stream
			} catch(Exception e) {
				System.out.println("Invalid size entered ...");
			}
			
		} while(jarSize <= 0);
		return true;
	}
	
	/**
	 * Outputs attributes of Food Item to a File
	 * @param writer File
	 */
	@Override
	public void outputItem(Formatter writer) {
		super.outputItem(writer);
		writer.format("%d\r\n", jarSize);
	}
}
