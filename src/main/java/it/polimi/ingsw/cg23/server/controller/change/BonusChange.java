package it.polimi.ingsw.cg23.server.controller.change;

import it.polimi.ingsw.cg23.server.model.bonus.Bonus;

/**
 * The BonusChange serves to notify a bonus.
 * @author Andrea
 *
 */
public class BonusChange implements Change {

	private static final long serialVersionUID = 3242666695369598939L;
	private final Bonus bonus;
	
	/**
	 * The constructor of the BonusChange
	 * @param bonus The bonus.
	 */
	public BonusChange(Bonus bonus) {
		this.bonus=bonus;
	}

	
	public Bonus getBonus(){
		return bonus;
	}
}
