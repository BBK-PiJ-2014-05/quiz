package quiz;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class QuizQuestionImplTest {
	
	QuizQuestion q = new QuizQuestionImpl("What is the capital of New York?");
	
	@Before
	public void setUp(){
	q.addAnswerChoice("Manhattan",false);
	q.addAnswerChoice("New York City", false);
	q.addAnswerChoice("Albany", true);
	q.addAnswerChoice("White Plains", false);
	q.addAnswerChoice("Empire State Building", false);
	}
	
	@Test
	public void testNotNull() {
		assertNotNull(q);
	}
	
	@Test
	public void testVerifyAnswer(){
		int selection = 3;
		boolean output = q.verifyAnswer(selection);
		boolean expected = true;
		assertEquals(expected,output);
	}
	
	@Test
	public void testGetQuestion(){
		String output = q.getQuestion();
		String expected = "What is the capital of New York?";
		assertEquals(expected,output);
	}
	
	@Test
	public void testAnswerChoices(){
		ArrayList<String> choices = q.getAnswerChoices();
		String output = choices.get(2);
		String expected = "Albany";
		assertEquals(expected,output);
	}
	
	
	
	
	

}
