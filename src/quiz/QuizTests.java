package quiz;

public class QuizTests {

	public static void main (String[] args){
		QuizQuestion q = new QuizQuestionImpl("2 + 2?");
		q.addAnswerChoice("3", false);
		q.addAnswerChoice("4", true);
		
	}
	
	
}
