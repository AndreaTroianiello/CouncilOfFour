package it.polimi.ingsw.cg23.model;

import java.util.ArrayList;
import java.util.List;
import it.polimi.ingsw.cg23.model.bonus.Bonus;
import it.polimi.ingsw.cg23.model.components.Council;
import it.polimi.ingsw.cg23.model.components.RegionDeck;

public class Region {


	private final String name;													//the name of the region.
	private final Bonus bonus;													//the bonus of the region.
	private final List<City> cities;											//the list of cities contained.
	private boolean bonusAvailable;												//status of the bonus.
	private final Council council;												//the council of the region.
	private final RegionDeck deck;												//the deck of business permit tiles.
	
	public Region(String name, Bonus bonus, RegionDeck deck) {
		this.name = name;
		this.bonus = bonus;
		this.cities = new ArrayList<>();
		this.bonusAvailable = true;
		this.council=new Council();
		this.deck=deck;
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
	 * Returns the region's council.
	 * 
	 * @return the council 
	 */
	public Council getCouncil() {
		return council;
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
	 * @param city the city to add.
	 */
	public void addCity(City city){
		cities.add(city);
	}
		
	/**
	 * Returns the deck of the tiles.
	 * 
	 * @return the deck the deck of business permit tiles.
	 */
	public RegionDeck getDeck() {
		return deck;
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
	 * Returns to the city of a specific type.
	 * 
	 * @param type the specific type.
	 */
	public List<City> searchCityByType(String type){
		List<City> citiesType= new ArrayList<>();				//Create the list of cities.
		for(City city: cities)									//Explore the city.
			if(type.equals(city.getType()))						//Control if the city has the same type.
				citiesType.add(city);							//If true add the city at the list.
		return citiesType;
	}
	

	/**
	 * Returns the status of the region for the specific player.
	 * 
	 * @param player
	 * @return the status of the region. If true the region is completed by player.
	 */
	public boolean completedRegion(Player player){
		boolean allCities=true;
		for(City city: cities)											//Explore the region's cities.
			allCities=allCities && city.containsEmporium(player);		//Control if the city contains a player's emporium.
		return allCities;
	}
	
	/**
	 *  Runs the bonus associated to the region and sets the status of the bonus at false.
	 *  
	 *  @param player the player that takes the bonus of the region. 
	 */
	public void runBonusRegion(Player player){
		if(bonusAvailable && completedRegion(player)){					//Excute the bonus if it's available and the player has emporiums in all cities.
			bonus.giveBonus(player);									//Run the bonus.
			setBonusUnavailable();										//Set unavailable the bonus.
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
