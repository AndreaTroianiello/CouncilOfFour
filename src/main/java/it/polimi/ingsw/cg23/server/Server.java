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

import it.polimi.ingsw.cg23.server.controller.Avvio;
import it.polimi.ingsw.cg23.server.controller.Controller;
import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.view.RMIView;
import it.polimi.ingsw.cg23.server.view.RMIViewRemote;
import it.polimi.ingsw.cg23.server.view.ServerSocketView;

/**
 * The Server accepts new connections from clients.
 * @author Andrea
 *
 */
public class Server {
	private final static int SOCKET_PORT=29999;
	private final String NAME = "council";
	private final static int RMI_PORT=52365;
	
	private static Logger logger;
	
	private int index; 							//Number of the players connected.
	private Board model;						//The model of the game.
	private Controller controller;				//The controller of the game.
	
	/**
	 * The constructor of Server.
	 */
	public Server(){
		logger = Logger.getLogger(ServerSocketView.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");
		this.index=0;
	}
	
	
	private void startRMI() throws RemoteException, AlreadyBoundException{
		Registry registry=LocateRegistry.createRegistry(RMI_PORT);
		System.out.println("Costructing the RMI registry");
		
		RMIView rmiView=new RMIView();
		rmiView.registerObserver(this.controller);
		this.model.registerObserver(rmiView);
		
		RMIViewRemote viewRemote=(RMIViewRemote) UnicastRemoteObject.exportObject(rmiView, 0);
		registry.bind("council", rmiView);
	}
	
	/**
	 * Starts the socket connection.
	 * @throws IOException If the socket connection has problem.
	 */
	private void startSocket() throws IOException{
		boolean run=true;
		
		ExecutorService executor=Executors.newCachedThreadPool();
		ServerSocket serverSocket=new ServerSocket(SOCKET_PORT);
		System.out.println("SERVER SOCKET, PORT:" + SOCKET_PORT);
		
		while(run){
			Socket socket=serverSocket.accept();
			incrementIndex();
			ServerSocketView view=new ServerSocketView(socket,model);
			this.model.registerObserver(view);
			view.registerObserver(this.controller);
			executor.submit(view);
			logger.info(index);				
		}
		
		serverSocket.close();
	}
		
	/**
	 * Returns the index of players.
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
	
	public void incrementIndex(){
		++index;
		if(index==1)
			initializationGame();
		if(index==2)
			new Thread(new NewGame(this)).start();
	}
	
	/**
	 * Initializes a new controller and model.
	 */
	public void initializationGame(){
		Avvio avvio=new Avvio();
		avvio.startPartita();
		model=avvio.getBoard();
		this.controller = new Controller(model);
	}

	/**
	 * Returns the controller of the game.
	 * @return controller
	 */
	public Controller getController(){
		return controller;
	}
	
	/**
	 * Starts the server.
	 * @param args
	 */
	public static void main(String[] args)  {
		Server server=new Server();
		try {
			server.startSocket();
		} catch (IOException e) {
			logger.error(e);
		}
	}	
}