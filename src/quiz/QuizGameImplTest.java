package quiz;

import static org.junit.Assert.*;

import org.junit.Test;

public class QuizGameImplTest {

	@Test
	public void test() {
		QuizQuestion question = new QuizQuestionImpl("a test question");
		question.addAnswerChoice("firstchoice", true);
		question.addAnswerChoice("secondchoice", false);
		QuizGame quiz = new QuizGameImpl("testGame");
		quiz.loadQuizQuestion(question);
		int output = quiz.getQuestionCount();
		int expected = 1;
		assertEquals(expected,output);
		quiz.removeQuizQuestion(question);
		output = quiz.getQuestionCount();
		expected = 0;
		assertEquals(expected,output);
		output = quiz.getQuizId();
		expected = 0;
		assertEquals(expected,output);
		QuizGame quiz2 = new QuizGameImpl("another test game");
		output = quiz2.getQuizId();
		expected = 1;
		assertEquals(expected,output);
		
		
	}

}
