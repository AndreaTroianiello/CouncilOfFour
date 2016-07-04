package it.polimi.ingsw.cg23.server.model.components;

import java.io.Serializable;

/**
 * The victory track contains the victory points of the player.
 * 
 * @author Andrea
 *
 */
public class VictoryTrack implements Serializable {

	private static final long serialVersionUID = 394372805913576038L;
	private int victoryPoints;

	/**
	 * The constructor of VictoryTrack. The victory points are default to zero.
	 */
	public VictoryTrack() {
		this.victoryPoints = 0;
	}

	/**
	 * Returns the victory points.
	 * 
	 * @return the victoryPoints
	 */
	public int getVictoryPoints() {
		return victoryPoints;
	}

	/**
	 * Sets the victory points contained in the victory track.
	 * 
	 * @param victoryPoints
	 *            the number of victory points to set
	 */
	public void setVictoryPoints(int victoryPoints) {
		this.victoryPoints = victoryPoints;
	}

}
