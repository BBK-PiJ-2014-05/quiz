package quiz;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class QuizGameImplTest {

QuizQuestion q = new QuizQuestionImpl("What is the capital of New York?");
QuizQuestion q1 = new QuizQuestionImpl("What is the capital of Norway?");
QuizGame game = new QuizGameImpl("Capital Cities");
QuizGame game1 = new QuizGameImpl("Roman Empire");
	
	@Before
	public void setUp(){
	q.addAnswerChoice("Manhattan",false);
	q.addAnswerChoice("New York City", false);
	q.addAnswerChoice("Albany", true);
	q.addAnswerChoice("White Plains", false);
	q.addAnswerChoice("Empire State Building", false);
	q1.addAnswerChoice("Oslo", true);
	q1.addAnswerChoice("Fredrikstad", false);
	q1.addAnswerChoice("Stavanger", false);
	q1.addAnswerChoice("Larvik", false);
	q1.addAnswerChoice("Copenhagen", false);
	QuizGameImpl.resetId();
	}
	
	@Test
	public void testgetQuizId() {
		int output = game1.getQuizId();
		int expected = 1;
		assertEquals(expected,output);
	}
	
	@Test
	public void testgetQuizName(){
		String output = game.getQuizName();
		String expected = "Capital Cities";
		assertEquals(expected,output);
	}
	
	@Test
	public void testGetQuestionCount(){
		game.loadQuizQuestion(q);
		game.loadQuizQuestion(q1);
		int output = game.getQuestionCount();
		int expected = 2;
		assertEquals(expected,output);
	}
	
	@Test
	public void testGetQuestionSet(){
		game.loadQuizQuestion(q);
		game.loadQuizQuestion(q1);
		ArrayList<QuizQuestion> output = game.getQuestionSet();
		ArrayList<QuizQuestion> expected = new ArrayList<QuizQuestion>();
		expected.add(q);
		expected.add(q1);
		assertEquals(expected,output);
	}
	
	@Test
	public void testRemoveQuizQuestion(){
		game.loadQuizQuestion(q);
		game.loadQuizQuestion(q1);
		ArrayList<QuizQuestion> output = game.getQuestionSet();
		ArrayList<QuizQuestion> expected = new ArrayList<QuizQuestion>();
		expected.add(q);
		expected.add(q1);
		game.removeQuizQuestion(q1);
		expected.remove(q1);
		assertEquals(expected,output);
	}
	
	
	@Test
	public void testgetAnswerChoiceFromQuizGame(){
		game.loadQuizQuestion(q);
		game.loadQuizQuestion(q1);
		ArrayList<QuizQuestion> questions = game.getQuestionSet();
		QuizQuestion question = questions.get(1);
		ArrayList<String> choices = question.getAnswerChoices();
		String output = choices.get(3);
		String expected = "Larvik";
		assertEquals(expected,output);
	}
	
	@Test
	public void testGetGameStatus(){
		boolean output = game.getGameStatus();
		boolean expected = true;
		assertEquals(expected,output);
	}
	
	@Test
	public void testCloseGame(){
		game.closeGame();
		boolean output = game.getGameStatus();
		boolean expected = false;
		assertEquals(expected,output);
		
	}
	

}
