package it.polimi.ingsw.cg23.model.bonus;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.model.bonus.BonusTileBonus;

public class BonusTileBonusTest {

	@Before
	public void setUp() throws Exception {
	}

	/**
	 * it tests if getBusinessPErmitTiles works properly
	 */
	@Test
	public void testGetBusinessPermitTiles() {
		BonusTileBonus bonus = new BonusTileBonus(2);
		bonus.setNumber(0);
		bonus.setParameters();
		assertEquals(new ArrayList<>(), bonus.getBusinessPermitTiles());
	}

	/**
	 * it tests if getName works properly
	 */
	@Test
	public void testGetName() {
		BonusTileBonus bonus = new BonusTileBonus(0);
		assertEquals("TileBonus", bonus.getName());
	}


	/**
	 * it tests if getName works properly
	 */
	@Test
	public void testToString() {
		BonusTileBonus bonus = new BonusTileBonus(0);
		assertEquals("BonusTileBonus [businessPermitCard=[]]", bonus.toString());
	}

	/**
	 * it tests if getName works properly
	 */
	@Test
	public void testClone() {
		BonusTileBonus bonus = new BonusTileBonus(0);
		BonusTileBonus newBonus = (BonusTileBonus) bonus.clone();
		assertEquals(bonus.getName(), newBonus.getName());
	}

}
