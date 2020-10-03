package roachmotel;

/**
 * RefillBar is a concrete RoomDecorator class, it adds an auto refill amenity
 * of the food bar to the Motel room. The auto refill food bar adds an extra $5
 * per night to the room cost.
 * 
 * @author Jerry Aragon
 * @author Jerry Belmonte
 * @date 05/01/2020
 */
public class RefillBarDecorator extends RoomDecorator {

	/** The auto refill food bar amenity. */
	private Amenity amenity;

	/**
	 * Normal constructor for the RefillBar class.
	 * 
	 * @param abstractRoom The room to decorate.
	 */
	public RefillBarDecorator(AbstractRoom abstractRoom) {
		super(abstractRoom);
		this.amenity = Amenity.AUTO_REFILL;
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

}// End of the RefillBar class
