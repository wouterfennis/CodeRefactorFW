/**
 * 
 */
package domain.FamilyWeb;

import java.sql.Date;

/**
 * The Class Request.
 *
 * @author Team HIS
 * @version 0.1
 * @since 2015-04-21
 */
public abstract class Request extends Notification{	
	
	/** The commentary. */
	private String commentary;
	
	/** The commentary admin. */
	private String commentaryAdmin;
	
	/** The approved. */
	private boolean approved;
	
	/** The to socialworker. */
	private User toSocialworker;
	
	/** The from socialworker. */
	private User fromSocialworker;
		
	
	/**
	 * Constructor with fields.
	 *
	 * @param notification the notification
	 * @param createdCreated the created created
	 * @param commentary the commentary
	 * @param commentaryAdmin the commentary admin
	 * @param approved the approved
	 * @param toSocialworker the to socialworker
	 * @param fromSocialworker the from socialworker
	 */
	public Request(String notification, Date createdCreated, String commentary,
			String commentaryAdmin, boolean approved, User toSocialworker,
			User fromSocialworker) {
		super(notification, createdCreated);
		this.commentary = commentary;
		this.commentaryAdmin = commentaryAdmin;
		this.approved = approved;
		this.toSocialworker = toSocialworker;
		this.fromSocialworker = fromSocialworker;
	}


	/**
	 * Clean constructor, fields from the Notification.
	 *
	 * @param notification the notification
	 * @param createdCreated the created created
	 */
	public Request(String notification, Date createdCreated) {
		super(notification, createdCreated);
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
	 * Gets the commentary admin.
	 *
	 * @return the commentaryAdmin
	 */
	public String getCommentaryAdmin() {
		return commentaryAdmin;
	}


	/**
	 * Sets the commentary admin.
	 *
	 * @param commentaryAdmin the commentaryAdmin to set
	 */
	public void setCommentaryAdmin(String commentaryAdmin) {
		this.commentaryAdmin = commentaryAdmin;
	}


	/**
	 * Checks if is approved.
	 *
	 * @return the approved
	 */
	public boolean isApproved() {
		return approved;
	}


	/**
	 * Sets the approved.
	 *
	 * @param approved the approved to set
	 */
	public void setApproved(boolean approved) {
		this.approved = approved;
	}


	/**
	 * Gets the to socialworker.
	 *
	 * @return the toSocialworker
	 */
	public User getToSocialworker() {
		return toSocialworker;
	}


	/**
	 * Sets the to socialworker.
	 *
	 * @param toSocialworker the toSocialworker to set
	 */
	public void setToSocialworker(User toSocialworker) {
		this.toSocialworker = toSocialworker;
	}


	/**
	 * Gets the from socialworker.
	 *
	 * @return the fromSocialworker
	 */
	public User getFromSocialworker() {
		return fromSocialworker;
	}


	/**
	 * Sets the from socialworker.
	 *
	 * @param fromSocialworker the fromSocialworker to set
	 */
	public void setFromSocialworker(User fromSocialworker) {
		this.fromSocialworker = fromSocialworker;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + " Request = Request [Commentary = " + getCommentary()
				+ ", CommentaryAdmin = " + getCommentaryAdmin()
				+ ", isApproved = " + isApproved() + ", ToSocialworker = "
				+ getToSocialworker() + ", FromSocialworker = "
				+ getFromSocialworker() + "]";
	}
	
	
}
