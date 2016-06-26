package it.polimi.ingsw.cg23.server.model.components;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The nobility track is a track with some bonuses in it that the players can receive
 * 
 * @author Vincenzo
 *
 */
public class NobilityTrack implements Serializable {
	
	private static final long serialVersionUID = 4192304981537277832L;
	private final List<NobilityBox> nobilityBoxes;
	
	/**
	 * the constructor creates the list of nobility boxes
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
