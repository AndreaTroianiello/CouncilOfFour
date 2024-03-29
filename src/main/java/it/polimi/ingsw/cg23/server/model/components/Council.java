package it.polimi.ingsw.cg23.server.model.components;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The council contains a number of councilors. This can be of a Region or King.
 * 
 * @author Andrea
 *
 */
public class Council implements Serializable {

	private static final long serialVersionUID = -6561964067553960969L;
	private final List<Councillor> councillors;

	/**
	 * The constructor of council. Initializes a list of councillors.
	 */
	public Council() {
		this.councillors = new ArrayList<>();
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
	 * It generates a string formed by the most significant statistics of the
	 * Council.
	 * 
	 * @return string
	 */
	@Override
	public String toString() {
		return "Council [councillors=" + councillors + "]";
	}

}
