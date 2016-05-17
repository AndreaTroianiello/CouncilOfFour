package it.polimi.ingsw.cg23.model.bonus;

import it.polimi.ingsw.cg23.model.Player;

public class BonusAdditionalAction implements Bonus {
	
	private final String name;
	
	public BonusAdditionalAction(){
		this.name="AdditionalAction";
	}
	
	/**
	 * if the player have the addictional action set to false,
	 * it switches it
	 * 
	 * @param player
	 */
	@Override
	public void giveBonus(Player player) {
		if(!player.isAdditionalAction())
			player.switchAdditionalAction();
	}
	
	public void setParameters(){
		
	}
	
	public void setNumber(int number){
		
	}
	
	/**
	 * return the bonus name and the number(if exist)
	 */
	@Override
	public String getName(){
		return name;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BonusAdditionalAction []";
	}

	public Bonus clone() {
		return new BonusAdditionalAction(); 
	}	
}
