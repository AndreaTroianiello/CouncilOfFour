package it.polimi.ingsw.cg23.view;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.model.exception.XmlException;
import it.polimi.ingsw.cg23.server.view.ReadNobilityTrackXml;

public class NobilityTrackXmlTest {
	
	ReadNobilityTrackXml ntx;
	
	@Before
	public void setUp(){
		ntx=new ReadNobilityTrackXml();
	}
	
	@Test(expected=XmlException.class)
	public void NobilityLenghtTest() throws XmlException {
		assertEquals(ntx.nobilityTrackLenght("NobilityTrack.xml"), 21);
		assertNotEquals(ntx.nobilityTrackLenght("NobilityTracks.xml"), 21);//file inesistente
	}
	
	@Test(expected=XmlException.class)
	public void NobilityTest() throws XmlException {
		assertEquals(ntx.nobilityTrackBonus("NobilityTrack.xml").length, 11);
		assertNotEquals(ntx.nobilityTrackBonus("NobilityTracks.xml"), 21);//file inesistente
	}

}
