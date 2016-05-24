package it.polimi.ingsw.cg23.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import it.polimi.ingsw.cg23.controller.Controller;
import it.polimi.ingsw.cg23.controller.Turn;
import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.view.ServerSocketView;

public class Server {
	private final static int PORT=29999;
	
	private Board model;
	private Controller controller;
	
	private void startSocket() throws IOException{
		ExecutorService executor=Executors.newCachedThreadPool();
		ServerSocket serverSocket=new ServerSocket(PORT);
		System.out.println("SERVER SOCKET, PORT:" + PORT);
		//model=new Board();
		while(true){
			Socket socket=serverSocket.accept();
			ServerSocketView view=new ServerSocketView(socket,model);
			this.model.registerObserver(view);
			view.registerObserver(this.controller);
			executor.submit(view);
		}
	}
	
	public static void main(String[] args) throws IOException {
		Server server=new Server();
		server.startSocket();
	}	
}