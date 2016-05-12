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
	
	private final int coin;						//the amount of coin given by the bonus

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.cg23.model.bonus.Bonus#esegui(it.polimi.ingsw.cg23.model.Player)
	 */
	public BonusCoin(int coin) {
		this.coin = coin;
	}


	/**
	 * @return the coin
	 */
	public int getCoin() {
		return coin;
	}

	/**
	 * add to the player's current coins the amount of coins of the bonus 
	 * 
	 * @param player
	 */
	
	@Override
	public void giveBonus(Player player) {
		int playerCoins=player.getCoins();				//set a variable at the amount of coins of the player
		playerCoins=playerCoins+this.coin;				//add to the variable the coins given by the bonus
		try {
			player.setCoins(playerCoins);				//set the player's coins at the updated value and throw an exception
		} catch (NegativeNumberException e) {
			System.out.println("The bonus makes the player have negative coins");
		}
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BonusCoin [coin=" + coin + "]";
	}
	
	

}
