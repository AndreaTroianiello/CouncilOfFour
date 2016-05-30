package it.polimi.ingsw.cg23;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.controller.creation.CreateCostruction;
import it.polimi.ingsw.cg23.model.City;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.Region;
import it.polimi.ingsw.cg23.model.Type;
import it.polimi.ingsw.cg23.model.bonus.BonusCoin;
import it.polimi.ingsw.cg23.model.components.BonusKing;
import it.polimi.ingsw.cg23.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.model.components.RegionDeck;
import it.polimi.ingsw.cg23.view.PrintMap;

public class PrintMapTest {

	PrintMap map=new PrintMap();
	CreateCostruction cc=new CreateCostruction();
	
	private Type type1,type2;
	private List<Region> regions;
	private List<Integer> bonuses;
	private List<Player> player;
	private List<City> cities;
	private BonusKing bonusKing;
	
	@Before
	public void setUp(){
		//Set up the bonus king
		bonuses=new ArrayList<>();
		bonuses.add(10);
		bonuses.add(3);
		bonuses.add(0);
		bonusKing=new BonusKing(bonuses);

		regions=new ArrayList<>();
		cities=new ArrayList<>();
		player=new ArrayList<>();

		//Set up the types.
		type1=new Type("Gold",10,bonusKing);
		type2=new Type("purple",10,bonusKing);
		
		//Set up the regions and cities
		regions.add(new Region("Region0",5,new RegionDeck(2),bonusKing));
		regions.add(new Region("Region1",5,new RegionDeck(2),bonusKing));
		cities.add(new City('A', "Aosta", type1 , regions.get(0)));
		cities.add(new City('B', "Bari", type2 , regions.get(0)));
		cities.add(new City('C', "Crotone", type1 , regions.get(0)));
		cities.add(new City('R', "Roma", type1 , regions.get(1)));
		cities.add(new City('P', "Palermo", type2 , regions.get(1)));
		
		cities.get(0).addNeighbor(cities.get(1));	
		
		player.add(new Player("player1",10,100,new NobilityTrack(3)));
		player.add(new Player("player2", 10, 10,new NobilityTrack(3)));
		
	}
	
	@Test
	public void cityfromRegionTest() {
		
		assertEquals(map.getCityfromRegion(regions).size(), cities.size());
	}
	
	@Test
	public void cityBonusTest() {
		cities.get(0).addBonus(new BonusCoin(10));//aggiungo il bonus alla citta'
		assertEquals(map.cityBonus(cities.get(0)), "10Coin");
		assertEquals(map.cityBonus(cities.get(1)), "");
	}
	
	@Test
	public void playerTest(){
		assertNotNull(map.createPlayerInfo(player));
	}
	
	@Test
	public void addSpaceTest(){
		int n=10;
		String nome="prova";
		assertEquals(map.addSpace(nome, n).length(),n);
		assertEquals(map.addSpace(nome, n),"prova     ");
		assertEquals(map.addSpace("provaprova", n).length(),n);
	}
	
	@Test
	public void addSpaceIntTest(){
		int n=5;
		int numero=3;
		assertEquals(map.addSpace(numero, n).length(),n);
		assertEquals(map.addSpace(12345, n).length(), n);
		assertEquals(map.addSpace(-12345, n).length(), n+1);
	}
	
	@Test
	public void printNameTest(){
		int n=50;
		assertEquals(map.printName(regions, n).length(),n*regions.size());
	}
	
	@Test
	public void addminusTest(){
		int n=50;
		assertEquals(map.addMinus(n).length(), n);
	}
	
	@Test
	public void cityNeighboursTest(){
		assertEquals(map.getNeighbourID(cities.get(0)), "Vicini: "+cities.get(1).getId());
	}
}
