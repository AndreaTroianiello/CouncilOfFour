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

	/**
	 * it tests if colorNumber returns the number of color in the xml's file
	 * @throws XmlException
	 */
	@Test
	public void testColorNumberShouldReturnTheNumberOfTheColorInTheXml() throws XmlException{
		int colorNum=rc.colorNumber("Colori.xml");
		assertEquals(colorNum, 6);//numero di colori nel file xml
		assertNotEquals(colorNum, 0);
	}
	
	/**
	 * it tests if coloriXml creates an array of strings with all the color in the xml
	 * @throws XmlException
	 */
	@Test
	public void testColoriXmlShouldCreateAnArrayWithAllTheColorsInTheXmlFile() throws XmlException{
		String[] colors=rc.coloriXml("Colori.xml");//numero di colori nel file xml
		assertEquals(colors.length, rc.colorNumber("Colori.xml"));
		
		for(int i=0; i<colors.length; i++){
			assertNotEquals(colors[i], "");
			assertNotEquals(colors[i], null);
		}
		
		
	}
	
	/**
	 * it tests if it throws an xml exception when the file doesn't exist
	 * @throws XmlException
	 */
	@Test(expected=XmlException.class)
	public void testColorNumberShouldThrowsAnXmlExceptionIfTheFileDoesntExist() throws XmlException{
		assertEquals(rc.colorNumber("colore.xml"), 0);
	}
	
	/**
	 * it tests if it throws an xml exception when the file doesn't exist
	 * @throws XmlException
	 */
	@Test(expected=XmlException.class)
	public void testColoriXmlShouldThrowsAnXmlExceptionIfTheFileDoesntExist() throws XmlException{
		assertEquals(rc.coloriXml("Coloris.xml").length, 0);
	}

}
