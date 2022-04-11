import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class interacts with user when entering food item information
 * Student Name: Samuel Omaghomi
 * Student Number: 041012180
 * Course: CST8130 - Data Structures
 * CET-CS-Level 3
 * Due Date: 20/02/2022
 * Professor: James Mwangi 
 * 
 */
public class Assign2 {
	/**
	 * Main method for Assign2 class
	 * @param args Main Arguments array
	 */
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		int option =0; //user option
		Inventory inventory = new Inventory();	//initializes a new Inventory object
		do {
				try {
				displayMenu();	//displays main menu
				option = keyboard.nextInt(); //user option
				switch(option) {
					case 1:
						inventory.addItem(keyboard, false);	//adds item to inventory
						break;
					case 2:
						inventory.toString();	//displays values in the inventory
						break;
					case 3:
						inventory.updateQuantity(keyboard, true);	//buy = true
						break;
					case 4:
						inventory.updateQuantity(keyboard, false);	//sell = false
						break;
					case 5:
						inventory.searchForItem(keyboard);
						break;
					case 6:
						inventory.saveToFile(keyboard);
						break;
					case 7:
						inventory.readFromFile(keyboard);
						break;
					case 8:
						System.out.println("Goodbye...");
						break;
					default:
						System.err.println("Invalid input!... Please enter a number between 1 - 8");	//error message if invalid input is entered
						break;
					}
				} catch(InputMismatchException ime) {
					System.err.println("Invalid input!... Please enter a number");	//error message if invalid input is entered
					keyboard.nextLine(); //clears input stream;
				} catch(Exception e) {
					System.err.println("Invalid input!... Please enter a number");	//error message if invalid input is entered
				}
		} while(option != 8);
		keyboard.close();	//closes the input stream to prevent resource leak


	}
	
	/**
	 * Displays a menu of options for user
	 */
	public static void displayMenu() {
		System.out.printf("Please select one of the following: \n1. Add Item to Inventory \n2. Display Current Inventory \n3. Buy Item(s) "
				+ "\n4. Sell Item(s) \n5. Search for Item \n6. Save Inventory to File \n7. Read Inventory from File \n8. To Exit \nEnter Option: ");
	}
}
