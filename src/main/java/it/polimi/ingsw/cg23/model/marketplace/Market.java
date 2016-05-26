package it.polimi.ingsw.cg23.model.marketplace;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The market's class. The market allows a player to sell to other players
 * @author Andrea
 *
 */
public class Market implements Serializable{

	private static final long serialVersionUID = 1070392830721899258L;
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
	 * @param newItem  a new item to sell.
	 */
	public void addItemToSell(Item newItem){
		this.itemsToSell.add(newItem);
	}
	
	/**
	 * Removes all items from the list.
	 */
	public void resetItems(){
		this.itemsToSell.clear();
	}

}
