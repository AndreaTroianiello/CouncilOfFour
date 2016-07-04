package it.polimi.ingsw.cg23.model.bonus;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.City;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.Type;
import it.polimi.ingsw.cg23.server.model.bonus.BonusCityToken;
import it.polimi.ingsw.cg23.server.model.bonus.BonusNobility;
import it.polimi.ingsw.cg23.server.model.bonus.BonusVictoryPoints;
import it.polimi.ingsw.cg23.server.model.components.BonusKing;
import it.polimi.ingsw.cg23.server.model.components.Emporium;
import it.polimi.ingsw.cg23.server.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.server.model.exception.NegativeNumberException;

public class BonusCityTokenTest {

	private List<City> cities;
	public boolean runnable;
	private Type type;
	private Region region;
	private Player player;
	private Board board;

	@Before
	public void setUp() throws Exception {
		this.cities = new ArrayList<>();
		this.type = new Type("blu", 0, null);
		List<Integer> bKing = new ArrayList<>();
		bKing.add(3);
		this.region = new Region("regione", 0, null, new BonusKing(bKing));
		City city = new City('A', "Ancona", type, region);
		BonusVictoryPoints bonus1 = new BonusVictoryPoints(10);
		city.addBonus(bonus1);
		this.cities.add(city);
		this.runnable = true;
		List<Region> regions = new ArrayList<>();
		regions.add(region);
		this.board = new Board(null, regions, null, new NobilityTrack(3), null, null);
		this.player = new Player("a", new NobilityTrack(3));
	}

	/**
	 * it tests if getNumber works porperly
	 */
	@Test
	public void testGetNumber() {
		BonusCityToken bonus = new BonusCityToken(0);
		assertEquals(0, bonus.getNumber());
	}

	/**
	 * it tests if getName works properly
	 */
	@Test
	public void testGetName() {
		BonusCityToken bonus = new BonusCityToken(0);
		assertEquals("0CityToken", bonus.getName());
	}

	/**
	 * it tests if giveBonus doesn't give the bonus when the player doesn't have
	 * an emporium in the city
	 * 
	 * @throws NegativeNumberException
	 */
	@Test
	public void testGiveBonusShouldntGiveTheBonusWhenThePlayerDoesntHaveAnEmporiumInTheCity()
			throws NegativeNumberException {
		BonusCityToken bonus = new BonusCityToken(1);
		bonus.setCities(cities);
		bonus.setBoard(board);
		this.player.getVictoryTrack().setVictoryPoints(10);
		bonus.giveBonus(player);
		assertEquals(10, this.player.getVictoryTrack().getVictoryPoints());
	}

	/**
	 * it tests if giveBonus give the bonus when it's all fine, and if doesn't
	 * give it when the board is null
	 * 
	 * @throws NegativeNumberException
	 */
	@Test
	public void testGiveBonusShouldGiveTheBonusWhenItDoesntHaveANobilityBonus() throws NegativeNumberException {
		BonusCityToken bonus = new BonusCityToken(1);
		bonus.giveBonus(player);
		assertEquals(0, this.player.getVictoryTrack().getVictoryPoints());
		bonus.setCities(cities);
		bonus.setBoard(board);
		this.player.setEmporium(new Emporium(player));
		this.cities.get(0).buildEmporium(this.player.getAvailableEmporium());
		this.player.getVictoryTrack().setVictoryPoints(10);
		bonus.giveBonus(player);
		assertEquals(20, this.player.getVictoryTrack().getVictoryPoints());
	}

	/**
	 * it tests if giveBonus doesn't give the bonus when the city has a
	 * nobilityBonus
	 * 
	 * @throws NegativeNumberException
	 */
	@Test
	public void testGiveBonusShouldntGiveTheBonusWhenTheCityHasANobilityBonus() throws NegativeNumberException {
		BonusCityToken bonus = new BonusCityToken(1);
		bonus.setCities(cities);
		bonus.setBoard(board);
		this.cities.get(0).addBonus(new BonusNobility(2, board));
		this.player.setEmporium(new Emporium(player));
		this.cities.get(0).buildEmporium(this.player.getAvailableEmporium());
		this.player.getVictoryTrack().setVictoryPoints(10);
		bonus.giveBonus(player);
		assertEquals(10, this.player.getVictoryTrack().getVictoryPoints());
	}

	/**
	 * it tests if toString works properly
	 */
	@Test
	public void testToString() {
		BonusCityToken bonus = new BonusCityToken(0);
		assertEquals("BonusCityToken [parameters=0]", bonus.toString());
	}

	/**
	 * it tests if clone works properly
	 */
	@Test
	public void testClone() {
		BonusCityToken bonus = new BonusCityToken(0);
		BonusCityToken newBonus = (BonusCityToken) bonus.copy();
		assertEquals(bonus.toString(), newBonus.toString());
	}
}
