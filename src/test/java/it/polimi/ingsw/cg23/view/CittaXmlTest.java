package it.polimi.ingsw.cg23.view;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CittaXmlTest {
	
	ReadCittaXml rcx;
	@Before
	public void setUp(){
		rcx=new ReadCittaXml();
	}
	
	@Test
	public void bonusRegionTest() {
		assertEquals(rcx.getBonusRegion("ConfigurazionePartita.xml").length, 3);
		assertNotEquals(rcx.getBonusRegion("ConfigurazionePartitas.xml").length, 3);//file inesistente
	}

	@Test
	public void typeTest() {
		assertEquals(rcx.getType("ConfigurazionePartita.xml").length, 5);
		assertNotEquals(rcx.getType("ConfigurazionePartitas.xml").length, 5);//file inesistente
	}
	
	@Test
	public void cityNodeNumberTest() {
		assertEquals(rcx.cityNodeNumber("ConfigurazionePartita.xml"), 6);
		assertNotEquals(rcx.cityNodeNumber("ConfigurazionePartitas.xml"), 6);//file inesistente
	}
	
	@Test
	public void cityNumberTest() {
		assertEquals(rcx.cityNumber("ConfigurazionePartita.xml"), 15);
		assertNotEquals(rcx.cityNumber("ConfigurazionePartitas.xml"), 15);//file inesistente
	}
	
	@Test
	public void idConversionTest() {
		assertEquals(rcx.idConversion("      A      B",2).length(), 2);
		assertNotNull(rcx.idConversion("      A      B",2).length());
		assertEquals(rcx.idConversion("      A      B",2),"AB");
	}
	
	@Test
	public void ReadFileXxmlTest(){
		assertEquals(rcx.readFileXml("ConfigurazionePartita.xml").length, 15);
		assertNotEquals(rcx.readFileXml("ConfigurazionePartitas.xml").length, 15);//file inesistente
	}
	
}
