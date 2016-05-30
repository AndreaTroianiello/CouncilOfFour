package it.polimi.ingsw.cg23.view;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CostructionXmlTest {

	private ReadCostructionXml rcx;
	@Before
	public void setUp(){
		rcx=new ReadCostructionXml();
	}
	
	@Test
	public void CardNodeNumbertest() {
		assertEquals(rcx.cardNodeNumber("CostructionCard.xml"), 3);//nodi di card +1 (bonus, citta') (regione)
		assertNotEquals(rcx.cardNodeNumber("CostructionCards.xml"), 3);//file inesistente
	}
	
	@Test
	public void CardNumbertest() {
		assertEquals(rcx.cardNumber("CostructionCard.xml"), 45);//numero di carte costruzione
	}

	@Test
	public void idConversioneTest() {
		String idcity="     A     B";
		int n=2;
		assertNotNull(rcx.idConversion(idcity, n));//numero di carte costruzione
		assertEquals(rcx.idConversion(idcity, n).length(), n);//numero di carte costruzione
	}
	
	@Test
	public void readCostructionTest() {
		assertEquals(rcx.readCardXml("CostructionCard.xml").length, 45);//numero di carte costruzione
		assertNotEquals(rcx.readCardXml("CostructionCards.xml").length, 45);//file inesistente
	}
}
