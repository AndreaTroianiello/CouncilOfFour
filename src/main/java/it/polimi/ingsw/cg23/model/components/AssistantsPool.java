package it.polimi.ingsw.cg23.model.components;

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
	 */
	public void setAssistants(int assistants) {
		this.assistants = assistants;
	}
	
}
