package it.polimi.ingsw.cg23.model.action;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.components.AssistantsPool;
import it.polimi.ingsw.cg23.model.components.BusinessPermitTile;
import it.polimi.ingsw.cg23.model.components.PoliticCard;

public class MarketSellTest {

	private Player player;
	private AssistantsPool assistants;
	private List <BusinessPermitTile> tiles;
	private List<PoliticCard> cards;
	
	@Before
	public void setUp() throws Exception {
		player=new Player("user",10,100,null);
		assistants=new AssistantsPool(9);
		cards=Arrays.asList(new PoliticCard(Color.BLACK, false),
							new PoliticCard(Color.BLACK, false),
							new PoliticCard(Color.RED, false),
							new PoliticCard(null, true));
		List<Character> id1=Arrays.asList('A','B','C');
		List<Character> id2=Arrays.asList('A','B');
		List<Character> id3=Arrays.asList('A','C','D');
		List<Character> id4=Arrays.asList('X','Y','Z');
		List<Character> id5=Arrays.asList('A','Y');
		tiles=Arrays.asList(new BusinessPermitTile(id1, "regione"),
							new BusinessPermitTile(id2, "regione"),
							new BusinessPermitTile(id3, "regione"),
							new BusinessPermitTile(id4, "regione"),
							new BusinessPermitTile(id5, "regione"));
		player.getHand().addAll(cards);
		player.getAvailableBusinessPermits().addAll(tiles);
	}

	@Test
	public void testMarketSell() {
		Board board=new Board(null, null, null, null, null);
		new MarketSell(tiles.get(2), 10).runAction(player, board);
		new MarketSell(cards.get(1), 10).runAction(player, board);
		new MarketSell(cards.get(3), 10).runAction(player, board);
		new MarketSell(cards.get(3), 10).runAction(player, board);
		new MarketSell(cards.get(1), 10).runAction(player, board);
		new MarketSell(assistants, 10).runAction(player, board);
		assertEquals(board.getMarket().getItems().size(), 5);
		assertEquals(player.getAvailableBusinessPermits().size(), 4);
		assertEquals(player.getHand().size(), 1);
		assertEquals(player.getAssistantsPool().getAssistants(), 1);
	}

}
