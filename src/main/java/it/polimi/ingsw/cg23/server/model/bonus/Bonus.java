package it.polimi.ingsw.cg23.server.model.bonus;


import java.io.Serializable;

import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Player;

/**
 * the bonus interface
 */
public interface Bonus extends Serializable{
	
	/**
	 * it allows to give the bonus to the player
	 * @param player
	 */
	public void giveBonus(Player player);
	
	/**
	 * it returns the name and the variables of the class in string
	 */
	public String getName();
	
	/**
	 * it return the parameter number
	 */
	public int getNumber();
	
	/**
	 * it set number to the value of the parameter
	 * @param number
	 */
	public void setNumber(int number);
	
	/**
	 * it set the board to the board passed parameter
	 * @param board
	 */
	public void setBoard(Board board);
	
	/**
	 * makes a copy of the bonus
	 */
	public Bonus copy();

}
