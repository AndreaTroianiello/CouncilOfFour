package it.polimi.ingsw.cg23.model.bonus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.polimi.ingsw.cg23.model.City;
import it.polimi.ingsw.cg23.model.Player;

public class BonusCityToken implements Bonus {
	
	private final int number;				//how many times the player can run the bonus
	private final List<City> city;			//the city the player chooses to run the bonus from
	private final boolean[] runnable;		//a list of boolean that show if the bonus in the city are runnable 
	private final String name="CityToken";
		
	

	public BonusCityToken(int number, City[] city) {
		this.number = number;
		this.city = new ArrayList<>();
		this.runnable = new boolean[this.city.size()];
		for(int i=0; i<this.runnable.length; i++)			//set all the array's elements at false
			this.runnable[i] = true;
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
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
	public List<City> getCity() {
		return city;
	}


	/**
	 * call the method runBonusCity of the class city
	 */
	@Override
	public void giveBonus(Player player) {
		for(int i=0; i<this.number; i++){											//iterate the city in the list
			for(int j=0; j<this.city.get(i).getToken().size(); j++) 					//iterate the bonus in every city
				if(this.city.get(i).getToken().get(j).contains("BonusNobility")) {		//if the city contains a nobilityBonus bonus
					this.runnable[i]=false;										//set as true the boolean referred to that city 
					}
			if(this.city.get(i).containsEmporium(player) && this.runnable[i])		//control if the city contains an emporium and if it doeasn't have bonusNobility in its bonuses
				(this.city.get(i)).runBonusCity(player);								//if it does, give the player the bonus
		}
	}

	public void setParameters(){
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BonusCityToken [number=" + number + ", city=" + city + ", runnable="
				+ Arrays.toString(runnable) + "]";
	}


	
}
