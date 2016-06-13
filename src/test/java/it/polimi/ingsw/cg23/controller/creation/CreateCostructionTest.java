package it.polimi.ingsw.cg23.controller.creation;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.controller.creation.CreateCostruction;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.components.BonusKing;
import it.polimi.ingsw.cg23.server.model.components.RegionDeck;

public class CreateCostructionTest {

	private CreateCostruction cc;
	private List<Region> reg;
	private List<Integer> bonuses;

	@Before
	public void setUp(){
		cc=new CreateCostruction("ConfigurazionePartita.xml");

		//Set up the bonus king
		bonuses=new ArrayList<>();
		reg=new ArrayList<>();
		bonuses.add(10);
		bonuses.add(3);
		bonuses.add(0);
		BonusKing bonusKing=new BonusKing(bonuses);

		reg.add(new Region("Costa", 5, new RegionDeck(2), bonusKing));
		reg.add(new Region("Collina", 5, new RegionDeck(2), bonusKing));
	}

	@Test
	public void createRegionDeckTest() {
		cc.createCardCostruction(null);
		cc.createRegionDeck(reg);
		assertFalse(reg.get(0).getDeck().isEmpty());
	}

	@Test
	public void createCostructionTest() {
		assertEquals(cc.createCardCostruction(null).size(), 45);
	}

}
