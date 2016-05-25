package it.polimi.ingsw.cg23;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.components.BonusKing;

public class BonusKingTest {

	private List<Integer> bonuses,bonuses2;
	private Player player;
	
	/**
	 * Sets up the test.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		//Set up the bonus king
		bonuses=new ArrayList<>();
		bonuses.add(10);
		bonuses.add(3);
		bonuses.add(0);
		bonuses2=new ArrayList<>();
		bonuses2.add(25);
		bonuses2.add(0);
		player=new Player("user", 10, 10, null);
	}

	/**
	 * Tests the current bonus method of bonusKing.
	 */
	@Test
	public void testGetCurrentBonus() {
		BonusKing bonusKing= new BonusKing(bonuses);
		assertEquals(bonusKing.getCurrentBonusKing(),10);
		assertEquals(bonusKing.getCurrentIndexBonusKing(),0);
	}
	
	/**
	 * Tests the giveBonus method of bonusKing.
	 */
	@Test
	public void testGiveBonus() {
		BonusKing bonusKing= new BonusKing(bonuses);
		bonusKing.setBonusValues(bonuses2);
		assertEquals(bonusKing.getCurrentBonusKing(),25);
		bonusKing.runBonusKing(player);
		assertEquals(player.getVictoryTrack().getVictoryPoints(),25);
		assertEquals(bonusKing.getCurrentBonusKing(),0);
		assertEquals(bonusKing.getCurrentIndexBonusKing(),1);
	}

}
