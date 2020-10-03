package roachmotel;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * The Abstract Room class for the Roach Motel.
 * 
 * @author Jerry Aragon
 * @author Jerry Belmonte
 * @date 04/24/2020
 */
public abstract class AbstractRoom {

	/** The motel room description. */
	protected String description = "Motel Room";
	/** The motel room's number. */
	private int roomNumber;
	/** The room amenities. */
	private Set<Amenity> amenities;

	/**
	 * Normal constructor for the abstract Room class.
	 * 
	 * @param description The description of the Motel room.
	 */
	public AbstractRoom(String description) {
		this.description = description;
		this.roomNumber = 0;
		this.amenities = new HashSet<>();
	}// End of the normal constructor.

	/**
	 * Gets the description of the Motel room.
	 * 
	 * @return The description.
	 */
	public String getDescription() {
		return this.description;
	}// End of the getDescription method

	/**
	 * Gets the room number of the Motel room.
	 * 
	 * @return The room number.
	 */
	public int getRoomNumber() {
		return this.roomNumber;
	}// End of the getRoomNumber method

	/**
	 * Sets the Motel room number.
	 * 
	 * @param roomNumber The number of the Motel room.
	 */
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}// End of the setRoomNumber method

	/**
	 * Gets the room's amenities.
	 * 
	 * @return The amenities.
	 */
	public Set<Amenity> getAmenities() {
		return this.amenities;
	}// End of the getAmenities method

	/**
	 * Adds an amenity to the Motel room.
	 * 
	 * @param newAmenity The new amenity being added.
	 */
	public void setAmenities(Amenity newAmenity) {
		if (!this.amenities.add(newAmenity)) {
			throw new IllegalArgumentException(
					"Error, you used the amenity " + newAmenity.name() + " twice.");
		}//end if
	}// End of the setAmenities method

	/**
	 * The cost of the motel room can vary depending on the type of room and the
	 * added amenities.
	 * 
	 * @return Total cost for the Motel room.
	 */
	public abstract double cost();

	/**
	 * Hash code function for the abstract room class.
	 * 
	 * @return Hash code for a Motel room.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(this.amenities, this.description, this.roomNumber);
	}// End of the hashCode method

	/**
	 * Checks if two Motel rooms are equal based on their attributes.
	 * 
	 * @param obj The object to compare against.
	 * @return True if the rooms have the same attributes, false otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}//end if
		if (this == obj) {
			return true;
		}//end if
		AbstractRoom other = (AbstractRoom) obj;
		return Objects.equals(this.amenities, other.amenities)
				&& Objects.equals(this.description, other.description)
				&& this.roomNumber == other.roomNumber;
	}// End of the equals method

	/**
	 * The motel description contains the room number, description of the room
	 * type, the list of amenities if any, and the room cost per day.
	 * 
	 * @return String description of the Motel room.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Room Number: " + this.roomNumber);
		sb.append(" " + this.getDescription());
		sb.append(" Cost: $" + this.cost());
		return sb.toString();
	}// End of the toString method

}// End of the Room class
