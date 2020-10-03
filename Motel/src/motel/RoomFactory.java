package roachmotel;

import java.util.List;

/**
 * The factory class for rooms in the Roach Motel.
 * 
 * @author Jerry Belmonte
 * @date 04/24/2020
 */
public class RoomFactory {

	/**
	 * Creates a room for the roach motel.
	 * 
	 * @param type      The type of room.
	 * @param amenities The list of amenities. If null, does not decorate room.
	 * @return The motel room.
	 */
	public static AbstractRoom createRoom(Room type, List<Amenity> amenities) {
		AbstractRoom newRoom = null;

		switch(type) {
			case BASIC:
				newRoom = new BasicRoom();
				break;
			case DELUXE:
				newRoom = new DeluxeRoom();
				break;
			case SUITE:
				newRoom = new SuiteRoom();
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + type);
		}//end switch

		if (!amenities.isEmpty()) {
			for (Amenity next : amenities) {
				newRoom = RoomFactory.decorateRoom(newRoom, next);
			}//end for loop
		}//end if

		return newRoom;
	}// End of the createRoom method

	/**
	 * Decorates a room with motel amenities.
	 * 
	 * @param abstractRoom    The room being decorated.
	 * @param amenity The amenity to wrap the room.
	 * @return The decorated room.
	 */
	public static AbstractRoom decorateRoom(AbstractRoom abstractRoom, Amenity amenity) {
		AbstractRoom decoratedRoom = null;

		switch(amenity) {
			case FOOD_BAR:
				decoratedRoom = new FoodBarDecorator(abstractRoom);
				break;
			case AUTO_REFILL:
				decoratedRoom = new RefillBarDecorator(abstractRoom);
				break;
			case SPA:
				decoratedRoom = new SpaDecorator(abstractRoom);
				break;
			case SHOWER:
				decoratedRoom = new ShowerDecorator(abstractRoom);
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + amenity);
		}//end switch

		return decoratedRoom;
	}// End of the decorateRoom method

}// End of the RoomFactory class
