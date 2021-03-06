package quiz;

import java.util.ArrayList;



/**
 * a quiz consists of a name and a series of (multiple choice) questions that may be attempted by registered quiz players
 * @author geoff_000
 *
 */

public interface QuizGame {

	
	/**
	 * load a question into the quiz
	 * 
	 * @param question
	 */
	void loadQuizQuestion(QuizQuestion question);
	
	/**
	 * remove the specified question from the quiz
	 * @param question
	 */
	void removeQuizQuestion(QuizQuestion question);
	

	/**
	 * get the number of questions in the quiz
	 * 
	 */
	int getQuestionCount();
	
	
	/**
	 * get the id of the quiz
	 * @return id
	 */
	int getQuizId();
	
	String getQuizName();
	
	ArrayList<QuizQuestion> getQuestionSet();
	
	void closeGame();
	
	boolean getGameStatus();
	
	
	
	
	
}
