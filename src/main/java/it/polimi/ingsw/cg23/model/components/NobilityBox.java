package it.polimi.ingsw.cg23.model.components;

import it.polimi.ingsw.cg23.model.bonus.Bonus;

public class NobilityBox {
	private final Bonus[] bonus;
	private final int bonusNumber;
	private final int position;
	

	// must be modified
	public NobilityBox(Bonus[] bonus, int bonusNumber, int position) {
		this.bonus = bonus;
		this.bonusNumber = bonusNumber;
		this.position = position;
	}



	/**
	 * @return the bonus
	 */
	public Bonus[] getBonus() {
		return bonus;
	}



	/**
	 * @return the bonusNumber
	 */
	public int getBonusNumber() {
		return bonusNumber;
	}



	/**
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}
	
	

}
