package it.polimi.ingsw.cg23.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.components.AssistantsPool;
import it.polimi.ingsw.cg23.server.model.components.BusinessPermitTile;
import it.polimi.ingsw.cg23.server.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.server.model.components.PoliticCard;
import it.polimi.ingsw.cg23.server.model.exception.NegativeNumberException;
import it.polimi.ingsw.cg23.server.model.marketplace.Item;
import it.polimi.ingsw.cg23.server.model.marketplace.Market;

public class MarketTest {

	private Item item1, item2, item3;
	private List<Player> players;

	@Before
	public void setUp() throws Exception {
		Player player = new Player("ciao", new NobilityTrack(1));
		item1 = new Item(new PoliticCard(Color.BLACK, false), player, 10);
		List<Character> id = Arrays.asList('A', 'B', 'C');
		item2 = new Item(new BusinessPermitTile(id, "regione"), player, 5);
		AssistantsPool pool = new AssistantsPool();
		pool.setAssistants(10);
		item3 = new Item(pool, player, 5);
		players = Arrays.asList(new Player("user1", new NobilityTrack(1)), new Player("user2", new NobilityTrack(1)),
				new Player("user3", new NobilityTrack(1)));
	}

	/**
	 * it tests if getItems returns a list with the item in the market, and if
	 * addItemToSell add an item in the market
	 * 
	 * @throws NegativeNumberException
	 */
	@Test
	public void testGetItemsAndAddItemToSellShouldAddAnItemInTheMarket() throws NegativeNumberException {
		Market market = new Market();
		assertEquals(market.getItems().size(), 0);
		market.addItemToSell(item1);
		assertEquals(market.getItems().size(), 1);
		market.addItemToSell(item2);
		assertEquals(market.getItems().size(), 2);
		market.addItemToSell(item3);
		assertEquals(market.getItems().size(), 3);
		market.resetItems();
		assertEquals(market.getItems().size(), 0);
		assertEquals(item1.getPlayer().getAvailableBusinessPermits().size(), 1);
		assertEquals(item2.getPlayer().getHand().size(), 1);
		assertEquals(item3.getPlayer().getAssistantsPool().getAssistants(), 10);
	}

	/**
	 * it tests if generatePlayersList returns a list with all the players
	 */
	@Test
	public void testGeneratePlayersListShouldReturnAListWithAllThePlayers() {
		Market market = new Market();
		List<Player> newPlayers = market.generatePlayersList(players);
		assertEquals(newPlayers.size(), players.size());
		assertTrue(newPlayers.containsAll(players));
	}
}
