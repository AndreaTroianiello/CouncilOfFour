package it.polimi.ingsw.cg23.model.action;

import it.polimi.ingsw.cg23.controller.action.Action;
import it.polimi.ingsw.cg23.controller.change.Change;
import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.observer.Observable;
/**
 * The game action is used to modify the game's model.
 * @author Andrea
 *
 */
public abstract class GameAction extends Observable<Change> implements Action {
	private boolean main;
	private Player player;
	
	/**
	 * The constructor of the GameAction.
	 * @param main It indicates whether the action is primary or secondary.
	 */
	public GameAction(boolean main) {
		this.main=main;
		this.player=null;
	}
	
	/**
	 * Sets the player of the action.
	 * @param player the owner of the action.
	 */
	public void setPlayer(Player player){
		this.player=player;
	}
	
	/**
	 * Return the player of the action.
	 * @return the owner of the action.
	 */
	public Player getPlayer(){
		return player;
	}
	
	/**
	 * It allows to run the action.
	 * @param player The current player of the turn.
	 * @param board The model of the game.
	 */
	public void runAction(Player player, Board board){}

	/**
	 * Returns the indicator that shows if the action is primary or secondary.
	 * @return the main If true is a main action,else is a secondary action.
	 */
	public boolean isMain(){
		return main;
	}

}
