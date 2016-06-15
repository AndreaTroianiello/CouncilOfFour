package it.polimi.ingsw.cg23.server.view;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.observer.Observable;
import it.polimi.ingsw.cg23.observer.Observer;
import it.polimi.ingsw.cg23.server.controller.action.Action;
import it.polimi.ingsw.cg23.server.controller.change.Change;

/**
 * The general view of the server. It can be a RMI or a Socket view.
 * @author Andrea
 *
 */
public abstract class View extends Observable<Action> implements Observer<Change>{

	private Logger logger;
	private boolean suspended;
	
	/**
	 * The constructor of View. Initializes the view's variables.
	 */
	public View() {
		logger = Logger.getLogger(ServerSocketView.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");
		suspended=false;
	}

	/**
	 * Sets the status of the user/view.
	 * @param suspended the new status of the user/view.
	 */
	public void setSuspended(boolean suspended){
		this.suspended=suspended;
	}
	
	/**
	 * Returns the status of the user/view.
	 * @return If true the user is suspended and no longer participates in the game.
	 */
	public boolean getSuspended(){
		return suspended;
	}
	
	/**
	 * Sets the logger of the view.
	 * @param logger action's logger.
	 */
	public void setLogger(Logger logger){
		this.logger=logger;
		PropertyConfigurator.configure("src/main/resources/logger.properties");
	}
	
	/**
	 * Returns the logger of the view.
	 * @return logger
	 */
	public Logger getLogger(){
		return logger;
	}
}
