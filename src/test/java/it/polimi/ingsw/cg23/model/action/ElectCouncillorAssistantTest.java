package it.polimi.ingsw.cg23.model.action;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.action.ElectCouncillorAssistant;
import it.polimi.ingsw.cg23.server.model.components.Council;
import it.polimi.ingsw.cg23.server.model.components.Councillor;
import it.polimi.ingsw.cg23.server.model.components.King;
import it.polimi.ingsw.cg23.server.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.server.model.exception.NegativeNumberException;

public class ElectCouncillorAssistantTest {

	private boolean king;
	private Region region;
	private Color councillor;
	private Player player;
	private Board board;
	private List<Region> regions = new ArrayList<>();
	

	@Before
	public void setUp() throws Exception {
		this.king=false;
		this.region=new Region("mare", 0, null, null);
		this.councillor=Color.ORANGE;
		regions.add(region);
		player = new Player("player1", new NobilityTrack(3));
		King theKing = new King(null);
		board = new Board(null, regions, null, null, theKing, null);
	}

	/**
	 * it tests if when there aren't councillor of the chosen color in the pool runAction doesn't
	 * change the council
	 */
	@Test
	public void testRunActionShouldntChangeTheCouncilWhenThereArentCouncillorsOfTheChosenColorInThePool() {
		this.councillor=Color.ORANGE;
		ElectCouncillorAssistant action = new ElectCouncillorAssistant(councillor, region, false);
		Council council = region.getCouncil();
		council.getCouncillors().add(new Councillor(Color.BLUE));
		council.getCouncillors().add(new Councillor(Color.BLACK));
		council.getCouncillors().add(new Councillor(Color.RED));
		council.getCouncillors().add(new Councillor(Color.WHITE));
		Council oldCouncil = council;
		action.runAction(player, board);
		assertSame(oldCouncil, council);
	}
	
	/**
	 * it tests if runAction change the last councillor of the councillor pool of the 
	 * chosen region and if it takes an assistant from the player
	 * @throws NegativeNumberException
	 */
	@Test
	public void testRunActionShouldChangeTheRegionCouncilAndTakeAnAssistantFromThePlayer() throws NegativeNumberException{
		this.councillor=Color.ORANGE;
		ElectCouncillorAssistant action = new ElectCouncillorAssistant(councillor, region, false);
		List<Councillor> councillorPool = board.getCouncillorPool();
		Councillor newCouncillor = new Councillor(Color.ORANGE);
		councillorPool.add(newCouncillor);
		Council council = region.getCouncil();
		council.getCouncillors().add(new Councillor(Color.BLUE));
		council.getCouncillors().add(new Councillor(Color.BLACK));
		council.getCouncillors().add(new Councillor(Color.RED));
		council.getCouncillors().add(new Councillor(Color.WHITE));
		player.getAssistantsPool().setAssistants(10);;
		action.runAction(player, board);
		assertEquals(newCouncillor, council.getCouncillors().get(3));
		assertEquals(9, player.getAssistantsPool().getAssistants());
	}


	/**
	 * it tests if it works properly when the player doesn't have assistant
	 * @throws NegativeNumberException
	 */
	/*@Test
	public void testRunActionShouldntDoAnythingIfThePlayerDoesntHaveAssistant() throws NegativeNumberException{
		this.councillor=Color.ORANGE;
		ElectCouncillorAssistant action = new ElectCouncillorAssistant(councillor, null, true);
		List<Councillor> councillorPool = board.getCouncillorPool();
		Councillor newCouncillor = new Councillor(Color.ORANGE);
		councillorPool.add(newCouncillor);
		Council council = board.getKing().getCouncil();
		council.getCouncillors().add(new Councillor(Color.BLUE));
		council.getCouncillors().add(new Councillor(Color.BLACK));
		council.getCouncillors().add(new Councillor(Color.RED));
		council.getCouncillors().add(new Councillor(Color.WHITE));
		Council oldCouncil = council;
		player.getAssistantsPool().setAssistants(0);;
		action.runAction(player, board);
		assertSame(oldCouncil, council);
	}*/

	/**
	 * it tests if isKing works properly
	 */
	/*@Test
	public void testIsKing() {
		ElectCouncillorAssistant action = new ElectCouncillorAssistant(null, region, king);
		assertEquals(false, action.isKing());
	}*/

	/**
	 * it tests if getRegion works properly
	 */
	/*@Test
	public void testGetRegion() {
		ElectCouncillorAssistant action = new ElectCouncillorAssistant(null, region, king);
		assertEquals(region, action.getRegion());
	}*/

	/**
	 * it tests if getCouncillor works properly
	 */
	/*@Test
	public void testGetCouncillor() {
		ElectCouncillorAssistant action = new ElectCouncillorAssistant(councillor, region, king);
		assertEquals(councillor, action.getCouncillor());
	}*/

	/**
	 * it tests if toString works properly
	 */
	/*@Test
	public void testToString() {
		ElectCouncillorAssistant action = new ElectCouncillorAssistant(null, null, true);
		assertEquals("ElectCouncillorAssistant [councillor=null, region=null, king=true]", action.toString());

	}*/

}
