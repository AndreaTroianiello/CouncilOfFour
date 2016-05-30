package it.polimi.ingsw.cg23.model.bonus;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.text.PlainDocument;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.City;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.Region;
import it.polimi.ingsw.cg23.model.Type;
import it.polimi.ingsw.cg23.model.components.NobilityTrack;

public class BonusCityTokenTest {
	
	private City city;
	private List<City> cities = new ArrayList<>();
	public boolean[] runnable;
	private Type type = new Type("blu", 0, null);
	private Region region = new Region("regione", 0, null, null);
	private Board board;
	private Player player;

	@Before
	public void setUp() throws Exception {
		city = new City('A', "Ancona", type, region);
		cities.add(city);
		BonusVictoryPoints bonus1 = new BonusVictoryPoints(10);
		this.city.addBonus(bonus1);
		this.runnable = new boolean[this.cities.size()];
		for(int i=0; i<this.runnable.length; i++)	
			this.runnable[i] = true;
		List<Region> regions = new ArrayList<>();
		regions.add(region);
		board = new Board(null, regions, null, null, null);
		player = new Player("a", 10, 100, new NobilityTrack(3));
	}

	/**
	 * it tests if getNumber works porperly
	 */
	@Test
	public void testGetNumber() {
		BonusCityToken bonus = new BonusCityToken(1, cities, null);
		assertEquals(1, bonus.getNumber());
	}

	/**
	 * it tests if getRunnable works properly
	 */
	/*@Test
	public void testGetRunnable() {
		BonusCityToken bonus = new BonusCityToken(1, cities, null);
		assertEquals(runnable[0], bonus.getRunnable()[0]);
	}*/

	/**
	 * it tests if getName works properly
	 */
	@Test
	public void testGetName() {
		BonusCityToken bonus = new BonusCityToken(1, cities, null);
		assertEquals("CityToken", bonus.getName());
	}

	/**
	 * it tests if getCity works properly
	 */
	@Test
	public void testGetCity() {
		BonusCityToken bonus = new BonusCityToken(1, cities, null);
		cities.add(city);
		assertEquals(cities, bonus.getCity());
	}



	/**
	 * it tests if toString works properly
	 */
	/*@Test
	public void testToString() {
		BonusCityToken bonus = new BonusCityToken(1, cities, null);
		assertEquals("BonusCityToken [number=1, city=" + cities + ", runnable="
				+ Arrays.toString(runnable) + "]", bonus.toString());
	}*/

	/**
	 * it tests if clone works properly
	 */
	@Test
	public void testClone() {
		BonusCityToken bonus = new BonusCityToken(0, cities, null);
		BonusCityToken newBonus = (BonusCityToken) bonus.clone();
		assertEquals(bonus.toString(), newBonus.toString());
	}
	

}
