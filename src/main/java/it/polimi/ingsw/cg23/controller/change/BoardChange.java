package it.polimi.ingsw.cg23.controller.change;

import it.polimi.ingsw.cg23.model.Board;

/**
 * The BoardChange serves to notify a new board.
 * @author Andrea
 *
 */
public class BoardChange implements Change {

	private static final long serialVersionUID = 8956768514612147676L;
	private final Board newBoard;
	
	/**
	 * The constructor of the BoardChange
	 * @param newBoard The new board.
	 */
	public BoardChange(Board newBoard){
		this.newBoard=newBoard;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StateChange [newState=" + newBoard + "]";
	}

}

