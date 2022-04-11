import java.util.Formatter;
import java.util.Scanner;

/**
 * This class contains attributes and properties of a vegetable
 * Student Name: Samuel Omaghomi
 * Student Number: 041012180
 * Course: CST8130 - Data Structures
 * CET-CS-Level 3
 * Due Date: 20/02/2022
 * Professor: James Mwangi 
 * 
 */
public class Vegetable extends FoodItem {
	/**
	 * Stores the name of the farm
	 */
	private String farmName;
	
	/**
	 * Default constructor
	 */
	Vegetable(){}
	
	/**
	 * Outputs a String of attributes of a vegetable object
	 */
	@Override
	public String toString() {
		super.toString();
		System.out.printf("farm supplier: %s \n",farmName);
		return null;
	}
	
	/**
	 * Fills in the attributes of a vegetable 
	 * @param scanner User input
	 * @param fromFile Input From File
	 */
	@Override 
	public boolean addItem(Scanner scanner, boolean fromFile) {
		super.addItem(scanner, fromFile);
		if(fromFile == false) {
			System.out.print("Enter the name of the farm supplier: ");
			farmName = scanner.nextLine();
		} else {
			farmName = scanner.next();
		}
		
		return true;
	}
	
	/**
	 * Outputs attributes of Food Item to a File
	 * @param writer File
	 */
	@Override
	public void outputItem(Formatter writer) {
		super.outputItem(writer);
		writer.format("%s\r\n", farmName);
	}
}
