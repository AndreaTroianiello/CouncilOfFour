package it.polimi.ingsw.cg23.model.components;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.cg23.model.bonus.Bonus;

public class NobilityBox {
	private final List<Bonus> bonus;
	

	public NobilityBox() {
		this.bonus = new ArrayList<>();
	}


	/**
	 * @return the bonus
	 */
	public List<Bonus> getBonus() {
		return bonus;
	}
	
	/**
	 * add a bonus in the noibilityBox
	 * 
	 * @param bonus
	 */
	public void addBonus(Bonus bonus){
		this.bonus.add(bonus);	
	}



}
