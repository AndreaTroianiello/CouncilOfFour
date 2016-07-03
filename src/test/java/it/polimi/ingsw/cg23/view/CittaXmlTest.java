package it.polimi.ingsw.cg23.view;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.controller.xmlreader.ReadCittaXml;
import it.polimi.ingsw.cg23.server.model.exception.XmlException;

public class CittaXmlTest {
	
	ReadCittaXml rcx;
	
	@Before
	public void setUp(){
		rcx=new ReadCittaXml();
	}
	
	/**
	 * it tests if it throws an xml exception when the file doesn't exist
	 * @throws XmlException
	 */
	@Test(expected=XmlException.class)
	public void testBonusRegionShouldThrowsAnXmlExceptionIfTheFileDoesntExist() throws XmlException {
		assertEquals(rcx.getBonusRegion("ConfigurazionePartita.xml").length, 3);
		assertNotEquals(rcx.getBonusRegion("ConfigurazionePartitas.xml").length, 3);//file inesistente
	}
	
	/**
	 * it tests if it throws an xml exception when the file doesn't exist
	 * @throws XmlException
	 */
	@Test(expected=XmlException.class)
	public void testReadFileXxml() throws XmlException{
		String[][] cities=rcx.readFileXml("ConfigurazionePartita.xml");
		assertEquals(cities.length, 15);
		
		for(int i=0; i<cities.length; i++){//controllo che l'array sia pieno
			assertNotEquals(cities[i][0], "");
			assertNotEquals(cities[i][1], "");
			assertNotEquals(cities[i][2], "");
			assertNotEquals(cities[i][3], "");
			assertNotEquals(cities[i][5], "");
		}
		assertNotEquals(rcx.readFileXml("ConfigurazionePartitas.xml").length, 15);//file inesistente
	}
	
}
