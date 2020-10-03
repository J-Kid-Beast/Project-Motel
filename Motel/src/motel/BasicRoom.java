package roachmotel;

/**
 * This class represents a basic room the Roach Motel offers.
 * 
 * @author Jerry Aragon
 * @author Jerry Belmonte
 * @date 05/01/2020
 */
public class BasicRoom extends AbstractRoom {

	/** The motel room type. */
	private Room basicRoom;

	/**
	 * Default constructor for the BasicRoom class.
	 */
	public BasicRoom() {
		super(Room.BASIC.toString());
		this.basicRoom = Room.BASIC;
	}// End of the default constructor.

	/**
	 * The cost method for the basic room in the Roach Motel.
	 * 
	 * @return The price for a basic room (starting at $49.99).
	 */
	@Override
	public double cost() {
		return this.basicRoom.getCost();
	}// End of the cost method

}// End of the BasicRoom class
