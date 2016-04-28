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
	 * @return the city
	 */
	public City getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(City city) {
		this.city = city;
	}

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}
	
	
	
	
}
