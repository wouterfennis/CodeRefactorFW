/**
 * 
 */
package domain.FamilyWeb;

import java.sql.Date;

/**
 * The Class Notification.
 *
 * @author Team HIS
 * @version 0.1
 * @since 2015-04-21
 */
public abstract class Notification {
	
	/** The notification. */
	private String notification;
	
	/** The created created. */
	private Date createdCreated;
	
	/**
	 * Constructor with fields.
	 *
	 * @param notification the notification
	 * @param createdCreated the created created
	 */
	public Notification(String notification, Date createdCreated) {
		super();
		this.notification = notification;
		this.createdCreated = createdCreated;
	}

	/**
	 * Gets the notification.
	 *
	 * @return the notification
	 */
	public String getNotification() {
		return notification;
	}

	/**
	 * Sets the notification.
	 *
	 * @param notification the notification to set
	 */
	public void setNotification(String notification) {
		this.notification = notification;
	}

	/**
	 * Gets the created created.
	 *
	 * @return the createdCreated
	 */
	public Date getCreatedCreated() {
		return createdCreated;
	}

	/**
	 * Sets the created created.
	 *
	 * @param createdCreated the createdCreated to set
	 */
	public void setCreatedCreated(Date createdCreated) {
		this.createdCreated = createdCreated;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Notification [Notification = " + getNotification()
				+ ", CreatedCreated = " + getCreatedCreated() + "]";
	}
}
