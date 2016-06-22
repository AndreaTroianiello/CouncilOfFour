package it.polimi.ingsw.cg23.server.controller.action;

import it.polimi.ingsw.cg23.server.controller.Controller;
import it.polimi.ingsw.cg23.server.controller.Timer;
import it.polimi.ingsw.cg23.server.controller.Turn;
import it.polimi.ingsw.cg23.server.controller.change.BoardChange;
import it.polimi.ingsw.cg23.server.controller.change.RankChange;
import it.polimi.ingsw.cg23.server.controller.change.StateChange;
import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Rank;
import it.polimi.ingsw.cg23.server.model.State;
import it.polimi.ingsw.cg23.server.model.exception.NegativeNumberException;

/**
 * The action EndTurn is used to end turn and to notify the change of the current player.
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
	private void finishGame(Controller controller){
		Board board=controller.getTurn().getBoard();
		State status=board.getStatus();
		status.setStatus("FINISH");
		board.notifyObserver(new StateChange(status));
		new Rank(board).createRank();
		board.notifyObserver(new RankChange(board.getPlayers()));
		controller.resetInterconnections();
	}
	
	/**
	 * Controls the status of view and if necessary change the state. 
	 * If all views are suspended sets the status at finish.
	 * @param controller The controller of the game.
	 * @return if false all views are suspended or the current view isn't suspended.
	 */
	private boolean controlSuspended(Controller controller){
		Board board=controller.getTurn().getBoard();
		if(controller.isAllSuspended()){
			finishGame(controller);
			return false;
		}
		else{
			board.notifyObserver(new StateChange(board.getStatus()));
			board.notifyObserver(new BoardChange(board));
			return controller.getView(board.getStatus().getCurrentPlayer()).getSuspended();
		}
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
		if(turn.getTimer().isRunning())
			turn.getTimer().stop();
		while(run){
			try {
				if(turn.changePlayer()){
					finishGame(controller);
					run=false;
				}
				else{
					run=controlSuspended(controller);
				}
			} catch (NegativeNumberException e) {
				getLogger().error(e);
			}
		}
		if(!"FINISH".equals(board.getStatus().getStatus()))
			new Thread(new Timer(controller.getView(turn.getCurrentPlayer()),controller)).start();
	}

}
