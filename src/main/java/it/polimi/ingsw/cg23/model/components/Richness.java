package it.polimi.ingsw.cg23.model.components;

public class Richness {
	private int coins;

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
	 */
	public void setCoins(int coins) {
		this.coins = coins;
	}
	
}

