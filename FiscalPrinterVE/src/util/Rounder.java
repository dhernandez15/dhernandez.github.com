package util;

/**
 * Class that rounders the amounts.
 * @author  David Hernandez
 * @since   2020-02-25
 * @version 1.0.0
 */
public class Rounder {

	/**
	 * Constructor of the class.
	 * The utility class are privates.
	 */
	private Rounder() {}
	
	/**
	 * Method that rounds the amount according to a radix.
	 * @param amount - Amount to round
	 * @param radix - Number of decimal to round.
	 * @return double - Amount rounded.
	 */
	public static double round(double amount, int radix) {
        double shift = Math.pow(10, radix);
        return Math.round(amount * shift) / shift;
    } // round
	    
	/**
	 * Method that round the currency amount.
	 * @param val - Amount to round.
	 * @return double - Amount rounded.
	 */
    public static double roundCurrency(double val) {
        return round(val + 0.0000001, 2);
    } // roundCurrency

} // Rounder
