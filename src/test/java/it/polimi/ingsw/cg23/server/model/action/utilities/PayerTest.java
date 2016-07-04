package it.polimi.ingsw.cg23.server.model.action.utilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.server.model.components.PoliticCard;
import it.polimi.ingsw.cg23.server.model.exception.NegativeNumberException;

public class PayerTest {

	private List<PoliticCard> cards;
	private List<PoliticCard> discardedCards;
	private Player player;

	@Before
	public void SetUp() throws Exception {
		this.cards = new ArrayList<>();
		this.discardedCards = new ArrayList<>();
		this.player = new Player("user1", new NobilityTrack(1));
	}

	/**
	 * it tests if getLogger works properly
	 */
	@Test
	public void testGetLogger() {
		Payer payer = new Payer();
		payer.setLogger(null);
		assertNull(payer.getLogger());
	}

	/**
	 * it tests if payCoins returns -1 if there are no matches
	 */
	@Test
	public void testPayCoinsShouldReturnMinus1IfThereArentMatches() {
		Payer payer = new Payer();
		assertEquals(-1, payer.payCoins(cards, discardedCards, 0, player));
	}

	/**
	 * it tests if payCoins returns 0 if there are 4 matches
	 */
	@Test
	public void testPayCoinsShouldReturn0IfThereAre4Matches() {
		Payer payer = new Payer();
		assertEquals(0, payer.payCoins(cards, discardedCards, 4, player));
	}

	/**
	 * it tests if payCoins returns -1 if there are 1 match and the player
	 * doesn't have enough money
	 */
	@Test
	public void testPayCoinsShouldReturnMinus1IfThereAre1MatchAndThePlayerDoesntHaveEnoughMoney() {
		Payer payer = new Payer();
		assertEquals(-1, payer.payCoins(cards, discardedCards, 1, player));
	}

	/**
	 * it tests if payCoins returns 10 if there is 1 match and the player have
	 * enough money
	 * 
	 * @throws NegativeNumberException
	 */
	@Test
	public void testPayCoinsShouldReturnMinus10IfThereIs1Match() throws NegativeNumberException {
		Payer payer = new Payer();
		this.player.getRichness().setCoins(20);
		assertEquals(10, payer.payCoins(cards, discardedCards, 1, player));
		assertEquals(10, this.player.getRichness().getCoins());
	}

}
