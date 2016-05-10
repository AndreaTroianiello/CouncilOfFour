package it.polimi.ingsw.cg23.model.action;

import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.Player;

public class AdditionalAction extends SecondaryAction implements Action {
	
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

}
