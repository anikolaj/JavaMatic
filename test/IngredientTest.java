package test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javamatic.Ingredient;

class IngredientTest {
	private static Ingredient ingredient;
	
	@BeforeEach
	void setUp() {
		ingredient = new Ingredient("Cocoa", 0.90);
	}

	@Test
	void testName() {
		assertEquals(ingredient.getName(), "Cocoa");
	}

	@Test
	void testCost() {
		assert(ingredient.getCost() == 0.90);
	}
}
