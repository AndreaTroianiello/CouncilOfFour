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
	
	private final int coin;

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
	 * @param player
	 */
	// add to the player's current coins the amount of coins of the bonus 
	@Override
	public void giveBonus(Player player) {
		int playerCoins=player.getCoins();
		playerCoins=playerCoins+this.coin;
		try {
			player.setCoins(playerCoins);
		} catch (NegativeNumberException e) {
			return;
		}
	}
	
	

}
