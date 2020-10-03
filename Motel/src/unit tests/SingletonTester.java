package unittest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.io.IOException;
import java.util.Arrays;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import roachmotel.Amenity;
import roachmotel.RoachMotel;
import roachmotel.RoachColony;
import roachmotel.AbstractRoom;
import roachmotel.Room;

/**
 * There will only be one roach motel. When you create the motel, you specify
 * the capacity which is the number of rooms and the name of the motel.
 * 
 * @author Jerry Belmonte
 * @date 04/30/2020
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SingletonTester {
	static RoachMotel roachMotel = null;
	static String onlyMotelName = "The only motel in town";
	static int initialCapacity = 5;
	static RoachColony firstRoaches = null;
	static String firstColonyName = "first colony";
	static int firstPopulation = 100;
	static AbstractRoom basicRoom = null;
	static RoachColony secondRoaches = null;
	static String secondColonyName = "second colony";
	static int secondPopulation = 200;
	static RoachColony findColony = null;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("Setting up class.");
		System.out.println("Calling the getMotel method for the first time.");
		roachMotel = RoachMotel.getMotel(onlyMotelName, initialCapacity);
		System.out.println(roachMotel);
		firstRoaches = new RoachColony();
		firstRoaches.setColonyName(firstColonyName);
		firstRoaches.setPopulation(firstPopulation);
		secondRoaches = new RoachColony();
		secondRoaches.setColonyName(secondColonyName);
		secondRoaches.setPopulation(secondPopulation);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("Tearing down class.");
		System.out.println(roachMotel);
		System.out.println("Completed Satisfactorily");
		roachMotel = null;
		firstRoaches = null;
		secondRoaches = null;
		basicRoom = null;
	}

	@Test
	@Order(1)
	void testGetMotel() {
		System.out.println("Calling the getMotel method with no parameters.");
		roachMotel = RoachMotel.getMotel();
		assertEquals("Motel: The only motel in town Room roster: Available rooms: [101, 102, 103, 104, 105]",
				roachMotel.toString());
		System.out.println(roachMotel);
	}

	@Test
	@Order(2)
	void testGetMotelWithParameters() {
		System.out.println("Calling the getMotel method with a different name and capacity.");
		roachMotel = RoachMotel.getMotel("Holiday Roach Inn", 3);
		assertNotEquals("Motel: Holiday Roach Inn Room roster: [101, 102, 103]",
				roachMotel.toString());
		System.out.println(roachMotel);
	}

	@Test
	@Order(3)
	void testMotelIsNotFull() {
		System.out.println("Checking that motel is not full.");
		assertFalse(roachMotel.isFull());
		System.out.println("There is vacancy in the motel.");
	}

	@Test
	@Order(4)
	void testCheckInBasicRoom() throws IOException {
		basicRoom = roachMotel.checkIn(firstRoaches, Room.BASIC, Arrays.asList(Amenity.FOOD_BAR));
		assertEquals("Basic Room, Food Bar", basicRoom.getDescription());
	}

	@Test
	@Order(5)
	void testCheckInSecondRoom() throws IOException {
		basicRoom = roachMotel.checkIn(secondRoaches, Room.BASIC, Arrays.asList(Amenity.FOOD_BAR));
		assertEquals(102, basicRoom.getRoomNumber());
	}

	@Test
	@Order(6)
	void testFindRoomFromColonyFirstColony() {
		basicRoom = RoachMotel.findRoomFromColony(firstRoaches);
		assertEquals("Room Number: 101 Basic Room, Food Bar Cost: $59.99", basicRoom.toString());
	}

	@Test
	@Order(7)
	void testFindColonyFromSecondRoom() {
		basicRoom = RoachMotel.findRoomFromColony(secondRoaches);
		findColony = RoachMotel.findColonyFromRoom(basicRoom);
		assertEquals("second colony 200", findColony.toString());
	}

}// End of the roachMotelTester class}
