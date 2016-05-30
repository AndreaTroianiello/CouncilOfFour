/**
 * 
 */
package it.polimi.ingsw.cg23.model.bonus;

import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.exception.NegativeNumberException;

/**
 * the class of the bonus that allows the player to increase his richness. It contains the number of coins given
 * byt the bonus to the player and the string of the name
 *
 * 
 * @author utente
 *
 */
public class BonusCoin implements Bonus {
	
	private static final long serialVersionUID = 5361184315271238556L;
	private int coins;						//the amount of coin given by the bonus
	private final String name;
	
	/**
	 * the constructor set the coins as the parameter given to the method and the name as the name
	 * of the bonus
	 * 
	 * @param coin  (can't be negative)
	 */
	public BonusCoin(int coin) {
		this.coins = coin;
		this.name="Coin";
	}

	/**
	 * @return the bonus name and the number(if exist)
	 */
	@Override
	public String getName(){
		return coins+name;
	}
	
	/**
	 * @return the coin
	 */
	public int getCoin() {
		return coins;
	}


	/**
	 * set the number of the coins given by the bonus
	 */
	@Override
	public void setNumber(int number){
		this.coins = number;
	}
	
	/**
	 * add to the player's current coins the amount of coins of the bonus 
	 * 
	 * @param player
	 */
	@Override
	public void giveBonus(Player player) {
		int playerCoins=player.getRichness().getCoins();				//set a variable at the amount of coins of the player
		playerCoins=playerCoins+this.coins;				//add to the variable the coins given by the bonus
		try {
			player.getRichness().setCoins(playerCoins);				//set the player's coins at the updated value and throw an exception
		} catch (NegativeNumberException e) {
			System.out.println("The bonus makes the player have negative coins");
		}
	}
	
	@Override
	public void setParameters(){
		
	}


	/**
	 * @return the name of the class as string
	 */
	@Override
	public String toString() {
		return "BonusCoin [coin=" + coins + "]";
	}
	
	/**
	 * @return a new BonusCoin
	 */
	@Override
	public Bonus clone() {
		return new BonusCoin(0); 
	}	

}
