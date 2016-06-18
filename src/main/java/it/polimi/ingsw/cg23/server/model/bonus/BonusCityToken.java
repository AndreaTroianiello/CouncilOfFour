package it.polimi.ingsw.cg23.server.model.bonus;


import java.util.List;

import it.polimi.ingsw.cg23.observer.Observable;
import it.polimi.ingsw.cg23.server.controller.change.BonusChange;
import it.polimi.ingsw.cg23.server.controller.change.Change;
import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.City;
import it.polimi.ingsw.cg23.server.model.Player;

/**
 * the class of the bonus that allows to run a bonus from a city where the player builds an emporium. It contains 
 * the number of times the bonus can be run, a list of the city where the player wants to take the bonus
 * from, an array that shows if the bonuses in the chosen cities are runnable, a string of the name, the board 
 * and the CliInterface 
 * @author Vincenzo
 *
 */
public class BonusCityToken extends Observable<Change> implements Bonus {
	
	private static final long serialVersionUID = -8457638846172650018L;
	private int number;
	private List<City> cities;			//the city the player chooses to run the bonus from
	//private boolean runnable;			//a boolean that show if the bonus in the city are runnable 
	private final String name;
	private int parameters;
	
	
	/**
	 * the constructor set the number and the board as the paramater given to the method, the city as a new 
	 * arraylist, every elements of runnbale as true, cl as a new cliinterface and the name as the name
	 * of the bonus
	 * @param city
	 * @param board
	 */
	public BonusCityToken(int parameters) {
		this.number = 0;
		this.cities = null;
		this.parameters=parameters;
		this.name="CityToken";
	}
	
	

	/**
	 * @return the runnable
	 */
	/*public boolean getRunnable() {
		return runnable;
	}*/

	/**
	 * return the bonus name and the number(if exist)
	 */
	@Override
	public String getName(){
		return name;
	}

	private boolean controlParameters(){
		if(cities==null||cities.size()==0)
			return false;
		for(City city: cities){
			for(int i=0; i<city.getToken().size(); i++) 								//iterate the bonus in the city
				if(city.getToken().get(i).getName().contains("BonusNobility")) 
					return false;
		}
		return true;
	}
	/**
	 * call the method runBonusCity of the class city
	 */
	@Override
	public void giveBonus(Player player) {
		if(!controlParameters()){
			this.notifyObserver(new BonusChange(this));
			return;
		}
		/*if(city.containsEmporium(player))						//control if the city contains an emporium and if it doeasn't have bonusNobility in its bonuses
			city.runBonusCity(player);											//if it does, give the player the bonus
		 */
	}
	
	/**
	 *  @return the name and the variables of the class in string
	 */
	@Override
	public String toString() {
		return "BonusCityToken [parameters="+ parameters +"]";
	}

	/**
	 * @return a new BonusCityToken
	 */
	@Override
	public Bonus copy() {
		return new BonusCityToken(parameters); 
	}

	@Override
	public int getParameters() {
		return parameters;
	}	

	@Override
	public void setNumber(int number) {
		this.parameters=number;
	}	
	
}