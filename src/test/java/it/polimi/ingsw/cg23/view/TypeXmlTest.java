package it.polimi.ingsw.cg23.view;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.model.exception.XmlException;
import it.polimi.ingsw.cg23.server.view.xmlreader.ReadType;

public class TypeXmlTest {

	private ReadType rt;

	@Before
	public void setUp() throws Exception {
		rt=new ReadType();
	}

	@Test
	public void typeTest() throws XmlException {
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
