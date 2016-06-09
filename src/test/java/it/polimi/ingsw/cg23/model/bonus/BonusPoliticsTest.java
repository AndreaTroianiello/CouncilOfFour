package it.polimi.ingsw.cg23.model.bonus;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.bonus.BonusPolitics;
import it.polimi.ingsw.cg23.server.model.components.Deck;
import it.polimi.ingsw.cg23.server.model.components.PoliticCard;

public class BonusPoliticsTest {

	private Board board;
	private Deck deck;
	private Player player;
	
	@Before
	public void setUp() throws Exception {
		PoliticCard card = new PoliticCard(Color.ORANGE, false);
		List<PoliticCard> cards = new ArrayList<>();
		cards.add(card);
		deck = new Deck(cards);
		board = new Board( deck, null, null, null, null);
		player = new Player("a", null);
	}

	/**
	 * it tests if getName works properly
	 */
	@Test
	public void testGetName() {
		BonusPolitics bonus = new BonusPolitics(1, board);
		assertEquals("1Politics", bonus.getName());
	}

	/**
	 * it tests if getBoard works properly
	 */
	@Test
	public void testGetBoard() {
		BonusPolitics bonus = new BonusPolitics(1, board);
		assertEquals(board, bonus.getBoard());

	}

	/**
	 * it tests if getCardNumber works properly
	 */
	@Test
	public void testGetCardNumber() {
		BonusPolitics bonus = new BonusPolitics(1, board);
		assertEquals(1, bonus.getCardNumber());
	}

	/**
	 * it tests if giveBonus works properly
	 */
	@Test
	public void testGiveBonusShouldGiveThePlayerTheFirstCardInTheDeck() {
		BonusPolitics bonus = new BonusPolitics(1, board);
		bonus.setParameters();
		bonus.giveBonus(player);
		PoliticCard expectedCard = new PoliticCard(Color.ORANGE, false);
		assertEquals(expectedCard.getColor(), player.getHand().get(0).getColor());
	}
	
	/**
	 * it tests if giveBonus works properly when there is no card in the deck
	 */
	@Test
	public void testGiveBonusShouldntGiveThePlayerTheFirstCardInTheDeckIfItIsNull() {
		List<PoliticCard> emptyDeck = new ArrayList<>();
		Deck newDeck = new Deck(emptyDeck);
		Board board2 = new Board(newDeck, null, null, null, null);
		BonusPolitics bonus = new BonusPolitics(1, board2);
		bonus.setParameters();
		bonus.giveBonus(player);
		assertEquals(new ArrayList<>(), player.getHand());
	}

	/**
	 * it tests if toString works properly
	 */
	@Test
	public void testToString() {
		BonusPolitics bonus = new BonusPolitics(1, board);
		assertEquals("BonusPolitics [cardNumber=1]", bonus.toString());
	}

	/**
	 * it tests if clone works properly
	 */
	@Test
	public void testClone() {
		BonusPolitics bonus = new BonusPolitics(1, board);
		BonusPolitics newBonus = (BonusPolitics) bonus.copy();
		newBonus.setNumber(1);
		assertEquals(bonus.getName(), newBonus.getName());
	}

}
