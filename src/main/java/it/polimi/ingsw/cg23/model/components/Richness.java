package it.polimi.ingsw.cg23.model.components;

import it.polimi.ingsw.cg23.model.exception.NegativeNumberException;

/**
 * The Richness contains the coins of the player.
 * @author Andrea
 *
 */
public class Richness {
	private int coins;

	/**
	 * The constructor of richness.
	 * @param coins The initial coins of the player.
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

