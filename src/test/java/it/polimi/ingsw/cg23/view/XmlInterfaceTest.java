package it.polimi.ingsw.cg23.view;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.view.Print;
import it.polimi.ingsw.cg23.server.view.XmlInterface;

public class XmlInterfaceTest {

	XmlInterface xI;

	@Before
	public void setUp(){
		xI=new XmlInterface();
	}

	@Test
	public void getTypeTest() {
		String[][] type=xI.getType("ConfigurazionePartita.xml");
		assertEquals(type.length, 5);//numero dei tipi

		for(int i=0; i<type.length; i++){//tutti i campi non sono vuoti
			assertNotEquals(type[i][0], "");
			assertNotEquals(type[i][1], "");
		}

	}

	@Test(expected=Exception.class)
	public void getTypeTestFail() throws Exception{
		assertNotEquals(xI.getType("ConfigurazionePartitas.xml").length, 5);//nome del file sbagliato
	}

	@Test
	public void getNobilityTrackLenghtTest() {
		int length=xI.getNobilityTrackLength("NobilityTrack.xml");
		assertEquals(length, 21);//lunghezza del nobility track

		//se il file non esiste viene restituito 0
		assertEquals(xI.getNobilityTrackLength("NobilityTracks.xml"), 0);//nome del file sbagliato
	}

	
	@Test
	public void getNobilityTrackBonusTest(){
		String[][] ntBonus=xI.getNobilityTrackBonus("NobilityTrack.xml");
		new Print().printArray(ntBonus);
		
		assertEquals(ntBonus.length, 11);//ci sono 11 bonus nel nobility track
		for(int i=0; i<ntBonus.length; i++){//controllo che il nobility track non sia vuoto
			assertNotEquals(ntBonus[i][0], "");
			assertNotEquals(ntBonus[i][1], "");
		}
		
		//se il file non esiste viene restituito null
		assertNull(xI.getNobilityTrackBonus("NobilityTracks.xml"));//nome del file sbagliato
	}

}
