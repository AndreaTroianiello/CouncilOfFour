package it.polimi.ingsw.cg23.server.model.components;


import it.polimi.ingsw.cg23.server.model.exception.NegativeNumberException;
import it.polimi.ingsw.cg23.server.model.marketplace.CanBeSold;

/**
 * The assistants pool contains the player's assistants.
 * @author Andrea
 *
 */
public class AssistantsPool implements CanBeSold{
	
	private static final long serialVersionUID = -8746286160268047774L;
	private int assistants;

	/**
	 * The constructor of assistants pool. The default value of assistants is 0. 
	 */
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
