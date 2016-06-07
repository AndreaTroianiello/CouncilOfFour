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
	public void cityNodeNumberTest() throws XmlException {
		assertEquals(rcx.cityNodeNumber("ConfigurazionePartita.xml"), 6);
		assertNotEquals(rcx.cityNodeNumber("ConfigurazionePartitas.xml"), 6);//file inesistente
	}
	
	@Test(expected=XmlException.class)
	public void cityNumberTest() throws XmlException {
		assertEquals(rcx.cityNumber("ConfigurazionePartita.xml"), 15);
		assertNotEquals(rcx.cityNumber("ConfigurazionePartitas.xml"), 15);//file inesistente
	}
	
	@Test(expected=XmlException.class)
	public void ReadFileXxmlTest() throws XmlException{
		assertEquals(rcx.readFileXml("ConfigurazionePartita.xml").length, 15);
		assertNotEquals(rcx.readFileXml("ConfigurazionePartitas.xml").length, 15);//file inesistente
	}
	
}
