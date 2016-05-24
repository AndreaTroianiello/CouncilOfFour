package it.polimi.ingsw.cg23.model.components;

import it.polimi.ingsw.cg23.model.City;
import it.polimi.ingsw.cg23.model.Player;

public class Emporium {
	private final Player player;
	private City city;
	
	public Emporium(Player player) {
		this.player = player;
		this.city = null;
	}

	/**
	 * Returns the city in which it was built the emporium. 
	 * 
	 * @return the city
	 */
	public City getCity() {
		return city;
	}

	/**
	 * Sets the city of emporium. 
	 * 
	 * @param city the city in which it was built the emporium.
	 */
	public void setCity(City city) {
		this.city = city;
	}

	/**
	 * Returns the player owner of the emporium.
	 * 
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
		return player.getUser();
	}
	
}
