package quiz;

import java.rmi.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;



public class QuizClientSetup {

	
	public static void main(String[] args) throws Exception {
		if (System.getSecurityManager() == null) {
				System.setSecurityManager(new RMISecurityManager());
		}
		Remote service = Naming.lookup("//127.0.0.1:1099/quiz");
		QuizService quizService = (QuizService) service;
		
		
		System.out.println("Welcome to Quiz Client SETUP");
		
		QuizClientSetup client = new QuizClientSetup();
		int menuChoice = client.mainMenu();
		switch(menuChoice){
		case 1:
		client.createNewQuiz(quizService);
		break;
		case 2:
		break;
		case 3:
		break;
		case 4:
		break;
		}

	}

	public int mainMenu(){
		System.out.println("MAIN MENU\n"
				+ "1- Create New Quiz\n"
				+ "2- View Quiz's\n"
				+ "3- xx\n"
				+ "4- Exit\n");
		System.out.print("SELECT: ");
		Scanner in = new Scanner(System.in);
		int x = in.nextInt();
		//in.close();
		return x;
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
		do {
			System.out.print("Enter Question Text(Finish with 'Exit': ");
			userInput = in.nextLine();
			int questionID = quizService.addQuizQuestion(userInput,quizGameId);
			for (int i = 0; i < 5; i++){
				System.out.print("Enter answer choice " + i + 1 );
				String answerChoiceText = in.nextLine();
				System.out.print("Enter true or false to indicate if choice correct:");
				String correctString = in.nextLine();
				boolean correct = false;
				if (correctString.equals("true")){
					correct = true;
					
				}
				quizService.addAnswerChoice(quizGameId, questionID, answerChoiceText, correct);
			}
		} while (!userInput.equals("Exit"));	
			
	
	}
		
		
		



}