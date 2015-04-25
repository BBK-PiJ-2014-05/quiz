package quiz;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface QuizService extends Remote{

	
	int NewQuizGame(String quizName) throws RemoteException;
	
	public int addQuizQuestion(String question, int quizId) throws RemoteException;
	
	public ArrayList<QuizGame> getQuizGamesList() throws RemoteException;
	
	public void addAnswerChoice(int quizId, int questionId, String choiceText, boolean correct) throws RemoteException;
	
	public void closeQuizGame(int quizId) throws RemoteException;
	
	public int addNewPlayer(String name) throws RemoteException;
	
	public boolean checkIfPlayerExists(int playerId) throws RemoteException;
	
	public int newScore(int playerId, int quizGameId, int score) throws RemoteException;
	
	public ArrayList<Score> getLeagueTable() throws RemoteException;
}
