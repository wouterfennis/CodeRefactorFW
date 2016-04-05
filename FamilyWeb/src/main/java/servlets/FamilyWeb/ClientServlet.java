package servlets.FamilyWeb;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import servletControllers.FamilyWeb.OverviewController;
import util.FamilyWeb.Validation;
import databaseControllers.FamilyWeb.DatabaseInterface;
import domain.FamilyWeb.Administrator;
import domain.FamilyWeb.Client;
import domain.FamilyWeb.Socialworker;
import domain.FamilyWeb.User;

@SuppressWarnings("serial")
public class ClientServlet extends HttpServlet {

	private final String MESSAGE_SUCCESS = "success";
	private final String MESSAGE_ERROR = "error";
	
	private String PAGE_CLIENT_OVERVIEW = "client_overview.jsp";
	private String PAGE_CLIENT_ADD_EDIT = "add_edit_client.jsp";

	private RequestDispatcher reqDisp = null;
	private HttpServletRequest req = null;
	private Client client = null;
	private User currentUser = null;
	private Validation validation = Validation.getInstance();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		this.req = req;
		// get the option that is called this clould be create client, update client or summary before update
		String option = (req.getParameter("option") != null) ? req.getParameter("option") : "" ;

		currentUser = FindCurrentUserFromHttpRequest(req) ;

		this.ChangePageClientPages(currentUser)   ;
		this.CheckChosenOptionAndAct(option) ;

		try {
			reqDisp.forward(req, resp);
		} catch (Exception e){
			this.UnexpectedError();
		}
	}

    /**
	 * Finds the User object in HttpServletRequest and converts it to a valid User object.
	 * @param req Should have the value of the current DoPost() HttpServletRequest Object
	 * @return User Object that is either Administrator or SocialWorker.
     */
	public User FindCurrentUserFromHttpRequest(HttpServletRequest req){
		User foundUser = null ;
		// Get current user
		Object cUser = req.getSession().getAttribute("user");
		foundUser= (cUser instanceof Administrator) ? (Administrator) cUser : (Socialworker) cUser;

		return  foundUser ;
	}

	/**
	 *  If currentUser is administrator refresh autocomplete that is required to choose socialworker
	    Also Check if currentuser is administrator or socialworker to load correct page
	 */
	public void ChangePageClientPages(User user){
		if (user instanceof Administrator) {
			req.getSession().setAttribute("users", OverviewController.getInstance().autoComplete(user));

			PAGE_CLIENT_OVERVIEW = "/administrator/" + PAGE_CLIENT_OVERVIEW;
			PAGE_CLIENT_ADD_EDIT = "/administrator/" + PAGE_CLIENT_ADD_EDIT;
		} else {
			PAGE_CLIENT_OVERVIEW = "/socialworker/" + PAGE_CLIENT_OVERVIEW;
			PAGE_CLIENT_ADD_EDIT = "/socialworker/" + PAGE_CLIENT_ADD_EDIT;
		}
	}

	/**
	 *  Check wich option is choosen and act accordingly
	 */
	public void CheckChosenOptionAndAct(String option){
		if (option.equals("create")) {
			this.create();
		} else if (option.equals("update")) {
			this.update();
		} else if (option.equals("summary")) {
			// get the clientID to summary the client
			this.FindClientIdForClientSummary() ;
			// Option is empty wrong call.
		} else {
			this.UnexpectedError() ;
		}
	}

	/**
	 * 	get the clientID to summary the client
	 */
	public void FindClientIdForClientSummary(){
		if (req.getParameter("currentID") != null) {
			int clientID = Integer.parseInt(req.getParameter("currentID"));
			this.summary(clientID);
		} else {
			this.UnexpectedError() ;
		}
	}

	/**
	 * Method to create new client.
	 */
	private void create() {

		String message = "";
		client = new Client();
		
		// Give client object access to the databaseinterface.
		client.setDbController((DatabaseInterface) this.getServletContext().getAttribute("dbController"));
		
		// Validate the input, if return is empty string validation is successful
		message = this.setValidation();
		
		// If currentUser is socialworker the currentUser id is used, else get the choosen socialworker. 
		int userID = 0;
		if (this.currentUser instanceof Socialworker) {
			userID = this.currentUser.getUser_id();
		} else {
			try {
				userID = Integer.valueOf(req.getParameter("socialworker_id"));
			} catch (NumberFormatException e) {
				message += "Zorgprofessional niet gevonden.";
			}
		}
		
		// If message equals empty the validation is successful
		if (message.equals("")) {
			
			// Add user to database
			client.setDateCreated(new Date());
			client.addDB(userID);

			message += "Client " + client.getForename() + " " + client.getSurname() + " succesvol toegevoegd.";
			this.setMessage(MESSAGE_SUCCESS, message);
			
			// Refresh client overview page
			try {
				req.getSession().setAttribute("clientsJSON", OverviewController.getInstance().RefreshOverviewClients(this.currentUser));
			} catch (JSONException e) {
				message += " Overzicht door een onbekende fout niet herlanden, overzicht is niet up-to-date met de database.";
				this.setMessage(MESSAGE_ERROR, message);
			}
			
			reqDisp = req.getRequestDispatcher(PAGE_CLIENT_OVERVIEW);
		
		// Validation failed
		} else {
			this.setMessage(MESSAGE_ERROR, message);
			reqDisp = req.getRequestDispatcher(PAGE_CLIENT_ADD_EDIT);
		}
	}

	/**
	 * Method to update client.
	 */
	private void update() {
		
		String message = "";
		
		// Get the correct id to update client.
		if (req.getParameter("clientID") != null) {
			try {
				int clientID = Integer.parseInt(req.getParameter("clientID"));
				this.summary(clientID);
			} catch (NumberFormatException e) {
				message += "Client niet gevonden.";
			}
		}
		
		// Get the client object
		Object clientObject = req.getAttribute("client");	
		if (clientObject != null) {
			client = (Client) clientObject;
			
			// Give client object acces to the databaseinterface.
			client.setDbController((DatabaseInterface) this.getServletContext().getAttribute("dbController"));
			
			// Validate the input, if return is empty string validation is successful
			message = this.setValidation();

			// If currentUser is socialworker the socialworker is the same and not updated, else get the choosen socialworker.
			int socialworkerID = 0;
			if(currentUser instanceof Administrator) {
				try {
						socialworkerID = Integer.valueOf(req.getParameter("socialworker_id"));
					} catch (NumberFormatException e) {
						message += "Zorgprofessional niet gevonden.";
					}
			} 
			
			// If message equals empty the validation is successful
			if (message.equals("")) {

				// Update the client in database
				client.updateDB(socialworkerID);
				req.removeAttribute("client");
				message = "Client " + client.getForename() + " " + client.getSurname() + " succesvol bijgewerkt.";
				this.setMessage(MESSAGE_SUCCESS, message);
				
				// Refresh the overviewpage
				try {
					req.getSession().setAttribute("clientsJSON", OverviewController.getInstance().RefreshOverviewClients(this.currentUser));
				} catch (JSONException e) {
					message += " Overzicht door een onbekende fout niet herlanden, overzicht is niet up-to-date met de database.";
					this.setMessage(MESSAGE_ERROR, message);
				}
				reqDisp = req.getRequestDispatcher(PAGE_CLIENT_OVERVIEW);
				
			// Validation failed
			} else {
				this.setMessage(MESSAGE_ERROR, message);
				reqDisp = req.getRequestDispatcher(PAGE_CLIENT_ADD_EDIT);
			}
		
		// Client is not found
		} else {
			this.UnexpectedError();
			reqDisp = req.getRequestDispatcher(PAGE_CLIENT_OVERVIEW);
		}
	}

	/**
	 * Method is used by create or update method, to set the attributes and validate the input.
	 */
	private String setValidation() {

		String message = "";
		
		// Load input into variables
		String filenumber = req.getParameter("filenumber");
		String forename = validation.validateForename(req.getParameter("forename"));
		String surname = validation.validateSurname(req.getParameter("surname"));
		
		String dateOfBirth = req.getParameter("dateofbirth");
		
		if (dateOfBirth != null && !dateOfBirth.equals("")) {
			String[] parts = dateOfBirth.split("-");
			String year = parts[0]; 
			String month = parts[1]; 
			String date = parts[2];

			// Validate dateofbirth
			Date dateofbirth = validation.validateDateOfBirth(date, month, year);
			if (dateofbirth != null) {
				client.setDateOfBirth(dateofbirth);
			} else {
				message += "Geboortedatum, ";
			}
		} else {
			message += "Geboortedatum, ";
		}

		String nationality = validation.validateNationality(req.getParameter("nationality"));
		String street = validation.validateStreet(req.getParameter("street"));
		String housenumber = validation.validateHouseNumber(req.getParameter("streetnumber"));
		String postcode = validation.validatePostcode(req.getParameter("postcode"));
		String city = validation.validateCity(req.getParameter("city"));
		String phonenumber = validation.validateTelephoneNumber(req.getParameter("phonenumber"));
		String mobile = validation.validateMobilePhoneNumber(req.getParameter("mobile"));
		String email = validation.validateEmail(req.getParameter("email"), req.getParameter("email_confirmation"));
		
		// Check if input is correct
		if (filenumber != null) { client.setFileNumber(filenumber); } else { message += "Dossiernummer, "; }
		if (forename != null) { client.setForename(forename); } else { message += "Voornaam, "; }
		if (surname != null) { client.setSurname(surname); } else { message += "Achternaam, "; }
		if (nationality != null) { client.setNationality(nationality); } else { message += "Nationaliteit, "; }
		if (street != null) { client.setStreet(street); } else { message += "Straat, "; }
		if (housenumber != null) { client.setHouseNumber(housenumber); } else { message += "Huisnummer, "; }
		if (postcode != null) { client.setPostcode(postcode); } else { message += "Postcode, "; }
		if (city != null) { client.setCity(city); } else { message += "Woonplaats, "; }
		if (phonenumber != null) { client.setTelephoneNumber(phonenumber); } else { message += "Telefoonnummer, "; }
		if (mobile != null) { client.setMobilePhoneNumber(mobile); } else { message += "Mobielnummer, "; }
		if (email != null) { client.setEmail(email); } else { message += "Email, "; }
		
		// If the message is not empty the validation failed
		message += (!message.equals("")) ? "niet correct ingevuld." : "";
		return message;
	}

	/**
	 * Method to summary client, load the client object based on the clientID
	 * @param clientID the client id used to load the correct client.
	 */
	private void summary(int clientID) {
		
		// Get client
		this.client = null;
		for (Client c : this.currentUser.getMyClients()) {
			if (clientID == c.getClient_id()) {
				this.client = c;
				break;
			}
		}
		
		// If currentUser is administrator load the socialworker to fill the default socialworker into autocomplete field.
		User socialworkerClient = null;
		if (currentUser instanceof Administrator) {
			for (User u : currentUser.getDbController().getAllUsers()) {
				socialworkerClient = findAllClientsOfAUser(socialworkerClient, u);
			}
			// If default socialworker found set fields
			if (socialworkerClient != null) {
				req.setAttribute("socialworkerID", String.valueOf(socialworkerClient.getUser_id()));
				req.setAttribute("socialworkerName", socialworkerClient.getForename() + " " + socialworkerClient.getSurname() + " | NR: " + socialworkerClient.getEmployeeNumber());
			}	
		}
		
		// set client
		req.setAttribute("client", client);
		reqDisp = req.getRequestDispatcher(PAGE_CLIENT_ADD_EDIT);
	}

	private User findAllClientsOfAUser(User socialworkerClient, User u) {
		for (Client c : currentUser.getDbController().getAllClientsOfUser(u)) {
            if (client.getClient_id() == c.getClient_id()) {
                socialworkerClient = u;
                break;
            }
        }
		return socialworkerClient;
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

	public void UnexpectedError(){
		this.setMessage(MESSAGE_ERROR, "Onverwachte fout opgetreden, client niet gevonden.");
		reqDisp = req.getRequestDispatcher(PAGE_CLIENT_OVERVIEW);
	}
}
