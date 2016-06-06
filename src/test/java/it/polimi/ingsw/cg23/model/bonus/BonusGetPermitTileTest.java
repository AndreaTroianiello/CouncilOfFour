package it.polimi.ingsw.cg23.model.bonus;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.bonus.BonusGetPermitTile;
import it.polimi.ingsw.cg23.server.model.components.BusinessPermitTile;

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
		BonusGetPermitTile bonus = new BonusGetPermitTile(0, board, null, null);
		assertEquals("GetPermitTile", bonus.getName());
	}

	/**
	 * it tests if getBoard works properly
	 */
	@Test
	public void testGetBoard() {
		BonusGetPermitTile bonus = new BonusGetPermitTile(0, board, null, null);
		assertEquals(board, bonus.getBoard());
	}

	/**
	 * it tests if toString works properly
	 */
	@Test
	public void testToString() {
		BonusGetPermitTile bonus = new BonusGetPermitTile(0, board, null, null);
		assertEquals("BonusGetPermitTile", bonus.toString());
	}

	/**
	 * it tests if clone works properly
	 */
	@Test
	public void testClone() {
		BonusGetPermitTile bonus = new BonusGetPermitTile(0, board, null, null);
		BonusGetPermitTile newBonus = (BonusGetPermitTile) bonus.clone();
		assertEquals(bonus.getName(), newBonus.getName());
	}
	
	/**
	 * it tests if getRegion works properly
	 */
	@Test
	public void testGetRegion(){
		Region region = new Region(null, 0, null, null);
		BonusGetPermitTile bonus = new BonusGetPermitTile(0, board, null, region);
		assertEquals(region, bonus.getRegion());
	}
	
	/**
	 * it tests if getBusinessPermit works properly
	 */
	@Test
	public void testGetBusinessPermit(){
		BusinessPermitTile permit = new BusinessPermitTile(null, null);
		BonusGetPermitTile bonus = new BonusGetPermitTile(0, board, permit, null);
		assertEquals(permit, bonus.getBusinessPermit());
	}

}
