/**
 * 
 */
package it.polimi.ingsw.cg23.model.bonus;

import it.polimi.ingsw.cg23.model.Player;

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


	@Override
	public void esegui(Player player) {
		int coins=player.getRichness().getCoins();
		coins=coins+this.coin;
		player.getRichness().setCoins(coins);
	}
	
	

}
