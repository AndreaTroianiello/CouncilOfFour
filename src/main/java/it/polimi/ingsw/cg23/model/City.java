package it.polimi.ingsw.cg23.model;

import java.util.ArrayList;
import java.util.List;
import it.polimi.ingsw.cg23.model.bonus.Bonus;
import it.polimi.ingsw.cg23.model.components.Emporium;
import it.polimi.ingsw.cg23.model.exception.NegativeNumberException;

public class City {

	private final char id;									//The identifier of the city
	private final String name;								//The name of the city, the first char is the id.
	private final List<Bonus> token;						//The list of city's bonus.
	private final Type type;								//The city's type.
	private final Region region;							//The city's region.
	private final List<City> neighbors;						//The list of nearly cities.
	private final List<Emporium> emporiums;					//The list of emporiums builded in the city.
	private Type1 objectType;
	
	public City(char id, String name, Type type, Region region){
		this.id=id;
		this.name=name;		
		this.token=new ArrayList<>();
		this.type=type;
		this.region=region;
		//this.type.addCity(this);						//Add this city at the type's list.
		this.region.addCity(this);						//Add this city at the region's list.
		this.neighbors=new ArrayList<>();
		this.emporiums=new ArrayList<>();
		this.objectType=null;
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
		return type.getName();
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
	 * 
	 */
	public void addBonus(Bonus bonus){
		this.token.add(bonus);
	}
	
	/**
	 * Returns the list of the bonus.
	 * 
	 * @return the token.
	 */
	public List<String> getToken() {
		List<String> token=new ArrayList<>();
		for(Bonus bonus: this.token)
			token.add(bonus.getName());
		return token;
	}
	
	/**
	 * Controls if this city contains a player's emporium.
	 * 
	 * @param player the player to control.
	 * @return if this city contains the emporium returns true, otherwise false.
	 */
	public boolean containsEmporium(Player player){
		//Control if the city contains a player's emporium
		for(Emporium emporium: emporiums){							//Extract the emporium.
			Player emporiumPlayer=emporium.getPlayer();				//Get the player of the emporium.
			if(emporiumPlayer.equals(player))						//Control the player.
				return true;										//If its return true.
		}
		return false;												//Otherwise false.
	}

	/**
	 * Builds an emporium in the city. When a player builds a emporium loses assistants. The assistants of the player can't be negative.
	 * This method doesn't control if the player hasn't a emporium in this city.
	 * 
	 * @param emporium an emporium of the player.
	 * @throws NegativeNumberException the assistants of the player must be positive.
	 */
	public void buildEmporium(Emporium emporium) throws NegativeNumberException{
		Player player=emporium.getPlayer();
		int assistantsPlayer=player.getAssistants();
		assistantsPlayer=assistantsPlayer-emporiums.size();						
		player.setAssistants(assistantsPlayer);										//If sets a negative number throws a NegativeNumberException. 
		runBonusCityAndNeighbors(player , new ArrayList<String>());					//Runs the bonus of the city and visits the neighbors.
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
		boolean containsEmporium=containsEmporium(player);					//Control if this city contains a player's emporium.
		
		//Execute if the city is just not visited and doesn't contain a player's emporium
		if(!citiesVisited.contains(name) && !containsEmporium){
			runBonusCity(player);											//Run the bonus
			for(City neighbor: neighbors){									//Visit the neighbors
				neighbor.runBonusCityAndNeighbors(player, citiesVisited);	//Visit the neighbor and run the bonus
				citiesVisited.add(name);									//Add the name of city at citiesVisited
			}
		}
	}
	
	/**
	 * Returns the minimum distance between this city and the destination.
	 * 
	 * @param target the destination city.
	 * @param path the list of cities already visited.
	 * @return the minimum distance between the source and the target.
	 */
	public double minimumDistance(City target,List<City> path)
	{
		if(this.equals(target))														//If this city is the target returns 0.
			return 0;
		else{
			ArrayList<City> neighbors=new ArrayList<>();							//Creates a new list for the neighbors.
			neighbors.addAll(0, this.neighbors);									//Copies the original list of neighbors.
			System.out.println(neighbors.removeAll(path));							//Removes all cities already visited from neighbors' list
			path.add(this);															//Adds this city at the list of cities already visited.
			double minDistance=Double.POSITIVE_INFINITY;							//The minimum default distance has infinite value.
			for (City neighbor : neighbors)											//Explores the neighbors' list.
			{
				double distanceThroughSource=neighbor.minimumDistance(target,path);	//Gets the minimum distance of the neighbor.
				if (distanceThroughSource < minDistance)							//Compares the distance.
					minDistance = distanceThroughSource;							//If the new distance is smaller than the old, this becomes the new minimum distance.
			}
			path.remove(this);														//Removes this city from the path. It can be visited again by new path.
			return minDistance+1;													//Returns the minimum distance increased by one. 
		}
	}
	
	public void setObjectType(Type1 objectType) {
		this.objectType = objectType;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String city= "City [id=" + id + ", name=" + name + ", bonus=" + token.toString() + ", type=" + type +", neighbors=" +neighbors.size()+"]";
		return city;
	}
}
