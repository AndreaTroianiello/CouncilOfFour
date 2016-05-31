package it.polimi.ingsw.cg23.controller.creation;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.controller.creation.CreateCostruction;
import it.polimi.ingsw.cg23.model.Region;
import it.polimi.ingsw.cg23.model.components.BonusKing;
import it.polimi.ingsw.cg23.model.components.RegionDeck;

public class CreateCostructionTest {
		List<Region> reg;
		CreateCostruction cc;
		private List<Integer> bonuses;
		
	@Before
	public void setUp(){
		cc=new CreateCostruction();
		
		//Set up the bonus king
		bonuses=new ArrayList<>();
		reg=new ArrayList<>();
		bonuses.add(10);
		bonuses.add(3);
		bonuses.add(0);
		BonusKing bonusKing=new BonusKing(bonuses);
		
		reg.add(new Region("costa", 5, new RegionDeck(2), bonusKing));
		reg.add(new Region("collina", 5, new RegionDeck(2), bonusKing));
	}
	
	@Test
	public void createRegionDeckTest() {
		cc.createCardCostruction(null);
		cc.createRegionDeck(reg);
		assertFalse(reg.get(0).getDeck().isEmpty());
	}
	
	@Test
	public void createCostructionTest() {
		cc.createCardCostruction(null);
		assertNotNull(cc.createCardCostruction(null));
		assertEquals(cc.createCardCostruction(null).size(), 45);
	}

}
