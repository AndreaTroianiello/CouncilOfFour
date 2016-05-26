package it.polimi.ingsw.cg23.model.action;

import it.polimi.ingsw.cg23.controller.action.Action;
import it.polimi.ingsw.cg23.controller.change.Change;
import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.observer.Observable;

public abstract class GameAction extends Observable<Change> implements Action {
	private boolean main;
	
	public GameAction(boolean main) {
		this.main=main;
	}
	
	/**
	 *  it allows to run the action
	 * @param player
	 * @param board
	 */
	public void runAction(Player player, Board board){}

	/**
	 * @return the main
	 */
	public boolean isMain(){
		return main;
	}

}
