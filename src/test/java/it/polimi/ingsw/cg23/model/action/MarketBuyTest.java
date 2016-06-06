package it.polimi.ingsw.cg23.model.action;

import static org.junit.Assert.*;

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
import it.polimi.ingsw.cg23.server.model.components.PoliticCard;
import it.polimi.ingsw.cg23.server.model.marketplace.Item;
import it.polimi.ingsw.cg23.server.model.marketplace.Market;

public class MarketBuyTest {

	private Player player1,player2,player3;
	private List <BusinessPermitTile> tiles;
	private List<PoliticCard> cards;
	private Board board;

	@Before
	public void setUp() throws Exception {
		player1=new Player("user1",10,100,null);
		player2=new Player("user2",10,100,null);
		player3=new Player("user3",10,100,null);
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
		board=new Board(null,null,null,null,null);
		Market market=board.getMarket();
		market.addItemToSell(new Item(cards.get(0), player1, 5));
		market.addItemToSell(new Item(cards.get(3), player2, 5));
		market.addItemToSell(new Item(tiles.get(1), player1, 5));
		market.addItemToSell(new Item(cards.get(2), player3, 10));
		market.addItemToSell(new Item(new AssistantsPool(10), player1, 15));
	}

	@Test
	public void testMarketBuy() {
		assertTrue(board.getMarket().getItems().size()==5);

		//First action. Buy a normal card.
		GameAction action=new MarketBuy(new Item(new PoliticCard(Color.BLACK, false),
				new Player("user1",10,100,null),
				5));
		action.runAction(player2, board);
		assertTrue(player2.getHand().size()==1);
		assertTrue(player2.getRichness().getCoins()==95);
		assertTrue(player1.getRichness().getCoins()==105);
		assertTrue(board.getMarket().getItems().size()==4);

		//Second action. Buy a jolly.
		action=new MarketBuy(new Item(new PoliticCard(null, true),
				new Player("user2",10,100,null),
				5));
		action.runAction(player1, board);
		assertTrue(player1.getHand().size()==1);
		assertTrue(player2.getRichness().getCoins()==100);
		assertTrue(player1.getRichness().getCoins()==100);
		assertTrue(board.getMarket().getItems().size()==3);

		//Third action. Buy nothing.
		List<Character> ids=Arrays.asList('A','B','C');
		action=new MarketBuy(new Item(new BusinessPermitTile(ids,"regione"),
				new Player("user1",10,100,null),
				15));
		action.runAction(player2, board);
		assertTrue(player2.getAvailableBusinessPermits().size()==0);
		assertTrue(player1.getRichness().getCoins()==100);
		assertTrue(player2.getRichness().getCoins()==100);
		assertTrue(board.getMarket().getItems().size()==3);

		//Fourth action. Buy assistants.(nothing)
		action=new MarketBuy(new Item(new AssistantsPool(15),
				new Player("user1",10,100,null),
				15));
		action.runAction(player3, board);
		assertTrue(player3.getAssistantsPool().getAssistants()==10);
		assertTrue(player1.getRichness().getCoins()==100);
		assertTrue(player3.getRichness().getCoins()==100);
		assertTrue(board.getMarket().getItems().size()==3);

		//Fifth action. Buy assistants.
		action=new MarketBuy(new Item(new AssistantsPool(10),
				new Player("user1",10,100,null),
				15));
		action.runAction(player3, board);
		assertTrue(player3.getAssistantsPool().getAssistants()==20);
		assertTrue(player1.getRichness().getCoins()==115);
		assertTrue(player3.getRichness().getCoins()==85);
		assertTrue(board.getMarket().getItems().size()==2);

		//Sixth action. Buy the same assistants.(nothing)
		action=new MarketBuy(new Item(new AssistantsPool(10),
				new Player("user1",10,100,null),
				15));
		action.runAction(player3, board);
		assertTrue(player3.getAssistantsPool().getAssistants()==20);
		assertTrue(player1.getRichness().getCoins()==115);
		assertTrue(player3.getRichness().getCoins()==85);
		assertTrue(board.getMarket().getItems().size()==2);


		//Seventh action. Buy the tile.
		ids=Arrays.asList('A','B');
		action=new MarketBuy(new Item(new BusinessPermitTile(ids,"regione"),
				new Player("user1",10,100,null),
				15));
		action.runAction(player2, board);
		assertTrue(player2.getAvailableBusinessPermits().size()==1);
		assertTrue(player1.getRichness().getCoins()==120);
		assertTrue(player2.getRichness().getCoins()==95);
		assertTrue(board.getMarket().getItems().size()==1);

		//Eighth action. Buy nothing.
		action=new MarketBuy(new Item(new PoliticCard(Color.RED,false),
				new Player("user1",10,100,null),
				15));
		action.runAction(player2, board);
		assertTrue(player2.getAvailableBusinessPermits().size()==1);
		assertTrue(player1.getRichness().getCoins()==120);
		assertTrue(player2.getRichness().getCoins()==95);
		assertTrue(board.getMarket().getItems().size()==1);
		
		//Ninth action. Buy nothing.
		ids=Arrays.asList('A','B');
		action=new MarketBuy(new Item(new BusinessPermitTile(ids,"regione"),
				new Player("user1",10,100,null),
				15));
		action.runAction(player2, board);
		assertTrue(player2.getAvailableBusinessPermits().size()==1);
		assertTrue(player1.getRichness().getCoins()==120);
		assertTrue(player2.getRichness().getCoins()==95);
		assertTrue(board.getMarket().getItems().size()==1);
	}	
}
