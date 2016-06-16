package it.polimi.ingsw.cg23.server.model.action;

import java.awt.Color;


import it.polimi.ingsw.cg23.server.controller.change.CouncilChange;
import it.polimi.ingsw.cg23.server.controller.change.ErrorChange;
import it.polimi.ingsw.cg23.server.controller.change.PlayerChange;
import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.components.Councillor;
import it.polimi.ingsw.cg23.server.model.exception.NegativeNumberException;

/**
 * the class of the action that allows to elect a councillor by paying one assistants. It contains the color
 * of the chosen councillor, the chose region, a boolean that shows if the player chooses the king's council
 * and a boolean that shows if it is a main action
 *
 *@author Vincenzo
 */
public class ElectCouncillorAssistant extends GameAction implements StandardAction{

	private static final long serialVersionUID = -8838134392923545287L;
	private final Color councillor;
	private final Region region; 											//wich region the player choose 
	private final boolean king;
	private ControlAction controlAction;
	
	
	/**
	 * the constructor set the variables of the class: main is set to false, and the other variables are set
	 * as the parameter given to the method
	 * 
	 * @param councillor
	 * @param region
	 * @param king
	 * @throws NullPointerException if the paramiters are null.
	 */
	public ElectCouncillorAssistant(Color councillor, Region region, boolean king) throws NullPointerException {
		super(false);
		if(councillor!=null)
			this.councillor = councillor;
		else
			throw new NullPointerException();
		if(region!=null || king){
			this.region = region;
			this.king = king;
		}
		else
			throw new NullPointerException();
		this.controlAction = new ControlAction();
	}

	/**
	 * @return the king
	 */
	public boolean isKing() {
		return king;
	}



	/**
	 * @return the region
	 */
	public Region getRegion() {
		return region;
	}


	/**
	 * @return the councillor
	 */
	public Color getCouncillor() {
		return councillor;
	}

	/**
	 * remove the first councillor from the chosen council and append
	 * the new one
	 * @param player
	 * @param board
	 */
	@Override
	public boolean runAction(Player player, Board board){
		int assistants = player.getAssistantsPool().getAssistants();
		assistants = assistants - 1;
		try {
			player.getAssistantsPool().setAssistants(assistants);
			board.notifyObserver(new PlayerChange(player));
		} catch (NegativeNumberException e) {
			getLogger().error(e);
			this.notifyObserver(new ErrorChange(e.getMessage()));
			return false;
		}
		Councillor newCouncillor=board.getCouncillor(councillor);
		if(newCouncillor != null){
			if(!this.king){
				Region realRegion = controlAction.controlRegion(region, board);
				if(realRegion != null){
					Councillor oldCouncillor=this.region.getCouncil().getCouncillors().remove(0);				//remove the first councillor in the chosen council
					board.setCouncillor(oldCouncillor);
					this.region.getCouncil().getCouncillors().add(newCouncillor);								//append the chosen councillor in the same council
					board.notifyObserver(new CouncilChange(this.region.getCouncil()));
				}
			}
			else{
				Councillor oldCouncillor=board.getKing().getCouncil().getCouncillors().remove(0);				//remove the first councillor in the chosen council
				board.setCouncillor(oldCouncillor);
				board.getKing().getCouncil().getCouncillors().add(newCouncillor);								//append the chosen councillor in the same council
				board.notifyObserver(new CouncilChange(board.getKing().getCouncil()));
			}
			return true;
		}
		try {
			player.getAssistantsPool().setAssistants(assistants+1);
		} catch (NegativeNumberException e) {
			getLogger().error(e);
		}
		return false;
	}



	/**
	 *  @return the name and the variables of the class in string
	 */
	@Override
	public String toString() {
		return "ElectCouncillorAssistant [councillor=" + councillor + ", region=" + region + ", king=" + king + "]";
	}
	
	
	
}
