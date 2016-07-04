package it.polimi.ingsw.cg23.server.controller;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.server.controller.action.EndTurn;
import it.polimi.ingsw.cg23.server.view.View;

/**
 * The timer suspends the view if it isn't active.
 * 
 * @author Andrea
 *
 */
public class Timer implements Runnable {

	private View view;
	private static Logger logger;
	private Controller controller;
	private boolean running;

	/**
	 * The constructor of PlayersControl.
	 * 
	 * @param view
	 *            The number of all views.
	 * @param controller
	 *            The game's controller.
	 */
	public Timer(View view, Controller controller) {
		this.view = view;
		this.controller = controller;
		this.running = true;
		logger = Logger.getLogger(Timer.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");
	}

	/**
	 * Returns the boolean running
	 * 
	 * @return running
	 */
	public boolean isRunning() {
		return running;
	}

	/**
	 * Stops the timer.
	 */
	public void stop() {
		running = false;
		Thread.currentThread().interrupt();
	}

	/**
	 * The method performed by the thread. It controls if the number of players
	 * is equals than the number of views.
	 */
	@Override
	public void run() {
		controller.getTurn().setTimer(this);
		view.setSuspended(true);
		try {
			Thread.sleep(60000);
			if (!view.getSuspended()) {
				Thread.sleep(240000);
			}
			if (running)
				new EndTurn().runAction(controller);
		} catch (InterruptedException e) {
			logger.error(e);
			view = null;
			controller = null;
			running = false;
			Thread.currentThread().interrupt();
		}
	}

}
