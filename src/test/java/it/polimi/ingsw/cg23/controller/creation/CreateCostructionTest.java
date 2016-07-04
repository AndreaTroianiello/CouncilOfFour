package it.polimi.ingsw.cg23.controller.creation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.controller.creation.CreateCostruction;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.components.BonusKing;
import it.polimi.ingsw.cg23.server.model.components.RegionDeck;

public class CreateCostructionTest {

	private CreateCostruction cc;
	private List<Region> reg;
	private List<Integer> bonuses;

	@Before
	public void setUp() {
		cc = new CreateCostruction();

		// Set up the bonus king
		bonuses = new ArrayList<>();
		reg = new ArrayList<>();
		bonuses.add(10);
		bonuses.add(3);
		bonuses.add(0);
		BonusKing bonusKing = new BonusKing(bonuses);

		reg.add(new Region("Coast", 5, new RegionDeck(2), bonusKing));
		reg.add(new Region("Hills", 5, new RegionDeck(2), bonusKing));
	}

	/**
	 * it tests if createRegionDeck fill the region's deck
	 */
	@Test
	public void testCreateRegionDeckShouldFillTheRegionDeck() {
		cc.createCardCostruction(null);
		cc.createRegionDeck(reg);
		assertFalse(reg.get(0).getDeck().isEmpty());
	}

	/**
	 * it tests if createCostruction creates the business permit tiles properly
	 */
	@Test
	public void testCreateCostruction() {
		assertEquals(cc.createCardCostruction(null).size(), 45);
	}

}
