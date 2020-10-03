package roachmotel;

/**
 * MasterRoach is the concrete implementation of a credit/debit card payment.
 * 
 * @author Jerry Belmonte
 * @date 04/25/2020
 */
public class MasterRoach implements Payment {

	/** The name of the Roach colony. */
	private String name;
	/** The credit card number. */
	private String cardNumber;
	/** The security code on the back of the card. */
	private String cvv;
	/** The date of expiration: 'MM/yyyy' */
	private String expirationDate;

	/**
	 * Default constructor for the MasterRoach class.
	 */
	public MasterRoach() {
		this.name = null;
		this.cardNumber = null;
		this.cvv = null;
		this.expirationDate = null;
	}// End of the default constructor.

	/**
	 * Normal constructor for the MasterRoach class.
	 * 
	 * @param name           The name of the roach colony.
	 * @param cardNumber     The credit card number.
	 * @param cvv            The credit card security code.
	 * @param expirationDate The date of expiration.
	 */
	public MasterRoach(String name, String cardNumber, String cvv, String expirationDate) {
		this.name = name;
		this.cardNumber = cardNumber;
		this.cvv = cvv;
		this.expirationDate = expirationDate;
	}// End of the normal constructor.

	/**
	 * The pay method for the MasterRoach class. Returns the MasterRoach credit
	 * card information for the Motel records.
	 * 
	 * @param amount The cost for the motel room.
	 * @return MasterRoach payment transaction record.
	 */
	@Override
	public String pay(double amount) {
		StringBuilder sb = new StringBuilder();
		sb.append("$" + amount + " paid using ");
		sb.append("MasterRoach card - name: " + this.name);
		sb.append(" Number ending in: " + this.cardNumber);
		sb.append(" cvv: " + this.cvv);
		sb.append(" Expiration Date: " + this.expirationDate);
		return sb.toString();
	}// End of the pay method

}// End of the MasterRoach class
