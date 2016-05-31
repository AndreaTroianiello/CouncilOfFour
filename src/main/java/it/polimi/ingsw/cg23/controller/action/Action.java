package it.polimi.ingsw.cg23.controller.action;

import it.polimi.ingsw.cg23.controller.change.Change;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.observer.Observable;

/**
 * The action of the application.
 * @author Vincenzo
 */
public abstract class Action extends Observable<Change>{


	private Player player;
	
	/**
	 * The constructor of the Action.
	 */
	public Action() {
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
	
}
