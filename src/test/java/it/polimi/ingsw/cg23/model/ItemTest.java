package it.polimi.ingsw.cg23.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.components.AssistantsPool;
import it.polimi.ingsw.cg23.server.model.components.BusinessPermitTile;
import it.polimi.ingsw.cg23.server.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.server.model.components.PoliticCard;
import it.polimi.ingsw.cg23.server.model.marketplace.Item;

public class ItemTest {

	private PoliticCard card;
	private AssistantsPool assistants;
	private BusinessPermitTile tile;
	private Player p;
	
	@Before
	public void setUp() throws Exception {
		card=new PoliticCard(null, false);
		assistants=new AssistantsPool();
		assistants.setAssistants(10);
		tile=new BusinessPermitTile(null,"");
		p=new Player("ciao", new NobilityTrack(1));
	}

	@Test
	public void testItem() {
		Item item1=new Item(card, p, 10);
		Item item2=new Item(assistants, p, 5);
		Item item3=new Item(tile, p, 3);
		assertTrue(item1.getItem() instanceof PoliticCard);
		assertTrue(item2.getItem() instanceof AssistantsPool);
		assertTrue(item3.getItem() instanceof BusinessPermitTile);
		assertEquals(item1.getCoins(),10);
		assertEquals(item3.getPlayer(),p);
	}
	
	@Test
	public void testToString(){
		Item item1=new Item(card, p, 10);
		assertEquals(item1.toString(),"Item [coins=" + 10 + ", itemToSell=" + card.toString() + ", player=" + p.getUser() + "]");

	}
}
