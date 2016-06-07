package it.polimi.ingsw.cg23.view;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.model.City;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.Type;
import it.polimi.ingsw.cg23.server.model.bonus.BonusAdditionalAction;
import it.polimi.ingsw.cg23.server.model.bonus.BonusAssistants;
import it.polimi.ingsw.cg23.server.model.bonus.BonusCoin;
import it.polimi.ingsw.cg23.server.model.bonus.BonusVictoryPoints;
import it.polimi.ingsw.cg23.server.model.components.BonusKing;
import it.polimi.ingsw.cg23.server.model.components.BusinessPermitTile;
import it.polimi.ingsw.cg23.server.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.server.model.components.RegionDeck;
import it.polimi.ingsw.cg23.server.view.CreateMap;


public class PrintMapTest {

CreateMap map=new CreateMap();
	
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
		
		//Set up the bonuses of the cities
		cities.get(0).addBonus(new BonusVictoryPoints(1));
		cities.get(1).addBonus(new BonusCoin(10));
		cities.get(2).addBonus(new BonusVictoryPoints(1));
		cities.get(3).addBonus(new BonusAssistants());
		
		List<Character> c1=Arrays.asList('A','B');
		List<Character> c2=Arrays.asList('C');
		List<Character> c3=Arrays.asList('N','O','P');
		
		//creo le carte permesso di costruzione
		BusinessPermitTile bpt1=new BusinessPermitTile(c1, regions.get(0).getName());
		BusinessPermitTile bpt2=new BusinessPermitTile(c2, regions.get(0).getName());
		BusinessPermitTile bpt3=new BusinessPermitTile(c3, regions.get(1).getName());
		
		//setto i bonus delle carte permesso
		bpt1.addBonus(new BonusCoin(10));
		bpt1.addBonus(new BonusAdditionalAction());
		bpt2.addBonus(new BonusVictoryPoints(1));
		bpt3.addBonus(new BonusAssistants());
		
		List<BusinessPermitTile> b1=new ArrayList<>();
		b1.add(bpt1);
		b1.add(bpt2);
		b1.add(bpt3);
		List<BusinessPermitTile> b2=new ArrayList<>();
		b2.add(bpt3);
		b2.add(bpt2);
		b2.add(bpt1);
		
		regions.get(0).getDeck().setBusinessPermitTiles(b1);
		regions.get(1).getDeck().setBusinessPermitTiles(b2);
		
	}
	/*
	@Test
	public void cityfromRegionTest() {
		
		assertEquals(map.getCityfromRegion(regions).size(), cities.size());
	}
	
	@Test
	public void cityBonusTest() {
		assertEquals(map.cityBonus(cities.get(0)), "1VictoryPoints");
		assertEquals(map.cityBonus(cities.get(4)), "");
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
	
	@Test
	public void bonusCostructorTest(){
		int space=50;
		String n1=map.getBonusCostructor(regions, space, 0);
		assertEquals(n1.length(), 200);
		String n2=map.getBonusCostructor(regions, space, 1);
		assertEquals(n2.length(), 200);
		
	}*/

}
