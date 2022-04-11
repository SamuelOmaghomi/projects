import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class contains attributes and properties of a food item
 * Student Name: Samuel Omaghomi
 * Student Number: 041012180
 * Course: CST8130 - Data Structures
 * CET-CS-Level 3
 * Due Date: 20/02/2022
 * Professor: James Mwangi 
 * 
 */
public class FoodItem implements Comparable<FoodItem>{
	/**
	 * Code of Food Item
	 */
	protected int itemCode;
	/**
	 * Name of Food Item
	 */
	protected String itemName;
	/**
	 * Price of Food Item
	 */
	protected float itemPrice;
	/**
	 * Quantity in stock of Food Item
	 */
	protected int itemQuantityInStock; 
	/**
	 * Cost of Food Item
	 */
	protected float itemCost;
	
	/**
	 * Default Constructor
	 */
	FoodItem(){}
	
	/**
	 * Outputs a String of attributes of a Food item object
	 */
	@Override
	public String toString() {
		System.out.print("Item: " + Integer.toString(itemCode) + " " + itemName + " " + Integer.toString(itemQuantityInStock) + " "
				+ "price: " + Float.toString(itemPrice) + " cost: " + Float.toString(itemCost)+ " ");
		return null;		
	}
	
	/**
	 * Updates item's quantity in stock
	 * @param amount Quantity to be deducted/added
	 * @return True or False
	 */
	public boolean updateItem(int amount) {
		if(itemQuantityInStock-amount > 0 ) {	//checks if amount to be deducted is less than item quantity
			itemQuantityInStock = itemQuantityInStock-amount;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Checks if object passed has the same item code as current object
	 * @param item FoodItem Object
	 * @return True or False
	 */
	public boolean isEqual(FoodItem item) {
		if(item.itemCode == itemCode) {	//if item codes match
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Fills in the attributes of a Food item
	 * @param scanner User Input
	 * @param fromFile Input From File
	 * @return True or False
	 */
	public boolean addItem(Scanner scanner, boolean fromFile) {	
		if(fromFile == false) {
			System.out.print("Enter the name for the item: ");
			itemName = scanner.nextLine();
		} else {
			itemName = scanner.next();
		}
		
		do {
			try {
				if(fromFile == false) {
					System.out.print("Enter the quantity for the item: ");
				}
				itemQuantityInStock = scanner.nextInt();
				if(itemQuantityInStock <= 0) {	//checks if item quantity is greater than 0
					System.out.println("Invalid quantity entered ...");
				}
			} catch(InputMismatchException ime) {
				System.out.println("Invalid quantity entered ...");
				scanner.nextLine(); //clears input stream
			} catch(Exception e) {
				System.out.println("Invalid quantity entered ...");
			}
			
		} while(itemQuantityInStock <= 0); 	//checks if item quantity has been entered correctly
		
		do {
			try {
				if(fromFile == false) {
					System.out.print("Enter the cost of the item: ");
				}
				itemCost = scanner.nextFloat();
				
				if(itemCost <= 0) {	//checks if item cost is greater than 0
					System.out.println("Invalid cost entered ...");
				}
			} catch(InputMismatchException ime) {
				System.out.println("Invalid cost entered ...");
				scanner.nextLine(); //clears input stream
			} catch(Exception e) {
				System.out.println("Invalid cost entered ...");
			}
			
		} while(itemCost <= 0);	//checks if item cost has been entered correctly
		
		do {
			try {
				if(fromFile == false) {
					System.out.print("Enter the sales price of the item: ");
				}
				itemPrice = scanner.nextFloat();
				if(itemPrice <= 0) {	//checks if item price is greater than 0
					System.out.println("Invalid price entered ...");
				}
				if(fromFile == false) {
					scanner.nextLine(); //clears input stream
				}
			} catch(InputMismatchException ime) {
				System.out.println("Invalid price entered ...");
				scanner.nextLine(); //clears input stream
			} catch(Exception e) {
				System.out.println("Invalid price entered ...");
			}
			
		} while(itemPrice <= 0); //checks if item price has been entered correctly
		
		return true;	//successfully adds item
		
	}
	
	/**
	 * Reads Code of Food Item
	 * @param scanner User Input
	 * @param fromFile Input From File
	 * @return True or False
	 */
	public boolean inputCode(Scanner scanner, boolean fromFile) {
		int input=-1;	
		boolean success = false;	//method success variable
		try {
			if(fromFile == false) {
				System.out.print("Enter the code for the item: ");
			}
			 input = scanner.nextInt(); //user input
			 if(fromFile == false) {
				 scanner.nextLine(); //clears input stream
			 }
			  itemCode = input;
			 success = true;	//item code successfully entered 
			
		} catch(InputMismatchException ime) {
			scanner.nextLine(); //clears input stream
		} catch(Exception e) {
			System.out.println("Invalid input entered ...");
		}

		return success;
	}
	
	/**
	 * Outputs the item code
	 * @return Item Code
	 */
	public int getItemCode() {
		return itemCode;
	}
	
	/**
	 * Compares the item code to the item code of the argument
	 */
	@Override
	public int compareTo(FoodItem item) {
		return this.itemCode - item.itemCode;
	}
	
	/**
	 * Outputs attributes of Food Item to a File
	 * @param writer File 
	 */
	public void outputItem(Formatter writer) {
		try {	
				writer.format("%d\r\n%s\r\n%d\r\n%.2f\r\n%.2f\r\n", itemCode, itemName, itemQuantityInStock, itemCost, itemPrice );	//outputs the values in the Food Item
		} catch (SecurityException se) {
			System.out.println("write permission denied...");
		}  catch (Exception e) {
			System.out.println("Error writing to file");
		}
	}
}
