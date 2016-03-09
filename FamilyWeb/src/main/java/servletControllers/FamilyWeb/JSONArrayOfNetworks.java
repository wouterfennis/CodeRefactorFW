package servletControllers.FamilyWeb;

import domain.FamilyWeb.Client;
import org.json.JSONArray;

public class JSONArrayOfNetworks {
    private final Client client;
    private final JSONArray arrayOfNetworkNodes;
    private final JSONArray arrayOfNetworkLinks;

    public JSONArrayOfNetworks(Client client, JSONArray arrayOfNetworkNodes, JSONArray arrayOfNetworkLinks) {
        this.client = client;
        this.arrayOfNetworkNodes = arrayOfNetworkNodes;
        this.arrayOfNetworkLinks = arrayOfNetworkLinks;
    }

    public Client getClient() {
        return client;
    }

    public JSONArray getArrayOfNetworkNodes() {
        return arrayOfNetworkNodes;
    }

    public JSONArray getArrayOfNetworkLinks() {
        return arrayOfNetworkLinks;
    }
}
