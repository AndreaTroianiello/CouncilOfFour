package it.polimi.ingsw.cg23.server.model.components;

import java.io.Serializable;

import it.polimi.ingsw.cg23.server.model.exception.NegativeNumberException;

/**
 * The Richness contains the coins of the player.
 * @author Andrea
 *
 */
public class Richness implements Serializable{
	
	private static final long serialVersionUID = -3088246797738991332L;
	private int coins;

	/**
	 * The constructor of richness. The default value of coins is 0. 
	 */
	public Richness(int coins) {
		this.coins = coins;
	}

	/**
	 * Returns the assistants of the pool.
	 * 
	 * @return the coins
	 */
	public int getCoins() {
		return coins;
	}

	/**
	 * Sets the coins contained in the richness.
	 * 
	 * @param coins the number of coins to set
	 * @throws NegativeNumberException the coins of the player must be positive.
	 */
	public void setCoins(int coins) throws NegativeNumberException {
		if(coins>=0)
			this.coins=coins;
		else
			throw new NegativeNumberException("The number of coins can't be negative.");
	}
	
}

