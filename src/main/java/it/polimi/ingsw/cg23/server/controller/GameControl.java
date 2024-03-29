package it.polimi.ingsw.cg23.server.controller;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * PlayersControl controls if the all game'players are created and starts the
 * game.
 * 
 * @author Andrea
 *
 */
public class GameControl implements Runnable {

	private int index;
	private Controller controller;
	private static Logger logger;

	/**
	 * The constructor of PlayersControl.
	 * 
	 * @param index
	 *            The number of all views.
	 * @param controller
	 *            The game's controller.
	 */
	public GameControl(int index, Controller controller) {
		this.index = index;
		this.controller = controller;
		logger = Logger.getLogger(GameControl.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");
	}

	/**
	 * The method performed by the thread. It controls if the number of players
	 * is equals than the number of views.
	 */
	@Override
	public void run() {
		boolean run = true;
		while (run) {
			try {
				if (controller.isReady(index)) {
					controller.startGame();
					run = false;
				} else
					Thread.sleep(2000);
			} catch (InterruptedException e) {
				logger.error(e);
				run = false;
				Thread.currentThread().interrupt();
			}

		}
	}

}
