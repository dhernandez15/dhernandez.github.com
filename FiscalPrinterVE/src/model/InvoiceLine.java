package model;

import util.Rounder;

/**
 * Class that model the invoice line.
 * @author  David Hernandez
 * @since   2020-02-25
 * @version 1.0.0
 */
public class InvoiceLine {

	/** Product name */
    private String name;
    /** Product quantity on the line */
    private double quantity;
    /** Product price by unity */
    private double price;
    /** Product Tax type  */
    private TaxType taxRate;
    /** Operation: "M" => The line adds; "m" => The line subtracts */
    private String operation;
    /** Product discount type on the line */
    private DiscountType discountType = DiscountType.NODISCOUNT;
    /** Product discount on the line */
    private double discount;
    
    /**
     * Constructor of the class. Invoice line without discount.
     * @param name - Product description.
     * @param quantity - Product quantity.
     * @param price - Product price.
     * @param taxRate - Tax type
     * @param operation - Line operation. ("M" => Adds the line; "m" => Subtracts the line)
     */
    public InvoiceLine(String name, double quantity, double price, TaxType taxRate, String operation) {
        this.name = name;
        this.quantity = quantity;
        this.price = Rounder.roundCurrency(price);
        this.taxRate = taxRate;
        this.operation = operation;
    } // constructor

    /**
     * Constructor of the class. Invoice line within discount.
     * @param name - Product description.
     * @param amount - Product quantity.
     * @param price - Product price.
     * @param taxRate - Tax type.
     * @param operation - Line operation. ("M" => The line adds; "m" => The line subtracts)
     * @param discountType - Discount type object.
     * @param discount - Discount amount.
     */
    public InvoiceLine(String name, double amount, double price, TaxType taxRate, String operation
    		, DiscountType discountType, double discount) {
        this.name = name;
        this.quantity = amount;
        this.price = Rounder.roundCurrency(price);
        this.taxRate = taxRate;
        this.operation = operation;
        this.discountType = discountType;
        if (discountType == DiscountType.DISCOUNT || discountType == DiscountType.DISCOUNTBYAMT) {
            discount = Rounder.roundCurrency(discount);
        } // end if
        this.discount = discount;
    } // constructor

    /**
     * Method that gets the invoice line gross amount. (quantity * price)
     * @return double - Gross amount.
     */
    public double getLineGrossAmt() {
        return Rounder.roundCurrency(quantity * price);
    } // getGross

    /**
     * Method that gets total amount on invoice line. (including discount).
     * @return double - Invoice line amount with discount.
     */
    public double getLineTotalAmt() {
        double lineAmt = quantity * price;
        switch (discountType) {
            case DISCOUNT:
                lineAmt = lineAmt - lineAmt * discount;
                break;
            case CHARGE:
                lineAmt = lineAmt + lineAmt * discount;
                break;
            case DISCOUNTBYAMT:
                lineAmt -= discount;
                break;
			default:
				break;
        } // switch
        return Rounder.roundCurrency(lineAmt);
    } // getTotal
    
    /**
     * Method that gets the command for the invoice line on the Bixolom printer.
     * @return String - BixolomVE printer command.
     */
    public String getBixolomVEPrinterCmd() {
        // Format: @PrintLineItem|Description|Quantity|Price|Tax|M
    	return "@PrintLineItem|" + getName() + "|" + getQuantity() + "|" + getPrice() + "|"  + getTaxRate().getRate() + "|M";
    } // cmdPrinterBixolomVE

    /**
     * Method that gets the product quantity on the invoice line.
     * @return double - Product quantity on invoice line.
     */
    public double getQuantity() {
        return quantity;
    } // getAmount

    /**
     * Method that sets the product quantity on the invoice line.
     * @param amount - Product quantity.
     */
    public void setQuantity(double amount) {
        this.quantity = amount;
    } // setAmount

    /**
     * Method that gets the product name on the invoice line.
     * @return String - Product name.
     */
    public String getName() {
        return name;
    } // getName

    /**
     * Method that sets the product name on the invoice line.
     * @param name - Product name.
     */
    public void setName(String name) {
        if (name != null) {
            name = name.trim();
        } // end if
        this.name = name;
    } // setName

    /**
     * Method that gets the product price on the invoice line.
     * @return double - Product price.
     */
    public double getPrice() {
        return price;
    } // getPrice

    /**
     * Method that sets the product price on the invoice line. 
     * @param price - Product price
     */
    public void setPrice(double price) {
        this.price = price;
    } // setPrice

    /**
     * Method that gets the tax rate on the invoice line.
     * @return IVARate - IVA Rate object.
     */
    public TaxType getTaxRate() {
        return taxRate;
    } // getTaxRate

    /**
     * Method that sets the tax rate on the invoice line.
     * @param taxRate - IVA Rate object.
     */
    public void setTaxRate(TaxType taxRate) {
        this.taxRate = taxRate;
    } // setTaxRate

    /**
     * Method that gets the operation on the invoice line.
     * @return String - Operation ("M" o "m").
     */
    public String getOperation() {
        return operation;
    } // getOperation

    /**
     * Method that sets the operation on the invoice line.
     * @param operation - Line operation.
     */
    public void setOperation(String operation) {
        this.operation = operation;
    } // setOperation
    
    /**
     * Method that gets the discount on the invoice line.
     * @return
     */
    public double getDiscount() {
        return discount;
    } // getDiscount

    /**
     * Method that sets the discount on the invoice line.
     * @param discount - Discount amount on the invoice line.
     */
    public void setDiscount(double discount) {
        this.discount = discount;
    } // setDiscount

    /**
     * Method that gets the discount type on the invoice line.
     * @return DiscountType - Discount type object.
     */
    public DiscountType getDiscountType() {
        return discountType;
    } // getDiscountType

    /**
     * Method that sets the discount type on the invoice line.
     * @param discountType - Discount type.
     */
    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    } // setDiscountType

} // InvoiceLine
