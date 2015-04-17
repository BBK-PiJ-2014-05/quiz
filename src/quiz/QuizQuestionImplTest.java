package quiz;

import static org.junit.Assert.*;

import org.junit.Test;

public class QuizQuestionImplTest {

	@Test
	public void testCorrect() {
		QuizQuestion q = new QuizQuestionImpl();
		q.setQuestion("What is the capital of New York?");
		q.addAnswerChoice("Manhattan",false);
		q.addAnswerChoice("New York City", false);
		q.addAnswerChoice("Albany", true);
		q.addAnswerChoice("White Plains", false);
		q.addAnswerChoice("Empire State Building", false);
		int output = q.getAnswer();
		int expected = 3;
		assertEquals(expected,output);
	}

}
