package it.polimi.ingsw.cg23.model.components;

import it.polimi.ingsw.cg23.model.exception.NegativeNumberException;

public class AssistantsPool {
	private int assistants;

	public AssistantsPool(int assistants) {
		this.assistants = assistants;
	}

	/**
	 * Returns the assistants of the pool.
	 * 
	 * @return the assistants
	 */
	public int getAssistants() {
		return assistants;
	}

	/**
	 * Sets the assistants contained in the assistants pool.
	 * 
	 * @param assistants the number of assistants to set.
	 * @throws NegativeNumberException the assistants of the player must be positive.
	 */
	public void setAssistants(int assistants) throws NegativeNumberException {
		if (assistants>=0)
			this.assistants=assistants;
		else
			throw new NegativeNumberException("The number of assistants can't be negative.");
	
	}
	
}
