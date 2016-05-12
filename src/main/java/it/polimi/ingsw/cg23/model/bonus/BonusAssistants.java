package it.polimi.ingsw.cg23.model.bonus;

import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.exception.NegativeNumberException;

public class BonusAssistants implements Bonus {
	
	private final int assistants;				//the amount of assistants given by the bonus
	
	
	
	public BonusAssistants(int assistants) {
		this.assistants = assistants;
	}
	

	/**
	 * @return the assistants
	 */
	public int getAssistants() {
		return assistants;
	}

	/**
	 * add to the player's assistants' pool the amount of assistants of the bonus
	 * 
	 * @param player
	 */
	@Override
	public void giveBonus(Player player) {
		int playerAssistants = player.getAssistants();				//set in a variable the amount of assistants of the player		
		playerAssistants = playerAssistants + this.assistants;		//add to the variable the assistants given by the bonus
		try {
			player.setAssistants(playerAssistants);					//set the player's pool and throw an exception
		} catch (NegativeNumberException e) {
			System.out.println("The bonus makes the player have negative assistants");
		}

	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BonusAssistants [assistants=" + assistants + "]";
	}
	
	

}
