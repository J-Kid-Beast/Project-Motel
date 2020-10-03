package roachmotel;

import java.util.Iterator;

/**
 * The abstract room decorator class. When the customer checks in, they can opt
 * for various room amenities.
 * 
 * @author Jerry Aragon
 * @author Jerry Belmonte
 * @date 04/24/2020
 */
public abstract class RoomDecorator extends AbstractRoom {

	/** The Motel room object wrapped by the decorator. */
	protected AbstractRoom abstractRoom;

	/**
	 * Normal constructor for the abstract RoomDecorator class.
	 * 
	 * @param abstractRoom The Motel room object being wrapped.
	 */
	public RoomDecorator(AbstractRoom abstractRoom) {
		super(abstractRoom.getDescription());
		this.abstractRoom = abstractRoom;
		// copy the room number from the room parameter
		super.setRoomNumber(abstractRoom.getRoomNumber());
		// grab the list of amenities from the parameter 'room'
		// and add them to the new room that will be created 
		Iterator<Amenity> iterator = abstractRoom.getAmenities().iterator();
		while (iterator.hasNext()) {
			super.setAmenities(iterator.next());
		}// end while
	}// End of the normal constructor.

	/**
	 * It is required that the room decorators reimplement the getDescription
	 * method. So that we first delegate to the room object we are decorating to
	 * get its description, then append the amenities description.
	 * 
	 * @return A describtion of the decorated room object.
	 */
	public abstract String getDescription();

}// End of the RoomDecorator class
