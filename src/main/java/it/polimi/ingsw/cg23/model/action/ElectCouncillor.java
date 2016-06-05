package it.polimi.ingsw.cg23.model.action;

import java.awt.Color;

import org.apache.log4j.Logger;

import it.polimi.ingsw.cg23.controller.change.CouncilChange;
import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.Region;
import it.polimi.ingsw.cg23.model.components.Councillor;
import it.polimi.ingsw.cg23.model.exception.NegativeNumberException;

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
	
	private static Logger logger;
	
	/**
	 * the constructor set the variables of the class: it set the main to true, and the other 
	 * variables as the paramater given to the method
	 * 
	 * @param councillor
	 * @param region
	 * @param king
	 */
	public ElectCouncillor(Color councillor, Region region, boolean king) {
		super(false);
		logger = Logger.getLogger(ElectCouncillor.class);
		this.councillor = councillor;
		this.region = region;
		this.king = king;
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
	public void runAction(Player player, Board board){
		Councillor newCouncillor=board.getCouncillor(councillor);
		if(newCouncillor!=null){
			if(!this.king){
				Councillor oldCouncillor=this.region.getCouncil().getCouncillors().remove(0);				//remove the first councillor in the chosen council
				board.setCouncillor(oldCouncillor);
				this.region.getCouncil().getCouncillors().add(newCouncillor);								//append the chosen councillor in the same council
				this.notifyObserver(new CouncilChange(this.region.getCouncil()));
			}
			else{
				Councillor oldCouncillor=board.getKing().getCouncil().getCouncillors().remove(0);			//remove the first councillor in the chosen council
				board.setCouncillor(oldCouncillor);
				board.getKing().getCouncil().getCouncillors().add(newCouncillor);							//append the chosen councillor in the same council
				this.notifyObserver(new CouncilChange(board.getKing().getCouncil()));
			}
			int coins = player.getRichness().getCoins();
			coins = coins +4;
			try{
				player.getRichness().setCoins(coins);
			} catch(NegativeNumberException e){
				logger.error(e);
			}			
		}
	}

	/**
	 *  @return the name and the variables of the class in string
	 */
	@Override
	public String toString() {
		return "ElectCouncillor [councillor=" + councillor + ", region=" + region + ", king=" + king + "]";
	}
	
	

}
