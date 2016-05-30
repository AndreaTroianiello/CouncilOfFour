package it.polimi.ingsw.cg23.model.marketplace;

import java.io.Serializable;

import it.polimi.ingsw.cg23.model.Player;

/**
 * The item to sell in the market. This item can be a number of assistants, a business permit tile or a politic card.
 * 
 * @author Andrea
 *
 */
public class Item implements Serializable {
	
	private static final long serialVersionUID = -4640335373820355916L;
	private int coins;
	private CanBeSold itemToSell;
	private Player player;

	/**
	 * The constructor of the item to sell.
	 * 
	 * @param itemToSell the real object to sell.
	 * @param player the owner of the item.
	 * @param coins the price of the item.
	 */
	public Item(CanBeSold itemToSell,Player player,int coins) {
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
	public CanBeSold getItem() {
		return itemToSell;
	}

	/**
	 * Returns the owner of the item.
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Item [coins=" + coins + ", itemToSell=" + itemToSell + ", player=" + player.getUser() + "]";
	}
	
	

}
