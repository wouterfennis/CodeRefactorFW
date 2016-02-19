package servlets.FamilyWeb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import servletControllers.FamilyWeb.OverviewController;
import domain.FamilyWeb.Administrator;
import domain.FamilyWeb.Socialworker;
import domain.FamilyWeb.User;

@SuppressWarnings("serial")
public class PasswordresetServlet extends HttpServlet { 

	private HttpServletRequest req = null;
	
	private final String PAGE_LOGIN = "/login.jsp";
	private final String PAGE_PASSWORD_RESET = "/password_reset.jsp";
	private final String PAGE_STARTSCREEN_ADMINISTRATOR = "/administrator/startscreen_administrator.jsp";
	private final String PAGE_STARTSCREEN_SOCIALWORKER = "/socialworker/startscreen_socialworker.jsp";
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		this.req = req;
		// get the currentUser 
		Object cUser = req.getSession().getAttribute("user");
		User currentUser = (cUser instanceof Administrator) ? (Administrator) cUser : (Socialworker) cUser;		
		
		// check if password is not null
		if (req.getParameter("old_password") != null && req.getParameter("new_password") != null && req.getParameter("new_password_confirmation") != null) {
			
			String oldpassword = req.getParameter("old_password").trim();
			String newpassword = req.getParameter("new_password").trim();
			String newpasswordConfirm = req.getParameter("new_password_confirmation").trim();
			
			// check if old password is valid and new password is equal to new password confirmation
			if (oldpassword.equals(currentUser.getPassword()) && newpassword.equals(newpasswordConfirm)) {
				
				// Update user in database and set password reset to false
				currentUser.setPassword(newpassword);
				currentUser.setWwreset(false);
				currentUser.updateDB();
				
				// Refresh the user in session
				req.getSession().setAttribute("user", currentUser.getDbController().getUser(currentUser.getUsername()));
				this.setMessage("success", "Succesvol wachtwoord veranderd.");
				
				// Check if user is administrator
				if (currentUser instanceof Administrator) {
					try {
					// load/set users and clients in overview tables
					req.getSession().setAttribute("usersJSON", OverviewController.getInstance().RefreshOverviewUsers(currentUser));
					req.getSession().setAttribute("clientsJSON", OverviewController.getInstance().RefreshOverviewClients(currentUser));
					// load/set users for autocomple client add/edit page
					req.getSession().setAttribute("users", OverviewController.getInstance().autoComplete(currentUser));
					req.getRequestDispatcher(PAGE_STARTSCREEN_ADMINISTRATOR).forward(req, resp);
					} catch (JSONException e) {
						this.setMessage("error", "Kon de gegevens niet goed inladen, probeer opnieuw in te loggen.");
						req.getRequestDispatcher(PAGE_LOGIN).forward(req, resp);
					}
				// User is socialworker
				} else {
					try {
					// load/set clients in overview tables
					req.getSession().setAttribute("clientsJSON", OverviewController.getInstance().RefreshOverviewClients(currentUser));
					req.getSession().setAttribute("clients", currentUser.getDbController().getAllClientsOfUser(currentUser));
					req.getRequestDispatcher(PAGE_STARTSCREEN_SOCIALWORKER).forward(req, resp);
					} catch (JSONException e) {
						this.setMessage("error", "Kon de gegevens niet goed inladen, probeer opnieuw in te loggen.");
						req.getRequestDispatcher(PAGE_LOGIN).forward(req, resp);
					}		
				}
				
			// input not valid, password combination is wrong
			} else {
				this.setMessage("warning", "Wachtwoord onjuist of wachtwoorden komen niet overeeen.");
				req.getRequestDispatcher(PAGE_PASSWORD_RESET).forward(req, resp);
			}
		
		// input not valid, password is null or empty
		} else {
			this.setMessage("warning", "Alle velden dienen ingevuld te worden.");
			req.getRequestDispatcher(PAGE_PASSWORD_RESET).forward(req, resp);
		}
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
