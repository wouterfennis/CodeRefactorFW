package databaseControllers.FamilyWeb;

import java.util.ArrayList;

import domain.FamilyWeb.Client;
import domain.FamilyWeb.Familymember;
import domain.FamilyWeb.Network;
import domain.FamilyWeb.Question;
import domain.FamilyWeb.Survey;
import domain.FamilyWeb.User;

/**
 * The Interface DatabaseInterface.
 */
public interface DatabaseInterface {
	
/**
 * Authentication.
 * If username and password combination in the database exists, than the authentication will pass otherwise it will fail and returns false
 *
 * @param username the username
 * @param password the password
 * @return true, if successful
 */
	public boolean authentication(String username, String password);
	
	/**
	 * Gets an user from the database
	 *
	 * @param username the username
	 * @return the user
	 */
	public User getUser(String username);
	
/**
 * Adds the user to the database
 *
 * @param user the user
 * @return true, if successful
 */
	public boolean addUser(User user);
	
	/**
	 * Update an user in the database
	 *
	 * @param user the user
	 * @return true, if successful
	 */
	public boolean updateUser(User user);
	
	/**
	 * Gets the all socialworkers existed in the database
	 *
	 * @return all socialworkers that exists
	 */
	public ArrayList<User> getAllSocialworkers();
	
	
	/**
	 * Add the client to the database.
	 *
	 * @param client the client
	 * @param userID the user id of an Socialworker
	 * @return true, if successful
	 */
	public boolean addClient(Client client, int userID);
	
	/**
	 * Update client in the database.
	 * if the userID is 0, than the user_id won't update, else the user_id will update to the given userID
	 *
	 * @param client the client
	 * @param userID the user id, 0 if the user doesn't need to change
	 * @return true, if successful
	 */
	public boolean updateClient(Client client, int userID);
	
	/**
	 * Get the client equal to the integer from the database
	 *
	 * @param client_id the client_id
	 * @return the client
	 */
	public Client getClient(int client_id);
	
	/**
	 * Get all clients of user from the database
	 *
	 * @param user the user
	 * @return the all clients of user
	 */
	public ArrayList<Client> getAllClientsOfUser(User user);
	
	/**
	 * Get all clients that existes in the database.
	 *
	 * @return the all clients
	 */
	public ArrayList<Client> getAllClients();
	
	/**
	 * Add the familymember into the database, connect the familymember to the client in the database
	 *
	 * @param famMember the familymember
	 * @param client the client to connect to
	 * @return true, if successful
	 */
	public boolean addFamilymember(Familymember famMember, Client client);
	
	/**
	 * Update familymember in the database
	 *
	 * @param famMember the fam member
	 * @return true, if successful
	 */
	public boolean updateFamilymember(Familymember famMember);
	
	/**
	 * Get all familymembers of client from the database.
	 *
	 * @param client the client
	 * @return the familymembers of client
	 */
	public ArrayList<Familymember> getFamilymembersOfClient(Client client);
	
	/**
	 * Adds the survey to the databse.
	 *
	 * @param survey the survey
	 * @return true, if successful
	 */
	public boolean addSurvey(Survey survey);
	
	/**
	 * Update survey in the database
	 *
	 * @param survey the survey
	 * @return true, if successful
	 */
	public boolean updateSurvey(Survey survey);
	
	/**
	 * Get the survey from the database equal to the unique name
	 *
	 * @param surveyName the survey name
	 * @return the survey
	 */
	public Survey getSurvey(String surveyName);
	
	/**
	 * Get all survey names from the database.
	 *
	 * @return the survey names
	 */
	public ArrayList<String> getSurveyNames();
	
	/**
	 * Add the question to the database.
	 *
	 * @param question the question
	 * @return true, if successful
	 */
	public boolean addQuestion(Question question);
	
	/**
	 * Update question the question in the database.
	 *
	 * @param question the question
	 * @return true, if successful
	 */
	public boolean updateQuestion(Question question);
	
	/**
	 * Get the question equal to the integer in the database.
	 *
	 * @param question_id the question_id
	 * @return the question
	 */
	public Question getQuestion(int question_id);
	
	/**
	 * Get all users from the database.
	 *
	 * @return the all users
	 */
	public ArrayList<User> getAllUsers();			
	
	/**
	 * Add the network to the client or familymember.
	 * when to add it to the client use 0 for familymember, if the network belongs to an familymember use 0 for client_id
	 *
	 * @param network the network
	 * @param client_id the client_id, 0 if network doesn't belongs to a client
	 * @param familymember_id the familymember_id, 0 if network doesn't belongs to a familymember
	 * @return true, if successful
	 */
	public boolean addNetwork(Network network, int client_id, int familymember_id);
	
	/**
	 * Get the networks from client or familymember.
	 * when to get it from an client use 0 for familymember, if to get from an familymember use 0 for client_id
	 * if both are not 0, then it returns null
	 *
	 * @param client_id the client_id, 0 if doesn't want to get from the client
	 * @param familymember_id the familymember_id, 0 if doesn't want to get from an family member
	 * @return the networks
	 */
	public ArrayList<Network> getNetworks(int client_id,int familymember_id);
}
