package it.polimi.ingsw.cg23.controller.creation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.controller.creation.CreateBonus;
import it.polimi.ingsw.cg23.server.model.City;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.Type;
import it.polimi.ingsw.cg23.server.model.components.BusinessPermitTile;

public class CreateBonusTest {

	private CreateBonus cb;
	private BusinessPermitTile bpt1, bpt2;
	private City c1, c2;

	@Before
	public void setUp() {
		cb = new CreateBonus();
		List<Character> idcity = new ArrayList<>();
		idcity.add('A');
		idcity.add('B');
		idcity.add('C');

		bpt1 = new BusinessPermitTile(idcity, "region0");
		bpt2 = new BusinessPermitTile(idcity, "region1");

		c1 = new City('M', "Milano", new Type("color", 5, null), new Region("region1", 5, null, null));
		c2 = new City('C', "Como", new Type("Purple", 5, null), new Region("region0", 5, null, null));

	}

	/**
	 * it tests if bonusList return a list with size 9
	 */
	@Test
	public void testBonusList() {
		assertEquals(cb.bonusList(null).size(), 9);
		assertNotNull(cb.bonusList(null));
	}

	/**
	 * it tests if bonusKing return an object bonusKing with 6 bonuses in it
	 */
	@Test
	public void testBonusKingShouldReturnABonusKingWith6BonusesInIt() {
		assertEquals(cb.bonusKing().getBonusValues().size(), 6);
		assertNotNull(cb.bonusKing());
	}

	/**
	 * it tests if getCondtructorBonus set the bonuses of tile if the list of
	 * the bonus is full, it doesn't do it otherwise
	 */
	@Test
	public void testCostructorBonusShouldSetTheBonusOfTheTileIfTheListIsFilledAndItShouldntOtherwise() {
		String bonus1 = "3VictoryPoints,1Assistants";
		String bonus2 = "3Coin,3Politics,1Nobility";
		cb.getCostructorBonus(bpt1, bonus1);
		assertEquals(bpt1.getBonusTile().size(), 0);// la lista e' vuota

		cb.bonusList(null);// riempio la lista con i bonus
		cb.getCostructorBonus(bpt1, bonus1);
		assertNotNull(bpt1.getBonusTile());
		assertEquals(bpt1.getBonusTile().size(), 2);

		cb.getCostructorBonus(bpt2, bonus2);
		assertNotNull(bpt2.getBonusTile());
		assertEquals(bpt2.getBonusTile().size(), 3);
	}

	/**
	 * it tests if getCityBonus works properly
	 */
	@Test
	public void testCityBonus() {
		cb.bonusList(null);// creo la lista dei bonus
		cb.getCityBonus(c1);
		assertNotNull(c1.getToken());

		cb.getCityBonus(c2);
		assertEquals(c2.getToken().size(), 0);// 0 la citta' del re non ha bonus
												// (in posizione 10 nel file
												// xml)
	}
}
