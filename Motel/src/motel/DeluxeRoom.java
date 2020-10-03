package roachmotel;

/**
 * This class represents a deluxe room the Roach Motel offers.
 * 
 * @author Jerry Aragon
 * @author Jerry Belmonte
 * @date 05/01/2020
 */
public class DeluxeRoom extends AbstractRoom {

	/** The motel room type. */
	private Room deluxeRoom;

	/**
	 * Default constructor for the DeluxeRoom class.
	 */
	public DeluxeRoom() {
		super(Room.DELUXE.toString());
		this.deluxeRoom = Room.DELUXE;
	}// End of the default constructor.

	/**
	 * The cost method for the deluxe room in the Roach Motel.
	 * 
	 * @return The price for the deluxe room (starting at $99.99).
	 */
	@Override
	public double cost() {
		return this.deluxeRoom.getCost();
	}// End of the cost method

}// End of the DeluxeRoom class
