package it.polimi.ingsw.cg23.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.server.controller.Controller;
import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.view.rmi.RMIView;
import it.polimi.ingsw.cg23.server.view.rmi.RMIViewRemote;
import it.polimi.ingsw.cg23.server.view.socket.ServerSocketView;

/**
 * The Server accepts new connections from clients.
 * 
 * @author Andrea
 *
 */
public class Server {
	private static final int SOCKET_PORT = 29999;
	private static final String NAME = "council";
	private static final int RMI_PORT = 52365;

	private static Logger logger;

	private int index; // Number of the players connected.
	private Board model; // The model of the game.
	private Controller controller; // The controller of the game.
	private Chat chat; // The chat of the game.

	/**
	 * The constructor of Server.
	 */
	public Server() {
		logger = Logger.getLogger(Server.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");
		this.index = 0;
	}

	/**
	 * Starts the RMI connection.
	 * 
	 * @throws RemoteException
	 *             If the RMI connection has problem.
	 * @throws AlreadyBoundException
	 *             If the RMI connection has problem.
	 */
	private void startRMI() throws RemoteException, AlreadyBoundException {
		Registry registry = LocateRegistry.createRegistry(RMI_PORT);
		RMIView rmiView = new RMIView(this, registry);
		RMIViewRemote viewRemote = (RMIViewRemote) UnicastRemoteObject.exportObject(rmiView, 0);
		registry.bind(NAME, viewRemote);
		logger.info("SERVER RMI, NAME:" + NAME + " AND PORT:" + RMI_PORT);
	}

	/**
	 * Starts the socket connection.
	 * 
	 * @throws IOException
	 *             If the socket connection has problem.
	 */
	private void startSocket() throws IOException {
		boolean run = true;

		ExecutorService executor = Executors.newCachedThreadPool();
		ServerSocket serverSocket = new ServerSocket(SOCKET_PORT);
		logger.info("SERVER SOCKET, PORT:" + SOCKET_PORT);

		while (run) {
			try {
				Socket socket = serverSocket.accept();
				incrementIndex();
				ServerSocketView view = new ServerSocketView(socket, chat);
				this.model.registerObserver(view);
				view.registerObserver(this.controller);
				executor.submit(view);
			} catch (IOException e) {
				run = false;
				logger.error(e);
			}
		}

		serverSocket.close();
	}

	/**
	 * Returns the index of players.
	 * 
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Resets the index.
	 */
	public void resetIndex() {
		this.index = 0;
	}

	/**
	 * Increments the index of the connected views.
	 */
	public void incrementIndex() {
		++index;
		if (index == 1)
			initializationGame();
		if (index == 2)
			new Thread(new NewGame(this)).start();
		logger.info(index);
	}

	public Chat getChat() {
		return chat;
	}

	/**
	 * Initializes a new controller and model.
	 */
	private void initializationGame() {
		model = new Board(null, null, null, null, null, null);
		controller = new Controller(model);
		chat = new Chat();
	}

	/**
	 * Returns the controller of the game.
	 * 
	 * @return controller
	 */
	public Controller getController() {
		return controller;
	}

	/**
	 * Returns the model of the game
	 * 
	 * @return model
	 */
	public Board getModel() {
		return model;
	}

	/**
	 * Starts the server.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Server server = new Server();
		try {
			server.startRMI();
			server.startSocket();
		} catch (IOException | AlreadyBoundException e) {
			logger.error(e);
		}
	}
}