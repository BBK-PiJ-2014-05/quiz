package quiz;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class QuizQuestionImplTest {
	
	@Before
	
	
	@Test
	public void testCorrect() {
		QuizQuestion q = new QuizQuestionImpl("What is the capital of New York?");
		q.addAnswerChoice("Manhattan",false);
		q.addAnswerChoice("New York City", false);
		q.addAnswerChoice("Albany", true);
		q.addAnswerChoice("White Plains", false);
		q.addAnswerChoice("Empire State Building", false);
		int output = q.getAnswer();
		int expected = 3;
		assertEquals(expected,output);
	}
	
	@Test
	public void testVerifyAnswer(){
		QuizQuestion q = new QuizQuestionImpl("What is the capital of New York?");
		q.addAnswerChoice("Manhattan",false);
		q.addAnswerChoice("New York City", false);
		q.addAnswerChoice("Albany", true);
		q.addAnswerChoice("White Plains", false);
		q.addAnswerChoice("Empire State Building", false);
		int selection = 3;
		boolean output = q.verifyAnswer(selection);
		boolean expected = true;
		assertEquals(expected,output);
	}

}
