package it.polimi.ingsw.cg23.model.action;

import static org.junit.Assert.assertEquals;

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
		this.board = new Board(new Deck(new ArrayList<PoliticCard>()), new ArrayList<Region>(), types, null, king,
				null);
		board.getRegions().add(region);
	}

	/**
	 * it tests if runAction() works properly when all is fine
	 * 
	 * @throws NegativeNumberException
	 */
	@Test
	public void testRunActionShouldChangeTheKingCityToDestinationIfAllIsFine() throws NegativeNumberException {
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
	 * it tests if runAction() works properly when the player doesn't have
	 * enough money
	 */
	@Test
	public void testRunActionShouldntChangeTheKingCityIfThePlayerDoesntHaveMoney() throws NegativeNumberException {
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
	 * it tests if runAction() works properly when the player doesn't have
	 * enough money for the jolly
	 */
	@Test
	public void testRunActionShouldntChangeTheKingCityIfThePlayerDoesntHaveMoneyForTheJolly()
			throws NegativeNumberException {
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
	 * it tests if runAction() works properly when the player doesn't have
	 * assistants
	 * 
	 * @throws NegativeNumberException
	 */
	@Test
	public void testRunActionShouldntChangeTheKingCityIfThereAreEmporiumAndThePlayerDoesntHaveAssistants()
			throws NegativeNumberException {
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
	 * it tests if runAction() works properly when the player doesn't have
	 * emporiums
	 * 
	 * @throws NegativeNumberException
	 */
	@Test
	public void testRunActionShouldntChangeTheKingCityIfThePlayerDoesntHaveEmpuriums() throws NegativeNumberException {
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
		for (int i = 0; i < 10; i++) {
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

	/**
	 * it tests if runAction() works properly when there are no match
	 * 
	 * @throws NegativeNumberException
	 */
	@Test
	public void testRunActionShouldntChangeTheCityIfThereAreNoMatch() throws NegativeNumberException {
		System.out.println("I'M RUNNING THE TEST");
		PoliticCard card1 = new PoliticCard(Color.ORANGE, false);
		PoliticCard card2 = new PoliticCard(Color.PINK, false);
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
		this.player.getRichness().setCoins(10);
		this.player.getAssistantsPool().setAssistants(10);
		this.player.setEmporium(new Emporium(this.player));
		this.destination = new City('I', "Iuvenar", this.type, new Region("mare", 0, null, new BonusKing(bonusKing)));
		this.board.getRegions().get(0).addCity(destination);
		this.board.getKing().getCity().addNeighbor(destination);
		destination.addNeighbor(this.board.getKing().getCity());
		BuildEmporiumKing action = new BuildEmporiumKing(cards, destination);
		action.runAction(player, board);
		City city = board.getKing().getCity();
		assertEquals(kingCity, city);
	}

	/**
	 * @throws NegativeNumberException
	 * 
	 */
	@Test
	public void testRunActionShouldChangeTheCityIfThePlayerHas4MatchesAndEnoughMoneyForTheSteps()
			throws NegativeNumberException {
		System.out.println("I'M RUNNING THE TEST");
		PoliticCard card1 = new PoliticCard(null, true);
		PoliticCard card2 = new PoliticCard(Color.BLUE, false);
		PoliticCard card3 = new PoliticCard(Color.BLACK, false);
		PoliticCard card4 = new PoliticCard(Color.WHITE, false);
		Council council = board.getKing().getCouncil();
		council.getCouncillors().add(new Councillor(Color.BLUE));
		council.getCouncillors().add(new Councillor(Color.BLACK));
		council.getCouncillors().add(new Councillor(Color.RED));
		council.getCouncillors().add(new Councillor(Color.WHITE));
		System.out.println(this.board.getKing().getCouncil().toString());
		this.cards = new ArrayList<>();
		this.cards.add(card1);
		this.cards.add(card2);
		this.cards.add(card3);
		this.cards.add(card4);
		this.player.addPoliticCard(card1);
		this.player.addPoliticCard(card2);
		this.player.addPoliticCard(card3);
		this.player.addPoliticCard(card4);
		this.player.getRichness().setCoins(10);
		this.player.getAssistantsPool().setAssistants(10);
		this.player.setEmporium(new Emporium(this.player));
		this.destination = new City('I', "Iuvenar", this.type, new Region("mare", 0, null, new BonusKing(bonusKing)));
		this.board.getRegions().get(0).addCity(destination);
		this.board.getKing().getCity().addNeighbor(destination);
		destination.addNeighbor(this.board.getKing().getCity());
		BuildEmporiumKing action = new BuildEmporiumKing(cards, destination);
		action.runAction(player, board);
		City city = board.getKing().getCity();
		assertEquals(this.destination, city);
	}

	/**
	 * tests if runAction() works properly when the player doesn't have enough
	 * money for the match payment
	 * 
	 * @throws NegativeNumberException
	 */
	@Test
	public void testRunActionShouldntChangeTheKingCityWhenThePlayerDoesntHaveEnoughMoney()
			throws NegativeNumberException {
		System.out.println("I'M RUNNING THE TEST");
		PoliticCard card1 = new PoliticCard(null, true);
		PoliticCard card2 = new PoliticCard(Color.BLUE, false);
		PoliticCard card3 = new PoliticCard(Color.BLACK, false);
		Council council = board.getKing().getCouncil();
		council.getCouncillors().add(new Councillor(Color.BLUE));
		council.getCouncillors().add(new Councillor(Color.BLACK));
		council.getCouncillors().add(new Councillor(Color.RED));
		council.getCouncillors().add(new Councillor(Color.WHITE));
		System.out.println(this.board.getKing().getCouncil().toString());
		this.cards = new ArrayList<>();
		this.cards.add(card1);
		this.cards.add(card2);
		this.cards.add(card3);
		this.player.addPoliticCard(card1);
		this.player.addPoliticCard(card2);
		this.player.addPoliticCard(card3);
		this.player.getRichness().setCoins(0);
		this.player.getAssistantsPool().setAssistants(10);
		this.player.setEmporium(new Emporium(this.player));
		this.destination = new City('I', "Iuvenar", this.type, new Region("mare", 0, null, new BonusKing(bonusKing)));
		this.board.getRegions().get(0).addCity(destination);
		this.board.getKing().getCity().addNeighbor(destination);
		destination.addNeighbor(this.board.getKing().getCity());
		BuildEmporiumKing action = new BuildEmporiumKing(cards, destination);
		action.runAction(player, board);
		City city = board.getKing().getCity();
		assertEquals(kingCity, city);
	}

}
