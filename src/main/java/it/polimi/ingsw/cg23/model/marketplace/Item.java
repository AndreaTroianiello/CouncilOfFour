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
	private Object itemToSell;
	private Player player;

	/**
	 * The constructor of the item to sell.
	 * 
	 * @param itemToSell the real object to sell.
	 * @param player the owner of the item.
	 * @param coins the price of the item.
	 */
	public Item(Object itemToSell,Player player,int coins) {
		this.itemToSell=itemToSell;
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
		return itemToSell;
	}

	/**
	 * Returns the owner of the item.
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

}