package it.polimi.ingsw.cg23;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.model.City;
import it.polimi.ingsw.cg23.model.Region;
import it.polimi.ingsw.cg23.model.Type;
import it.polimi.ingsw.cg23.model.components.Councillor;
import it.polimi.ingsw.cg23.model.components.King;

public class KingTest {

	private List<Councillor> councillor;
	private Region region;
	
	@Before
	public void setUp() throws Exception {
		councillor=new ArrayList<>();
		for(int index=0;index<4;index++)
			councillor.add(new Councillor(Color.BLACK));
		region=new Region("Region", 0, null, null);
		Type type=new Type("Type", 0, null);
		new City('A', "Aosta", type, region);
		new City('B', "Bari", type, region);
	}

	@Test
	public void testGetCity() {
		King king=new King(region.searchCityById('A'));
		assertEquals(king.getCity(), region.searchCityById('A'));
	}

	@Test
	public void testSetCity() {
		King king=new King(region.searchCityById('A'));
		king.setCity(region.searchCityById('B'));
		assertEquals(king.getCity(), region.searchCityById('B'));
	}

	@Test
	public void testGetCouncil() {
		King king=new King(region.searchCityById('A'));
		assertNotNull(king.getCouncil());
		assertEquals(king.getCouncil().getCouncillors().size(),0);
		king.getCouncil().getCouncillors().addAll(councillor);
		assertNotEquals(king.getCouncil().getCouncillors().size(),0);
	}

}
