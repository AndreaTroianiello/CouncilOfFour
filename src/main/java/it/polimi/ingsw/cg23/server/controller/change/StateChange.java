package it.polimi.ingsw.cg23.server.controller.change;

import it.polimi.ingsw.cg23.server.model.State;

/**
 * The StateChange serves to notify a new state.
 * @author Andrea
 *
 */
public class StateChange implements Change {

	private static final long serialVersionUID = 5908064198661444659L;
	private final State newState;
	
	/**
	 * The constructor of the StateChange
	 * @param newState The new state.
	 */
	public StateChange(State newState){
		this.newState=newState;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StateChange [newState=" + newState + "]";
	}

}
