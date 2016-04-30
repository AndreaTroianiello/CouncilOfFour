package it.polimi.ingsw.cg23.model;

import java.util.ArrayList;
import java.util.List;
import it.polimi.ingsw.cg23.model.bonus.Bonus;

public class City {
	private final char id;
	private final String name;
	private final Bonus bonus;
	private final String type;
	private final Region region;
	private final List<City> neighbors;
	private boolean bonusAvailable;
	
	public City(char id, String name, Bonus bonus, String type, Region region){
		this.id=id;
		this.name=name;		
		this.bonus=bonus;
		this.type=type;
		this.region=region;
		this.neighbors=new ArrayList();
		this.bonusAvailable=true;
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
	 * 
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
	 * @return see if bonus is usable
	 */
	public boolean isBonusAvailable() {
		return bonusAvailable;
	}

	/**
	 *  set bonusAvailable at false
	 */
	public void setBonusUnavailable() {
		this.bonusAvailable = false;
	}

	/**
	 *  run the bonus associated to the city
	 */
	public void runBonus(Player player){
		if(bonusAvailable)
			bonus.esegui(player);
	}
	
}
