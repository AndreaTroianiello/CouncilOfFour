package it.polimi.ingsw.cg23.server.controller.xmlreader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.model.exception.XmlException;

public class NobilityTrackXmlTest {

	ReadNobilityTrackXml ntx;

	@Before
	public void setUp() {
		ntx = new ReadNobilityTrackXml();
	}

	/**
	 * it tests if it throws an xml exception when the file doesn't exist
	 * 
	 * @throws XmlException
	 */
	@Test(expected = XmlException.class)
	public void testNobilityTrackLenghtShouldThrowsAnExceptionIfTheXmlFileDoesntExist() throws XmlException {
		assertEquals(ntx.nobilityTrackLenght("NobilityTrack.xml"), 21);
		assertNotEquals(ntx.nobilityTrackLenght("NobilityTracks.xml"), 21);// file
																			// inesistente
	}

	/**
	 * it tests if it throws an xml exception when the file doesn't exist
	 * 
	 * @throws XmlException
	 */
	@Test(expected = XmlException.class)
	public void testNobilityTrackBonusShouldThrowsAnExceptionIfTheXmlFileDoesntExist() throws XmlException {
		assertEquals(ntx.nobilityTrackBonus("NobilityTrack.xml").length, 11);
		assertNotEquals(ntx.nobilityTrackBonus("NobilityTracks.xml"), 11);// file
																			// inesistente
	}

}
