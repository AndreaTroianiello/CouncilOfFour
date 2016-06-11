package it.polimi.ingsw.cg23.server.controller.change;

import java.util.List;

import it.polimi.ingsw.cg23.server.model.components.Emporium;

/**
 * The EmporiumsChange serves to notify a new list of emporiums.
 * @author Andrea
 *
 */
public class EmporiumsChange implements Change {
	
	private static final long serialVersionUID = 4069412638795971731L;
	private final List<Emporium> emporiums;
	
	/**
	 * The constructor of the EmporiumsChange
	 * @param emporiums The new emporiums.
	 */
	public EmporiumsChange(List<Emporium> emporiums){
		this.emporiums=emporiums;
	}

	/**
	 * It generates a string formed by the most significant statistics of the EmporiumsChange.
	 * @return string
	 */
	@Override
	public String toString() {
		return "EmporiumsChange [ Emporiums= [City=" + emporiums.get(0).getCity().getId()
				+ ", Players=" + emporiums + "]";
	}

}

