package it.polimi.ingsw.cg23.server.model.components;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.bonus.Bonus;
import it.polimi.ingsw.cg23.server.model.bonus.BonusAdditionalAction;

public class NobilityBoxTest {

	private Player player;
	private Bonus bonus;

	@Before
	public void setUp() throws Exception {
		this.player = new Player("user", new NobilityTrack(1));
		this.bonus = new BonusAdditionalAction();
	}

	/**
	 * it tests if addPlayer() and removePlayer() work properly
	 */
	@Test
	public void testAddPlayerAndRemovePlayer() {
		NobilityBox box = new NobilityBox();
		box.addPlayer(player);
		assertTrue(box.getPlayers().contains(player));
		box.removePlayer(player);
		box.toString();
		assertTrue(box.getPlayers().isEmpty());
	}

	/**
	 * it tests if addBonus() works properly
	 */
	@Test
	public void testAddBonus() {
		NobilityBox box = new NobilityBox();
		box.addBonus(bonus);
		assertTrue(box.getBonus().contains(bonus));
	}

}
