package roachmotel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Singleton Motel class for the Roach Motel.
 * 
 * @author Jerry Aragon
 * @author Jerry Belmonte
 * @date 04/26/2020
 */
public class RoachMotel {

	/** The file name where we will do the ASCII logging. */
	private static String motelFileName = "motel_log.txt";

	/**
	 * The single and ONLY Motel instance. Note: Volatile keyword is used to
	 * make sure that multiple threads handle only the ONE and ONLY unique
	 * 'lazy' instance of the Motel.
	 */
	private static volatile RoachMotel roachMotel;

	/** The File corresponding to the Motel log file name. */
	private File motelLogFile;

	/** Flag to tell whether we have written to this log yet or not. */
	private boolean append = false;

	/** The name of the Motel. */
	private final String motelName;

	/** The number of rooms in the Motel. */
	private final int motelCapacity;

	/** The neon sign outside the motel displaying the vacancy status. */
	private boolean noVacancySign;

	/** The rooms that are available for check in. */
	private List<Integer> rooms;

	/** The roster of occupied rooms. */
	private Map<RoachColony, AbstractRoom> roomRoster;

	/**
	 * The private constructor that only the Motel class can access.
	 * 
	 * @param motelName     The name of the motel.
	 * @param motelCapacity The total amount of rooms the motel can have.
	 */
	private RoachMotel(String motelName, int motelCapacity) {
		this.motelLogFile = new File(RoachMotel.motelFileName);
		this.motelName = motelName;
		this.motelCapacity = motelCapacity;
		this.noVacancySign = false;
		this.rooms = null;
		this.roomRoster = new HashMap<>();
	}// End of the private constructor for Motel.

	/**
	 * Creates the motel room numbers for the RoachMotel.
	 */
	private void createMotelRooms() {
		if (this.rooms == null) {			// check that the rooms haven't been created
			this.rooms = new ArrayList<>();
			for (int count = 1; count <= this.motelCapacity; count++) {
				rooms.add(100 + count);			// the number 100 indicates they are on the first floor
			}// end for loop 
		}//end if 
		else {			// throw an exception if called a second time
			throw new IllegalArgumentException("Motel rooms have already been created!");
		}// end else
	}// End of the createMotelRooms method

	/**
	 * This method is used instead of the constructor to get the one and only
	 * Motel instance.
	 * 
	 * @return The one and only instance of Motel.
	 */
	public static RoachMotel getMotel() {
		if (RoachMotel.roachMotel == null) { 								// Check #1
			synchronized(RoachMotel.class) { 						// Make this thread safe
				if (RoachMotel.roachMotel == null) { 						// Check #2
					RoachMotel.roachMotel = new RoachMotel("Roach Motel", 5);	// Create the "Roach Motel"
					RoachMotel.roachMotel.createMotelRooms();				// Create 5 rooms
				}// end inner if
			}// end synchronized scope
		}// end outer if
		return RoachMotel.roachMotel;
	}// End of the getMotel method.

	/**
	 * Overloaded getMotel method used instead of the constructor to get the one
	 * and only Motel instance. If the Motel has yet to be instantiated, it will
	 * create a Motel instance with the specified parameter arguments.
	 * 
	 * @param name     The specified name for the motel.
	 * @param capacity The fixed room capacity for the motel.
	 * @return The one and only instance of Motel.
	 */
	public static RoachMotel getMotel(String name, int capacity) {
		if (RoachMotel.roachMotel == null) { 								// Check #1
			synchronized(RoachMotel.class) { 						// Make this thread safe
				if (RoachMotel.roachMotel == null) { 						// Check #2
					RoachMotel.roachMotel = new RoachMotel(name, capacity);	// Create the Motel singleton
					RoachMotel.roachMotel.createMotelRooms();				// Create the Motel rooms
				}// end inner if
			}// end synchronized scope
		}// end outer if 
		return RoachMotel.roachMotel;
	}// End of the getMotel method.

	/**
	 * Sets the file path for the motel log file.
	 * 
	 * @param fileName The file path for the log file.
	 */
	public static void setFile(String fileName) {
		RoachMotel.motelFileName = fileName;
	}// End of the setFile method

	/**
	 * This is an instance-oriented version of the log utility. All payments
	 * made will be written to a transaction log. This log will contain the name
	 * of the colony, the type of payment made and the amount.
	 * 
	 * @param record The payment transaction to write to the transaction log.
	 * @throws IOException
	 */
	public void transactionLog(String record) throws IOException {
		//Create a prefix to indicate the exact time and date of the log message.
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Calendar cal = Calendar.getInstance();
		FileWriter fileWriter = null;					// The file writer that we will write to
		//Make sure that never more than one thread runs this next block of code at the same time.
		//We only have one instance, so this really is just like a static synchronize stretch.
		synchronized(this) {
			//Create a new FileWriter and append if we have written anything already.
			fileWriter = new FileWriter(this.motelLogFile.getAbsoluteFile(), this.append);
			this.append = true;					// From here on out, append
			PrintWriter pw = new PrintWriter(fileWriter);
			pw.println(dateFormat.format(cal.getTime()) + " " + record);
			pw.close();
		}// end synchronized scope
	}// End of the instance-oriented transactionLog method

	/**
	 * Static method to read the transaction log when exiting the program. After
	 * reading the transaction log, the contents are displayed in the console.
	 * 
	 * @throws IOException If motel does not have a transaction file to read.
	 */
	public static void displayTransactionLog() throws IOException {
		RoachMotel roachMotel = RoachMotel.getMotel();
		String newline = System.lineSeparator();
		StringBuilder sb = new StringBuilder();

		try (BufferedReader br = new BufferedReader(new FileReader(new File(RoachMotel.motelFileName)))) {
			sb.append("Dumping the log file for " + roachMotel.motelName + ":");
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(newline);
				sb.append(line);
			}//end while
		}//end try block
		catch (FileNotFoundException e) {
			throw new IllegalArgumentException("File is not in the specified location!");
		}//end catch block

		System.out.println(sb.toString());
		System.out.println("Completed Satisfactorily.");
	} // End of the static transaction logger member method.

	/**
	 * Checks the customer into the RoachMotel.
	 * 
	 * @param roaches   The roach colony checking in.
	 * @param type      The type of motel room.
	 * @param amenities The list of amenities for the room.
	 * @return The motel room created for the roach colony.
	 *         Returns null if the motel is full.
	 * @throws IOException
	 */
	public AbstractRoom checkIn(RoachColony roaches, Room type, List<Amenity> amenities) throws IOException {
		if (roaches == null) {
			throw new IllegalArgumentException("RoachColony cannot be null!");
		}// end if
		if (amenities == null) {
			throw new IllegalArgumentException("Amenities list is null!");
		}// end if 

		System.out.println("Checkin - available Rooms: " + this.rooms);		// display available rooms
		StringBuilder sb = new StringBuilder();								// StringBuilder to build log file
		AbstractRoom newRoom = null;

		if (!this.isFull()) {												// check room occupancy
			newRoom = RoomFactory.createRoom(type, amenities);				// create the room type
			newRoom.setRoomNumber(rooms.remove(0));							// give the colony first available room

			// log successful customer check in if there is vacancy
			sb.append("Colony: " + roaches + " safely registered with amenities: [ ");
			for (Iterator<Amenity> iter = amenities.iterator(); iter.hasNext(); sb.append(", ")) {
				sb.append(iter.next().getDescription());
			}//end for loop

			// replace the last comma
			int ndx = sb.lastIndexOf(",");
			if (ndx > 0) {
				sb.replace(ndx, ndx + 1, " ]");
			}//end if
			else {
				sb.append(" ]"); // finish empty brackets
			}//end else

			// call the logger method to write a record
			this.transactionLog(sb.toString());
			// put the two objects into the room roster
			newRoom = this.roomRoster.put(roaches, newRoom);
		}//end if 
		else {
			sb.append("No room at the inn for: " + roaches);
			this.transactionLog(sb.toString());
		}//end else

		// Display check in status if successful, or display No Vacancy if full
		if ((newRoom = this.roomRoster.get(roaches)) != null) {
			System.out.println("CheckIn - The colony: " + roaches + " was assigned room: " + newRoom);
		}//end if
		else {
			System.out.println("Checkin - We're full.");
		}//end else

		return newRoom;
	}// End of the checkIn method

	/**
	 * Checks a roach colony out of a motel room by calculating the cost of
	 * their stay as well as sending a record of their bill to a transaction log
	 * to be recorded. The colony will then be removed from the room and the
	 * room will become available.
	 * 
	 * @param motelRoom    The customers' motel room.
	 * @param numberOfDays The number of days checked in.
	 * @param customerName The name of the customer paying the bill.
	 * @return The itemized receipt bill amount paid by the customer.
	 * @throws IOException If their payment transaction gets declined.
	 */
	public double checkOut(AbstractRoom motelRoom, int numberOfDays, String customerName) throws IOException {
		System.out.println("A customer is checking out. Right now we have available Rooms: " + this.rooms);
		RoachColony roaches = RoachMotel.findColonyFromRoom(motelRoom);

		if (roaches == null) {
			throw new IllegalArgumentException("Customer information cannot be found!");
		}//end if
		else if (roaches.getColonyName().compareToIgnoreCase(customerName) != 0) {
			throw new IllegalArgumentException("Customer information does not match!");
		}//end else if

		StringBuilder sb = new StringBuilder();
		sb.append("Colony: " + roaches + " checking out of room: ");
		sb.append(motelRoom + " for ");

		double roomCost = motelRoom.cost();				// get the room cost including amenities
		roomCost *= numberOfDays;						// multiply room cost by the number of days

		// log the transaction information
		this.pay(roaches.payTheBill(), roomCost, sb);

		this.rooms.add(motelRoom.getRoomNumber());		// list the room as being available
		this.rooms.sort(null);
		return roomCost;
	}// End of the checkOut method

	/**
	 * When a colony checks out of the motel, they will either use RoachPay or
	 * their MasterRoach credit card to pay the bill. Roach Pay requires the
	 * name and email of the colony paying the bill. The MasterRoach credit card
	 * requires a name, a security code, a card number and an expiration date.
	 * 
	 * @param paymentType  The type of payment for the bill.
	 * @param checkOutBill The total billed amount for check out.
	 * @param strBuilder   The StringBuilder with the check out information.
	 * @throws IOException
	 */
	public void pay(Payment paymentType, double checkOutBill, StringBuilder strBuilder) throws IOException {
		if (paymentType == null) {
			throw new IllegalArgumentException("Payment cannot be null!");
		}//end if

		// Method where the roach colony pays the bill
		String transaction = paymentType.pay(checkOutBill); // keep payment transaction anonymous
		strBuilder.append(transaction);	// append transaction confirmation
		this.transactionLog(strBuilder.toString()); // log the transaction into the Motel records

		System.out.println("$" + checkOutBill + " paid with credit/debit card"); // only display the amount paid
	}//end of the pay method

	/**
	 * Checks if the Motel is currently all booked.
	 * 
	 * @return True if the Motel is full ("NO Vacancy").
	 *         Otherwise, false if there are available rooms ("Vacancy").
	 */
	public boolean isFull() {
		// if there are no available rooms, sign = "NO Vacancy"
		// else, rooms are available, sign = "Vacancy"
		this.noVacancySign = this.rooms.isEmpty() ? true : false;
		return this.noVacancySign;
	}// End of the isFull method

	/**
	 * Static method that uses membership to find which room is associated with
	 * the given roach colony argument.
	 * 
	 * @param roaches The roach colony staying at the Roach Motel.
	 * @return The room where the roach colony is staying. Returns null if the
	 *         Room is not found.
	 */
	public static AbstractRoom findRoomFromColony(RoachColony roaches) {
		if (roaches == null) {	// null argument check
			throw new IllegalArgumentException("RoachColony argument is null!");
		}// end if

		RoachMotel roachMotel = RoachMotel.getMotel();
		return roachMotel.roomRoster.get(roaches);
	}// End of the findRoomFromColony method

	/**
	 * Static method that uses membership to find which roach colony is staying
	 * in the given Motel room argument.
	 * 
	 * @param abstractRoom The occupied motel room.
	 * @return The roach colony in the room. Returns null if the roach colony is
	 *         not found.
	 */
	public static RoachColony findColonyFromRoom(AbstractRoom abstractRoom) {
		if (abstractRoom == null) {										// null argument check
			throw new IllegalArgumentException("Room argument is null!");
		}// end if

		RoachMotel roachMotel = RoachMotel.getMotel();

		Map<RoachColony, AbstractRoom> hm = roachMotel.roomRoster;		// reference to the map object
		Set<RoachColony> keys = roachMotel.roomRoster.keySet(); // iterate through key set

		int roomNumToFind = abstractRoom.getRoomNumber();				// room number to search for
		RoachColony roaches = null;								// holds a roach colony if found

		for (RoachColony colony : keys) {
			int roomNumber = hm.get(colony).getRoomNumber();
			if (roomNumber == roomNumToFind) {					// found the room
				roaches = colony;								// the colony we are looking for
			}// end if
		}// end for loop

		return roaches;
	}// End of the findColonyFromRoom method

	/**
	 * Sprays the room a roach colony has partied in. Reduces the roach
	 * population by 25% if a spray resistent shower was added to the room. Else
	 * it is reduced by 50%.
	 * 
	 * @param roaches The roach colony throwing a party.
	 */
	public void sprayRoom(RoachColony roaches) {
		AbstractRoom abstractRoom = findRoomFromColony(roaches);
		Set<Amenity> list = abstractRoom.getAmenities();

		// check if shower was added to room
		double reduce = list.contains(Amenity.SHOWER) ? 0.25 : 0.5;
		int population = roaches.getPopulation();

		// subtract the total population with the percentage of the population being sprayed away. 
		roaches.setPopulation((int) (population - (population * reduce)));
	}// End of the sprayRoom method

	/**
	 * Displays the current information in regards to the Roach Motel. In
	 * addition to displaying the name of the motel, it displays the room roster
	 * of currently occupied rooms, and the list of available rooms.
	 * 
	 * @return Current Roach Motel management information with the current room
	 *         roster and check in status.
	 */
	@Override
	public String toString() {
		String newline = System.lineSeparator();
		StringBuilder sb = new StringBuilder();
		sb.append("Motel: " + this.motelName + " Room roster: ");

		if (!this.roomRoster.isEmpty()) {
			Iterator<Map.Entry<RoachColony, AbstractRoom>> iter = roomRoster.entrySet().iterator();

			while (iter.hasNext()) {
				Map.Entry<RoachColony, AbstractRoom> pair = iter.next();
				String colonyDetails = pair.getKey().toString();
				String roomDetails = pair.getValue().toString();
				sb.append(newline + "Room: " + roomDetails + newline);
				sb.append("Is hosting: " + colonyDetails);
			}//end of while loop with iterator

			sb.append(newline);
		}//end if

		sb.append("Available rooms: " + this.rooms);
		return sb.toString();
	}// End of the toString method

}// End of the Motel class
