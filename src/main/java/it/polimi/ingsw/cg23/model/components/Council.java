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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Council [councillors=" + councillors + "]";
	}
	
}
