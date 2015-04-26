package quiz;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * An implementation of the player client. This class is used to play quizzes and to view high scores. Scores are only visible
 * for quizzes that have been 'closed' by a user in the client setup program. Players are assigned an ID which is used to keep
 * track of the players' scores for each quiz. 
 * Initially the client establishes a security manager and then tries to connect to the server. All of the program's class objects are stored
 * on the server and most of the computation work is done on the server. However some of the computation work is done in this class.  
 * @author geoff_000
 *
 */

public class QuizClientPlayer implements Serializable {
	
	private static final long serialVersionUID = 1L;
	Scanner in = new Scanner(System.in);

	public static void main(String[] args) throws Exception {
			if (System.getSecurityManager() == null) {
					System.setSecurityManager(new RMISecurityManager());
			}

			QuizClientPlayer clientPlayer = new QuizClientPlayer();
			clientPlayer.launch();
		}

			public void launch() throws Exception{
			Remote service = Naming.lookup("//127.0.0.1:1099/quiz");
			QuizService quizService = (QuizService) service;
			System.out.println("                             ");
			System.out.println("******************************");
			System.out.println("Welcome to Quiz Client PLAYER!");
			System.out.println("******************************");

			mainMenu(quizService);

				}


			public void playQuiz(QuizService quizService) throws RemoteException{
				System.out.println("             \n"
						+ "1- Existing Player...Enter Player ID to Login\n"
						+ "2- New Player\n"
						+ "            ");
					System.out.print("SELECT: ");
					int menuChoice = 0;
					do{
					menuChoice = in.nextInt();
					} while (menuChoice < 0 || menuChoice > 2);
					switch(menuChoice){
					case 1:
					logIn(quizService);
					break;
					case 2:
					newPlayer(quizService);
					break;
					}


			}

				public void mainMenu(QuizService quizService) throws RemoteException{
					System.out.println(" \n"
							+ "MAIN MENU\n"
							+ "  \n"
							+ "1- View Quizzes\n"
							+ "2- Play a Quiz\n"
							+ "3- Quiz Winners\n"
							+ "4- Exit\n");
					System.out.print("SELECT: ");
					int menuChoice = 0;
					do{
					menuChoice = in.nextInt();
					} while (menuChoice < 0 && menuChoice > 4);
					switch(menuChoice){
					case 1:
					viewQuizzes(quizService);
					break;
					case 2:
					playQuiz(quizService);
					break;
					case 3:
					viewQuizWinners(quizService);
					break;
					case 4:
					exitQuiz(quizService);
					break;
					default:
					exitQuiz(quizService);
					}

				}


/**
 * Existing users attempt login simply by entering their user ID. If the integer entered is not recognised then the user is directed to
 * create a new ID
 * @param quizService
 * @throws RemoteException
 */
				public void logIn(QuizService quizService) throws RemoteException{
					String name = " ";
					System.out.println("  ");
					System.out.print("Enter Player ID: ");
							int id = 0;
							do {
							id = in.nextInt();
							} while (id <0);
							boolean found = quizService.checkIfPlayerExists(id);
							if (found){
								name = quizService.getPlayerName(id);
								System.out.println("  ");
								System.out.println("Welcome back " + name + "...");
								startQuiz(quizService,id);
							} else {
								System.out.println("  ");
								System.out.println("Player ID not found!");
								System.out.println("Set up new login...");
								newPlayer(quizService);
							}

				}
/**
 * New players add their name to generate an ID
 * @param quizService
 * @throws RemoteException
 */
				public void newPlayer(QuizService quizService) throws RemoteException{
					System.out.println("Enter your name: ");
					Scanner strIn = new Scanner(System.in);
					String str = strIn.nextLine();
					int playerId = quizService.addNewPlayer(str);
					System.out.println("Your ID is: " + playerId);
					startQuiz(quizService, playerId);
				}

				public void startQuiz(QuizService quizService,int playerId)throws RemoteException{
					int keepScore = 0;
					System.out.println("  ");
					System.out.print("Enter the ID of the Quiz you'd like to play: ");
					int quizId = 0;
					do{
					quizId = in.nextInt();
					} while (quizId < 0);
					ArrayList<QuizGame> quizGameList = quizService.getQuizGamesList();
					for (int i = 0; i < quizGameList.size(); i++){
						QuizGame quizGame = quizGameList.get(i);
						if (quizGame.getQuizId() == quizId){
							System.out.println(" \n"
									+ " ");
							System.out.println("QUIZ: " + quizGame.getQuizName());
							System.out.println(" ");
							ArrayList<QuizQuestion> quizQuestions = quizGame.getQuestionSet();

							for (int j = 0; j < quizQuestions.size(); j++){
								QuizQuestion question = quizQuestions.get(j);
								int quesNo = j + 1;
								System.out.println(" ");
								System.out.println("Question " + quesNo + "-  " + question.getQuestion() );
								System.out.println("                  ");
								System.out.println("SELECT>");
								ArrayList<String> answerChoices = question.getAnswerChoices();
								for (int k = 0; k < answerChoices.size(); k++){
									System.out.println(" ");
									String answerChoice = answerChoices.get(k);
									int choiceNo = k+1;
									System.out.println(" " + choiceNo + "- " + answerChoice);
								}
									System.out.println(" ");
									System.out.print("> ");
									int selection = in.nextInt();
									boolean correct = question.verifyAnswer(selection);
									System.out.println(" ");
									if (correct) {
										System.out.println("CORRECT!");
										keepScore++;
									} else {
										System.out.println("WRONG!");
									}


							}

						}

					}
					int x = quizService.newScore(playerId, quizId, keepScore);
					mainMenu(quizService);
				}


				public void viewQuizzes(QuizService quizService) throws RemoteException{
					System.out.println(" ");
					System.out.println("*****************************");
					System.out.println("******Available Quizzes******");
					System.out.println("*****************************");
					ArrayList<QuizGame> quizGameList = quizService.getQuizGamesList();
					for (int i = 0; i < quizGameList.size(); i++) {
						QuizGame game = quizGameList.get(i);
						System.out.println("ID: " +game.getQuizId() + "\t" + " QuizName: " + game.getQuizName());
					}
					System.out.println(" ");
					mainMenu(quizService);
				}



				public void exitQuiz(QuizService quizService)throws RemoteException{
					in.close();
					quizService.shutdown();

				}

				public void viewQuizWinners(QuizService quizService) throws RemoteException{
					ArrayList<Score> leagueTable = quizService.getWinners();
									if(leagueTable.isEmpty()){
										System.out.println("All quizzes are still active. Close a Quiz in client setup to get High Scores");
									} else {
									Collections.sort(leagueTable);
									System.out.println("  ");
									System.out.println("*****************************\n"
									        + "*********QUIZ WINNERS*********");
									System.out.println(" ");

									for (int i = 0; i < leagueTable.size(); i++){
										Score score = leagueTable.get(i);
										String name = score.getPlayer().getPlayerName();
										String quiz = score.getQuizGame().getQuizName();
										int highScore = score.getScore();
										System.out.println(name + "\t" + quiz + "\t" + highScore);
									}
									System.out.println(" ");
									System.out.println("*****************************");
									mainMenu(quizService);


									}
				}


	}