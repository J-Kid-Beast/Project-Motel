package roachmotel;

/**
 * Shower is a concrete RoomDecorator class, it adds a spray resistant shower
 * amenity to the Motel room. The spray resistant shower adds an extra $25 per
 * night to the room cost.
 * 
 * @author Jerry Aragon
 * @author Jerry Belmonte
 * @date 04/24/2020
 */
public class ShowerDecorator extends RoomDecorator {

	/** The spray resistant shower amenity. */
	private Amenity amenity;

	/**
	 * Normal constructor for the Shower class.
	 * 
	 * @param abstractRoom The room to decorate.
	 */
	public ShowerDecorator(AbstractRoom abstractRoom) {
		super(abstractRoom);
		this.amenity = Amenity.SHOWER;
		super.setAmenities(this.amenity);// throws exception for duplicate amenities
	}// End of the normal constructor.

	/**
	 * Gets a description of the Motel room with the appended amenities.
	 * 
	 * @return Description of the Motel room.
	 */
	@Override
	public String getDescription() {
		return super.description + ", " + this.amenity.getDescription();
	}// End of the getDescription method

	/**
	 * Computes the cost of the Motel room with the extra amenities. It
	 * delegates to the room object being decorated first so that it can
	 * compute its cost and then add in the cost of the amenity.
	 * 
	 * @return The cost of the room and the amenities.
	 */
	@Override
	public double cost() {
		return this.amenity.getPrice() + super.abstractRoom.cost();
	}// End of the cost method

}// End of the Shower class
