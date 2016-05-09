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
	public NobilityTrack(int lenght) {
		this.nobilityBoxes = new ArrayList<>();
		for(int i=0; i<lenght; i++){
			nobilityBoxes.add(new NobilityBox());
		}
	}

	/**
	 * @return the nobilityBoxes
	 */
	public List<NobilityBox> getNobilityBoxes() {
		return nobilityBoxes;
	}

		

}
