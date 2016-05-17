/**
 * 
 */
package it.polimi.ingsw.cg23.model.bonus;

import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.exception.NegativeNumberException;

/**
 * @author utente
 *
 */
public class BonusCoin implements Bonus {
	
	private int coins;						//the amount of coin given by the bonus
	private final String name;
	
	/* (non-Javadoc)
	 * @see it.polimi.ingsw.cg23.model.bonus.Bonus#esegui(it.polimi.ingsw.cg23.model.Player)
	 */
	public BonusCoin(int coin) {
		this.coins = coin;
		this.name="Coin";
	}

	/**
	 * return the bonus name and the number(if exist)
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
	 * add to the player's current coins the amount of coins of the bonus 
	 * 
	 * @param player
	 */
	
	public void setNumber(int number){
		this.coins = number;
	}
	
	@Override
	public void giveBonus(Player player) {
		int playerCoins=player.getCoins();				//set a variable at the amount of coins of the player
		playerCoins=playerCoins+this.coins;				//add to the variable the coins given by the bonus
		try {
			player.setCoins(playerCoins);				//set the player's coins at the updated value and throw an exception
		} catch (NegativeNumberException e) {
			System.out.println("The bonus makes the player have negative coins");
		}
	}
	
	
	public void setParameters(){
		
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BonusCoin [coin=" + coins + "]";
	}
	
	public Bonus clone() {
		return new BonusCoin(0); 
	}	

}
