package it.polimi.ingsw.cg23.model.bonus;

import it.polimi.ingsw.cg23.model.Player;

/**
 * the class of the bonus that allows to have another main action. It contains the string of the name
 * 
 * @author Vincenzo
 *
 */
public class BonusAdditionalAction implements Bonus {

	
	private final String name;
	
	/**
	 * the constructor set the name of the bonus
	 */
	public BonusAdditionalAction(){
		this.name="AdditionalAction";
	}
	
	/**
	 * if the player have the additional action set to false,
	 * it switches it
	 * 
	 * @param player
	 */
	@Override
	public void giveBonus(Player player) {
		if(!player.isAdditionalAction())
			player.switchAdditionalAction();
	}
	
	@Override
	public void setParameters(){
		
	}
	
	@Override
	public void setNumber(int number){
		
	}
	
	/**
	 * return the bonus name and the number(if exist)
	 */
	@Override
	public String getName(){
		return name;
	}
	
	/**
	 * @return the name of the bonus in string
	 */
	@Override
	public String toString() {
		return "BonusAdditionalAction []";
	}

	/**
	 * @return a new BonusAdditionalAction
	 */
	@Override
	public Bonus clone() {
		return new BonusAdditionalAction(); 
	}	
}
