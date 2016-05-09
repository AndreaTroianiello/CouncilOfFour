package it.polimi.ingsw.cg23.model;

import java.util.ArrayList;
import java.util.List;
import it.polimi.ingsw.cg23.model.bonus.Bonus;

public class Region {


	private final String name;													//the name of the region.
	private final Bonus bonus;													//the bonus of the region.
	private final List<City> cities;											//the list of cities contained.
	private boolean bonusAvailable;												//status of the bonus.
	
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
	 * Seeks a specific city by the id
	 * 
	 * @param id the identifier of the city.
	 */
	public City searchCity(char id){
		for(int index=0;index<cities.size();++index)
			if(cities.get(index).getId()==id)
				return cities.get(index);
		return null;
	}
	
	/**
	 *  Run the bonus associated to the region and sets the status of the bonus at false.
	 *  
	 *  @param player the player that takes the bonus of the region. 
	 */
	public void runBonusRegion(Player player){
		if(bonusAvailable){
			bonus.giveBonus(player);
			setBonusUnavailable();
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Region [name=" + name + ", bonus=" + bonus + ", cities=" + cities + ", bonusAvailable=" + bonusAvailable
				+ "]";
	}
}
