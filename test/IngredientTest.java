package test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javamatic.Ingredient;

class IngredientTest {
	private static Ingredient ingredient;
	
	/**
	 * Creates Ingredient object for each test case.
	 */
	@BeforeEach
	void setUp() {
		ingredient = new Ingredient("Cocoa", 0.90);
	}

	/**
	 * Tests the Ingredient name returns the correct value.
	 */
	@Test
	void testName() {
		assertEquals(ingredient.getName(), "Cocoa");
	}

	/**
	 * Tests the Ingredient cost returns the correct value.
	 */
	@Test
	void testCost() {
		assert(ingredient.getCost() == 0.90);
	}
}
