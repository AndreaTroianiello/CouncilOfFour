package it.polimi.ingsw.cg23.server.model.action;

import it.polimi.ingsw.cg23.server.controller.action.Action;
import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Player;
/**
 * The game action is used to modify the game's mode
 * @author Andrea
 *
 */
public abstract class GameAction extends Action{
	
	private static final long serialVersionUID = -2996870548641018764L;
	private boolean main;
	
	/**
	 * The constructor of the GameAction.
	 * @param main It indicates whether the action is primary or secondary.
	 */
	public GameAction(boolean main) {
		super();
		this.main=main;
	}
	
	/**
	 * It allows to run the action.
	 * @param player The current player of the turn.
	 * @param board The model of the game.
	 */
	public void runAction(Player player, Board board) {
	}

	/**
	 * Returns the indicator that shows if the action is primary or secondary.
	 * @return the main If true is a main action,else is a secondary action.
	 */
	public boolean isMain(){
		return main;
	}

}
