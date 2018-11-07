package javamatic;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

/**
 * This class describes the JavaMatic machine, which consists of the
 * drinks it can dispense and the ingredients it uses.
 */
public class Machine {
	
	private HashMap<String, Integer> ingredientInventory;
	private HashMap<String, Double> ingredientCost;
	private List<Ingredient> ingredients;
	private List<Drink> drinks;
	
	private final int MAX_AMOUNT = 10;
	
	/**
	 * Default constructor for a machine.
	 */
	public Machine() {
		this.ingredientInventory = new HashMap<String, Integer>();
		this.ingredientCost = new HashMap<String, Double>();
		this.ingredients = new ArrayList<Ingredient>();
		this.drinks = new ArrayList<Drink>();
	}
	
	/**
	 * This method handles creating a new ingredient to be used by the machine.
	 * @param name is the name of the Ingredient.
	 * @param amount is cost of the ingredient.
	 * @return The Ingredient being added to this machine.
	 */
	public Ingredient addIngredient(String name, double amount) {
		ingredientInventory.put(name, MAX_AMOUNT);
		ingredientCost.put(name, amount);
		Ingredient newIngredient = new Ingredient(name, amount);
		ingredients.add(newIngredient);
		
		ingredients.sort((i1, i2) -> {
			if(i1.getName().compareTo(i2.getName()) > 0) {
				return 1;
			}
			else {
				return -1;
			}
		});
		
		return newIngredient;
	}
	
	/**
	 * Method handles adding new drink to machine
	 * @param new drink being serviced by the machine
	 */
	public void addDrink(Drink drink) {
		drinks.add(drink);
		drinks.sort((d1, d2) -> {
			if(d1.getName().compareTo(d2.getName()) > 0) {
				return 1;
			}
			else {
				return -1;
			}
		});
	}
	
	/**
	 * Retrieves the number of drink options available in the machine
	 * @return the number of drink options
	 */
	public int getNumberOfDrinks() {
		return drinks.size();
	}
	
	/**
	 * Retrieves an instance of a Drink object
	 * @param item the drink selection from the menu represent as an integer
	 * @return the Drink object
	 */
	public Drink dispenseDrink(int item) {
		return drinks.get(item - 1);
	}
	
	/**
	 * Restocks the inventory by setting each ingredients inventory amount to the max amount
	 */
	public void restock() {
		for(int i = 0; i < ingredients.size(); ++i) {
			ingredientInventory.put(ingredients.get(i).getName(), MAX_AMOUNT);
		}
	}
	
	/**
	 * Accepts Drink object and determines if the machine has a sufficient number of ingredients
	 * to dispense the drink
	 * @param drink is the Drink we wish to verify can be dispensed
	 */
	public void checkInStock(Drink drink) {
		for(Ingredient ingredient : drink.getIngredients()) {
			int amount = drink.getIngredientAmount(ingredient);
			if(amount > ingredientInventory.get(ingredient.getName())) {
				drink.markInStock(false);
				return;
			}
		}
		
		drink.markInStock(true);
		return;
	}
	
	/**
	 * Updates the machines inventory by removing the ingredient amounts for the drink
	 * @param drink The drink we need to use to update the amounts in the machine's inventory
	 */
	public void updateInventory(Drink drink) {
		for(Ingredient ingredient : drink.getIngredients()) {
			int unitsUsed = drink.getIngredientAmount(ingredient);
			int newAmount = ingredientInventory.get(ingredient.getName()) - unitsUsed;
			ingredientInventory.put(ingredient.getName(), newAmount);
		}
	}
	
	/**
	 * Outputs the current inventory status including the name of the ingredient and the amount of each
	 */
	public void printInventory() {
		System.out.println("Inventory:");
		for(Ingredient ing : ingredients) {
			System.out.println(ing.getName()+','+ingredientInventory.get(ing.getName()));
		}
	}
	
	/**
	 * Outputs the menu options including whether or not the machine contains sufficient amounts of ingredients
	 * to service the menu's amount of drinks
	 */
	public void printMenu() {
		System.out.println("Menu:");
		for(int i = 0; i < drinks.size(); ++i) {
			checkInStock(drinks.get(i));
			System.out.println(Integer.toString(i+1)+','+drinks.get(i).getName()+",$"+drinks.get(i).getCost()+','+drinks.get(i).getInStock());
		}
	}
}
