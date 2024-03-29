package it.polimi.ingsw.cg23.server.model.bonus;

import java.util.List;

import it.polimi.ingsw.cg23.observer.Observable;
import it.polimi.ingsw.cg23.server.controller.change.BonusChange;
import it.polimi.ingsw.cg23.server.controller.change.Change;
import it.polimi.ingsw.cg23.server.controller.change.InfoChange;
import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.components.RegionDeck;

/**
 * the class of the bonus that allows to get a permit tile from a region.
 * 
 * @author Vincenzo
 *
 */
public class BonusGetPermitTile extends Observable<Change>implements Bonus {

	private static final long serialVersionUID = 5218205311068329970L;
	private final String name;
	private int number;
	private Board board;
	private Region region;
	private int numberTile;

	/**
	 * the constructor set number to 1 and the name as the name of the bonus
	 */
	public BonusGetPermitTile() {
		this.name = "GetPermitTile";
		this.number = 1;
	}

	/**
	 * @return the bonus name and the number(if exist)
	 */
	@Override
	public String getName() {
		return number + name;
	}

	@Override
	public void setNumber(int number) {
		// this is a method of the Bonus interfaced not used in this class
	}

	/**
	 * Set the board of the bonus
	 */
	@Override
	public void setBoard(Board board) {
		this.board = board;
	}

	/**
	 * Set the tile to be taken with the bonus
	 * 
	 * @param region
	 *            where the tile is taken from
	 * @param numberTile
	 *            the number of the tile in the showed deck(form 0 to
	 *            maxShowedTile-1)
	 */
	public void setTile(Region region, int numberTile) {
		this.region = region;
		this.numberTile = numberTile;
	}

	/**
	 * give to the player the PermitTitle chosen and replace it on the field
	 * with the one on the top of the region deck
	 * 
	 * @param player
	 *            whom the bonus is given to
	 */
	@Override
	public void giveBonus(Player player) {
		Region realRegion = searchRegion();
		if (realRegion != null) {
			RegionDeck deck = realRegion.getDeck();
			if (deck.getShowedDeck().isEmpty()) {
				this.notifyObserver(new InfoChange("The tiles are finished."));
				return;
			}
			if (numberTile < deck.getShowedDeck().size() && numberTile >= 0) {
				player.getAvailableBusinessPermits().add(deck.getShowedDeck().get(numberTile)); // add
																								// the
																								// chosen
																								// PermitTitle
																								// to
																								// the
																								// player
																								// collection
				realRegion.getDeck().changeShowedDeck(); // replace the
															// PermitTitle
															// chosen with the
															// one in top of the
															// deck
			}
		} else {
			this.notifyObserver(new BonusChange(this));
			return;
		}
	}

	/**
	 * Search if the region is in the board
	 * 
	 * @return null if the board is null or the region isn't found, the region
	 *         otherwise
	 */
	private Region searchRegion() {
		if (board == null)
			return null;
		List<Region> regions = board.getRegions();
		for (Region r : regions)
			if (r.getName().equals(region.getName()))
				return r;
		return null;
	}

	/**
	 * @return the number of the tile
	 */
	@Override
	public int getNumber() {
		return number;
	}

	/**
	 * @return the name of the class as string
	 */
	@Override
	public String toString() {
		return "BonusGetPermitTile";
	}

	/**
	 * @return a new BonusGetPermitTile
	 */
	@Override
	public Bonus copy() {
		return new BonusGetPermitTile();
	}

}