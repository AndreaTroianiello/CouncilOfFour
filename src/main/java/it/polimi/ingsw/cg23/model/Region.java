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
		this.cities = new ArrayList<City>();
		this.bonusAvailable = true;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the bonusAvailable
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
	 * @return the cities
	 */
	public List<City> getCities() {
		return cities;
	}
	
	/**
	 * @param the city put the city in the region
	 */
	public void addCity(City city){
		cities.add(city);
	}
	
	/**
	 *  run the bonus associated to the region
	 */
	public void runBonusRegion(Player player){
		if(bonusAvailable){
			bonus.esegui(player);
			setBonusUnavailable();
		}
	}

}
