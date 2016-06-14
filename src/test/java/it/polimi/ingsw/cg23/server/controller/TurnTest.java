package it.polimi.ingsw.cg23.server.controller;

import static org.junit.Assert.*;

import java.awt.Color;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.action.AdditionalAction;
import it.polimi.ingsw.cg23.server.model.action.ElectCouncillor;
import it.polimi.ingsw.cg23.server.model.action.GameAction;
import it.polimi.ingsw.cg23.server.model.action.HireAssistant;
import it.polimi.ingsw.cg23.server.model.components.Councillor;
import it.polimi.ingsw.cg23.server.model.components.Deck;
import it.polimi.ingsw.cg23.server.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.server.model.components.PoliticCard;
import it.polimi.ingsw.cg23.server.model.exception.NegativeNumberException;
import it.polimi.ingsw.cg23.server.view.ServerSocketView;
import it.polimi.ingsw.cg23.server.view.View;

public class TurnTest {

	private int currentPlayer;										
	private GameAction action;	
	private Board board;
	private Player player1;
	private Player player2;
	private Deck deck;
	private Controller controller;
	
	@Before
	public void setUp() throws Exception {
		List<Region> regions = new ArrayList<>();
		List<PoliticCard> politicCards = new ArrayList<>();
		politicCards.add(new PoliticCard(null, true));
		politicCards.add(new PoliticCard(null, true));
		politicCards.add(new PoliticCard(null, true));
		politicCards.add(new PoliticCard(null, true));
		this.deck = new Deck(politicCards);
		this.board = new Board(deck, regions, null, null, null, null);
		this.player1 = new Player("user1", new NobilityTrack(1));
		this.player2 = new Player("user1", new NobilityTrack(1));
		this.board.addPlayer(player1);
		this.board.addPlayer(player2);
		this.action = new HireAssistant();
		this.controller = new Controller(board);
	}

	/**
	 * it tests if the the methods return the current player
	 */
	@Test
	public void testGetCurrentPlayer() {
		Turn turn = new Turn(board);
		Player currentPlayer =turn.getCurrentPlayer();
		assertEquals(player1, currentPlayer);
	}

	/**
	 * it tests if the state of the game is changed
	 */
	@Test
	public void testIsChangeState() {
		Turn turn = new Turn(board);
		assertTrue(turn.isChangeState());
	}
	
	/**
	 * it tests if the state of the game isn't changed
	 */
	@Test
	public void testIsChangeStateSgouldReturFalseIsTheStateIsFINALTURN() {
		Turn turn = new Turn(board);
		this.board.getStatus().setStatus("FINAL TURN");
		assertFalse(turn.isChangeState());
	}

	/**
	 * it tests if the current player is changed correctly
	 * 
	 * @throws NegativeNumberException
	 */
	@Test
	public void testIfChangePlayerActuallyChangeThePlayer() throws NegativeNumberException {
		List<PoliticCard> politics = new ArrayList<>();
		Deck deck2 = new Deck(politics);
		Board board2 = new Board(deck2, null, null, null, null, null);
		board2.addPlayer(player1);
		board2.addPlayer(player2);
		Turn turn = new Turn(board2);
		board2.getStatus().setStatus("TURN");
		assertFalse(turn.changePlayer());
		assertTrue(turn.getCurrentPlayer().equals(player2));
	}
	
	/**
	 * it tests if when the status is "TURN" the player draw a politic's card
	 * 
	 * @throws NegativeNumberException
	 */
	@Test 
	public void testIfChangePlayerMakeThePlayerDraw() throws NegativeNumberException{
		Turn turn = new Turn(board);
		this.board.getStatus().setStatus("MARKET: BUYING");
		this.player1.switchAdditionalAction();
		turn.changePlayer();
		turn.changePlayer();
		assertFalse(player1.getHand().isEmpty());
	}
	
	/**
	 * it tests if when the current player is the first the status is changed porperly
	 * 
	 * @throws NegativeNumberException
	 */
	@Test
	public void testChangeActionIfTheStatusIsChangedProperly() throws NegativeNumberException{
		Turn turn = new Turn(board);
		this.board.getStatus().setStatus("MARKET: SELLING");
		turn.changePlayer();
		turn.changePlayer();
		assertEquals("MARKET: BUYING", board.getStatus().getStatus());
	}
	
	/**
	 * it tests if changePlayer returns true when the current player is the final player
	 * 
	 * @throws NegativeNumberException
	 */
	@Test
	public void testChangeActionShouldReturnTrueIfTheCurrentPlayerIsTheFinal() throws NegativeNumberException{
		Turn turn = new Turn(board);
		board.getStatus().setFinalPlayer(player2);
		assertTrue(turn.changePlayer());
	}
	
	/**
	 * it tests if the method returns the right board
	 */
	@Test
	public void testGetBoard() {
		Turn turn = new Turn(board);
		turn.setPlayers(null);
		assertEquals(board, turn.getBoard());
	}
	
	/**
	 * it tests if isFinalTurn returns the right boolean
	 */
	@Test
	public void testIsFinalTurn() {
		Turn turn = new Turn(board);
		assertFalse(turn.isFinalTurn());
		this.board.getStatus().setFinalPlayer(player1);
		assertTrue(turn.isFinalTurn());
	}

	/**
	 * it tests if the action is runned correctly
	 * 
	 * @throws NegativeNumberException
	 */
	@Test
	public void testRunActionShouldSwitchHireAssistantsShouldHireAnAssistantWhenTheActionIsHireAssistant() throws NegativeNumberException {
		Turn turn = new Turn(board);
		this.player1.getAssistantsPool().setAssistants(10);
		this.player1.getRichness().setCoins(2);
		turn.setAction(action);
		turn.runAction();
		assertTrue(this.player1.getAssistantsPool().getAssistants() == 10);
	}
	
	/**
	 * it tests if runAction doesn't give the player the chance to do two main action when
	 * he doesn't have additional action
	 */
	@Test
	public void testRunActionShouldntMakeThePlayerHaveTwoMainActionIfHeDoesntHaveAdditionalAction(){
		Turn turn = new Turn(board);
		Region region = new Region("mare", 0, null, null);
		region.getCouncil().getCouncillors().add(new Councillor(new Color(0, 0, 0)));
		this.board.getRegions().add(region);
		this.board.getCouncillorPool().add(new Councillor(new Color(25, 0, 0)));
		this.board.getCouncillorPool().add(new Councillor(new Color(0, 0, 0)));
		for(int i=0; i<10; i++){
			this.player1.getAvailableEmporium();
		}
		turn.setAction(new ElectCouncillor(new Color(25, 0, 0), region, false));
		turn.runAction();
		turn.setAction(new ElectCouncillor(new Color(0, 0, 0), region, false));
		turn.runAction();
		assertTrue(region.getCouncil().getCouncillors().get(0).getColor().equals(new Color(25, 0, 0)));
		assertEquals(this.board.getStatus().getFinalPlayer(), this.player1);
	}

	/**
	 * it tests if toString() works properly
	 */
	@Test
	public void testToString() {
		Turn turn = new Turn(board);
		assertEquals("Turn [currentPlayer=user1, mainIndex=1, mainAction=true, secondAction=true]", turn.toString());
	}

}
