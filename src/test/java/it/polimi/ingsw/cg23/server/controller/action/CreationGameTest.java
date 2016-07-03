package it.polimi.ingsw.cg23.server.controller.action;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.controller.Avvio;
import it.polimi.ingsw.cg23.server.controller.Controller;
import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.components.Deck;
import it.polimi.ingsw.cg23.server.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.server.model.components.PoliticCard;

public class CreationGameTest {

	private Board board;
	private Controller controller;
	
	@Before
	public void setUp() throws Exception {
		this.board=new Board(null,null,null,null,null,null);
		Avvio start=new Avvio("map1.xml", board);
	 	start.startPartita();
		this.controller=new Controller(board);
	}
	
	/**
	 * it tests if the constructor throws a NullPointerException when its parameters are null
	 */
	@Test(expected=NullPointerException.class)
	public void testConstructorShouldThrowNullPointerExceptionWhenTheParametersAreNull(){
		CreationGame creation = new CreationGame(null, null);
		creation.runAction(controller, board);
	}
	

	/**
	 * it tests is runAction creates a new player if there aren't any players with the same username in the board
	 */
	@Test
	public void testRunActionShouldCreateANewPlayerWhenTheChosenUserIsNotUsedYet() {
		CreationGame creation = new CreationGame("ciao", "map1");
		this.board.setDeck(null);
		creation.runAction(controller, board);
		this.controller.update(creation);
		assertEquals("ciao", board.getPlayers().get(0).getUser());
	}
	
	/**
	 * it tests is runAction doesn't create a new player if there is a player with the same 
	 * username in the board
	 */
	@Test
	public void testRunActionShouldntCreateANewPlayerWhenTheChosenUserIsUsed() {
		CreationGame creation = new CreationGame("ciao", "map1");
		this.board.getPlayers().add(new Player("ciao", new NobilityTrack(3)));
		creation.runAction(controller, board);
		assertEquals(1, this.board.getPlayers().size());
	}

	/**
	 * it tests is runAction creates a new player if there aren't any players with the same username in the board
	 */
	@Test
	public void testRunActionShouldntCreateAGameIfTheDeckIsSettedInTheBoardAndTheNobilityTrackIsNull() {
		CreationGame creation = new CreationGame("ciao", "map1");
		List<PoliticCard> politicCards = new ArrayList<>();
		board.setDeck(new Deck(politicCards));
		board.setNobilityTrack(null);
		creation.runAction(controller, board);
		assertEquals(0, board.getPlayers().size());
	}
}
