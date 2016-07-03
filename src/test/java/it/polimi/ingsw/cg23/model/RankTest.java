package it.polimi.ingsw.cg23.model;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.controller.Setting;
import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.Rank;
import it.polimi.ingsw.cg23.server.model.components.NobilityBox;
import it.polimi.ingsw.cg23.server.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.server.model.components.PoliticCard;
import it.polimi.ingsw.cg23.server.model.exception.NegativeNumberException;

public class RankTest {
	
	private Board board;
	
	@Before
	public void setUp() throws Exception {
		board=new Board(null, null, null, new NobilityTrack(21), null, null);
		Setting setting=new Setting();
		setting.nobilityTrackFill(board.getNobilityTrack());
	}

	/**
	 * it tests if createRank creates the list of the top players properly
	 */
	@Test
	public void testRank1() {
		Rank rank=new Rank(board);
		board.addPlayer(new Player("user1",board.getNobilityTrack()));
		board.addPlayer(new Player("user2",board.getNobilityTrack()));
		board.addPlayer(new Player("user3",board.getNobilityTrack()));
		List<NobilityBox> boxes=board.getNobilityTrack().getNobilityBoxes();
		boxes.get(3).addPlayer(board.getPlayers().get(1));
		boxes.get(5).addPlayer(board.getPlayers().get(0));
		boxes.get(8).addPlayer(board.getPlayers().get(2));
		rank.createRank();
		assertEquals(board.getPlayers().get(0).getUser(),"user3");
		assertEquals(board.getPlayers().get(1).getUser(),"user1");
		assertEquals(board.getPlayers().get(2).getUser(),"user2");	
	}
	
	/**
	 * it tests if createRank creates the list of the top players properly
	 */
	@Test
	public void testRank2() throws NegativeNumberException {
		Rank rank=new Rank(board);
		board.addPlayer(new Player("user1",board.getNobilityTrack()));
		board.addPlayer(new Player("user2",board.getNobilityTrack()));
		board.addPlayer(new Player("user3",board.getNobilityTrack()));
		board.addPlayer(new Player("user4",board.getNobilityTrack()));
		board.getStatus().setFinalPlayer(board.getPlayers().get(3));
		board.getPlayers().get(3).getAssistantsPool().setAssistants(10);
		board.getPlayers().get(3).addPoliticCard(new PoliticCard(null, true));
		board.getPlayers().get(2).addPoliticCard(new PoliticCard(null, true));
		List<NobilityBox> boxes=board.getNobilityTrack().getNobilityBoxes();
		boxes.get(5).addPlayer(board.getPlayers().get(1));
		boxes.get(5).addPlayer(board.getPlayers().get(0));
		boxes.get(8).addPlayer(board.getPlayers().get(2));
		boxes.get(8).addPlayer(board.getPlayers().get(3));
		rank.createRank();
		assertEquals(board.getPlayers().get(0).getUser(),"user4");
		assertEquals(board.getPlayers().get(1).getUser(),"user3");
		assertEquals(board.getPlayers().get(2).getUser(),"user1");	
		assertEquals(board.getPlayers().get(3).getUser(),"user2");
		assertTrue(board.getPlayers().get(0).getVictoryTrack().getVictoryPoints()==8);
		assertTrue(board.getPlayers().get(1).getVictoryTrack().getVictoryPoints()==5);
		assertTrue(board.getPlayers().get(2).getVictoryTrack().getVictoryPoints()==0);
		assertTrue(board.getPlayers().get(3).getVictoryTrack().getVictoryPoints()==0);
	}
	
	/**
	 * it tests if createRank creates the list of the top players properly
	 */
	@Test
	public void testRank3() throws NegativeNumberException {
		Rank rank=new Rank(board);
		board.addPlayer(new Player("user1",board.getNobilityTrack()));
		board.addPlayer(new Player("user2",board.getNobilityTrack()));
		board.addPlayer(new Player("user3",board.getNobilityTrack()));
		board.addPlayer(new Player("user4",board.getNobilityTrack()));
		board.getPlayers().get(3).getAssistantsPool().setAssistants(10);
		board.getPlayers().get(3).addPoliticCard(new PoliticCard(null, true));
		board.getPlayers().get(0).addPoliticCard(new PoliticCard(null, true));
		board.getPlayers().get(1).getAssistantsPool().setAssistants(100);
		List<NobilityBox> boxes=board.getNobilityTrack().getNobilityBoxes();
		boxes.get(5).addPlayer(board.getPlayers().get(3));
		boxes.get(5).addPlayer(board.getPlayers().get(1));
		boxes.get(5).addPlayer(board.getPlayers().get(0));
		boxes.get(8).addPlayer(board.getPlayers().get(2));
		rank.createRank();
		assertTrue(board.getPlayers().get(0).getVictoryTrack().getVictoryPoints()==5);
		assertTrue(board.getPlayers().get(1).getVictoryTrack().getVictoryPoints()==2);
		assertTrue(board.getPlayers().get(2).getVictoryTrack().getVictoryPoints()==2);
		assertTrue(board.getPlayers().get(3).getVictoryTrack().getVictoryPoints()==2);
		assertEquals(board.getPlayers().get(0).getUser(),"user3");
		assertEquals(board.getPlayers().get(1).getUser(),"user2");
		assertEquals(board.getPlayers().get(2).getUser(),"user4");	
		assertEquals(board.getPlayers().get(3).getUser(),"user1");
	}
}