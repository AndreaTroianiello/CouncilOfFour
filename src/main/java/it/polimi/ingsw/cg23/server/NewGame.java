package it.polimi.ingsw.cg23.server;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.server.controller.Controller;
import it.polimi.ingsw.cg23.server.controller.GameControl;
import it.polimi.ingsw.cg23.server.view.socket.ServerSocketView;

/**
 * NewGame checks if the game is ready to start and if it creates a new one.
 * 
 * @author Andrea
 *
 */
public class NewGame implements Runnable {

	private Server server;
	private static Logger logger;

	/**
	 * The constructor of the NewGame.
	 * 
	 * @param server
	 *            The server running.
	 */
	public NewGame(Server server) {
		this.server = server;
		logger = Logger.getLogger(ServerSocketView.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");

	}

	/**
	 * The method performed by the thread. After 20 seconds starts the game and
	 * resets the server.
	 */
	@Override
	public void run() {
		try {
			Thread.sleep(20000);
			int index = server.getIndex();
			Controller controller = server.getController();
			server.resetIndex();
			new Thread(new GameControl(index, controller)).start();
		} catch (InterruptedException e) {
			logger.error(e);
			Thread.currentThread().interrupt();
		}
	}
}
