package it.polimi.ingsw.cg23.model.action;

import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.action.GameAction;
import it.polimi.ingsw.cg23.server.model.action.MarketBuy;
import it.polimi.ingsw.cg23.server.model.components.AssistantsPool;
import it.polimi.ingsw.cg23.server.model.components.BusinessPermitTile;
import it.polimi.ingsw.cg23.server.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.server.model.components.PoliticCard;
import it.polimi.ingsw.cg23.server.model.exception.NegativeNumberException;
import it.polimi.ingsw.cg23.server.model.marketplace.Item;
import it.polimi.ingsw.cg23.server.model.marketplace.Market;

public class MarketBuyTest {

	private Player player1, player2, player3;
	private List<BusinessPermitTile> tiles;
	private List<PoliticCard> cards;
	private Board board;

	@Before
	public void setUp() throws Exception {
		player1 = new Player("user1", new NobilityTrack(1));
		player2 = new Player("user2", new NobilityTrack(1));
		player3 = new Player("user3", new NobilityTrack(1));
		player1.getAssistantsPool().setAssistants(10);
		player2.getAssistantsPool().setAssistants(10);
		player3.getAssistantsPool().setAssistants(10);
		player1.getRichness().setCoins(100);
		player2.getRichness().setCoins(100);
		player3.getRichness().setCoins(100);
		cards = Arrays.asList(new PoliticCard(Color.BLACK, false), new PoliticCard(Color.BLACK, false),
				new PoliticCard(Color.RED, false), new PoliticCard(null, true));
		List<Character> id1 = Arrays.asList('A', 'B', 'C');
		List<Character> id2 = Arrays.asList('A', 'B');
		List<Character> id3 = Arrays.asList('A', 'C', 'D');
		List<Character> id4 = Arrays.asList('X', 'Y', 'Z');
		List<Character> id5 = Arrays.asList('A', 'Y');
		tiles = Arrays.asList(new BusinessPermitTile(id1, "regione"), new BusinessPermitTile(id2, "regione"),
				new BusinessPermitTile(id3, "regione"), new BusinessPermitTile(id4, "regione"),
				new BusinessPermitTile(id5, "regione"));
		board = new Board(null, null, null, null, null, null);
		Market market = board.getMarket();
		market.addItemToSell(new Item(cards.get(0), player1, 5));
		market.addItemToSell(new Item(cards.get(3), player2, 5));
		market.addItemToSell(new Item(tiles.get(1), player1, 5));
		market.addItemToSell(new Item(cards.get(2), player3, 10));
		AssistantsPool pool = new AssistantsPool();
		pool.setAssistants(10);
		market.addItemToSell(new Item(pool, player1, 15));
	}

	@Test
	public void testMarketBuy() throws NegativeNumberException {
		assertTrue(board.getMarket().getItems().size() == 5);
		List<Player> falsePlayers = Arrays.asList(new Player("user1", new NobilityTrack(1)),
				new Player("user2", new NobilityTrack(1)), new Player("user3", new NobilityTrack(1)));
		for (Player player : falsePlayers) {
			player.getAssistantsPool().setAssistants(10);
			player.getRichness().setCoins(100);
		}
		// First action. Buy a normal card.
		GameAction action = new MarketBuy(new Item(new PoliticCard(Color.BLACK, false), falsePlayers.get(0), 5));
		action.runAction(player2, board);
		assertTrue(player2.getHand().size() == 1);
		assertTrue(player2.getRichness().getCoins() == 95);
		assertTrue(player1.getRichness().getCoins() == 105);
		assertTrue(board.getMarket().getItems().size() == 4);

		// Second action. Buy a jolly.
		action = new MarketBuy(new Item(new PoliticCard(null, true), falsePlayers.get(1), 5));
		action.runAction(player1, board);
		assertTrue(player1.getHand().size() == 1);
		assertTrue(player2.getRichness().getCoins() == 100);
		assertTrue(player1.getRichness().getCoins() == 100);
		assertTrue(board.getMarket().getItems().size() == 3);

		// Third action. Buy nothing.
		List<Character> ids = Arrays.asList('A', 'B', 'C');
		action = new MarketBuy(new Item(new BusinessPermitTile(ids, "regione"), falsePlayers.get(0), 15));
		action.runAction(player2, board);
		assertTrue(player2.getAvailableBusinessPermits().size() == 0);
		assertTrue(player1.getRichness().getCoins() == 100);
		assertTrue(player2.getRichness().getCoins() == 100);
		assertTrue(board.getMarket().getItems().size() == 3);

		// Fourth action. Buy assistants.(nothing)
		AssistantsPool pool1 = new AssistantsPool();
		pool1.setAssistants(15);
		action = new MarketBuy(new Item(pool1, falsePlayers.get(0), 15));
		action.runAction(player3, board);
		assertTrue(player3.getAssistantsPool().getAssistants() == 10);
		assertTrue(player1.getRichness().getCoins() == 100);
		assertTrue(player3.getRichness().getCoins() == 100);
		assertTrue(board.getMarket().getItems().size() == 3);

		// Fifth action. Buy assistants.
		AssistantsPool pool2 = new AssistantsPool();
		pool2.setAssistants(10);
		action = new MarketBuy(new Item(pool2, falsePlayers.get(0), 15));
		action.runAction(player3, board);
		assertTrue(player3.getAssistantsPool().getAssistants() == 20);
		assertTrue(player1.getRichness().getCoins() == 115);
		assertTrue(player3.getRichness().getCoins() == 85);
		assertTrue(board.getMarket().getItems().size() == 2);

		// Sixth action. Buy the same assistants.(nothing)
		AssistantsPool pool3 = new AssistantsPool();
		pool3.setAssistants(10);
		action = new MarketBuy(new Item(pool3, falsePlayers.get(0), 15));
		action.runAction(player3, board);
		assertTrue(player3.getAssistantsPool().getAssistants() == 20);
		assertTrue(player1.getRichness().getCoins() == 115);
		assertTrue(player3.getRichness().getCoins() == 85);
		assertTrue(board.getMarket().getItems().size() == 2);

		// Seventh action. Buy the tile.
		ids = Arrays.asList('A', 'B');
		action = new MarketBuy(new Item(new BusinessPermitTile(ids, "regione"), falsePlayers.get(0), 15));
		action.runAction(player2, board);
		assertTrue(player2.getAvailableBusinessPermits().size() == 1);
		assertTrue(player1.getRichness().getCoins() == 120);
		assertTrue(player2.getRichness().getCoins() == 95);
		assertTrue(board.getMarket().getItems().size() == 1);

		// Eighth action. Buy nothing.
		action = new MarketBuy(new Item(new PoliticCard(Color.RED, false), falsePlayers.get(0), 15));
		action.runAction(player2, board);
		assertTrue(player2.getAvailableBusinessPermits().size() == 1);
		assertTrue(player1.getRichness().getCoins() == 120);
		assertTrue(player2.getRichness().getCoins() == 95);
		assertTrue(board.getMarket().getItems().size() == 1);

		// Ninth action. Buy nothing.
		ids = Arrays.asList('A', 'B');
		action = new MarketBuy(new Item(new BusinessPermitTile(ids, "regione"), falsePlayers.get(0), 15));
		action.runAction(player2, board);
		assertTrue(player2.getAvailableBusinessPermits().size() == 1);
		assertTrue(player1.getRichness().getCoins() == 120);
		assertTrue(player2.getRichness().getCoins() == 95);
		assertTrue(board.getMarket().getItems().size() == 1);
	}
}
