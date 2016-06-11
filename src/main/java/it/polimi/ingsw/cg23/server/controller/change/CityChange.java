package it.polimi.ingsw.cg23.server.controller.change;

import it.polimi.ingsw.cg23.server.model.City;

/**
 * The CityChange serves to notify a new city.
 * @author Andrea
 *
 */
public class CityChange implements Change {

	private static final long serialVersionUID = 675216218423747479L;
	private final City newCity;
	
	/**
	 * The constructor of the CityChange
	 * @param newBoard The new board.
	 */
	public CityChange(City newCity){
		this.newCity=newCity;
	}

	/**
	 * It generates a string formed by the most significant statistics of the CityChange.
	 * @return string
	 */
	@Override
	public String toString() {
		return "CityChange [newCity=" + newCity + "]";
	}

}


