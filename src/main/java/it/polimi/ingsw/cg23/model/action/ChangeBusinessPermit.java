package it.polimi.ingsw.cg23.model.action;

import java.util.List;

import org.apache.log4j.Logger;

import it.polimi.ingsw.cg23.controller.change.BusinessPermitTileChange;
import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.Region;
import it.polimi.ingsw.cg23.model.components.BusinessPermitTile;
import it.polimi.ingsw.cg23.model.exception.NegativeNumberException;

/**
 * the class of the action that allows to change the showed business permit tile in one region which the
 * firsts cards in the hidden deck
 *
 * @author Vincenzo
 */
public class ChangeBusinessPermit extends GameAction implements StandardAction{
	
	private static final long serialVersionUID = -2799809256014430924L;
	private final Region region;
	
	

	/**
	 * the constructor set the variables of the class: main is set to false, and region is set as 
	 * the parameter given to the method
	 * 
	 * @param region
	 */
	public ChangeBusinessPermit(Region region) {
		super(false);
		this.region = region;
	}

	/**
	 * @return the region
	 */
	public Region getRegion() {
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
			getLogger().error("The player doesn't have enough assistants!", e);
			return;
		}
		
		region.getDeck().changeShowedDeck();
		
		//notify the change
		for(BusinessPermitTile bpt : region.getDeck().getShowedDeck()){
			this.notifyObserver(new BusinessPermitTileChange(bpt));
		}

	}

	/** 
	 * @return the name and the variables of the class in string
	 */
	@Override
	public String toString() {
		return "ChangeBusinessPermit [region=" + region + "]";
	}
	
	

}
