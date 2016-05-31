package it.polimi.ingsw.cg23.controller.creation;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.controller.creation.CreateBonus;
import it.polimi.ingsw.cg23.model.City;
import it.polimi.ingsw.cg23.model.Region;
import it.polimi.ingsw.cg23.model.Type;
import it.polimi.ingsw.cg23.model.components.BusinessPermitTile;

public class CreateBonusTest {
	
	CreateBonus cb;
	BusinessPermitTile bpt1, bpt2;
	City c1, c2;
	
	@Before
	public void setUp(){
		cb=new CreateBonus("ConfigurazionePartita.xml");
		List<Character> idcity=new ArrayList<>();
		idcity.add('A');
		idcity.add('B');
		idcity.add('C');
		
		bpt1=new BusinessPermitTile(idcity,"region0");
		bpt2=new BusinessPermitTile(idcity,"region1");
		
		c1=new City('M', "Milano", new Type("color", 5, null), new Region("region1",5, null, null));
		c2=new City('C', "Como", new Type("purple", 5, null), new Region("region0",5, null, null));
		
	}
	
	@Test
	public void bonusListTest() {
		assertEquals(cb.bonusList(null).size(), 9);
		assertNotNull(cb.bonusList(null));
	}
	
	@Test
	public void bonusKingTest() {
		assertEquals(cb.bonusKing().getBonusValues().size(), 6);
		assertNotNull(cb.bonusKing());
	}
	
	@Test
	public void CostructorBonusTest() {
		String bonus1="3VictoryPoints,1Assistants";
		String bonus2="3Coin,3Politics,1Nobility";
		cb.getCostructorBonus(bpt1, bonus1);
		assertEquals(bpt1.getBonusTile().size(), 0);//la lista e' vuota
		
		cb.bonusList(null);//riempio la lista con i bonus
		cb.getCostructorBonus(bpt1, bonus1);
		assertNotNull(bpt1.getBonusTile());
		assertEquals(bpt1.getBonusTile().size(), 2);
		
		cb.getCostructorBonus(bpt2, bonus2);
		assertNotNull(bpt2.getBonusTile());
		assertEquals(bpt2.getBonusTile().size(), 3);
	}
	
	@Test
	public void CityBonusTest(){
		cb.bonusList(null);//creo la lista dei bonus
		cb.getCityBonus(4, c1);
		assertNotNull(c1.getToken());
		assertEquals(c1.getToken().size(), 2);//2 numero di bonus della citta' in posizione 4 nel file xml
		
		cb.getCityBonus(10, c2);
		assertEquals(c2.getToken().size(), 0);//0 la citta' del re non ha bonus (in posizione 10 nel file xml)
	}
}
