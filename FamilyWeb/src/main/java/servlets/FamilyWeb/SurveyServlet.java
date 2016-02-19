package servlets.FamilyWeb;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import servletControllers.FamilyWeb.OverviewController;
import domain.FamilyWeb.Answer;
import domain.FamilyWeb.Client;
import domain.FamilyWeb.Contact;
import domain.FamilyWeb.Network;
import domain.FamilyWeb.Question;
import domain.FamilyWeb.Result;
import domain.FamilyWeb.Survey;
import domain.FamilyWeb.User;

/**
 * Servlet implementation class SurveyServlet
 */
public class SurveyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// get user from session
		User user = (User) req.getSession().getAttribute("user");
		RequestDispatcher reqDisp = null;	
		if(user != null){			
			// get the interviewee
			int client_id = Integer.valueOf(req.getSession().getAttribute("intervieweeC").toString()); 
			int family_id = Integer.valueOf(req.getSession().getAttribute("intervieweeF").toString());
			
			// create the new network with basic input
			Network newNetwork = new Network(new Date(new java.util.Date().getTime()), req.getParameter("general_comment"));
			// get the survey
			Survey survey = (Survey) req.getSession().getAttribute("survey");
			// get all contacts from session
			ArrayList<Contact> contacts = (ArrayList<Contact>) req.getSession().getAttribute("contacts");			
			// set the survey to the network
			newNetwork.setTheSurvey(survey);			
			// for each contact, loop
			for(Contact c : contacts){
				// initialize result arraylist
				ArrayList<Result> results = new ArrayList<Result>();
				// for each question in the survey, loop
				for(Question question : survey.getQuestions()){
					// get answerId from input
					int answerID = Integer.parseInt(req.getParameter(question.getQuestion_id()+":"+c.getContact_id()));
					Answer answer = null;
					// find Answer in the survey that is equal to the input
					for(Answer a : question.getTheAnswers()){
						if(a.getAnswer_id()==answerID)
							answer = a;
					}
					// set result
					results.add(new Result(question,answer));
				}
				// set each results to the contact
				c.setMyResults(results);
			}
			// set contacts to the network
			newNetwork.setContacts(contacts);
			// add network to the dataabse
			user.getDbController().addNetwork(newNetwork, client_id, family_id);
			JSONObject[] networks;
			try {
				// refresh networks in the session and send user to sub_page overview
				networks = OverviewController.getInstance().createJSONNetworks((Client) req.getSession().getAttribute("client"));
				req.getSession().setAttribute("nodesNetwork", networks[0]);
				req.getSession().setAttribute("linksNetwork", networks[1]);
				req.setAttribute("message", "Het nieuwe netwerk is toegevoegd.");
				req.setAttribute("messageType", "succes");
				reqDisp = req.getRequestDispatcher("/socialworker/family/family_members_overview.jsp");
			} catch (JSONException e) {			
				// set succes message but JSON error, so send user to startscreen
				req.setAttribute("message", "Het nieuwe netwerk is toegevoegd.");
				req.setAttribute("messageType", "succes");
				reqDisp = req.getRequestDispatcher("/socialworker/startscreen_socialworker.jsp");
			}
		}else{
			// someting went wrong and send session to login
			req.setAttribute("message", "Er is iets onverwachts gelopen, probeer opnieuw in te loggen.");
			req.setAttribute("messageType", "error");
			reqDisp = req.getRequestDispatcher("/login.jsp");
		}
		reqDisp.forward(req, resp);
	}
}