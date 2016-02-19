package servletControllers.FamilyWeb;

import databaseControllers.FamilyWeb.DatabaseInterface;
import databaseControllers.FamilyWeb.MySQLDao;
import domain.FamilyWeb.Administrator;

/**
 * The Class LoginController.
 */
public class LoginController {

	/** The lc. */
	private static LoginController lc;
	
	/** The db. */
	private DatabaseInterface db = null;
	
	/**
	 * Instantiates a new login controller.
	 */
	private LoginController(){
		this.db = new MySQLDao();
		lc = this;
	}
	
	/**
	 * Instantiates a new login controller.
	 *
	 * @param db the db
	 */
	public LoginController(DatabaseInterface db) {
		this.db = db;
		lc = this;
	}
	
	/**
	 * Gets the single instance of LoginController.
	 *
	 * @return single instance of LoginController
	 */
	public static LoginController getInstance() {
		if(lc == null){
			lc = new LoginController();
		}
		return lc;
	}
	
	/**
	 * Authentication send to database.
	 *
	 * @param username the username
	 * @param password the password
	 * @return true, if successful
	 */
	public boolean authentication(String username, String password) {
		return db.authentication(username, password);
	}
	
	/**
	 * Checks if object is an administrator.
	 *
	 * @param userObject the user object
	 * @return true, if is administrator
	 */
	public boolean isAdministrator(Object userObject) {
		return (userObject instanceof Administrator) ? true : false; 
	}
	
	/**
	 * Get the user from database.
	 *
	 * @param username the username
	 * @return the user
	 */
	public Object getUser(String username) {
		return db.getUser(username);
	}
	
}
