package model;

/**
 * Class that models to the customer.
 * @author  David Hernandez
 * @since   2020-02-27
 * @version 1.0.0
 */
public class Customer {

	/** CI / RIF document */
	private String ciRif;
	/** Customer name */
	private String name;
	/** Customer  phone */
	private String phone;
	/** Customer address */
	private String address;
	
	/**
	 * Method that gets the customer CI / RIF document
	 * @return String - CI / RIF. 
	 */
	public String getCiRif() {
		return ciRif;
	} // getCiRif

	/**
	 * Method that sets the customer CI / RIF document.
	 * @param ciRif - CI / RIF document.
	 */
	public void setCiRif(String ciRif) {
		this.ciRif = ciRif;
	} // setCiRif

	/**
	 * Method that gets the customer name.
	 * @return String - customer name.
	 */
	public String getName() {
		return name;
	} // getName

	/**
	 * Method that sets the customer name.
	 * @param name - Customer name.
	 */
	public void setName(String name) {
		this.name = name;
	} // setName

	/**
	 * Method that gets the customer phone.
	 * @return String - Customer phone.
	 */
	public String getPhone() {
		return phone;
	} // getPhone

	/**
	 * Method that sets the customer phone.
	 * @param phone - Customer phone.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	} // setPhone

	/**
	 * Method that gets the customer address.
	 * @return String - Customer address.
	 */
	public String getAddress() {
		return address;
	} // getAddress

	/**
	 * Method that sets the customer address.
	 * @param address - Customer address.
	 */
	public void setAddress(String address) {
		this.address = address;
	} // setAddress

} // Customer
