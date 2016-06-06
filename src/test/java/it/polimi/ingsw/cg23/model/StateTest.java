package it.polimi.ingsw.cg23.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.State;

public class StateTest {

	private Player p;
	
	@Before
	public void setUp() throws Exception {
		p=new Player("user1", 0, 0, null);
	}

	@Test
	public void testStatus() {
		State state=new State();
		assertEquals(state.getStatus(),"INITIALIZATION");
		state.setStatus("OK");
		assertEquals(state.getStatus(),"OK");
	}

	@Test
	public void testCurrentPlayer() {
		State state=new State();
		assertNull(state.getCurrentPlayer());
		state.setCurrentPlayer(p);
		assertEquals(state.getCurrentPlayer(),p);
	}

	@Test
	public void testFinalPlayer() {
		State state=new State();
		assertNull(state.getFinalPlayer());
		state.setFinalPlayer(p);
		assertEquals(state.getFinalPlayer(),p);
	}

	@Test
	public void testToString() {
		State state=new State();
		assertEquals(state.toString(),"State [status=INITIALIZATION]");
		state.setCurrentPlayer(p);
		assertEquals(state.toString(),"State [status=INITIALIZATION, current player="+p.getUser()+"]");
		state.setFinalPlayer(p);
		assertEquals(state.toString(),"State [status=INITIALIZATION, current player="+p.getUser()
			+", final player="+p.getUser()+"]");
	}

}
