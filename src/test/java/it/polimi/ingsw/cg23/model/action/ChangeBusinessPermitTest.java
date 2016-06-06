package it.polimi.ingsw.cg23.model.action;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.action.ChangeBusinessPermit;
import it.polimi.ingsw.cg23.server.model.components.BusinessPermitTile;
import it.polimi.ingsw.cg23.server.model.components.Deck;
import it.polimi.ingsw.cg23.server.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.server.model.components.PoliticCard;
import it.polimi.ingsw.cg23.server.model.components.RegionDeck;
import it.polimi.ingsw.cg23.server.model.exception.NegativeNumberException;

public class ChangeBusinessPermitTest {

	private Region region;
	private Player player;
	private Board board;
	private List<BusinessPermitTile> deckCards = new ArrayList<>();
	private List<BusinessPermitTile> showedDeck = new ArrayList<>();
	List<Character> cities = new ArrayList<>();

	@Before
	public void setUp() throws Exception {
		BusinessPermitTile card1 = new BusinessPermitTile(new ArrayList<>(), "si");
		BusinessPermitTile card2 = new BusinessPermitTile(new ArrayList<>(), "no");
		cities.add('I');
		BusinessPermitTile card3 = new BusinessPermitTile(cities, "forse");
		BusinessPermitTile card4 = new BusinessPermitTile(cities, "boh");
		deckCards.add(card1);
		deckCards.add(card2);
		deckCards.add(card3);
		deckCards.add(card4);
		player = new Player("player1", 10, 10, new NobilityTrack(3));
		List<Region> regions = new ArrayList<>();
		RegionDeck deck = new RegionDeck(2);
		deck.setBusinessPermitTiles(deckCards);
		showedDeck.addAll(deck.getShowedDeck());
		region = new Region(null, 0, deck, null);
		regions.add(region);
		board = new Board(new Deck(new ArrayList<PoliticCard>()), regions, null, null, null);

	}

	/**
	 * it tests if runAction() changes the showed cards when the player has enough assistants
	 */
	@Test
	public void testRunActionShouldChangeTheShowedDeckWhenTiIsAllFine() {
		ChangeBusinessPermit action = new ChangeBusinessPermit(region);
		action.runAction(player, board);
		assertNotEquals(showedDeck, region.getDeck().getShowedDeck());
	}
	
	/**
	 * it tests if runAction() doesn't change the showed cards when the player hasn't enough assistants
	 * @throws NegativeNumberException 
	 */
	@Test
	public void testRunAction() throws NegativeNumberException {
		ChangeBusinessPermit action = new ChangeBusinessPermit(region);
		player.getAssistantsPool().setAssistants(0);
		action.runAction(player, board);
		assertEquals(showedDeck, board.getRegions().get(0).getDeck().getShowedDeck());
	}


	/**
	 * it tests if getRegion() works properly
	 */
	@Test
	public void testGetRegion() {
		ChangeBusinessPermit action = new ChangeBusinessPermit(region);
		Region actualRegion = action.getRegion();
		assertEquals(region, actualRegion);
	}

	/**
	 * it tests if toString() works properly
	 */
	@Test
	public void testToString() {
		ChangeBusinessPermit action = new ChangeBusinessPermit(null);
		assertEquals("ChangeBusinessPermit [region=null]", action.toString());
	}

}
