package it.polimi.ingsw.cg23.server.model.components;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

public class PoliticCardTest {
	PoliticCard card1, card2, card3;

	@Before
	public void setUp() throws Exception {
		card1 = new PoliticCard(null, true);
		card2 = new PoliticCard(Color.BLACK, false);
		card3 = new PoliticCard(Color.RED, false);
	}

	@Test
	public void testGetColor() {
		assertNull(card1.getColor());
		assertEquals(card2.getColor(), Color.BLACK);
		assertEquals(card3.getColor(), Color.RED);
	}

	@Test
	public void testIsJolly() {
		assertTrue(card1.isJolly());
		assertFalse(card2.isJolly());
		assertFalse(card3.isJolly());
	}

	@Test
	public void testToString() {
		assertEquals(card1.toString(), "PoliticCard [jolly=true]");
		assertEquals(card2.toString(), "PoliticCard [color=Black]");
		assertEquals(card3.toString(), "PoliticCard [color=Red]");
	}

}