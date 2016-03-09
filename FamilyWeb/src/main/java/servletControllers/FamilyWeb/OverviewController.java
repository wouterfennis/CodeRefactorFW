package servletControllers.FamilyWeb;

import java.util.ArrayList;
import java.util.logging.Logger;

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
import domain.FamilyWeb.User;

public class OverviewController {
    private static OverviewController instance;
    private DatabaseInterface databaseInterface = null;

    private OverviewController() {
        this.setDatabaseInterface(new MySQLDao());
    }

    public static OverviewController getInstance() {
        if (instance == null)
            instance = new OverviewController();
        return instance;
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
        JSONArrayOfNetworks arrayOfNetworks = new JSONArrayOfNetworks(client, new JSONArray(), new JSONArray());
        JSONObject networkPerson = new JSONObject();
        JSONObject networkLink = new JSONObject();
        JSONObject allNetworkNodes = new JSONObject();
        JSONObject allNetworkLinks = new JSONObject();

        ArrayList<Network> clientNetworks = databaseInterface.getNetworks(client.getClient_id(), 0); // no familymember, so has to give a 0 to the method

        fillClientsNetwork(client, networkNodes, networkLinks, clientNetworks);

        if (!clientNetworks.isEmpty()) {
            String clientsFullName = client.getForename() + " " + client.getSurname();
            networkPerson.put(clientsFullName, networkNodes);
            networkLink.put(clientsFullName, networkLinks);
            arrayOfNetworks.getArrayOfNetworkNodes().put(networkPerson);
            arrayOfNetworks.getArrayOfNetworkLinks().put(networkLink);
        }

        fillFamilyNetworks(arrayOfNetworks);

        allNetworkNodes.put("allNetworks", arrayOfNetworks.getArrayOfNetworkNodes());
        allNetworkLinks.put("allNetworks", arrayOfNetworks.getArrayOfNetworkLinks());

        return new JSONObject[]{allNetworkNodes, allNetworkLinks};
    }

    private void fillFamilyNetworks(JSONArrayOfNetworks arrayOfNetworks) throws JSONException {
        JSONArray networkNodes;
        JSONArray networkLinks;
        JSONObject networkPerson;
        JSONObject networkLink;
        for (Familymember familymember : arrayOfNetworks.getClient().getMyFamilymembers()) {
            networkNodes = new JSONArray();
            networkLinks = new JSONArray();
            ArrayList<Network> familyNetworks = databaseInterface.getNetworks(0, familymember.getMember_id());

            for (Network network : familyNetworks) {
                JSONArray contacts = new JSONArray();
                JSONArray contactsLinks = new JSONArray();
                contacts.put(arrayOfNetworks.getClient().getJsonOfClientNode());
                int contactsCounter = 0;
                for (Contact contact : network.getContacts()) {
                    contactsCounter++;
                    contacts.put(contact.createJsonOfContact());
                    contactsLinks.put(contact.createJsonOfContactLink(contactsCounter, this));
                }
                if (contactsCounter != 0) {
                    JSONObject nodesPerson = new JSONObject();
                    JSONObject linksPerson = new JSONObject();
                    nodesPerson.put("commentaar", network.getCommentary());
                    nodesPerson.put("datum", network.getDateCreated().toString());
                    nodesPerson.put("nodes", contacts);
                    linksPerson.put(network.getDateCreated().toString(), contactsLinks);
                    networkNodes.put(nodesPerson);
                    networkLinks.put(linksPerson);
                }
            }
            if (!familyNetworks.isEmpty()) {
                networkPerson = new JSONObject();
                networkLink = new JSONObject();
                String familymemberFullName = familymember.getForename() + " " + familymember.getSurname();
                networkPerson.put(familymemberFullName, networkNodes);
                networkLink.put(familymemberFullName, networkLinks);
                arrayOfNetworks.getArrayOfNetworkNodes().put(networkPerson);
                arrayOfNetworks.getArrayOfNetworkLinks().put(networkLink);
            }
        }
    }

    private void fillClientsNetwork(Client client, JSONArray networkNodes, JSONArray networkLinks, ArrayList<Network> clientNetworks) throws JSONException {
        for (Network network : clientNetworks) {
            JSONArray arrayOfContactNodes = new JSONArray();
            JSONArray arrayOfContactLinks = new JSONArray();
            arrayOfContactNodes.put(client.getJsonOfClientNode());
            int contactsCounter = 0;
            for (Contact contact : network.getContacts()) {
                contactsCounter++;
                arrayOfContactNodes.put(contact.createJsonOfContact());
                arrayOfContactLinks.put(contact.createJsonOfContactLink(contactsCounter, this));
            }
            if (contactsCounter != 0) {
                JSONObject nodesPerson = new JSONObject();
                JSONObject linksPerson = new JSONObject();
                nodesPerson.put("commentaar", network.getCommentary());
                nodesPerson.put("datum", network.getDateCreated().toString());
                nodesPerson.put("nodes", arrayOfContactNodes);
                linksPerson.put(network.getDateCreated().toString(), arrayOfContactLinks);
                networkNodes.put(nodesPerson);
                networkLinks.put(linksPerson);
            }
        }
    }

    public JSONArray refreshOverviewClients(User currentUser) throws JSONException {
        JSONArray refreshedJsonArrayOfClients = new JSONArray();
        ArrayList<Client> refreshedArrayListOfClients = new ArrayList<Client>();
        ArrayList<Client> clients;

        if (currentUser instanceof Administrator)
            clients = databaseInterface.getAllClients();
        else
            clients = databaseInterface.getAllClientsOfUser(currentUser);

        for (Client client : clients) {
            refreshedArrayListOfClients.add(client);
            refreshedJsonArrayOfClients.put(client.getClientJSON());
        }

        currentUser.setMyClients(refreshedArrayListOfClients);
        return refreshedJsonArrayOfClients;
    }

    public JSONArray refreshOverviewUsers(User currentUser) throws JSONException {
        JSONArray refreshedJsonArrayOfUsers = new JSONArray();
        ArrayList<User> refreshedArrayListOfUsers = new ArrayList<User>();
        for (User user : databaseInterface.getAllUsers()) {
            refreshedArrayListOfUsers.add(user);
            refreshedJsonArrayOfUsers.put(user.getUserJSON());
        }
        if (currentUser instanceof Administrator) {
            Administrator admin = (Administrator) currentUser;
            admin.setUsers(refreshedArrayListOfUsers);
        }
        return refreshedJsonArrayOfUsers;
    }

    public JSONArray autoComplete(User currentUser, Logger logger) {
        JSONArray usersJSON = new JSONArray();
        if (currentUser instanceof Administrator) {
            Administrator admin = (Administrator) currentUser;
            try {
                for (User user : admin.getDbController().getAllUsers()) {
                    JSONObject userJSON = new JSONObject();
                    userJSON.put("label",
                            user.getForename() + " " + user.getSurname() + " | NR: "
                                    + user.getEmployeeNumber());
                    userJSON.put("value", String.valueOf(user.getUser_id()));
                    usersJSON.put(userJSON);
                }
            } catch (JSONException e) {
                logger.warning(e.toString());
            }
        }
        return usersJSON;
    }

    public JSONArray refreshFamilymembers(Client client) throws JSONException {
        JSONArray refreshedFamily = new JSONArray();
        ArrayList<Familymember> family = new ArrayList<Familymember>();
        for (Familymember familymember : client.getMyFamilymembers()) {
            family.add(familymember);
            refreshedFamily.put(familymember.getJsonObject());
        }
        client.setMyFamilymembers(family);
        return refreshedFamily;
    }

}