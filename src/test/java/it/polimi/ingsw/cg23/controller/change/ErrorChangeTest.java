package it.polimi.ingsw.cg23.controller.change;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.cg23.server.controller.change.ErrorChange;

public class ErrorChangeTest {


	@Test
	public void testErrorChange() {
		ErrorChange change=new ErrorChange("error");
		assertEquals(change.toString(),"ErrorChange [msg=error]");
	}

}
