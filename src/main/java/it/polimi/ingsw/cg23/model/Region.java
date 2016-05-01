package it.polimi.ingsw.cg23.model;

import java.util.ArrayList;
import java.util.List;
import it.polimi.ingsw.cg23.model.bonus.Bonus;

public class Region {

	private final String name;
	private final Bonus bonus;
	private final List<City> cities;
	private boolean bonusAvailable;
	
	public Region(String name, Bonus bonus) {
		this.name = name;
		this.bonus = bonus;
		this.cities = new ArrayList<>();
		this.bonusAvailable = true;
	}

	/**
	 * Returns the name of the region.
	 * 
	 * @return the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the status of the bonus region. If true, the bonus is already available.
	 * 
	 * @return the bonusAvailable.
	 */
	public boolean isBonusAvailable() {
		return bonusAvailable;
	}

	/**
	 *  Sets the bonus status at false. The bonus is not available.
	 */
	public void setBonusUnavailable() {
		this.bonusAvailable = false;
	}

	/**
	 * Returns all cities of the region.
	 * 
	 * @return the list of cities.
	 */
	public List<City> getCities() {
		return cities;
	}
	
	/**
	 * Adds a city in the region.
	 * 
	 * @param city
	 */
	public void addCity(City city){
		cities.add(city);
	}
	
	/**
	 *  Run the bonus associated to the region and sets the status of the bonus at false.
	 *  
	 *  @param player the player that takes the bonus of the region. 
	 */
	public void runBonusRegion(Player player){
		if(bonusAvailable){
			bonus.esegui(player);
			setBonusUnavailable();
		}
	}

}
