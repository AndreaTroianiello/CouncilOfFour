package it.polimi.ingsw.cg23.server.model.bonus;


import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.cg23.observer.Observable;
import it.polimi.ingsw.cg23.server.controller.change.BonusChange;
import it.polimi.ingsw.cg23.server.controller.change.Change;
import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.City;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.Region;

/**
 * the class of the bonus that allows to run a bonus from a city where the player builds an emporium. 
 *  
 * @author Vincenzo
 *
 */
public class BonusCityToken extends Observable<Change> implements Bonus {
	
	private static final long serialVersionUID = -8457638846172650018L;
	private List<City> cities;			//the city the player chooses to run the bonus from
	private final String name;
	private int number;
	private transient Board board;
	
	/**
	 * the constructor set the parameter as the parameter given to the method, the city as null,
	 * and the name as CityToken
	 * 
	 * @param parameters the number of cities the bonus can be run from
	 */
	public BonusCityToken(int parameters) {
		this.cities = null;
		this.number=parameters;
		this.name="CityToken";
	}

	/**
	 * Sets the board.
	 * 
	 * @param board the model of the game
	 */
	@Override
	public void setBoard(Board board) {
		this.board=board;	
	}

	/**
	 * @return the bonus name and the number(if exist)
	 */
	@Override
	public String getName(){
		return number+name;
	}

	/**
	 * set the list of the cities
	 * @param cities the list of the cities
	 */
	public void setCities(List<City> cities){
		this.cities=cities;
	}
	
	/**
	 * Control if the cities don't contains the nobility bonus and contain the player's emporium
	 * @param realCities the list of the cities
	 * @param player who must have the emporiums in the cities
	 * @return false if the size of the list is different from number, and if a city contains a nobility bonus
	 * 		   or doesn't contain the player's emporium, true otherwise
	 */
	private boolean controlParameters(List<City> realCities,Player player){
		if(realCities.size()!=number)
			return false;
		for(City city: realCities){
			for(int i=0; i<city.getToken().size(); i++) 								//iterate the bonus in the city
				if(city.getToken().get(i).getName().contains("BonusNobility")
				   || !city.containsEmporium(player)) 
					return false;
		}
		
		return true;
	}
	
	/**
	 * Add to a list the city in cities that are actually in the board
	 * @param realCities the list where the real cities are added
	 */
	private void searchRealCities(List<City> realCities){
		if(board==null)
			return;
		List<Region> regions=board.getRegions();
		for(City city:cities)
			for(Region region:regions)
				if(region.searchCityById(city.getId())!=null)
					realCities.add(city);
	}
	/**
	 * call the method runBonusCity of the class city
	 * 
	 * @param player whom the bonus is given to
	 */
	@Override
	public void giveBonus(Player player) {
		List<City> realCities=new ArrayList<>();
		searchRealCities(realCities);
		if(!controlParameters(realCities,player)){
			this.notifyObserver(new BonusChange(this));
			return;
		}
		for(City city: realCities)
			city.runBonusCity(player);				//if it does, give the player the bonus
		 
	}
	
	/**
	 *  @return the name and the variables of the class in string
	 */
	@Override
	public String toString() {
		return "BonusCityToken [parameters="+ number +"]";
	}

	/**
	 * @return a new BonusCityToken
	 */
	@Override
	public Bonus copy() {
		return new BonusCityToken(number); 
	}
	
	/**
	 * @return the number of the cities
	 */
	@Override
	public int getNumber() {
		return number;
	}	

	/**
	 * set the number of the cities
	 */
	@Override
	public void setNumber(int number) {
		this.number=number;
	}	
	
}