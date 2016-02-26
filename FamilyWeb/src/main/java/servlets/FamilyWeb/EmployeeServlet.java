package servlets.FamilyWeb;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import servletControllers.FamilyWeb.OverviewController;
import util.FamilyWeb.MailService;
import util.FamilyWeb.Validation;
import databaseControllers.FamilyWeb.DatabaseInterface;
import domain.FamilyWeb.Administrator;
import domain.FamilyWeb.Socialworker;
import domain.FamilyWeb.User;

@SuppressWarnings("serial")
public class EmployeeServlet extends HttpServlet {

	private final String MESSAGE_SUCCESS = "success";
	private final String MESSAGE_ERROR = "error";
	
	private final String PAGE_EMPLOYEE_OVERVIEW = "/administrator/employee_overview.jsp";
	private final String PAGE_EMPLOYEE_ADD_EDIT = "/administrator/add_edit_employee.jsp";
	
	private RequestDispatcher reqDisp = null;
	private HttpServletRequest req = null;
	private User user = null;
	private User currentUser = null;
	private Validation validation = Validation.getInstance();
	
	private String option = "";

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		this.req = req;
		// get the option that is called this clould be create client, update client or summary before update
		option = (req.getParameter("option") != null) ? (String) req.getParameter("option") : "";

		// Get current user
		Object cUser = req.getSession().getAttribute("user");
		currentUser = (cUser instanceof Administrator) ? (Administrator) cUser : (Socialworker) cUser;

		// Check wich option is choosen
		if (option.equals("create")) {
			this.create();
			req.setAttribute("option", "create");
			
		} else if (option.equals("update")) {
			this.update();

		} else if (option.equals("summary")) {

			// get the userID to summary the user this could be socialworker or administrator 
			if (req.getParameter("currentID") != null) {
				int userID = Integer.valueOf((String) req.getParameter("currentID"));
				this.summary(userID);
			} else {
				this.setMessage(MESSAGE_ERROR, "Onverwachte fout opgetreden, werknemer niet gevonden.");
				reqDisp = req.getRequestDispatcher(PAGE_EMPLOYEE_OVERVIEW);
			}
			
		// Option is empty wrong call.
		} else {
			this.setMessage(MESSAGE_ERROR, "Onverwachte fout opgetreden, pagina niet gevonden.");
			reqDisp = req.getRequestDispatcher(PAGE_EMPLOYEE_OVERVIEW);
		}
		reqDisp.forward(req, resp);
	}
	
	/**
	 * Method to create new user. 
	 */
	private void create() {

		String message = "";
		user = (req.getParameter("is_administrator") != null) ? new Administrator() : new Socialworker();
		
		// Give user object acces to the databaseinterface.
		user.setDbController((DatabaseInterface) this.getServletContext().getAttribute("dbController"));
		
		// Validate the input, if return is empty string validation is successful
		message = this.setValidation();
		
		// If message equals empty the validation is successful
		if (message.equals("")) {
			
			// Add user to the database
			user.addDB();
			
			// Send information/password mail to the new added user
			String mailSubject = "Welkom bij FamilyWeb!"; 
			String mailMessage = "<div class='text'><p>Beste <span class='bold_text'>" + user.getForename() + "</span>,</p><p>Er is een account aangemaakt op FamilyWeb met uw e-mailadres.</p><p>U kunt nu inloggen op <a href='familyweb.balans.nl'>Familyweb</a> met de volgende gegevens:</p></div><div class='information'><table class='custom_table'><tr class='row'><td class='data'>Gebruikersnaam</td><td class='data'>" + this.user.getUsername() + "</td></tr><tr class='row'><td class='data'>Wachtwoord</td><td class='data'>" + this.user.getPassword() + "</td></tr></table></div><div class='text'><p>Mochten er zich problemen voordoen met het inloggen of met het gebruik van de applicatie dan kunt u contact opnemen met de <a href='mailto:info@familyweb.nl'>administrator.</a></p><p>Wij hopen dat u een fijne ervaring heeft met de applicatie.</p><p>FamilyWeb</p></div>";
			MailService mailService = new MailService(this.user, mailSubject, mailMessage);
			message += (mailService.sendMail()) ? message : message + " Mailservice fout de mail is niet verzonden, Raadpleeg de administrator om het wachtwoord te resetten.";
			
			// If message is not empty mail is send.
			if (message.equals("")) {
				message += "Gebruiker " + user.getForename() + " " + user.getSurname() + " succesvol toegevoegd.";
				this.setMessage(MESSAGE_SUCCESS, message);
			} else {
				this.setMessage(MESSAGE_ERROR, message);
			}
			
			// Refresh employee overview page
			try {
				// If currentUser is administrator refresh autocomplete that is required to choose socialworker 
				if (currentUser instanceof Administrator) {
					req.getSession().setAttribute("users", OverviewController.getInstance().autoComplete(currentUser));
				}
				req.getSession().setAttribute("usersJSON", OverviewController.getInstance().refreshOverviewUsers(user));
			} catch (JSONException e) {
				message += " Overzicht door een onbekende fout niet herladen.";
				this.setMessage(MESSAGE_ERROR, message); //e.printStackTrace();
			}
			
			reqDisp = req.getRequestDispatcher(PAGE_EMPLOYEE_OVERVIEW);
			
		// Validation failed
		} else {
			this.setMessage(MESSAGE_ERROR, message);
			reqDisp = req.getRequestDispatcher(PAGE_EMPLOYEE_ADD_EDIT);
		}
	}

	/**
	 * Method to update user.
	 */
	private void update() {
		
		String message = "";
		
		// Get the correct id to update user.
		if (req.getParameter("userID") != null) {
			try {
				int userID = Integer.valueOf(req.getParameter("userID"));
				this.summary(userID);
			} catch (NumberFormatException e) {
				message += "User niet gevonden."; //e.printStackTrace();
			}
		}
		
		// Get the User object
		Object employeeObject = req.getAttribute("employee");	
		if (employeeObject != null) {
			
			this.user = (employeeObject instanceof Administrator) ? (Administrator) employeeObject : (Socialworker) employeeObject;
			
			// Validate the input, if return is empty string validation is successful
			message = this.setValidation();
			
			// If message equals empty the validation is successful
			if (message.equals("")) {
				
				// Check if password reset is called
				if (req.getParameter("reset_password") != null) {
					// set password reset true
					user.setWwreset(true);
					String password = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);
					user.setPassword(password);
					
					// Send information/ new password mail to the user
					String mailSubject = "FamilyWeb wachtwoord resetten"; 
					String mailMessage = "<div class='text'><p>Beste <span class='bold_text'>" + user.getForename() + "</span>,</p><p>Er is een wachtwoord reset aangevraagd.</p><p>U kunt nu inloggen op <a href='familyweb.balans.nl'>Familyweb</a> met de volgende gegevens:</p></div><div class='information'><table class='custom_table'><tr class='row'><td class='data'>Gebruikersnaam</td><td class='data'>" + this.user.getUsername() + "</td></tr><tr class='row'><td class='data'>Wachtwoord</td><td class='data'>" + this.user.getPassword() + "</td></tr></table></div><div class='text'><p>Mochten er zich problemen voordoen met het inloggen of met het gebruik van de applicatie dan kunt u contact opnemen met de <a href='mailto:info@familyweb.nl'>administrator.</a></p><p>Wij hopen dat u een fijne ervaring heeft met de applicatie.</p><p>FamilyWeb</p></div>";
					MailService mailService = new MailService(this.user, mailSubject, mailMessage);
					message += (mailService.sendMail()) ? message : message + " Mailservice fout de mail is niet verzonden, Raadpleeg de administrator om het wachtwoord te resetten.";
				}
				
				// Update user
				user.updateDB();
				req.removeAttribute("employee");
				message = "Employee " + user.getForename() + " " + user.getSurname() + " succesvol bijgewerkt.";
				this.setMessage(MESSAGE_SUCCESS, message);
				
				// Refresh employee overview page
				try {
					// If currentUser is administrator refresh autocomplete that is required to choose socialworker 
					if (currentUser instanceof Administrator) {
						req.getSession().setAttribute("users", OverviewController.getInstance().autoComplete(currentUser));
					}
					req.getSession().setAttribute("usersJSON", OverviewController.getInstance().refreshOverviewUsers(user));
				} catch (JSONException e) {
					message += " Overzicht door een onbekende fout niet herladen.";
					this.setMessage(MESSAGE_ERROR, message); //e.printStackTrace();
				}
				reqDisp = req.getRequestDispatcher(PAGE_EMPLOYEE_OVERVIEW);
				
			// Validation failed
			} else {
				this.setMessage(MESSAGE_ERROR, message);
				reqDisp = req.getRequestDispatcher(PAGE_EMPLOYEE_ADD_EDIT);
			}
		
		// User not found
		} else {
			message = "Onverwachte fout opgetreden, werknemer niet gevonden.";
			this.setMessage(MESSAGE_ERROR, message);
			reqDisp = req.getRequestDispatcher(PAGE_EMPLOYEE_OVERVIEW);
		}
	}
	
	/**
	 * Method is used by create or update method, to set the attributes and validate the input.
	 */
	private String setValidation() {

		String message = "";
		
		// Load input into variables
		String forename = validation.validateForename(req.getParameter("forename"));
		String surname = validation.validateSurname(req.getParameter("surname"));
		String dateOfBirth = req.getParameter("dateofbirth");
		String nationality = validation.validateNationality(req.getParameter("nationality"));
		String street = validation.validateStreet(req.getParameter("street"));
		String housenumber = validation.validateHouseNumber(req.getParameter("streetnumber"));
		String postcode = validation.validatePostcode(req.getParameter("postcode"));
		String city = validation.validateCity(req.getParameter("city"));
		String phonenumber = validation.validateTelephoneNumber(req.getParameter("phonenumber"));
		String mobile = validation.validateMobilePhoneNumber(req.getParameter("mobile"));
		String email = validation.validateEmail(req.getParameter("email"), req.getParameter("email_confirmation"));
		
		if (forename != null) { user.setForename(forename); } else { message += "Voornaam, "; }
		if (surname != null) { user.setSurname(surname); } else { message += "Achternaam, "; }
		if (!dateOfBirth.equals("")) {
			String[] parts = dateOfBirth.split("-");
			String year = parts[0]; 
			String month = parts[1]; 
			String date = parts[2];
			
			// Validate dateofbirth
			Date dateofbirth = validation.validateDateOfBirth(date, month, year);
			if (dateofbirth != null) {
				user.setDateOfBirth(dateofbirth);
			} else {
				message += "Geboortedatum, ";
			}
		} else {
			message += "Geboortedatum, ";
		}
		
		// Check if input is correct
		if (nationality != null) { user.setNationality(nationality); } else { message += "Nationaliteit, "; }
		if (street != null) { user.setStreet(street); } else { message += "Straat, "; }
		if (housenumber != null) { user.setHouseNumber(housenumber); } else { message += "Huisnummer, "; }
		if (postcode != null) { user.setPostcode(postcode); } else { message += "Postcode, "; }
		if (city != null) { user.setCity(city); } else { message += "Woonplaats, "; }
		if (phonenumber != null) { user.setTelephoneNumber(phonenumber); } else { message += "Telefoonnummer, "; }
		if (mobile != null) { user.setMobilePhoneNumber(mobile); } else { message += "Mobielnummer, "; }
		if (email != null) { user.setEmail(email); } else { message += "Email, "; }

		user.setActive((req.getParameter("is_active") != null ? true : false));
		
		// Check if option is create 
		if (!option.equals("update")) {
		
		String EmployeeNumber = validation.validateEmployeeNumber((req.getParameter("employeenumber")));
		String username = validation.validateUsername(req.getParameter("username"));
		
		// Create new random password
		String password = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);
		user.setPassword(password);
		
		// Validate username and employeenumber
		if (username != null) { user.setUsername(username); } else { message += "Gebruikersnaam, "; }
		if (EmployeeNumber != null) { user.setEmployeeNumber(EmployeeNumber); } else { message += "Personeelsnummer, "; }
		
		message += (!message.equals("")) ? "niet correct ingevuld." : "";
		
		// Check if email/employeenumber/username already exist.
		message += (!this.emailExist((email != null) ? email : "") ? "" : " Email bestaat al in het systeem.");
		message += (!this.employeeNumberExist((EmployeeNumber != null) ? EmployeeNumber : "") ? "" : " Personeelsnummer bestaat al in het systeem.");
		message += (!this.usernameExist((username != null) ? username : "") ? "" : " Gebruikersnaam bestaat al in het systeem.");
			if (!message.equals("")) {
				req.setAttribute("employee", user);
				req.setAttribute("option", "create");
			}
		} else {
			message += (!message.equals("")) ? "niet correct ingevuld." : "";
		}
		
		// If the message is not empty the validation failed
		return message;
	}

	/**
	 * Method to summary user, load the user object based on the userID
	 * @param userID the user id used to load the correct user.
	 */
	private void summary(int userID) {
		this.user = null;
		for (User u : currentUser.getDbController().getAllUsers()) {
			if (userID == u.getUser_id()) {
				this.user = u;
				break;
			}
		}
		req.setAttribute("employee", user);
		reqDisp = req.getRequestDispatcher(PAGE_EMPLOYEE_ADD_EDIT);
	}
	
	/**
	 * Method to check if email already exist in database, must be unique 
	 * @param email String email
	 * @return true if already exist
	 */
	private boolean emailExist(String email) {
		for (User u : currentUser.getDbController().getAllUsers()) {
			if (u.getEmail().equals(email)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Method to check if employeenumber already exist in database, must be unique 
	 * @param employeenumber String employeenumber
	 * @return true if already exist
	 */
	private boolean employeeNumberExist(String employeenumber) {
		for (User u : currentUser.getDbController().getAllUsers()) {
			if (u.getEmployeeNumber().equals(employeenumber)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Method to check if username already exist in database, must be unique
	 * @param username String username
	 * @return true if already exist
	 */
	private boolean usernameExist(String username) {
		for (User u : currentUser.getDbController().getAllUsers()) {
			if (u.getUsername().equals(username)) {
				return true;
			}
		}
		return false;
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