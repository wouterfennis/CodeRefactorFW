package servletControllers.FamilyWeb;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import databaseControllers.FamilyWeb.DatabaseInterface;
import databaseControllers.FamilyWeb.MySQLDao;
import domain.FamilyWeb.Administrator;
import domain.FamilyWeb.Client;
import domain.FamilyWeb.Contact;
import domain.FamilyWeb.Familymember;
import domain.FamilyWeb.Network;
import domain.FamilyWeb.Result;
import domain.FamilyWeb.User;

/**
 * The Class OverviewController.
 */
public class OverviewController {
	
	/** The overviewController for Singleton pattern. */
	private static OverviewController oc;
	
	/** The database interface to acces the dao. */
	private DatabaseInterface db = null;

	/**
	 * Instantiates a new overview controller.
	 */
	private OverviewController() {
		this.db = new MySQLDao();
		oc = this;
	}

	/**
	 * Instantiates a new overview controller.
	 *
	 * @param db the databaseInterface to use.
	 */
	public OverviewController(DatabaseInterface db) {
		this.db = db;
		oc = this;
	}

	/**
	 * Gets the single instance of OverviewController.
	 *
	 * @return single instance of OverviewController
	 */
	public static OverviewController getInstance() {
		if (oc == null) {
			oc = new OverviewController();
		}
		return oc;
	}

	/**
	 * Gets the databaseInterface.
	 *
	 * @return the db
	 */
	public DatabaseInterface getDb() {
		return db;
	}

	/**
	 * Sets the databaseInterface.
	 *
	 * @param db            the databaseInterface to set
	 */
	public void setDb(DatabaseInterface db) {
		this.db = db;
	}

	/**
	 * Creates the json networks how the compare networks page works.
	 *
	 * @param client the client
	 * @return the JSON object[]
	 * @throws JSONException the JSON exception
	 */
	public JSONObject[] createJSONNetworks(Client client) throws JSONException {
		// intialize arrays and object
		JSONArray netwerkNodes = new JSONArray();
		JSONArray netwerkLinks = new JSONArray();
		JSONArray netwerks1 = new JSONArray();
		JSONArray netwerks2 = new JSONArray();		
		JSONObject netwerkPerson = new JSONObject();
		JSONObject netwerkLink = new JSONObject();
		JSONObject netwerkN = new JSONObject();
		JSONObject netwerkL = new JSONObject();
		ArrayList<Network> clientNetworks = db.getNetworks(
				client.getClient_id(), 0);
		// add networks from the client
		for (Network n : clientNetworks) {
			JSONObject nodesPerson = new JSONObject();
			JSONObject linksPerson = new JSONObject();
			JSONArray contacts = new JSONArray();
			JSONArray contactsLinks = new JSONArray();
			JSONObject clientNode = new JSONObject();
			clientNode.put("name", client.getForename()+" "+client.getSurname());
			clientNode.put("group", 0);
			contacts.put(clientNode);
			int i = 0;
			// for each contact create JSON object for basic info and for results
			for (Contact c : n.getContacts()) {
				i++;
				JSONObject contact = new JSONObject();
				contact.put("name", c.getFullname());
				contact.put("group", c.getCategories().get(0).getGroup_id());
				String commentary = c.getCommentary();
				if(commentary == null || commentary.trim().equals(""))
					contact.put("commentary","" );
				else
					contact.put("commentary",commentary);
				contacts.put(contact);
				JSONObject link = createLink(c.getMyResults());
				link.put("group", c.getCategories().get(0).getGroup_id());
				link.put("source", i);
				link.put("target", 0);
				contactsLinks.put(link);
			}
			if (i != 0) {
				nodesPerson.put("commentaar", n.getCommentary());
				nodesPerson.put("datum", n.getDateCreated().toString());
				nodesPerson.put("nodes", contacts);
				linksPerson.put(n.getDateCreated().toString(), contactsLinks);
				netwerkNodes.put(nodesPerson);
				netwerkLinks.put(linksPerson);	
			}
		}
		if (!clientNetworks.isEmpty()) {			
			netwerkPerson.put(client.getForename() + " " + client.getSurname(),
					netwerkNodes);
			netwerkLink.put(client.getForename() + " " + client.getSurname(),
					netwerkLinks);					
			netwerks1.put(netwerkPerson);
			netwerks2.put(netwerkLink);
		}
		// for each familymember connected to the client 
		for (Familymember fm : client.getMyFamilymembers()) {
			netwerkNodes = new JSONArray();
			netwerkLinks = new JSONArray();
			netwerkPerson = new JSONObject();
			netwerkLink = new JSONObject();
			JSONObject nodesPerson = new JSONObject();
			JSONObject linksPerson = new JSONObject();
			ArrayList<Network> familyNetworks = db.getNetworks(0,
					fm.getMember_id());
			// create JSON object for each network
			for (Network n : familyNetworks) {
				JSONArray contacts = new JSONArray();
				JSONArray contactsLinks = new JSONArray();
				JSONObject clientNode = new JSONObject();
				clientNode.put("name", client.getForename()+" "+client.getSurname());
				clientNode.put("group", 0);
				contacts.put(clientNode);
				int i = 0;
				for (Contact c : n.getContacts()) {
					i++;					
					JSONObject contact = new JSONObject();
					contact.put("name", c.getFullname());
					contact.put("group", c.getCategories().get(0).getGroup_id());
					String commentary = c.getCommentary();
					if(commentary == null)
						contact.put("commentary","" );
					else
						contact.put("commentary",commentary);
					contacts.put(contact);
					JSONObject link = createLink(c.getMyResults());
					link.put("group", c.getCategories().get(0).getGroup_id());
					link.put("source", i);
					link.put("target", 0);
					contactsLinks.put(link);
				}
				if (i != 0) {
					nodesPerson.put("commentaar", n.getCommentary());
					nodesPerson.put("datum", n.getDateCreated().toString());
					nodesPerson.put("nodes", contacts);
					linksPerson.put(n.getDateCreated().toString(),
							contactsLinks);
					netwerkNodes.put(nodesPerson);
					netwerkLinks.put(linksPerson);
				}
			}
			if (!familyNetworks.isEmpty()) {				
				netwerkPerson.put(fm.getForename() + " " + fm.getSurname(),
						netwerkNodes);
				netwerkLink.put(fm.getForename() + " " + fm.getSurname(),
						netwerkLinks);
				netwerks1.put(netwerkPerson);
				netwerks2.put(netwerkLink);
			}
		}
		netwerkN.put("allNetworks", netwerks1);
		netwerkL.put("allNetworks", netwerks2);
		JSONObject[] network = { netwerkN, netwerkL };
		return network;
	}

	/**
	 * Creates the links between the network, depending on the results of the survey.
	 *
	 * @param myResults the my results
	 * @return the JSON object
	 * @throws JSONException the JSON exception
	 */
	private JSONObject createLink(ArrayList<Result> myResults)
			throws JSONException {
		JSONObject link = new JSONObject();
		for (Result r : myResults) {
			switch (r.getMyAnswer().getAnswer_id()) {
			case 1:
				link.put("type", 1);
				break;
			case 2:
				link.put("type", 2);
				break;
			case 3:
				link.put("type", 3);
				break;
			case 4:
				link.put("type", 4);
				break;
			case 5:
				link.put("type", 5);
				break;
			case 6:
				link.put("type", 6);
				break;
			case 7:
				link.put("strength", 1);
				break;
			case 8:
				link.put("strength", 2);
				break;
			case 9:
				link.put("strength", 3);
				break;
			case 10:
				link.put("strength", 4);
				break;
			case 11:
				link.put("strength", 5);
				break;
			case 12:
				link.put("distance", 5);
				break;
			case 13:
				link.put("distance", 4);
				break;
			case 14:
				link.put("distance", 3);
				break;
			case 15:
				link.put("distance", 2);
				break;
			case 16:
				link.put("distance", 1);
				break;
			}
		}
		return link;
	}

	/**
	 * Refresh overview clients.
	 *
	 * @param currentUser the current user
	 * @return the JSON array
	 * @throws JSONException the JSON exception
	 */
	public JSONArray RefreshOverviewClients(User currentUser)
			throws JSONException {
		JSONArray returns = new JSONArray();
		ArrayList<Client> clients = new ArrayList<Client>();
		if (currentUser instanceof Administrator) {
			// for each client make an JSON Object and put it into the array
			for (Client c : db.getAllClients()) {
				clients.add(c);
				JSONObject clientJSON = new JSONObject();
				clientJSON.put("forename", c.getForename());
				clientJSON.put("surname", c.getSurname());
				clientJSON.put("dateOfBirth", c.getDateOfBirth());
				clientJSON.put("postcode", c.getPostcode());
				clientJSON.put("street", c.getStreet());
				clientJSON.put("houseNumber", c.getHouseNumber());
				clientJSON.put("city", c.getCity());
				clientJSON.put("nationality", c.getNationality());
				clientJSON.put("telephoneNumber", c.getTelephoneNumber());
				clientJSON.put("mobilePhoneNumber", c.getMobilePhoneNumber());
				clientJSON.put("email", c.getEmail());
				clientJSON.put("fileNumber", c.getFileNumber());
				clientJSON.put("client_id", c.getClient_id());				
				returns.put(clientJSON);
			}
		} else {
			// for each client make an JSON Object and put it into the array
			for (Client c : db.getAllClientsOfUser(currentUser)) {
				clients.add(c);
				JSONObject clientJSON = new JSONObject();
				clientJSON.put("forename", c.getForename());
				clientJSON.put("surname", c.getSurname());
				clientJSON.put("dateOfBirth", c.getDateOfBirth());
				clientJSON.put("postcode", c.getPostcode());
				clientJSON.put("street", c.getStreet());
				clientJSON.put("houseNumber", c.getHouseNumber());
				clientJSON.put("city", c.getCity());
				clientJSON.put("nationality", c.getNationality());
				clientJSON.put("telephoneNumber", c.getTelephoneNumber());
				clientJSON.put("mobilePhoneNumber", c.getMobilePhoneNumber());
				clientJSON.put("email", c.getEmail());
				clientJSON.put("fileNumber", c.getFileNumber());
				clientJSON.put("client_id", c.getClient_id());
				returns.put(clientJSON);
			}
		}
		// update clients for user
		currentUser.setMyClients(clients);
		return returns;
	}

	/**
	 * Refresh overview users.
	 *
	 * @param user the user
	 * @return the JSON array
	 * @throws JSONException the JSON exception
	 */
	public JSONArray RefreshOverviewUsers(User user) throws JSONException {
		JSONArray returns = new JSONArray();
		ArrayList<User> users = new ArrayList<User>();
		for (User u : db.getAllUsers()) {
			// for each user, create JSON Object and put it into the array
			JSONObject userJSON = new JSONObject();
			users.add(u);
			userJSON.put("forename", u.getForename());
			userJSON.put("surname", u.getSurname());
			userJSON.put("username", u.getUsername());
			userJSON.put("dateOfBirth", u.getDateOfBirth());
			userJSON.put("isActive", u.isActive());
			userJSON.put("postcode", u.getPostcode());
			userJSON.put("street", u.getStreet());
			userJSON.put("houseNumber", u.getHouseNumber());
			userJSON.put("city", u.getCity());
			userJSON.put("nationality", u.getNationality());
			userJSON.put("telephoneNumber", u.getTelephoneNumber());
			userJSON.put("mobilePhoneNumber", u.getMobilePhoneNumber());
			userJSON.put("email", u.getEmail());
			userJSON.put("employeeNumber", u.getEmployeeNumber());
			userJSON.put("user_id", u.getUser_id());
			returns.put(userJSON);
		}
		if (user instanceof Administrator) {
			// set users to administrator
			Administrator admin = (Administrator) user;
			admin.setUsers(users);
		}
		return returns;
	}

	/**
	 * Auto complete.
	 *
	 * @param currentUser the current user
	 * @return the JSON array
	 */
	public JSONArray autoComplete(User currentUser) {
		JSONArray usersJSON = new JSONArray();
		if (currentUser instanceof Administrator) {
			Administrator admin = (Administrator) currentUser;

			try {
				// create JSON object for each user
				for (User u : admin.getDbController().getAllUsers()) {
					JSONObject userJSON = new JSONObject();
					userJSON.put("label",
							u.getForename() + " " + u.getSurname() + " | NR: "
									+ u.getEmployeeNumber());
					userJSON.put("value", String.valueOf(u.getUser_id()));
					usersJSON.put(userJSON);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return usersJSON;
	}

	/**
	 * Refresh familymember.
	 *
	 * @param client the client
	 * @return the JSON array
	 * @throws JSONException the JSON exception
	 */
	public JSONArray refreshFamilymember(Client client) throws JSONException {
		JSONArray returns = new JSONArray();
		ArrayList<Familymember> family = new ArrayList<Familymember>();		
		for (Familymember fm : client.getMyFamilymembers()) {
			// for each familymember, create JSON Object and add it to the array
			JSONObject familyJSON = new JSONObject();
			family.add(fm);
			familyJSON = new JSONObject();
			familyJSON.put("forename", fm.getForename());
			familyJSON.put("surname", fm.getSurname());
			familyJSON.put("dateOfBirth", fm.getDateOfBirth());
			familyJSON.put("postcode", fm.getPostcode());
			familyJSON.put("street", fm.getStreet());
			familyJSON.put("houseNumber", fm.getHouseNumber());
			familyJSON.put("city", fm.getCity());
			familyJSON.put("nationality", fm.getNationality());
			familyJSON.put("telephoneNumber", fm.getTelephoneNumber());
			familyJSON.put("mobilePhoneNumber", fm.getMobilePhoneNumber());
			familyJSON.put("email", fm.getEmail());
			familyJSON.put("type", "familymember");
			familyJSON.put("member_id", fm.getMember_id());
			returns.put(familyJSON);
		}
		// update clients family
		client.setMyFamilymembers(family);		
		return returns;
	}
}