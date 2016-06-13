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
import it.polimi.ingsw.cg23.server.model.action.ElectCouncillor;
import it.polimi.ingsw.cg23.server.model.components.Council;
import it.polimi.ingsw.cg23.server.model.components.Councillor;
import it.polimi.ingsw.cg23.server.model.components.King;
import it.polimi.ingsw.cg23.server.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.server.model.exception.NegativeNumberException;

public class ElectCouncillorTest {
	
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
		ElectCouncillor action = new ElectCouncillor(councillor, region, false);
		Council council = region.getCouncil();
		council.getCouncillors().add(new Councillor(Color.BLUE));
		council.getCouncillors().add(new Councillor(Color.BLACK));
		council.getCouncillors().add(new Councillor(Color.RED));
		council.getCouncillors().add(new Councillor(Color.WHITE));
		Council newCouncil = council;
		action.runAction(player, board);
		assertSame(newCouncil, council);
	}
	
	/**
	 * it tests if runAction change the last councillor of the councillor pool of the 
	 * chosen region and if it gives the player 4 coins
	 * @throws NegativeNumberException
	 */
	@Test
	public void testRunActionShouldChangeTheRegionCouncilAndGiveThePlayer4CoinsIfItIsAllFine() throws NegativeNumberException{
		this.councillor=Color.ORANGE;
		ElectCouncillor action = new ElectCouncillor(councillor, region, false);
		List<Councillor> councillorPool = board.getCouncillorPool();
		Councillor newCouncillor = new Councillor(Color.ORANGE);
		councillorPool.add(newCouncillor);
		Council council = region.getCouncil();
		council.getCouncillors().add(new Councillor(Color.BLUE));
		council.getCouncillors().add(new Councillor(Color.BLACK));
		council.getCouncillors().add(new Councillor(Color.RED));
		council.getCouncillors().add(new Councillor(Color.WHITE));
		player.getRichness().setCoins(10);
		action.runAction(player, board);
		assertEquals(newCouncillor, council.getCouncillors().get(3));
		assertEquals(14, player.getRichness().getCoins());
	}
	
	/**
	 * it tests if runAction change the last councillor of the councillor pool of the 
	 * chosen region and if it gives the player 4 coins
	 * @throws NegativeNumberException
	 */
	@Test
	public void testRunActionShouldChangeTheKingCouncilAndGiveThePlayer4CoinsIfItIsAllFine() throws NegativeNumberException{
		this.councillor=Color.ORANGE;
		ElectCouncillor action = new ElectCouncillor(councillor, null, true);
		List<Councillor> councillorPool = board.getCouncillorPool();
		Councillor newCouncillor = new Councillor(Color.ORANGE);
		councillorPool.add(newCouncillor);
		Council council = board.getKing().getCouncil();
		council.getCouncillors().add(new Councillor(Color.BLUE));
		council.getCouncillors().add(new Councillor(Color.BLACK));
		council.getCouncillors().add(new Councillor(Color.RED));
		council.getCouncillors().add(new Councillor(Color.WHITE));
		player.getRichness().setCoins(10);
		action.runAction(player, board);
		assertEquals(newCouncillor, council.getCouncillors().get(3));
		assertEquals(14, player.getRichness().getCoins());
	}


	/**
	 * it tests if isKing works properly
	 */
	@Test
	public void testIsKing() {
		ElectCouncillor action = new ElectCouncillor(null, region, king);
		assertEquals(false, action.isKing());
	}

	/**
	 * it tests if getRegion works properly
	 */
	@Test
	public void testGetRegion() {
		ElectCouncillor action = new ElectCouncillor(null, region, king);
		assertEquals(region, action.getRegion());
	}

	/**
	 * it tests if getCouncillor works properly
	 */
	@Test
	public void testGetCouncillor() {
		ElectCouncillor action = new ElectCouncillor(councillor, region, king);
		assertEquals(councillor, action.getCouncillor());
	}

	/**
	 * it tests if toString works properly
	 */
	@Test
	public void testToString() {
		ElectCouncillor action = new ElectCouncillor(null, null, true);
		assertEquals("ElectCouncillor [councillor=null, region=null, king=true]", action.toString());

	}

}
