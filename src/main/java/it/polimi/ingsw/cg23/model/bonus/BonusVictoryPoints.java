package it.polimi.ingsw.cg23.model.bonus;

import it.polimi.ingsw.cg23.model.Player;

public class BonusVictoryPoints implements Bonus {
	
	private final int points;

	public BonusVictoryPoints(int points) {
		this.points = points;
	}


	/**
	 * @return the points
	 */
	public int getPoints() {
		return points;
	}


	//give to the player the amount of victory points of the bonus
	@Override
	public void giveBonus(Player player) {
		int playerPoints = player.getVictoryPoints();
		playerPoints = playerPoints + this.points;
		player.setVictoryPoints(playerPoints);
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BonusVictoryPoints [points=" + points + "]";
	}
	
	

}
