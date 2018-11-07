package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javamatic.Drink;
import javamatic.Ingredient;
import javamatic.Machine;

class MachineTest {

	private static Machine machine;
	
	@BeforeEach
	void setUp() {
		machine = new Machine();
		
		// Add the ingredients and drinks the machine can dispense
		// ingredients
		Ingredient coffee = machine.addIngredient("Coffee", 0.75);
		Ingredient decafCoffee = machine.addIngredient("Decaf Coffee", 0.75);
		Ingredient sugar = machine.addIngredient("Sugar", 0.25);
		Ingredient cream = machine.addIngredient("Cream", 0.25);
		
		// drinks
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
	}

	/**
	 * Tests that the machine has the correct number of drinks.
	 */
	@Test
	void testNumberOfDrinks() {
		assert(machine.getNumberOfDrinks() == 2);
	}

	/**
	 * Tests that the machine returns the correct drink for the user input.
	 */
	@Test
	void testDrinkSelection() {
		Drink item1 = machine.dispenseDrink(1);
		assertEquals(item1.getName(), "Coffee");
		
		Drink item2 = machine.dispenseDrink(2);
		assertEquals(item2.getName(), "Decaf Coffee");
	}
	
	/**
	 * Tests that the machine correctly reports whether or not the drink is in stock.
	 */
	@Test
	void testDrinkInStock() {
		Drink selection = machine.dispenseDrink(1);
		machine.updateInventory(selection);
		machine.checkInStock(selection);
		assert(selection.getInStock() == true);
		
		machine.updateInventory(selection);
		machine.checkInStock(selection);
		assert(selection.getInStock() == true);
		
		machine.updateInventory(selection);
		machine.checkInStock(selection);

		// verify now that we cannot purchase this drink
		selection = machine.dispenseDrink(1);
		assert(selection.getInStock() == false);
	}
	
	/**
	 * Tests that restock correctly resets the inventory amounts
	 */
	@Test
	void testRestock() {
		// Simulate drink purchases on the inventory
		Drink selection = machine.dispenseDrink(1);
		machine.updateInventory(selection);
		machine.updateInventory(selection);
		machine.updateInventory(selection);
		machine.checkInStock(selection);
		assert(selection.getInStock() == false);
		
		// restock and verify amount
		machine.restock();
		machine.checkInStock(selection);
		assert(selection.getInStock() == true);
	}
}
