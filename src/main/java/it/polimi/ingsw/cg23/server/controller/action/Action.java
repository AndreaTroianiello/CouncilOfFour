package it.polimi.ingsw.cg23.server.controller.action;

import java.io.IOException;
import java.io.Serializable;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.observer.Observable;
import it.polimi.ingsw.cg23.server.controller.change.Change;
import it.polimi.ingsw.cg23.server.view.View;

/**
 * The action of the application.
 * @author Vincenzo
 */
public abstract class Action extends Observable<Change> implements Serializable{

	private static final long serialVersionUID = -818604974357806991L;
	private transient View player;
	private transient Logger logger;
	
	/**
	 * The constructor of the Action.
	 * @throws IOException 
	 */
	public Action(){
		this.player=null;
		this.logger=Logger.getLogger(Action.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");
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
	
	/**
	 * Sets the logger of the action.
	 * @param logger action's logger.
	 */
	public void setLogger(Logger logger){
		this.logger=logger;
		PropertyConfigurator.configure("src/main/resources/logger.properties");
	}
	
	/**
	 * Returns the logger of the action.
	 * @return logger
	 */
	public Logger getLogger(){
		return logger;
	}
	
}
