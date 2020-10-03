package roachmotel;

/**
 * The type of rooms that a guest can choose from when they check in.
 * 
 * @author Jerry Belmonte
 * @date 04/24/2020
 */
public enum Room {
	BASIC (50), DELUXE (75), SUITE (100);

	/** The US dollar cost for the motel room. */
	private double cost;

	/**
	 * The private constructor for the RoomType.
	 */
	private Room(double cost) {
		this.cost = cost;
	}// End of the private constructor.

	/**
	 * Gets the cost of the room type.
	 * 
	 * @return The room cost.
	 */
	public double getCost() {
		return this.cost;
	}// End of the getCost method

	/**
	 * @return String with the type of Motel room.
	 */
	public String toString() {
		return this.name().substring(0, 1) + this.name().substring(1).toLowerCase() + " Room";
	}// End of the toString method

}// End of the RoomType enumeration
