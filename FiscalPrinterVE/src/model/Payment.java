package model;

/**
 * Class that defines the possible form of payment for the invoice on the Bixolom printer.
 * @author David Hernandez
 * @since  2020-02-14
 */
public class Payment {

    /**
     * Enum that defines the possible forms of payment for the invoice on the Bixolom printer.
     */
    public enum PaymentType {
    	
        /** Cash 1 */
        CASH1 (1),
        /** Cash 2 */
        CASH2 (2),
        /** Cash 3 */
        CASH3 (3),
        /** Cash 4 */
        CASH4 (4),
        /** Check 1 */
        CHEQUE1 (5),
        /** Check 2 */
        CHEQUE2 (6),
        /** Check 3 */
        CHEQUE3 (7),
        /** Check 4 */
        CHEQUE4 (8),
        /** A Card 1 */
        ACARD1 (9),
        /** A Card 2 */
        ACARD2 (10),
        /** A Card 3 */
        ACARD3 (11),
        /** A Card 4 */
        ACARD4 (12),
        /** B Card 1 */
        BCARD1 (13),
        /** B Card 2 */
        BCARD2 (14),
        /** B Card 3 */
        BCARD3 (15),
        /** B Card 4 */
        BCARD4 (16);
    	
    	/** Payment index */
    	private int value;
    	
		/**
		 * Constructor of the class.
		 * @param value - enum value.
		 */
    	private PaymentType (int value){
    		this.value = value;
    	} // Constructor
    	
    	/**
    	 * Method that gets the value according to the enum used.
    	 * @return int - Value of the enum.
    	 */
    	public int getValue(){
    		return value;
    	} // getValue
    } // enum
    
    /** Payment amount */
    private double payment;
    /** Payment type */
    private PaymentType paymentType;
    /** Payment name */
    private String name;

    /**
     * Method that shows the attributes string of the payment.
     * @return String - Payment attributes.
     */
    @Override
    public String toString() {
        return paymentType + ": " + name + ": " + payment;
    } // toString

    /**
     * Method that gets the payment amount.
     * @return double - Payment amount.
     */
    public double getAmount() {
        return payment;
    } // getAmount

    /**
     * Method that sets the payment amount.
     * @param amount - Payment amount.
     */
    public void setAmount(double amount) {
        this.payment = amount;
    } // setAmount

    /**
     * Method that gets the payment type.
     * @return PaymenType - Payment type enum.
     */
    public PaymentType getPaymentType() {
        return paymentType;
    } // getType

    /**
     * Method that sets the payment type.
     * @param paymentType - Payment type enum.
     */
    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    } // setType

    /**
     * Method that gets the payment name.
     * @return String - Payment name.
     */
    public String getName() {
        return name;
    } // getName

    /**
     * Method that sets the payment name.
     * @param name - Payment name.
     */
    public void setName(String name) {
        this.name = name;
    } // setName
    
} // ReceiptPayment
