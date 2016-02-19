/**
 * 
 */
package domain.FamilyWeb;

import java.sql.Date;
import java.util.ArrayList;

/**
 * The Class Administrator.
 *
 * @author Joery
 * @version 0.1
 * @since 2015-04-20
 */
public class Administrator extends User {
	
	/** The users. */
	protected ArrayList<User> users;
	
	/**
	 * Clean constructor.
	 */
	public Administrator() {
		super();
		this.users = new ArrayList<User>();
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
	 * @param users the users
	 */
	public Administrator(String username, String password, String forename,
			String surname, Date dateOfBirth, String postcode, String street,
			String houseNumber, String city, String nationality,
			String telephoneNumber, String mobilePhoneNumber, String email,
			boolean isActive, String employeeNumber, ArrayList<User> users) {
		super(username, password, forename, surname, dateOfBirth, postcode, street,
				houseNumber, city, nationality, telephoneNumber, mobilePhoneNumber,
				email, isActive, employeeNumber);
		this.users = users;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Administrator [userInfo = " + super.toString() + ", users = "
				+ this.users + "]";
	}

	/**
	 * Sets the users.
	 *
	 * @param allSocialworkers the new users
	 */
	public void setUsers(ArrayList<User> allSocialworkers) {
		this.users = allSocialworkers;
	}
	
	/**
	 * Gets the users.
	 *
	 * @return the users
	 */
	public ArrayList<User> getUsers() {
		return users;
	}
}
