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
    private static OverviewController înstance;
    private DatabaseInterface databaseInterface = null;

    private OverviewController() {
        this.setDatabaseInterface(new MySQLDao());
    }

    public static OverviewController getInstance() {
        if (înstance == null) {
            înstance = new OverviewController();
        }
        return înstance;
    }

    public DatabaseInterface getDatabaseInterface() {
        return databaseInterface;
    }

    public void setDatabaseInterface(DatabaseInterface databaseInterface) {
        this.databaseInterface = databaseInterface;
    }

    public JSONObject[] createJSONNetworkOfClient(Client client) throws JSONException {
        JSONArray networkNodes = new JSONArray();
        JSONArray networkLinks = new JSONArray();
        JSONArray arrayOfNetworkNodes = new JSONArray();
        JSONArray arrayOfNetworkLinks = new JSONArray();
        JSONObject networkPerson = new JSONObject();
        JSONObject networkLink = new JSONObject();
        JSONObject allNetworkNodes = new JSONObject();
        JSONObject allNetworkLinks = new JSONObject();

        ArrayList<Network> clientNetworks = databaseInterface.getNetworks(client.getClient_id(), 0);

        fillClientsNetwork(client, networkNodes, networkLinks, clientNetworks);

        if (!clientNetworks.isEmpty()) {
            networkPerson.put(client.getForename() + " " + client.getSurname(), networkNodes);
            networkLink.put(client.getForename() + " " + client.getSurname(), networkLinks);
            arrayOfNetworkNodes.put(networkPerson);
            arrayOfNetworkLinks.put(networkLink);
        }

        fillFamilyNetworks(client, arrayOfNetworkNodes, arrayOfNetworkLinks);

        allNetworkNodes.put("allNetworks", arrayOfNetworkNodes);
        allNetworkLinks.put("allNetworks", arrayOfNetworkLinks);

        return new JSONObject[]{allNetworkNodes, allNetworkLinks};
    }

    private void fillFamilyNetworks(Client client, JSONArray arrayOfNetworkNodes, JSONArray arrayOfNetworkLinks) throws JSONException {
        JSONArray networkNodes;
        JSONArray networkLinks;
        JSONObject networkPerson;
        JSONObject networkLink;
        for (Familymember fm : client.getMyFamilymembers()) {
            networkNodes = new JSONArray();
            networkLinks = new JSONArray();
            networkPerson = new JSONObject();
            networkLink = new JSONObject();
            JSONObject nodesPerson = new JSONObject();
            JSONObject linksPerson = new JSONObject();
            ArrayList<Network> familyNetworks = databaseInterface.getNetworks(0, fm.getMember_id());

            for (Network n : familyNetworks) {
                JSONArray contacts = new JSONArray();
                JSONArray contactsLinks = new JSONArray();
                contacts.put(getJsonOfClientNode(client));
                int i = 0;
                for (Contact c : n.getContacts()) {
                    i++;
                    contacts.put(createJsonOfContact(c));
                    contactsLinks.put(createJsonOfContactLink(i, c));
                }
                if (i != 0) {
                    nodesPerson.put("commentaar", n.getCommentary());
                    nodesPerson.put("datum", n.getDateCreated().toString());
                    nodesPerson.put("nodes", contacts);
                    linksPerson.put(n.getDateCreated().toString(), contactsLinks);
                    networkNodes.put(nodesPerson);
                    networkLinks.put(linksPerson);
                }
            }
            if (!familyNetworks.isEmpty()) {
                networkPerson.put(fm.getForename() + " " + fm.getSurname(), networkNodes);
                networkLink.put(fm.getForename() + " " + fm.getSurname(), networkLinks);
                arrayOfNetworkNodes.put(networkPerson);
                arrayOfNetworkLinks.put(networkLink);
            }
        }
    }

    private void fillClientsNetwork(Client client, JSONArray networkNodes, JSONArray networkLinks, ArrayList<Network> clientNetworks) throws JSONException {
        for (Network n : clientNetworks) {
            JSONObject nodesPerson = new JSONObject();
            JSONObject linksPerson = new JSONObject();
            JSONArray arrayOfContactNodes = new JSONArray();
            JSONArray arrayOfContactLinks = new JSONArray();
            arrayOfContactNodes.put(getJsonOfClientNode(client));
            int i = 0;
            for (Contact c : n.getContacts()) {
                i++;
                arrayOfContactNodes.put(createJsonOfContact(c));
                arrayOfContactLinks.put(createJsonOfContactLink(i, c));
            }
            if (i != 0) {
                nodesPerson.put("commentaar", n.getCommentary());
                nodesPerson.put("datum", n.getDateCreated().toString());
                nodesPerson.put("nodes", arrayOfContactNodes);
                linksPerson.put(n.getDateCreated().toString(), arrayOfContactLinks);
                networkNodes.put(nodesPerson);
                networkLinks.put(linksPerson);
            }
        }
    }

    private JSONObject getJsonOfClientNode(Client client) throws JSONException {
        JSONObject clientNode = new JSONObject();
        clientNode.put("name", client.getForename() + " " + client.getSurname());
        clientNode.put("group", 0);
        return clientNode;
    }

    private JSONObject createJsonOfContactLink(int i, Contact c) throws JSONException {
        JSONObject link = createLink(c.getMyResults());
        link.put("group", c.getCategories().get(0).getGroup_id());
        link.put("source", i);
        link.put("target", 0);
        return link;
    }

    private JSONObject createJsonOfContact(Contact c) throws JSONException {
        JSONObject contact = new JSONObject();
        contact.put("name", c.getFullname());
        contact.put("group", c.getCategories().get(0).getGroup_id());
        String commentary = c.getCommentary();
        if (commentary == null || commentary.trim().equals(""))
            contact.put("commentary", "");
        else
            contact.put("commentary", commentary);
        return contact;
    }

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

    public JSONArray RefreshOverviewClients(User currentUser)
            throws JSONException {
        JSONArray refreshedClients = new JSONArray();
        ArrayList<Client> clients = new ArrayList<Client>();
        if (currentUser instanceof Administrator) {
            for (Client client : databaseInterface.getAllClients()) {
                clients.add(client);
                refreshedClients.put(getClientJSON(client));
            }
        } else {
            for (Client client : databaseInterface.getAllClientsOfUser(currentUser)) {
                clients.add(client);
                refreshedClients.put(getClientJSON(client));
            }
        }
        currentUser.setMyClients(clients);
        return refreshedClients;
    }

    private JSONObject getClientJSON(Client c) throws JSONException {
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
        return clientJSON;
    }

    /**
     * Refresh overview users.
     *
     * @param user the user
     * @return the JSON array
     * @throws JSONException the JSON exception
     */
    public JSONArray refreshOverviewUsers(User user) throws JSONException {
        JSONArray returns = new JSONArray();
        ArrayList<User> users = new ArrayList<User>();
        for (User u : databaseInterface.getAllUsers()) {
            users.add(u);
            returns.put(getUserJSON(u));
        }
        if (user instanceof Administrator) {
            // set users to administrator
            Administrator admin = (Administrator) user;
            admin.setUsers(users);
        }
        return returns;
    }

    private JSONObject getUserJSON(User u) throws JSONException {
        JSONObject userJSON = new JSONObject();
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
        return userJSON;
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
                for (User user : admin.getDbController().getAllUsers()) {
                    JSONObject userJSON = new JSONObject();
                    userJSON.put("label",
                            user.getForename() + " " + user.getSurname() + " | NR: "
                                    + user.getEmployeeNumber());
                    userJSON.put("value", String.valueOf(user.getUser_id()));
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
    public JSONArray refreshFamilymembers(Client client) throws JSONException {
        JSONArray refreshedFamily = new JSONArray();
        ArrayList<Familymember> family = new ArrayList<Familymember>();
        for (Familymember familymember : client.getMyFamilymembers()) {
            // for each familymember, create JSON Object and add it to the array
            JSONObject familyMemberJSON = new JSONObject();
            family.add(familymember);
            familyMemberJSON = new JSONObject();
            familyMemberJSON.put("forename", familymember.getForename());
            familyMemberJSON.put("surname", familymember.getSurname());
            familyMemberJSON.put("dateOfBirth", familymember.getDateOfBirth());
            familyMemberJSON.put("postcode", familymember.getPostcode());
            familyMemberJSON.put("street", familymember.getStreet());
            familyMemberJSON.put("houseNumber", familymember.getHouseNumber());
            familyMemberJSON.put("city", familymember.getCity());
            familyMemberJSON.put("nationality", familymember.getNationality());
            familyMemberJSON.put("telephoneNumber", familymember.getTelephoneNumber());
            familyMemberJSON.put("mobilePhoneNumber", familymember.getMobilePhoneNumber());
            familyMemberJSON.put("email", familymember.getEmail());
            familyMemberJSON.put("type", "familymember");
            familyMemberJSON.put("member_id", familymember.getMember_id());
            refreshedFamily.put(familyMemberJSON);
        }
        // update clients family
        client.setMyFamilymembers(family);
        return refreshedFamily;
    }
}