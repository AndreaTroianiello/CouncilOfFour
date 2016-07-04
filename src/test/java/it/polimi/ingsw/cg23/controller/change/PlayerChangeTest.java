package it.polimi.ingsw.cg23.controller.change;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.controller.change.PlayerChange;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.components.NobilityTrack;

public class PlayerChangeTest {
	PlayerChange change;

	/**
	 * Initializes the variables.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		Player newPlayer = new Player("user", new NobilityTrack(1));
		change = new PlayerChange(newPlayer);
	}

	/**
	 * it test getPlayer() method.
	 */
	@Test
	public void testGetPlayer() {
		assertTrue(change.getPlayer().getUser().equals("user"));
	}

	/**
	 * it tests if toString works properly
	 */
	@Test
	public void testToString() {
		assertEquals(change.toString(), "PlayerChange [newPlayer=Player [user=user, additionalAction=false,"
				+ " assistantsPool=0, richness=0, victoryTrack=0, nobilityBoxPosition=0]]");
	}

}
