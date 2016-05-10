package it.polimi.ingsw.cg23.model.action;

import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.exception.NegativeNumberException;

public class ChangeBusinessPermit extends SecondaryAction implements Action {
	
	private final int region;
	

	public ChangeBusinessPermit(int region) {
		this.region = region;
	}

	/**
	 * @return the region
	 */
	public int getRegion() {
		return region;
	}

	/**
	 * change the chosen region's showed deck and take
	 * one assistants from the player's assistants' pool
	 */
	@Override
	public void runAction(Player player, Board board) {
		int assistants = player.getAssistants();
		assistants = assistants -1;
		try {
			player.setAssistants(assistants);
		} catch (NegativeNumberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		board.getRegions().get(region).getDeck().changeShowedDeck();

	}

}
