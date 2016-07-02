package it.polimi.ingsw.cg23.controller.change;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.cg23.server.controller.change.InfoChange;

public class InfoChangeTest {

	/**
	 * it tests if toString works properly
	 */
	@Test
	public void testToString() {
		InfoChange change=new InfoChange("error");
		assertEquals(change.toString(),"InfoChange [msg=error]");
	}

}
