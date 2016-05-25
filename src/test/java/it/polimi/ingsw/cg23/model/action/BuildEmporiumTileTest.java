package it.polimi.ingsw.cg23.model.action;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.City;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.Region;
import it.polimi.ingsw.cg23.model.Type;
import it.polimi.ingsw.cg23.model.components.BusinessPermitTile;
import it.polimi.ingsw.cg23.model.components.NobilityTrack;

public class BuildEmporiumTileTest {
	
	BusinessPermitTile card;
	Player player;
	Board board;

	@Before
	public void setUp() throws Exception {
		List<Character> cityIDs = new ArrayList<>();
		cityIDs.add('I');
		card = new BusinessPermitTile(cityIDs, null);
		player = new Player("player1", 10, 100, new NobilityTrack(3));
		List<Region> regions = new ArrayList<>();
		Region region = new Region("regione", 0, null, null);
		regions.add(region);
		region.addCity(new City('I', "Ioio", new Type(null, 0, null), region));
		board = new Board(null, regions, null, null, null);
	}

	
	/**
	 * it tests if getCityID works properly
	 */
	@Test
	public void testGetCityIDShouldReturnTheCityID() {
		BuildEmporiumTile action = new BuildEmporiumTile(null, 1);
		int cityID = action.getCityID();
		assertEquals(1, cityID);
	}

	/**
	 * it tests if getCard works properly
	 */
	@Test
	public void testGetCard() {
		BuildEmporiumTile action = new BuildEmporiumTile(card, 1);
		assertEquals(card, action.getCard());
	}

	/**
	 * it tests if toStign works properly
	 */
	@Test
	public void testToString() {
		BuildEmporiumTile action = new BuildEmporiumTile(null, 1);
		assertEquals("BuildEmporiumTile [card=null, cityID=1]", action.toString());
	}
	
	/*
	@Test
	public void testRunAction(){
		BuildEmporiumTile action = new BuildEmporiumTile(card, 0);
		action.runAction(player, board);
	}*/

}
