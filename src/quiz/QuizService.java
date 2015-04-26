package quiz;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface QuizService extends Remote{

	/**
	 * Create a new QuizGame
	 * 
	 * @param quizName
	 * @return
	 * @throws RemoteException
	 */
	int NewQuizGame(String quizName) throws RemoteException;
	
	/**
	 * Used to add questions to the quiz
	 * 
	 * @param question
	 * @param quizId
	 * @return the id of the question
	 * @throws RemoteException
	 */
	public int addQuizQuestion(String question, int quizId) throws RemoteException;
	
	/**
	 * used to view a list of active quizzes
	 * 
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<QuizGame> getQuizGamesList() throws RemoteException;
	
	/**
	 * Add answer choices to the quiz
	 * 
	 * @param quizId
	 * @param questionId
	 * @param choiceText
	 * @param correct
	 * @throws RemoteException
	 */
	public void addAnswerChoice(int quizId, int questionId, String choiceText, boolean correct) throws RemoteException;
	
	/**
	 * Close a quiz making it unavailable to players
	 * 
	 * @param quizId
	 * @throws RemoteException
	 */
	public void closeQuizGame(int quizId) throws RemoteException;
	
	/**
	 * Take the users name to generate a user id
	 * 
	 * @param name
	 * @return the users id
	 * @throws RemoteException
	 */
	public int addNewPlayer(String name) throws RemoteException;
	
	/**
	 * verify a player is is recognised in the database
	 * 
	 * @param playerId
	 * @return 
	 * @throws RemoteException
	 */
	public boolean checkIfPlayerExists(int playerId) throws RemoteException;
	
	/**
	 * A Score class keeps track of players' results
	 * 
	 * @param playerId
	 * @param quizGameId
	 * @param score
	 * @return score
	 * @throws RemoteException
	 */
	
	public int newScore(int playerId, int quizGameId, int score) throws RemoteException;
	
	/**
	 * A list of winners for each closed quiz, sorted by score
	 * 
	 * @return a list of winners
	 * @throws RemoteException
	 */
	public ArrayList<Score> getWinners() throws RemoteException;
	
	/**
	 * get the player name that matches a given player id
	 * 
	 * @param playerId
	 * @return player name
	 * @throws RemoteException
	 */
	public String getPlayerName(int playerId) throws RemoteException;
	
	/**
	 * Exit the quiz, save data to disk
	 * 
	 * @throws RemoteException
	 */
	public void shutdown() throws RemoteException;
}
