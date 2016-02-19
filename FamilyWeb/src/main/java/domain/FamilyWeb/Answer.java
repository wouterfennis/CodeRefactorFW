/**
 * 
 */
package domain.FamilyWeb;

/**
 * The Class Answer.
 *
 * @author Team HIS
 * @version 0.1
 * @since 2015-04-21
 */
public class Answer {
	
	/** The answer. */
	private String answer;
	
	/** The answer_id. */
	private int answer_id;
	
	/**
	 * Constructor with fields.
	 *
	 * @param answer the answer
	 * @param answer_id the answer_id
	 */
	public Answer(String answer, int answer_id) {
		super();
		this.answer = answer;
		this.answer_id = answer_id;
	}

	/**
	 * clean constructor.
	 */
	public Answer() {
		super();
	}

	/**
	 * Gets the answer.
	 *
	 * @return the answer
	 */
	public String getAnswer() {
		return answer;
	}

	/**
	 * Sets the answer.
	 *
	 * @param answer the answer to set
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	/**
	 * Gets the answer_id.
	 *
	 * @return the answerWeight
	 */
	public int getAnswer_id() {
		return answer_id;
	}

	/**
	 * Sets the answer_id.
	 *
	 * @param answerID the new answer_id
	 */
	public void setAnswer_id(int answerID) {
		this.answer_id = answerID;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Answer [Answer_id = "
				+ getAnswer_id() + ", Answer = " + getAnswer() + "]";
	}
}
