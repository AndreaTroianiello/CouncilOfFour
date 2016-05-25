package it.polimi.ingsw.cg23.model.action;

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
import it.polimi.ingsw.cg23.model.components.Council;
import it.polimi.ingsw.cg23.model.components.Councillor;
import it.polimi.ingsw.cg23.model.components.Deck;
import it.polimi.ingsw.cg23.model.components.King;
import it.polimi.ingsw.cg23.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.model.components.PoliticCard;
import it.polimi.ingsw.cg23.model.exception.NegativeNumberException;

public class BuildEmporiumKingTest {
	
	Player player;
	Board board;
	private List<PoliticCard> cards;
	private List<PoliticCard> discardedCards;
	private City destination;

	@Before
	public void setUp() throws Exception {
		player = new Player("player 1", 10, 100, new NobilityTrack(3));
		board = new Board(new Deck(new ArrayList<PoliticCard>()), null, null, null, new King(null));
	}



	/**
	 * it tests if HowManyJolly() returns the number of jolly  
	 */
	@Test
	public void testHowManyJollyShouldReturn1IfIHave1Jolly() { 
		PoliticCard card1 = new PoliticCard(null, true);
		PoliticCard card2 = new PoliticCard(Color.BLUE, false);
		this.cards = new ArrayList<>();
		this.cards.add(card1);
		this.cards.add(card2);
		destination = new City('O', "Oblak", new Type(null, 0, null), new Region(null, 0, null, null));
		BuildEmporiumKing action = new BuildEmporiumKing(cards, destination);
		int jolly = action.howManyJolly(board);
		assertEquals(jolly, 1);
	}
	
	/**
	 * it tests if HowManyMatch() returns the number of match
	 */
	@Test
	public void testHowManyMatchShouldReturn1IfIHave1Match() { 
		PoliticCard card1 = new PoliticCard(Color.ORANGE, false);
		PoliticCard card2 = new PoliticCard(Color.BLUE, false);
		Council council = new Council();
		council.getCouncillors().add(new Councillor(Color.BLUE));
		council.getCouncillors().add(new Councillor(Color.BLACK));
		council.getCouncillors().add(new Councillor(Color.RED));
		council.getCouncillors().add(new Councillor(Color.WHITE));
		this.cards = new ArrayList<>();
		this.cards.add(card1);
		this.cards.add(card2);
		destination = new City('O', "Oblak", new Type(null, 0, null), new Region(null, 0, null, null));
		BuildEmporiumKing action = new BuildEmporiumKing(cards, destination);
		int match = action.howManyMatch(board, council);
		assertEquals(match, 1);
	}
	
	/**
	 * it tests if it returns 0 when the payment is successful 
	 */
	@Test
	public void testTryPaymentShouldReturn0IfNotNegative(){
		BuildEmporiumKing action = new BuildEmporiumKing(cards, null);
		int number = action.tryPayment(player, 10, 7);
		assertEquals(number, 0);
	}
	
	/**
	 * it tests if it returns -1 when the payment isn't successful 
	 */
	@Test
	public void testTryPaymentShouldReturnMinus1IfItIsNegative(){
		this.cards = new ArrayList<>();
		BuildEmporiumKing action = new BuildEmporiumKing(cards, null);
		cards.add(new PoliticCard(null, true));
		this.discardedCards = new ArrayList<>();
		discardedCards.add(new PoliticCard(null, true));
		int number = action.tryPayment(player, 6, 7);
		assertEquals(number, -1);
	}
	
	/**
	 * it tests if payCoins() works properly when there are 0 or 4 match
	 */
	@Test
	public void testPayCoinsShouldReturn0IfMatchIs4AndMinus1IfMatchIs0(){
		BuildEmporiumKing action = new BuildEmporiumKing(cards, null);
		int payment = action.payCoins(0, player);
		assertEquals(payment, -1);
		payment = action.payCoins(4, player);
		assertEquals(payment, 0);
	}
	
	/**
	 * it tests if payCoins() works properly when there are from 1 to 3 match
	 */
	@Test
	public void testPayCoinsShouldReturn7IfThereAre2Match(){
		BuildEmporiumKing action = new BuildEmporiumKing(cards, null);
		int payment = action.payCoins(2, player);
		assertEquals(payment, 7);
	}
	
	/**
	 * it tests if payCoins() works properly when the palyer doesn't have enough money
	 * @throws NegativeNumberException
	 */
	@Test
	public void testPayCoinsShoulReturnMinus1IfThePlayerDoesntHaveEnoughMoney() throws NegativeNumberException{
		this.cards = new ArrayList<>();
		BuildEmporiumKing action = new BuildEmporiumKing(cards, null);
		this.player.getRichness().setCoins(0);
		int payment = action.payCoins(1, player);
		assertEquals(payment, -1);
	}
	
	@Test
	public void testRunActionShouldChangeTheKingCityToDestinationIfAllIsFine(){
		player = new Player("player 1", 10, 100, new NobilityTrack(3));
		this.destination = new City('I', "Iuvenar", new Type(null, 0, null), new Region(null, 0, null, null));
		this.cards = new ArrayList<>();
		BuildEmporiumKing action = new BuildEmporiumKing(cards, destination);
		action.runAction(player, board);
		City city = board.getKing().getCity();
		assertEquals(city, destination);
	}

}
