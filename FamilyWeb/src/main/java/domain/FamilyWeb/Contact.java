/**
 * 
 */
package domain.FamilyWeb;

import org.json.JSONException;
import org.json.JSONObject;
import servletControllers.FamilyWeb.OverviewController;

import java.util.ArrayList;

/**
 * The Class Contact.
 *
 * @author Team His
 * @version 0.1
 * @since 2015-04-21
 */
public class Contact {
	public static final String LINK_STRENGTH = "strength";
	public static final String LINK_TYPE = "type";
	public static final String LINK_DISTANCE = "distance";
	
	/** The fullname. */
	private String fullname;
	
	/** The commentary. */
	private String commentary;	
	
	/** The role. */
	private String role;
	
	/** The age. */
	private int age;
	
	/** The categories. */
	private ArrayList<Category> categories;
	
	/** The my results. */
	private ArrayList<Result> myResults;
	
	/** The contact_id. */
	private int contact_id;
	
	/**
	 * Constructor with fields.
	 *
	 * @param fullname the fullname
	 * @param commentary the commentary
	 * @param role the role
	 * @param age the age
	 * @param group the group
	 * @param group_id the group_id
	 */
	public Contact(String fullname, String commentary, String role, int age, String group,int group_id) {			
		this.fullname = fullname;
		this.commentary = commentary;
		this.role = role;
		this.age = age;
		this.categories = new ArrayList<Category>();
		this.getCategories().add(new Category(group,group_id));
		this.myResults = new ArrayList<Result>();
	}

	/**
	 * clean constructor.
	 */
	public Contact() {
		this.categories = new ArrayList<Category>();
		this.myResults = new ArrayList<Result>();
	}

	/**
	 * Gets the fullname.
	 *
	 * @return the fullname
	 */
	public String getFullname() {
		return fullname;
	}

	/**
	 * Sets the fullname.
	 *
	 * @param fullname the fullname to set
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	/**
	 * Gets the commentary.
	 *
	 * @return the commentary
	 */
	public String getCommentary() {
		return commentary;
	}

	/**
	 * Sets the commentary.
	 *
	 * @param commentary the commentary to set
	 */
	public void setCommentary(String commentary) {
		this.commentary = commentary;
	}

	/**
	 * Gets the categories.
	 *
	 * @return the categories
	 */
	public ArrayList<Category> getCategories() {
		return categories;
	}

	/**
	 * Sets the categories.
	 *
	 * @param categories the categories to set
	 */
	public void setCategories(ArrayList<Category> categories) {
		this.categories = categories;
	}

	/**
	 * Gets the my results.
	 *
	 * @return the myResults
	 */
	public ArrayList<Result> getMyResults() {
		return myResults;
	}

	/**
	 * Sets the my results.
	 *
	 * @param myResults the myResults to set
	 */
	public void setMyResults(ArrayList<Result> myResults) {
		this.myResults = myResults;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Contact [Fullname = " + getFullname() + ", Commentary = "
				+ getCommentary() +", category ="+getCategories() + ", results = "+getMyResults()+"]";
	}
	
	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Sets the role.
	 *
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * Gets the age.
	 *
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Sets the age.
	 *
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	
	/**
	 * Gets the contact_id.
	 *
	 * @return the contact_id
	 */
	public int getContact_id() {
		return contact_id;
	}
	
	/**
	 * Sets the contact_id.
	 *
	 * @param contact_id the contact_id to set
	 */
	public void setContact_id(int contact_id) {
		this.contact_id = contact_id;
	}

	public JSONObject createJsonOfContact() throws JSONException {
        JSONObject jsonOfContact = new JSONObject();
        jsonOfContact.put("name", getFullname());
        jsonOfContact.put("group", getCategories().get(0).getGroup_id());
        String commentary = getCommentary();
        jsonOfContact.put("commentary", (commentary == null || "".equals(commentary.trim())) ? "" : commentary);
        return jsonOfContact;
    }

	public JSONObject createJsonOfContactLink(int contactCounter, OverviewController overviewController) throws JSONException {
        JSONObject link = this.createLinksOfResults(getMyResults());
        link.put("group", getCategories().get(0).getGroup_id());
        link.put("source", contactCounter);
        link.put("target", 0); // 0, because client is always target 0 for frontend
        return link;
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
}
