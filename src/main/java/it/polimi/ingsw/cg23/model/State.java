package it.polimi.ingsw.cg23.model;

/**
 * The state of the model.
 * 
 * @author Andrea
 *
 */
public class State {

	private String status;
	private Player player;
	
	/**
	 * The constructor of the class. 
	 * 
	 * @param status The initial status.
	 */
	public State(String status,Player player) {
		this.status=status;
		this.player=player;
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
	 * Returns the player who has built all emporiums available first.
	 * 
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Sets the player who has built all emporiums available first.
	 * 
	 * @param player the player to set
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}
}
