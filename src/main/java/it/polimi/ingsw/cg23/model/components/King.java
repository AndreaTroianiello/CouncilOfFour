package it.polimi.ingsw.cg23.model.components;

import java.io.Serializable;

import it.polimi.ingsw.cg23.model.City;

/**
 * The pawn king is used to build a emporium in the king's city.
 * @author Andrea
 *
 */
public class King implements Serializable{
	
	private static final long serialVersionUID = -2378038028693192360L;
	private City city;
	private final Council council;
	
	/**
	 * The constructor of the pawn King.
	 * @param city The initial city of the king.
	 */
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

	/**
	 * It generates a string formed by the most significant statistics of the King.
	 * @return string
	 */
	@Override
	public String toString() {
		return "King [city=" + city.getId() + ", council=" + council.getCouncillors().size() + "]";
	}
}
