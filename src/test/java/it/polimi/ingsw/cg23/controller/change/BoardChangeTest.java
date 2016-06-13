package it.polimi.ingsw.cg23.controller.change;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.controller.change.BoardChange;
import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.City;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.Type;
import it.polimi.ingsw.cg23.server.model.components.Deck;
import it.polimi.ingsw.cg23.server.model.components.King;
import it.polimi.ingsw.cg23.server.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.server.model.components.PoliticCard;

public class BoardChangeTest {
	private Board board;
	@Before
	public void setUp() throws Exception {
		List<Region> regions=Arrays.asList(new Region("regione1",0,null,null),new Region("regione2",0,null,null),
				new Region("regione3",0,null,null));
		List<Type> types=Arrays.asList(new Type("type1",0,null),new Type("type2",0,null));
		City city=new City('A', "Aosta", types.get(0), regions.get(0));
		board=new Board(new Deck(new ArrayList<PoliticCard>()), regions, types, new NobilityTrack(10), new King(city), null);
		
	}

	@Test
	public void testBoardChange() {
		BoardChange change=new BoardChange(board);
		assertTrue(board.equals(change.getBoard()));
		assertEquals(change.toString(),"BoardChange [newBoard=Board [deck=true, regions=3, types=2, nobilityTrack=10, king=King [city=A, council=0]]]");
	}

}
