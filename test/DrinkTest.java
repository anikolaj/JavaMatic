package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javamatic.Drink;
import javamatic.Ingredient;

import static org.junit.Assert.assertEquals;

class DrinkTest {
	private static Drink drink;
	
	@BeforeEach
	/**
	 * Create new drink object before every test case.
	 */
	public void setUp() {
		drink = new Drink("Coffee");
		drink.markInStock(true);
	}

	/**
	 * Tests whether the drink name is being properly set.
	 */
	@Test
	public void testName() {
		assertEquals(drink.getName(), "Coffee");
	}
	
	/**
	 * Tests whether the drink is marked either in stock or not.
	 */
	@Test
	public void testInStock() {
		assertEquals(drink.getInStock(), true);
	}
	
	/**
	 * Tests that the drink correctly calculates its cost.
	 */
	@Test
	public void testCost() {
		Ingredient ing1 = new Ingredient("ing1", 1.00);
		Ingredient ing2 = new Ingredient("ing2", 2.00);
		Ingredient ing3 = new Ingredient("ing3", 3.65);
		
		drink.addIngredientToDrink(ing1, 2);
		drink.addIngredientToDrink(ing2, 4);
		drink.addIngredientToDrink(ing3, 6);
		
		assertEquals(drink.getCost(), "31.90");
	}
	
	/**
	 * Tests the drink correctly returns the number of units needed for an ingredient.
	 */
	@Test
	public void testIngredientAmount() {
		Ingredient ing = new Ingredient("ing", 3.45);
		drink.addIngredientToDrink(ing, 679);
		
		assertEquals(drink.getIngredientAmount(ing), 679);
	}
}
