package javamatic;
/**
 * This class contains the definition for an ingredient that is used in order
 * to service a drink.
 */
public class Ingredient {
	
	private String name;
	private double cost;
	
	/**
	 * Constructor for an ingredient.
	 * @param name is the name of the ingredient.
	 * @param cost is the price per unit of the ingredient.
	 */
	public Ingredient(String name, double cost) {
		this.name = name;
		this.cost = cost;
	}
	
	/**
	 * Returns the name of the ingredient.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the cost of the ingredient
	 */
	public double getCost() {
		return cost;
	}
}
