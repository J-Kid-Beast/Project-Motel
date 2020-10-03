package unittest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import roachmotel.Amenity;
import roachmotel.MasterRoach;
import roachmotel.RoachMotel;
import roachmotel.Payment;
import roachmotel.RoachColony;
import roachmotel.RoachPay;
import roachmotel.AbstractRoom;
import roachmotel.Room;

/**
 * This JUnit5 test case is used to test all of the functionalies and methods of
 * the Roach Motel, and the various design patterns of the console application.
 * 
 * @author Jerry Belmonte
 * @date 05/05/2020
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RoachMotelTester {

	static RoachMotel roachMotel = null;								// will reference the motel single instance
	static String motelName = "Jerry Motel Inn";			// custom motel name for this test case
	static String filName = "log.txt";						// filename for writing transactions
	static AbstractRoom tempRoom = null;							// temporary variable for a Room instance

	static String[] listOfNames = {							// dummy variable names for the customers
			"Cucarachas Uno",
			"Cucarachas Dos",
			"Cucarachas Tres",
			"Cucarachas Cuatro",
			"Cucarachas Cinco",
			"Cucarachas Seis",
			"Cucarachas Siete",
			"Cucarachas Ocho"
	};
	static RoachColony roaches = null;						// temporary variable for a RoachColony instance
	static RoachColony colonyOne = null;					// reference to the first roach colony
	static RoachColony colonyTwo = null;					// reference to the second coach colony
	static RoachColony colonyThree = null;					// reference to the third coach colony
	static RoachColony colonyFour = null;					// reference to the fourth coach colony
	static RoachColony colonyFive = null;					// reference to the fifth coach colony
	static RoachColony colonySix = null;					// reference to the sixth coach colony
	static RoachColony colonySeven = null;					// reference to the seventh coach colony
	static RoachColony colonyEight = null;					// reference to the eight coach colony

	static List<Amenity> listOfAmenities = Arrays.asList(Amenity.values());
	static double fullAmenitiesCost = 0.0;
	static double delta = 0.2;								// delta correctional value for double assertions
	static Payment payment = null;							// holds a reference to a concrete payment object
	static String record = null;							// temporary variable for a record transaction

	/**
	 * Sets up the Roach Motel instance with the given motel name and sets the
	 * file for logging transactions. It also instantiates the roach colony
	 * objects to be used for the various test methods and assertions.
	 * 
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		RoachMotel.setFile(filName);
		roachMotel = RoachMotel.getMotel(motelName, 5);

		colonyOne = new RoachColony(listOfNames[0], 100, 1.1, true);
		colonyTwo = new RoachColony(listOfNames[1], 200, 2.0, false);
		colonyThree = new RoachColony(listOfNames[2], 300, 1.3, true);
		colonyFour = new RoachColony(listOfNames[3], 400, 2.4, true);
		colonyFive = new RoachColony(listOfNames[4], 500, 1.5, false);
		colonySix = new RoachColony(listOfNames[5], 600, 2.6, true);
		colonySeven = new RoachColony(listOfNames[6], 700, 1.7, false);
		colonyEight = new RoachColony(listOfNames[7], 800, 2.8, true);

		for (Amenity element : Amenity.values()) {
			fullAmenitiesCost += element.getPrice();
		}
	}//end of setUpBeforeClass

	/**
	 * Upon tearing down the application, the transaction log is dumped into the
	 * console output to display the transaction records written during runtime.
	 * 
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		RoachMotel.displayTransactionLog();
		roachMotel = null;
	}//end of tearDownAfterClass

	/**
	 * Displays the Roach Motel's status to the console output before a test.
	 * 
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		System.out.println(roachMotel);
	}//end of setUp

	/**
	 * Displays the Roach Motel's status to the console output after the test.
	 * 
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		//System.out.println(motel);
	}//end of tearDown

	/**
	 * Test method for the Roach Motel's overloaded getMotel static method that
	 * declares a different motel name and room capacity.
	 * {@link roachmotel.RoachMotel#getMotel(java.lang.String, int)}.
	 */
	@DisplayName("Tests the getMotel method with a different name and capacity")
	@Test
	@Order(1)
	void testGetMotelWithDifferentParameters() {
		roachMotel = RoachMotel.getMotel("Another name", 2);
		assertNotEquals("Motel: Another name Room roster: [101, 102]", roachMotel.toString());
	}// end of testGetMotelWithDifferentParameters

	/**
	 * Test method for the unique instance of the Roach Motel. Asserts that the
	 * motel object is the one and only motel in town.
	 * {@link roachmotel.RoachMotel#getMotel()}.
	 */
	@DisplayName("Tests the getMotel method with no parameters")
	@Test
	@Order(2)
	void testGetMotelUniqueInstance() {
		roachMotel = null;
		assertNull(roachMotel);
		roachMotel = RoachMotel.getMotel();
		assertSame(roachMotel, RoachMotel.getMotel(), "Motel is not a singleton!");
		assertEquals(
				"Motel: Jerry Motel Inn Room roster: Available rooms: [101, 102, 103, 104, 105]",
				roachMotel.toString());
	}// end of testGetMotelUniqueInstance

	/**
	 * Test method for vacancy status when the Roach Motel is first initialized.
	 * If there are available rooms it should display 'Vacancy'; but if all
	 * rooms are occupied, it should display 'No Vacancy'.
	 * {@link roachmotel.RoachMotel#isFull()}.
	 */
	@DisplayName("Tests that the motel is not full")
	@Test
	@Order(3)
	void testMotelIsNotFullWhenInitialized() {
		assertFalse(roachMotel.isFull());
	}// end of testMotelIsNotFullWhenInitialized

	/**
	 * Test method for the Motel's static method that dumps the transaction log.
	 * If the file path is not set properly, it should throw an exception.
	 * {@link roachmotel.RoachMotel#displayTransactionLog()}.
	 */
	@DisplayName("Tests the Motel's displayTransactionLog method")
	@Test
	@Order(4)
	void testDisplayTransactionLog() {
		RoachMotel.setFile("not valid a file name");
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> RoachMotel.displayTransactionLog());
		assertEquals("File is not in the specified location!", e.getMessage());
		RoachMotel.setFile(filName);
	}// end of testDisplayTransactionLog

	/**
	 * Test method for checking into the Roach Motel with full amenities at
	 * various room tier levels. Tests the different room cost with full added
	 * amenities.
	 * {@link roachmotel.RoachMotel#checkIn(roachmotel.RoachColony, roachmotel.Room, java.util.List)}.
	 * 
	 * @throws IOException
	 */
	@DisplayName("Tests the Motel's checkIn method with full amenities")
	@Test
	@Order(5)
	void testCheckInWithFullAmenities() throws IOException {
		double totalRoomCost = Room.BASIC.getCost() + fullAmenitiesCost;
		tempRoom = roachMotel.checkIn(colonyOne, Room.BASIC, listOfAmenities);
		assertEquals(101, tempRoom.getRoomNumber());
		assertEquals(totalRoomCost, tempRoom.cost(), delta);
		assertEquals("Basic Room, Food Bar, Auto Refill Food Bar, Spa, Spray Resistant Shower",
				tempRoom.getDescription());

		totalRoomCost = Room.DELUXE.getCost() + fullAmenitiesCost;
		tempRoom = roachMotel.checkIn(colonyTwo, Room.DELUXE, listOfAmenities);
		assertEquals(102, tempRoom.getRoomNumber());
		assertEquals(totalRoomCost, tempRoom.cost(), delta);
		assertEquals("Deluxe Room, Food Bar, Auto Refill Food Bar, Spa, Spray Resistant Shower",
				tempRoom.getDescription());

		totalRoomCost = Room.SUITE.getCost() + fullAmenitiesCost;
		tempRoom = roachMotel.checkIn(colonyThree, Room.SUITE, listOfAmenities);
		assertEquals(103, tempRoom.getRoomNumber());
		assertEquals(totalRoomCost, tempRoom.cost(), delta);
		assertEquals("Suite Room, Food Bar, Auto Refill Food Bar, Spa, Spray Resistant Shower",
				tempRoom.getDescription());

		Exception e = assertThrows(IllegalArgumentException.class,
				() -> roachMotel.checkIn(null, null, null));
		assertEquals("RoachColony cannot be null!", e.getMessage());

		e = assertThrows(IllegalArgumentException.class,
				() -> roachMotel.checkIn(colonyEight, null, null));
		assertEquals("Amenities list is null!", e.getMessage());
	}// end of testCheckInWithFullAmenities

	/**
	 * Test method for the Motel's checkIn method not having any vacancy for new
	 * customers when all the rooms are occupied.
	 * {@link roachmotel.RoachMotel#isFull()}.
	 * 
	 * @throws IOException
	 */
	@DisplayName("Tests that the motel is full after 5 customers check in")
	@Test
	@Order(6)
	void testMotelIsFullAfterCheckIn() throws IOException {
		assertFalse(roachMotel.isFull());
		roachMotel.checkIn(colonyFour, Room.BASIC, Arrays.asList(Amenity.FOOD_BAR));
		assertFalse(roachMotel.isFull());
		roachMotel.checkIn(colonyFive, Room.DELUXE, Arrays.asList(Amenity.SHOWER));
		assertTrue(roachMotel.isFull());
		roachMotel.checkIn(colonySix, Room.SUITE, Arrays.asList(Amenity.SPA));
		assertTrue(roachMotel.isFull());
		roachMotel.checkIn(colonySeven, Room.BASIC, Arrays.asList(Amenity.AUTO_REFILL));
		assertTrue(roachMotel.isFull());
	}// end of testMotelIsFullAfterCheckIn

	/**
	 * Test method for the RoachColony objects that check into the Roach Motel
	 * as customers. Tests for membership by checking that equality assertions
	 * remain true after their population chges, due to the runtime change
	 * depending on their growth rate (when invoking their party method).
	 * {@link roachmotel.RoachColony#RoachColony(java.lang.String,int,double)}.
	 */
	@DisplayName("Tests the growth rate for the RoachColony class")
	@Test
	@Order(7)
	void testRoachColonyClassMethods() {
		roaches = new RoachColony(listOfNames[0], 100, colonyOne.getGrowthRate(), true);
		assertEquals(colonyOne, roaches);
		roaches.setPopulation(50);
		assertEquals(colonyOne, roaches);
		roaches.setGrowthRate(0);
		assertEquals(colonyOne, roaches);

		roaches = new RoachColony(listOfNames[1], 200, colonyTwo.getGrowthRate(), true);
		assertEquals(colonyTwo, roaches);
		roaches.setColonyName(listOfNames[2]);
		assertNotEquals(colonyTwo, roaches);

		roaches = new RoachColony(listOfNames[2], 300, colonyThree.getGrowthRate(), true);
		roaches.setColonyName(listOfNames[4]);
		assertEquals(listOfNames[4], roaches.getColonyName());
		roaches.setGrowthRate(0.8);
		assertEquals(0.8, roaches.getGrowthRate(), delta);
		roaches.setPopulation(500);
		assertEquals(500, roaches.getPopulation());
	}// end of testRoachColonyClassMethods

	/**
	 * Test method for the Motel staff spraying a room, when a roach colony
	 * decides to throw a party in their room. Compares the population amount
	 * before a party and after spraying the room.
	 * {@link roachmotel.RoachMotel#sprayRoom(roachmotel.RoachColony)}.
	 */
	@DisplayName("Tests the Motel's sprayRoom method")
	@Test
	@Order(8)
	void testSprayRoomWhenRoachesPartyFirstFourRooms() {
		int firstPopulation = colonyOne.getPopulation();
		colonyOne.party();
		assertNotEquals(firstPopulation, colonyOne.getPopulation());

		int secondPopulation = ((int) (colonyTwo.getPopulation() * colonyTwo.getGrowthRate()));
		colonyTwo.party();
		assertNotEquals(secondPopulation, colonyTwo.getPopulation());

		int populationBeforeParty = colonyThree.getPopulation();
		double colonyGrowthRate = colonyThree.getGrowthRate();
		tempRoom = RoachMotel.findRoomFromColony(colonyThree);
		Set<Amenity> roomAmenities = tempRoom.getAmenities();
		assertTrue(roomAmenities.contains(Amenity.SHOWER));

		double reduceRate = 0.25;
		colonyThree.party();
		int populationAfterParty = ((int) (populationBeforeParty * colonyGrowthRate));
		double reduceAmount = populationAfterParty * reduceRate;
		int result = ((int) (populationAfterParty - reduceAmount));
		assertEquals(result, colonyThree.getPopulation());

		populationBeforeParty = colonyFour.getPopulation();
		colonyGrowthRate = colonyFour.getGrowthRate();
		tempRoom = RoachMotel.findRoomFromColony(colonyFour);
		roomAmenities = tempRoom.getAmenities();
		assertFalse(roomAmenities.contains(Amenity.SHOWER));

		reduceRate = 0.50;
		colonyFour.party();
		populationAfterParty = ((int) (populationBeforeParty * colonyGrowthRate));
		reduceAmount = populationAfterParty * reduceRate;
		result = ((int) (populationAfterParty - reduceAmount));
		assertEquals(result, colonyFour.getPopulation());
	}// end of testSprayRoomWhenRoachesPartyFirstFourRooms

	/**
	 * Test method for the Motel check out method. Asserts the runtime
	 * functionalities of the checkout method when customers check out.
	 * {@link roachmotel.RoachMotel#checkOut(roachmotel.AbstractRoom, int, java.lang.String)}.
	 * 
	 * @throws IOException
	 */
	@DisplayName("Test the Motel's checkOut method")
	@Test
	@Order(9)
	void testMotelCheckOutMethod() throws IOException {
		tempRoom = RoachMotel.findRoomFromColony(colonyOne);
		int numberOfDays = 3;
		double roomCost = tempRoom.cost();
		double amount = roachMotel.checkOut(tempRoom, numberOfDays, listOfNames[0]);
		double result = roomCost * numberOfDays;
		assertEquals(result, amount);

		tempRoom = RoachMotel.findRoomFromColony(colonyTwo);
		roomCost = tempRoom.cost();
		amount = roachMotel.checkOut(tempRoom, numberOfDays, listOfNames[1]);
		result = roomCost * numberOfDays;
		assertEquals(result, amount);

		tempRoom = RoachMotel.findRoomFromColony(colonyThree);
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> roachMotel.checkOut(tempRoom, 0, listOfNames[0]));
		assertEquals("Customer information does not match!", e.getMessage());

		e = assertThrows(NullPointerException.class,
				() -> roachMotel.checkOut(tempRoom, 0, null));
	}// end of testMotelCheckOutMethod

	/**
	 * Test method for records returned from the pay method that is implemented
	 * by the MasterRoach class and the RoachPay class.
	 * {@link roachmotel.RoachMotel#transactionLog(java.lang.String)}.
	 * 
	 * @throws IOException
	 */
	@DisplayName("Tests the payment records for the transaction log")
	@Test
	@Order(10)
	void testPayWithMasterRoachOrRoachPay() throws IOException {
		Payment masterRoach = new MasterRoach(listOfNames[0], "5678", "123", "05/2021");
		Payment roachPay = new RoachPay(listOfNames[1], "name@mail.com");

		record = masterRoach.pay(100.0);
		assertEquals(
				"$100.0 paid using MasterRoach card - name: Cucarachas Uno Number ending in: 5678 cvv: 123 Expiration Date: 05/2021",
				record);

		record = roachPay.pay(100.0);
		assertEquals("$100.0 paid using RoachPay - name: Cucarachas Dos Email: name@mail.com",
				record);

		Exception e = assertThrows(IllegalArgumentException.class,
				() -> roachMotel.pay(null, 0, null));
		assertEquals("Payment cannot be null!", e.getMessage());
	}// end of testPayWithMasterRoachOrRoachPay

	/**
	 * Test method for the static method in the Motel class that finds a motel
	 * room from a given RoachColony object.
	 * {@link roachmotel.RoachMotel#findRoomFromColony(roachmotel.RoachColony)}.
	 */
	@DisplayName("Tests the static method find room from colony")
	@Test
	@Order(11)
	void testFindRoomFromColony() {
		roaches = colonyFive;
		tempRoom = RoachMotel.findRoomFromColony(roaches);
		assertNotNull(tempRoom);

		roaches = colonyEight;
		tempRoom = RoachMotel.findRoomFromColony(roaches);
		assertNull(tempRoom);

		Exception e = assertThrows(IllegalArgumentException.class,
				() -> RoachMotel.findRoomFromColony(null));
		assertEquals("RoachColony argument is null!", e.getMessage());
	}// end of testFindRoomFromColony

	/**
	 * Test method for the static method in the Motel class that finds a roach
	 * colony from a given room object parameter.
	 * {@link roachmotel.RoachMotel#findColonyFromRoom(roachmotel.AbstractRoom)}.
	 */
	@DisplayName("Tests the static method find colony from room")
	@Test
	@Order(12)
	void testFindColonyFromRoom() {
		tempRoom = RoachMotel.findRoomFromColony(colonyTwo);
		roaches = RoachMotel.findColonyFromRoom(tempRoom);

		assertEquals(listOfNames[1], roaches.getColonyName());
		assertEquals(colonyTwo.getGrowthRate(), roaches.getGrowthRate(), delta);
		assertEquals(colonyTwo.getPopulation(), roaches.getPopulation());

		tempRoom = RoachMotel.findRoomFromColony(colonyEight);
		assertNull(tempRoom);

		Exception e = assertThrows(IllegalArgumentException.class,
				() -> RoachMotel.findColonyFromRoom(null));
		assertEquals("Room argument is null!", e.getMessage());
	}// end of testFindColonyFromRoom

}// End of the RoachMotelTester class
