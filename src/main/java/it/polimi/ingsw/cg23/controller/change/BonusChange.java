package it.polimi.ingsw.cg23.controller.change;

import it.polimi.ingsw.cg23.model.bonus.Bonus;

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BonusChange [bonus=" + bonus + "]";
	}

}
