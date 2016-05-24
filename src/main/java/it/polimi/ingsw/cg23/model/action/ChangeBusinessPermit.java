package it.polimi.ingsw.cg23.model.action;

import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.exception.NegativeNumberException;

/**
 * the class of the action that allows to change the showed business permit tile in one region which the
 * firsts cards in the hidden deck
 *
 * @author Vincenzo
 */
public class ChangeBusinessPermit implements Action {
	
	private final int region;
	private final boolean main;
	

	/**
	 * the constructor set the variables of the class: main is set to false, and region is set as 
	 * the parameter given to the method
	 * 
	 * @param region
	 */
	public ChangeBusinessPermit(int region) {
		this.region = region;
		this.main = false;
	}
	
	

	/**
	 * @return the main
	 */
	@Override
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

	/** 
	 * @return the name and the variables of the class in string
	 */
	@Override
	public String toString() {
		return "ChangeBusinessPermit [region=" + region + "]";
	}
	
	

}
