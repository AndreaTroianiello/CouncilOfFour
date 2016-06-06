package it.polimi.ingsw.cg23.model.bonus;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.bonus.BonusAssistants;
import it.polimi.ingsw.cg23.server.model.exception.NegativeNumberException;

public class BonusAssistantsTest {

	Player player;
	
	@Before
	public void setUp() throws Exception {
		player = new Player("a", 0, 0, null);
	}

	/**
	 * it tests if getAssistants works properly
	 */
	@Test
	public void testGetAssistants() {
		BonusAssistants bonus = new BonusAssistants();
		bonus.setNumber(10);
		assertEquals(10, bonus.getAssistants());
	}

	/**
	 * it tests if the bonus gives the palyer the right amount of assistants
	 * @throws NegativeNumberException
	 */
	@Test
	public void testGiveBonusShouldGiveThePlayer10AssistantsIfNumberIs10() throws NegativeNumberException {
		BonusAssistants bonus = new BonusAssistants();
		bonus.setNumber(10);
		bonus.setParameters();
		player.getAssistantsPool().setAssistants(0);
		bonus.giveBonus(player);
		assertEquals(10, player.getAssistantsPool().getAssistants());
	}
	
	/**
	 * it tests if giveBonus works properly when the number is negative
	 * @throws NegativeNumberException
	 */
	@Test
	public void testGiveBonusShouldntWorkWhenTheNumberIsNegative() throws NegativeNumberException{
		BonusAssistants bonus = new BonusAssistants();
		bonus.setNumber(-3);
		player.getAssistantsPool().setAssistants(0);
		bonus.giveBonus(player);
		assertEquals(0, player.getAssistantsPool().getAssistants());
	}
	
	

	/**
	 * it tests if getName works properly
	 */
	@Test
	public void testGetName() {
		BonusAssistants bonus = new BonusAssistants();
		bonus.setNumber(0);
		assertEquals("0Assistants", bonus.getName());
	}

	/**
	 * it tests if toString works properly
	 */
	@Test
	public void testToString() {
		BonusAssistants bonus = new BonusAssistants();
		bonus.setNumber(0);
		assertEquals("BonusAssistants [assistants=0]", bonus.toString());
	}

	/**
	 * it tests if clone works properly
	 */
	@Test
	public void testClone() {
		BonusAssistants bonus = new BonusAssistants();
		BonusAssistants newBonus = (BonusAssistants) bonus.clone();
		assertEquals(bonus.getName(), newBonus.getName());	}

}
