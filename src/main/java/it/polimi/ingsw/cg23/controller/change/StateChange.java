package it.polimi.ingsw.cg23.controller.change;

import it.polimi.ingsw.cg23.model.State;

/**
 * The StateChange serves to notify a new state.
 * @author Andrea
 *
 */
public class StateChange implements Change {

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