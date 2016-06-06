package it.polimi.ingsw.cg23.controller.change;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.controller.change.CityChange;
import it.polimi.ingsw.cg23.server.model.City;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.Type;

public class CityChangeTest {
	private City city;
	@Before
	public void setUp() throws Exception {
		city=new City('A', "Aosta", new Type("type1",0,null), new Region("regione1",0,null,null));
		
	}

	@Test
	public void testCityChange() {
		CityChange change=new CityChange(city);
		assertEquals(change.toString(),"CityChange [newCity=City [id=A, name=Aosta, region=regione1, bonus=[], type=type1, neighbors=0]]");
	}

}
