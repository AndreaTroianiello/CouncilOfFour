package it.polimi.ingsw.cg23.server.model.bonus;

import it.polimi.ingsw.cg23.observer.Observable;
import it.polimi.ingsw.cg23.server.controller.change.BonusChange;
import it.polimi.ingsw.cg23.server.controller.change.Change;
import it.polimi.ingsw.cg23.server.controller.change.InfoChange;
import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.components.BusinessPermitTile;

/**
 * the class of the bonus that allows to run the bonus from a tile the player is
 * in possession.
 * 
 * @author Vincenzo
 *
 */
public class BonusTileBonus extends Observable<Change>implements Bonus {

	private static final long serialVersionUID = -5750535311281465339L;
	private final String name;
	private int number;
	private int numberTile;

	/**
	 * the constructor set number and numberTile to 1 and the name of the bonus
	 * 
	 */
	public BonusTileBonus() {
		this.number = 1;
		this.numberTile = -1;
		this.name = "TileBonus";
	}

	/**
	 * Set the numberTile
	 * 
	 * @param numberTile
	 *            the number of the tile chosen
	 */
	public void setNumberTile(int numberTile) {
		this.numberTile = numberTile;
	}

	/**
	 * @return the bonus name and the number(if exist)
	 */
	@Override
	public String getName() {
		return number + name;
	}

	/**
	 * for businessPermitTile give the player the bonuses in it
	 * 
	 * @param player
	 *            whom the bonus is given to
	 */
	@Override
	public void giveBonus(Player player) {
		if (player.getAvailableBusinessPermits().isEmpty()) {
			this.notifyObserver(new InfoChange("The list is empty."));
			return;
		}
		if (numberTile < player.getAvailableBusinessPermits().size() && numberTile >= 0) {
			BusinessPermitTile businessPermitTile = player.getAvailableBusinessPermits().get(numberTile); // add
																											// the
																											// chosen
																											// PermitTitle
																											// to
																											// the
																											// player
																											// collection
			for (Bonus bonus : businessPermitTile.getBonusTile()) { // iterate
																	// the bonus
																	// in the
																	// tile and
				bonus.giveBonus(player); // for each bonus give it to the player
			}
		} else {
			this.notifyObserver(new BonusChange(this));
			return;
		}
	}

	@Override
	public void setNumber(int number) {
		// this is a method of the Bonus interfaced not used in this class
	}

	/**
	 * @return number
	 */
	@Override
	public int getNumber() {
		return number;
	}

	@Override
	public void setBoard(Board board) {
		// Not implemented.
	}

	/**
	 * @return the name of the class as string
	 */
	@Override
	public String toString() {
		return "BonusTileBonus [businessPermitCard=" + number + "]";
	}

	/**
	 * @return a new BonusTileBonus
	 */
	@Override
	public Bonus copy() {
		return new BonusTileBonus();
	}

}
