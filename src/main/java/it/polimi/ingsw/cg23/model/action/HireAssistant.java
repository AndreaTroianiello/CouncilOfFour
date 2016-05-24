package it.polimi.ingsw.cg23.model.action;

import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.exception.NegativeNumberException;

/**
 * the class of the action that allows to hire an assistants by paying 3 coins. It contains a boolean
 * that shows if it is a main action or not.
 *
 *@author Vincenzo
 */
public class HireAssistant implements Action {
	
	private final boolean main;
	
	
	/**
	 * the constructor set the variables of the class: the boolean main is set to false
	 */
	public HireAssistant() {
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
	 * give the player an assistant and take
	 * from him 3 coins
	 * 
	 * @param palyer
	 * @param board
	 */
	@Override
	public void runAction(Player player, Board board) {
		int coin = player.getRichness().getCoins();
		int assistants = player.getAssistantsPool().getAssistants();
		
		coin = coin - 3;
		try {
			player.getRichness().setCoins(coin);
		} catch (NegativeNumberException e) {
			return;
		}
		
		assistants = assistants + 1;
		try {
			player.getAssistantsPool().setAssistants(assistants);
		} catch (NegativeNumberException e) {
			return;
		}

	}

	/**
	 * @return the name and the variables of the class in string
	 */
	@Override
	public String toString() {
		return "HireAssistant []";
	}
	
	
	

}
