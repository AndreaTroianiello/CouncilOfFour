package it.polimi.ingsw.cg23.model.components;

import java.util.ArrayList;
import java.util.List;
import it.polimi.ingsw.cg23.model.bonus.Bonus;

public class BusinessPermitTitle {
	
	private final List<Character> citiesId;
	private final List<Bonus> bonusTitle;

	public BusinessPermitTitle(List<Character> citiesId){
		this.citiesId=citiesId;
		this.bonusTitle=new ArrayList<>();
	}

	/**
	 * Returns the all id of cities, where you can build.
	 * 
	 * @return the citiesId
	 */
	public List<Character> getCitiesId() {
		return citiesId;
	}

	/**
	 * Returns all bonus of the title.
	 * 
	 * @return the bonusTitle
	 */
	public List<Bonus> getBonusTitle() {
		return bonusTitle;
	}
	
	/**
	 * Adds a bonus in the title.
	 * 
	 * @param bonus
	 */
	public void addBonus(Bonus bonus){
		this.bonusTitle.add(bonus);
	}
}
