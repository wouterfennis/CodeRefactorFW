/**
 * 
 */
package domain.FamilyWeb;

/**
 * The Class Result.
 *
 * @author Team HIS
 * @version 0.1
 * @since 2015-04-21
 */
public class Result {
	
	/** The question. */
	private Question theQuestion;
	
	/** The my answer. */
	private Answer myAnswer;
	
	/**
	 * Constructor with fields.
	 *
	 * @param theQuestion the the question
	 * @param myAnswer the my answer
	 */
	public Result(Question theQuestion, Answer myAnswer) {
		super();
		this.theQuestion = theQuestion;
		this.myAnswer = myAnswer;
	}

	/**
	 * Gets the the question.
	 *
	 * @return the theQuestion
	 */
	public Question getTheQuestion() {
		return theQuestion;
	}

	/**
	 * Sets the the question.
	 *
	 * @param theQuestion the theQuestion to set
	 */
	public void setTheQuestion(Question theQuestion) {
		this.theQuestion = theQuestion;
	}

	/**
	 * Gets the my answer.
	 *
	 * @return the myAnswer
	 */
	public Answer getMyAnswer() {
		return myAnswer;
	}

	/**
	 * Sets the my answer.
	 *
	 * @param myAnswer the myAnswer to set
	 */
	public void setMyAnswer(Answer myAnswer) {
		this.myAnswer = myAnswer;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Result [TheQuestion = " + getTheQuestion()
				+ ", MyAnswer = " + getMyAnswer() + "]";
	}	
}
