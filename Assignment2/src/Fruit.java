import java.util.Formatter;
import java.util.Scanner;

/**
 * This class contains attributes and properties of a Fruit
 * Student Name: Samuel Omaghomi
 * Student Number: 041012180
 * Course: CST8130 - Data Structures
 * CET-CS-Level 3
 * Due Date: 20/02/2022
 * Professor: James Mwangi 
 * 
 */
public class Fruit extends FoodItem{
	/**
	 * Name of Orchard supplier
	 */
	private String orchardName;
	
	/**
	 * Default Constructor
	 */
	Fruit(){}
	
	/**
	 * Outputs a String of attributes of a fruit object
	 */
	@Override
	public String toString() {
		super.toString();
		System.out.printf("orchard supplier: %s \n", orchardName);
		return null;
	}
	
	/**
	 * Fills in the attributes of a Fruit
	 * @param scanner User input
	 * @param fromFile Input From File
	 */
	@Override 
	public boolean addItem(Scanner scanner, boolean fromFile) {
		super.addItem(scanner,fromFile);
		if(fromFile == false) {
			System.out.print("Enter name of the orchard supplier: ");
			orchardName = scanner.nextLine();
		} else {
			orchardName = scanner.next();
		}
				
		return true;
	}
	
	/**
	 * Outputs attributes of Fruit to a File
	 * @param writer File
	 */
	@Override
	public void outputItem(Formatter writer) {
		super.outputItem(writer);
		writer.format("%s\r\n", orchardName);
	}
}
