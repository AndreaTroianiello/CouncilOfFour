package it.polimi.ingsw.cg23.view;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.controller.xmlreader.ReadColorXml;
import it.polimi.ingsw.cg23.server.model.exception.XmlException;

public class ColorXmlText {
	
	ReadColorXml rc;
	
	@Before
	public void setUp() {
		rc=new ReadColorXml();
	}

	@Test
	public void colorNumberTest() throws XmlException{
		int colorNum=rc.colorNumber("Colori.xml");
		assertEquals(colorNum, 6);//numero di colori nel file xml
		assertNotEquals(colorNum, 0);
	}
	
	@Test
	public void colorTest() throws XmlException{
		String[] colors=rc.coloriXml("Colori.xml");//numero di colori nel file xml
		assertEquals(colors.length, rc.colorNumber("Colori.xml"));
		
		for(int i=0; i<colors.length; i++){
			assertNotEquals(colors[i], "");
			assertNotEquals(colors[i], null);
		}
		
		
	}
	
	@Test(expected=XmlException.class)
	public void fail2() throws XmlException{
		assertEquals(rc.colorNumber("colore.xml"), 0);
	}
	
	@Test(expected=XmlException.class)
	public void fail() throws XmlException{
		assertEquals(rc.coloriXml("Coloris.xml").length, 0);
	}

}
