/**
 * 
 */
package it.polimi.ingsw.cg23.model.bonus;

import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.components.NobilityBox;

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
		NobilityBox nobilityBox = player.getPlayerNobilityTrack().getNobilityBoxes().get(player.getNobilityBoxPosition()+steps);
		player.setNobilityBoxPoistion(player.getNobilityBoxPosition()+steps);
		for(Bonus b: nobilityBox.getBonus()){
			b.giveBonus(player);
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BonusNobility [steps=" + steps + "]";
	}

	public String toStringName(){
		return "BonusNobility";
	}
	

}
