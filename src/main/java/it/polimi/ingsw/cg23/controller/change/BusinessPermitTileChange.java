package it.polimi.ingsw.cg23.controller.change;

import it.polimi.ingsw.cg23.model.components.BusinessPermitTile;

/**
 * The BusinessPermitTileChange serves to notify a new tile.
 * @author Andrea
 *
 */
public class BusinessPermitTileChange implements Change {

	private static final long serialVersionUID = -70859360088360813L;
	private final BusinessPermitTile newTile;

	/**
	 * The constructor of the BusinessPermitTileChange
	 * @param newTile The new business permit tile.
	 */
	public BusinessPermitTileChange(BusinessPermitTile newTile){
		this.newTile=newTile;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BusinessPermitTileChange [Tile= [Cities=" + newTile.getCitiesId()
				+ ", Bonuses=" + newTile.getBonusTile() + "]";
	}
}
