package it.polimi.ingsw.cg23.server.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.cg23.server.model.bonus.Bonus;
import it.polimi.ingsw.cg23.server.model.bonus.BonusVictoryPoints;
import it.polimi.ingsw.cg23.server.model.components.BonusKing;
import it.polimi.ingsw.cg23.server.model.components.Council;
import it.polimi.ingsw.cg23.server.model.components.RegionDeck;
/**
 * The Region's class. The region is characterized by a name, a bonus and contains a list of city, a council and a deck of tiles.
 * 
 * @author Andrea
 *
 */
public class Region implements Serializable {

	private static final long serialVersionUID = 65091222421530741L;
	private final String name;													//The name of the region.
	private final Bonus bonus;													//The bonus of the region.
	private final List<City> cities;											//The list of cities contained.
	private boolean bonusAvailable;												//Status of the bonus.
	private final Council council;												//The council of the region.
	private final RegionDeck deck;												//The deck of business permit tiles.
	private final BonusKing bonusKing;											//The bonus king
	
	/**
	 * The class' constructor. Initializes  of region.
	 * @param name The region's name.
	 * @param points The victory points which the bonus of region gives.
	 * @param deck Region's deck. This deck contains business permit tiles.
	 * @param bonusKing	The bonus king.
	 */
	public Region(String name, int points, RegionDeck deck, BonusKing bonusKing) {
		this.name = name;
		this.bonus = new BonusVictoryPoints(points);
		this.cities = new ArrayList<>();
		this.bonusAvailable = true;
		this.council=new Council();
		this.deck=deck;
		this.bonusKing=bonusKing;
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
	 * Returns the cities' list of a specific type.
	 * 
	 * @param type the specific type.
	 */
	public List<City> searchCitiesByType(String type){
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
			bonusKing.runBonusKing(player);								//Run bonus king.
		}
	}

	/**
	 * Returns the bonus of region.
	 * @return the bonus
	 */
	public BonusVictoryPoints getBonus() {
		return (BonusVictoryPoints) bonus;
	}
	
	/**
	 * It generates a string formed by the most significant statistics of the Region.
	 * @return string
	 */
	@Override
	public String toString() {
		return "Region [name=" + name + ", bonus=" + bonus.getName() + ", cities=" + cities.size() + ", bonusAvailable=" + bonusAvailable
				+ "]";
	}

}
