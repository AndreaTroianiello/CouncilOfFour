package it.polimi.ingsw.cg23.model.bonus;

import java.util.Arrays;

import it.polimi.ingsw.cg23.model.City;
import it.polimi.ingsw.cg23.model.Player;

public class BonusCityToken implements Bonus {
	
	private final int number;				//how many times the player can run the bonus
	private final City[] city;
	
	

	public BonusCityToken(int number, City[] city) {
		this.number = number;
		this.city = city;
	}

	

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}



	/**
	 * @return the city
	 */
	public City[] getCity() {
		return city;
	}


	/**
	 * call the method runBonusCity of the class city
	 */
	@Override
	public void giveBonus(Player player) {
		for(int i=0; i<this.number; i++)					//iterate the city in the list
			if(this.city[i].containsEmporium(player))		//control if the city contains an emporium
				(this.city[i]).runBonusCity(player);		//if it does, give the player the bonus
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BonusCityToken [number=" + number + ", city=" + Arrays.toString(city) + "]";
	}

	
}
