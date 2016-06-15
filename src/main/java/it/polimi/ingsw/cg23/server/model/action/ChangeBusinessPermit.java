package it.polimi.ingsw.cg23.server.model.action;


import it.polimi.ingsw.cg23.server.controller.change.BusinessPermitTileChange;
import it.polimi.ingsw.cg23.server.controller.change.ErrorChange;
import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.components.BusinessPermitTile;
import it.polimi.ingsw.cg23.server.model.exception.NegativeNumberException;

/**
 * the class of the action that allows to change the showed business permit tile in one region which the
 * firsts cards in the hidden deck
 *
 * @author Vincenzo
 */
public class ChangeBusinessPermit extends GameAction implements StandardAction{
	
	private static final long serialVersionUID = -2799809256014430924L;
	private final Region region;
	private final ControlAction controlAction;
	
	

	/**
	 * the constructor set the variables of the class: main is set to false, and region is set as 
	 * the parameter given to the method
	 * 
	 * @param region
	 * @throws NullPointerException if the parameters are null.
	 */
	public ChangeBusinessPermit(Region region) throws NullPointerException {
		super(false);
		if(region!=null)
			this.region = region;
		else 
			throw new NullPointerException();
		this.controlAction = new ControlAction();
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
	public boolean runAction(Player player, Board board) {
		Region realRegion = controlAction.controlRegion(region, board);
		if(realRegion != null){
			int assistants = player.getAssistantsPool().getAssistants();
			assistants = assistants -1;
			try {
				player.getAssistantsPool().setAssistants(assistants);
				realRegion.getDeck().changeShowedDeck();
			} catch (NegativeNumberException e) {
				getLogger().error("The player doesn't have enough assistants!", e);
				this.notifyObserver(new ErrorChange("The player doesn't have enough assistants!"));
				return false;
			}
		
		
			//notify the change
			for(BusinessPermitTile bpt : realRegion.getDeck().getShowedDeck()){
				board.notifyObserver(new BusinessPermitTileChange(bpt));
			}
			return true;
		}
		
		this.notifyObserver(new ErrorChange("Region not found!"));
		return false;
	}

	/** 
	 * @return the name and the variables of the class in string
	 */
	@Override
	public String toString() {
		return "ChangeBusinessPermit [region=" + region + "]";
	}
	
	

}
