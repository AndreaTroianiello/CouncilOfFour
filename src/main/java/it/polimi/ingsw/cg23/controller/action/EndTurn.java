package it.polimi.ingsw.cg23.controller.action;



import it.polimi.ingsw.cg23.controller.Turn;
import it.polimi.ingsw.cg23.controller.change.Change;
import it.polimi.ingsw.cg23.controller.change.StateChange;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.State;
import it.polimi.ingsw.cg23.observer.Observable;

/**
 * The action EndTurn is used to notify the change of the current player.
 * @author Andrea
 *
 */
public class EndTurn extends Observable<Change> implements Action {
	
	private Player player;
	
	/**
	 * The constructor of EndTurn.
	 */
	public EndTurn() {
		this.player=null;
	}
	
	/**
	 * Sets the player of the action.
	 * @param player the owner of the action.
	 */
	@Override
	public void setPlayer(Player player){
		this.player=player;
	}
	
	/**
	 * Return the player of the action.
	 * @return the owner of the action.
	 */
	@Override
	public Player getPlayer(){
		return player;
	}
	
	/**
	 * Runs the action.
	 * 
	 * @param turn The turn manager.
	 */
	public void runAction(Turn turn){
		if(turn.changePlayer()){
			State status=turn.getBoard().getStatus();
			status.setStatus("FINISH");
			this.notifyObserver(new StateChange(status));
		}
		else{
			turn.getBoard().changeStatus();
		}
	}

}
