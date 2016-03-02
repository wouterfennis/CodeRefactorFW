package servlets.FamilyWeb;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import servletControllers.FamilyWeb.OverviewController;
import domain.FamilyWeb.Client;
import domain.FamilyWeb.User;

/**
 * Servlet implementation class FamilyInformation
 */
public class FamilyInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	User user;
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// get the user that is logged in
		user = (User) req.getSession().getAttribute("user");
		RequestDispatcher reqDisp = null;		
		// check if user is an correct user
		if(user != null){
			// get the client that is selected
			int client_id = Integer.parseInt((req.getParameter("currentID").trim()));
			// get clients from the user
			ArrayList<Client> clients = user.getDbController().getAllClientsOfUser(user);		
			Client client = null;
			// check if user has clients
			if(clients != null && !clients.isEmpty()){
				// for each client, look if the client is the same as selected
				for(Client c : clients){
					// ifso, set the client in an variable
					if(c.getClient_id() == client_id)
						client = c;
				}
			}
			// if no client was found, return to the overview
			if(client == null){
				req.setAttribute("messageType", "error");
				req.setAttribute("message", "Kon de Client niet goed inladen.");
				reqDisp = req.getRequestDispatcher("/socialworker/client_overview.jsp");
			}
			// setup the sub_ page
			else{
				// set the survey names in the session
				ArrayList<String> surveys = user.getDbController().getSurveyNames();
				// check if surveynames exists, if not return the user to the startscreen
				if(surveys == null || surveys.isEmpty()){
					req.setAttribute("message", "Kon geen vragenlijsten vinden.");
					req.setAttribute("messageType", "error");
					reqDisp = req.getRequestDispatcher("/socialworker/startscreen_socialworker.jsp");
				}
				// go further to setup de sub_page
				else{
					// set the client that was selected into the session
					req.getSession().setAttribute("client", client);	
					// set the surveynames into the session
					req.getSession().setAttribute("surveys", surveys);	
					
					try {
						// get the networks from the client and his familymembers
						JSONObject[] networks = OverviewController.getInstance().createJSONNetworkOfClient(client);
						// set the familymembers into an JSON file for the familymembers_overview
						req.getSession().setAttribute("familyJSON", OverviewController.getInstance().refreshFamilymembers(client));
						// set networks from the client into the session
						req.getSession().setAttribute("nodesNetwork", networks[0]);
						req.getSession().setAttribute("linksNetwork", networks[1]);
						// send user to the sub_page with as startscreen family_members_overview
						reqDisp = req.getRequestDispatcher("/socialworker/family/family_members_overview.jsp");
					} catch (JSONException e) {
						// return user to startscreen, because JSON error
						req.setAttribute("message", "Kan degegevens van "+client.getForename()+" "+client.getSurname()+" niet goed ophalen, log opnieuw in en probeer het opnieuw.");
						req.setAttribute("messageType", "error");
						reqDisp = req.getRequestDispatcher("/socialworker/startscreen_socialworker.jsp");						
					}				
					
				}
			}
		}
		// return the seesion to the login page
		else{
			req.setAttribute("message", "Er is iets onverwachts gelopen, probeer opnieuw in te loggen.");
			req.setAttribute("messageType", "error");
			reqDisp = req.getRequestDispatcher("/login.jsp");
		}		
		reqDisp.forward(req, resp);
	}

}
