package it.polimi.ingsw.cg23.model.components;

import it.polimi.ingsw.cg23.model.City;

public class King {
	private City city;
	private final Council council;
	
	public King(City city){
		this.city=city;
		this.council= new Council();
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

	/**
	 * Returns the king's council.
	 * 
	 * @return the council
	 */
	public Council getCouncil() {
		return council;
	}
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "King [city=" + city + "]"+council;
	}
}
