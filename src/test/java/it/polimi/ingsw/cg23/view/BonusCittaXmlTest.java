package it.polimi.ingsw.cg23.view;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.controller.xmlreader.ReadCityBonusXml;
import it.polimi.ingsw.cg23.server.model.exception.XmlException;

public class BonusCittaXmlTest {
	
	ReadCityBonusXml cityBonus;
	
	@Before
	public void setUp() throws Exception {
		this.cityBonus=new ReadCityBonusXml();
	}

	@Test
	public void BonusCityTest() throws XmlException {
		String[] bonus=cityBonus.bonusCityXml("CityBonus.xml");
		
		assertEquals(bonus.length, 14);
		for(int i=0; i<bonus.length; i++){
			assertNotEquals(bonus[i], "");
			assertNotEquals(bonus[i], null);
		}
	}
	
	@Test(expected=XmlException.class)
	public void BonusCityTestFail() throws XmlException{
		String[] bonus=cityBonus.bonusCityXml("CityBonused.xml");//file inesistente
		assertNotEquals(bonus.length, 14);
	}

}
