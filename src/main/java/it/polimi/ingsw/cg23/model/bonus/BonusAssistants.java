package it.polimi.ingsw.cg23.model.bonus;

import it.polimi.ingsw.cg23.model.Player;

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


	// add to the player's assistants' pool the amount of assistants of the bonus
	@Override
	public void esegui(Player player) {
		int playerAssistants = player.getAssistants();
		playerAssistants = playerAssistants + this.assistants;
		player.setAssistants(playerAssistants);

	}

}
