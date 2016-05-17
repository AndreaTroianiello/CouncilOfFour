package it.polimi.ingsw.cg23.model.bonus;

import java.util.List;
import it.polimi.ingsw.cg23.model.Player;

public class BonusKing implements Bonus{
	private int index;								//The current bonus king.
	private List<Integer> bonus;					//Set of all bonus king.


	private String name="King";
	
	public BonusKing(List<Integer> bonus){
		this.index=0;
		this.bonus=bonus;
	}
	
	/**
	 * Returns the value of current bonus king.
	 * 
	 * @return the bonusKing
	 */
	public int getCurrentBonusKing() {
		return bonus.get(index);
	}
	
	/**
	 * return the bonus name and the number(if exist)
	 */
	public String getName(){
		return name;
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
		if(index<bonus.size()-1)					//The index can be at most the size of the set minus 1.
			this.index++;
	}
	
	/**
	 * Runs the bonus with the current value and increases the index.
	 * 
	 * @param player
	 */
	public void runBonusKing(Player player){
		Bonus bonusKing=new BonusVictoryPoints(bonus.get(index));
		bonusKing.giveBonus(player);
		increasePosition();
	}

	@Override
	public void giveBonus(Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setParameters() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNumber(int number) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Bonus clone() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BonusKing [index=" + index + ", bonus=" + bonus + "]";
	}
}
