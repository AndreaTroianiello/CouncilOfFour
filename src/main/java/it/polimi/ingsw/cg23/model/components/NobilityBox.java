package it.polimi.ingsw.cg23.model.components;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.cg23.model.bonus.Bonus;

public class NobilityBox implements Serializable{
	
	private static final long serialVersionUID = -2228749391593113128L;
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

	/**
	 * It generates a string formed by the most significant statistics of the NobilityBox.
	 * @return string
	 */
	@Override
	public String toString() {
		return "NobilityBox [bonus=" + bonus + "]";
	}

}
