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
	
	/**
	 * synchronise the id count to avoid misplaced id's with PlayerImpl objects
	 * @return
	 */
	
	private synchronized int generateId(){
		return nextId++;
	}

	
	
	

}
