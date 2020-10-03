package roachmotel;

/**
 * The suite room in the Roach Motel.
 * 
 * @author Jerry Aragon
 * @author Jerry Belmonte
 * @date 05/01/2020
 */
public class SuiteRoom extends AbstractRoom {

	/** The motel room type. */
	private Room suiteRoom;

	/**
	 * Default constructor for the SuiteRoom class.
	 */
	public SuiteRoom() {
		super(Room.SUITE.toString());
		this.suiteRoom = Room.SUITE;
	}// End of the default constructor.

	/**
	 * The cost method for the deluxe room in the Roach Motel.
	 * 
	 * @return The price for the deluxe room (starting at $199.99).
	 */
	@Override
	public double cost() {
		return this.suiteRoom.getCost();
	}// End of the cost method

}// End of the SuiteRoom class
