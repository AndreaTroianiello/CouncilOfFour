package it.polimi.ingsw.cg23.server.model.action;

import java.awt.Color;


import it.polimi.ingsw.cg23.server.controller.change.CouncilChange;
import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.components.Councillor;
import it.polimi.ingsw.cg23.server.model.exception.NegativeNumberException;

/**
 * the class of the action that allows to elect a new councillor in a region. It contains the color 
 * of the chosen councillor, the chosen region, a boolean that shows if it is a main aciton, and a boolean
 * that shows if the player chooses the king's council
 *
 *@author Vincenzo
 */
public class ElectCouncillor extends GameAction implements StandardAction{
	
	private static final long serialVersionUID = -2461481103395662345L;
	private final Color councillor;
	private final Region region; 											//wich region the player choose 
	private final boolean king;
	private final ControlAction controlAction;
	
	/**
	 * the constructor set the variables of the class: it set the main to true, and the other 
	 * variables as the paramater given to the method
	 * 
	 * @param councillor
	 * @param region
	 * @param king
	 * @throws NullPointerException if the parameters are null.
	 */
	public ElectCouncillor(Color councillor, Region region, boolean king) throws NullPointerException {
		super(true);
		if(councillor!=null&&(region!=null || king)){
			this.councillor = councillor;
			this.region = region;
			this.king = king;
		}else
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
		Councillor newCouncillor=board.getCouncillor(councillor);
		if(newCouncillor!=null){
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
				Councillor oldCouncillor=board.getKing().getCouncil().getCouncillors().remove(0);			//remove the first councillor in the chosen council
				board.setCouncillor(oldCouncillor);
				board.getKing().getCouncil().getCouncillors().add(newCouncillor);							//append the chosen councillor in the same council
				board.notifyObserver(new CouncilChange(board.getKing().getCouncil()));
			}
			int coins = player.getRichness().getCoins();
			coins = coins +4;
			try{
				player.getRichness().setCoins(coins);
			} catch(NegativeNumberException e){
				getLogger().error(e);
			}
			return true;
		}
		return false;
	}

	/**
	 *  @return the name and the variables of the class in string
	 */
	@Override
	public String toString() {
		return "ElectCouncillor [councillor=" + councillor + ", region=" + region + ", king=" + king + "]";
	}
	
	

}
