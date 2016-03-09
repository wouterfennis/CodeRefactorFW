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

public class OverviewController {
    public static final String LINK_STRENGTH = "strength";
    public static final String LINK_TYPE = "type";
    public static final String LINK_DISTANCE = "distance";
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
                contacts.put(getJsonOfClientNode(arrayOfNetworks.getClient()));
                int contactsCounter = 0;
                for (Contact contact : network.getContacts()) {
                    contactsCounter++;
                    contacts.put(createJsonOfContact(contact));
                    contactsLinks.put(createJsonOfContactLink(contactsCounter, contact));
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
                networkPerson.put(familymember.getForename() + " " + familymember.getSurname(), networkNodes);
                networkLink.put(familymember.getForename() + " " + familymember.getSurname(), networkLinks);
                arrayOfNetworks.getArrayOfNetworkNodes().put(networkPerson);
                arrayOfNetworks.getArrayOfNetworkLinks().put(networkLink);
            }
        }
    }

    private void fillClientsNetwork(Client client, JSONArray networkNodes, JSONArray networkLinks, ArrayList<Network> clientNetworks) throws JSONException {
        for (Network network : clientNetworks) {
            JSONArray arrayOfContactNodes = new JSONArray();
            JSONArray arrayOfContactLinks = new JSONArray();
            arrayOfContactNodes.put(getJsonOfClientNode(client));
            int contactsCounter = 0;
            for (Contact contact : network.getContacts()) {
                contactsCounter++;
                arrayOfContactNodes.put(createJsonOfContact(contact));
                arrayOfContactLinks.put(createJsonOfContactLink(contactsCounter, contact));
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

    private JSONObject getJsonOfClientNode(Client client) throws JSONException {
        JSONObject clientNode = new JSONObject();
        clientNode.put("name", client.getForename() + " " + client.getSurname());
        clientNode.put("group", 0); // 0, because client is always group 0 for frontend
        return clientNode;
    }

    private JSONObject createJsonOfContactLink(int contactCounter, Contact contact) throws JSONException {
        JSONObject link = createLinksOfResults(contact.getMyResults());
        link.put("group", contact.getCategories().get(0).getGroup_id());
        link.put("source", contactCounter);
        link.put("target", 0); // 0, because client is always target 0 for frontend
        return link;
    }

    private JSONObject createJsonOfContact(Contact contact) throws JSONException {
        JSONObject jsonOfContact = new JSONObject();
        jsonOfContact.put("name", contact.getFullname());
        jsonOfContact.put("group", contact.getCategories().get(0).getGroup_id());
        String commentary = contact.getCommentary();
        jsonOfContact.put("commentary", (commentary == null || "".equals(commentary.trim())) ? "" : commentary);
        return jsonOfContact;
    }

    private JSONObject createLinksOfResults(ArrayList<Result> myResults)
            throws JSONException {
        JSONObject link = new JSONObject();
        for (Result result : myResults) {
            int answerId = result.getMyAnswer().getAnswer_id();
            createLinkOfAnswerId(link, answerId);
        }
        return link;
    }

    private void createLinkOfAnswerId(JSONObject link, int answerId) throws JSONException {
        switch (answerId) {
            case 1:
                link.put(LINK_TYPE, 1);
                break;
            case 2:
                link.put(LINK_TYPE, 2);
                break;
            case 3:
                link.put(LINK_TYPE, 3);
                break;
            case 4:
                link.put(LINK_TYPE, 4);
                break;
            case 5:
                link.put(LINK_TYPE, 5);
                break;
            case 6:
                link.put(LINK_TYPE, 6);
                break;
            case 7:
                link.put(LINK_STRENGTH, 1);
                break;
            case 8:
                link.put(LINK_STRENGTH, 2);
                break;
            case 9:
                link.put(LINK_STRENGTH, 3);
                break;
            case 10:
                link.put(LINK_STRENGTH, 4);
                break;
            case 11:
                link.put(LINK_STRENGTH, 5);
                break;
            case 12:
                link.put(LINK_DISTANCE, 5);
                break;
            case 13:
                link.put(LINK_DISTANCE, 4);
                break;
            case 14:
                link.put(LINK_DISTANCE, 3);
                break;
            case 15:
                link.put(LINK_DISTANCE, 2);
                break;
            case 16:
                link.put(LINK_DISTANCE, 1);
                break;
            default :
                System.out.println("Unknown answer in creating the link");
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
            refreshedJsonArrayOfClients.put(getClientJSON(client));
        }

        currentUser.setMyClients(refreshedArrayListOfClients);
        return refreshedJsonArrayOfClients;
    }

    private JSONObject getClientJSON(Client client) throws JSONException {
        JSONObject clientJSON = new JSONObject();
        clientJSON.put("forename", client.getForename());
        clientJSON.put("surname", client.getSurname());
        clientJSON.put("dateOfBirth", client.getDateOfBirth());
        clientJSON.put("postcode", client.getPostcode());
        clientJSON.put("street", client.getStreet());
        clientJSON.put("houseNumber", client.getHouseNumber());
        clientJSON.put("city", client.getCity());
        clientJSON.put("nationality", client.getNationality());
        clientJSON.put("telephoneNumber", client.getTelephoneNumber());
        clientJSON.put("mobilePhoneNumber", client.getMobilePhoneNumber());
        clientJSON.put("email", client.getEmail());
        clientJSON.put("fileNumber", client.getFileNumber());
        clientJSON.put("client_id", client.getClient_id());
        return clientJSON;
    }

    public JSONArray refreshOverviewUsers(User currentUser) throws JSONException {
        JSONArray refreshedJsonArrayOfUsers = new JSONArray();
        ArrayList<User> refreshedArrayListOfUsers = new ArrayList<User>();
        for (User user : databaseInterface.getAllUsers()) {
            refreshedArrayListOfUsers.add(user);
            refreshedJsonArrayOfUsers.put(getUserJSON(user));
        }
        if (currentUser instanceof Administrator) {
            Administrator admin = (Administrator) currentUser;
            admin.setUsers(refreshedArrayListOfUsers);
        }
        return refreshedJsonArrayOfUsers;
    }

    private JSONObject getUserJSON(User user) throws JSONException {
        JSONObject userJSON = new JSONObject();
        userJSON.put("forename", user.getForename());
        userJSON.put("surname", user.getSurname());
        userJSON.put("username", user.getUsername());
        userJSON.put("dateOfBirth", user.getDateOfBirth());
        userJSON.put("isActive", user.isActive());
        userJSON.put("postcode", user.getPostcode());
        userJSON.put("street", user.getStreet());
        userJSON.put("houseNumber", user.getHouseNumber());
        userJSON.put("city", user.getCity());
        userJSON.put("nationality", user.getNationality());
        userJSON.put("telephoneNumber", user.getTelephoneNumber());
        userJSON.put("mobilePhoneNumber", user.getMobilePhoneNumber());
        userJSON.put("email", user.getEmail());
        userJSON.put("employeeNumber", user.getEmployeeNumber());
        userJSON.put("user_id", user.getUser_id());
        return userJSON;
    }

    public JSONArray autoComplete(User currentUser) {
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
                e.printStackTrace();
            }
        }
        return usersJSON;
    }

    public JSONArray refreshFamilymembers(Client client) throws JSONException {
        JSONArray refreshedFamily = new JSONArray();
        ArrayList<Familymember> family = new ArrayList<Familymember>();
        for (Familymember familymember : client.getMyFamilymembers()) {
            family.add(familymember);
            JSONObject familyMemberJSON = new JSONObject();
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
        client.setMyFamilymembers(family);
        return refreshedFamily;
    }
}