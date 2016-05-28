package it.polimi.ingsw.cg23;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.components.PoliticCard;
import it.polimi.ingsw.cg23.model.marketplace.Item;
import it.polimi.ingsw.cg23.model.marketplace.Market;

public class MarketTest {

	private Item item;
	@Before
	public void setUp() throws Exception {
		item=new Item(new PoliticCard(Color.BLACK,false), new Player("ciao",0,0,null), 10);
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

}
