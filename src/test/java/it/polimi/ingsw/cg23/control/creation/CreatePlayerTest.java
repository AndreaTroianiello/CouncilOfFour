package it.polimi.ingsw.cg23.control.creation;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.controller.creation.CreatePlayer;

public class CreatePlayerTest {
	
	private CreatePlayer cp;
	
	@Before
	public void setUp(){
		cp=new CreatePlayer();
	}
	
	@Test
	public void createPlayerTest() {
		cp.createPlayer("nome");
		assertNotNull(cp.getGiocatori());
		assertEquals(cp.getGiocatori().size(), 1);
	}

	@Test
	public void playerNumberTest() {
		cp.createPlayer("nome1");
		cp.createPlayer("nome2");
		assertEquals(cp.playerNumber(), 2);
	}
	
	@Test
	public void cityBonusTest() {
		cp.createPlayer("nome1");
		cp.createPlayer("nome2");
		assertEquals(cp.playerNumber(), 2);
	}
	
}
