package it.polimi.ingsw.cg23.server.model.components;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.model.City;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.Type;

public class KingTest {

	private List<Councillor> councillor;
	private Region region;

	@Before
	public void setUp() throws Exception {
		councillor = new ArrayList<>();
		for (int index = 0; index < 4; index++)
			councillor.add(new Councillor(Color.BLACK));
		region = new Region("Region", 0, null, null);
		Type type = new Type("Type", 0, null);
		new City('A', "Aosta", type, region);
		new City('B', "Bari", type, region);
	}

	/**
	 * it tests if getCity works properly
	 */
	@Test
	public void testGetCity() {
		King king = new King(region.searchCityById('A'));
		assertEquals(king.getCity(), region.searchCityById('A'));
	}

	/**
	 * it tests if setCity actually set the right city
	 */
	@Test
	public void testSetCityShouldSetTheCityKingAsTheCityPassed() {
		King king = new King(region.searchCityById('A'));
		king.setCity(region.searchCityById('B'));
		assertEquals(king.getCity(), region.searchCityById('B'));
	}

	/**
	 * it tests if getCouncil works properly
	 */
	@Test
	public void testGetCouncil() {
		King king = new King(region.searchCityById('A'));
		assertNotNull(king.getCouncil());
		assertEquals(king.getCouncil().getCouncillors().size(), 0);
		king.getCouncil().getCouncillors().addAll(councillor);
		assertNotEquals(king.getCouncil().getCouncillors().size(), 0);
	}

}
