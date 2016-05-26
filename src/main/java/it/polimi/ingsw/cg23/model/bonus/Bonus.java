package it.polimi.ingsw.cg23.model.bonus;


import java.io.Serializable;

import it.polimi.ingsw.cg23.model.Player;

/**
 * the bonus interface
 *
 */
public interface Bonus extends Serializable{
	
	/**
	 * it allows to give the bonus to the player
	 * @param player
	 */
	public void giveBonus(Player player);
	
	/**
	 *  it returns the name and the variables of the class in string
	 */
	public String getName();
	public void setParameters();
	public void setNumber(int number);
	public Bonus clone();

}
