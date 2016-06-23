package it.polimi.ingsw.cg23.server.controller;

import static org.junit.Assert.*;

import java.awt.Color;
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
import it.polimi.ingsw.cg23.server.model.action.MarketBuy;
import it.polimi.ingsw.cg23.server.model.action.MarketSell;
import it.polimi.ingsw.cg23.server.model.components.AssistantsPool;
import it.polimi.ingsw.cg23.server.model.components.Councillor;
import it.polimi.ingsw.cg23.server.model.components.Deck;
import it.polimi.ingsw.cg23.server.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.server.model.components.PoliticCard;
import it.polimi.ingsw.cg23.server.model.exception.NegativeNumberException;

public class TurnTest {
									
	private GameAction action;	
	private Board board;
	private Player player1;
	private Player player2;
	private Deck deck;
	
	@Before
	public void setUp() throws Exception {
		List<Region> regions = new ArrayList<>();
		List<PoliticCard> politicCards = new ArrayList<>();
		politicCards.add(new PoliticCard(null, true));
		politicCards.add(new PoliticCard(null, true));
		politicCards.add(new PoliticCard(null, true));
		politicCards.add(new PoliticCard(null, true));
		this.deck = new Deck(politicCards);
		this.board = new Board(deck, regions, null, new NobilityTrack(1), null, null);
		this.player1 = new Player("user1", new NobilityTrack(1));
		this.player2 = new Player("user1", new NobilityTrack(1));
		this.board.addPlayer(player1);
		this.board.addPlayer(player2);
		this.action = new HireAssistant();
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
		List<Region> regions = new ArrayList<>();
		Board board2 = new Board(deck2, regions, null, new NobilityTrack(1), null, null);
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
	 * it tests if additionalAction makes the player has two main action
	 */
	@Test
	public void testRunActionShouldMakeThePlayerMakesTwoMainActionWhenIsAdditionalAction(){
		Turn turn = new Turn(board);
		Region region = new Region("mare", 0, null, null);
		region.getCouncil().getCouncillors().add(new Councillor(new Color(0, 0, 0)));
		this.board.getRegions().add(region);
		this.board.getCouncillorPool().add(new Councillor(new Color(25, 0, 0)));
		this.board.getCouncillorPool().add(new Councillor(new Color(0, 0, 0)));
		turn.setAction(new AdditionalAction());
		turn.runAction();
		turn.setAction(new ElectCouncillor(new Color(25, 0, 0), region, false));
		turn.runAction();
		turn.setAction(new ElectCouncillor(new Color(0, 0, 0), region, false));
		turn.runAction();
		assertTrue(region.getCouncil().getCouncillors().get(0).getColor().equals(new Color(0, 0, 0)));

	}
	
	/**
	 * it tests if the marketSell action works and take the item form the player when it's the selling turn,
	 * if marketBuy gives the item when it's the buying turn, and if it did nothing when the player wants to 
	 * do a market's action but is not the right turn
	 * @throws NegativeNumberException
	 */
	@Test
	public void testRunActionMarketSellShouldTakeTheItemFromThePlayerAndMarketBuyShouldGiveItToThePlayer() throws NegativeNumberException{
		Turn turn = new Turn(board);
		this.board.getStatus().setStatus("MARKET: SELLING");
		AssistantsPool assistants = new AssistantsPool();
		this.player1.getAssistantsPool().setAssistants(10);
		assistants.setAssistants(5);
		turn.setAction(new MarketSell(assistants, 5));
		turn.runAction();
		turn.setAction(new MarketBuy(this.board.getMarket().getItems().get(0)));
		turn.runAction();
		assertEquals(5, this.player1.getAssistantsPool().getAssistants());
		this.board.getStatus().setStatus("MARKET: BUYING");
		this.player1.getRichness().setCoins(5);
		turn.setAction(new MarketBuy(this.board.getMarket().getItems().get(0)));
		turn.runAction();
		assertEquals(10, this.player1.getAssistantsPool().getAssistants());
		turn.setAction(new MarketSell(assistants, 5));
		turn.runAction();
		assertEquals(10, this.player1.getAssistantsPool().getAssistants());
	}
	
	/**
	 * it tests if toString() works properly
	 */
	@Test
	public void testToString() {
		Turn turn = new Turn(board);
		assertEquals("Turn [currentPlayer=user1, mainIndex=1, mainAction=true, secondAction=true]", turn.toString());
	}
	
	/**
	 * it tests if setTimer and getTimer work properly
	 */
	@Test
	public void testSetGetTimer(){
		Turn turn = new Turn(board);
		Timer timer = new Timer(null, null);
		timer.isRunning();
		turn.setTimer(timer);
		assertEquals(timer, turn.getTimer());
	}

}
