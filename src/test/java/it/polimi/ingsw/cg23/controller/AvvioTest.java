package it.polimi.ingsw.cg23.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.controller.Avvio;
import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.exception.XmlException;

public class AvvioTest {

	Avvio avv;

	@Before
	public void setUp() throws XmlException {
		avv = new Avvio("map1.xml", new Board(null, new ArrayList<>(), new ArrayList<>(), null, null, null));
	}

	/**
	 * it tests if in the board there are 8 councillor in the pool, if the
	 * king's city is Juvelar, if the king's council has 4 councillor, if the
	 * nobilityTrack's size is 21 and if it is properly filled, and if there are
	 * 3 regions
	 */
	@Test
	public void testStartPartitaShouldCreateProperlyTheBoard() {
		avv.startPartita();
		Board b = avv.getBoard();

		assertEquals(b.getCouncillorPool().size(), 8);// consiglieri rimasti
		assertEquals(b.getKing().getCity().getName(), "Juvelar");// citta' del
																	// re
		assertEquals(b.getKing().getCouncil().getCouncillors().size(), 4);// consiglieri
																			// del
																			// re
		assertEquals(b.getNobilityTrack().getNobilityBoxes().size(), 20 + 1);// lunghezza
																				// nobility
																				// box
		assertEquals(b.getRegions().size(), 3);// numero di regioni
		assertEquals(b.getNobilityTrack().getNobilityBoxes().isEmpty(), false);// nobility
																				// track
																				// pieno
		assertEquals(b.getNobilityTrack().getNobilityBoxes().get(2).getBonus().get(0).getName(), "2VictoryPoints");// nobility
																													// track
																													// pieno
	}

	/**
	 * it tests if there are 5 type of city, if the politic's card's deck is not
	 * empty, if each city but Juvelar has a token, and if each city has at
	 * least one neighbor
	 */
	@Test
	public void testStartPartitaShouldWorksProperly() {
		avv.startPartita();
		Board b = avv.getBoard();
		assertEquals(b.getTypes().size(), 5);// numero di tipi
		assertEquals(b.getDeck().deckIsEmpty(), false);// il deck e' pieno

		for (int i = 0; i < b.getRegions().size(); i++) {// scorre le regioni
			assertEquals(b.getRegions().get(i).getCities().size(), 5);// citta'
																		// della
																		// regione
			assertEquals(b.getRegions().get(i).getDeck().getHiddenDeckSize(), 13);// casrte
																					// costruzione
			assertEquals(b.getRegions().get(i).getCouncil().getCouncillors().size(), 4);

			for (int k = 0; k < b.getRegions().get(i).getCities().size(); k++) {// scorre
																				// le
																				// citta'

				if (b.getRegions().get(i).getCities().get(k).getId() != 'J') // cerco
																				// la
																				// città
																				// del
																				// re
					assertEquals(b.getRegions().get(i).getCities().get(k).getToken().isEmpty(), false);// la
																										// lista
																										// dei
																										// bonus
																										// delle
																										// città
																										// non
																										// e'
																										// vuota
				else
					assertEquals(b.getRegions().get(i).getCities().get(k).getToken().isEmpty(), true);// la
																										// lista
																										// dei
																										// bonus
																										// della
																										// cittaà
																										// del
																										// re
																										// e'
																										// vuota

				assertEquals(b.getRegions().get(i).getCities().get(k).getNeighbors().isEmpty(), false);// vicini
																										// non
																										// nulli
			}
		}
	}
}
