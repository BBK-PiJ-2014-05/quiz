package quiz;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

public class QuizClientPlayer {

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
		System.out.println("*****************************");
		System.out.println("Welcome to Quiz Client PLAYER");
		System.out.println("*****************************");

		mainMenu(quizService);

			}


		public void playQuiz(QuizService quizService) throws RemoteException{
			System.out.println("             \n"
					+ "1- Existing Player...Enter Player ID to Login\n"
					+ "2- New Player\n"
					+ "            ");
				System.out.print("SELECT: ");
				Scanner in = new Scanner(System.in);
				int menuChoice = in.nextInt();
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
				System.out.println("                 \n"
						+ "MAIN MENU\n"
						+ "                   \n"
						+ "1- View Quizzes\n"
						+ "2- Play a Quiz\n"
						+ "3- League Table\n"
						+ "4- Exit\n");
				System.out.print("SELECT: ");
				Scanner in = new Scanner(System.in);
				int menuChoice = in.nextInt();
				switch(menuChoice){
				case 1:
				viewQuizzes(quizService);
				break;
				case 2:
				playQuiz(quizService);
				break;
				case 3:
				viewLeagueTable(quizService);
				break;
				case 4:
				exitQuiz();
				break;
				}

			}

			public void logIn(QuizService quizService) throws RemoteException{
				System.out.print("Enter Player ID: ");
						//System.out.print("SELECT: ");
						Scanner in = new Scanner(System.in);
						int id = in.nextInt();
						boolean found = quizService.checkIfPlayerExists(id);
						if (found){
							System.out.println("Enter Quiz ID to play Quiz: ");
							//startQuiz

						} else {
							System.out.println("Player ID not found!");
							newPlayer(quizService);
						}
			}

			public void newPlayer(QuizService quizService) throws RemoteException{
				System.out.println("Enter your name: ");
				Scanner in = new Scanner(System.in);
				String str = in.nextLine();
				int playerId = quizService.addNewPlayer(str);
				System.out.println("Your ID is: " + playerId);
				startQuiz(quizService, playerId);
			}

			public void startQuiz(QuizService quizService,int playerId)throws RemoteException{
				int keepScore = 0;
				System.out.print("Enter the ID of the Quiz you'd like to play: ");
				Scanner in = new Scanner(System.in);
				int quizId = in.nextInt();
				ArrayList<QuizGame> quizGameList = quizService.getQuizGamesList();
				for (int i = 0; i < quizGameList.size(); i++){
					QuizGame quizGame = quizGameList.get(i);
					if (quizGame.getQuizId() == quizId){
						System.out.println(" \n"
								+ " ");
						System.out.println("Quiz: " + quizGame.getQuizName());
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
								in = new Scanner(System.in);
								int selection = in.nextInt();
								boolean correct = question.verifyAnswer(selection);
								System.out.println(" ");
								if (correct) {
									System.out.println("CORRECT!");
									keepScore++;
								} else {
									System.out.println("INCORRECT!");
								}


						}

					}

				}
				int x = quizService.newScore(playerId, quizId, keepScore);
				System.out.print("score herebb: " +x);
				mainMenu(quizService);


			}


			public void viewQuizzes(QuizService quizService) throws RemoteException{
				System.out.println("*********************");
				System.out.println("**Available Quizzes**");
				System.out.println("*********************");
				ArrayList<QuizGame> quizGameList = quizService.getQuizGamesList();
				for (int i = 0; i < quizGameList.size(); i++) {
					QuizGame game = quizGameList.get(i);
					System.out.println("  ID: " + game.getQuizId() + "  QuizName: " + game.getQuizName());
				}
				System.out.println("                      ");
				mainMenu(quizService);
			}



			public void exitQuiz()throws RemoteException{
			}

			public void viewLeagueTable(QuizService quizService) throws RemoteException{
				ArrayList<Score> leagueTable = quizService.getLeagueTable();
				System.out.println("  ");
				System.out.println("*****************************\n"
				        + "*********LEAGE TABLE*********");
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