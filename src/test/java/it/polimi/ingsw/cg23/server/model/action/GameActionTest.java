package it.polimi.ingsw.cg23.server.model.action;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GameActionTest {

	
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * it tests if isMain works properly
	 */
	@Test
	public void testIsMain(){
		GameAction action = new HireAssistant();
		assertFalse(action.isMain());
	}
}
