package it.polimi.ingsw.cg23.model;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.model.City;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.Type;
import it.polimi.ingsw.cg23.server.model.bonus.BonusVictoryPoints;
import it.polimi.ingsw.cg23.server.model.components.BonusKing;
import it.polimi.ingsw.cg23.server.model.components.BusinessPermitTile;
import it.polimi.ingsw.cg23.server.model.components.Council;
import it.polimi.ingsw.cg23.server.model.components.Councillor;
import it.polimi.ingsw.cg23.server.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.server.model.components.RegionDeck;
import it.polimi.ingsw.cg23.server.model.exception.NegativeNumberException;

public class MapTest {
	private List<Region> regions;
	private Player p,p2;
	private Type type1,type2;
	private List<BusinessPermitTile> tiles;
	private List<Integer> bonuses;
	private List<City> cities1;
	private List<City> cities2;

	/**
	 * The set up of the tests.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		p=new Player("player1",10,100,new NobilityTrack(3));
		p2=new Player("player2", 10, 10,new NobilityTrack(3));
		tiles=new ArrayList<>();
		regions=new ArrayList<>();
		for(int i=0;i<10;++i){
			List<Character> ids=new ArrayList<>();
			ids.add('A');
			tiles.add(new BusinessPermitTile(ids,null));
		}

		//Set up the bonus king
		bonuses=new ArrayList<>();
		bonuses.add(10);
		bonuses.add(3);
		bonuses.add(0);
		BonusKing bonusKing=new BonusKing(bonuses);


		cities1=new ArrayList<>();
		cities2=new ArrayList<>();

		//Set up the types.
		type1=new Type("Gold",10,bonusKing);
		type2=new Type("Silver",10,bonusKing);

		//Set up the regions and cities
		regions.add(new Region("Region0",5,new RegionDeck(2),bonusKing));
		regions.add(new Region("Region1",5,new RegionDeck(2),bonusKing));
		cities1.add(new City('A', "Aosta", type1 , regions.get(0)));
		cities1.add(new City('B', "Bari", type2 , regions.get(0)));
		cities1.add(new City('C', "Crotone", type1 , regions.get(0)));
		cities2.add(new City('R', "Roma", type1 , regions.get(1)));
		cities2.add(new City('P', "Palermo", type2 , regions.get(1)));


		//Set up the bonuses of the cities
		cities1.get(0).addBonus(new BonusVictoryPoints(1));
		cities1.get(1).addBonus(new BonusVictoryPoints(1));
		cities1.get(2).addBonus(new BonusVictoryPoints(1));
		cities2.get(0).addBonus(new BonusVictoryPoints(1));
		cities2.get(1).addBonus(new BonusVictoryPoints(1));

		//Set up the neighbors of the cities.
		cities1.get(0).addNeighbor(cities1.get(1));						//A near B
		cities1.get(1).addNeighbor(cities1.get(0));						//B near A
		cities1.get(0).addNeighbor(cities1.get(2));						//A near C
		cities1.get(2).addNeighbor(cities1.get(0));						//C near A
		cities1.get(0).addNeighbor(cities2.get(1));						//A near P
		cities2.get(1).addNeighbor(cities1.get(0));						//P near A
		cities2.get(0).addNeighbor(cities2.get(1));						//R near P
		cities2.get(1).addNeighbor(cities2.get(0));						//P near R
	}

	/**
	 * Tests the creation of the region.
	 */
	@Test
	public void testRegion() {
		Region r=regions.get(0);
		assertEquals(r.getName(),"Region0");
		assertNotNull(r.getCities());
		assertNotEquals(r.getCities().size(),0);
		assertNotNull(r.getCouncil());
		assertTrue(r.isBonusAvailable());
	}

	/**
	 * Tests cities' methods.
	 */
	@Test
	public void testCities() {
		assertEquals(regions.get(0).getCities().size(),3);
		assertNotEquals(regions.get(1).getCities().size(),0);
		City c1=regions.get(0).searchCityById('A');
		assertNotNull(c1);
		List<City> c2=regions.get(1).searchCitiesByType("Silver");
		assertEquals(c2.size(),1);
		City c3=regions.get(0).searchCityById('R');
		assertNull(c3);
		List<City> c4=regions.get(1).searchCitiesByType("Bronze");
		assertEquals(c4.size(),0);
		List<City> c5=type1.getCities();
		assertEquals(c5.size(),3);
		City c6=type1.searchCityById('B');
		assertNull(c6);
		City c7=type1.searchCityById('A');
		assertEquals(c7.getId(),'A');
		
		assertEquals((int) c1.minimumDistance(c2.get(0), new ArrayList<City>()),1);
	}

	/**
	 * Tests bonuses' methods.
	 */
	@Test
	public void testBonus() {
		for(City city: regions.get(0).getCities())
			try {
				int points=p.getVictoryTrack().getVictoryPoints();
				city.buildEmporium(p.getAvailableEmporium());
				assertNotEquals(p.getVictoryTrack().getVictoryPoints(),points);
			} catch (NegativeNumberException e) {
				assertEquals(p.getAssistantsPool().getAssistants(),10);
			}
		assertTrue(regions.get(0).completedRegion(p));
		assertFalse(regions.get(0).isBonusAvailable());
		for(City city: regions.get(0).getCities())
			try {
				int points=p2.getVictoryTrack().getVictoryPoints();
				city.buildEmporium(p2.getAvailableEmporium());
				assertNotEquals(p2.getVictoryTrack().getVictoryPoints(),points);
			} catch (NegativeNumberException e) {
				assertEquals(p2.getAssistantsPool().getAssistants(),10);
			}
		assertTrue(regions.get(0).completedRegion(p2));
		assertFalse(regions.get(1).completedRegion(p));
		assertFalse(type1.completedType(p));
		assertTrue(type1.isBonusAvailable());
		for(City city: regions.get(1).getCities())
			try {
				int points=p.getVictoryTrack().getVictoryPoints();
				city.buildEmporium(p.getAvailableEmporium());
				assertNotEquals(p.getVictoryTrack().getVictoryPoints(),points);
			} catch (NegativeNumberException e) {
				assertEquals(p.getAssistantsPool().getAssistants(),10);
			}
		assertTrue(type1.completedType(p));
		assertFalse(type1.isBonusAvailable());
	}

	/**
	 * Tests deck's methods.
	 */
	@Test
	public void testDeck() {
		RegionDeck rd=regions.get(0).getDeck();
		assertNotNull(rd);
		rd.setBusinessPermitTiles(tiles);
		assertEquals(rd.getShowedDeck().size(),2);
		assertEquals(rd.getHiddenDeckSize(),8);
	}

	/**
	 * Tests region's council.
	 */
	@Test
	public void testCouncil() {
		Council c=regions.get(0).getCouncil();
		assertNotNull(c);
		assertEquals(c.getCouncillors().size(),0);
		Councillor co=new Councillor(Color.BLACK);
		c.getCouncillors().add(co);
		assertEquals(co.getColor(),Color.BLACK);
		assertEquals(c.getCouncillors().size(),1);
	}



}
