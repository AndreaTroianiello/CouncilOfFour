package it.polimi.ingsw.cg23.server.view;

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

	public View() {
	}

}
