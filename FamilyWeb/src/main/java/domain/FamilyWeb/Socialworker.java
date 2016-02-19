/**
 * 
 */
package domain.FamilyWeb;

import java.sql.Date;

/**
 * The Class Socialworker.
 *
 * @author Team HIS
 * @version 0.1
 * @since 2015-04-20
 */
public class Socialworker extends User {
	
	/**
	 * Clean constructor.
	 */
	public Socialworker() {
		super();
	}
	

	/**
	 * Constructor with all parameters.
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
	public Socialworker(String username, String password, String forename,
			String surname, Date dateOfBirth, String postcode, String street,
			String houseNumber, String city, String nationality,
			String telephoneNumber, String mobilePhoneNumber, String email,
			boolean isActive, String employeeNumber) {
		super(username, password, forename, surname, dateOfBirth, postcode, street,
				houseNumber, city, nationality, telephoneNumber, mobilePhoneNumber,
				email, isActive, employeeNumber);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Socialworker [userInfo = " + super.toString() + "]";
	}
}
