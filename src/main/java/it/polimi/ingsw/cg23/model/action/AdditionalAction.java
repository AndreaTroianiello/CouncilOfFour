package it.polimi.ingsw.cg23.model.action;

import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.Player;

public class AdditionalAction implements Action {
	
	private final boolean main;
	
	
	
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

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AdditionalAction []";
	}

	
}
