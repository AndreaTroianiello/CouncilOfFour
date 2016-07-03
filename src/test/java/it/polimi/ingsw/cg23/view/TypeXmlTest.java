package it.polimi.ingsw.cg23.view;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.controller.xmlreader.ReadType;
import it.polimi.ingsw.cg23.server.model.exception.XmlException;

public class TypeXmlTest {

	private ReadType rt;

	@Before
	public void setUp() throws Exception {
		rt=new ReadType();
	}

	/**
	 * it tests if typeXml returns an array of string with all the type's colors in it
	 * @throws XmlException
	 */
	@Test
	public void testTypeXmlShouldReturnAnArrayOfStringsWithAllTheTypesColorInIt() throws XmlException {
		String[][] type=rt.typeXml("Type.xml");
		
		assertEquals(type.length, 5);//numero di righe
		assertEquals(type[0].length, 2);//numero di colonne
		
		for(int i=0; i<type.length; i++){
			boolean ok;
			try{
				Integer.parseInt(type[1][1]);
				ok=true;
			}catch(NumberFormatException e){
				ok=false;
			}
			assertTrue(ok);
		}
	}

}
