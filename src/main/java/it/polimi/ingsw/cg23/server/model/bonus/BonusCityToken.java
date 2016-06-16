package it.polimi.ingsw.cg23.server.model.bonus;


import it.polimi.ingsw.cg23.observer.Observable;
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
	private final City city;			//the city the player chooses to run the bonus from
	private  boolean runnable;			//a boolean that show if the bonus in the city are runnable 
	private final String name;
		
	private final Board board;
	
	
	/**
	 * the constructor set the number and the board as the paramater given to the method, the city as a new 
	 * arraylist, every elements of runnbale as true, cl as a new cliinterface and the name as the name
	 * of the bonus
	 * @param city
	 * @param board
	 */
	public BonusCityToken(City city, Board board) {
		this.number = 0;
		this.city = city;
		this.runnable=true;
		this.board = board;
		this.name="CityToken";
	}
	
	

	/**
	 * @return the runnable
	 */
	public boolean getRunnable() {
		return runnable;
	}

	/**
	 * return the bonus name and the number(if exist)
	 */
	@Override
	public String getName(){
		return name;
	}

	/**
	 * @return the city
	 */
	public City getCity() {
		return city;
	}


	/**
	 * call the method runBonusCity of the class city
	 */
	@Override
	public void giveBonus(Player player) {
		
		for(int i=0; i<this.city.getToken().size(); i++) 								//iterate the bonus in the city
			if(this.city.getToken().get(i).getName().contains("BonusNobility")) {		//if the city contains a nobilityBonus bonus
				this.runnable=false;													//set as false the boolean referred to the city 
				}
		if(this.city.containsEmporium(player) && this.runnable)						//control if the city contains an emporium and if it doeasn't have bonusNobility in its bonuses
			this.city.runBonusCity(player);											//if it does, give the player the bonus
	}
	

	@Override
	public void setParameters(){
		//this is a method of the Bonus interfaced not used in this class
	}

	/**
	 *  @return the name and the variables of the class in string
	 */
	@Override
	public String toString() {
		return "BonusCityToken [city=" + city + ", runnable="
				+ runnable + "]";
	}

	/**
	 * @return a new BonusCityToken
	 */
	@Override
	public Bonus copy() {
		return new BonusCityToken(city, board); 
	}

	
	public int getNumber() {
		return number;
	}	

	@Override
	public void setNumber(int number) {
		//this is a method of the Bonus interfaced not used in this class
	}	
	
}