package it.polimi.ingsw.cg23.controller.change;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.controller.change.ItemChange;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.server.model.components.PoliticCard;
import it.polimi.ingsw.cg23.server.model.marketplace.Item;

public class ItemChangeTest {

	@Before
	public void setUp() throws Exception {
	}

	/**
	 * it tests if toString works properly
	 */
	@Test
	public void testToString() {
		ItemChange change=new ItemChange(new Item(new PoliticCard(null,true), new Player("user",new NobilityTrack(1)), 5));
		assertEquals(change.toString(),"ItemChange [Item=Item [coins=5, itemToSell=PoliticCard [jolly=true], player=user]]");
	}

}
