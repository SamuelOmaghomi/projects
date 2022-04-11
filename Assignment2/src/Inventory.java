import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class holds an inventory of food items
 * Student Name: Samuel Omaghomi
 * Student Number: 041012180
 * Course: CST8130 - Data Structures
 * CET-CS-Level 3
 * Due Date: 20/02/2022
 * Professor: James Mwangi 
 * 
 */
public class Inventory {
	/**
	 * Stores Food Item objects
	 */
	private ArrayList<FoodItem> inventory;
	/**
	 * Number of items in inventory array
	 */
	private int numItems;
	
	/**
	 * Default Constructor
	 */
	Inventory(){
		inventory = new ArrayList<FoodItem>(20);	//initializes inventory arraylist 
	}
	
	/**
	 * Outputs a String of attributes of items in the inventory array
	 */
	public String toString() {
		sort(); //sorts the inventory array list
		System.out.println("Inventory: ");
		if(numItems != 0) {	//checks if inventory arraylist is empty
			for(int i =0; i < numItems; i++) {
				inventory.get(i).toString();
			}
			System.out.println("");
		}
		
		return null;
		
	}
	
	/**
	 * Checks if object passed has the same item code as an existing object
	 * @param item FoodItem Object
	 * @return 0 or -1
	 */
	public int alreadyExists(FoodItem item) {
		int index = 0;	//base index of array
		if(numItems > 0) {	//checks if there are at least 2 elements in inventory arraylist
			for(int i=0; i < numItems; i++) {	//loops through each element in the inventory arraylist
				if(inventory.get(i).isEqual(item) == true){		//compares item code of all items in the inventory arraylist to the item code of the argument object 
					index = -1;	//index is -1 if object has the same item code of an existing item code
				} 
			} 
		}
		return index;	//-1 if there's a match, 0 if not
	}
	
	/**
	 * Adds a food item to the inventory array
	 * @param scanner User Input
	 * @param fromFile Input From File
	 * @return True or false
	 */
	public boolean addItem(Scanner scanner, boolean fromFile) {
		String option = null; //user option
		boolean continueLoop = true;	//loop variable for do-while loop
		boolean success = false; //method success variable
		do {
			if(fromFile == false) {
				System.out.print("Do you wish to add a fruit(f), vegetable(v) or a preserve(p)? ");
			}
			try {
				option = scanner.next();
				if(option.equalsIgnoreCase("f") || option.equalsIgnoreCase("v") || option.equalsIgnoreCase("p")) { //checks if user input if f, v or p
					if(option.equalsIgnoreCase("f")) {
						inventory.add(new Fruit());	//creates new fruit object
					} else if(option.equalsIgnoreCase("v")){
						inventory.add(new Vegetable()); //creates new vegetable object
					} else {
						inventory.add(new Preserve()); //creates new preserve object
					}
					if(inventory.get(numItems).inputCode(scanner, fromFile) == true) {	//Reads item code of food item
						if(alreadyExists(inventory.get(numItems)) != -1) { //checks if item code already exists
							inventory.get(numItems).addItem(scanner,fromFile); //adds items if no match is found in item code
							numItems++;
							success = true; //item successfully added
						} else {
							System.out.println("Item code aleady exists");
						}
					} else {
						System.out.println("Invalid code entered");
					}
					continueLoop = false;	//stops loop
				} else {
					System.out.println("Invalid Entry");
				}
			}catch(Exception e) {
				continueLoop = false;	//stops loop
			}
		} while(continueLoop == true);
		return success;
		
	}
	
	/**
	 * Updates item's quantity in stock after a user buys/sells
	 * @param scanner User Input
	 * @param buyOrSell Buy(True) or Sell(False)
	 * @return True or False
	 */
	public boolean updateQuantity(Scanner scanner, boolean buyOrSell) {	//buy == true, sell ==false
		boolean success = false; //method success variable
		
		if(numItems != 0) {	//checks if inventory arraylist is empty
			int itemCode =-1;	//item code
			int itemIndex =-1; //item index in inventory array
			try {
				System.out.print("Enter valid item code: ");
				itemCode = scanner.nextInt();
				for(int i=0; i < numItems; i++) { //loops through each item in inventory arraylist
					if(inventory.get(i).itemCode == itemCode) { //matches item code entered buy user to an item in the arraylist
						itemIndex = i;
					} 
				} if(itemIndex == -1) {	//if a match was not found
					System.out.println("Code not found in inventory...");
				}
			} catch(InputMismatchException ime) {
				System.out.println("Invalid code entered ...");
				scanner.nextLine(); //clears input stream
			} catch(Exception e) {
				System.out.println("Invalid code entered ...");
			}
			
			if(buyOrSell == true && itemIndex != -1) {	//if item was found and buy was selected
				try {
					System.out.println("Enter valid quantity to buy: ");
					int buy = scanner.nextInt();//quantity to buy
					if(buy > 0) {	//quantity must be above 0
						buy = -buy; //buy is negative, -- = +, so it adds to item quantity
						if(inventory.get(itemIndex).updateItem(buy) == false) {	//if buy quantity is more than item quantity in stock
							System.out.println("Invalid quantity...");
							System.out.println("Error...could not buy item");
						} else {
							success = true; //item was successfully bought
						}
					}
				} catch(InputMismatchException ime) {
					System.out.println("Invalid quantiy...");
					scanner.nextLine(); //clears input stream
				} catch(Exception e) {
					System.out.println("Invalid quantiy...");
				}
			} 	else if(buyOrSell == false && itemIndex != -1) { //if item was found and sell was selected
				try {
					System.out.println("Enter valid quantity to sell: ");
					int sell = scanner.nextInt(); //quantity to sell
					if(sell > 0) { //quantity must be above 0
						if(inventory.get(itemIndex).updateItem(sell) == false) {//if item quantity wasn't successfully updates
							System.out.println("Invalid quantiy...");
							System.out.println("Error...could not buy item");
						} else {
							success = true; //item was successfully sold
						}
					}
				} catch(InputMismatchException ime) {
					System.out.println("Invalid quantiy...");
					scanner.nextLine(); //clears input stream
				} catch(Exception e) {
					System.out.println("Invalid quantiy...");
				}
			} else {
				System.out.println("Error...could not buy item");
			}
		} else {
			System.out.println("Error...could not buy item");
		}
		return success;
	}
	
	
	/**
	 * Reads values from file into the inventory arraylist
	 * @param scanner File Object
	 */
	public void readFromFile(Scanner scanner) {
		System.out.print("Enter name of file to read from: ");
		String fileName = String.format("C:\\CST8130 Eclipse Workspace\\Assignment2\\src\\%s", scanner.next());	//sets file path name to the source folder
		
		
		try {	//opens the file
			scanner = null;
			scanner = new Scanner(Paths.get(fileName));
			scanner.useDelimiter("\r\n");	//Carriage return and next Line
		} catch (FileNotFoundException fnfe) {
			System.out.println("File not found or file not accessible");
		} catch (NoSuchFileException nsfe) {
			System.out.println("No such file available");
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
		
		if(scanner != null) { //Checks if the file has been correctly opened
			try {
				while(scanner.hasNextLine()) {	//reads input from each line
					addItem(scanner, true);
				}
				
			} catch (InputMismatchException e) {
				System.out.println("Input mismatch exception while reading from file");
				scanner.nextLine();
			} catch (IllegalStateException e) {
				System.out.println("Scanner closed");
			} catch(Exception e) {
				e.printStackTrace();
				System.out.println("Exception while reading from file");
			}
			scanner.close(); //closes the file
		}
	}
	
	/**
	 * Saves values from inventory arraylist to a file
	 * @param scanner User Input
	 */
	public void saveToFile(Scanner scanner) {
		System.out.print("Enter the name of file to save to: ");
		String fileName = String.format("C:\\CST8130 Eclipse Workspace\\Assignment2\\src\\%s", scanner.next()); //filename
		Formatter output = null;	
		
		try {	//opens the file
			output = new Formatter(fileName);	//creates new output object;
			String fileType;
			for(int i = 0; i < numItems; i++) {
				if(inventory.get(i) instanceof Fruit) { //if item is a Fruit Food Item
					fileType = "f";
				} else if(inventory.get(i) instanceof Preserve) { //if item is a Preserve Food Item
					fileType = "p";
				} else { //if item is a Vegetable Food Item
					fileType = "v";
				}
				output.format("%s\r\n",fileType); //Carriage return and next Line
				inventory.get(i).outputItem(output);	//outputs the values in the arraylist
			}
		} catch (SecurityException se) {
			System.out.println("write permission denied...");
		}  catch (IOException ioe) {
			System.out.println("Error opening file");
		}
		output.close();//closes the flie
	}
	
	/**
	 * Sorts the food items in the inventory arraylist in ascending order using bubble sort algorithim
	 */
	private void sort() {
		for(int i = 0; i < numItems; i++) {
			for(int j = 0; j < numItems-i-1; j++) {
				if(inventory.get(j).compareTo(inventory.get(j+1)) > 0) {	//if current FoodItem item code is greater than the next FoodItem item code in the arraylist
					FoodItem temp = inventory.get(j); //stores the greater FoodItem in a temporary location
					inventory.remove(j); //removes the greater FoodItem from the arrayList
					inventory.add(j+1, temp);	//adds the greater foodItem behind the lower FoodItem
				}
			}
		}	
	}
	
	/**
	 * Searches for an item in the inventory
	 * @param scanner User Input
	 */
	public void searchForItem(Scanner scanner) {
		sort();	// sorts the inventory
		try{
			System.out.print("Enter the code for the item: ");
			int code = scanner.nextInt();
			int itemPosition = -1;	//position of number
			
			if(numItems != 0) {	//checks if inventory array list is empty
				int high = numItems-1;	//upper bound of inventory arraylist
				int low = 0;	//lower bound of inventory arraylist
				int middle = (low + high +1 ) /2; //middle bound of inventory arraylist
				
				do {	
					if(code == inventory.get(middle).getItemCode()) {	//if number is at the middle of the inventory arraylist
						itemPosition = middle;	//item is at middle
					} else if(code > inventory.get(middle).getItemCode()) {	//if current item code is greater than the middle item code
						low = middle + 1;	//new lower bound is above middle
					} else {	//if current item code is less than the middle item code
						high = middle - 1;	//new higher bound is below middle
					} 
					middle = (low + high +1 ) /2;	//sets the middle bound to a new bound depending on the higher and lower bounds
				}while(itemPosition == -1 && low <= high);	
				
			}
			if(itemPosition != -1) {	//if item code was not found
				inventory.get(itemPosition).toString();
				System.out.println("");
			}	else {
				System.out.println("Code not found in inventory...");
			}
		} catch(InputMismatchException ime) {
			System.out.println("Invalid input entered ...");
			scanner.nextLine(); //clears input stream
		} catch(Exception e) {
			System.out.println("Invalid input entered ...");
		}
		
	}
}
