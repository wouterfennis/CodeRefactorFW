/**
 * 
 */
package domain.FamilyWeb;

import java.util.Date;
import java.util.ArrayList;

import databaseControllers.FamilyWeb.DatabaseInterface;

/**
 * The Class Client.
 *
 * @author Team HIS
 * @version 0.1
 * @since 2015-04-20
 */
public class Client {

	/** The client_id. */
	private int client_id;
	
	/** The forename. */
	private String forename;
	
	/** The surname. */
	private String surname;
	
	/** The date of birth. */
	private Date dateOfBirth;
	
	/** The postcode. */
	private String postcode;
	
	/** The street. */
	private String street;
	
	/** The house number. */
	private String houseNumber;
	
	/** The city. */
	private String city;
	
	/** The nationality. */
	private String nationality;
	
	/** The telephone number. */
	private String telephoneNumber;
	
	/** The mobile phone number. */
	private String mobilePhoneNumber;
	
	/** The email. */
	private String email;
	
	/** The file number. */
	private String fileNumber;
	
	/** The date created. */
	private Date dateCreated;

	/** The my familymembers. */
	private ArrayList<Familymember> myFamilymembers;
	
	/** The my networks. */
	private ArrayList<Network> myNetworks;
	
	/** The db controller. */
	private DatabaseInterface dbController;

	/**
	 * Constructor with parameters.
	 *
	 * @param forename the forename
	 * @param surname the surname
	 * @param dateOfBirth the date of birth
	 * @param postcode the postcode
	 * @param street the street
	 * @param houseNumber the house number
	 * @param city the city
	 * @param nationality the nationality
	 * @param telephoneNumber the telephone number
	 * @param mobilePhoneNumber the mobile phone number
	 * @param email the email
	 * @param fileNumber the file number
	 * @param dateCreated the date created
	 */
	public Client(String forename, String surname, Date dateOfBirth,
			String postcode, String street, String houseNumber, String city,
			String nationality, String telephoneNumber,
			String mobilePhoneNumber, String email, String fileNumber,
			Date dateCreated) {
		this.forename = forename;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
		this.postcode = postcode;
		this.street = street;
		this.houseNumber = houseNumber;
		this.city = city;
		this.nationality = nationality;
		this.telephoneNumber = telephoneNumber;
		this.mobilePhoneNumber = mobilePhoneNumber;
		this.email = email;
		this.fileNumber = fileNumber;
		this.dateCreated = dateCreated;

		this.myFamilymembers = new ArrayList<Familymember>();
		this.myNetworks = new ArrayList<Network>();
	}

	/**
	 * Gets the db controller.
	 *
	 * @return the db controller
	 */
	public DatabaseInterface getDbController() {
		return dbController;
	}

	/**
	 * Sets the db controller.
	 *
	 * @param dbController the new db controller
	 */
	public void setDbController(DatabaseInterface dbController) {
		this.dbController = dbController;
	}

	/**
	 * Clean constructor.
	 */
	public Client() {
		this.myFamilymembers = new ArrayList<Familymember>();
		this.myNetworks = new ArrayList<Network>();
	}
	
	/**
	 * Gets the client_id.
	 *
	 * @return the client_id
	 */
	public int getClient_id() {
		return client_id;
	}

	/**
	 * Sets the client_id.
	 *
	 * @param client_id the new client_id
	 */
	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

	/**
	 * Gets the forename.
	 *
	 * @return the forename
	 */
	public String getForename() {
		return forename;
	}

	/**
	 * Sets the forename.
	 *
	 * @param forename the new forename
	 */
	public void setForename(String forename) {
		this.forename = forename;
	}

	/**
	 * Gets the surname.
	 *
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Sets the surname.
	 *
	 * @param surname the new surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Gets the date of birth.
	 *
	 * @return the date of birth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * Sets the date of birth.
	 *
	 * @param dateOfBirth the new date of birth
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * Gets the postcode.
	 *
	 * @return the postcode
	 */
	public String getPostcode() {
		return postcode;
	}

	/**
	 * Sets the postcode.
	 *
	 * @param postcode the new postcode
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	/**
	 * Gets the street.
	 *
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * Sets the street.
	 *
	 * @param street the new street
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * Gets the house number.
	 *
	 * @return the house number
	 */
	public String getHouseNumber() {
		return houseNumber;
	}

	/**
	 * Sets the house number.
	 *
	 * @param houseNumber the new house number
	 */
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets the nationality.
	 *
	 * @return the nationality
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * Sets the nationality.
	 *
	 * @param nationality the new nationality
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	/**
	 * Gets the telephone number.
	 *
	 * @return the telephone number
	 */
	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	/**
	 * Sets the telephone number.
	 *
	 * @param telephoneNumber the new telephone number
	 */
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	/**
	 * Gets the mobile phone number.
	 *
	 * @return the mobile phone number
	 */
	public String getMobilePhoneNumber() {
		return mobilePhoneNumber;
	}

	/**
	 * Sets the mobile phone number.
	 *
	 * @param mobilePhoneNumber the new mobile phone number
	 */
	public void setMobilePhoneNumber(String mobilePhoneNumber) {
		this.mobilePhoneNumber = mobilePhoneNumber;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the file number.
	 *
	 * @return the file number
	 */
	public String getFileNumber() {
		return fileNumber;
	}

	/**
	 * Sets the file number.
	 *
	 * @param fileNumber the new file number
	 */
	public void setFileNumber(String fileNumber) {
		this.fileNumber = fileNumber;
	}

	/**
	 * Gets the date created.
	 *
	 * @return the date created
	 */
	public Date getDateCreated() {
		return dateCreated;
	}

	/**
	 * Sets the date created.
	 *
	 * @param dateCreated the new date created
	 */
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	/**
	 * Gets the my familymembers.
	 *
	 * @return the my familymembers
	 */
	public ArrayList<Familymember> getMyFamilymembers() {
		return myFamilymembers;
	}

	/**
	 * Sets the my familymembers.
	 *
	 * @param myFamilymembers the new my familymembers
	 */
	public void setMyFamilymembers(ArrayList<Familymember> myFamilymembers) {
		this.myFamilymembers = myFamilymembers;
	}

	/**
	 * Gets the my networks.
	 *
	 * @return the my networks
	 */
	public ArrayList<Network> getMyNetworks() {
		return myNetworks;
	}

	/**
	 * Sets the my networks.
	 *
	 * @param myNetworks the new my networks
	 */
	public void setMyNetworks(ArrayList<Network> myNetworks) {
		this.myNetworks = myNetworks;
	}

	/**
	 * Adds the db.
	 *
	 * @param userID the user id
	 * @return true, if successful
	 */
	public boolean addDB(int userID) {
		return this.dbController.addClient(this, userID);
	}

	/**
	 * Update db.
	 *
	 * @param user_id the user_id
	 * @return true, if successful
	 */
	public boolean updateDB(int user_id) {
		return this.dbController.updateClient(this, user_id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Client [Client_id = " + getClient_id() + ", Forename = "
				+ getForename() + ", Surname = " + getSurname()
				+ ", DateOfBirth = " + getDateOfBirth() + ", Postcode = "
				+ getPostcode() + ", Street = " + getStreet()
				+ ", HouseNumber =" + getHouseNumber() + ", City = "
				+ getCity() + ", Nationality = " + getNationality()
				+ ", TelephoneNumber = " + getTelephoneNumber()
				+ ", MobilePhoneNumber = " + getMobilePhoneNumber()
				+ ", Email = " + getEmail() + ", FileNumber = "
				+ getFileNumber() + ", DateCreated = " + getDateCreated() + "]";
	}

}