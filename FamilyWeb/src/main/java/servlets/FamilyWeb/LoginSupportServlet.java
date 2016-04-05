package servlets.FamilyWeb;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.FamilyWeb.MailService;
import databaseControllers.FamilyWeb.DatabaseInterface;
import domain.FamilyWeb.User;

@SuppressWarnings("serial")
public class LoginSupportServlet extends HttpServlet {

	private final String PAGE_LOGIN = "/login.jsp";

    private HttpServletRequest httpReqField = null;

    @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		this.httpReqField = req;
		DatabaseInterface db = (DatabaseInterface) this.getServletContext().getAttribute("dbController");

		// get the option that is called this clould be forgotPassword, forgotUsername
        String option = (req.getParameter("option") != null) ? req.getParameter("option") : "";

		// If forgot password called.
		if (option.equals("forgotPassword")) {
			this.ForgotPassword(req, resp, db);
		}
		// If forgot username called.
		else if (option.equals("forgotUsername")) {
			this.ForgotUsername(req, resp, db, "FamilyWeb Gebruikersnaam vergeten");
		}

	}

	private void ForgotUsername(HttpServletRequest req, HttpServletResponse resp, DatabaseInterface db, String mailSubjects) throws ServletException, IOException {
		String message = "";
		User user = null;
		String email = req.getParameter("email");

        String PAGE_FORGOT_USERNAME = "/forgot_username.jsp";
        if (email != null && !email.trim().equals("")) {
            for (User u : db.getAllUsers()) {
                if (u.getEmail().equals(email.trim())) {
                    user = u;
                    break;
                }
            }

            if (user != null) {
                // Send username mail to the user.
                String mailSubject = mailSubjects;
                String mailMessage = "<div class='text'><p>Beste <span class='bold_text'>" + user.getForename() + "</span>,</p><p>Er is een gebruikersnaam vergeten aangevraagd.</p></div><div class='information'><table class='custom_table'><tr class='row'><td class='data'>Gebruikersnaam</td><td class='data'>" + user.getUsername() + "</td></tr></table></div><div class='text'><p>Mochten er zich problemen voordoen met het inloggen of met het gebruik van de applicatie dan kunt u contact opnemen met de <a href='mailto:info@familyweb.nl'>administrator.</a></p><p>Wij hopen dat u een fijne ervaring heeft met de applicatie.</p><p>FamilyWeb</p></div>";
                MailService mailService = new MailService(user, mailSubject, mailMessage);
                message += (mailService.sendMail()) ? message : message + " Mailservice fout de mail is niet verzonden, Raadpleeg de administrator.";

                if (message.equals("")) {
                    this.setMessage(new MessageInfo("success", "De gebruikersnaam is verzonden naar je mail."));
                    req.getRequestDispatcher(PAGE_LOGIN).forward(req, resp);
                } else {
                    this.setMessage(new MessageInfo("error", message));
                    req.getRequestDispatcher(PAGE_FORGOT_USERNAME).forward(req, resp);
                }
            } else {
                this.setMessage(new MessageInfo("error", "Email niet correct of niet gevonden in ons systeem."));
                req.getRequestDispatcher(PAGE_FORGOT_USERNAME).forward(req, resp);
            }
        } else {
            this.setMessage(new MessageInfo("error", "Email niet correct of niet gevonden in ons systeem."));
            req.getRequestDispatcher(PAGE_FORGOT_USERNAME).forward(req, resp);
        }
	}

	private void ForgotPassword(HttpServletRequest req, HttpServletResponse resp, DatabaseInterface db) throws ServletException, IOException {
		String message = "";
		User user = null;
		String username = req.getParameter("username").trim();

        String PAGE_FORGOT_PASSWORD = "/forgot_password.jsp";
            if (username != null && !username.equals("")) {

                for (User u : db.getAllUsers()) {
                     if (u.getUsername().equals(username.trim())) {
                        user = u;
                        break;
                     }
                }

            if (user != null) {

                // Give user object acces to the databaseinterface.
                user.setDbController((DatabaseInterface) this.getServletContext().getAttribute("dbController"));

                // set password reset true
                user.setWwreset(true);
                String password = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);
                user.setPassword(password);

                // Send information/ new password mail to the user
                String mailSubject = "FamilyWeb wachtwoord vergeten";
                String mailMessage = "<div class='text'><p>Beste <span class='bold_text'>" + user.getForename() + "</span>,</p><p>Er is een wachtwoord reset aangevraagd.</p><p>U kunt nu inloggen op <a href='familyweb.balans.nl'>Familyweb</a> met de volgende gegevens:</p></div><div class='information'><table class='custom_table'><tr class='row'><td class='data'>Gebruikersnaam</td><td class='data'>" + user.getUsername() + "</td></tr><tr class='row'><td class='data'>Wachtwoord</td><td class='data'>" + user.getPassword() + "</td></tr></table></div><div class='text'><p>Mochten er zich problemen voordoen met het inloggen of met het gebruik van de applicatie dan kunt u contact opnemen met de <a href='mailto:info@familyweb.nl'>administrator.</a></p><p>Wij hopen dat u een fijne ervaring heeft met de applicatie.</p><p>FamilyWeb</p></div>";
                MailService mailService = new MailService(user, mailSubject, mailMessage);
                message += (mailService.sendMail()) ? message : message + " Mailservice fout de mail is niet verzonden, Raadpleeg de administrator om het wachtwoord te resetten.";

                if (message.equals("")) {
                    user.updateDB();
                    this.setMessage(new MessageInfo("success", "Nieuw wachtwoord is verzonden naar je mail."));
                    req.getRequestDispatcher(PAGE_LOGIN).forward(req, resp);
                } else {
                    this.setMessage(new MessageInfo("error", message));
                    req.getRequestDispatcher(PAGE_FORGOT_PASSWORD).forward(req, resp);
                }

            } else {
                this.setMessage(new MessageInfo("error", "Gebruikersnaam niet correct of niet gevonden in ons systeem."));
                req.getRequestDispatcher(PAGE_FORGOT_PASSWORD).forward(req, resp);
            }
        } else {
            this.setMessage(new MessageInfo("error", "Gebruikersnaam niet correct of niet gevonden in ons systeem."));
            req.getRequestDispatcher(PAGE_FORGOT_PASSWORD).forward(req, resp);
        }
	}

	/**
	 * Method to set information message on page.
     * @param messageInfo
     */
	private void setMessage(MessageInfo messageInfo) {
		httpReqField.setAttribute("messageType", messageInfo.getMessageType());
		httpReqField.setAttribute("message", messageInfo.getMessage());
	}
}
