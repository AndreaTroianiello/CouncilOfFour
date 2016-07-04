package it.polimi.ingsw.cg23.server.controller.change;

import it.polimi.ingsw.cg23.server.model.components.BusinessPermitTile;

/**
 * The BusinessPermitTileChange serves to notify a new tile.
 * 
 * @author Andrea
 *
 */
public class BusinessPermitTileChange implements Change {

	private static final long serialVersionUID = -70859360088360813L;
	private final BusinessPermitTile newTile;

	/**
	 * The constructor of the BusinessPermitTileChange
	 * 
	 * @param newTile
	 *            The new business permit tile.
	 */
	public BusinessPermitTileChange(BusinessPermitTile newTile) {
		this.newTile = newTile;
	}

	/**
	 * It generates a string formed by the most significant statistics of the
	 * BusinessPermitTileChange.
	 * 
	 * @return string
	 */
	@Override
	public String toString() {
		return "BusinessPermitTileChange [Tile= [Cities=" + newTile.getCitiesId() + ", Bonuses="
				+ newTile.getBonusTile() + "]";
	}
}
