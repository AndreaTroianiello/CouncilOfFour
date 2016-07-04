package it.polimi.ingsw.cg23.server.controller.change;

import it.polimi.ingsw.cg23.server.model.components.Council;

/**
 * The CouncilChange serves to notify a new council.
 * 
 * @author Andrea
 *
 */
public class CouncilChange implements Change {

	private static final long serialVersionUID = 4166988948632759528L;
	private final Council council;

	/**
	 * The constructor of the CouncilChange
	 * 
	 * @param council
	 *            The new council.
	 */
	public CouncilChange(Council council) {
		this.council = council;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CouncilChange [Council= [" + council.getCouncillors() + "]";
	}

}
