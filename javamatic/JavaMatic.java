package javamatic;
import java.util.Scanner;

/**
 * This class handles creating a JavaMatic machine
 * and running it for the provided drinks and
 * ingredients.
 */
public class JavaMatic {

	/**
	 * Main method for the program
	 * @param The command line arguments, not used for this project
	 */
	public static void main(String[] args) {
		// Create a new JavaMatic machine
		Machine machine = new Machine();
		
		// Feed machine the ingredients and drinks being serviced
		createMachine(machine);
		
		// Run the JavaMatic machine
		runMachine(machine);
	}
	
	/**
	 * Method handles inputting the drinks and ingredients for the
	 * JavaMatic machine.
	 * @param machine is the Machine object for which we will add drinks and ingredients
	 */
	private static void createMachine(Machine machine) {
		// INGREDIENT DEFINITIONS CONTAINED BELOW
		Ingredient coffee = machine.addIngredient("Coffee", 0.75);
		Ingredient decafCoffee = machine.addIngredient("Decaf Coffee", 0.75);
		Ingredient sugar = machine.addIngredient("Sugar", 0.25);
		Ingredient cream = machine.addIngredient("Cream", 0.25);
		Ingredient steamedMilk = machine.addIngredient("Steamed Milk", 0.35);
		Ingredient foamedMilk = machine.addIngredient("Foamed Milk", 0.35);
		Ingredient espresso = machine.addIngredient("Espresso", 1.10);
		Ingredient cocoa = machine.addIngredient("Cocoa", 0.90);
		Ingredient whippedCream = machine.addIngredient("Whipped Cream", 1.00);
		
		// DRINK DEFINITIONS CONTAINED BELOW
		// Coffee
		Drink coffeeDrink = new Drink("Coffee");
		coffeeDrink.addIngredientToDrink(coffee, 3);
		coffeeDrink.addIngredientToDrink(sugar, 1);
		coffeeDrink.addIngredientToDrink(cream, 1);
		machine.addDrink(coffeeDrink);
		
		// Decaf Coffee
		Drink decafcoffeeDrink = new Drink("Decaf Coffee");
		decafcoffeeDrink.addIngredientToDrink(decafCoffee, 3);
		decafcoffeeDrink.addIngredientToDrink(sugar, 1);
		decafcoffeeDrink.addIngredientToDrink(cream, 1);
		machine.addDrink(decafcoffeeDrink);
		
		// Caffe Latte
		Drink caffeLatte = new Drink("Caffe Latte");
		caffeLatte.addIngredientToDrink(espresso, 2);
		caffeLatte.addIngredientToDrink(steamedMilk, 1);
		machine.addDrink(caffeLatte);
		
		// Caffe Americano
		Drink caffeAmericano = new Drink("Caffe Americano");
		caffeAmericano.addIngredientToDrink(espresso, 3);
		machine.addDrink(caffeAmericano);
		
		// Caffe Mocha
		Drink caffeMocha = new Drink("Caffe Mocha");
		caffeMocha.addIngredientToDrink(espresso, 1);
		caffeMocha.addIngredientToDrink(cocoa, 1);
		caffeMocha.addIngredientToDrink(steamedMilk, 1);
		caffeMocha.addIngredientToDrink(whippedCream, 1);
		machine.addDrink(caffeMocha);
		
		// Cappuccino
		Drink cappuccino = new Drink("Cappuccino");
		cappuccino.addIngredientToDrink(espresso, 2);
		cappuccino.addIngredientToDrink(steamedMilk, 1);
		cappuccino.addIngredientToDrink(foamedMilk, 1);
		machine.addDrink(cappuccino);
	}
	
	/**
	 * Handles running the JavaMatic machine algorithm. This method is responsible 
	 * for handling user input and printing output
	 * @param machine is the Machine object with the drinks and ingredients
	 */
	private static void runMachine(Machine machine) {
		// should run the actual machine algorithm
		Scanner reader = new Scanner(System.in);
		boolean continueRunning = true;
		boolean printState = true;
		
		while(continueRunning == true) {
			
			if(printState == true) {
				// on start, output the inventory
				machine.printInventory();
				
				// print menu
				machine.printMenu();
			}
		
			// take user input
			String input = reader.nextLine();
		
			if(input.length() > 0) {
				if(input.toLowerCase().equals("r")) {
					// restock the inventory
					machine.restock();
				}
				else if(input.toLowerCase().equals("q")) {
					// exit the program
					continueRunning = false;
				}
				else {
					try {
						// convert to integer
						int selection = Integer.parseInt(input);
						printState = true;
						
						// make sure the drink selection is valid
						if(selection < 1 || selection > machine.getNumberOfDrinks()) {
							System.out.println("Invalid Selection: " + input);
						}
					
						else {
							Drink drinkSelection = machine.dispenseDrink(selection);
							
							// check if the drink is in stock
							if(drinkSelection.getInStock() == true) {
								// dispense the drink
								System.out.println("Dispensing: " + drinkSelection.getName());
								
								// update the inventory
								machine.updateInventory(drinkSelection);
							}
							else {
								System.out.println("Out of stock: " + drinkSelection.getName());
							}
						}
					}
					catch(NumberFormatException e) { 
						System.out.println("Invalid Selection: " + input);
					}
				}
			}
			else {
				printState = false;
			}
		}
		
		reader.close();
	}

}
