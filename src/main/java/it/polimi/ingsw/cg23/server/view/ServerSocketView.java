package it.polimi.ingsw.cg23.server.view;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.server.controller.action.Action;
import it.polimi.ingsw.cg23.server.controller.change.Change;
import it.polimi.ingsw.cg23.server.model.Board;

/**
 * The view that manages the connections of the socket clients.
 * @author Andrea
 *
 */
public class ServerSocketView extends View implements Runnable {

	private ObjectInputStream socketIn;
	private ObjectOutputStream socketOut;
	private static Logger logger;

	/**
	 * The constructor of ServerSocketView.
	 * @param socket The socket of the connection.
	 * @param model The board of the game.
	 * @throws IOException if the socket connection has problems.
	 */
	public ServerSocketView(Socket socket, Board model) throws IOException {
		logger = Logger.getLogger(ServerSocketView.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");
		this.socketIn = new ObjectInputStream(socket.getInputStream());
		this.socketOut = new ObjectOutputStream(socket.getOutputStream());
	}

	/**
	 * Notify the client with the incoming change.
	 * @param change the change to be sent to the client.
	 */
	@Override
	public void update(Change change) {
		logger.error("Sending to the client " + change);
		try {
			this.socketOut.writeObject(change);
			this.socketOut.flush();
			this.socketOut.reset();

		} catch (IOException e) {
			logger.error(e);
		}
	}

	/**
	 * The run of the thread. When receives a action,notify the controller with the incoming action.
	 */
	@Override
	public void run() {
		boolean run=true;
		while (run) {

			try {

				Object object = socketIn.readObject();
				if (object instanceof Action) {
					Action action = (Action) object;
					action.setLogger(Logger.getLogger(Action.class));
					action.setPlayer(this);
					logger.error("VIEW: received the action " + action);
					action.registerObserver(this);
					this.notifyObserver(action);
				}
			} catch (ClassNotFoundException e){
				logger.error(e);
			} catch (IOException e) {
				logger.error(e);
				run=false;
			}
		}
	}
}
