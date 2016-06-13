package it.polimi.ingsw.cg23.view;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.view.XmlInterface;

public class XmlInterfaceTest {

	XmlInterface xI;

	@Before
	public void setUp(){
		xI=new XmlInterface();
	}

	@Test
	public void getTypeTest() {
		String[][] type=xI.getType("Type.xml");
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

		assertEquals(ntBonus.length, 11);//ci sono 11 bonus nel nobility track
		for(int i=0; i<ntBonus.length; i++){//controllo che il nobility track non sia vuoto
			assertNotEquals(ntBonus[i][0], "");
			assertNotEquals(ntBonus[i][1], "");
		}

		//se il file non esiste viene restituito null
		assertNull(xI.getNobilityTrackBonus("NobilityTracks.xml"));//nome del file sbagliato
	}

	@Test
	public void getCittaXmlTest(){
		String[][] cities=xI.cittaXml("RegionCity.xml");
		
		assertEquals(cities.length, 15);//numero di citta' lette dall'xml
		for(int i=0; i<cities.length; i++){//controllo che nell'array non ci siano valori nulli
			assertNotEquals(cities[i][0],"");
			assertNotEquals(cities[i][1],"");
			assertNotEquals(cities[i][2],"");
			assertNotEquals(cities[i][3],"");
			assertNotEquals(cities[i][5],"");

			if(cities[i][3].equals("J"))//la citta' del re non ha bonus
				assertEquals(cities[i][4],"");
			else
				assertNotEquals(cities[i][4],"");
		}
		assertNull(xI.cittaXml("ConfigurazionePartitar.xml"));//il file non esiste
	}
	
	@Test
	public void getBonusRegionTest(){
		String[][] regBonus=xI.getBonusRegion("RegionCity.xml");
		
		assertEquals(regBonus.length, 3);
		for(int i=0; i<regBonus.length; i++){//controllo che nell'array non ci siano valori nulli
			assertNotEquals(regBonus[i][0],"");
			assertNotEquals(regBonus[i][1],"");
		}
		assertNull(xI.getBonusRegion("ConfigurazionePartitas.xml"));//file inesistente
	}
	
	@Test
	public void costructionCard(){
		String[][] costructionCards=xI.costructionCard("CostructionCard.xml");
		
		assertEquals(costructionCards.length, 45);
		for(int i=0; i<costructionCards.length; i++){//controllo che nell'array non ci siano valori nulli
			assertNotEquals(costructionCards[i][0], "");
			assertNotEquals(costructionCards[i][1], "");
			assertNotEquals(costructionCards[i][2], "");
		}
		assertNull(xI.costructionCard("CostructionCards.xml"));//file inesistente
	}
	
	@Test
	public void colorTest(){
		assertEquals(xI.colorNumberXml("Colori.xml"), 6);
		String[] colors=xI.colorXml("Colori.xml");
		assertEquals(colors.length, 6);
		
		for(int i=0; i<colors.length; i++){
			assertNotEquals(colors[i], "");
			assertNotEquals(colors[i], null);
		}
	}
	
	@Test (expected=Exception.class)
	public void colorTestFail(){
		assertEquals(xI.colorNumberXml("Coloric.xml"), 0);
		String[] colors=xI.colorXml("Colorim.xml");
		assertEquals(colors.length, null);

	}
}
