package it.polimi.ingsw.cg23.model.bonus;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.bonus.Bonus;
import it.polimi.ingsw.cg23.server.model.bonus.BonusCoin;
import it.polimi.ingsw.cg23.server.model.bonus.BonusNobility;
import it.polimi.ingsw.cg23.server.model.components.NobilityTrack;

public class BonusNobilityTest {

	private Board board;
	private Player player;
	private Bonus bonus2;
	
	@Before
	public void setUp() throws Exception {
		NobilityTrack nobilityTrack = new NobilityTrack(10);
		bonus2 = new BonusCoin(4);
		nobilityTrack.getNobilityBoxes().get(2).addBonus(bonus2);
		board = new Board(null, null, null ,nobilityTrack, null, null);
		player = new Player("a", nobilityTrack);
	}

	/**
	 * it tests if getName works properly
	 */
	@Test
	public void testGetName() {
		BonusNobility bonus = new BonusNobility(2, board);
		assertEquals("2BonusNobility", bonus.getName());
	}

	/**
	 * it tests if getBoard works properly
	 */
	@Test
	public void testGetBoard() {
		BonusNobility bonus = new BonusNobility(2, board);
		assertEquals(board, bonus.getBoard());
	}

	/**
	 * it tests if getSteps works properly
	 */
	@Test
	public void testGetSteps() {
		BonusNobility bonus = new BonusNobility(2, board);
		bonus.setBoard(board);
		assertEquals(2, bonus.getNumber());
	}

	/**
	 * it tests if giveBonus works properly
	 */
	@Test
	public void testGiveBonus() {
		BonusNobility bonus = new BonusNobility(2, board);
		bonus.giveBonus(player);
		bonus.setNumber(2);
		assertEquals(2, player.getNobilityBoxPosition());
		assertEquals(4, player.getRichness().getCoins());
	}

	/**
	 * it tests if toString works properly
	 */
	@Test
	public void testToString() {
		BonusNobility bonus = new BonusNobility(2, board);
		assertEquals("BonusNobility [steps=2]", bonus.toString());
	}

	/**
	 * it tests if clone works properly
	 */
	@Test
	public void testClone() {
		BonusNobility bonus = new BonusNobility(0, board);
		BonusNobility newBonus = (BonusNobility) bonus.copy();
		assertEquals(bonus.getName(), newBonus.getName());
	}

}
