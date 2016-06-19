/**
 * 
 */
package it.polimi.ingsw.cg23.server.model.bonus;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.exception.NegativeNumberException;

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
	
	private static Logger logger;
	
	/**
	 * the constructor set the coins as the parameter given to the method and the name as the name
	 * of the bonus
	 * 
	 * @param coin  (can't be negative)
	 */
	public BonusCoin(int coin) {
		BonusCoin.logger = Logger.getLogger(BonusCoin.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");
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
	
	@Override
	public void setBoard(Board board) {
		// Not implemented.
		
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
			logger.error("The bonus makes the player have negative coins", e);
		}
	}
	
	@Override
	public int getNumber(){
		return coins;
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
	public Bonus copy() {
		return new BonusCoin(0); 
	}	

}
