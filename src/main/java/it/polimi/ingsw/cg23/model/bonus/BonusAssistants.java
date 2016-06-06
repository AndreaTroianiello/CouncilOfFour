package it.polimi.ingsw.cg23.model.bonus;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.exception.NegativeNumberException;

/**
 * the class of the bonus that allows the player to take an amount of assistants specified it the bonus. It
 * contains the number of assistants given by the bonus and a string of the name
 * 
 * @author utente
 *
 */
public class BonusAssistants implements Bonus {
	
	private static final long serialVersionUID = -5759832124873766927L;
	private int assistants;				//the amount of assistants given by the bonus
	private final String name;
	
	private static Logger logger;
	
	/**
	 * the construcotor set the name of the bonus and the assistants to 0
	 */
	public BonusAssistants() {
		BonusAssistants.logger = Logger.getLogger(BonusAssistants.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");
		this.assistants = 0;
		this.name="Assistants";
	}
	

	/**
	 * @return the assistants
	 */
	public int getAssistants() {
		return assistants;
	}
	
	/**
	 * it sets the number of the assistants that the bonus gives
	 */
	@Override
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
		int playerAssistants = player.getAssistantsPool().getAssistants();				//set in a variable the amount of assistants of the player		
		playerAssistants = playerAssistants + this.assistants;							//add to the variable the assistants given by the bonus
		if(this.assistants>0){
			try {
				player.getAssistantsPool().setAssistants(playerAssistants);					//set the player's pool and throw an exception
			} catch (NegativeNumberException e) {
				logger.error("The bonus makes the player have negative assistants", e);
			}
		}
	}
	
	@Override
	public void setParameters(){
		
	}
	
	/**
	 * return the bonus name and the number(if exist)
	 */
	@Override
	public String getName(){
		return assistants+name;
	}

	/**
	 *  @return the name and the variables of the class in string
	 */
	@Override
	public String toString() {
		return "BonusAssistants [assistants=" + assistants + "]";
	}
	
	/**
	 * @return a new BonusAssistants
	 */
	@Override
	public Bonus clone() {
		return new BonusAssistants(); 
	}	

}
