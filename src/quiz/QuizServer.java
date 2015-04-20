package quiz;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class QuizServer extends UnicastRemoteObject implements QuizService{

	private ArrayList<QuizGame> quizGames;
	
	public QuizServer() throws RemoteException{
		quizGames = new ArrayList<QuizGame>();
	}
	
	
	public int NewQuizGame(String quizName){
		QuizGame quizGame = new QuizGameImpl(quizName);
		quizGames.add(quizGame);
		int quizGameId = quizGame.getQuizId();
		System.out.println("Received NewQuizGame Request.");
		return quizGameId;
	}
	
	public int addQuizQuestion(String question, int quizId){
		QuizQuestion quizQuestion = new QuizQuestionImpl(question);
		int questionId = quizQuestion.getQuestionId();
		boolean found = false;
		for (int i = 0; i < quizGames.size(); i++){
			QuizGame temp = quizGames.get(i);
			if (temp.getQuizId() == quizId){
				System.out.print("found quiz!");
				found = true;
				temp.loadQuizQuestion(quizQuestion);
			} else { 
				System.out.print("Not found!");
			}
			}
		if (!found) { System.out.print("Not sccessful!");
		
		
		}
		return questionId;
	}

	public void addAnswerChoice(int quizId, int questionId, String choiceText, boolean correct){
		boolean found = false;
		for (int i = 0; i < quizGames.size(); i++){
			QuizGame game = quizGames.get(i);
			int gameId = game.getQuizId();
			if (gameId == quizId){
				ArrayList<QuizQuestion> questionSet = game.getQuestionSet();
					for (int j = 0; j < questionSet.size(); j++){
						QuizQuestion ques = questionSet.get(j);
						int quesId = ques.getQuestionId();
							if (quesId == questionId){
								found = true;
								System.out.println("found the question!");
								ques.addAnswerChoice(choiceText, correct);
							}
					}
				}
		}
	if (!found) {
		System.out.println("Question Not found!");
	}
	}
	
	
	
	public ArrayList<QuizGame> getQuizGamesList(){
		return quizGames;
	}
	
	
	
}
