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
		assertEquals(rcx.readCardXml("CostructionCard.xml").length, 45);//numero di carte costruzione
	}
	
	
	@Test(expected=XmlException.class)
	public void readCostructionTestFalse() throws XmlException {
		assertEquals(rcx.readCardXml("CostructionCards.xml").length, 45);//file inesistente
	}
}
