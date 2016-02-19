/**
 * 
 */
package domain.FamilyWeb;

import java.sql.Date;
import java.util.ArrayList;

/**
 * The Class Familymember.
 *
 * @author Team HIS
 * @version 0.1
 * @since 2015-04-20
 */
public class Familymember {
	
	/** The member_id. */
	private int member_id;
	
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

	/** The my networks. */
	private ArrayList<Network> myNetworks;
	
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
	 */
	public Familymember(String forename, String surname, Date dateOfBirth,
			String postcode, String street, String houseNumber, String city,
			String nationality, String telephoneNumber,
			String mobilePhoneNumber, String email) {
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

		this.myNetworks = new ArrayList<Network>();
	}
	
	/**
	 * Clean constructor.
	 */
	public Familymember() {
		this.myNetworks = new ArrayList<Network>();
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
	 * @param forename the forename to set
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
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Gets the date of birth.
	 *
	 * @return the dateOfBirth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * Sets the date of birth.
	 *
	 * @param dateOfBirth the dateOfBirth to set
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
	 * @param postcode the postcode to set
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
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * Gets the house number.
	 *
	 * @return the houseNumber
	 */
	public String getHouseNumber() {
		return houseNumber;
	}

	/**
	 * Sets the house number.
	 *
	 * @param houseNumber the houseNumber to set
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
	 * @param city the city to set
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
	 * @param nationality the nationality to set
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	/**
	 * Gets the telephone number.
	 *
	 * @return the telephoneNumber
	 */
	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	/**
	 * Sets the telephone number.
	 *
	 * @param telephoneNumber the telephoneNumber to set
	 */
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	/**
	 * Gets the mobile phone number.
	 *
	 * @return the mobilePhoneNumber
	 */
	public String getMobilePhoneNumber() {
		return mobilePhoneNumber;
	}

	/**
	 * Sets the mobile phone number.
	 *
	 * @param mobilePhoneNumber the mobilePhoneNumber to set
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
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the my networks.
	 *
	 * @return the myNetworks
	 */
	public ArrayList<Network> getMyNetworks() {
		return myNetworks;
	}

	/**
	 * Sets the my networks.
	 *
	 * @param myNetworks the myNetworks to set
	 */
	public void setMyNetworks(ArrayList<Network> myNetworks) {
		this.myNetworks = myNetworks;
	}

	/**
	 * Gets the member_id.
	 *
	 * @return the member_id
	 */
	public int getMember_id() {
		return member_id;
	}

	/**
	 * Sets the member_id.
	 *
	 * @param member_id the member_id to set
	 */
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Familymember [Forename = " + getForename()
				+ ", Surname = " + getSurname() + ", DateOfBirth = "
				+ getDateOfBirth() + ", Postcode = " + getPostcode()
				+ ", Street = " + getStreet() + ", HouseNumber = "
				+ getHouseNumber() + ", City = " + getCity()
				+ ", Nationality = " + getNationality()
				+ ", TelephoneNumber = " + getTelephoneNumber()
				+ ", MobilePhoneNumber = " + getMobilePhoneNumber()
				+ ", Email = " + getEmail() + "]";
	}

}
