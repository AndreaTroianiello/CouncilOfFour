package it.polimi.ingsw.cg23.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.State;
import it.polimi.ingsw.cg23.server.model.components.NobilityTrack;

public class StateTest {

	private Player p;
	
	@Before
	public void setUp() throws Exception {
		p=new Player("user1", new NobilityTrack(1));
	}

	/**
	 * test if getStatus works properly
	 */
	@Test
	public void testGetStatus() {
		State state=new State();
		assertEquals(state.getStatus(),"INITIALIZATION");
		state.setStatus("OK");
		assertEquals(state.getStatus(),"OK");
	}

	/**
	 * tests if getCurrentPlayer returns the right player
	 */
	@Test
	public void testCurrentPlayerShouldReturnTheCurrentPlayerIfItIsSetted() {
		State state=new State();
		assertNull(state.getCurrentPlayer());
		state.setCurrentPlayer(p);
		assertEquals(state.getCurrentPlayer(),p);
	}

	/**
	 * test if getFinalPlayer returns the player that has built all the emporiums
	 */
	@Test
	public void testFinalPlayer() {
		State state=new State();
		assertNull(state.getFinalPlayer());
		state.setFinalPlayer(p);
		assertEquals(state.getFinalPlayer(),p);
	}
	
	/**
	 * it tests if changeStatus works properly
	 */
	@Test
	public void testChangeStatus() {
		State state=new State();
		assertEquals(state.getStatus(),"INITIALIZATION");
		state.changeStatus();
		assertEquals(state.getStatus(),"TURN");
		state.changeStatus();
		assertEquals(state.getStatus(),"MARKET: SELLING");
		state.changeStatus();
		assertEquals(state.getStatus(),"MARKET: BUYING");
		state.changeStatus();
		assertEquals(state.getStatus(),"TURN");
		state.setFinalPlayer(p);
		state.changeStatus();
		assertEquals(state.getStatus(),"FINAL TURN");
		
	}

	/**
	 * it tests if toString works properly
	 */
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
