package it.polimi.ingsw.cg23;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.City;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.Region;
import it.polimi.ingsw.cg23.model.Type;
import it.polimi.ingsw.cg23.model.components.Councillor;
import it.polimi.ingsw.cg23.model.components.Deck;
import it.polimi.ingsw.cg23.model.components.King;
import it.polimi.ingsw.cg23.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.model.components.PoliticCard;

public class BoardTest {
	
	private Player p;
	private Region region;
	private Type type;
	private King king;
	private Deck deck;
	private NobilityTrack nobilityTrack;
	
	@Before
	public void setUp() throws Exception {
		p=new Player("user1", 0, 0, null);
		region=new Region("region1", 0, null, null);
		type=new Type("type1", 0, null);
		deck=new Deck(new ArrayList<PoliticCard>());
		king=new King(new City('A', "Aosta", type, region));
		nobilityTrack=new NobilityTrack(1);
	}

	@Test
	public void testDeck() {
		Board board=new Board(null, new ArrayList<>(), new ArrayList<>(), null, null);
		assertNull(board.getDeck());
		board.setDeck(deck);
		assertEquals(board.getDeck(),deck);
	}

	@Test
	public void testTypes() {
		Board board=new Board(null, new ArrayList<>(), new ArrayList<>(), null, null);
		assertEquals(board.getTypes().size(),0);
		List<Type> types=new ArrayList<>();
		types.add(type);
		board.setTypes(types);
		assertEquals(board.getTypes().size(),1);
	}

	@Test
	public void testRegions() {
		Board board=new Board(null, new ArrayList<>(), new ArrayList<>(), null, null);
		assertEquals(board.getRegions().size(),0);
		List<Region> regions=new ArrayList<>();
		regions.add(region);
		board.setRegions(regions);
		assertEquals(board.getRegions().size(),1);
	}


	@Test
	public void testNobilityTrack() {
		Board board=new Board(null, new ArrayList<>(), new ArrayList<>(), null, null);
		assertNull(board.getNobilityTrack());
		board.setNobilityTrack(nobilityTrack);
		assertNotNull(board.getNobilityTrack());
	}

	@Test
	public void testKing() {
		Board board=new Board(null, new ArrayList<>(), new ArrayList<>(), null, null);
		assertNull(board.getKing());
		board.setKing(king);
		assertNotNull(board.getKing());
	}

	@Test
	public void testCouncillor() {
		Board board=new Board(null, new ArrayList<>(), new ArrayList<>(), null, null);
		assertEquals(board.getCouncillorPool().size(),0);
		assertNull(board.getCouncillor(Color.BLACK));
		board.getCouncillorPool().add(new Councillor(Color.BLACK));
		assertNotNull(board.getCouncillor(Color.BLACK));
		assertEquals(board.getCouncillorPool().size(),0);
	}

	@Test
	public void testPlayers() {
		Board board=new Board(null, new ArrayList<>(), new ArrayList<>(), null, null);
		assertEquals(board.getPlayers().size(),0);
		board.addPlayer(p);
		assertEquals(board.getPlayers().size(),1);		
	}

	@Test
	public void testGetStatus() {
		Board board=new Board(null, new ArrayList<>(), new ArrayList<>(), null, null);
		assertEquals(board.getStatus().getStatus(),"TURN");
		board.changeStatus();
		assertEquals(board.getStatus().getStatus(),"MARKET: SELLING");
		board.changeStatus();
		assertEquals(board.getStatus().getStatus(),"MARKET: BUYING");
		board.changeStatus();
		assertEquals(board.getStatus().getStatus(),"TURN");
		board.getStatus().setFinalPlayer(p);
		board.changeStatus();
		assertEquals(board.getStatus().getStatus(),"FINAL TURN");
		
	}
	
	@Test
	public void testMarket() {
		Board board=new Board(null, new ArrayList<>(), new ArrayList<>(), null, null);
		assertNotNull(board.getMarket());
		
	}

}
