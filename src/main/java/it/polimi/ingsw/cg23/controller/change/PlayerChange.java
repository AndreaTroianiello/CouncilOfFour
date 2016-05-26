package it.polimi.ingsw.cg23.controller.change;

import it.polimi.ingsw.cg23.model.Player;

/**
 * The PlayerChange serves to notify a new player.
 * @author Andrea
 *
 */
public class PlayerChange implements Change {

	private final Player newPlayer;
	
	/**
	 * The constructor of the BoardChange
	 * @param newBoard The new board.
	 */
	public PlayerChange(Player newPlayer){
		this.newPlayer=newPlayer;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StateChange [newState=" + newPlayer + "]";
	}

}


