package quiz;

import java.io.Serializable;
import java.util.ArrayList;

public class QuizGameImpl implements QuizGame, Serializable{

	private String quizName;
	private int quizId = 0;
	private static int nextId = 0;
	private ArrayList<QuizQuestion> questionSet;
	private boolean open;

	
	public QuizGameImpl(String quizName){
		this.quizName = quizName;
		quizId = generateId();
		questionSet = new ArrayList<QuizQuestion>();
		open = true;
	}

	public void loadQuizQuestion(QuizQuestion question){
		questionSet.add(question);
	}
			
	public int getQuestionCount(){
		return questionSet.size();
	}
	
	public void removeQuizQuestion(QuizQuestion question){
		questionSet.remove(question);
	}
	
	private int generateId(){
		return nextId++;
	}
	
	public int getQuizId(){
		System.out.println(quizId);
		return quizId;
	}
	
	@Override
	public String toString(){
		String s = "QuizName: " + quizName;
		System.out.println(s);
		return s;
	}
	
	public String getQuizName(){
		return quizName;
		
	}
	
	public ArrayList<QuizQuestion> getQuestionSet(){
		return questionSet;
	}
	
	public void closeGame(){
		open = false;
	}
	
	public boolean getGameStatus(){
		return open;
	}
	
	
	/**
	 * For testing purposes only - otherwise tests fail as new objects are instantiated
	 */
	
	public static void resetId(){
		nextId = 0;
	}

}
