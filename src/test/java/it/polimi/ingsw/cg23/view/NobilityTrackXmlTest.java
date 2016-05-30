package it.polimi.ingsw.cg23.view;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class NobilityTrackXmlTest {
	
	ReadNobilityTrackXml ntx;
	
	@Before
	public void setUp(){
		ntx=new ReadNobilityTrackXml();
	}
	
	@Test
	public void NobilityLenghttest() {
		assertEquals(ntx.nobilityTrackLenght("NobilityTrack.xml"), 20);
		assertNotEquals(ntx.nobilityTrackLenght("NobilityTracks.xml"), 20);//file inesistente
	}
	
	@Test
	public void Nobilitytest() {
		assertEquals(ntx.nobilityTrackBonus("NobilityTrack.xml").length, 11);
		assertNotEquals(ntx.nobilityTrackBonus("NobilityTracks.xml"), 20);//file inesistente
	}

}
