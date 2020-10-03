package roachmotel;

/**
 * RoachPay is the concrete implementation of a smart phone application payment.
 * 
 * @author Jerry Belmonte
 * @date 04/25/2020
 */
public class RoachPay implements Payment {

	/** The name of the Roach colony paying the bill. */
	private String name;
	/** The email of the Roach colony paying the bill. */
	private String email;

	/**
	 * Default constructor for the RoachPay class.
	 */
	public RoachPay() {
		this.name = null;
		this.email = null;
	}// End of the default constructor.

	/**
	 * Normal constructor for the RoachPay class.
	 * 
	 * @param name  The name of the Roach colony.
	 * @param email The email for the Roach colony.
	 */
	public RoachPay(String name, String email) {
		this.name = name;
		this.email = email;
	}// End of the normal constructor.

	/**
	 * The pay method for the RoachPay class. Returns the RoachPay transaction
	 * information for the Motel record files.
	 * 
	 * @param amount The cost of the motel room.
	 * @return RoachPay user payment transaction record.
	 */
	@Override
	public String pay(double amount) {
		return "$" + amount + " paid using RoachPay - name: " + this.name + " Email: " + this.email;
	}// End of the pay method

}// End of the RoachPay class
