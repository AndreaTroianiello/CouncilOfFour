package it.polimi.ingsw.cg23.model.bonus;

import it.polimi.ingsw.cg23.model.Player;

public class BonusAdditionalAction implements Bonus {

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BonusAdditionalAction []";
	}
	
	
}
