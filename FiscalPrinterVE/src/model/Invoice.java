package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.Rounder;

/**
 * Class that models the invoice.
 * @author  David Hernandez
 * @since   2020-02-25
 * @version 1.0.0
 */
public class Invoice {

    /** Reference for invoice. Must be unique. */
    private String reference;
    /** Date of invoice creation */
    private Date created;
    /** Date of printing the invoice (its fiscalisation) */
    private Date printed;
    /** Cash register name - a field on the invoice specifying the name of the cash register. 
     *  Used also to distinguish where to queue printouts. */
    private String cashbox;
    /** Cashier's name and surname */
    private String cashierName;
    /** invoice lines list */
    private List<InvoiceLine> invoiceLines = new ArrayList<>();
    /** Related payments to the invoice */
    private List<Payment> invoicePayments = new ArrayList<>();
    
	/**
	 * Constructor of the class.
	 */
	public Invoice() {
		this.created = new Date();
	} // constructor
	
	/**
     * Method that adds items to the invoice.
	 * @param name - Name of item.
	 * @param quantity - The quantity of the item.
	 * @param price - Gross price of the goods.
	 * @param taxRate - VAT rate on goods.
	 * @param operation - Line operation ("M" o "m").
     */
    public final void addLine(String name, double quantity, double price, TaxType taxRate, String operation) {
        addLine(new InvoiceLine(name, quantity, price, taxRate, operation));
    } // addLine

    /**
     * Method that adds items to the invoice.
     * @param name - Name of item.
     * @param quantity - The quantity of the item.
     * @param price - Gross price of the goods.
     * @param taxRate - Tax rate object.
     * @param operation - Line operation ("M" o "m").
     * @param discountType - Discount type.
     * @param discount - Discount amount.
     */
    public final void addLine(String name, double quantity, double price, TaxType taxRate, String operation
    		, DiscountType discountType, double discount) {
        addLine(new InvoiceLine(name, quantity, price, taxRate, operation, discountType, discount));
    } // addLine

    /**
     * Method that adds items to the invoice.
     * @param line - invoice line object.
     */
    public final void addLine(InvoiceLine line) {
        invoiceLines.add(line);
    } // addLine

    /**
     * Getter for property NoOfLines.
     * @return Value of property noOfLines.
     */
    public int getNoOfLines() {
        return invoiceLines.size();
    } // getNoOfLines

    /**
     * Indexed getter for property payment.
     * @param index - Index of the property.
     * @return Value of the property at <CODE>index</CODE>.
     */
    public InvoiceLine getLine(int index) {
        return invoiceLines.get(index);
    } // getNoOfLines

    /**
     * Method that obtains the gross value of the entire invoice.
     * @return Gross value of the entire invoice.
     */
    public double getTotal() {
        double sum = 0.0;
        for (int i = 0; i < invoiceLines.size(); i++) {
            sum += invoiceLines.get(i).getLineTotalAmt();
        } // end if
        return Rounder.roundCurrency(sum);
    } // getTotal
    
    /**
     * Method that gets the reference of the invoice.
     * @return String - Reference of the invoice.
     */
    public String getReference() {
        return reference;
    } // getReference
    
    /**
     * Method that sets the reference of the invoice.
     * @param reference - Reference of the invoice.
     */
    public void setReference(String reference) {
        if (reference != null) {
            reference = reference.trim();
        } // end if
        this.reference = reference;
    } // setReference
    
    /**
     * Method that gets the created date of the invoice.
     * @return Date - Created date of the invoice.
     */
    public Date getCreated() {
        return created;
    } // getCreated

    /**
     * Method that sets the created date of the invoice.
     * @param created - Created date of the invoice.
     */
    public void setCreated(Date created) {
        this.created = created;
    } // setCreated

    /**
     * Method that gets printing date of the invoice.
     * @return Date - Printing date of the invoice.
     */
    public Date getPrinted() {
        return printed;
    } // getPrinted

    /**
     * Method that sets the printing date of the invoice.
     * @param printed - Printing date.
     */
    public void setPrinted(Date printed) {
        this.printed = printed;
    } // setPrinted

    /**
     * Method that gets the invoice line list.
     * @return List - Invoice line list.
     */
    public List<InvoiceLine> getInvoiceLines() {
        return invoiceLines;
    } // getSlipLines

    /**
     * Method that gets the cash box value.
     * @return String - Cash box value.
     */
    public String getCashbox() {
        return cashbox;
    } // getCashbox

    /**
     * Method that sets the cash box value.
     * @param cashbox - Cash box value.
     */
    public void setCashbox(String cashbox) {
        if (cashbox != null) {
            cashbox = cashbox.trim();
        } // end if
        this.cashbox = cashbox;
    } // setCashbox

    /**
     * Method that gets the cashier name.
     * @return String - Cashier name.
     */
    public String getCashierName() {
        return cashierName;
    } // getCashierName

    /**
     * Method that sets the cashier name.
     * @param cashierName - Cashier name.
     */
    public void setCashierName(String cashierName) {
        if (cashierName != null) {
            cashierName = cashierName.trim();
        } // end if
        this.cashierName = cashierName;
    } // setCashierName

    public List<Payment> getInvoicePayments() {
        return invoicePayments;
    } // getSlipPayments
    
    @Override
    public String toString() {
        StringBuilder retVal = new StringBuilder();
        retVal.append("\ninvoice: Reference: ").append(reference).append(" Cash register: ").append(getCashbox()).append("\n");
        for (InvoiceLine sl : invoiceLines) {
            retVal.append(sl).append("\n");
        } // end for
        retVal.append("Total invoice: ").append(getTotal()).append(" Cashier: ").append(getCashierName());
        return retVal.toString();
    } // toString

    /* *********************** Payments *************************** */
    /**
     * Method that verifies if there are payments.
     * @return boolean - true, whether there is at least one payment; false, otherwise.
     */
    public boolean isUsingPayments() {
        return invoicePayments.isEmpty();
    } // isUsingPayments

    /**
     * Method that allows to set payment methods for printing on a fiscal invoice.
     * @param payment - Form payment method.
     * @param amount - Payment amount.
     * @param name - Additional description (ignored in the case of Cash).
     */
    public final void addPayment(Payment.PaymentType paymentForm, double amount, String name) {
        if (name != null) {
            if (name.length() > 16) {
                name = name.substring(0, 16);
            } // end if
            name = name.trim();
        } // end if

        Payment invoicePayment = getPayment(paymentForm);
        if (invoicePayment == null) {
            invoicePayment = new Payment();
            invoicePayment.setPaymentType(paymentForm);
            invoicePayment.setName(name);
            invoicePayments.add(invoicePayment);
        } // end if
        invoicePayment.setAmount(Rounder.round(invoicePayment.getAmount() + amount, 2));
    } // addPayment

    /**
     * Method that gets the previously set payment methods.
     * @param type - Payment method.
     * @return value of a given payment method.
     */
    public Payment getPayment(Payment.PaymentType type) {
        for (Payment invoicePayment : invoicePayments) {
            if (invoicePayment.getPaymentType() == type) {
                return invoicePayment;
            } // end if
        } // end for
        return null;
    } // getPayment

    /**
     * Method that gets the previously set payment methods
     * @param paymentForm - Payment method
     * @return value of a given payment method.
     */
    public double getPaymentAmount(Payment.PaymentType paymentForm) {
        Payment payment = getPayment(paymentForm);
        if (payment != null) {
            return payment.getAmount();
        } else {
            return 0.0;
        } // end if
    } // getPaymentAmount

    /**
     * Method that gets a predefined descriptions for each form of payment.
     * @param paymentForm - Payment method.
     * @return Additional specification for a given payment method.
     */
    public String getPaymentName(Payment.PaymentType paymentForm) {
        Payment payment = getPayment(paymentForm);
        if (payment != null) {
            return payment.getName();
        } else {
            return "";
        } // end if
    } // getPaymentName
    /* ************************************************************* */
} // Invoice
