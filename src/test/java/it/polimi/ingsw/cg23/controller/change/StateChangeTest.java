package it.polimi.ingsw.cg23.controller.change;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.cg23.server.controller.change.StateChange;
import it.polimi.ingsw.cg23.server.model.State;

public class StateChangeTest {

	@Test
	public void testStateChange() {
		State newState=new State();
		StateChange change=new StateChange(newState);
		assertEquals(change.toString(),"StateChange [newState=State [status=INITIALIZATION]]");
	}

}
