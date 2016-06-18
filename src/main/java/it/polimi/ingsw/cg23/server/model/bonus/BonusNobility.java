package it.polimi.ingsw.cg23.server.model.bonus;

import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.components.NobilityBox;

/**
 * the class of the bonus that allows the player to step forward in the nobility track. It contains the
 * number of steps given by the bonus, the board and the name.
 * 
 * @author Vincenzo
 *
 */
public class BonusNobility implements Bonus {
	
	private static final long serialVersionUID = 5191094826225138720L;
	private int steps;
	private final Board board;
	private final String name;

	/**
	 * the constructor set the name as the name of the bonus, and the other variables as the 
	 * parameter given to the method
	 * @param steps (can't be negative)
	 * @param board
	 */
	public BonusNobility(int steps, Board board) {
		this.steps = steps;
		this.board = board;
		this.name="BonusNobility";
	}

	/**
	 * return the bonus name and the number(if exist)
	 */
	@Override
	public String getName(){
		return steps+name;
	}
	
	/**
	 * @return the board
	 */
	public Board getBoard() {
		return board;
	}



	/**
	 * @return the steps
	 */
	public int getSteps() {
		return steps;
	}
	
	

	/**
	 * @param steps the steps to set
	 */
	@Override
	public void setNumber(int number) {
		this.steps = number;
	}

	
	/** 
	 * make the player advance tot steps in the nobility track
	 * 
	 * @param player
	 */
	@Override
	public void giveBonus(Player player) {
		NobilityBox nobilityBox = this.board.getNobilityTrack().getNobilityBoxes().get(player.getNobilityBoxPosition()+steps);
		nobilityBox.removePlayer(player);
		player.setNobilityBoxPoistion(player.getNobilityBoxPosition()+steps);
		this.board.getNobilityTrack().getNobilityBoxes().get(player.getNobilityBoxPosition()).addPlayer(player);
		for(Bonus b: nobilityBox.getBonus()){
			b.giveBonus(player);
		}
	}
	
	@Override
	public int getParameters(){
		return steps;
	}

	/**
	 * @return the name of the class as string
	 */
	@Override
	public String toString() {
		return "BonusNobility [steps=" + steps + "]";
	}

	

	/**
	 * @return a new BonusNobility
	 */
	@Override
	public Bonus copy() {
		return new BonusNobility(0, board); 
	}	

}
