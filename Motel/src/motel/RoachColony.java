package roachmotel;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Every customer in the Roach Motel is a roach colony.
 * 
 * @author Jerry Aragon
 * @author Jerry Belmonte
 * @date 05/01/2020
 */
public class RoachColony {

	/** The name of the roach colony. */
	private String colonyName;
	/** The number of roaches in the colony. */
	private int population;
	/** The growth rate for the roach colony. */
	private double growthRate;
	/** The type of payment selection (card or mobile). */
	private Payment paySelection;

	/**
	 * Default constructor for the RoachColony class.
	 */
	public RoachColony() {
		this.colonyName = "Roach Colony";
		this.population = 0;
		this.growthRate = 0;
		this.paySelection = null;
	}// End of the default constructor.

	/**
	 * Normal constructor for the RoachColony class.
	 * 
	 * @param colonyName The name of the roach colony customer paying the bill.
	 * @param population The initial population checking into the Motel.
	 * @param growthRate The growth rate for the colony if they throw a party.
	 * @param choice     The payment choice (true for MasterRoach, false for
	 *                   RoachPay).
	 */
	public RoachColony(String colonyName, int population, double growthRate, boolean choice) {
		this.colonyName = colonyName;
		this.population = population;
		this.growthRate = growthRate;
		this.paySelection = RoachColony.selectPayment(colonyName, choice);
	}// End of the normal constructor.

	/**
	 * Gets a payment strategy instance for this roach colony. The roach motel
	 * customers have the option of chosing to pay with a MasterRoach card or
	 * RoachPay.
	 * 
	 * @param colonyName The name of the roach colony.
	 * @param choice     The payment type choice (true for MasterRoach, false
	 *                   for RoachPay).
	 * @return MasterRoach if choice is true, other returns RoachPay if choice
	 *         is false.
	 */
	private static Payment selectPayment(String colonyName, boolean choice) {
		// create MasterRoach Payment
		if (choice) {
			String cardNum = Integer.toString(ThreadLocalRandom.current().nextInt(1000, 9999 + 1));
			String cvv = Integer.toString(ThreadLocalRandom.current().nextInt(100, 999 + 1));
			String Date = Integer.toString(ThreadLocalRandom.current().nextInt(1, 12 + 1)) + "/"
					+ Integer.toString(ThreadLocalRandom.current().nextInt(2020, 2025 + 1));
			MasterRoach visa = new MasterRoach(colonyName, cardNum, cvv, Date);
			return visa;
		}// end if 
		else {
			RoachPay account = new RoachPay(colonyName, colonyName.replaceAll(" ", "") + "1234@roachmail.com");
			return account;
		}// end else
	}//end selectPayment

	/**
	 * Gets the roach colonys' name.
	 * 
	 * @return The name of the roach colony.
	 */
	public String getColonyName() {
		return this.colonyName;
	}// end getColonyName()

	/**
	 * Sets the name of the roach colony.
	 * 
	 * @param colonyName The new roach colony name.
	 */
	public void setColonyName(String colonyName) {
		this.colonyName = colonyName;
	}// end setColonyName()

	/**
	 * Gets the current population of the roach colony. Since roaches like to
	 * party this number can vary.
	 * 
	 * @return The current roach colony population.
	 */
	public int getPopulation() {
		return this.population;
	}// end getPopulation()

	/**
	 * Updates the population of the roach colony.
	 * 
	 * @param population The roach colony population to set.
	 */
	public void setPopulation(int population) {
		this.population = population;
	}// end setPopulation()

	/**
	 * Gets the roach colonys' growth rate.
	 * 
	 * @return The growth rate.
	 */
	public double getGrowthRate() {
		return this.growthRate;
	}// end getGrowthRate()

	/**
	 * Sets the colonys' growth rate.
	 * 
	 * @param growthRate The growth rate to set.
	 */
	public void setGrowthRate(double growthRate) {
		this.growthRate = growthRate;
	}// end setGrowthRate()

	/**
	 * Invoke this method if the roach colony is checking out and wishes to pay
	 * the total bill for staying at the Roach Motel.
	 * 
	 * @return The Payment instance that will be used to pay the total bill.
	 */
	public Payment payTheBill() {
		return this.paySelection;
	}// end of payTheBill method

	/**
	 * Roaches are fond of throwing parties. Every time they throw a party,
	 * the number of occupants in the room increases by the growth rate.
	 * Whenever the roaches throw a party, management responds by spraying the
	 * room. As a result, this method invokes the spray method in the Motel.
	 */
	public void party() {
		setPopulation((int) (population * growthRate));
		RoachMotel.getMotel().sprayRoom(this);
	}// End of the party method

	/**
	 * Generates hash code for the roach colonies' name.
	 * 
	 * @return Hash code for the RoomColony instance.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(this.colonyName);
	}// End of the hashCode method

	/**
	 * The equals method for the RoachColony class bases it's comparison on the
	 * name of the colony, since the population can vary during runtime
	 * depending on the number of motel room parties.
	 * 
	 * @param obj The other object.
	 * @return True if the roach colony names are the same, otherwise false.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}//end if
		if (!(obj instanceof RoachColony)) {
			return false;
		}//end if
		RoachColony other = (RoachColony) obj;
		return this.colonyName.equals(other.colonyName);
	}// End of the equals method

	/**
	 * Used to capture the information of the roach colony checking in.
	 * 
	 * @return The roach colony name and the population.
	 */
	@Override
	public String toString() {
		return this.colonyName + " " + this.population;
	}// End of the toString method

}// End of the RoachColony class
