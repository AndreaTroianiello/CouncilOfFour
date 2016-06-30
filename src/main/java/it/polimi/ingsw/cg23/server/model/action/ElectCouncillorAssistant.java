package it.polimi.ingsw.cg23.server.model.action;

import java.awt.Color;

import it.polimi.ingsw.cg23.server.controller.change.BoardChange;
import it.polimi.ingsw.cg23.server.controller.change.InfoChange;
import it.polimi.ingsw.cg23.server.controller.change.PlayerChange;
import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.action.utilities.Elector;
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
	private final Elector elector;
	
	
	/**
	 * the constructor set the variables of the class: main is set to false, and the other variables are set
	 * as the parameter given to the method
	 * 
	 * @param councillor the color of the councillor to be elected
	 * @param region the region of the election
	 * @param king if the election is for the king's council
	 * @throws NullPointerException if the councillor is null, and if the region is null and the king is false.
	 */
	public ElectCouncillorAssistant(Color councillor, Region region, boolean king){
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
		this.elector = new Elector(controlAction);
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
	 * elects a new councillor and update the player's assistant's pool
	 * @param player who runs the action
	 * @param board the model of the game
	 * 
	 * @return true if the action is successful, false otherwise
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
			this.notifyObserver(new InfoChange(e.getMessage()));
			return false;
		}
		Councillor newCouncillor=board.getCouncillor(councillor);
		if(newCouncillor != null){
			this.elector.election(newCouncillor, board, this.region, this.king);
			board.notifyObserver(new BoardChange(board));
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
