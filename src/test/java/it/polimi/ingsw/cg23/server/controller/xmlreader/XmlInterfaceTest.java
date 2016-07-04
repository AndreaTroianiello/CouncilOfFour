package it.polimi.ingsw.cg23.server.controller.xmlreader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.model.exception.XmlException;

public class XmlInterfaceTest {

	private XmlInterface xI;
	private Logger logger;

	@Before
	public void setUp() {
		xI = new XmlInterface();
		// configurazione logger
		logger = Logger.getLogger(this.getClass());
		PropertyConfigurator.configure("src/main/resources/logger.properties");// carica
																				// la
																				// configurazione
																				// del
																				// logger
	}

	/**
	 * it tests if getType returns an array with all the types in it
	 */
	@Test
	public void testGetTypeShouldReturnAnArrayWithAllTheTypesInIt() {
		String[][] type = xI.getType("Type.xml");
		assertEquals(type.length, 5);// numero dei tipi

		for (int i = 0; i < type.length; i++) {// tutti i campi non sono vuoti
			assertNotEquals(type[i][0], "");
			assertNotEquals(type[i][1], "");
		}

	}

	/**
	 * it tests if it throws an xml exception when the file doesn't exist
	 * 
	 * @throws XmlException
	 */
	@Test(expected = Exception.class)
	public void testGetTypeShouldThrowsAnXmlExceptionIfTheFileNameIsWrong() throws Exception {
		assertNotEquals(xI.getType("ConfigurazionePartitas.xml").length, 5);// nome
																			// del
																			// file
																			// sbagliato
	}

	/**
	 * it tests if getNobilityTrackLength returns the length of the nobility
	 * track
	 */
	@Test
	public void testGetNobilityTrackLenghtShouldReturnTheLengthOfTheNobilityTrack() {
		int length = xI.getNobilityTrackLength("NobilityTrack.xml");
		assertEquals(length, 21);// lunghezza del nobility track

		// se il file non esiste viene restituito 0
		assertEquals(xI.getNobilityTrackLength("NobilityTracks.xml"), 0);// nome
																			// del
																			// file
																			// sbagliato
	}

	/**
	 * it tests if getNobilityTrackBonus returns a two dimensional array with
	 * the nobility track's infos in it
	 */
	@Test
	public void testGetNobilityTrackBonusShouldReturnAnArrayWithTheNobilityTracksInfosInIt() {
		String[][] ntBonus = xI.getNobilityTrackBonus("NobilityTrack.xml");

		assertEquals(ntBonus.length, 11);// ci sono 11 bonus nel nobility track
		for (int i = 0; i < ntBonus.length; i++) {// controllo che il nobility
													// track non sia vuoto
			assertNotEquals(ntBonus[i][0], "");
			assertNotEquals(ntBonus[i][1], "");
		}

		// se il file non esiste viene restituito null
		assertNull(xI.getNobilityTrackBonus("NobilityTracks.xml"));// nome del
																	// file
																	// sbagliato
	}

	/**
	 * it tests if cittaXml returns an array with the cities' infos in it
	 */
	@Test
	public void testCittaXmlShouldReturnAnArrayWithTheCitiesInfosInIt() {
		String[][] cities = null;
		String name1 = "map1.xml";
		try {
			cities = xI.cittaXml(name1);
		} catch (XmlException e) {
			logger.error("Errore lettura file xml: " + name1, e);
		}

		assertEquals(cities.length, 15);// numero di citta' lette dall'xml
		for (int i = 0; i < cities.length; i++) {// controllo che nell'array non
													// ci siano valori nulli
			assertNotEquals(cities[i][0], "");
			assertNotEquals(cities[i][1], "");
			assertNotEquals(cities[i][2], "");
			assertNotEquals(cities[i][3], "");
			assertNotEquals(cities[i][4], "");

		}
		String name2 = "maps.xml";
		try {
			assertNull(xI.cittaXml(name2));
		} catch (XmlException e) {
			logger.error("Errore lettura file xml: " + name2, e);
		} // il file non esiste
	}

	/**
	 * it tests if getBonusRegion returns an array with the regions and the
	 * bonuses in them
	 */
	@Test
	public void getBonusRegionShouldReturnAnArrayWithTheRegionsAndTheBonusesInThem() {
		String[][] regBonus = xI.getBonusRegion("map1.xml");

		assertEquals(regBonus.length, 3);
		for (int i = 0; i < regBonus.length; i++) {// controllo che nell'array
													// non ci siano valori nulli
			assertNotEquals(regBonus[i][0], "");
			assertNotEquals(regBonus[i][1], "");
		}
		assertNull(xI.getBonusRegion("ConfigurazionePartitas.xml"));// file
																	// inesistente
	}

	/**
	 * it tests if costructionCard returns an array with the permit tile's infos
	 * in it
	 */
	@Test
	public void testCostructionCardShouldReturnAnArrayWithAllTheTilesInfosInIt() {
		String[][] costructionCards = xI.costructionCard("CostructionCard.xml");

		assertEquals(costructionCards.length, 45);
		for (int i = 0; i < costructionCards.length; i++) {// controllo che
															// nell'array non ci
															// siano valori
															// nulli
			assertNotEquals(costructionCards[i][0], "");
			assertNotEquals(costructionCards[i][1], "");
			assertNotEquals(costructionCards[i][2], "");
		}
		assertNull(xI.costructionCard("CostructionCards.xml"));// file
																// inesistente
	}

	/**
	 * it tests if colorXml returns an array with all the colors in it
	 */
	@Test
	public void testColorXmlShouldReturnAnArrayWithAllTheColorsInIt() {
		assertEquals(xI.colorNumberXml("Colori.xml"), 6);
		String[] colors = xI.colorXml("Colori.xml");
		assertEquals(colors.length, 6);

		for (int i = 0; i < colors.length; i++) {
			assertNotEquals(colors[i], "");
			assertNotEquals(colors[i], null);
		}
	}

	/**
	 * it tests if it throws an xml exception when the file's name is incorrect
	 * 
	 * @throws XmlException
	 */
	@Test(expected = Exception.class)
	public void ctestColorXmlShouldThrowsAnExceptionWhenTheNameOfTheFileIsIncorrect() {
		assertEquals(xI.colorNumberXml("Coloric.xml"), 0);
		String[] colors = xI.colorXml("Colorim.xml");
		assertEquals(colors.length, null);
	}

	/**
	 * it tests if it throws an xml exception when the file doesn't exist
	 * 
	 * @throws XmlException
	 */
	@Test(expected = Exception.class)
	public void testBonusCityShouldThrowsAnExceptionWhenTheFileDoesntExist() {
		String[] bonus = xI.bonusCity("CityBonus.xml");
		assertEquals(bonus.length, 14);

		assertEquals(xI.bonusCity("CityBonuses.xml").length, null);// file
																	// inesistente
	}
}
