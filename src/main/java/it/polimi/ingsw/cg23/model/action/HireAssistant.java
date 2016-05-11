package it.polimi.ingsw.cg23.model.action;

import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.exception.NegativeNumberException;

public class HireAssistant extends SecondaryAction implements Action {
	
	/**
	 * give the player an assistant and take
	 * from him 3 coins
	 */
	@Override
	public void runAction(Player player, Board board) {
		int coin = player.getCoins();
		int assistants = player.getAssistants();
		
		coin = coin - 3;
		try {
			player.setCoins(coin);
		} catch (NegativeNumberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assistants = assistants + 1;
		try {
			player.setAssistants(assistants);
		} catch (NegativeNumberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HireAssistant []";
	}
	
	
	

}
