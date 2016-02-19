package util.FamilyWeb;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import databaseControllers.FamilyWeb.DatabaseInterface;
import databaseControllers.FamilyWeb.MySQLDao;
import servletControllers.FamilyWeb.LoginController;

public class MyServletContextListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {	
		// set MySQLDao as standard databaseInterface
		DatabaseInterface dbController = new MySQLDao();
		
		// initialize controllers
		new LoginController(dbController);
		new Validation();
		// set controller in session
		sce.getServletContext().setAttribute("dbController", dbController);		
	}

	public void contextDestroyed(ServletContextEvent sce) { }

}
