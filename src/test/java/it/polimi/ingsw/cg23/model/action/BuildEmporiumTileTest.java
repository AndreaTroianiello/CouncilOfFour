package it.polimi.ingsw.cg23.model.action;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.City;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.Region;
import it.polimi.ingsw.cg23.model.Type;
import it.polimi.ingsw.cg23.model.components.BonusKing;
import it.polimi.ingsw.cg23.model.components.BusinessPermitTile;
import it.polimi.ingsw.cg23.model.components.Deck;
import it.polimi.ingsw.cg23.model.components.Emporium;
import it.polimi.ingsw.cg23.model.components.King;
import it.polimi.ingsw.cg23.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.model.components.PoliticCard;

public class BuildEmporiumTileTest {
	
	BusinessPermitTile card;
	Player player;
	Board board;
	City city;
	List<Integer> bonusKing = new ArrayList<>();
	Type type = new Type("purple", 5, new BonusKing(bonusKing));


	@Before
	public void setUp() throws Exception {
		List<Character> cityIDs = new ArrayList<>();
		cityIDs.add('I');
		card = new BusinessPermitTile(cityIDs, null);
		player = new Player("player1", 10, 100, new NobilityTrack(3));
		int n = 5;
		bonusKing.add(n);	
		List<Type> types = new ArrayList<>();
		types.add(type);
		King king = new King(new City('J', "Juvelar", type, new Region(null, 0, null, new BonusKing(bonusKing))));
		List<Region> regions = new ArrayList<>();
		Region region = new Region("regione", 0, null, new BonusKing(bonusKing));
		regions.add(region);
		city = new City('I', "Ioio", new Type(null, 0, new BonusKing(bonusKing)), region);
		region.addCity(city);
		board = new Board(new Deck(new ArrayList<PoliticCard>()), regions, types, null, king);
	}

	
	/**
	 * it tests if getCityID works properly
	 */
	@Test
	public void testGetCityIDShouldReturnTheCityID() {
		BuildEmporiumTile action = new BuildEmporiumTile(null, city);
		City city = action.getCity();
		assertEquals(action.getCity(), city);
	}

	/**
	 * it tests if getCard works properly
	 */
	@Test
	public void testGetCard() {
		BuildEmporiumTile action = new BuildEmporiumTile(card, city);
		assertEquals(card, action.getCard());
	}

	/**
	 * it tests if toStign works properly
	 */
	@Test
	public void testToString() {
		BuildEmporiumTile action = new BuildEmporiumTile(null, null);
		assertEquals("BuildEmporiumTile [card=null, city=null]", action.toString());
	}
	
	/**
	 * it tests if runAction() works properly when it's all fine
	 */
	@Test
	public void testRunActionShouldBuildAnEmporiumIfItsAllFine(){
		BuildEmporiumTile action = new BuildEmporiumTile(card, city);
		action.runAction(player, board);
		assertEquals(true, this.city.containsEmporium(player));
	}

	/**
	 * it tests if runAction() works properly when the player doesn't have assistants
	 */
	@Test
	public void testRunActionShouldntBuildIfThereIsAnEmporiumInTheCityAndThePlayerDoesntHaveAssistants(){
		Player player2 = new Player("player2", 0, 100, new NobilityTrack(3));
		BuildEmporiumTile action = new BuildEmporiumTile(card, city);
		this.city.getEmporiums().add(new Emporium(player));
		action.runAction(player2, board);
		assertEquals(false, this.city.containsEmporium(player2));
	}
}
