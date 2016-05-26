package it.polimi.ingsw.cg23.controller.change;

import it.polimi.ingsw.cg23.model.City;

/**
 * The CityChange serves to notify a new city.
 * @author Andrea
 *
 */
public class CityChange implements Change {

	private final City newCity;
	
	/**
	 * The constructor of the CityChange
	 * @param newBoard The new board.
	 */
	public CityChange(City newCity){
		this.newCity=newCity;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StateChange [newState=" + newCity + "]";
	}

}


