package it.polimi.ingsw.cg23.utility;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.City;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.Type;
import it.polimi.ingsw.cg23.server.model.bonus.BonusAdditionalAction;
import it.polimi.ingsw.cg23.server.model.bonus.BonusAssistants;
import it.polimi.ingsw.cg23.server.model.bonus.BonusCityToken;
import it.polimi.ingsw.cg23.server.model.bonus.BonusCoin;
import it.polimi.ingsw.cg23.server.model.components.BonusKing;
import it.polimi.ingsw.cg23.server.model.components.BusinessPermitTile;
import it.polimi.ingsw.cg23.server.model.components.Councillor;
import it.polimi.ingsw.cg23.server.model.components.King;
import it.polimi.ingsw.cg23.server.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.server.model.components.RegionDeck;
import it.polimi.ingsw.cg23.server.model.exception.NegativeNumberException;
import it.polimi.ingsw.cg23.utility.MapSetting;

public class MapSettingTest {

	private MapSetting ms;
	private Type type1,type2;
	private List<Type> tipi;
	private List<Region> reg;
	private List<Player> giocatori;
	private List<Integer> bonuses;
	private List<City> cities;
	private King k;
	private NobilityTrack nt;
	private Board board;
	private List<BusinessPermitTile> bpt;

	@Before
	public void setUp() throws Exception {
		bpt=new  ArrayList<>();
		ms=new MapSetting();
		tipi=new ArrayList<>();
		reg=new ArrayList<>();
		giocatori=new ArrayList<>();

		//Set up the bonus king
		bonuses=new ArrayList<>();
		bonuses.add(10);
		bonuses.add(3);
		bonuses.add(0);
		BonusKing bonusKing=new BonusKing(bonuses);

		cities=new ArrayList<>();

		//Set up the types.
		type1=new Type("Gold",10,bonusKing);
		type2=new Type("Silver",10,bonusKing);
	

		tipi.add(type1);//aggiungo i tipi alla lista
		tipi.add(type2);
		
		
		//aggiungo regioni
		reg.add(new Region("Region0",5,new RegionDeck(2),bonusKing));
		reg.add(new Region("Region1",5,new RegionDeck(2),bonusKing));
		reg.get(0).setBonusUnavailable();
		
		//aggiungo i consiglieri alle regioni
		reg.get(0).getCouncil().getCouncillors().add(new Councillor(Color.blue));
		reg.get(0).getCouncil().getCouncillors().add(new Councillor(Color.red));
		reg.get(0).getCouncil().getCouncillors().add(new Councillor(Color.gray));
		reg.get(1).getCouncil().getCouncillors().add(new Councillor(Color.black));
		reg.get(1).getCouncil().getCouncillors().add(new Councillor(Color.pink));
		
		//aggiungo le citta'
		cities.add(new City('A', "Aosta", type1 , reg.get(0)));
		cities.add(new City('B', "Bari", type2 , reg.get(0)));
		cities.add(new City('C', "Crotone", type1 , reg.get(0)));
		cities.add(new City('R', "Roma", type1 , reg.get(1)));
		cities.add(new City('P', "Palermo", type2 , reg.get(1)));
		cities.add(new City('M', "Milano", type2 , reg.get(1)));

		//vicini delle citta'
		cities.get(0).addNeighbor(cities.get(1));
		cities.get(1).addNeighbor(cities.get(2));
		cities.get(2).addNeighbor(cities.get(3));
		cities.get(3).addNeighbor(cities.get(4));
		cities.get(4).addNeighbor(cities.get(5));
		cities.get(5).addNeighbor(cities.get(0));

		//bonus alle citta'
		cities.get(0).addBonus(new BonusAdditionalAction());
		cities.get(1).addBonus(new BonusAssistants());
		cities.get(2).addBonus(new BonusCoin(10));

		//re
		k=new King(cities.get(0));

		//players
		nt=new NobilityTrack(20);
		giocatori.add(new Player("playerplayerplayer11", nt));
		giocatori.add(new Player("player2", nt));

		//setto un tipo gia' preso da un giocatore//RIVEDERE
		tipi.get(0).runBonusType(giocatori.get(1));
		
		//setto i giocatori
		try {
			giocatori.get(0).getAssistantsPool().setAssistants(10);
			giocatori.get(0).getAssistantsPool().setAssistants(2147483647);
			giocatori.get(1).setNobilityBoxPoistion(-5);
		} catch (NegativeNumberException e) {
			e.printStackTrace();
		}

		//creazione carte costruzione
		List<Character> c1=new ArrayList<>();
		c1.add('A');
		c1.add('B');
		c1.add('C');
		BusinessPermitTile b1=new BusinessPermitTile(c1, reg.get(0).getName());
		b1.addBonus(new BonusCoin(10));
		List<Character> c2=new ArrayList<>();
		c2.add('R');
		c2.add('M');
		c2.add('C');
		BusinessPermitTile b2=new BusinessPermitTile(c2, reg.get(0).getName());
		b2.addBonus(new BonusAdditionalAction());
		List<BusinessPermitTile> bl1=new ArrayList<>();
		bl1.add(b1);
		bl1.add(b2);
		reg.get(0).getDeck().setBusinessPermitTiles(bl1);
		bpt.addAll(bl1);

		//creazione carte costruzione
		List<Character> c3=new ArrayList<>();
		c3.add('R');
		c3.add('B');
		c3.add('P');
		BusinessPermitTile b3=new BusinessPermitTile(c3, reg.get(0).getName());
		b3.addBonus(new BonusAssistants());
		b3.addBonus(new BonusAdditionalAction());
		List<Character> c4=new ArrayList<>();
		c4.add('R');
		c4.add('A');
		c4.add('C');
		BusinessPermitTile b4=new BusinessPermitTile(c4, reg.get(0).getName());
		b4.addBonus(new BonusCityToken(1));
		List<BusinessPermitTile> bl2=new ArrayList<>();
		bl2.add(b3);
		bl2.add(b4);
		reg.get(1).getDeck().setBusinessPermitTiles(bl2);
		bpt.addAll(bl2);

		board=new Board(null, reg, tipi, nt, k, bonusKing);
		board.addPlayer(giocatori.get(0));
		board.addPlayer(giocatori.get(1));
	}

	/**
	 * it tests if getCityFromRegion return the list of cities with all the Cities in cities
	 */
	@Test
	public void testGetCityFromRegionShouldCreateAListOfCitiesWithAllTheCitiesInCities() {
		List<City> citta=ms.getCityfromRegion(reg);

		assertEquals(citta.size(), 6);
		for(int i=0; i<cities.size(); i++){//puo' essere che non tutti i parametri siano settati
			assertEquals(citta.get(i).getName(), cities.get(i).getName());
			assertEquals(citta.get(i).getId(), cities.get(i).getId());
			assertEquals(citta.get(i).getNeighbors(), cities.get(i).getNeighbors());
			assertEquals(citta.get(i).getRegion(), cities.get(i).getRegion());
			assertEquals(citta.get(i).getEmporiums(), cities.get(i).getEmporiums());
			assertEquals(citta.get(i).getToken(), cities.get(i).getToken());
			assertEquals(citta.get(i).getType(), cities.get(i).getType());
		}
	}

	/**
	 * it tests if cityBonus converts the bonus in a string properly
	 */
	@Test
	public void testCityBonusShouldConvertTheBonusInAString(){
		assertEquals(ms.cityBonus(cities.get(0)), "AdditionalAction");
		assertEquals(ms.cityBonus(cities.get(1)), "0Assistants");
		assertEquals(ms.cityBonus(cities.get(2)), "10Coin");

	}

	/**
	 * it tests if addSpace adds spaces until the string reaches the wanted length if the string is smaller then
	 * the wanted length, and does nothing if it is greater
	 */
	@Test
	public void testAddSpaceShouldAddSpaceUntilTheStringReachTheWantedLength(){
		String prova="prova";
		int space=10;
		assertEquals(ms.addSpace(prova, space).length(), space);
		assertEquals(ms.addSpace("provaprova", space).length(), space);
		assertEquals(ms.addSpace(34, space).length(), space);
		assertEquals(ms.addSpace(1112234, 6).length(), 7);
		
	}

	/**
	 * it tests if printName returns the names of the regions in a string of the wanted length 
	 */
	@Test
	public void testPrintNameShouldContainsTheNameOfAllTheRegionsInReg(){
		String nome=ms.printName(reg, 50);
		assertTrue(nome.contains(reg.get(0).getName().toUpperCase()));
		assertTrue(nome.contains(reg.get(1).getName().toUpperCase()));
		assertEquals(nome.length(), 50*reg.size());
	}

	/**
	 * it tests if createCostructionShowed return a string with all the showed tiles of all the regions
	 */
	@Test
	public void testCreateCostructionShowedShouldReturnAStringWithAllTheNameOfTheShowedTilesOfAllTheRegions(){
		String nome=ms.createCostructionShowed(reg, 50);
		assertTrue(nome.contains(reg.get(0).getName()));
		assertTrue(nome.contains(reg.get(1).getName()));
		assertTrue(nome.contains("Business Permit Tiles"));
		assertEquals(nome.length(), 50*6*4+6);
		for(int i=0; i<bpt.size(); i++){
			assertTrue(nome.contains(bpt.get(i).getCitiesId().toString()));
			assertTrue(nome.contains(bpt.get(i).getBonusTile().get(0).getName()));
		}
	}

	/**
	 * it tests if getNeighbourID returns a string with all the neighbors of the city passed
	 */
	@Test
	public void testNeighbourIDShouldReturnAStringWithAllTheNeighborIDOfTheCity(){
		for(int i=0; i<cities.size(); i++){
			String nome=ms.getNeighbourID(cities.get(i));
			assertTrue(nome.contains("Neighbors:"));
			assertTrue(nome.contains(""+cities.get(i).getNeighbors().get(0).getId()));
		}
	}
	
	/**
	 * it tests if addMinus returns a string with the wanted number of '-'
	 */
	@Test
	public void testAddMinusShouldReturnAStringWithTheWantedNumberOfMinus(){
		String nome=ms.addMinus(20);
		assertEquals(nome.length(), 20);
		assertNotEquals(nome, "");
		assertNotEquals(nome, null);
		assertTrue(nome.contains("--"));
	}
	
	/**
	 * it tests if councillors returns a string with the region's council 
	 */
	@Test
	public void testCouncillorsShouldReturnAStringWithTheNameOfTheRegionAndTheCouncil(){
		for(int i=0; i<reg.size(); i++){
			String nome=ms.councillors(reg);
			assertTrue(nome.contains(reg.get(i).getName()+ " Council"));
			assertNotEquals(nome, "");
			assertNotEquals(nome, null);
			
		}
	}
	
	/**
	 * it tests if bonusCouncilKingType returns a string with the king's council and his type
	 */
	@Test
	public void testBonusCouncilKingTypeShouldReturnAStringWithTheKingsCouncilAndHisType(){
		String nome=ms.bonusCouncilKingType(board);
		assertEquals(nome.length(), 50*3);
		assertTrue(nome.contains("King Council:"));
		assertTrue(nome.contains("Bonus king available:"));
		assertTrue(nome.contains("Available type:"));
		assertNotEquals(nome, "");
		assertNotEquals(nome, null);
		assertTrue(nome.contains(tipi.get(0).getName()));
		assertTrue(nome.contains(tipi.get(1).getName()));
	}

}
