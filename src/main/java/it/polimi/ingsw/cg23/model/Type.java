package it.polimi.ingsw.cg23.model;

import java.util.ArrayList;
import java.util.List;
import it.polimi.ingsw.cg23.model.bonus.Bonus;
import it.polimi.ingsw.cg23.model.bonus.BonusKing;

public class Type {


	private final String name;													//The name of the region.
	private final Bonus bonus;													//The bonus of the type.
	private final BonusKing bonusKing;											//The bonus king
	private final List<City> cities;											//The list of cities contained.
	private boolean bonusAvailable;												//Status of the bonus.
	
	public Type(String name, Bonus bonus, BonusKing bonusKing) {
		this.name = name;
		this.bonus = bonus;
		this.bonusKing=bonusKing;
		this.cities = new ArrayList<>();
		this.bonusAvailable = true;
	}

	/**
	 * Returns the name of the type.
	 * 
	 * @return the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the status of the bonus type. If true, the bonus is already available.
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
	 * Returns all cities of the type.
	 * 
	 * @return the list of cities.
	 */
	public List<City> getCities() {
		return cities;
	}
	
	/**
	 * Adds a city in the type.
	 * 
	 * @param city the city to add.
	 */
	public void addCity(City city){
		cities.add(city);
	}

	/**
	 * Seeks a specific city by the id.
	 * 
	 * @param id the identifier of the city.
	 */
	public City searchCityById(char id){
		for(City city: cities)								//Explore the city.
			if(city.getId()==id)							//Control the id of the city.
				return city;								//If true return the city.
		return null;
	}	

	/**
	 * Returns the status of the type for the specific player.
	 * 
	 * @param player
	 * @return the status of the type. If true the type is completed by player.
	 */
	public boolean completedType(Player player){
		boolean allCities=true;
		for(City city: cities)											//Explore the region's cities.
			allCities=allCities && city.containsEmporium(player);		//Control if the city contains a player's emporium.
		return allCities;
	}
	
	/**
	 *  Runs the bonus associated to the type and sets the status of the bonus at false.
	 *  
	 *  @param player the player that takes the bonus of the region. 
	 */
	public void runBonusRegion(Player player){
		if(bonusAvailable && completedType(player)){					//Excute the bonus if it's available and the player has emporiums in all cities.
			bonus.giveBonus(player);									//Run the type's bonus.
			setBonusUnavailable();										//Set unavailable the bonus.
			bonusKing.runBonusKing(player);								//Run the bonus king.
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Type [name=" + name + ", bonus=" + bonus + ", cities=" + cities + ", bonusAvailable=" + bonusAvailable
				+ "]";
	}
}
