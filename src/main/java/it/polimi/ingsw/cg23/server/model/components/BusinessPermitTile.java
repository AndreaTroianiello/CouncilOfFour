package it.polimi.ingsw.cg23.server.model.components;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.cg23.server.model.bonus.Bonus;
import it.polimi.ingsw.cg23.server.model.marketplace.CanBeSold;

/**
 * The business permit tile gives bonuses and allows you to build in certain
 * cities.
 * 
 * @author Andrea
 *
 */
public class BusinessPermitTile implements CanBeSold {

	private static final long serialVersionUID = -3360173408293385934L;
	private final List<Character> citiesId;
	private final List<Bonus> bonusTile;
	private final String zone;

	/**
	 * The constructor of the permit tile.
	 * 
	 * @param citiesId
	 *            The list of cities where you can build with this tile.
	 * @param zone
	 *            The region whose deck contains this tile.
	 */
	public BusinessPermitTile(List<Character> citiesId, String zone) {
		this.zone = zone;
		this.citiesId = citiesId;
		this.bonusTile = new ArrayList<>();
	}

	/**
	 * Returns the all id of cities, where you can build.
	 * 
	 * @return the citiesId
	 */
	public List<Character> getCitiesId() {
		return citiesId;
	}

	/**
	 * Returns all bonus of the tile.
	 * 
	 * @return the bonusTitle
	 */
	public List<Bonus> getBonusTile() {
		return bonusTile;
	}

	/**
	 * @return the zone
	 */
	public String getZone() {
		return zone;
	}

	/**
	 * Adds a bonus in the tile.
	 * 
	 * @param bonus
	 */
	public void addBonus(Bonus bonus) {
		this.bonusTile.add(bonus);
	}

	/**
	 * It generates a string formed by the most significant statistics of the
	 * Business Permit Tile.
	 * 
	 * @return string
	 */
	@Override
	public String toString() {
		return "BusinessPermitTile [citiesId=" + citiesId + ", bonusTile=" + bonusTile + " zone=" + zone + "]";
	}

}
