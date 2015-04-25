package quiz;

import java.io.Serializable;

public class Score implements Serializable {

	
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
}
