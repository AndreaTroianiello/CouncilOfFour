package it.polimi.ingsw.cg23.model.bonus;

import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.exception.NegativeNumberException;

public class BonusAssistants implements Bonus {
	
	private final int assistants;
	
	
	//BonusAssistants' constructor
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
	 * @param player
	 */
	// add to the player's assistants' pool the amount of assistants of the bonus
	@Override
	public void giveBonus(Player player) {
		int playerAssistants = player.getAssistants();
		playerAssistants = playerAssistants + this.assistants;
		try {
			player.setAssistants(playerAssistants);
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
