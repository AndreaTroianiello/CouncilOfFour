package it.polimi.ingsw.cg23.model.action;

import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.Player;

/**
 * the class of the action that allows to make an additional main action. It contains a boolean that show if
 * it is a main action or not.
 *
 * @author Vincenzo
 */
public class AdditionalAction implements Action {
	
	private final boolean main;
	
	
	/**
	 * the constructor set the boolean main to false
	 */
	public AdditionalAction() {
		this.main = false;
	}
	


	/**
	 * @return the main
	 */
	public boolean isMain() {
		return main;
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
	}

	
	/** 
	 * @return the name of the class in string
	 */
	@Override
	public String toString() {
		return "AdditionalAction []";
	}

	
}
