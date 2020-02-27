package model;

/**
 * Enum that contains the different discount type.
 * @author  David Hernandez
 * @since   2020-02-25
 * @version 1.0.0
 */
public enum DiscountType {

    /** No discount */
    NODISCOUNT (0),
    /** Percentage Discount (M) */
    DISCOUNT (1),
    /** Percentage Charge  (m) */
    CHARGE (2),
    /** Discount by amount (d) */
    DISCOUNTBYAMT (3);
	
	/** Value of the discount type */
	private int value;
	
	/**
	 * Constructor of the class.
	 * @param value - Value of the constant.
	 */
	private DiscountType(int value) {
		this.value = value;
	} // constructor
	
	/**
	 * Method that gets the value of the discount type constant.
	 * @return int - value of the constant.
	 */
	public int getValue() {
		return value;
	} // getValue
} // DiscountType
