package it.polimi.ingsw.cg23;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.components.PoliticCard;
import it.polimi.ingsw.cg23.model.marketplace.Item;
import it.polimi.ingsw.cg23.model.marketplace.Market;

public class MarketTest {

	private Item item;
	private List<Player> players;
	@Before
	public void setUp() throws Exception {
		item=new Item(new PoliticCard(Color.BLACK,false), new Player("ciao",0,0,null), 10);
		players=Arrays.asList(new Player("user1",0,0,null),new Player("user2",0,0,null),new Player("user3",0,0,null));
	}

	@Test
	public void testMarket() {
		Market market=new Market();
		assertEquals(market.getItems().size(), 0);
		market.addItemToSell(item);
		assertEquals(market.getItems().size(), 1);
		market.resetItems();
		assertEquals(market.getItems().size(), 0);
	}

	@Test
	public void testPlayers(){
		Market market=new Market();
		List<Player> newPlayers=market.generatePlayersList(players);
		assertEquals(newPlayers.size(), players.size());
		assertTrue(newPlayers.containsAll(players));
	}
}
