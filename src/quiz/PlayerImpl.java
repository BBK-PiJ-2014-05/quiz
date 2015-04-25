package quiz;

import java.io.Serializable;

public class PlayerImpl implements Player, Serializable {

	private String playerName;
	private int playerId;
	private static int nextId;
	
	
	public PlayerImpl(String name) {
		playerName = name;
		playerId = generateId();
	}
	
	public String getPlayerName(){
		return playerName;
	}
	
	
	public int getPlayerId(){
		return playerId;
	}
	
	
	
	private int generateId(){
		return nextId++;
	}

	
	
	

}
