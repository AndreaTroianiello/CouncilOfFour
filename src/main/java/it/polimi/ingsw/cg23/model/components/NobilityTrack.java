package it.polimi.ingsw.cg23.model.components;

import java.util.ArrayList;
import java.util.List;

public class NobilityTrack {
	
	private final List<NobilityBox> nobilityBoxes;
	
	/**
	 * the constructor creates a nobility track 
	 * 
	 * @param lenght
	 */
	public NobilityTrack() {
		this.nobilityBoxes = new ArrayList<>();
	}

	/**
	 * @return the nobilityBoxes
	 */
	public List<NobilityBox> getNobilityBoxes() {
		return nobilityBoxes;
	}

		

}
