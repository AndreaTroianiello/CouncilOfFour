package it.polimi.ingsw.cg23.client.socket;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.client.ClientController;

/**
 * ClientSocket manages the creation of socket connection.
 * 
 * @author Andrea
 *
 */
public class ClientSocket {

	private static final int PORT = 29999;
	private static Logger logger;

	/**
	 * The constructor of ClientSocket.
	 */
	public ClientSocket() {
		logger = Logger.getLogger(ClientSocket.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");
	}

	/**
	 * Starts the Socket connection. Initializes the out/in handler objects.
	 * 
	 * @param controller
	 *            The client's controller.
	 * @throws IOException
	 *             if the socket connection has problems.
	 */
	public void startClient(ClientController controller, String address) throws IOException {
		Socket socket = new Socket(address, PORT);
		ExecutorService executor = Executors.newFixedThreadPool(2);
		ClientHandler clientHandler = new ClientHandler(socket, controller);
		executor.submit(clientHandler);
		logger.info("Connection created");
	}

}