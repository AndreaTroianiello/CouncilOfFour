package it.polimi.ingsw.cg23.controller.change;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import it.polimi.ingsw.cg23.server.controller.change.StateChange;
import it.polimi.ingsw.cg23.server.model.State;

public class StateChangeTest {

	/**
	 * it tests if toString works properly
	 */
	@Test
	public void testToString() {
		State newState = new State();
		StateChange change = new StateChange(newState);
		assertEquals(change.toString(), "StateChange [newState=State [status=INITIALIZATION]]");
	}

}
