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
import it.polimi.ingsw.cg23.server.model.action.BuildEmporiumKing;
import it.polimi.ingsw.cg23.server.model.action.BuyPermitTile;
import it.polimi.ingsw.cg23.server.model.components.BonusKing;
import it.polimi.ingsw.cg23.server.model.components.BusinessPermitTile;
import it.polimi.ingsw.cg23.server.model.components.Council;
import it.polimi.ingsw.cg23.server.model.components.Councillor;
import it.polimi.ingsw.cg23.server.model.components.Deck;
import it.polimi.ingsw.cg23.server.model.components.Emporium;
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
		player = new Player("player 1", new NobilityTrack(3));
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
	 * @throws NegativeNumberException 
	 */
	@Test
	public void testRunAction() throws NegativeNumberException{
		this.player.getAssistantsPool().setAssistants(10);
		this.player.getRichness().setCoins(100);
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
	 * it tests if runAction() works properly when there are no match
	 * @throws NegativeNumberException
	 */
	@Test
	public void testRunActionShouldntChangeTheCityIfThereAreNoMatch() throws NegativeNumberException{
		System.out.println("I'M RUNNING THE TEST");
		PoliticCard card1 = new PoliticCard(Color.ORANGE, false);
		PoliticCard card2 = new PoliticCard(Color.PINK, false);
		council.getCouncillors().add(new Councillor(Color.BLUE));
		council.getCouncillors().add(new Councillor(Color.BLACK));
		council.getCouncillors().add(new Councillor(Color.RED));
		council.getCouncillors().add(new Councillor(Color.WHITE));
		this.cards = new ArrayList<>();
		this.cards.add(card1);
		this.cards.add(card2);
		this.player.addPoliticCard(card1);
		this.player.addPoliticCard(card2);
		this.player.getRichness().setCoins(10);
		this.player.getAssistantsPool().setAssistants(10);
		this.player.setEmporium(new Emporium(this.player));
		BuyPermitTile action = new BuyPermitTile(cards, region, choosenTile);
		action.runAction(player, board);
		List<BusinessPermitTile> cards = player.getAvailableBusinessPermits();
		assertEquals(new ArrayList<>(), cards);
	}
	
	/**
	 * @throws NegativeNumberException 
	 * 
	 */
	@Test
	public void testRunActionShouldGiveTheTileIfThePlayerHas4MatchesAndEnoughMoneyForTheSteps() throws NegativeNumberException{
		PoliticCard card1 = new PoliticCard(null, true);
		PoliticCard card2 = new PoliticCard(Color.BLUE, false);
		PoliticCard card3 = new PoliticCard(Color.BLACK, false);
		PoliticCard card4 = new PoliticCard(Color.WHITE, false);
		council.getCouncillors().add(new Councillor(Color.BLUE));
		council.getCouncillors().add(new Councillor(Color.BLACK));
		council.getCouncillors().add(new Councillor(Color.RED));
		council.getCouncillors().add(new Councillor(Color.WHITE));
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
		BuyPermitTile action = new BuyPermitTile(cards, region, choosenTile);
		action.runAction(player, board);
		List<BusinessPermitTile> card = player.getAvailableBusinessPermits();
		assertEquals(choosenTile.toString(), card.get(0).toString());
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
	
	/**
	 * tests if runAction() works properly when the player doesn't have enough money for the match payment
	 * @throws NegativeNumberException
	 */
	@Test
	public void testRunActionShouldntGiveTheCardWhenThePlayerDoesntHaveEnoughMoney() throws NegativeNumberException{
		PoliticCard card1 = new PoliticCard(null, true);
		PoliticCard card2 = new PoliticCard(Color.BLUE, false);
		PoliticCard card3 = new PoliticCard(Color.BLACK, false);
		council.getCouncillors().add(new Councillor(Color.BLUE));
		council.getCouncillors().add(new Councillor(Color.BLACK));
		council.getCouncillors().add(new Councillor(Color.RED));
		council.getCouncillors().add(new Councillor(Color.WHITE));
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
		BuyPermitTile action = new BuyPermitTile(cards, region, choosenTile);
		action.runAction(player, board);
		List<BusinessPermitTile> cards = player.getAvailableBusinessPermits();
		assertEquals(new ArrayList<>(), cards);
	}
	
	/**
	 * it tests if runAction() works properly when the player doesn't have enough money for the jolly
	 */
	@Test
	public void testRunActionShouldntGiveTheCardIfThePlayerDoesntHaveMoneyForTheJolly() throws NegativeNumberException{
		PoliticCard card1 = new PoliticCard(null, true);
		PoliticCard card2 = new PoliticCard(Color.BLUE, false);
		council.getCouncillors().add(new Councillor(Color.BLUE));
		council.getCouncillors().add(new Councillor(Color.BLACK));
		council.getCouncillors().add(new Councillor(Color.RED));
		council.getCouncillors().add(new Councillor(Color.WHITE));
		this.cards = new ArrayList<>();
		this.cards.add(card1);
		this.cards.add(card2);
		this.player.addPoliticCard(card1);
		this.player.addPoliticCard(card2);
		this.player.getRichness().setCoins(7);
		this.player.getAssistantsPool().setAssistants(10);
		BuyPermitTile action = new BuyPermitTile(cards, region, choosenTile);
		action.runAction(player, board);
		List<BusinessPermitTile> cards = player.getAvailableBusinessPermits();
		assertEquals(new ArrayList<>(), cards);
	}

}
