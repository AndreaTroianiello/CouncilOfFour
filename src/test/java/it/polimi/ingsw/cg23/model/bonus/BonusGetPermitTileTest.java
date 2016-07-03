package it.polimi.ingsw.cg23.model.bonus;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.bonus.BonusAdditionalAction;
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
	private List<Character> citiesId;
	
	@Before
	public void setUp() throws Exception {
		List<Integer> king = new ArrayList<>();
		king.add(2);
		this.region = new Region("Costa", 0, new RegionDeck(1), new BonusKing(king));
		List<Region> regions = new ArrayList<>();
		regions.add(region);
		this.board = new Board(null, regions, null, null, null, null);
		this.citiesId = new ArrayList<>();
		this.citiesId.add('R');
		this.tile = new BusinessPermitTile(citiesId, "Costa");
		this.tile.addBonus(new BonusAdditionalAction());
		this.region.getDeck().getShowedDeck().add(tile);
		this.player = new Player("user", new NobilityTrack(3));
	}

	/**
	 * it tests if getName works properly
	 */
	@Test
	public void testGetName() {
		BonusGetPermitTile bonus = new BonusGetPermitTile();
		assertEquals("1GetPermitTile", bonus.getName());
	}
	
	/**
	 * it tests if giveBonus add the tile to the player's tiles
	 */
	@Test
	public void testGiveBonusShouldAddThePermitTileInThePlayersTile(){
		BonusGetPermitTile bonus = new BonusGetPermitTile();
		bonus.giveBonus(player);
		assertTrue(this.player.getAvailableBusinessPermits().isEmpty());
		bonus.setBoard(board);
		bonus.setTile(region, 0);
		bonus.giveBonus(player);
		assertEquals(this.tile, this.player.getAvailableBusinessPermits().get(0));
	}
	
	/**
	 * it tests if it doesn't give the tile if the showed deck of the region is empty
	 */
	@Test
	public void testGiveBonusShouldntAddThePermitIfTheShowedDeckIsEmpty(){
		BonusGetPermitTile bonus = new BonusGetPermitTile();
		bonus.setBoard(board);
		this.region.getDeck().getShowedDeck().clear();
		bonus.setTile(region, 0);
		bonus.giveBonus(player);
		assertTrue(this.player.getAvailableBusinessPermits().isEmpty());
	}

	/**
	 * it tests if it doesn't give the bonus when the region is not in the board
	 */
	@Test
	public void testGiveBonusShouldntAddTheCardIfTheRegionIsNotInTheBoard(){
		BonusGetPermitTile bonus = new BonusGetPermitTile();
		bonus.setBoard(board);
		Region nullRegion = new Region("Ciao", 0, null, null);
		bonus.setTile(nullRegion, 0);
		bonus.giveBonus(player);
		assertTrue(this.player.getAvailableBusinessPermits().isEmpty());
	}
	/**
	 * it tests if toString works properly
	 */
	@Test
	public void testToString() {
		BonusGetPermitTile bonus = new BonusGetPermitTile();
		bonus.getNumber();
		assertEquals("BonusGetPermitTile", bonus.toString());
	}

	/**
	 * it tests if clone works properly
	 */
	@Test
	public void testClone() {
		BonusGetPermitTile bonus = new BonusGetPermitTile();
		BonusGetPermitTile newBonus = (BonusGetPermitTile) bonus.copy();
		assertEquals(bonus.getName(), newBonus.getName());
	}
}
