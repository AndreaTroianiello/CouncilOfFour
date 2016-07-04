package it.polimi.ingsw.cg23.client.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.client.ClientController;
import it.polimi.ingsw.cg23.client.ClientViewOut;
import it.polimi.ingsw.cg23.server.controller.action.Action;
import it.polimi.ingsw.cg23.server.controller.change.Change;

/**
 * The ClientHandler manages the input and output objects flow, when a Socket
 * connection between client and server is used.
 * 
 * @author Andrea
 *
 */
public class ClientHandler implements Runnable, ClientViewOut {

	private Socket socket;
	private ObjectOutputStream socketOut;
	private ObjectInputStream socketIn;
	private ClientController controller;
	boolean run;
	private Logger logger;

	/**
	 * The constructor of ClientHandler.
	 * 
	 * @param controller
	 *            The controller of the client.
	 * @param socket
	 *            the socket connection.
	 * @throws IOException
	 *             if the Socket connection has problems.
	 */
	public ClientHandler(Socket socket, ClientController controller) throws IOException {
		this.socketOut = new ObjectOutputStream(socket.getOutputStream());
		this.socketIn = new ObjectInputStream(socket.getInputStream());
		this.socket = socket;
		this.controller = controller;
		this.run = true;
		controller.setOutView(this);
		logger = Logger.getLogger(ClientHandler.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");
	}

	/**
	 * Sends the action to the server.
	 * 
	 * @param action
	 *            The action to send.
	 * @throws IOException
	 *             if the socket connection has problems.
	 */
	@Override
	public void update(Action action) throws IOException {
		socketOut.writeObject(action);
		socketOut.flush();
		socketOut.reset();
	}

	/**
	 * Closes the Socket connection.
	 * 
	 * @throws IOException
	 *             if the Socket connection has problems.
	 */
	@Override
	public void close() throws IOException {
		this.run = false;
		socket.close();
	}

	/**
	 * The run of the thread. When receives a change,notify the controller with
	 * the incoming change.
	 */
	@Override
	public void run() {
		while (run) {
			try {
				Change object = (Change) socketIn.readObject();
				controller.updateController(object);
			} catch (ClassNotFoundException e) {
				logger.error(e);
			} catch (IOException e) {
				logger.error(e);
				run = false;
			}
		}
	}
}
