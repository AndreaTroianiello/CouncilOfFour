package it.polimi.ingsw.cg23.controller.action;

import java.util.List;

import it.polimi.ingsw.cg23.controller.Turn;
import it.polimi.ingsw.cg23.controller.change.StateChange;
import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.State;

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
	 * Runs the action.
	 * 
	 * @param turn The turn manager.
	 */
	public void runAction(Turn turn){
		Board board=turn.getBoard();
		if(turn.changePlayer()){
			State status=board.getStatus();
			status.setStatus("FINISH");
			this.notifyObserver(new StateChange(status));
			return;
		}
		if(turn.isChangeState()){
			board.changeStatus();
			List<Player> players=board.getPlayers();
			State status=turn.getBoard().getStatus();
			if("MARKET: BUYING".equals(status.getStatus()))
				turn.setPlayers(board.getMarket().generatePlayersList(players));
			else
				turn.setPlayers(players);
		}
		else
			board.notifyObserver(new StateChange(board.getStatus()));
	}

}
