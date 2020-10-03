package roachmotel;

/**
 * The various amenities that a Motel guest can add when they book a Room.
 * 
 * @author Jerry Belmonte
 * @date 04/26/2020
 */
public enum Amenity {
	FOOD_BAR (10, "Food Bar"),
	AUTO_REFILL (5, "Auto Refill Food Bar"),
	SPA (20, "Spa"),
	SHOWER (25, "Spray Resistant Shower");

	/** The US dollar price for the amenity. */
	private int price;
	/** The description of the amenity. */
	private String description;

	/**
	 * The private constructor for the Amenity.
	 * 
	 * @param price       the price of the amenity.
	 * @param description A short explination of the amentity.
	 */
	private Amenity(int price, String description) {
		this.price = price;
		this.description = description;
	}// End of the private constructor.

	/**
	 * Gets the cost of the amenity.
	 * 
	 * @return An int representing the cost.
	 */
	public int getPrice() {
		return this.price;
	}// End of the getPrice method

	/**
	 * Gets the description of the amenity.
	 * 
	 * @return The string description.
	 */
	public String getDescription() {
		return this.description;
	}// End of the getDescription method

	/**
	 * Returns the description of the amenity and the price cost.
	 * 
	 * @return String with the amenity information.
	 */
	public String toString() {
		return this.getDescription() + ": $" + this.getPrice() + " per night";
	}// End of the toString method

}// End of the Amenities enumeration
