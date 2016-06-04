package it.polimi.ingsw.cg23.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.controller.Avvio;
import it.polimi.ingsw.cg23.controller.Controller;
import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.view.ServerSocketView;

public class Server {
	private final static int PORT=29999;

	private static Logger logger;
	
	private Board model;
	private Controller controller;
	
	public Server(){
		logger = Logger.getLogger(ServerSocketView.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");
	}
	
	private void startSocket() throws IOException{
		ExecutorService executor=Executors.newCachedThreadPool();
		ServerSocket serverSocket=new ServerSocket(PORT);
		System.out.println("SERVER SOCKET, PORT:" + PORT);
		
		boolean run=true;
		Avvio avvio=new Avvio();
		avvio.startPartita();
		model=avvio.getBoard();
		//model.addPlayer(new Player("player1", 10, 100, null));
		this.controller = new Controller(model);
		while(run){
			Socket socket=serverSocket.accept();
			ServerSocketView view=new ServerSocketView(socket,model);
			this.model.registerObserver(view);
			view.registerObserver(this.controller);
			executor.submit(view);
		}
		
		serverSocket.close();
	}
	
	public static void main(String[] args)  {
		Server server=new Server();
		try {
			server.startSocket();
		} catch (IOException e) {
			logger.error(e);
		}
	}	
}