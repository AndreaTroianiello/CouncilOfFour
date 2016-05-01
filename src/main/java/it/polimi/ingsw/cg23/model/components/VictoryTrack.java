package it.polimi.ingsw.cg23.model.components;

public class VictoryTrack {
	private int victoryPoints;

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
	 * @param victoryPoints the number of victory points to set
	 */
	public void setVictoryPoints(int victoryPoints) {
		this.victoryPoints = victoryPoints;
	}
	
}
