package quiz;

/** an implementation of a multiple choice quiz question
** 
*	@author geoff_000
*/

public interface QuizQuestion {

	/**
	 * Set the question text
	 * 
	 * @param question
	 * 
	 */
	void setQuestion(String question);
	
	/**
	 * Get the question text for the question id
	 * 
	 * @param questionId
	 * @return question text
	 */
	String getQuestion(int questionId);
	
	/**
	 * Set a question answer indicating if correct
	 * 
	 * @param answerChoice 
	 * @param correct
	 * 
	 */
	void setAnswer(String answerChoice, boolean correct);
	
	/**
	 * display the question text and the multiple choice answers 
	 * @param questionId
	 * @return the question and the answer choices
	 */
	String displayQuestion(int questionId);
	
	
	
	
}
