package it.polimi.ingsw.cg23.model.components;

import java.util.ArrayList;
import java.util.List;

public class Council {
	private final List<Councillor> councillors;
	
	public Council(){
		this.councillors= new ArrayList<>();
	}

	/**
	 * Returns the list of councillors.
	 * 
	 * @return the councillor
	 */
	public List<Councillor> getCouncillors() {
		return councillors;
	}
	
	/**
	 * Adds a councillor at first position of the list and removes the last councillor.
	 * 
	 * @param councillor
	 */
	public Councillor addCouncillor(Councillor councillor) {
		councillors.add(0,councillor);
		return councillors.remove(councillors.size()-1);
	}

}
