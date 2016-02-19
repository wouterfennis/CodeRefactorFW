/**
 * 
 */
package domain.FamilyWeb;

import java.sql.Date;
import java.util.ArrayList;

/**
 * The Class Share.
 *
 * @author Team HIS
 * @version 0.1
 * @since 2015-04-21
 */
public class Share extends Request {
	
	/** The shared networks. */
	private ArrayList<Network> sharedNetworks;
	
	/**
	 * Clean constructor, with fields from Notifiaction.
	 *
	 * @param notification the notification
	 * @param createdCreated the created created
	 */
	public Share(String notification, Date createdCreated) {
		super(notification, createdCreated);
		this.sharedNetworks = new ArrayList<Network>();
	}
	
	/**
	 * Clean constructor, with fields from Request.
	 *
	 * @param notification the notification
	 * @param createdCreated the created created
	 * @param commentary the commentary
	 * @param commentaryAdmin the commentary admin
	 * @param approved the approved
	 * @param toSocialworker the to socialworker
	 * @param fromSocialworker the from socialworker
	 */
	
	public Share(String notification, Date createdCreated, String commentary,
			String commentaryAdmin, boolean approved, User toSocialworker,
			User fromSocialworker) {
		super(notification, createdCreated, commentary, commentaryAdmin,
				approved, toSocialworker, fromSocialworker);
		this.sharedNetworks = new ArrayList<Network>();
	}

	
	/**
	 * Instantiates a new share.
	 *
	 * @param notification the notification
	 * @param createdCreated the created created
	 * @param commentary the commentary
	 * @param commentaryAdmin the commentary admin
	 * @param approved the approved
	 * @param toSocialworker the to socialworker
	 * @param fromSocialworker the from socialworker
	 * @param sharedNetworks the shared networks
	 */
	public Share(String notification, Date createdCreated, String commentary,
			String commentaryAdmin, boolean approved, User toSocialworker,
			User fromSocialworker, ArrayList<Network> sharedNetworks) {
		super(notification, createdCreated, commentary, commentaryAdmin,
				approved, toSocialworker, fromSocialworker);
		this.sharedNetworks = sharedNetworks;
	}

	/**
	 * Gets the shared networks.
	 *
	 * @return the sharedNetworks
	 */
	public ArrayList<Network> getSharedNetworks() {
		return sharedNetworks;
	}

	/**
	 * Sets the shared networks.
	 *
	 * @param sharedNetworks the sharedNetworks to set
	 */
	public void setSharedNetworks(ArrayList<Network> sharedNetworks) {
		this.sharedNetworks = sharedNetworks;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString()+" Share = Share [SharedNetworks = " + getSharedNetworks() + "]";
	}

	

}
