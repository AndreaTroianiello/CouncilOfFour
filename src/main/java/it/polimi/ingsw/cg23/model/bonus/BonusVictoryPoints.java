package it.polimi.ingsw.cg23.model.bonus;

import it.polimi.ingsw.cg23.model.Player;

public class BonusVictoryPoints implements Bonus, Cloneable {
	
	private int points;					//the amount of points given by the bonus
	private final String name="VictoryPoints";
	
	public BonusVictoryPoints(int points) {
		this.points = points;
	}
	
	/**
	 * return the bonus name and the number(if exist)
	 */
	@Override
	public String getName(){
		return points+name;
	}

	/**
	 * @return the points
	 */
	public int getPoints() {
		return points;
	}
	
	public void setNumber(int number){
		this.points = number;
	}


	/**give to the player the amount of victory points of the bonus
	 * 
	 * @param player
	 */
	@Override
	public void giveBonus(Player player) {
		int playerPoints = player.getVictoryPoints();
		playerPoints = playerPoints + this.points;
		player.setVictoryPoints(playerPoints);
	}
	
	
	public void setParameters(){
		
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BonusVictoryPoints [points=" + points + "]";
	}
	
	

}
