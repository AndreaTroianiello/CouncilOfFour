/**
 * 
 */
package it.polimi.ingsw.cg23.model.bonus;

import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.components.NobilityBox;

/**
 * @author utente
 *
 */
public class BonusNobility implements Bonus, Cloneable {
	
	private int steps;
	private final Board board;
	private final String name="BonusNobility";

	public BonusNobility(int steps, Board board) {
		this.steps = steps;
		this.board = board;
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
	public void setNumber(int number) {
		this.steps = number;
	}

	/*
	 * @see it.polimi.ingsw.cg23.model.bonus.Bonus#giveBonus(it.polimi.ingsw.cg23.model.Player)
	 */ 
	/** make the player advance tot steps in the nobility track 
	 */
	@Override
	public void giveBonus(Player player) {
		NobilityBox nobilityBox = this.board.getNobilityTrack().getNobilityBoxes().get(player.getNobilityBoxPosition()+steps);
		player.setNobilityBoxPoistion(player.getNobilityBoxPosition()+steps);
		for(Bonus b: nobilityBox.getBonus()){
			b.giveBonus(player);
		}
	}
	
	
	public void setParameters(){
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BonusNobility [steps=" + steps + "]";
	}

	public String toStringName(){
		return "BonusNobility";
	}
	
	public Bonus clone() {
		try {
			return (BonusNobility) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}	

}
