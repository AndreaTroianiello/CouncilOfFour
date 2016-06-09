package it.polimi.ingsw.cg23.controller.change;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.controller.change.PlayerChange;
import it.polimi.ingsw.cg23.server.model.Player;

public class PlayerChangeTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testPlayerChange() {
		Player newPlayer=new Player("user",null);
		PlayerChange change=new PlayerChange(newPlayer);
		assertEquals(change.toString(),"PlayerChange [newPlayer=Player [user=user, additionalAction=false,"
							+ " assistantsPool=0, richness=0, victoryTrack=0, nobilityBoxPosition=0]]" );
	}

}
