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
	 * Add an answer choice specifying if correct or not using a boolean variable
	 * 
	 * @param answerChoice
	 * @param correct
	 * 
	 */
	void addAnswerChoice(String answerChoice, boolean correct);
	
	/**
	 * Set the correct answer
	 * 
	 * @param choice
	 */
	void setAnswer(int choice);
	
	/**
	 * Get the correct answer number
	 * 
	 * @return 
	 */
	int getAnswer();
	
	/**
	 * display the question text and the multiple choice answers 
	 * @param questionId
	 * @return the question and the answer choices
	 */
	void displayQuestion();
	
	
}
