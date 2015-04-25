package quiz;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class QuizServer extends UnicastRemoteObject implements QuizService{

	private ArrayList<QuizGame> quizGames;
	private ArrayList<QuizGame> closedGames;
	private ArrayList<Player> players;
	private ArrayList<Score> scoreTable;

		public QuizServer() throws RemoteException{
			quizGames = new ArrayList<QuizGame>();
			closedGames = new ArrayList<QuizGame>();
			players = new ArrayList<Player>();
			scoreTable = new ArrayList<Score>();
		}


		public int NewQuizGame(String quizName){
			QuizGame quizGame = new QuizGameImpl(quizName);
			quizGames.add(quizGame);
			int quizGameId = quizGame.getQuizId();
			System.out.println("Received NewQuizGame Request");
			return quizGameId;
		}

		public int addQuizQuestion(String question, int quizId){
				QuizQuestion quizQuestion = new QuizQuestionImpl(question);
				int questionId = quizQuestion.getQuestionId();
				boolean found = false;
				for (int i = 0; i < quizGames.size(); i++){
					QuizGame temp = quizGames.get(i);
					if (temp.getQuizId() == quizId){
						System.out.print("found quiz!- ID is: " + temp.getQuizId());
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
										System.out.println("found the question!- question ID: " + quesId);
										ques.addAnswerChoice(choiceText, correct);
									}
							}
						}
				}
			if (!found) {
				System.out.println("Question Not found!");
			}
			}

			public void closeQuizGame(int quizId){
						for (int i = 0; i < quizGames.size(); i++){
							QuizGame game = quizGames.get(i);
							int gameId = game.getQuizId();
							if (gameId == quizId) {
								game.closeGame();
								closedGames.add(game);
								quizGames.remove(game);
							}
						}

					}



			public ArrayList<QuizGame> getQuizGamesList(){
				return quizGames;
			}
			
			public int addNewPlayer(String playerName){
				Player player = new PlayerImpl(playerName);
				players.add(player);
				int playerId = player.getPlayerId();
				return playerId;
			}
			
			public boolean checkIfPlayerExists(int playerId){
				boolean found = false;
				for (int i = 0; i < players.size(); i++){
					Player player = players.get(i);
					int id = player.getPlayerId();
					if (playerId == id){
						found = true;
					}
				} return found;
			}
			
			public int newScore(int playerId, int quizGameId, int score){
				boolean playerFound = false;
				boolean quizFound = false;
				for (int i = 0; i < players.size(); i++){
					Player plyr = players.get(i);
					int plyId = plyr.getPlayerId();
					if (plyId  == playerId){
						playerFound = true;
						for (int j = 0; j < quizGames.size(); j++){
								QuizGame game = quizGames.get(j);
								int gameId = game.getQuizId();
								if (gameId == quizGameId){
									quizFound = true;
									Score sc = new Score(plyr, game, score);
									scoreTable.add(sc);
								}
						}
					}
				}
				if (playerFound && quizFound){
					System.out.println("Found both player and quiz");
				}
				return score;
			}
			
			public ArrayList<Score> getLeagueTable(){
				return scoreTable;
			}
}
