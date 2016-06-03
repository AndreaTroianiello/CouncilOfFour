package it.polimi.ingsw.cg23.model;

import java.io.Serializable;

/**
 * The state of the model.
 * 
 * @author Andrea
 *
 */
public class State implements Serializable{

	private static final long serialVersionUID = 5440571559577905431L;
	private String status;
	private Player finalPlayer;
	private Player currentPlayer;
	
	/**
	 * The constructor of the class. 
	 * 
	 * @param status The initial status.
	 */
	public State() {
		this.status="INITIALIZATION";
		this.currentPlayer=null;
		this.finalPlayer=null;
	}

	/**
	 * Returns the status of the game.
	 * 
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status of the game.
	 * 
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Returns the current player of the turn.
	 * 
	 * @return the currentPlayer
	 */
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * Sets the current player of the turn.
	 * 
	 * @param currentPlayer the currentPlayer to set
	 */
	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	/**
	 * Returns the player who has built all emporiums available first.
	 * 
	 * @return the player
	 */
	public Player getFinalPlayer() {
		return finalPlayer;
	}

	/**
	 * Sets the player who has built all emporiums available first.
	 * 
	 * @param player the player to set
	 */
	public void setFinalPlayer(Player player) {
		this.finalPlayer = player;
	}

	/**
	 * It generates a string formed by the most significant statistics of the State.
	 * @return string
	 */
	@Override
	public String toString() {
		String state= "State [status=" + status;
		if(currentPlayer!=null)
			state+=", current player=" + currentPlayer.getUser();
		if(finalPlayer!=null)
			state+=", final player=" + finalPlayer.getUser();
		state+="]";
		return state;
	}
	
	
}
