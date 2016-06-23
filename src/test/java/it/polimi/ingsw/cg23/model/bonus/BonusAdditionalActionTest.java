package it.polimi.ingsw.cg23.model.bonus;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.bonus.BonusAdditionalAction;
import it.polimi.ingsw.cg23.server.model.components.NobilityTrack;

public class BonusAdditionalActionTest {

	Player player;
	
	@Before
	public void setUp() throws Exception {
		player = new Player("a", new NobilityTrack(1));
	}

	/**
	 * it tests if giveBonus switches addictionAciton when it is false
	 */
	@Test
	public void testGiveBonusShouldSwitchAdditionalActionIfItIsFalse() {
		if(player.isAdditionalAction())
			player.switchAdditionalAction();
		BonusAdditionalAction bonus = new BonusAdditionalAction();
		bonus.giveBonus(player);
		bonus.setBoard(null);
		bonus.setNumber(0);
		assertEquals(0, bonus.getNumber());
		assertEquals(true, player.isAdditionalAction());
	}
	
	/**
	 * it tests if giveBonus doesn't switch addictionAciton when it is false
	 */
	@Test
	public void testGiveBonusShouldntDoAnythingIfAdditionalActionIsTrue() {
		if(!player.isAdditionalAction())
			player.switchAdditionalAction();
		BonusAdditionalAction bonus = new BonusAdditionalAction();
		bonus.giveBonus(player);
		assertEquals(true, player.isAdditionalAction());
	}


	/**
	 * it tests if getName works properly
	 */
	@Test
	public void testGetName() {
		BonusAdditionalAction bonus = new BonusAdditionalAction();
		assertEquals("AdditionalAction", bonus.getName());
	}

	/**
	 * it tests if toString works properly
	 */
	@Test
	public void testToString() {
		BonusAdditionalAction bonus = new BonusAdditionalAction();
		assertEquals("BonusAdditionalAction []", bonus.toString());
	}

	/**
	 * it tests if clone return an equal bonus
	 */
	@Test
	public void testClone() {
		BonusAdditionalAction bonus = new BonusAdditionalAction();
		BonusAdditionalAction newBonus = (BonusAdditionalAction) bonus.copy();
		assertEquals(bonus.getName(), newBonus.getName());
		
	}

}
