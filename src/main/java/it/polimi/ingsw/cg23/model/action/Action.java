package it.polimi.ingsw.cg23.model.action;

import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.Player;

/**
 * the action's interface
 * @author Vincenzo
 */
public interface Action {
	
	/**
	 *  it allows to run the action
	 * @param player
	 * @param board
	 */
	public void runAction(Player player, Board board);
	
	/**
	 * @return the main
	 */
	public boolean isMain();
}
