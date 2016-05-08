package it.polimi.ingsw.cg23.model.components;

import java.util.List;
import it.polimi.ingsw.cg23.model.City;

public class King {
	private City city;
	
	public King(City city){
		this.city=city;
	}

	/**
	 * Returns the king's city.
	 * 
	 * @return the city.
	 */
	public City getCity() {
		return city;
	}

	/**
	 * Sets the new king's city.
	 * 
	 * @param city the city to set.
	 */
	public void setCity(City city) {
		this.city = city;
	}
	
	public void moveKing(List<String> cities){
		
	}
}
