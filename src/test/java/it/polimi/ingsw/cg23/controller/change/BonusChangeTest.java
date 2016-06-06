package it.polimi.ingsw.cg23.controller.change;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.controller.change.BonusChange;
import it.polimi.ingsw.cg23.server.model.bonus.Bonus;
import it.polimi.ingsw.cg23.server.model.bonus.BonusVictoryPoints;

public class BonusChangeTest {
	private Bonus bonus;
	
	@Before
	public void setUp() throws Exception {
		bonus=new BonusVictoryPoints(1);
	}

	@Test
	public void test() {
		BonusChange change=new BonusChange(bonus);
		assertEquals(change.toString(),"BonusChange [bonus=BonusVictoryPoints[points=1]]" );
	}

}
