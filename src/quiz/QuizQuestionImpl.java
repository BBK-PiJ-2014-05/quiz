package quiz;

import java.io.Serializable;
import java.util.ArrayList;

public class QuizQuestionImpl implements QuizQuestion, Serializable{

	private String question;
	private ArrayList<String> answerChoices;
	private int answer;
	
	
	public QuizQuestionImpl() {
		question = null;
		answerChoices = new ArrayList<String>();
		answer = 1;
	}
	
	@Override
	public void setQuestion(String question) {
		this.question = question;
		
	}


	@Override
	public void addAnswerChoice(String answerChoice, boolean correct) {
		answerChoices.add(answerChoice);
		if (correct) {
			int choice = answerChoices.size();
			setAnswer(choice);
		}
		
	}
	
	@Override
	public void setAnswer(int choice){
		answer = choice;
	}
	
	@Override
	public int getAnswer(){
		return answer;
	}

	@Override
	public void displayQuestion() {
		System.out.println(question);
		
	
	}

}
