package it.polimi.ingsw.cg23.model.action;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.Region;
import it.polimi.ingsw.cg23.model.components.Deck;
import it.polimi.ingsw.cg23.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.model.components.PoliticCard;
import it.polimi.ingsw.cg23.model.components.RegionDeck;

public class ChangeBusinessPermitTest {

	private int number;
	private Player player;
	private Board board;

	@Before
	public void setUp() throws Exception {
		number = 0;
		player = new Player("player1", 10, 10, new NobilityTrack(3));
		List<Region> regions = new ArrayList<>();
		RegionDeck deck = new RegionDeck(2);
		//deck.
		Region region = new Region(null, 0, deck, null);
		regions.add(region);
		board = new Board(new Deck(new ArrayList<PoliticCard>()), regions, null, null, null);

	}

	@Test
	public void testRunAction() {
		ChangeBusinessPermit action = new ChangeBusinessPermit(number);
		action.runAction(player, board);
	}


	/**
	 * it tests if getRegion() works properly
	 */
	@Test
	public void testGetRegion() {
		ChangeBusinessPermit action = new ChangeBusinessPermit(number);
		int actualRegion = action.getRegion();
		assertEquals(number, actualRegion);
	}

	/**
	 * it tests if toString() works properly
	 */
	@Test
	public void testToString() {
		ChangeBusinessPermit action = new ChangeBusinessPermit(0);
		assertEquals("ChangeBusinessPermit [region=0]", action.toString());
	}

}
