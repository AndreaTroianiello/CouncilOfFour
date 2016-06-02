package it.polimi.ingsw.cg23.model.components;

import java.io.Serializable;

import it.polimi.ingsw.cg23.model.City;
import it.polimi.ingsw.cg23.model.Player;

/**
 * The player's emporium to be built in a city.
 * @author Andrea
 *
 */
public class Emporium implements Serializable {
	
	private static final long serialVersionUID = -2660058974560248850L;
	private final Player player;
	private City city;
	
	/**
	 * The constructor of the emporium. The default value of city is null.
	 * @param player The owner of this emporium.
	 */
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

	/**
	 * It generates a string formed by the most significant statistics of the Emporium.
	 * @return string
	 */
	@Override
	public String toString() {
		return player.getUser();
	}
	
}
