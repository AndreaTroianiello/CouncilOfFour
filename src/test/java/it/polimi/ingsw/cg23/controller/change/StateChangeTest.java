package it.polimi.ingsw.cg23.controller.change;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.cg23.controller.change.StateChange;
import it.polimi.ingsw.cg23.model.State;

public class StateChangeTest {

	@Test
	public void testStateChange() {
		State newState=new State("OK");
		StateChange change=new StateChange(newState);
		assertEquals(change.toString(),"StateChange [newState=State [status=OK]]");
	}

}
