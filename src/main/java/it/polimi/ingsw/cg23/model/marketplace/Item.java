package it.polimi.ingsw.cg23.model.marketplace;

import it.polimi.ingsw.cg23.model.Player;

/**
 * The item to sell in the market. This item can be a number of assistants, a business permit tile or a politic card.
 * 
 * @author Andrea
 *
 */
public class Item {
	
	private int coins;
	private Object item;
	private Player player;

	/**
	 * The constructor of the item to sell.
	 * 
	 * @param item the real object to sell.
	 * @param player the owner of the item.
	 * @param coins the price of the item.
	 */
	public Item(Object item,Player player,int coins) {
		this.item=item;
		this.player=player;
		this.coins=coins;
	}

	/**
	 * The price of the item.
	 * 
	 * @return the price's value.
	 */
	public int getCoins() {
		return coins;
	}

	/**
	 * Returns the item to sell. This item can be a number of assistants, a business permit tile or a politic card.
	 * 
	 * @return the item 
	 */
	public Object getItem() {
		return item;
	}

	/**
	 * Returns the owner of the item.
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

}
