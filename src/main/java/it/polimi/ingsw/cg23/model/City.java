package it.polimi.ingsw.cg23.model;

import java.util.ArrayList;
import java.util.List;
import it.polimi.ingsw.cg23.model.bonus.Bonus;
import it.polimi.ingsw.cg23.model.components.Emporium;
import it.polimi.ingsw.cg23.model.exception.NegativeNumberException;

public class City {

	private final char id;
	private final String name;
	private final List<Bonus> token;
	private final String type;
	private final Region region;
	private final List<City> neighbors;
	private final List<Emporium> emporiums;
	
	public City(char id, String name, List<Bonus> token, String type, Region region){
		this.id=id;
		this.name=name;		
		this.token=token;
		this.type=type;
		this.region=region;
		this.neighbors=new ArrayList<>();
		this.emporiums=new ArrayList<>();
	}
	
	/**
	 * Returns the identifier of the city.
	 * 
	 * @return the number of id of city.
	 */
	public char getId() {
		return id;
	}

	
	/**
	 * Returns the name of the city.
	 * 
	 * @return the name.
	 */
	public String getName() {
		return name;
	}

	
	/**
	 * Returns the type of the city. It can be gold,silver,bronze or iron. 
	 * 
	 * @return the type of city.
	 */
	public String getType() {
		return type;
	}
	
	
	/**
	 * Returns the region that contains the city.
	 * 
	 * @return the region of city.
	 */
	public Region getRegion() {
		return region;
	}
	
	
	/**
	 * Adds a neighbors of the city.
	 * @param neighbor a city near at this city.
	 */
	public void addNeighbor(City neighbor){
		neighbors.add(neighbor);
	}
	
	
	/**
	 * Returns all neighbors of the city
	 * 
	 * @return a list of city.
	 */
	public List<City> getNeighbors() {
		return neighbors;
	}
	
	/**
	 * Returns all emporiums built in the city.
	 * 
	 * @return a list of emporiums.
	 */
	public List<Emporium> getEmporiums() {
		return emporiums;
	}

	/**
	 * Builds an emporium in the city. When a player builds a emporium loses assistants. The assistants of the player can't be negative.
	 * 
	 * @param emporium an emporium of the player.
	 * @throws NegativeNumberException the assistants of the player must be positive.
	 */
	public void buildEmporium(Emporium emporium) throws NegativeNumberException{
		int assistantsPlayer=emporium.getPlayer().getAssistants();
		assistantsPlayer=assistantsPlayer-emporiums.size();
		emporium.getPlayer().setAssistants(assistantsPlayer);						//If sets a negative number throws a NegativeNumberException. 
		runBonusCityAndNeighbors(emporium.getPlayer(), new ArrayList<String>());	//Runs the bonus of the city and visits the neighbors.
		emporium.setCity(this);														//Sets this city in the available emporium.
		this.emporiums.add(emporium);												//Adds the emporiums.
	}
	
	/**
	 *  Runs the bonus associated to the city (only these).
	 *  The neighbors are not visited.
	 *  
	 *  @param player the player who is to receive the bonus.
	 */

	public void runBonusCity(Player player){
		for(int index=0;index<token.size();index++)
			token.get(index).giveBonus(player);
	}
	
	/**
	 *  Runs the token associated to the city and its neighbors.
	 *  
	 *  @param player the player who is to receive the bonus.
	 *  @param citiesVisited the list of the cities already visited.
	 */
	public void runBonusCityAndNeighbors(Player player, List<String> citiesVisited){
		
		boolean containsEmporium= false;
		
		//control if the city contains a player's emporium
		for(int index=0;index<emporiums.size();++index){
			Emporium emporium=emporiums.get(index);										//Extract the emporium at the index
			Player emporiumPlayer=emporium.getPlayer();									//Get the player
			containsEmporium=emporiumPlayer.equals(player);								//Control the player
		}
		
		//execute if the city is just not visited and doesn't contain a player's emporium
		if(!citiesVisited.contains(name) && !containsEmporium){
			runBonusCity(player);														//Run the bonus
			for(int index=0;index<neighbors.size();++index){							//Visit the neighbors
				(neighbors.get(index)).runBonusCityAndNeighbors(player, citiesVisited);	//Visit the neighbor and run the bonus
				citiesVisited.add(name);												//Add the name of city at citiesVisited
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", token=" + token + ", type=" + type + ", neighbors=" + neighbors + ", emporiums=" + emporiums + "]";
	}
}
