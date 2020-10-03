package unittest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import roachmotel.Amenity;
import roachmotel.BasicRoom;
import roachmotel.FoodBarDecorator;
import roachmotel.RoachMotel;
import roachmotel.RoachColony;
import roachmotel.AbstractRoom;
import roachmotel.Room;

/**
 * 
 * @author Jerry Belmonte
 * @date 05/02/2020
 */
class DecoratorTester {
	static String newline = System.lineSeparator();
	static RoachMotel roachMotel = null;
	static String onlyMotelName = "The only motel in town";
	static int initialCapacity = 5;
	static RoachColony roaches = null;
	static String firstColonyName = "first colony";
	static int colonyPopulation = 100;
	static AbstractRoom basicRoom = null;
	static int roomNumber = 101;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		roachMotel = RoachMotel.getMotel(onlyMotelName, initialCapacity);
		System.out.println(roachMotel);
		roaches = new RoachColony(firstColonyName, colonyPopulation, 0, true);
		basicRoom = new FoodBarDecorator(new BasicRoom());
		basicRoom.setRoomNumber(roomNumber);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		basicRoom = null;
		roaches = null;
		roachMotel = null;
		System.out.println("Completed Satisfactorily");
	}

	/**
	 * Test method for
	 * {@link roachmotel.RoomDecorator#getDescription()}.
	 */
	@Test
	void testGetDescription() {
		System.out.print("testGetDescription: ");
		assertEquals("Basic Room, Food Bar", basicRoom.getDescription(),
				"Description does not match!");
		System.out.print(basicRoom.getDescription() + newline);
	}

	/**
	 * Test method for {@link roachmotel.RoomDecorator#RoomDecorator()}.
	 */
	@Test
	void testRoomDecorator() {
		System.out.print("testRoomDecorator: ");
		assertEquals("Basic Room, Food Bar", basicRoom.getDescription());
		System.out.print(basicRoom.getDescription() + newline);
	}

	/**
	 * Test method for {@link roachmotel.AbstractRoom#Room()}.
	 */
	@Test
	void testRoom() {
		System.out.print("testRoom: ");
		assertEquals("Basic Room, Food Bar",
				basicRoom.getDescription());
		System.out.print(basicRoom.toString() + newline);
	}

	/**
	 * Test method for {@link roachmotel.AbstractRoom#getRoomNumber()}.
	 */
	@Test
	void testGetRoomNumber() {
		System.out.print("testGetRoomNumber: ");
		assertEquals(101, basicRoom.getRoomNumber(), "Room numbers do not match!");
		System.out.print(basicRoom.getRoomNumber() + newline);
	}

	/**
	 * Test method for {@link roachmotel.AbstractRoom#setRoomNumber(int)}.
	 */
	@Test
	void testSetRoomNumber() {
		System.out.print("testSetRoomNumber: ");
		basicRoom.setRoomNumber(105);
		assertEquals(105, basicRoom.getRoomNumber(), "Room numbers do not match!");;
		System.out.print(basicRoom.getRoomNumber() + newline);
	}

	/**
	 * Test method for {@link roachmotel.AbstractRoom#getAmenities()}.
	 */
	@Test
	void testGetAmenities() {
		System.out.print("testGetAmenities: ");
		Iterator<Amenity> listIterator = basicRoom.getAmenities().iterator();
		Amenity result = listIterator.next();
		assertEquals(Amenity.FOOD_BAR, result, "Amenities do not match!");
		System.out.print(result + newline);
	}

	/**
	 * Test method for
	 * {@link roachmotel.AbstractRoom#setAmenities(roachmotel.Amenity)}.
	 */
	@Test
	void testSetAmenities() {
		System.out.print("testSetAmenities: ");
		basicRoom.setAmenities(Amenity.SPA);
		Iterator<Amenity> listIterator = basicRoom.getAmenities().iterator();
		Amenity result = listIterator.next();
		assertEquals(Amenity.FOOD_BAR, result, "Amenities do not match!");
		result = listIterator.next();
		assertEquals(Amenity.SPA, result, "Amenities do not match!");
		System.out.print(result + newline);
	}

	/**
	 * Test method for {@link roachmotel.AbstractRoom#cost()}.
	 */
	@Test
	void testCost() {
		System.out.print("testCost: ");
		assertEquals(59.99, basicRoom.cost(), "The costs do not match!");
		System.out.print(basicRoom.cost() + newline);
	}

	/**
	 * Test method for {@link roachmotel.RoachMotel#checkIn()}.
	 * 
	 * @throws IOException
	 */
	@Test
	void testCheckIn() throws IOException {
		assertEquals("Basic Room, Food Bar", roachMotel.checkIn(roaches, Room.BASIC, Arrays.asList(
				Amenity.FOOD_BAR)).getDescription());
	}

}// End of the DecoratorTester class}
