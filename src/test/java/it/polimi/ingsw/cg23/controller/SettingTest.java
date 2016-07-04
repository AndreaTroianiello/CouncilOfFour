package it.polimi.ingsw.cg23.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.controller.Setting;
import it.polimi.ingsw.cg23.server.model.City;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.Type;
import it.polimi.ingsw.cg23.server.model.components.BonusKing;
import it.polimi.ingsw.cg23.server.model.components.Deck;
import it.polimi.ingsw.cg23.server.model.components.King;
import it.polimi.ingsw.cg23.server.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.server.model.components.PoliticCard;
import it.polimi.ingsw.cg23.server.model.components.RegionDeck;

public class SettingTest {

	private Setting s = new Setting();
	private Type type1, type2;
	private List<Integer> bonuses;
	private List<City> cities1, cities2;
	private List<Region> regions;
	private List<Player> player;
	private List<PoliticCard> cartePolitiche;
	private BonusKing bonusKing;

	@Before
	public void setUp() throws Exception {
		// Set up the bonus king
		bonuses = new ArrayList<>();
		bonuses.add(10);
		bonuses.add(3);
		bonuses.add(0);
		bonusKing = new BonusKing(bonuses);

		regions = new ArrayList<>();
		player = new ArrayList<>();
		cities1 = new ArrayList<>();
		cities2 = new ArrayList<>();

		// Set up the types.
		type1 = new Type("Gold", 10, bonusKing);
		type2 = new Type("Purple", 10, bonusKing);

		// Set up the regions and cities
		regions.add(new Region("Region0", 5, new RegionDeck(2), bonusKing));
		regions.add(new Region("Region1", 5, new RegionDeck(2), bonusKing));

		cities1.add(new City('A', "Aosta", type1, regions.get(0)));
		cities1.add(new City('B', "Bari", type1, regions.get(1)));
		cities1.add(new City('C', "Crotone", type1, regions.get(0)));
		cities1.add(new City('R', "Roma", type1, regions.get(1)));
		cities1.add(new City('P', "Palermo", type2, regions.get(0)));

		cities2.add(new City('A', "Aosta", type1, regions.get(0)));
		cities2.add(new City('B', "Bari", type1, regions.get(1)));
		cities2.add(new City('C', "Crotone", type1, regions.get(0)));
		cities2.add(new City('R', "Roma", type1, regions.get(1)));

		player.add(new Player("player1", new NobilityTrack(3)));
		player.add(new Player("player2", new NobilityTrack(3)));

		cartePolitiche = s.politicList(13, 12);
	}

	/**
	 * it tests if king() return the king's city when there is a purple city in
	 * the list passed, and if it return null otherwise
	 */
	@Test
	public void testKingShouldReturnTheKingCityIfInTheListThereIsARegionWhitPurpleTypeAndNullOtherwise() {
		King k1 = s.king(cities1);
		assertEquals(k1.getCity().getName(), "Palermo");
		King k2 = s.king(cities2);
		assertEquals(k2, null);
	}

	/**
	 * it tests if 4 politic's cards are added to the player's hand when the int
	 * parameter is 4
	 */
	@Test
	public void testPescaShouldGiveThePlayer4PoliticsCardsIfTheParameterIs4() {
		s.pesca(new Deck(cartePolitiche), player, 4);
		assertEquals(player.get(0).getHand().size(), 4);
	}

	/**
	 * it tests if the color's array's length is 6
	 */
	@Test
	public void testColorShouldCreateAnArrayWith6ColorInIt() {
		assertEquals(s.color().length, 6);
	}

	/**
	 * it tests if politicList create a list of n(number of cards per
	 * color)*color(number of color)+k(number of jolly) politic's cards
	 */
	@Test
	public void testPoliticListShouldCreateAListOfPoliticsCards() {
		int n = 10;
		int k = 2;
		int col = s.color().length;
		assertEquals(s.politicList(n, k).size(), n * col + k);
	}

	/**
	 * it tests if createType creates 5 types
	 */
	@Test
	public void testCreateTypeShouldCreates5Types() {
		assertEquals(s.createType(bonusKing).size(), 5);
	}

	/**
	 * it tests nobilityTrackFill fill the nobilityTrack with 21 boxes and if
	 * there are 11 boxes occupied by bonuses
	 */
	@Test
	public void testNobilityTrackFillShouldFillTheNobilityTrackWith21BoxesAnd11OfThemHaveBonuses() {
		NobilityTrack nt = new NobilityTrack(21);
		nt = s.nobilityTrackFill(nt);
		for (int i = 0; i < nt.getNobilityBoxes().size(); i++) {
			assertNotNull(nt.getNobilityBoxes().get(i).getBonus());
		}
		assertEquals(nt.getNobilityBoxes().size(), 21);
		int totNt = 0;// numero di caselle del nobility track occupate
		for (int i = 0; i < nt.getNobilityBoxes().size(); i++) {
			if (nt.getNobilityBoxes().get(i).getBonus().size() != 0)
				totNt++;
		}
		assertEquals(totNt, 11);
	}

}
