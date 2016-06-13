package it.polimi.ingsw.cg23.controller.creation;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.controller.creation.CreateRegionCity;
import it.polimi.ingsw.cg23.server.model.City;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.components.BonusKing;

public class RegionCityTest {

	private CreateRegionCity crc;

	private List<Region> regions;
	private List<City> citta1, citta2, citta3;
	private List<Integer> bonuses;
	private BonusKing bonusKing;

	@Before
	public void setUp(){

		//Set up the bonus king
		bonuses=new ArrayList<>();
		bonuses.add(10);
		bonuses.add(3);
		bonuses.add(0);
		bonusKing=new BonusKing(bonuses);

		crc=new CreateRegionCity("RegionCity.xml");
		regions=new ArrayList<>();
		citta1=new ArrayList<>();
		citta2=new ArrayList<>();
		citta3=new ArrayList<>();
	}

	@Test
	public void regionTest() {
		regions=crc.createRegions(bonusKing);
		assertEquals(regions.size(), 3);
		assertNotNull(regions.get(0));
		assertNotNull(regions.get(1));
		assertNotNull(regions.get(2));
	}

	@Test
	public void cityTest() {
		regions=crc.createRegions(bonusKing);//regioni
		citta1=crc.createCities(0, regions.get(0), bonusKing);
		citta2=crc.createCities(1, regions.get(1), bonusKing);
		citta3=crc.createCities(2, regions.get(2), bonusKing);
		assertEquals(citta1.size(), 5);
		assertEquals(citta2.size(), 5);
		assertEquals(citta3.size(), 5);
		citta1.addAll(citta2);
		citta1.addAll(citta3);
		assertEquals(citta1.size(), 15);
		for(int i=0; i<citta1.size(); i++){
			assertNotNull(citta1.get(i));
		}
	}

	@Test
	public void neighbourTest() {
		regions=crc.createRegions(bonusKing);//regioni
		citta1=crc.createCities(0, regions.get(0), bonusKing);//citta'
		crc.addNeighbors(citta1);
		assertEquals(citta1.get(1).getNeighbors().get(0).getId(), 'A');

	}
}
