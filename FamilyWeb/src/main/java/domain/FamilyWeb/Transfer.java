/**
 * 
 */
package domain.FamilyWeb;

import java.sql.Date;

/**
 * The Class Transfer.
 *
 * @author Team HIS
 * @version 0.1
 * @since 2015-04-21
 */
public class Transfer extends Request {
	
	/** The client. */
	private Client theClient;
	
	/**
	 * Clean construct, with fields from Request.
	 *
	 * @param notification the notification
	 * @param createdCreated the created created
	 * @param commentary the commentary
	 * @param commentaryAdmin the commentary admin
	 * @param approved the approved
	 * @param toSocialworker the to socialworker
	 * @param fromSocialworker the from socialworker
	 */
	public Transfer(String notification, Date createdCreated,
			String commentary, String commentaryAdmin, boolean approved,
			User toSocialworker, User fromSocialworker) {
		super(notification, createdCreated, commentary, commentaryAdmin,
				approved, toSocialworker, fromSocialworker);
	}

	/**
	 * Clean constructor, with fields from Notification.
	 *
	 * @param notification the notification
	 * @param createdCreated the created created
	 */
	public Transfer(String notification, Date createdCreated) {
		super(notification, createdCreated);
	}

	/**
	 * Gets the the client.
	 *
	 * @return the theClient
	 */
	public Client getTheClient() {
		return theClient;
	}

	/**
	 * Sets the the client.
	 *
	 * @param theClient the theClient to set
	 */
	public void setTheClient(Client theClient) {
		this.theClient = theClient;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + " Transfer = Transfer [TheClient = " + getTheClient() + "]";
	}

}
