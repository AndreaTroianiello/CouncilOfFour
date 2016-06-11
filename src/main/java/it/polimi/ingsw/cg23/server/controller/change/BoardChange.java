package it.polimi.ingsw.cg23.server.controller.change;

import it.polimi.ingsw.cg23.server.model.Board;

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

	/**
	 * Returns the board of the game.
	 * @return newBoard.
	 */
	public Board getBoard(){
		return newBoard;
	}
	
	/**
	 * It generates a string formed by the most significant statistics of the BoardChange.
	 * @return string
	 */
	@Override
	public String toString() {
		return "BoardChange [newBoard=" + newBoard + "]";
	}

}

