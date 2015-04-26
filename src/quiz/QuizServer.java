package quiz;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;

public class QuizServer extends UnicastRemoteObject implements QuizService, Runnable{

	private static final long serialVersionUID = 1L;
	private ArrayList<QuizGame> quizGames;
	private ArrayList<QuizGame> closedGames;
	private ArrayList<Player> players;
	private ArrayList<Score> scoreTable;
	private ArrayList<Score> quizWinners;

		public QuizServer() throws RemoteException{
			quizGames = new ArrayList<QuizGame>();
			closedGames = new ArrayList<QuizGame>();
			players = new ArrayList<Player>();
			scoreTable = new ArrayList<Score>();
			quizWinners = new ArrayList<Score>();

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
				ArrayList<Score> temp = new ArrayList<Score>();
				Collections.sort(scoreTable);
				for (int i = 0; i < scoreTable.size(); i++){
						Score score = scoreTable.get(i);
						int scoreGameId = score.getQuizGame().getQuizId();
						if( scoreGameId == quizId){
							temp.add(score);
						}
				}

				int highScore = 0;
				for (int j = 0; j < temp.size(); j++){
					Score s = temp.get(j);
					int scr = s.getScore();
					if(scr >= highScore){
						quizWinners.add(s);
						highScore = scr;
					}
				}

			for (int k = 0; k < quizGames.size(); k++){
				QuizGame game = quizGames.get(k);
				int gameId = game.getQuizId();
				if (gameId == quizId){
					game.closeGame();
				}
			}

			}


			public ArrayList<QuizGame> getQuizGamesList(){
				ArrayList<QuizGame> gamesList = new ArrayList<QuizGame>();
				for (int i = 0; i < quizGames.size(); i++){
					QuizGame game = quizGames.get(i);
					if (game.getGameStatus()){
						gamesList.add(game);
					}
				}
				return gamesList;
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

			public ArrayList<Score> getWinners(){
				return quizWinners;
			}

			public String getPlayerName(int playerId){
				String name = " ";
				for (int i = 0; i < players.size(); i++){
					Player player = players.get(i);
					int id = player.getPlayerId();
					if (id == playerId){
						name = player.getPlayerName();
						return name;
					}
				}
				return null;

			}

		public void shutdown(){
			String fileString = "./QuizData.ser";
			File file = new File(fileString);
			FileOutputStream fos = null;
			ObjectOutputStream oos = null;
			try	{
				fos = new FileOutputStream(file);
				oos = new ObjectOutputStream(fos);
				oos.writeObject(quizGames);
				oos.writeObject(closedGames);
				oos.writeObject(players);
				oos.writeObject(scoreTable);
				oos.writeObject(quizWinners);
				oos.close();
				System.out.println("Shutting down...Serialized data saved to: QuizData.ser");

			} catch (Exception ex){
				ex.printStackTrace();

			}

		  }
		
		public void startup(){
			String fileString = "./QuizData.ser";
			File file = new File(fileString);
			FileInputStream fis = null;
			ObjectInputStream ois = null;
			try{
				fis = new FileInputStream(file);
				ois = new ObjectInputStream(fis);
				ArrayList<QuizGame> quizGames = (ArrayList<QuizGame>) ois.readObject();
				ArrayList<QuizGame> closedGames = (ArrayList<QuizGame>) ois.readObject();
				ArrayList<Player> players = (ArrayList<Player>) ois.readObject();
				ArrayList<Score> scoreTable = (ArrayList<Score>) ois.readObject();
				ArrayList<Score> quizWinners = (ArrayList<Score>) ois.readObject();
				ois.close();
			} catch (Exception ex){
				ex.printStackTrace();
			}
			
		}


		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}
}











		
