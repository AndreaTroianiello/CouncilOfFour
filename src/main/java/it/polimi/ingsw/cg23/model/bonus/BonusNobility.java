/**
 * 
 */
package it.polimi.ingsw.cg23.model.bonus;

import it.polimi.ingsw.cg23.model.Player;

/**
 * @author utente
 *
 */
public class BonusNobility implements Bonus {
	
	private final int steps;
	

	public BonusNobility(int steps) {
		this.steps = steps;
	}
	
	/**
	 * @return the steps
	 */
	public int getSteps() {
		return steps;
	}
	

	/*
	 * @see it.polimi.ingsw.cg23.model.bonus.Bonus#giveBonus(it.polimi.ingsw.cg23.model.Player)
	 */ 
	/** make the player advance tot steps in the nobility track 
	 */
	@Override
	public void giveBonus(Player player) {
		player.moveNobilityTrack(steps);
	}

	
	

}
