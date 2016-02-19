/**
 * 
 */
package domain.FamilyWeb;

import java.sql.Date;
import java.util.ArrayList;

/**
 * The Class Network.
 *
 * @author Team HIS
 * @version 0.1
 * @since 2015-04-21
 */
public class Network {
	
	/** The network_id. */
	private int network_id;
	
	/** The date created. */
	private Date dateCreated;
	
	/** The commentary. */
	private String commentary;
	
	/** The contacts. */
	private ArrayList<Contact> contacts;
	
	/** The survey. */
	private Survey theSurvey;
	
	/**
	 * Constructor with fields.
	 *
	 * @param dateCreated the date created
	 * @param commentary the commentary
	 */
	public Network(Date dateCreated, String commentary) {
		this.dateCreated = dateCreated;
		this.commentary = commentary;
		this.contacts = new ArrayList<Contact>();
	}


	/**
	 * Clean constructor.
	 */
	public Network() {
		this.contacts = new ArrayList<Contact>();
	}


	/**
	 * Gets the date created.
	 *
	 * @return the dateCreated
	 */
	public Date getDateCreated() {
		return dateCreated;
	}


	/**
	 * Sets the date created.
	 *
	 * @param dateCreated the dateCreated to set
	 */
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
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
	 * Gets the the survey.
	 *
	 * @return the theSurvey
	 */
	public Survey getTheSurvey() {
		return theSurvey;
	}


	/**
	 * Sets the the survey.
	 *
	 * @param theSurvey the theSurvey to set
	 */
	public void setTheSurvey(Survey theSurvey) {
		this.theSurvey = theSurvey;
	}


	/**
	 * Gets the contacts.
	 *
	 * @return the contacts
	 */
	public ArrayList<Contact> getContacts() {
		return contacts;
	}


	/**
	 * Sets the contacts.
	 *
	 * @param contacts the contacts to set
	 */
	public void setContacts(ArrayList<Contact> contacts) {
		this.contacts = contacts;
	}
	
	/**
	 * Gets the network_id.
	 *
	 * @return the network_id
	 */
	public int getNetwork_id() {
		return network_id;
	}
		
	/**
	 * Sets the network_id.
	 *
	 * @param network_id the network_id to set
	 */
	public void setNetwork_id(int network_id) {
		this.network_id = network_id;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Network [id = "+getNetwork_id()+", DateCreated = " + getDateCreated()
				+ ", Commentary = " + getCommentary()+", contacts = "+ getContacts().toString()+", survey = "+ getTheSurvey().toString() + "]";
	}
}
