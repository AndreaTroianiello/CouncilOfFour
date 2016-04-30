package it.polimi.ingsw.cg23.model;

import java.util.ArrayList;
import java.util.List;
import it.polimi.ingsw.cg23.model.bonus.Bonus;
import it.polimi.ingsw.cg23.model.components.Emporium;

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
		this.neighbors=new ArrayList<City>();
		this.emporiums=new ArrayList<Emporium>();
	}
	
	/**
	 * @return get the id of city
	 */
	public char getId() {
		return id;
	}

	
	/**
	 * @return get the name of city
	 */
	public String getName() {
		return name;
	}

	
	/**
	 * @return get the type of city 
	 */
	public String getType() {
		return type;
	}
	
	
	/**
	 * @return get the region of city
	 */
	public Region getRegion() {
		return region;
	}
	
	
	/**
	 * @param neighbor add a city at neighbors
	 */
	public void addNeighbor(City neighbor){
		neighbors.add(neighbor);
	}
	
	
	/**
	 * @return all neighbors of the city
	 */
	public List<City> getNeighbors() {
		return neighbors;
	}

	
	/**
	 * @param emporium set a emporium
	 */
	public void addEmporium(Emporium emporium){
		this.emporiums.add(emporium);
	}
	
	
	/**
	 * @return the emporiums built in the city
	 */
	public List<Emporium> getEmporiums() {
		return emporiums;
	}


	/**
	 *  run the bonus associated to the city and its neighbors
	 */
	public void runBonusCity(Player player, List<String> citiesVisited ){
		
		boolean containsEmporium= false;
		
		//control if the city contains a player's emporium
		for(int index=0;index<emporiums.size();++index){
			Emporium emporium=emporiums.get(index);							//Extract the emporium at the index
			Player emporiumPlayer=emporium.getPlayer();						//Get the player
			containsEmporium=emporiumPlayer.equals(player);					//Control the player
		}
		
		//execute if the city is just not visited and doesn't contain a player's emporium
		if(!citiesVisited.contains(name) && !containsEmporium){
			bonus.esegui(player);											//Run the bonus
			for(int index=0;index<neighbors.size();++index)					//Visit the neighbors
				(neighbors.get(index)).runBonusCity(player, citiesVisited);
			citiesVisited.add(name);										//add the name of city at citiesVisited
		}
	}
	
}
