package it.polimi.ingsw.cg23.server.controller.action;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.view.View;

public class ActionTest {

	private View view;
	private Action action;
	private Logger logger;
	
	/**
	 * Initializes the view with anonymous class.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		class TestView extends View{
		};
		view=new TestView();
		this.logger=Logger.getLogger(Action.class);
	}

	/**
	 * initializes the action with an anonymous class
	 * 
	 * it tests if getPlayer and setPlayer work properly
	 */
	@Test
	public void testGetPlayer() {
		class TestAction extends Action{

			/**
			 * 
			 */
			private static final long serialVersionUID = -7024154336670301087L;
		};
		action = new TestAction();
		action.setPlayer(view);
		assertEquals(view, action.getPlayer());
	}

	/**
	 * initializes the action with an anonymous class
	 * 
	 * it tests if setLogger and getLogger work properly
	 */
	@Test
	public void testGetLogger() {
		class TestAction extends Action{

			/**
			 * 
			 */
			private static final long serialVersionUID = -7024154336670301087L;
		};
		action = new TestAction();
		action.setLogger(logger);
		assertEquals(logger, action.getLogger());
	}

}
