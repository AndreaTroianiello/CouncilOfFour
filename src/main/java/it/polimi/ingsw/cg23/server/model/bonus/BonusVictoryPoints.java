package it.polimi.ingsw.cg23.server.model.bonus;


import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Player;

/**
 * the class of the bonus that allows to give the player some victory points.
 * 
 * @author utente
 *
 */
public class BonusVictoryPoints implements Bonus {
	
	private static final long serialVersionUID = 5512599104155333825L;
	private int points;					//the amount of points given by the bonus
	private final String name;
	
	/**
	 * the constructor set the name as the name of the bonus and the points as the paramater given to the 
	 * method
	 * 
	 * @param points how many points the player receives
	 */
	public BonusVictoryPoints(int points) {
		this.points = points;
		this.name="VictoryPoints";
	}
	
	/**
	 * @return the bonus name and the number(if exist)
	 */
	@Override
	public String getName(){
		return points+name;
	}


	
	/**
	 * set the number of points given by the bonus
	 * 
	 * @param number the number of points
	 */
	@Override
	public void setNumber(int number){
		this.points = number;
	}


	/**
	 * give to the player the amount of victory points of the bonus
	 * 
	 * @param player whom the bonus is given to
	 */
	@Override
	public void giveBonus(Player player) {
		int playerPoints = player.getVictoryTrack().getVictoryPoints();
		playerPoints = playerPoints + this.points;
		player.getVictoryTrack().setVictoryPoints(playerPoints);
	}
	
	/**
	 * @return the points
	 */
	@Override
	public int getNumber(){
		return points;
	}

	@Override
	public void setBoard(Board board) {
		// Not implemented.	
	}
	
	/**
	 * @return the name of the class as string
	 */
	@Override
	public String toString() {
		return "BonusVictoryPoints[points=" + points + "]";
	}
	
	/**
	 * @return a new BonusVictoryPoints
	 */
	@Override
	public Bonus copy() {
		return new BonusVictoryPoints(0);
	}	

}
