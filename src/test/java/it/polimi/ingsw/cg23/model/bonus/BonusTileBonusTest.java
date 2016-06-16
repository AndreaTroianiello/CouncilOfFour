package it.polimi.ingsw.cg23.model.bonus;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.bonus.BonusCoin;
import it.polimi.ingsw.cg23.server.model.bonus.BonusTileBonus;
import it.polimi.ingsw.cg23.server.model.components.BusinessPermitTile;
import it.polimi.ingsw.cg23.server.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.server.model.exception.NegativeNumberException;

public class BonusTileBonusTest {

	private Player player;
	private BusinessPermitTile tile;
	
	
	@Before
	public void setUp() throws Exception {
		this.player = new Player("player2", new NobilityTrack(2));
		this.tile = new BusinessPermitTile(null, null);
		this.tile.addBonus(new BonusCoin(2));
	}

	/**
	 * it tests if getBusinessPermitTiles works properly
	 */
	@Test
	public void testGetBusinessPermitTiles() {
		BonusTileBonus bonus = new BonusTileBonus(2, null);
		assertEquals(null, bonus.getBusinessPermitTiles());
	}
	
	/**
	 * it tests if giveBonus actually give the bonus
	 * @throws NegativeNumberException
	 */
	@Test 
	public void testGiveBonusShouldGiveTheBonusInTheTile() throws NegativeNumberException{
		BonusTileBonus bonus = new BonusTileBonus(0, tile);
		this.player.getRichness().setCoins(10);
		bonus.giveBonus(player);
		assertEquals(12, this.player.getRichness().getCoins());
	}

	/**
	 * it tests if getName works properly
	 */
	@Test
	public void testGetName() {
		BonusTileBonus bonus = new BonusTileBonus(0, null);
		assertEquals("TileBonus", bonus.getName());
	}


	/**
	 * it tests if getName works properly
	 */
	@Test
	public void testToString() {
		BonusTileBonus bonus = new BonusTileBonus(0, null);
		assertEquals("BonusTileBonus [businessPermitCard=null]", bonus.toString());
	}

	/**
	 * it tests if getName works properly
	 */
	@Test
	public void testClone() {
		BonusTileBonus bonus = new BonusTileBonus(0, null);
		BonusTileBonus newBonus = (BonusTileBonus) bonus.copy();
		assertEquals(bonus.getName(), newBonus.getName());
	}

}
