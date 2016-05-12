package it.polimi.ingsw.cg23.model.bonus;

import it.polimi.ingsw.cg23.model.Player;

public class BonusKing {
	private int bonusKing;
	private int maxBonus;
	
	public BonusKing(int length){
		this.bonusKing=1;
		this.maxBonus=length;
	}
	
	
	/**
	 * @return the bonusKing
	 */
	public int getBonusKing() {
		return bonusKing;
	}

	public void increasePosition(){
		this.bonusKing++;
	}
	
	public void runBonusKing(Player player){
		
	}
	
}
