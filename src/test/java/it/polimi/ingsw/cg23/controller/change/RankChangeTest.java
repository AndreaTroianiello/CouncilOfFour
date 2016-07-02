package it.polimi.ingsw.cg23.controller.change;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.controller.change.RankChange;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.components.NobilityTrack;
public class RankChangeTest {

	private List<Player> players;
	@Before
	public void setUp() throws Exception {
		players=Arrays.asList(new Player("user1",new NobilityTrack(1)),new Player("user2",new NobilityTrack(1)),
				new Player("user3",new NobilityTrack(1)));
	}

	/**
	 * it tests if getRank and toString work properly
	 */
	@Test
	public void testBoardChange() {
		RankChange change=new RankChange(players);
		assertTrue(players.equals(change.getRank()));
		assertEquals(change.toString(),"RankChange [newRank="+ players+"]");
	}

}
