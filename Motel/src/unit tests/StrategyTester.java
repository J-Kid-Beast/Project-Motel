package unittest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.util.Arrays;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
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
 * This class tests the strategy design pattern for the Roach Motel project.
 * 
 * @author Jerry Belmonte
 * @date 05/02/2020
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StrategyTester {
	static RoachMotel roachMotel = null;
	static String onlyMotelName = "The only motel in town";
	static int initialCapacity = 5;
	static RoachColony colonyOne = null;
	static String firstColonyName = "first colony";
	static int firstPopulation = 100;
	static RoachColony colonyTwo = null;
	static String secondColonyName = "second colony";
	static int secondPopulation = 200;
	static AbstractRoom basicRoom = null;
	static AbstractRoom deluxeRoom = null;
	static MasterRoach masterRoach = null;
	static RoachPay roachPay = null;
	static Payment payment = null;
	static double hundredDollars = 100.0;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("Setting up.");
		roachMotel = RoachMotel.getMotel(onlyMotelName, initialCapacity);
		System.out.println(roachMotel);
		colonyOne = new RoachColony(firstColonyName, firstPopulation, 0, true);
		colonyTwo = new RoachColony(secondColonyName, secondPopulation, 0, false);
		System.out.println(colonyOne);
		System.out.println(colonyTwo);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("Tearing down.");
		System.out.println(roachMotel);
		basicRoom = null;
		deluxeRoom = null;
		colonyOne = null;
		colonyTwo = null;
		roachMotel = null;
		System.out.println("Completed Satisfactorily");
	}

	/**
	 * Test method for
	 * {@link roachmotel.MasterRoach#MasterRoach(java.lang.String,java.lang.String,java.lang.String,java.lang.String)}.
	 */
	@Test
	@Order(1)
	void testMasterRoachClass() {
		System.out.println("Testing MasterRoach class.");
		masterRoach = new MasterRoach("roach", "9999", "123", "05/2020");
		assertEquals(
				"$0.0 paid using MasterRoach card - name: roach Number ending in: 9999 cvv: 123 Expiration Date: 05/2020",
				masterRoach.pay(0));
		System.out.println(masterRoach.pay(0));
	}

	/**
	 * Test method for
	 * {@link roachmotel.RoachPay#RoachPay(java.lang.String,java.lang.String)}.
	 */
	@Test
	@Order(2)
	void testRoachPayClass() {
		System.out.println("Testing RoachPay class.");
		roachPay = new RoachPay("roach", "name@mail.com");
		assertEquals("$0.0 paid using RoachPay - name: roach Email: name@mail.com", roachPay.pay(0));
		System.out.println(roachPay.pay(0));
	}

	/**
	 * Test method for
	 * {@link roachmotel.Payment#pay(double)}.
	 */
	@Test
	@Order(3)
	void testPaymentInterface() {
		System.out.println("Testing Payment interface.");

		payment = masterRoach;
		assertEquals(
				"$100.0 paid using MasterRoach card - name: roach Number ending in: 9999 cvv: 123 Expiration Date: 05/2020",
				payment.pay(hundredDollars));
		System.out.println(payment.pay(hundredDollars));

		payment = roachPay;
		assertEquals("$100.0 paid using RoachPay - name: roach Email: name@mail.com",
				payment.pay(hundredDollars));
		System.out.println(payment.pay(hundredDollars));
	}

	/**
	 * Test method for
	 * {@link roachmotel.RoachMotel#checkIn()}.
	 * 
	 * @throws IOException
	 */
	@Test
	@Order(4)
	void testCheckIn() throws IOException {
		System.out.println("Testing checkIn method.");

		basicRoom = roachMotel.checkIn(colonyOne, Room.BASIC, Arrays.asList(Amenity.FOOD_BAR));
		assertEquals("Basic Room, Food Bar", basicRoom.getDescription());
		System.out.println(basicRoom);

		deluxeRoom = roachMotel.checkIn(colonyTwo, Room.DELUXE, Arrays.asList(Amenity.SPA));
		assertEquals("Deluxe Room, Spa", deluxeRoom.getDescription());
		System.out.println(deluxeRoom);
	}

	/**
	 * Test method for
	 * {@link roachmotel.RoachMotel#checkOut(AbstractRoom,int,java.lang.String)}.
	 * 
	 * @throws IOException
	 */
	@Test
	@Order(5)
	void testMotelCheckOutMethod() throws IOException {
		System.out.println("Testing Roach Motel checkOut method.");
		double amount = 0.0;

		amount = roachMotel.checkOut(basicRoom, 3, firstColonyName);
		assertEquals(179.97, amount);
		roachMotel.pay(roachPay, amount, new StringBuilder(basicRoom.toString()));
		System.out.println("Cost: $" + amount);

		amount = roachMotel.checkOut(deluxeRoom, 2, secondColonyName);
		assertEquals(239.98, amount);
		roachMotel.pay(masterRoach, amount, new StringBuilder(deluxeRoom.toString()));
		System.out.println("Cost: $" + amount);
	}

}// End of the StrategyTester class
