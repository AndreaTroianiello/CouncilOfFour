package it.polimi.ingsw.cg23.server.controller.action;

import it.polimi.ingsw.cg23.server.controller.Controller;
import it.polimi.ingsw.cg23.server.controller.Turn;
import it.polimi.ingsw.cg23.server.controller.change.BoardChange;
import it.polimi.ingsw.cg23.server.controller.change.RankChange;
import it.polimi.ingsw.cg23.server.controller.change.StateChange;
import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Rank;
import it.polimi.ingsw.cg23.server.model.State;
import it.polimi.ingsw.cg23.server.model.exception.NegativeNumberException;

/**
 * The action EndTurn is used to notify the change of the current player.
 * @author Andrea
 *
 */
public class EndTurn extends Action {
	
	private static final long serialVersionUID = 8980874164357261363L;

	/**
	 * The constructor of EndTurn.
	 */
	public EndTurn() {
		super();
	}
	
	/**
	 * Sets the game in finish state and notify the observers.
	 * @param board
	 */
	private void finishGame(Board board){
		State status=board.getStatus();
		status.setStatus("FINISH");
		board.notifyObserver(new StateChange(status));
		new Rank(board).createRank();
		board.notifyObserver(new RankChange(board.getPlayers()));
	}
	/**
	 * Runs the action.
	 * 
	 * @param turn The turn manager.
	 */
	public void runAction(Controller controller){
		Turn turn=controller.getTurn();
		Board board=turn.getBoard();
		boolean run=true;
		while(run){
			try {
				if(turn.changePlayer())
					finishGame(board);
				else{
					board.notifyObserver(new StateChange(board.getStatus()));
					board.notifyObserver(new BoardChange(board));
					run=controller.getView(board.getStatus().getCurrentPlayer()).getSuspended();
				}
			} catch (NegativeNumberException e) {
				getLogger().error(e);
			}
		}
	}

}
