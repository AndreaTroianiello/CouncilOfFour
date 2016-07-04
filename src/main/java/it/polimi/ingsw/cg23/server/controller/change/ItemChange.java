package it.polimi.ingsw.cg23.server.controller.change;

import it.polimi.ingsw.cg23.server.model.marketplace.Item;

/**
 * The ItemChange serves to notify a new item.
 * 
 * @author Andrea
 *
 */
public class ItemChange implements Change {

	private static final long serialVersionUID = 9184856341208993100L;
	private final Item newItem;

	/**
	 * The constructor of the ItemChange
	 * 
	 * @param newItem
	 *            The new item.
	 */
	public ItemChange(Item newItem) {
		this.newItem = newItem;
	}

	/**
	 * It generates a string formed by the most significant statistics of the
	 * ItemChange.
	 * 
	 * @return string
	 */
	@Override
	public String toString() {
		return "ItemChange [Item=" + newItem + "]";
	}

}
