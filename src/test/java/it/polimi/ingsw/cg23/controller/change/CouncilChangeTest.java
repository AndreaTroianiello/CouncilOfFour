package it.polimi.ingsw.cg23.controller.change;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.model.components.Council;
import it.polimi.ingsw.cg23.model.components.Councillor;

public class CouncilChangeTest {

	private Council newCouncil;
	@Before
	public void setUp() throws Exception {
		newCouncil=new Council();
		List<Councillor> councillors=newCouncil.getCouncillors();
		for(int i=0;i<4;i++){
			councillors.add(new Councillor(Color.BLACK));
		}
	}

	@Test
	public void testCouncilChange() {
		CouncilChange change=new CouncilChange(newCouncil);
		assertEquals(change.toString(),"CouncilChange [Council= ["+ newCouncil.getCouncillors() + "]" );
	}

}
