package it.polimi.ingsw.cg23.view;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.model.exception.XmlException;
import it.polimi.ingsw.cg23.server.view.ReadCostructionXml;

public class CostructionXmlTest {

	private ReadCostructionXml rcx;
	
	@Before
	public void setUp(){
		rcx=new ReadCostructionXml();
	}
	
	@Test
	public void readCostructionTestTrue() throws XmlException {
		String[][] costructionCards=rcx.readCardXml("CostructionCard.xml");
		assertEquals(costructionCards.length, 45);//numero di carte costruzione
		
		for(int i=0; i<costructionCards.length; i++){//controllo che le carte costruzione non siano vuote
			assertNotEquals(costructionCards[i][0], "");
			assertNotEquals(costructionCards[i][1], "");
			assertNotEquals(costructionCards[i][2], "");
		}
	}
	
	
	@Test(expected=XmlException.class)
	public void readCostructionTestFalse() throws XmlException {
		assertEquals(rcx.readCardXml("CostructionCards.xml").length, 45);//file inesistente
	}
}
