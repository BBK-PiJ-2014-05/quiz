package quiz;

import java.io.Serializable;

public class PlayerImpl implements Player, Serializable {

	private String playerName;
	private int playerHighScore;
	
	
	public PlayerImpl(String name) {
		playerName = name;
		playerHighScore = 0;
	}
	
	public String getPlayerName(){
		return playerName;
	}
	

	@Override
	public int getHighScore() {
		return playerHighScore;
	}
	
	

}
