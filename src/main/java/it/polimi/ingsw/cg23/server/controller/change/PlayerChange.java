package it.polimi.ingsw.cg23.server.controller.change;

import it.polimi.ingsw.cg23.server.model.Player;

/**
 * The PlayerChange serves to notify a new player.
 * @author Andrea
 *
 */
public class PlayerChange implements Change {

	private static final long serialVersionUID = -1986727673842582938L;
	private final Player newPlayer;
	
	/**
	 * The constructor of the BoardChange
	 * @param newBoard The new board.
	 */
	public PlayerChange(Player newPlayer){
		this.newPlayer=newPlayer;
	}

	/**
	 * It generates a string formed by the most significant statistics of the PlayerChange.
	 * @return string
	 */
	@Override
	public String toString() {
		return "PlayerChange [newPlayer=" + newPlayer + "]";
	}

}


