package quiz;

import java.io.Serializable;
import java.util.ArrayList;


public class QuizQuestionImpl implements QuizQuestion, Serializable{

	private String question;
	private ArrayList<String> answerChoices;
	private int answer;
	private int questionId;
	private static int nextId;

	public QuizQuestionImpl(String question) {
		this.question = question;
		answerChoices = new ArrayList<String>();
		answer = 0;
		questionId = generateId();
	}

	public void addAnswerChoice(String answerChoice, boolean correct) {
		answerChoices.add(answerChoice);
		if (correct) {
			int choice = answerChoices.size();
			setAnswer(choice);
		}
	}

	public void setAnswer(int choice){
		answer = choice;
	}

	public int getAnswer(){
		return answer;
	}

	public void setQuestion(String question){
		this.question = question;
	}

	public String getQuestion(){
		return question;
	}

	public ArrayList<String> getAnswerChoices(){
		return answerChoices;
	}

	public boolean verifyAnswer(int selection){
		boolean temp = false;
		int correctChoice = getAnswer();
		if (correctChoice == selection){
			temp = true;
		}
		return temp;
	}
	
	public int getQuestionId(){
		return questionId;
	}

	public int generateId(){
		return nextId++;
	}
	
	/**
	 * for testing purposes only
	 */
	
	public static void resetId(){
		nextId = 0;
	}

}

