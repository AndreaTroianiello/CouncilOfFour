package it.polimi.ingsw.cg23.model.action;

import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.exception.NegativeNumberException;

public class ChangeBusinessPermit implements Action {
	
	private final int region;
	private final boolean main;
	

	public ChangeBusinessPermit(int region) {
		this.region = region;
		this.main = false;
	}
	
	

	/**
	 * @return the main
	 */
	public boolean isMain() {
		return main;
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
		int assistants = player.getAssistantsPool().getAssistants();
		assistants = assistants -1;
		try {
			player.getAssistantsPool().setAssistants(assistants);
		} catch (NegativeNumberException e) {
			return;
		}
		
		board.getRegions().get(region).getDeck().changeShowedDeck();

	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ChangeBusinessPermit [region=" + region + "]";
	}
	
	

}
