package it.polimi.ingsw.cg23.controller.action;

import java.io.IOException;
import java.io.Serializable;

import it.polimi.ingsw.cg23.controller.change.Change;
import it.polimi.ingsw.cg23.observer.Observable;
import it.polimi.ingsw.cg23.view.View;

/**
 * The action of the application.
 * @author Vincenzo
 */
public abstract class Action extends Observable<Change> implements Serializable{

	private static final long serialVersionUID = -818604974357806991L;
	private transient View player;
	
	/**
	 * The constructor of the Action.
	 * @throws IOException 
	 */
	public Action(){
		this.player=null;
	}

	/**
	 * Sets the player of the action.
	 * @param player the owner of the action.
	 */
	public void setPlayer(View player){
		this.player=player;
	}
	
	/**
	 * Return the player of the action.
	 * @return the owner of the action.
	 */
	public View getPlayer(){
		return player;
	}
	
}
