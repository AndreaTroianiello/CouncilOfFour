package it.polimi.ingsw.cg23.client;

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
import it.polimi.ingsw.cg23.server.model.components.AssistantsPool;
import it.polimi.ingsw.cg23.server.model.components.BusinessPermitTile;
import it.polimi.ingsw.cg23.server.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.server.model.components.PoliticCard;
import it.polimi.ingsw.cg23.server.model.components.RegionDeck;
import it.polimi.ingsw.cg23.server.model.marketplace.Item;

public class ClientModelTest {

	private Board board;
	private Player player;
	private List<Region> regions;
	private Region region;
	
	@Before
	public void setUp() throws Exception {
		this.regions = new ArrayList<>();
		this.board = new Board(null, regions, null, null, null, null);
		this.player = new Player("user", new NobilityTrack(1));
		this.region = new Region("costa", 0, new RegionDeck(1), null);
	}

	/**
	 * it tests if getPlayer works properly
	 */
	@Test
	public void testGetPlayer() {
		ClientModel client = new ClientModel();
		client.setPlayer(player);
		assertEquals(player, client.getPlayer());
	}

	/**
	 * it tests if it returns null when the city is not in the region
	 */
	@Test
	public void testFindCityShouldReturnNullIfTheCityIsNotInTheRegion() {
		ClientModel client = new ClientModel();
		client.setModel(board);
		this.board.getRegions().add(this.region);
		assertNull(client.findCity("bo"));
	}
	
	/**
	 * it tests if findCity return the right city
	 */
	@Test
	public void testFindCityShouldReturnTheCityIfTheCityIsInTheRegion() {
		ClientModel client = new ClientModel();
		client.setModel(board);
		City city = new City('B', "Barcellona", new Type("gold", 0, null), region);
		this.region.addCity(city);
		this.board.getRegions().add(this.region);
		assertEquals(city, client.findCity("Barcellona"));
	}

	/**
	 * it tests if findRegion return the right region
	 */
	@Test
	public void testFindRegionShouldReturnTheRegionIfItIsInTheBoard() {
		ClientModel client = new ClientModel();
		client.setModel(board);
		this.board.getRegions().add(this.region);
		assertEquals(this.region, client.findRegion("costa"));
	}
	
	/**
	 * it tests if findRegion return null when it doesn't find the region in the board
	 */
	@Test
	public void testFindRegionShouldReturnNullIfTheRegionIsNotInTheBoard() {
		ClientModel client = new ClientModel();
		client.setModel(board);
		this.board.getRegions().add(this.region);
		assertNull(client.findRegion("collina"));
	}

	/**
	 * it tests if findColor works properlys
	 */
	@Test
	public void testFindColor() {		
		ClientModel client = new ClientModel();
		Color color = client.findColor("White");
		assertEquals(new Color(255, 255, 255), color);
	}
	
	/**
	 * it tests if findPlayerTile returns null when the paramter is not an integer, and if it returns the
	 * right tile when it's all fine
	 */
	@Test
	public void testFindPlayerTile() {
		ClientModel client = new ClientModel();
		client.setPlayer(player);
		BusinessPermitTile tile = new BusinessPermitTile(null, null);
		assertNull(client.findPlayerTile("l"));
		this.player.addAvailableBusinessPermit(tile);
		assertEquals(tile, client.findPlayerTile("0"));
	}

	/**
	 * it tests if findRegionTile returns null when the paramter is not an integer, and if it returns the
	 * right tile when it's all fine
	 */
	@Test
	public void testFindRegionTile() {
		ClientModel client = new ClientModel();
		client.setModel(board);
		this.board.getRegions().add(this.region);
		BusinessPermitTile tile = new BusinessPermitTile(null, null);
		List<BusinessPermitTile> businessPermitTiles = new ArrayList<>();
		assertNull(client.findRegionTile("l", region));
		businessPermitTiles.add(tile);
		this.board.getRegions().get(0).getDeck().setBusinessPermitTiles(businessPermitTiles);
		assertEquals(tile, client.findRegionTile("0", region));
		}

	/**
	 * it tests if findItem return the chosen item of the market when it's all fine, and if it return null 
	 * when you insert a number greater then the market's size or if the parameter passed is not an integer
	 */
	@Test
	public void testFindItem() {
		ClientModel client = new ClientModel();
		Item assistants = new Item(new AssistantsPool(), this.player, 3);
		client.setModel(board);
		assertNull(client.findItem("l"));
		assertNull(client.findItem("0"));
		this.board.getMarket().addItemToSell(assistants);
		assertEquals(assistants, client.findItem("0"));
	}

	/**
	 * it tests if findPoliticCard returns null when the parameter is not an integer and if it returns
	 * the politic card chosen when it is all fine
	 */
	@Test
	public void testFindPoliticCard() {
		ClientModel client = new ClientModel();
		client.setPlayer(player);
		PoliticCard card = new PoliticCard(null, true);
		this.player.getHand().add(card);
		assertNull(client.findPoliticCard("l"));
		assertEquals(card, client.findPoliticCard("0"));
	}

}
