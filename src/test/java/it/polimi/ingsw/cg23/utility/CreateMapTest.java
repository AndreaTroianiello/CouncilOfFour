/**
 * 
 */
package it.polimi.ingsw.cg23.utility;

import static org.junit.Assert.*;

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
import it.polimi.ingsw.cg23.server.model.components.King;
import it.polimi.ingsw.cg23.server.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.server.model.components.RegionDeck;
import it.polimi.ingsw.cg23.server.model.exception.NegativeNumberException;
import it.polimi.ingsw.cg23.utility.CreateMap;

/**
 * @author viga94
 *
 */
public class CreateMapTest {
		
	private CreateMap cm;
	private Type type1,type2;
	private List<Type> tipi;
	private List<Region> reg;
	private List<Player> giocatori;
	private List<Integer> bonuses;
	private List<City> cities;
	private King k;
	private NobilityTrack nt;
	private Board board;
	
	@Before
	public void setUp(){
		tipi=new ArrayList<>();
		cm=new CreateMap();
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
		cities.add(new City('A', "Aosta", type1 , reg.get(0)));
		cities.add(new City('B', "Bari", type2 , reg.get(0)));
		cities.add(new City('C', "Crotone", type1 , reg.get(0)));
		cities.add(new City('R', "Roma", type1 , reg.get(1)));
		cities.add(new City('P', "Palermo", type2 , reg.get(1)));
		cities.add(new City('M', "Milano", type2 , reg.get(1)));
		
		//re
		k=new King(cities.get(0));
		
		//players
		nt=new NobilityTrack(20);
		giocatori.add(new Player("playerplayerplayer11", nt));
		giocatori.add(new Player("player2", nt));
		
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
		
		board=new Board(null, reg, tipi, nt, k, bonusKing);
		board.addPlayer(giocatori.get(0));
		board.addPlayer(giocatori.get(1));
	}

	/**
	 * it tests if after createMap the map contains the name of the regions, of the cities, of the players, 
	 * and if each city contains his type 
	 */
	@Test
	public void testCreateMapShouldCreateAMapThatContainsTheNameOfRegionsCitiesTypesAndPlayers() {
		String map=cm.createMap(reg, giocatori, k);
		assertTrue(map.contains(reg.get(0).getName().toUpperCase()));//la mappa contiene il nome della regione
		assertTrue(map.contains(reg.get(1).getName().toUpperCase()));
		
		for(int i=0; i<cities.size(); i++){//ciclo che scorre le citta'
		assertTrue(map.contains(cities.get(i).getName()));//la mappa contiene il nome delle citta'
		assertTrue(map.contains(cities.get(i).getType()));//la mappa contiene il colore (tipo) delle citta'
		}
		assertTrue(map.contains(giocatori.get(0).getUser()));//la mappa contiene il nome dei giocatori
		assertTrue(map.contains(giocatori.get(1).getUser()));
	}

	/**
	 * it tests if after createMapDraw the map contains the name of the regions, of the cities, of the players, 
	 * and if each city contains his type 
	 */
	@Test
	public void testCreateMapDrawShouldCreateAMapThatContainsTheNameOfRegionsCitiesTypesAndPlayers() {
		String map=cm.createMapDraw(board);
		assertTrue(map.contains(reg.get(0).getName().toUpperCase()));//la mappa contiene il nome della regione
		assertTrue(map.contains(reg.get(1).getName().toUpperCase()));
		
		for(int i=0; i<cities.size(); i++){//ciclo che scorre le citta'
		assertTrue(map.contains(cities.get(i).getName()));//la mappa contiene il nome delle citta'
		assertTrue(map.contains(cities.get(i).getType()));//la mappa contiene il colore (tipo) delle citta'
		}
		assertTrue(map.contains(giocatori.get(0).getUser()));//la mappa contiene il nome dei giocatori
		assertTrue(map.contains(giocatori.get(1).getUser()));
	}
	
	/**
	 * it tests if createPlayerInfo creates a string with the players info
	 */
	@Test
	public void testCreatePlayerInfoShouldCreateAStringWithThePlayersInfo(){
		String playerInfo=cm.createPlayerInfo(giocatori);
		
		assertTrue(playerInfo.contains(giocatori.get(0).getUser()));
		assertTrue(playerInfo.contains(giocatori.get(1).getUser()));
		assertNotEquals(playerInfo, null);
		assertNotEquals(playerInfo, "");
		assertTrue(playerInfo.contains("Player"));
		assertTrue(playerInfo.contains("coin"));
	}
}
