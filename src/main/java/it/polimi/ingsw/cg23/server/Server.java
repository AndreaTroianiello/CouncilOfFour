package it.polimi.ingsw.cg23.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.server.controller.Avvio;
import it.polimi.ingsw.cg23.server.controller.Controller;
import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.view.ServerSocketView;

/**
 * The Server accepts new connections from clients.
 * @author Andrea
 *
 */
public class Server {
	private final static int PORT=29999;

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
	
	/**
	 * Starts the socket connection.
	 * @throws IOException If the socket connection has problem.
	 */
	private void startSocket() throws IOException{
		boolean run=true;
		
		ExecutorService executor=Executors.newCachedThreadPool();
		ServerSocket serverSocket=new ServerSocket(PORT);
		System.out.println("SERVER SOCKET, PORT:" + PORT);
		
		while(run){
			Socket socket=serverSocket.accept();
			++index;
			if(index==1)
				initializationGame();
			ServerSocketView view=new ServerSocketView(socket,model);
			this.model.registerObserver(view);
			view.registerObserver(this.controller);
			executor.submit(view);
			logger.error(index);
			if(index==2){
				new Thread(new NewGame(this)).start();
			}
				
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