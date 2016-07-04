package it.polimi.ingsw.cg23.model.bonus;

import static org.junit.Assert.assertEquals;

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
	 * it tests if giveBonus actually give the bonus when the numberTile is set
	 * properly and if doesn't when the numberTile isn't set
	 * 
	 * @throws NegativeNumberException
	 */
	@Test
	public void testGiveBonusShouldGiveTheBonusInTheTileIfTheNumberTileIsSetndItShouldntGiveItOtherwise()
			throws NegativeNumberException {
		BonusTileBonus bonus = new BonusTileBonus();
		this.player.getAvailableBusinessPermits().add(tile);
		bonus.giveBonus(player);
		bonus.setBoard(null);
		assertEquals(0, this.player.getRichness().getCoins());
		bonus.setNumberTile(0);
		this.player.getRichness().setCoins(10);
		bonus.giveBonus(player);
		assertEquals(12, this.player.getRichness().getCoins());
	}

	/**
	 * it tests if giveBonus doesn't give the bonus when the player doesn't have
	 * available business permit tile
	 */
	@Test
	public void testGiveBonusShouldntGiveTheBonusIfThePlayerDoesntHaveAvailableTile() {
		BonusTileBonus bonus = new BonusTileBonus();
		bonus.giveBonus(player);
		assertEquals(0, this.player.getRichness().getCoins());
	}

	/**
	 * it tests if getName works properly
	 */
	@Test
	public void testGetName() {
		BonusTileBonus bonus = new BonusTileBonus();
		assertEquals("1TileBonus", bonus.getName());
	}

	/**
	 * it tests if getName works properly
	 */
	@Test
	public void testToString() {
		BonusTileBonus bonus = new BonusTileBonus();
		assertEquals("BonusTileBonus [businessPermitCard=1]", bonus.toString());
	}

	/**
	 * it tests if getNumber works properly
	 */
	@Test
	public void testGetNumber() {
		BonusTileBonus bonus = new BonusTileBonus();
		assertEquals(1, bonus.getNumber());
	}

	/**
	 * it tests if getName works properly
	 */
	@Test
	public void testClone() {
		BonusTileBonus bonus = new BonusTileBonus();
		BonusTileBonus newBonus = (BonusTileBonus) bonus.copy();
		assertEquals(bonus.getName(), newBonus.getName());
	}

}
