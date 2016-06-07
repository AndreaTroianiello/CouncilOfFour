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
import it.polimi.ingsw.cg23.server.model.action.BuyPermitTile;
import it.polimi.ingsw.cg23.server.model.components.BusinessPermitTile;
import it.polimi.ingsw.cg23.server.model.components.Council;
import it.polimi.ingsw.cg23.server.model.components.Councillor;
import it.polimi.ingsw.cg23.server.model.components.Deck;
import it.polimi.ingsw.cg23.server.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.server.model.components.PoliticCard;
import it.polimi.ingsw.cg23.server.model.components.RegionDeck;
import it.polimi.ingsw.cg23.server.model.exception.NegativeNumberException;

public class BuyPermitTileTest {

	private Region region;
	private BusinessPermitTile choosenTile;
	private Player player;
	private List<PoliticCard> discardedCards;
	private Board board;
	private List<PoliticCard> cards = new ArrayList<>();
	private List<Character> citiesId = new ArrayList<>();
	Council council;
	

	@Before
	public void setUp() throws Exception {
		player = new Player("player 1", 10, 100, new NobilityTrack(3));
		citiesId.add('J');
		choosenTile = new BusinessPermitTile(citiesId, null);
		cards.add(new PoliticCard(Color.BLUE, false));
		player.getHand().addAll(cards);
		RegionDeck deck = new RegionDeck(2);
		deck.getShowedDeck().add(choosenTile);
		region = new Region("si", 5,deck , null);
		List<Region> regions = new ArrayList<>();
		regions.add(region);
		board = new Board(new Deck(new ArrayList<PoliticCard>()), regions, null, null, null);
		choosenTile = new BusinessPermitTile(citiesId, null);
		council = region.getCouncil();
		council.getCouncillors().add(new Councillor(Color.BLUE));
		council.getCouncillors().add(new Councillor(Color.BLACK));
		council.getCouncillors().add(new Councillor(Color.RED));
		council.getCouncillors().add(new Councillor(Color.WHITE));
	}

	/**
	 * tests if the chosenTile is added to the player's permit tile
	 */
	@Test
	public void testRunAction(){
		BuyPermitTile action = new BuyPermitTile(cards, region, choosenTile);
		action.runAction(player, board);
		BusinessPermitTile card = player.getAvailableBusinessPermits().get(0);
		assertEquals(choosenTile, card);
	}

	/**
	 * it tests if getChosenTile works properly
	 */
	@Test
	public void testGetChosenTile() {
		BuyPermitTile action = new BuyPermitTile(cards, region, choosenTile);
		BusinessPermitTile tile = action.getChosenTile();
		assertEquals(choosenTile, tile);
	}

	/**
	 * it tests if getCards works properly
	 */
	@Test
	public void testGetCards() {
		BuyPermitTile action = new BuyPermitTile(cards, region, choosenTile);
		List<PoliticCard> politics = action.getCards();
		assertEquals(cards, politics);
	}
	
	/**
	* it tests if getRegion works properly
	*/
	@Test
	public void testGetRegion() {
		BuyPermitTile action = new BuyPermitTile(cards, region, choosenTile);
		Region actualRegion = action.getRegion();
		assertEquals(region, actualRegion);
	}

	/**
	 * it tests if howManyMatch returns the right number of jolly
	 */
	@Test
	public void testHowManyMatchShouldReturn1JollyIfIHave1Jolly() {
		PoliticCard card1 = new PoliticCard(null, true);
		PoliticCard card2 = new PoliticCard(Color.BLUE, false);
		this.cards = new ArrayList<>();
		this.cards.add(card1);
		this.cards.add(card2);
		BuyPermitTile action = new BuyPermitTile(cards, region, choosenTile);
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
		BuyPermitTile action = new BuyPermitTile(cards, region, choosenTile);
		int match = action.howManyMatch(board, council);
		assertEquals(match, 1);
	}

	/**
	 * it tests if it returns 0 when the payment is successful 
	 */
	@Test
	public void testTryPaymentShouldReturn0IfNotNegative(){
		BuyPermitTile action = new BuyPermitTile(cards, region, choosenTile);
		int number = action.tryPayment(player, 10, 7);
		assertEquals(number, 0);
	}
	
	/**
	 * it tests if it returns -1 when the payment isn't successful 
	 */
	@Test
	public void testTryPaymentShouldReturnMinus1IfItIsNegative(){
		this.cards = new ArrayList<>();
		BuyPermitTile action = new BuyPermitTile(cards, region, choosenTile);
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
		BuyPermitTile action = new BuyPermitTile(cards, region, choosenTile);
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
		BuyPermitTile action = new BuyPermitTile(cards, region, choosenTile);
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
		BuyPermitTile action = new BuyPermitTile(cards, region, choosenTile);
		this.player.getRichness().setCoins(0);
		int payment = action.payCoins(1, player);
		assertEquals(payment, -1);
	}


	/**
	 * tests if toString() works properly
	 */
	@Test
	public void testToString() {
		BuyPermitTile action = new BuyPermitTile(null, null, null);
		assertEquals("BuyPermitTile [cards=null, region=null, chosenTile=null]", action.toString());
	}
	
	/**
	 * it tests if runAction() works properly when the player doesn't have enough money
	 */
	@Test
	public void testRunActionShouldntDoAnythingIfThePlayerDoesntHaveMoney() throws NegativeNumberException{
		System.out.println("I'M RUNNING THE TEST");
		PoliticCard card1 = new PoliticCard(null, true);
		PoliticCard card2 = new PoliticCard(Color.BLUE, false);
		this.cards = new ArrayList<>();
		this.cards.add(card1);
		this.cards.add(card2);
		this.player.getRichness().setCoins(7);
		System.out.println(player.getRichness().getCoins());
		BuyPermitTile action = new BuyPermitTile(cards, region, choosenTile);
		action.runAction(player, board);
		System.out.println(player.getRichness().getCoins());
		List<BusinessPermitTile> card = player.getAvailableBusinessPermits();
		List<BusinessPermitTile> expected = new ArrayList<>();
		System.out.println("FINITO TEST");
		assertEquals(expected, card);
	}

}
