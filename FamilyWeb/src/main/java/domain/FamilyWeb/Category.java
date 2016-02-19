/**
 * 
 */
package domain.FamilyWeb;

/**
 * The Class Category.
 *
 * @author Team His
 * @version 0.1
 * @since 2015-04-21
 */
public class Category {
	
	/** The name. */
	private String name;
	
	/** The group_id. */
	private int group_id;

	/**
	 * Constructor with fields.
	 *
	 * @param name the name
	 * @param id the id
	 */
	public Category(String name, int id) {
		this.name = name;
		this.group_id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Category [Name = " + getName() + "]";
	}

	/**
	 * Gets the group_id.
	 *
	 * @return the group_id
	 */
	public int getGroup_id() {
		return group_id;
	}

	/**
	 * Sets the group_id.
	 *
	 * @param group_id the group_id to set
	 */
	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}
}
