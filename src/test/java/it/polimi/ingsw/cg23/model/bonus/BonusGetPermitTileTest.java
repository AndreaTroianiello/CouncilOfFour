package it.polimi.ingsw.cg23.model.bonus;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.model.Board;

public class BonusGetPermitTileTest {

	private Board board;
	
	@Before
	public void setUp() throws Exception {
		board = new Board(null, null, null, null, null);
	}

	/**
	 * it tests if getName works properly
	 */
	@Test
	public void testGetName() {
		BonusGetPermitTile bonus = new BonusGetPermitTile(0, board);
		assertEquals("GetPermitTile", bonus.getName());
	}

	/**
	 * it tests if getBoard works properly
	 */
	@Test
	public void testGetBoard() {
		BonusGetPermitTile bonus = new BonusGetPermitTile(0, board);
		assertEquals(board, bonus.getBoard());
	}

	/**
	 * it tests if toString works properly
	 */
	@Test
	public void testToString() {
		BonusGetPermitTile bonus = new BonusGetPermitTile(0, board);
		assertEquals("BonusGetPermitTile", bonus.toString());
	}

	/**
	 * it tests if clone works properly
	 */
	@Test
	public void testClone() {
		BonusGetPermitTile bonus = new BonusGetPermitTile(0, board);
		BonusGetPermitTile newBonus = (BonusGetPermitTile) bonus.clone();
		assertEquals(bonus.getName(), newBonus.getName());
	}

}