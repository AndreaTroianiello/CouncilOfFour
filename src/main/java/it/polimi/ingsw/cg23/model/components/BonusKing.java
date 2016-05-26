package it.polimi.ingsw.cg23.model.components;

import java.io.Serializable;
import java.util.List;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.bonus.Bonus;
import it.polimi.ingsw.cg23.model.bonus.BonusVictoryPoints;

/**
 * The bonus king is common to Region and Type. This is invoked when a bonus is give.
 * 
 * @author Andrea
 */
public class BonusKing implements Serializable{
	
	private static final long serialVersionUID = -6662021675560087243L;
	private int index;								//The current bonus king.
	private List<Integer> bonusValues;				//Set of all bonus king.
	
	/**
	 * The constructor of the bonus king.
	 * 
	 * @param bonusValues A list of integer, these are the values of victory points that bonus king gives.
	 */
	public BonusKing(List<Integer> bonusValues){
		this.index=0;
		this.bonusValues=bonusValues;
	}
	
	/**
	 * Returns the value of current bonus king.
	 * 
	 * @return the bonusKing
	 */
	public int getCurrentBonusKing() {
		return bonusValues.get(index);
	}
	
	/**
	 * Sets the values of bonus king.
	 * 
	 * @param bonusValues the all values of the bonus king.
	 */
	public void setBonusValues(List<Integer> bonusValues) {
		this.bonusValues=bonusValues;
	}
	
	/**
	 * Returns the index of current bonus king.
	 * 
	 * @return the index
	 */
	public int getCurrentIndexBonusKing(){
		return index;
	}
	
	/**
	 * Increases of one position the index of current bonus. 
	 */
	public void increasePosition(){
		if(index<bonusValues.size()-1)					//The index can be at most the size of the set minus 1.
			this.index++;
	}
	
	/**
	 * Runs the bonus with the current value and increases the index.
	 * 
	 * @param player
	 */
	public void runBonusKing(Player player){
		Bonus bonusKing=new BonusVictoryPoints(bonusValues.get(index));
		bonusKing.giveBonus(player);
		increasePosition();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BonusKing [index=" + index + ", bonus=" + bonusValues + "]";
	}
}
