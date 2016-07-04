package it.polimi.ingsw.cg23.model.bonus;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.bonus.BonusVictoryPoints;
import it.polimi.ingsw.cg23.server.model.components.NobilityTrack;

public class BonusVictoryPointsTest {

	private Player player;

	@Before
	public void setUp() throws Exception {
		player = new Player("a", new NobilityTrack(3));
	}

	/**
	 * it tests if getName works properly
	 */
	@Test
	public void testGetName() {
		BonusVictoryPoints bonus = new BonusVictoryPoints(3);
		assertEquals("3VictoryPoints", bonus.getName());
	}

	/**
	 * it tests if getPoints works properly
	 */
	@Test
	public void testGetPoints() {
		BonusVictoryPoints bonus = new BonusVictoryPoints(3);
		bonus.setBoard(null);
		assertEquals(3, bonus.getNumber());
	}

	/**
	 * it tests if giveBonus works properly
	 */
	@Test
	public void testGiveBonus() {
		BonusVictoryPoints bonus = new BonusVictoryPoints(3);
		bonus.giveBonus(player);
		assertEquals(3, player.getVictoryTrack().getVictoryPoints());
	}

	/**
	 * it tests if toString works properly
	 */
	@Test
	public void testToString() {
		BonusVictoryPoints bonus = new BonusVictoryPoints(3);
		assertEquals("BonusVictoryPoints[points=3]", bonus.toString());
	}

	/**
	 * it tests if getName works properly
	 */
	@Test
	public void testClone() {
		BonusVictoryPoints bonus = new BonusVictoryPoints(3);
		BonusVictoryPoints newBonus = (BonusVictoryPoints) bonus.copy();
		newBonus.setNumber(3);
		assertEquals(bonus.getName(), newBonus.getName());
	}

}
