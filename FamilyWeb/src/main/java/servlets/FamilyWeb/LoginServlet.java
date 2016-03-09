package servlets.FamilyWeb;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.FamilyWeb.User;
import servletControllers.FamilyWeb.LoginController;
import servletControllers.FamilyWeb.OverviewController;

import org.json.JSONException;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {

	private LoginController controller = LoginController.getInstance();
	private HttpServletRequest req = null;
	private RequestDispatcher reqDisp = null;
	
	private final String PAGE_STARTSCREEN_ADMINISTRATOR = "/administrator/startscreen_administrator.jsp";
	private final String PAGE_STARTSCREEN_SOCIALWORKER = "/socialworker/startscreen_socialworker.jsp";
	private final String PAGE_PASSWORD_RESET = "/password_reset.jsp";
	private final String PAGE_LOGIN = "/login.jsp";

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		this.req = req;
		
		if (req.getParameter("username") == null && req.getParameter("password") == null) {
			this.setMessage("warning", "Gebruikersnaam of wachtwoord niet ingevuld.");
			reqDisp = req.getRequestDispatcher(PAGE_LOGIN);
		} else {

			// Get username and password from form
			String username = req.getParameter("username").trim();
			String password = req.getParameter("password").trim();

			if (!username.equals("") && !password.equals("")) {

				// check if username/password exists in db
				if (controller.authentication(username, password)) {

					// get the user account
					User user = (User) controller.getUser(username);
					req.getSession().setAttribute("user", user);

					// check if user is administrator and is active and no password reset
					if (controller.isAdministrator(user) && user.isActive() && !user.isWwreset()) {
						try {
							// load/set users and clients in overview tables
							req.getSession().setAttribute("usersJSON", OverviewController.getInstance().refreshOverviewUsers(user));
							req.getSession().setAttribute("clientsJSON", OverviewController.getInstance().refreshOverviewClients(user));
							// load/set users for autocomple client add/edit page
							req.getSession().setAttribute("users", OverviewController.getInstance().autoComplete(user, Logger.getAnonymousLogger()));
							reqDisp = req.getRequestDispatcher(PAGE_STARTSCREEN_ADMINISTRATOR);
						} catch (JSONException e) {
							this.setMessage("error", "Kon de gegevens niet goed inladen, probeer opnieuw in te loggen.");
							reqDisp = req.getRequestDispatcher(PAGE_LOGIN);
						}
					//  check if user is socialworker and is active and no password reset
					} else if (!controller.isAdministrator(user) && user.isActive() && !user.isWwreset()) {
						try {
							// load/set clients in overview tables
							req.getSession().setAttribute("clientsJSON", OverviewController.getInstance().refreshOverviewClients(user));
							req.getSession().setAttribute("clients",user.getDbController().getAllClientsOfUser(user));
							reqDisp = req.getRequestDispatcher(PAGE_STARTSCREEN_SOCIALWORKER);
						} catch (JSONException e) {
							this.setMessage("error", "Kon de gegevens niet goed inladen, probeer opnieuw in te loggen.");
							reqDisp = req.getRequestDispatcher(PAGE_LOGIN);
						}
					// check if user is active and password must reset first
					} else if (user.isActive() && user.isWwreset()) {
						this.setMessage("warning", "Wachtwoord reset aangevraagd, verander het wachtwoord.");
						reqDisp = req.getRequestDispatcher(PAGE_PASSWORD_RESET);
					// the user is not active 
					} else {
						this.setMessage("warning", "Je kunt niet inloggen, je account is niet actief.");
						reqDisp = req.getRequestDispatcher(PAGE_LOGIN);
					}
				}
				// not valid login, username/password combination is wrong
				else {
					this.setMessage("warning", "Gebruikersnaam of wachtwoord onjuist.");
					reqDisp = req.getRequestDispatcher(PAGE_LOGIN);
				}
				// not valid login, username/password is null or empty
			} else {
				this.setMessage("error", "Gebruikersnaam of wachtwoord niet ingevuld.");
				reqDisp = req.getRequestDispatcher(PAGE_LOGIN);
			}
		}
		reqDisp.forward(req, resp);
	}
	
	/**
	 * Method to set information message on page.
	 * @param messageType String type of message could be success, error or warning
	 * @param message String the message to display
	 */
	private void setMessage(String messageType, String message) {
		req.setAttribute("messageType", messageType);
		req.setAttribute("message", message);
	}
}
