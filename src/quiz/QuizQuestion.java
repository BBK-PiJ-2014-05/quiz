package quiz;

import java.util.ArrayList;


/** an implementation of a multiple choice quiz question
**
*	@author geoff_000
*/

public interface QuizQuestion {

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
	* set the question text
	*
	* @param question
	*
	*/

	void setQuestion(String question);

	/**
	* get the question
	*
	* @return question
	*/
	String getQuestion();

	/**
	 * Get the correct answer number
	 *
	 * @return
	 */
	int getAnswer();

	/**
	* get the set of answer choices
	*
	* @return answerChoices
	*/
	ArrayList<String> getAnswerChoices();

	/**
	* check answer 
	*
	* @param selection
	* @return correct
	*/
	boolean verifyAnswer(int selection);
	
	int getQuestionId();
	
	int generateId();
	

}

