package it.polimi.ingsw.cg23.server.controller.xmlreader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.model.exception.XmlException;

public class CostructionXmlTest {

	private ReadCostructionXml rcx;

	@Before
	public void setUp() {
		rcx = new ReadCostructionXml();
	}

	/**
	 * it tests if readCarlXml returns a two-dimensional array with the permit
	 * tiles informations in it
	 * 
	 * @throws XmlException
	 */
	@Test
	public void testReadCardXmlShouldReturnATwoDimensionalArrayWithThePermitTilesInformationsInIt()
			throws XmlException {
		String[][] costructionCards = rcx.readCardXml("CostructionCard.xml");
		assertEquals(costructionCards.length, 45);// numero di carte costruzione

		for (int i = 0; i < costructionCards.length; i++) {// controllo che le
															// carte costruzione
															// non siano vuote
			assertNotEquals(costructionCards[i][0], "");
			assertNotEquals(costructionCards[i][1], "");
			assertNotEquals(costructionCards[i][2], "");
		}
	}

	/**
	 * it tests if it throws an xml exception when the file doesn't exist
	 * 
	 * @throws XmlException
	 */
	@Test(expected = XmlException.class)
	public void readCostructionTestFalse() throws XmlException {
		assertEquals(rcx.readCardXml("CostructionCards.xml").length, 45);// file
																			// inesistente
	}
}
