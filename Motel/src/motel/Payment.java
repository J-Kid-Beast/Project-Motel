package roachmotel;

/**
 * Payment strategy pattern for the Roach Motel. Gives customers the option of
 * paying with a credit/debit card or a smart phone payment application.
 * 
 * @author Jerry Belmonte
 * @date 04/25/2020
 */
public interface Payment {

	/**
	 * The pay method is how the roach colony pays the roach motel room bill.
	 * 
	 * @param amount The total bill amount in US dollars.
	 * @return Payment confirmation record.
	 */
	public String pay(double amount);

}// End of the Payment interface
