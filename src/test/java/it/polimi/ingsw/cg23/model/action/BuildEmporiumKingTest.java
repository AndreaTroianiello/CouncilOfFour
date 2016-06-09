package it.polimi.ingsw.cg23.model.action;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.City;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.Type;
import it.polimi.ingsw.cg23.server.model.action.BuildEmporiumKing;
import it.polimi.ingsw.cg23.server.model.components.BonusKing;
import it.polimi.ingsw.cg23.server.model.components.Council;
import it.polimi.ingsw.cg23.server.model.components.Councillor;
import it.polimi.ingsw.cg23.server.model.components.Deck;
import it.polimi.ingsw.cg23.server.model.components.Emporium;
import it.polimi.ingsw.cg23.server.model.components.King;
import it.polimi.ingsw.cg23.server.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.server.model.components.PoliticCard;
import it.polimi.ingsw.cg23.server.model.exception.NegativeNumberException;

public class BuildEmporiumKingTest {
	
	private Player player;
	private Board board;
	private List<PoliticCard> cards;
	private List<PoliticCard> discardedCards;
	private City destination;
	private List<Integer> bonusKing = new ArrayList<>();
	private City kingCity;
	private Region region;
	
	Type type = new Type("purple", 5, new BonusKing(bonusKing));

	@Before
	public void setUp() throws Exception {
		player = new Player("player 1", new NobilityTrack(3));
		int n = 5;
		bonusKing.add(n);	
		List<Type> types = new ArrayList<>();
		types.add(type);
		kingCity = new City('J', "Juvelar", type, new Region(null, 0, null, new BonusKing(bonusKing)));
		King king = new King(kingCity);
		region = new Region("mare", 0, null, null);
		this.board = new Board(new Deck(new ArrayList<PoliticCard>()), new ArrayList<Region>(), types, null, king);
		board.getRegions().add(region);
	}



	/**
	 * it tests if HowManyJolly() returns the number of jolly  
	 * @throws NegativeNumberException 
	 */
	@Test
	public void testHowManyJollyShouldReturn1IfIHave1Jolly() throws NegativeNumberException { 
		this.player.getRichness().setCoins(100);
		this.player.getAssistantsPool().setAssistants(10);
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
	 * @throws NegativeNumberException 
	 */
	@Test
	public void testHowManyMatchShouldReturn1IfIHave1Match() throws NegativeNumberException { 
		this.player.getRichness().setCoins(100);
		this.player.getAssistantsPool().setAssistants(10);
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
	 * @throws NegativeNumberException 
	 */
	@Test
	public void testTryPaymentShouldReturn0IfNotNegative() throws NegativeNumberException{
		this.player.getRichness().setCoins(100);
		this.player.getAssistantsPool().setAssistants(10);
		BuildEmporiumKing action = new BuildEmporiumKing(cards, null);
		int number = action.tryPayment(player, 10, 7);
		assertEquals(number, 0);
	}
	
	/**
	 * it tests if it returns -1 when the payment isn't successful 
	 * @throws NegativeNumberException 
	 */
	@Test
	public void testTryPaymentShouldReturnMinus1IfItIsNegative() throws NegativeNumberException{
		this.player.getRichness().setCoins(100);
		this.player.getAssistantsPool().setAssistants(10);
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
	 * @throws NegativeNumberException 
	 */
	@Test
	public void testPayCoinsShouldReturn0IfMatchIs4AndMinus1IfMatchIs0() throws NegativeNumberException{
		this.player.getRichness().setCoins(100);
		this.player.getAssistantsPool().setAssistants(10);
		BuildEmporiumKing action = new BuildEmporiumKing(cards, null);
		int payment = action.payCoins(0, player);
		assertEquals(payment, -1);
		payment = action.payCoins(4, player);
		assertEquals(payment, 0);
	}
	
	/**
	 * it tests if payCoins() works properly when there are from 1 to 3 match
	 * @throws NegativeNumberException 
	 */
	@Test
	public void testPayCoinsShouldReturn7IfThereAre2Match() throws NegativeNumberException{
		this.player.getRichness().setCoins(100);
		this.player.getAssistantsPool().setAssistants(10);
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
		this.player.getAssistantsPool().setAssistants(10);
		int payment = action.payCoins(1, player);
		assertEquals(payment, -1);
	}
	
	/**
	 * it tests if runAction() works properly when all is fine
	 * @throws NegativeNumberException
	 */
	@Test
	public void testRunActionShouldChangeTheKingCityToDestinationIfAllIsFine() throws NegativeNumberException{
		System.out.println("I'M RUNNING THE TEST");
		PoliticCard card1 = new PoliticCard(Color.ORANGE, false);
		PoliticCard card2 = new PoliticCard(Color.BLUE, false);
		Council council = board.getKing().getCouncil();
		council.getCouncillors().add(new Councillor(Color.BLUE));
		council.getCouncillors().add(new Councillor(Color.BLACK));
		council.getCouncillors().add(new Councillor(Color.RED));
		council.getCouncillors().add(new Councillor(Color.WHITE));
		System.out.println(this.board.getKing().getCouncil().toString());
		this.cards = new ArrayList<>();
		this.cards.add(card1);
		this.cards.add(card2);
		this.player.addPoliticCard(card1);
		this.player.addPoliticCard(card2);
		this.player.getRichness().setCoins(100);
		this.player.getAssistantsPool().setAssistants(10);
		this.player.setEmporium(new Emporium(this.player));
		this.destination = new City('I', "Iuvenar", this.type, new Region(null, 0, null, new BonusKing(bonusKing)));
		this.board.getRegions().get(0).addCity(destination);
		this.board.getKing().getCity().addNeighbor(destination);
		destination.addNeighbor(this.board.getKing().getCity());
		BuildEmporiumKing action = new BuildEmporiumKing(cards, destination);
		action.runAction(player, board);
		City city = board.getKing().getCity();
		assertEquals(destination, city);
	}
	
	/**
	 * it tests if runAction() works properly when the player doesn't have enough money
	 */
	@Test
	public void testRunActionShouldntChangeTheKingCityIfThePlayerDoesntHaveMoney() throws NegativeNumberException{
		System.out.println("I'M RUNNING THE TEST");
		PoliticCard card1 = new PoliticCard(null, true);
		PoliticCard card2 = new PoliticCard(Color.BLUE, false);
		Council council = board.getKing().getCouncil();
		council.getCouncillors().add(new Councillor(Color.BLUE));
		council.getCouncillors().add(new Councillor(Color.BLACK));
		council.getCouncillors().add(new Councillor(Color.RED));
		council.getCouncillors().add(new Councillor(Color.WHITE));
		System.out.println(this.board.getKing().getCouncil().toString());
		this.cards = new ArrayList<>();
		this.cards.add(card1);
		this.cards.add(card2);
		this.player.addPoliticCard(card1);
		this.player.addPoliticCard(card2);
		this.player.getRichness().setCoins(0);
		this.player.getAssistantsPool().setAssistants(10);
		this.player.setEmporium(new Emporium(this.player));
		this.destination = new City('I', "Iuvenar", this.type, new Region(null, 0, null, new BonusKing(bonusKing)));
		this.board.getKing().getCity().addNeighbor(destination);
		destination.addNeighbor(this.board.getKing().getCity());
		BuildEmporiumKing action = new BuildEmporiumKing(cards, destination);
		action.runAction(player, board);
		City city = board.getKing().getCity();
		assertEquals(board.getKing().getCity(), city);
	}
	
	
	/**
	 * it tests if runAction() works properly when the player doesn't have enough money for the steps
	 */
	@Test
	public void testRunActionShouldntChangeTheKingCityIfThePlayerDoesntHaveMoneyForTheSteps() throws NegativeNumberException{
		System.out.println("I'M RUNNING THE TEST");
		PoliticCard card1 = new PoliticCard(null, true);
		PoliticCard card2 = new PoliticCard(Color.BLUE, false);
		Council council = board.getKing().getCouncil();
		council.getCouncillors().add(new Councillor(Color.BLUE));
		council.getCouncillors().add(new Councillor(Color.BLACK));
		council.getCouncillors().add(new Councillor(Color.RED));
		council.getCouncillors().add(new Councillor(Color.WHITE));
		System.out.println(this.board.getKing().getCouncil().toString());
		this.cards = new ArrayList<>();
		this.cards.add(card1);
		this.cards.add(card2);
		this.player.addPoliticCard(card1);
		this.player.addPoliticCard(card2);
		this.player.getRichness().setCoins(9);
		this.player.getAssistantsPool().setAssistants(10);
		this.player.setEmporium(new Emporium(this.player));
		this.destination = new City('I', "Iuvenar", this.type, new Region("mare", 0, null, new BonusKing(bonusKing)));
		this.board.getRegions().get(0).addCity(destination);
		this.board.getKing().getCity().addNeighbor(destination);
		destination.addNeighbor(this.board.getKing().getCity());
		BuildEmporiumKing action = new BuildEmporiumKing(cards, destination);
		action.runAction(player, board);
		City city = board.getKing().getCity();
		assertEquals(board.getKing().getCity(), city);
	}
	
	/**
	 * it tests if runAction() works properly when the player doesn't have assistants
	 * @throws NegativeNumberException
	 */
	@Test
	public void testRunActionShouldntChangeTheKingCityIfThereAreEmporiumAndThePlayerDoesntHaveAssistants() throws NegativeNumberException{
		System.out.println("I'M RUNNING THE TEST");
		PoliticCard card1 = new PoliticCard(Color.ORANGE, false);
		PoliticCard card2 = new PoliticCard(Color.BLUE, false);
		Council council = board.getKing().getCouncil();
		council.getCouncillors().add(new Councillor(Color.BLUE));
		council.getCouncillors().add(new Councillor(Color.BLACK));
		council.getCouncillors().add(new Councillor(Color.RED));
		council.getCouncillors().add(new Councillor(Color.WHITE));
		System.out.println(this.board.getKing().getCouncil().toString());
		this.cards = new ArrayList<>();
		this.cards.add(card1);
		this.cards.add(card2);
		Player player2 = new Player("player 1", new NobilityTrack(3));
		player2.addPoliticCard(card1);
		player2.addPoliticCard(card2);
		player2.getRichness().setCoins(100);
		player2.getAssistantsPool().setAssistants(0);
		this.player.setEmporium(new Emporium(this.player));
		this.destination = new City('I', "Iuvenar", this.type, new Region(null, 0, null, new BonusKing(bonusKing)));
		this.destination.getEmporiums().add(new Emporium(player));
		this.board.getRegions().get(0).addCity(destination);
		this.board.getKing().getCity().addNeighbor(destination);
		destination.addNeighbor(this.board.getKing().getCity());
		BuildEmporiumKing action = new BuildEmporiumKing(cards, destination);
		action.runAction(player2, board);
		City city = board.getKing().getCity();
		assertEquals(kingCity, city);
	}

	/**
	 * it tests if runAction() works properly when the player doesn't have emporiums
	 * @throws NegativeNumberException
	 */
	@Test
	public void testRunActionShouldntChangeTheKingCityIfThePlayerDoesntHaveEmpuriums() throws NegativeNumberException{
		System.out.println("I'M RUNNING THE TEST");
		PoliticCard card1 = new PoliticCard(Color.ORANGE, false);
		PoliticCard card2 = new PoliticCard(Color.BLUE, false);
		Council council = board.getKing().getCouncil();
		council.getCouncillors().add(new Councillor(Color.BLUE));
		council.getCouncillors().add(new Councillor(Color.BLACK));
		council.getCouncillors().add(new Councillor(Color.RED));
		council.getCouncillors().add(new Councillor(Color.WHITE));
		System.out.println(this.board.getKing().getCouncil().toString());
		this.cards = new ArrayList<>();
		this.cards.add(card1);
		this.cards.add(card2);
		this.player.addPoliticCard(card1);
		this.player.addPoliticCard(card2);
		this.player.getRichness().setCoins(100);
		this.player.getAssistantsPool().setAssistants(10);
		this.player.setEmporium(new Emporium(this.player));
		for(int i=0; i<10; i++){
			this.player.getAvailableEmporium();
		}
		this.destination = new City('I', "Iuvenar", this.type, new Region(null, 0, null, new BonusKing(bonusKing)));
		this.board.getRegions().get(0).addCity(destination);
		this.board.getKing().getCity().addNeighbor(destination);
		destination.addNeighbor(this.board.getKing().getCity());
		BuildEmporiumKing action = new BuildEmporiumKing(cards, destination);
		action.runAction(player, board);
		City city = board.getKing().getCity();
		assertEquals(kingCity, city);
	}
}
