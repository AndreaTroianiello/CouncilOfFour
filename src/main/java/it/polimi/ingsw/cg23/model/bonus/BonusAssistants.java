package it.polimi.ingsw.cg23.model.bonus;

import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.exception.NegativeNumberException;

public class BonusAssistants implements Bonus {
	
	private int assistants;				//the amount of assistants given by the bonus
	private final String name;
	
	
	public BonusAssistants() {
		this.assistants = 0;
		this.name="Assistants";
	}
	

	/**
	 * @return the assistants
	 */
	public int getAssistants() {
		return assistants;
	}
	
	public void setNumber(int number){
		this.assistants = number;
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
	
	public void setParameters(){
		
	}
	
	/**
	 * return the bonus name and the number(if exist)
	 */
	@Override
	public String getName(){
		return assistants+name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BonusAssistants [assistants=" + assistants + "]";
	}
	
	public Bonus clone() {
		return new BonusAssistants(); 
	}	

}
