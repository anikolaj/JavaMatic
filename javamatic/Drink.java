package javamatic;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

/**
 * This class contains the details for a menu item that can be serviced by the JavaMatic
 * machine.
 */
public class Drink {
	private String name;
	private Map<Ingredient, Integer> ingredients;
	private double cost;
	private boolean inStock;
	
	/**
	 * Constructor for a Drink object.
	 * @param name The name of the drink.
	 */
	public Drink(String name) {
		this.name = name;
		this.ingredients = new HashMap<Ingredient, Integer>();
		this.cost = 0;
	}
	
	/**
	 * Returns the name of the drink object.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Adds an ingredient to a drink recipe.
	 * @param ingredient the Ingredient used for the drink.
	 * @param units The amount of the ingredient needed to make the drink.
	 */
	public void addIngredientToDrink(Ingredient ingredient, int units) {
		ingredients.put(ingredient, units);
		cost += ingredient.getCost() * units;
	}
	
	/**
	 * Returns whether the drink has been marked in stock.
	 */
	public boolean getInStock() {
		return inStock;
	}
	
	/**
	 * This method allows the machine to set whether or not the drink can be served
	 * with its inventory.
	 * @param isInStock whether or there are sufficient amounts of the needed ingredients in the machine inventory.
	 */
	public void markInStock(boolean isInStock) {
		inStock = isInStock;
	}
	
	/**
	 * Returns the cost of the drink as string with a 2 decimal place representation.
	 */
	public String getCost() {
		return String.format("%.2f", cost);
	}
	
	/**
	 * Returns a set of the Ingredients used to make the drink.
	 */
	public Set<Ingredient> getIngredients() {
		return ingredients.keySet();
	}
	
	/**
	 * Returns how many units of an ingredient are needed to create this drink.
	 */
	public int getIngredientAmount(Ingredient ingredient) {
		return ingredients.get(ingredient);
	}
}
