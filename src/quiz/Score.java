package quiz;

import java.io.Serializable;
import java.util.*;

public class Score implements Comparable<Object>, Serializable{

	private static final long serialVersionUID = 1L;
	private int score;
	private Player player;
	private QuizGame quizGame;


	public Score(Player player, QuizGame quizGame, int score){
		this.player = player;
		this.quizGame = quizGame;
		this.score = score;
	}


	public void incrementScore(){
		score++;
	}

	public Player getPlayer(){
		return player;
	}

	public QuizGame getQuizGame(){
		return quizGame;
	}

	public int getScore(){
		return score;
	}

public int compareTo(Object otherObject) {
		Score other = (Score) otherObject;
		if (score < other.score) { return 1;}
		if (score > other.score) { return -1;}
		return 0;
	}
}
