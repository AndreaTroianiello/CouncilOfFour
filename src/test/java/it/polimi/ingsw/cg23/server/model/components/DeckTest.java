package it.polimi.ingsw.cg23.server.model.components;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class DeckTest {

	private List<PoliticCard> politicCards;

	@Before
	public void setUp() throws Exception {
		politicCards = new ArrayList<>();
		for (int index = 0; index < 10; ++index)
			politicCards.add(new PoliticCard(Color.BLACK, false));
	}

	/**
	 * it tests if deckIsEmpty works properly
	 */
	@Test
	public void testDeckIsEmptyShouldReturnTrueIfThereArentCardsInIt() {
		Deck deck = new Deck(politicCards);
		for (int index = 0; index < 10; ++index)
			deck.draw();
		assertTrue(deck.deckIsEmpty());
		assertNull(deck.draw());
	}

	/**
	 * it tests if discardCard remove a card from the list and add it to the
	 * deck
	 */
	@Test
	public void testDiscardCardShoudAddACardInTheDeck() {
		Deck deck = new Deck(politicCards);
		List<PoliticCard> discardedCards = new ArrayList<>();
		for (int index = 0; index < 10; ++index)
			discardedCards.add(deck.draw());
		deck.discardCards(discardedCards);
		deck.draw();
		assertFalse(deck.deckIsEmpty());
		for (int index = 0; index < 10; ++index)
			deck.draw();
		assertTrue(deck.deckIsEmpty());
		assertNull(deck.draw());
	}

	/**
	 * Tests the toString().
	 */
	@Test
	public void testToString() {
		Deck deck = new Deck(politicCards);
		assertEquals(deck.toString(),
				"Deck [politicCards=[PoliticCard [color=Black], PoliticCard [color=Black], PoliticCard [color=Black], PoliticCard [color=Black], PoliticCard [color=Black], PoliticCard [color=Black], PoliticCard [color=Black], PoliticCard [color=Black], PoliticCard [color=Black], PoliticCard [color=Black]], discardedCards=[]]");
	}

}
