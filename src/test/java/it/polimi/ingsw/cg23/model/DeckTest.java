package it.polimi.ingsw.cg23.model;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.model.components.Deck;
import it.polimi.ingsw.cg23.server.model.components.PoliticCard;

public class DeckTest {

	private List<PoliticCard> politicCards;
	@Before
	public void setUp() throws Exception {
		politicCards=new ArrayList<>();
		for(int index=0;index<10;++index)
			politicCards.add(new PoliticCard(Color.BLACK, false));
	}

	/**
	 * it tests if deckIsEmpty works properly
	 */
	@Test
	public void testDeckIsEmptyShouldReturnTrueIfThereArentCardsInIt() {
		Deck deck=new Deck(politicCards);
		for(int index=0;index<10;++index)
			deck.draw();
		assertTrue(deck.deckIsEmpty());
		assertNull(deck.draw());
	}
	
	/**
	 * it tests if discardCard remove a card from the list and add it to the deck 
	 */
	@Test
	public void testDiscardCardShoudAddACardInTheDeck() {
		Deck deck=new Deck(politicCards);
		List<PoliticCard> discardedCards=new ArrayList<>();
		for(int index=0;index<10;++index)
			discardedCards.add(deck.draw());
		deck.discardCards(discardedCards);
		deck.draw();
		assertFalse(deck.deckIsEmpty());
		for(int index=0;index<10;++index)
			deck.draw();
		assertTrue(deck.deckIsEmpty());
		assertNull(deck.draw());
	}

}
