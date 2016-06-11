package it.polimi.ingsw.cg23.view;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.model.exception.XmlException;
import it.polimi.ingsw.cg23.server.view.ReadCittaXml;

public class CittaXmlTest {
	
	ReadCittaXml rcx;
	
	@Before
	public void setUp(){
		rcx=new ReadCittaXml();
	}
	
	@Test(expected=XmlException.class)
	public void bonusRegionTest() throws XmlException {
		assertEquals(rcx.getBonusRegion("ConfigurazionePartita.xml").length, 3);
		assertNotEquals(rcx.getBonusRegion("ConfigurazionePartitas.xml").length, 3);//file inesistente
	}

	@Test(expected=XmlException.class)
	public void typeTest() throws XmlException {
		assertEquals(rcx.getType("ConfigurazionePartita.xml").length, 5);
		assertNotEquals(rcx.getType("ConfigurazionePartitas.xml").length, 5);//file inesistente
	}
	
	@Test(expected=XmlException.class)
	public void ReadFileXxmlTest() throws XmlException{
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
