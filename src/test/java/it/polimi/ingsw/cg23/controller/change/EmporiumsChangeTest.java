package it.polimi.ingsw.cg23.controller.change;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.controller.change.EmporiumsChange;
import it.polimi.ingsw.cg23.server.model.City;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.Type;
import it.polimi.ingsw.cg23.server.model.components.Emporium;

public class EmporiumsChangeTest {
	private City city;
	private List<Player> players;
	
	@Before
	public void setUp() throws Exception {
		city=new City('A', "Aosta", new Type("type1",0,null), new Region("regione1",0,null,null));
		players=Arrays.asList(new Player("player1", null),new Player("player2", null),new Player("player3", null),new Player("player4", null));
		List<Emporium> emporiums=new ArrayList<>();
		for(Player player:players){
			Emporium e=player.getAvailableEmporium();
			e.setCity(city);
			emporiums.add(e);
		}
		city.getEmporiums().addAll(emporiums);
	}

	@Test
	public void testCityChange() {
		EmporiumsChange change=new EmporiumsChange(city.getEmporiums());
		assertEquals(change.toString(),"EmporiumsChange [ Emporiums= [City=A, Players=[player1, player2, player3, player4]]");
	}

}
