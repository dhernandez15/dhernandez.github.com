package model;

/**
 * Class that models to the client.
 * @author  David Hernandez
 * @since   2020-02-27
 * @version 1.0.0
 */
public class Client {

	/** Client name */
	private String name;
	/** Client RIF document */
	private String rif;
	/** Client phone (Main) */
	private String phone1;
	/** Client phone (Second) */
	private String phone2;
	/** Client address info 1 */
	private String address1;
	/** Client address info 2 */
	private String address2;
	/** Client address info 3 */
	private String address3;
	/** Client additional info 1 */
	private String info1;
	/** Client additional info 2 */
	private String info2;
	/** Client additional info 3 */
	private String info3;
	
	/** Method that gets client name.
	 * @return String - The client name.
	 */
	public String getName() {
		return name;
	} // getName
	
	/** Method that sets the client name.
	 * @param name - The client name. 
	 */
	public void setName(String name) {
		this.name = name;
	} // setName
	
	/** Method that gets the client RIF.
	 * @return String - The client RIF.
	 */
	public String getRif() {
		return rif;
	} // getRif
	
	/** Method that sets the client RIF.
	 * @param rif - The client RIF.
	 */
	public void setRif(String rif) {
		this.rif = rif;
	} // setRif
	
	/** Method that gets the client phone 1
	 * @return String - The client phone 1.
	 */
	public String getPhone1() {
		return phone1;
	} // getPhone1
	
	/** Method that sets the client the phone 1.
	 * @param phone1 - The client phone 1.
	 */
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	/** Method that gets the client the phone 2.
	 * @return String - The client phone 2.
	 */
	public String getPhone2() {
		return phone2;
	} // getPhone2
	
	/** Method that sets the client the phone 2.
	 * @param phone2 - The client phone 2.
	 */
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	} // setPhone2
	
	/** Method that gets the client address 1.
	 * @return String - The client address 1.
	 */
	public String getAddress1() {
		return address1;
	} // getAddress1
	
	/** Method that sets the client address 1.
	 * @param address1 - The client address 1.
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	} // setAddress1
	
	/** Method that gets the client address 2.
	 * @return String - The client address 2.
	 */
	public String getAddress2() {
		return address2;
	} // getAddress2
	
	/** Method that sets the client address 2.
	 * @param address1 - The client address 2.
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	} // setAddress2
	
	/** Method that gets the client address 3.
	 * @return String - The client address 3.
	 */
	public String getAddress3() {
		return address3;
	} // getAddress3
	
	/** Method that sets the client address 3.
	 * @param address1 - The client address 3.
	 */
	public void setAddress3(String address3) {
		this.address3 = address3;
	} // setAddress3

	/** Method that gets the client additional info 1.
	 * @return String - The client info1.
	 */
	public String getInfo1() {
		return info1;
	} // getInfo1

	/** Method that sets the client additional info 1.
	 * @param info1 - The client info 1.
	 */
	public void setInfo1(String info1) {
		this.info1 = info1;
	} // setInfo1

	/** Method that gets the client additional info 2.
	 * @return String - The client info 2.
	 */
	public String getInfo2() {
		return info2;
	} // getInfo2

	/** Method that sets the client additional info 2.
	 * @param info1 - The client info 2.
	 */
	public void setInfo2(String info2) {
		this.info2 = info2;
	} // setInfo2

	/** Method that gets the client additional info 3.
	 * @return String - The client info 3.
	 */
	public String getInfo3() {
		return info3;
	} // getInfo3

	/** Method that sets the client additional info 3.
	 * @param info1 - The client info 3.
	 */
	public void setInfo3(String info3) {
		this.info3 = info3;
	} // setInfo3
	
} // Client
