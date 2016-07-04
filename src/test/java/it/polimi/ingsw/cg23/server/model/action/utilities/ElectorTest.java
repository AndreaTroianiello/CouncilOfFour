package it.polimi.ingsw.cg23.server.model.action.utilities;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.action.ControlAction;
import it.polimi.ingsw.cg23.server.model.components.Councillor;
import it.polimi.ingsw.cg23.server.model.components.King;

public class ElectorTest {

	private ControlAction controlAction;
	private Councillor councillor;
	private Board board;
	private Region region;

	@Before
	public void setUp() throws Exception {
		this.controlAction = new ControlAction();
		this.councillor = new Councillor(Color.ORANGE);
		this.region = new Region("Coast", 0, null, null);
		List<Region> regions = new ArrayList<>();
		regions.add(region);
		this.board = new Board(null, regions, null, null, new King(null), null);
	}

	/**
	 * it tests if election change the councillors of the king's council if it's
	 * all ok
	 */
	@Test
	public void testElectionShouldChangeTheKingCouncilIfItsKing() {
		Elector elector = new Elector(controlAction);
		this.board.getKing().getCouncil().getCouncillors().add(new Councillor(Color.RED));
		elector.election(councillor, board, region, true);
		assertEquals(councillor, this.board.getKing().getCouncil().getCouncillors().get(0));
	}

	/**
	 * it tests if election change the councillors of the region's council if
	 * it's all ok
	 */
	@Test
	public void testElectionShouldChangeTheRegionCouncilIfItsAllOk() {
		Elector elector = new Elector(controlAction);
		this.board.getRegions().get(0).getCouncil().getCouncillors().add(new Councillor(Color.RED));
		elector.election(councillor, board, region, false);
		assertEquals(councillor, this.board.getRegions().get(0).getCouncil().getCouncillors().get(0));
	}

	/**
	 * it tests if election change the councillors of the region's council if
	 * it's all ok
	 */
	@Test
	public void testElectionShouldntChangeTheRegionCouncilIfItIsntAllOk() {
		Elector elector = new Elector(controlAction);
		Councillor oldCouncillor = new Councillor(Color.RED);
		this.board.getRegions().get(0).getCouncil().getCouncillors().add(oldCouncillor);
		elector.election(councillor, board, new Region("Hills", 0, null, null), false);
		assertEquals(oldCouncillor, this.board.getRegions().get(0).getCouncil().getCouncillors().get(0));
	}

}
