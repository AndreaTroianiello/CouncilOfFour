package it.polimi.ingsw.cg23.model.action;

import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.exception.NegativeNumberException;

public class HireAssistant implements Action {
	
	private final boolean main;
	
	
	
	public HireAssistant() {
		this.main = false;
	}

	
	/**
	 * @return the main
	 */
	public boolean isMain() {
		return main;
	}


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
			return;
		}
		
		assistants = assistants + 1;
		try {
			player.setAssistants(assistants);
		} catch (NegativeNumberException e) {
			return;
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
