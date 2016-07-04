package it.polimi.ingsw.cg23.server.model.action.utilities;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.components.PoliticCard;
import it.polimi.ingsw.cg23.server.model.exception.NegativeNumberException;

/**
 * the class is used for the payment in the councillor's matches
 * 
 * @author Vincenzo
 *
 */
public class Payer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8511514072537911826L;
	private transient Logger logger;

	/**
	 * the constructor set the logger
	 * 
	 * @param logger
	 */
	public Payer() {
		this.logger = Logger.getLogger(Payer.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");
	}

	/**
	 * Sets the logger of the action.
	 * 
	 * @param logger
	 *            action's logger.
	 */
	public void setLogger(Logger logger) {
		this.logger = logger;
		PropertyConfigurator.configure("src/main/resources/logger.properties");
	}

	/**
	 * Returns the logger of the action.
	 * 
	 * @return logger
	 */
	public Logger getLogger() {
		return logger;
	}

	/**
	 * take the relative amount of money based on the number of match
	 * 
	 * @param match
	 *            the number of match
	 * @param player
	 *            who runs the action
	 * @param board
	 *            the model of the game
	 * @return the money paid, -1 if the player doesn't have enough
	 */
	public int payCoins(List<PoliticCard> cards, List<PoliticCard> discardedCards, int match, Player player) {
		int coin = player.getRichness().getCoins();
		int payment = (4 - match) * 3 + 1;
		if (match == 0) {
			return -1;
		}
		if (match == 4) {
			return 0;
		} else {
			if (tryPayment(cards, discardedCards, player, coin, payment) != -1)
				return payment;
			return -1;
		}
	}

	/**
	 * try to make the payment, and catch the exception if the player doesn't
	 * have enough money
	 * 
	 * @param player
	 *            who tries the payment
	 * @param coin
	 *            the current money of the player
	 * @param payment
	 *            the money to be paid
	 * @return 0 if the payment is successful, -1 otherwise
	 */
	private int tryPayment(List<PoliticCard> cards, List<PoliticCard> discardedCards, Player player, int coin,
			int payment) {
		try {
			int money = coin - payment;
			player.getRichness().setCoins(money);
			return 0;
		} catch (NegativeNumberException e) {
			cards.addAll(discardedCards);
			getLogger().error("The player doesn't have enough money", e);
			return -1;
		}
	}

}
