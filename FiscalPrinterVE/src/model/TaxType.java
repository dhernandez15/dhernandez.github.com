package model;

/**
 * Enum that contains the tax type.
 * @author  David Hernandez
 * @since   2020-02-25
 * @version 1.0.0
 */
public enum TaxType {

	/** 0 => Exempt (No valid at Venezuela) */
    EXEMPT("0"),
	/** 1 => Price no includes the tax */
    TYPE1("1"),
    /** 2 => Price includes the tax */
    TYPE2("2");
	
	/** Tax type */
    private final String type;

    /**
     * Constructor of the class.
     * @param type - Tax type (0 - 1 - 2).
     */
    private TaxType(String type) {
        this.type = type;
    } // VATRate

    /**
     * Method that gets the Tax type.
     * @return String - Tax type (0 - 1 - 2).
     */
    public String getRate() {
        return type;
    } // getRate

} // IVARate
