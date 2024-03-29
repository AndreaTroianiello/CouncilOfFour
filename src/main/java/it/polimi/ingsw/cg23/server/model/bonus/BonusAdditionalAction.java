package it.polimi.ingsw.cg23.server.model.bonus;

import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Player;

/**
 * the class of the bonus that allows to have another main action.
 * 
 * @author Vincenzo
 *
 */
public class BonusAdditionalAction implements Bonus {

	private static final long serialVersionUID = -7894718147717452032L;
	private final String name;

	/**
	 * the constructor set the name of the bonus
	 */
	public BonusAdditionalAction() {
		this.name = "AdditionalAction";
	}

	/**
	 * if the player have the additional action set to false, it switches it
	 * 
	 * @param player
	 *            whom the bonus is given to
	 */
	@Override
	public void giveBonus(Player player) {
		boolean addictionalAction = player.isAdditionalAction();
		if (!addictionalAction) {
			player.switchAdditionalAction();
		}
	}

	@Override
	public int getNumber() {
		return 0;
	}

	@Override
	public void setNumber(int number) {
		// Not implemented.
	}

	@Override
	public void setBoard(Board board) {
		// Not implemented.
	}

	/**
	 * @return the bonus name and the number(if exist)
	 */
	@Override
	public String getName() {
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
	public Bonus copy() {
		return new BonusAdditionalAction();
	}
}
