package servletControllers.FamilyWeb;

import databaseControllers.FamilyWeb.DatabaseInterface;
import databaseControllers.FamilyWeb.MySQLDao;
import domain.FamilyWeb.Administrator;

public class LoginController {
	private static LoginController instance;
	private DatabaseInterface databaseInterface = null;

	private LoginController(){
		this.databaseInterface = new MySQLDao();
		instance = this;
	}

	public LoginController(DatabaseInterface databaseInterface) {
		this.databaseInterface = databaseInterface;
		instance = this;
	}

	public static LoginController getInstance() {
		if(instance == null){
			instance = new LoginController();
		}
		return instance;
	}

	public boolean authentication(String username, String password) {
		return databaseInterface.authentication(username, password);
	}

	public boolean isAdministrator(Object userObject) {
		return (userObject instanceof Administrator);
	}

	public Object getUser(String username) {
		return databaseInterface.getUser(username);
	}
	
}
