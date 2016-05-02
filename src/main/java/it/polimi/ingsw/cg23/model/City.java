package it.polimi.ingsw.cg23.model;

import java.util.ArrayList;
import java.util.List;
import it.polimi.ingsw.cg23.model.bonus.Bonus;
import it.polimi.ingsw.cg23.model.components.Emporium;
import it.polimi.ingsw.cg23.model.exception.NegativeNumberException;

public class City {
	private final char id;
	private final String name;
	private final Bonus bonus;
	private final String type;
	private final Region region;
	private final List<City> neighbors;
	private final List<Emporium> emporiums;
	
	public City(char id, String name, Bonus bonus, String type, Region region){
		this.id=id;
		this.name=name;		
		this.bonus=bonus;
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
	 * 
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
	 * @param emporium an emporium of the player, the city must be setted.
	 * @throws NegativeNumberException the assistants of the player must be positive.
	 */
	public void buildEmporium(Emporium emporium) throws NegativeNumberException{
		int assistantsPlayer=emporium.getPlayer().getAssistants();
		assistantsPlayer=assistantsPlayer-emporiums.size();
		emporium.getPlayer().setAssistants(assistantsPlayer);						//If sets a negative number throws a NegativeNumberException. 
		runBonusCity(emporium.getPlayer(), new ArrayList<String>(), true);			//Runs the bonus of the city and visits the neighbors.
		this.emporiums.add(emporium);												//Adds the emporiums.
	}
	
	
	/**
	 *  Runs the bonus associated to the city and its neighbors.
	 *  
	 *  @param player the player who is to receive the bonus.
	 *  @param citiesVisited the list of the cities already visited.
	 *  @param visitNeighbors the status indicating if the neighbors must be visited.
	 */
	public void runBonusCity(Player player, List<String> citiesVisited, boolean visitNeighbors){
		
		boolean containsEmporium= false;
		
		//control if the city contains a player's emporium
		for(int index=0;index<emporiums.size();++index){
			Emporium emporium=emporiums.get(index);										//Extract the emporium at the index
			Player emporiumPlayer=emporium.getPlayer();									//Get the player
			containsEmporium=emporiumPlayer.equals(player);								//Control the player
		}
		
		//execute if the city is just not visited and doesn't contain a player's emporium
		if(!citiesVisited.contains(name) && !containsEmporium){
			bonus.giveBonus(player);													//Run the bonus
			if(visitNeighbors){															//If true visits the neighbors
				for(int index=0;index<neighbors.size();++index)							//Visit the neighbors
					(neighbors.get(index)).runBonusCity(player, citiesVisited,true);
				citiesVisited.add(name);												//add the name of city at citiesVisited
			}
		}
	}
	
}
