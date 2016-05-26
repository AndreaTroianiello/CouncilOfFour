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
public class HireAssistant extends GameAction {
	
	private static final long serialVersionUID = 157988041663947858L;

	/**
	 * the constructor set the variables of the class: the boolean main is set to false
	 */
	public HireAssistant() {
		super(false);
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
