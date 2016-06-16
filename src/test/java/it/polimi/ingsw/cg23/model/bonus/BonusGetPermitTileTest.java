package it.polimi.ingsw.cg23.model.bonus;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.bonus.BonusGetPermitTile;
import it.polimi.ingsw.cg23.server.model.components.BonusKing;
import it.polimi.ingsw.cg23.server.model.components.BusinessPermitTile;
import it.polimi.ingsw.cg23.server.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.server.model.components.RegionDeck;

public class BonusGetPermitTileTest {

	private Board board;
	private Region region;
	private BusinessPermitTile tile;
	private Player player;
	
	@Before
	public void setUp() throws Exception {
		List<Integer> king = new ArrayList<>();
		king.add(2);
		this.region = new Region("Costa", 0, new RegionDeck(1), new BonusKing(king));
		List<Region> regions = new ArrayList<>();
		regions.add(region);
		this.board = new Board(null, regions, null, null, null, null);
		List<Character> citiesId = new ArrayList<>();
		citiesId.add('R');
		this.tile = new BusinessPermitTile(citiesId, "Costa");
		this.region.getDeck().getShowedDeck().add(tile);
		this.player = new Player("user", new NobilityTrack(3));
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
	 * it tests if giveBonus add the tile to the player's tiles
	 */
	@Test
	public void testGiveBonusShouldAddThePermitTileInThePlayersTile(){
		BonusGetPermitTile bonus = new BonusGetPermitTile(0, board, tile, region);
		bonus.giveBonus(player);
		assertEquals(this.tile, this.player.getAvailableBusinessPermits().get(0));
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
		BonusGetPermitTile newBonus = (BonusGetPermitTile) bonus.copy();
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
