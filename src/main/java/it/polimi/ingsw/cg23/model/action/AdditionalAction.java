package it.polimi.ingsw.cg23.model.action;

import it.polimi.ingsw.cg23.controller.change.PlayerChange;
import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.Player;

/**
 * the class of the action that allows to make an additional main action. It contains a boolean that show if
 * it is a main action or not.
 *
 * @author Vincenzo
 */
public class AdditionalAction extends GameAction implements StandardAction {

	private static final long serialVersionUID = -1876787306172166338L;

	/**
	 * the constructor set the boolean main to false
	 */
	public AdditionalAction() {
		super(false);
	}

	/**
	 * switch addictionalAction in true 
	 * @param player
	 * @param board
	 */
	@Override
	public void runAction(Player player, Board board) {
		boolean addictionalAction = player.isAdditionalAction();
		if(!addictionalAction){
			player.switchAdditionalAction();
		}
		this.notifyObserver(new PlayerChange(player));
	}

	
	/** 
	 * @return the name of the class in string
	 */
	@Override
	public String toString() {
		return "AdditionalAction []";
	}

	
}
