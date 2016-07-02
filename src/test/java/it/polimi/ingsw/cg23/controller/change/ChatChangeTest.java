package it.polimi.ingsw.cg23.controller.change;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.controller.change.ChatChange;

public class ChatChangeTest {

	@Before
	public void setUp() throws Exception {
	}

	/**
	 * it tests if toString works properly
	 */
	@Test
	public void testToString() {
		ChatChange change = new ChatChange("Ciao", "user");
		assertEquals("user : Ciao", change.toString());
	}

}
