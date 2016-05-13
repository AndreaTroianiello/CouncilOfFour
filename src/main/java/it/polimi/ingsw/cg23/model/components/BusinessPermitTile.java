package it.polimi.ingsw.cg23.model.components;

import java.util.ArrayList;
import java.util.List;
import it.polimi.ingsw.cg23.model.bonus.Bonus;

public class BusinessPermitTile {
	
	private final List<Character> citiesId;
	private final List<Bonus> bonusTile;
	private final String zone;

	public BusinessPermitTile(List<Character> citiesId, String zone){
		this.zone=zone;
		this.citiesId=citiesId;
		this.bonusTile=new ArrayList<>();
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
	 * Returns all bonus of the tile.
	 * 
	 * @return the bonusTitle
	 */
	public List<Bonus> getBonusTile() {
		return bonusTile;
	}
	
	/**
	 * Adds a bonus in the tile.
	 * 
	 * @param bonus
	 */
	public void addBonus(Bonus bonus){
		this.bonusTile.add(bonus);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BusinessPermitTile [citiesId=" + citiesId + ", bonusTile=" + bonusTile + " zone="+zone+"]";
	}
	
	
}
