/**
 * 
 */
package domain.FamilyWeb;

import java.util.ArrayList;

/**
 * The Class Survey.
 *
 * @author Team HIS
 * @version 0.1
 * @since 2015-04-21
 */
public class Survey {
	
	/** The name. */
	private String name;
	
	/** The survey_id. */
	private int survey_id;
	
	/** The questions. */
	private ArrayList<Question> questions;
	
	/**
	 * Constructor with fields.
	 *
	 * @param name the name
	 * @param questions the questions
	 */
	public Survey(String name, ArrayList<Question> questions) {
		this.name = name;
		this.questions = questions;
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

	/**
	 * Gets the questions.
	 *
	 * @return the questions
	 */
	public ArrayList<Question> getQuestions() {
		return questions;
	}

	/**
	 * Sets the questions.
	 *
	 * @param questions the questions to set
	 */
	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}

	/**
	 * Gets the survey_id.
	 *
	 * @return the survey_id
	 */
	public int getSurvey_id() {
		return survey_id;
	}

	/**
	 * Sets the survey_id.
	 *
	 * @param survey_id the survey_id to set
	 */
	public void setSurvey_id(int survey_id) {
		this.survey_id = survey_id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Survey [survey_id = "+this.getSurvey_id()+", Name = " + getName() + "]";
	}	
}
