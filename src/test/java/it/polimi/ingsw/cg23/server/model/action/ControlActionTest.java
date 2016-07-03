package it.polimi.ingsw.cg23.server.model.action;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.components.BusinessPermitTile;
import it.polimi.ingsw.cg23.server.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.server.model.components.PoliticCard;
import it.polimi.ingsw.cg23.server.model.components.RegionDeck;

public class ControlActionTest {

	private Board board;
	private Region region;
	private Player player;
	
	@Before
	public void setUp() throws Exception {
		List<Region> regions = new ArrayList<>();
		this.region = new Region("Costa", 0, new RegionDeck(1), null);
		regions.add(region);
		this.board = new Board(null, regions, null, null, null, null);
		this.player = new Player("user1", new NobilityTrack(1));
		this.player.getHand().add(new PoliticCard(Color.RED, false));
		this.player.getHand().add(new PoliticCard(null, true));
	}

	/**
	 * it tests if controlRegion return null if the argument is null and if the region isn't in the board,
	 * and the region when it is
	 */
	@Test
	public void testControlRegion() {
		Region region2 = new Region("Mare", 0, null, null);
		ControlAction control = new ControlAction();
		assertNull(control.controlRegion(null, this.board));
		assertNull(control.controlRegion(region2, this.board));
		assertEquals(this.region, control.controlRegion(this.region, this.board));
	}

	/**
	 * it tests if the method returns null when the player doesn't have the chosen cards, and if it
	 * returns the chosen cards when the player has them 
	 */
	@Test
	public void testControlPoliticCards() {
		ControlAction control = new ControlAction();
		List<PoliticCard> cards = new ArrayList<>();
		cards.add(new PoliticCard(Color.ORANGE, false));
		assertNull(control.controlPoliticCards(cards, player));
		cards.remove(0);
		cards.add(new PoliticCard(Color.RED, false));
		cards.add(new PoliticCard(null, true));
		assertEquals(this.player.getHand(), control.controlPoliticCards(cards, player));
		
	}

	/**
	 * it tests if the method returns null when the player doesn't have the chosen tile, and if it returns the
	 * tile when he has it
	 */
	@Test
	public void testControlBusinessPermit() {
		ControlAction control = new ControlAction();
		List<Character> citiesId = new ArrayList<>();
		citiesId.add('I');
		BusinessPermitTile tile = new BusinessPermitTile(citiesId, "Costa");
		assertNull(control.controlBusinessPermit(tile, player));
		this.player.addAvailableBusinessPermit(tile);
		assertEquals(this.player.getAvailableBusinessPermits().get(0), control.controlBusinessPermit(tile, player));
	}
	
	/**
	 * it tests if the method returns null when the region doesn't have the chosen tile, and if it returns 
	 * the tile when it has it
	 */
	@Test
	public void testControlBusinessPermitRegion() {
		ControlAction control = new ControlAction();
		List<Character> citiesId = new ArrayList<>();
		citiesId.add('I');
		BusinessPermitTile tile = new BusinessPermitTile(citiesId, "Costa");
		BusinessPermitTile tile2 = new BusinessPermitTile(citiesId, "Mare");
		this.region.getDeck().getShowedDeck().add(tile);
		assertNull(control.controlBusinessPermitRegion(tile2, region));
		assertEquals(this.region.getDeck().getShowedDeck().get(0), control.controlBusinessPermitRegion(tile, region));
	}

}
