package quiz;

import java.rmi.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class QuizClientSetup{

public static void main(String[] args) throws Exception {
	if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
	}

	QuizClientSetup client = new QuizClientSetup();
	client.launch();
}

	public void launch() throws Exception{
	Remote service = Naming.lookup("//127.0.0.1:1099/quiz");
	QuizService quizService = (QuizService) service;
	System.out.println("                             ");
	System.out.println("*****************************");
	System.out.println("Welcome to Quiz Client SETUP");
	System.out.println("*****************************");

	mainMenu(quizService);

		}

		public void mainMenu(QuizService quizService) throws RemoteException{
			System.out.println("                 \n"
					+ "MAIN MENU\n"
					+ "                   \n"
					+ "1- Create New Quiz\n"
					+ "2- View Quizzes\n"
					+ "3- Close a Quiz\n"
					+ "4- Exit\n");
			System.out.print("SELECT: ");
			Scanner in = new Scanner(System.in);
			int menuChoice = in.nextInt();
			switch(menuChoice){
			case 1:
			createNewQuiz(quizService);
			break;
			case 2:
			viewQuizzes(quizService);
			break;
			case 3:
			closeQuizGame(quizService);
			break;
			case 4:
			exitQuiz();
			break;
			default:
			exitQuiz();
			}

		}

		public void createNewQuiz(QuizService quizService) throws RemoteException{
				System.out.println("Create New Quiz\n"
						+ "\n"
						+ "Enter Quiz Name: ");
				Scanner in = new Scanner(System.in);
				String quizName = in.nextLine();
				int quizGameId = quizService.NewQuizGame(quizName);
				System.out.println("Enter a series of questions:");
				String userInput = "";
				int quesCount = 0;
				do {
					System.out.print("Enter Question Text: ");
					userInput = in.nextLine();
					int questionID = quizService.addQuizQuestion(userInput,quizGameId);
					for (int i = 0; i < 2; i++){
						System.out.print("Enter answer choice " + i + 1 );
						String answerChoiceText = in.nextLine();
						System.out.print("Enter true or false to indicate if choice correct:");
						String correctString = in.nextLine();
						boolean correct = false;
						if (correctString.equals("true")){
							correct = true;

						}
						quizService.addAnswerChoice(quizGameId, questionID, answerChoiceText, correct);

					} quesCount++;
				} while (quesCount < 2);

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

		public void closeQuizGame(QuizService quizService) throws RemoteException{
			System.out.println("                   ");
			System.out.println("Select QuizGame to close using ID: ");
						System.out.println("                   ");
						ArrayList<QuizGame> quizGameList = quizService.getQuizGamesList();
						for (int i = 0; i < quizGameList.size(); i++) {
							QuizGame game = quizGameList.get(i);
							System.out.println("                  ");
							System.out.print("  ID: " + game.getQuizId() + "  QuizName: " + game.getQuizName());
						}
						System.out.println("SELECT: ");
						Scanner in = new Scanner(System.in);
						int gameID = in.nextInt();
						quizService.closeQuizGame(gameID);
						ArrayList<QuizGame> revisedList = quizService.getQuizGamesList();
						for (int j = 0; j < quizGameList.size(); j++) {
							QuizGame game = quizGameList.get(j);
							System.out.println("                  ");
							System.out.println("  ID: " + game.getQuizId() + "  QuizName: " + game.getQuizName());
						}
				mainMenu(quizService);
		}

		public void exitQuiz() throws RemoteException{
		}


}