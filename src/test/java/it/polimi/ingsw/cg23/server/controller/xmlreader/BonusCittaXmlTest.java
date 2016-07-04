package it.polimi.ingsw.cg23.server.controller.xmlreader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.model.exception.XmlException;

public class BonusCittaXmlTest {

	ReadCityBonusXml cityBonus;

	@Before
	public void setUp() throws Exception {
		this.cityBonus = new ReadCityBonusXml();
	}

	/**
	 * it tests if bonusCityXml is able to find all the bonuses in the xml
	 * 
	 * @throws XmlException
	 */
	@Test
	public void testBonusCityXmlShouldCreateAnArrayOfStringsWithAllTheBonusesInIt() throws XmlException {
		String[] bonus = cityBonus.bonusCityXml("CityBonus.xml");

		assertEquals(bonus.length, 14);
		for (int i = 0; i < bonus.length; i++) {
			assertNotEquals(bonus[i], "");
			assertNotEquals(bonus[i], null);
		}
	}

	/**
	 * it tests if it throws an xml exception when the file doesn't exist
	 * 
	 * @throws XmlException
	 */
	@Test(expected = XmlException.class)
	public void testBonusCityXmlException() throws XmlException {
		String[] bonus = cityBonus.bonusCityXml("CityBonused.xml");// file
																	// inesistente
		assertNotEquals(bonus.length, 14);
	}

}
