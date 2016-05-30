package it.polimi.ingsw.cg23.model.bonus;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.exception.NegativeNumberException;

public class BonusCoinTest {

	private Player player;
	
	@Before
	public void setUp() throws Exception {
		player = new Player("a", 10, 100, null);
	}

	/**
	 * it tests if getName works porperly
	 */
	@Test
	public void testGetName() {
		BonusCoin bonus = new BonusCoin(2);
		bonus.setNumber(2);
		bonus.setParameters();
		assertEquals("2Coin", bonus.getName());
	}

	/**
	 * it tests if getCoin works properly
	 */
	@Test
	public void testGetCoin() {
		BonusCoin bonus = new BonusCoin(2);
		assertEquals(2, bonus.getCoin());
	}

	/**
	 * it tests if giveBonus works properly
	 * @throws NegativeNumberException
	 */
	@Test
	public void testGiveBonusShouldGiveThePlayerTheAmountOfCoinInTheBonus() throws NegativeNumberException {
		BonusCoin bonus = new BonusCoin(2);
		player.getRichness().setCoins(10);
		bonus.giveBonus(player);
		assertEquals(12, player.getRichness().getCoins());
	}

	/**
	 * it tests if toString works properly
	 */
	@Test
	public void testToString() {
		BonusCoin bonus = new BonusCoin(2);
		assertEquals("BonusCoin [coin=2]", bonus.toString());
	}

	/**
	 * it tests if clone works properly
	 */
	@Test
	public void testClone() {
		BonusCoin bonus = new BonusCoin(0);
		BonusCoin newBonus = (BonusCoin) bonus.clone();
		assertEquals(bonus.getName(), newBonus.getName());
	}

}