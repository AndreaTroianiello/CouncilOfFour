package it.polimi.ingsw.cg23.view;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.view.View;

public class ViewTest {
	private View view;
	
	/**
	 * Initializes the view with anonymous class.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		class TestView extends View{
		};
		view=new TestView();
	}

	/**
	 * Test getSuspended() and setSuspended() method.
	 */
	@Test
	public void testSuspended() {
		assertFalse(view.getSuspended());
		view.setSuspended(true);
		assertTrue(view.getSuspended());
	}

	/**
	 * Test getLogger() and setLogger() method.
	 */
	@Test
	public void testLogger() {
		assertNotNull(view.getLogger());
		view.setLogger(null);
		assertNull(view.getLogger());
	}
}
