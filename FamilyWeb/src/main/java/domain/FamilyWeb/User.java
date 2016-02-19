/*
 * 
 */
package domain.FamilyWeb;


import java.util.ArrayList;

import java.util.Date;

import javax.management.Notification;

import databaseControllers.FamilyWeb.DatabaseInterface;

/**
 * The Class User.
 *
 * @author Team HIS
 * @version 0.1
 * @since 2015-04-20
 */
public abstract class User {

	/** The username. */
	private String username;
	
	/** The password. */
	private String password;
	
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
	
	/** The is active. */
	private boolean isActive;
	
	/** The employee number. */
	private String employeeNumber;
	
	/** The user_id. */
	private int user_id;
	
	/** The wwreset. */
	private boolean wwreset;

	/** The notifications. */
	private ArrayList<Notification> notifications;
	
	/** The my clients. */
	private ArrayList<Client> myClients;
	
	/** The db controller. */
	private DatabaseInterface dbController;

	/**
	 * Constructor with fields.
	 *
	 * @param username the username
	 * @param password the password
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
	 * @param isActive the is active
	 * @param employeeNumber the employee number
	 */
	protected User(String username, String password, String forename,
			String surname, Date dateOfBirth, String postcode, String street,
			String houseNumber, String city, String nationality,
			String telephoneNumber, String mobilePhoneNumber, String email,
			boolean isActive, String employeeNumber) {
		this.username = username;
		this.password = password;
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
		this.isActive = isActive;
		this.employeeNumber = employeeNumber;
		this.notifications = new ArrayList<Notification>();
		this.myClients = new ArrayList<Client>();
		this.setWwreset(true);
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
	protected User() {
		this.notifications = new ArrayList<Notification>();
		this.myClients = new ArrayList<Client>();
	}
	
	/**
	 * Adds the db.
	 *
	 * @return true, if successful
	 */
	public boolean addDB() {
		return this.dbController.addUser(this);
	}

	/**
	 * Update db.
	 *
	 * @return true, if successful
	 */
	public boolean updateDB() {
		return this.dbController.updateUser(this);
	}

	/**
	 * Checks if is wwreset.
	 *
	 * @return the wwreset
	 */
	public boolean isWwreset() {
		return wwreset;
	}

	/**
	 * Sets the wwreset.
	 *
	 * @param wwreset            the wwreset to set
	 */
	public void setWwreset(boolean wwreset) {
		this.wwreset = wwreset;
	}
	
	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * Checks if is active.
	 *
	 * @return true, if is active
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * Sets the active.
	 *
	 * @param isActive the new active
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * Gets the employee number.
	 *
	 * @return the employee number
	 */
	public String getEmployeeNumber() {
		return employeeNumber;
	}

	/**
	 * Sets the employee number.
	 *
	 * @param employeeNumber the new employee number
	 */
	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	/**
	 * Gets the user_id.
	 *
	 * @return the user_id
	 */
	public int getUser_id() {
		return user_id;
	}

	/**
	 * Sets the user_id.
	 *
	 * @param user_id the new user_id
	 */
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	/**
	 * Gets the notifications.
	 *
	 * @return the notifications
	 */
	public ArrayList<Notification> getNotifications() {
		return notifications;
	}

	/**
	 * Sets the notifications.
	 *
	 * @param notifications the new notifications
	 */
	public void setNotifications(ArrayList<Notification> notifications) {
		this.notifications = notifications;
	}

	/**
	 * Gets the my clients.
	 *
	 * @return the my clients
	 */
	public ArrayList<Client> getMyClients() {
		return myClients;
	}

	/**
	 * Sets the my clients.
	 *
	 * @param myClients the new my clients
	 */
	public void setMyClients(ArrayList<Client> myClients) {
		this.myClients = myClients;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [Username = " + getUsername() + ", Password = "
				+ getPassword() + ", Forename = " + getForename()
				+ ", Surname = " + getSurname() + ", DateOfBirth = "
				+ getDateOfBirth() + ", Postcode = " + getPostcode()
				+ ", Street = " + getStreet() + ", HouseNumber = "
				+ getHouseNumber() + ", City = " + getCity()
				+ ", Nationality = " + getNationality()
				+ ", TelephoneNumber = " + getTelephoneNumber()
				+ ", MobilePhoneNumber = " + getMobilePhoneNumber()
				+ ", Email = " + getEmail() + ", isActive = " + isActive()
				+ ", EmployeeNumber = " + getEmployeeNumber() + "]";
	}

}