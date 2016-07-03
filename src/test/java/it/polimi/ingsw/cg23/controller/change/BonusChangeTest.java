package it.polimi.ingsw.cg23.controller.change;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.controller.change.BonusChange;
import it.polimi.ingsw.cg23.server.model.bonus.Bonus;
import it.polimi.ingsw.cg23.server.model.bonus.BonusAdditionalAction;

public class BonusChangeTest {

	@Before
	public void setUp() throws Exception {
	}
	
	/**
	 * it test getBonus() method.
	 */
	@Test
	public void testGetInfo(){
		BonusChange change=new BonusChange(new BonusAdditionalAction());
		assertNotNull(change.getBonus());
		assertTrue(change.getBonus() instanceof Bonus);
		assertTrue(change.getBonus() instanceof BonusAdditionalAction);
	}
}
