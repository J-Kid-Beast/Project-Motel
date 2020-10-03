package unittest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import roachmotel.Amenity;
import roachmotel.BasicRoom;
import roachmotel.DeluxeRoom;
import roachmotel.FoodBarDecorator;
import roachmotel.RoachMotel;
import roachmotel.RefillBarDecorator;
import roachmotel.RoachColony;
import roachmotel.AbstractRoom;
import roachmotel.RoomFactory;
import roachmotel.Room;
import roachmotel.ShowerDecorator;
import roachmotel.SpaDecorator;
import roachmotel.SuiteRoom;

/**
 * This class tests the factory design pattern for the Roach Motel.
 * 
 * @author Jerry Belmonte
 * @date 05/04/2020
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FactoryTester {
	static String newline = System.lineSeparator();
	static RoachMotel roachMotel = null;
	static String onlyMotelName = "The only motel in town";
	static int initialCapacity = 5;
	static RoachColony roaches = null;
	static String firstColonyName = "first colony";
	static int colonyPopulation = 100;
	static BasicRoom basicRoom = null;
	static DeluxeRoom deluxeRoom = null;
	static SuiteRoom suiteRoom = null;
	static Room basic = Room.BASIC;
	static Room deluxe = Room.DELUXE;
	static Room suite = Room.SUITE;
	static AbstractRoom tempRoom = null;
	static FoodBarDecorator food = null;
	static RefillBarDecorator refill = null;
	static SpaDecorator spaDecorator = null;
	static ShowerDecorator showerDecorator = null;
	static List<Amenity> list = Arrays.asList(Amenity.values());

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("Setting up class.");
		roachMotel = RoachMotel.getMotel(onlyMotelName, initialCapacity);
		System.out.println(roachMotel);
		roaches = new RoachColony(firstColonyName, colonyPopulation, 0, true);
		basicRoom = new BasicRoom();
		basicRoom.setRoomNumber(101);
		deluxeRoom = new DeluxeRoom();
		deluxeRoom.setRoomNumber(102);
		suiteRoom = new SuiteRoom();
		suiteRoom.setRoomNumber(103);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("Tearing down class.");
		basicRoom = null;
		deluxeRoom = null;
		suiteRoom = null;
		tempRoom = null;
		food = null;
		refill = null;
		spaDecorator = null;
		showerDecorator = null;
		list = null;
		roaches = null;
		System.out.println(roachMotel);
		roachMotel = null;
		System.out.println("Completed Satisfactorily");
	}

	@Test
	@Order(1)
	void testBasicRoomInstance() {
		assertEquals("Basic Room", basicRoom.getDescription());
		assertEquals(49.99, basicRoom.cost());
		System.out.println(basicRoom);
	}

	@Test
	@Order(2)
	void testDeluxeRoomInstance() {
		assertEquals("Deluxe Room", deluxeRoom.getDescription());
		assertEquals(99.99, deluxeRoom.cost());
		System.out.println(deluxeRoom);
	}

	@Test
	@Order(3)
	void testSuiteRoomInstance() {
		assertEquals("Suite Room", suiteRoom.getDescription());
		assertEquals(199.99, suiteRoom.cost());
		System.out.println(suiteRoom);
	}

	@Test
	@Order(4)
	void testCreateRoomFromFractory() {
		tempRoom = RoomFactory.createRoom(basic, null);
		assertEquals("Basic Room", tempRoom.getDescription());
		assertEquals(49.99, tempRoom.cost());

		tempRoom = RoomFactory.createRoom(deluxe, null);
		assertEquals("Deluxe Room", tempRoom.getDescription());
		assertEquals(99.99, tempRoom.cost());

		tempRoom = RoomFactory.createRoom(suite, null);
		assertEquals("Suite Room", tempRoom.getDescription());
		assertEquals(199.99, tempRoom.cost());
	}

	@Test
	@Order(5)
	void testFoodBarDecoratorInstance() {
		tempRoom = RoomFactory.createRoom(basic, null);
		food = new FoodBarDecorator(tempRoom);
		assertEquals("Basic Room, Food Bar", food.getDescription());
		System.out.println(food.getDescription() + " $" + food.cost());
	}

	@Test
	@Order(6)
	void testRefillBarDecoratorInstance() {
		tempRoom = RoomFactory.createRoom(basic, null);
		refill = new RefillBarDecorator(tempRoom);
		assertEquals("Basic Room, Auto Refill Food Bar", refill.getDescription());
		System.out.println(refill.getDescription() + " $" + refill.cost());
	}

	@Test
	@Order(7)
	void testSpaDecoratorInstance() {
		tempRoom = RoomFactory.createRoom(deluxe, null);
		spaDecorator = new SpaDecorator(tempRoom);
		assertEquals("Deluxe Room, Spa", spaDecorator.getDescription());
		System.out.println(spaDecorator.getDescription() + " $" + spaDecorator.cost());
	}

	@Test
	@Order(8)
	void testShowerDecoratorInstance() {
		tempRoom = RoomFactory.createRoom(suite, null);
		showerDecorator = new ShowerDecorator(tempRoom);
		assertEquals("Suite Room, Spray Resistant Shower", showerDecorator.getDescription());
		System.out.println(showerDecorator.getDescription() + " $" + showerDecorator.cost());
	}

	@Test
	@Order(9)
	void testDecorateBasicRoomFromFactory() {
		basicRoom.setRoomNumber(105);
		tempRoom = RoomFactory.decorateRoom(basicRoom, Amenity.FOOD_BAR);
		assertEquals("Basic Room, Food Bar", tempRoom.getDescription());
		System.out.println(tempRoom);

		tempRoom = RoomFactory.decorateRoom(tempRoom, Amenity.AUTO_REFILL);
		assertEquals("Basic Room, Food Bar, Auto Refill Food Bar", tempRoom.getDescription());
		System.out.println(tempRoom);

		tempRoom = RoomFactory.decorateRoom(tempRoom, Amenity.SPA);
		assertEquals("Basic Room, Food Bar, Auto Refill Food Bar, Spa", tempRoom.getDescription());
		System.out.println(tempRoom);

		tempRoom = RoomFactory.decorateRoom(tempRoom, Amenity.SHOWER);
		assertEquals("Basic Room, Food Bar, Auto Refill Food Bar, Spa, Spray Resistant Shower",
				tempRoom.getDescription());
		System.out.println(tempRoom);
	}

	@Test
	@Order(10)
	void testMotelIsNotFull() {
		System.out.println("Checking that motel is not full.");
		assertFalse(roachMotel.isFull());
		System.out.println("There is vacancy in the motel.");
	}

	@Test
	@Order(11)
	void testCheckInThreeRoomTypesWithFullAmenities() throws IOException {
		tempRoom = roachMotel.checkIn(roaches, basic, list);
		assertEquals("Basic Room, Food Bar, Auto Refill Food Bar, Spa, Spray Resistant Shower",
				tempRoom.getDescription());

		tempRoom = roachMotel.checkIn(roaches, deluxe, list);
		assertEquals("Deluxe Room, Food Bar, Auto Refill Food Bar, Spa, Spray Resistant Shower",
				tempRoom.getDescription());

		tempRoom = roachMotel.checkIn(roaches, suite, list);
		assertEquals("Suite Room, Food Bar, Auto Refill Food Bar, Spa, Spray Resistant Shower",
				tempRoom.getDescription());
	}

	@Test
	@Order(12)
	void testMotelNoVacancySignWorks() throws IOException {
		System.out.println("Checking that motel's no vacancy sign works.");

		tempRoom = roachMotel.checkIn(roaches, basic, Arrays.asList(Amenity.SPA, Amenity.FOOD_BAR));
		assertFalse(roachMotel.isFull());

		tempRoom = roachMotel.checkIn(roaches, deluxe, Arrays.asList(Amenity.SHOWER));
		assertTrue(roachMotel.isFull());

		tempRoom = roachMotel.checkIn(roaches, suite, Arrays.asList(Amenity.FOOD_BAR));
		assertTrue(roachMotel.isFull());

		System.out.println("There is 'No Vacancy' in the motel.");
	}

}// End of the FactoryTester class
