package it.polimi.ingsw.cg23.model.marketplace;

import java.util.ArrayList;
import java.util.List;

/**
 * The market's class. The market allows a player to sell to other players
 * @author Andrea
 *
 */
public class Market {
	
	private final List<Item> itemsToSell;

	/**
	 * The constructor of the market. Creates a empty list of items.
	 */
	public Market() {
		this.itemsToSell=new ArrayList<>();	
	}
	
	/**
	 * Returns the list of items to sell.
	 * 
	 * @param item
	 */
	public void addItemToSell(Item item){
		this.itemsToSell.add(item);
	}
	
	/**
	 * Removes all items from the list.
	 */
	public void resetItems(){
		this.itemsToSell.clear();
	}

}
