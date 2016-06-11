package it.polimi.ingsw.cg23.client.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.client.ClientController;

public class ClientSocket {

	private static final int PORT = 29999;
	private static final String IP = "127.0.0.1";
	
	private static Logger logger;

	public ClientSocket(){
		logger = Logger.getLogger(ClientSocket.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");
	}
	
	@SuppressWarnings("resource")
	public void startClient(ClientController controller) throws UnknownHostException, IOException {
		Socket socket = new Socket(IP, PORT);
		ExecutorService executor = Executors.newFixedThreadPool(2);
		ClientOutHandler out=new ClientOutHandler(new ObjectOutputStream(socket.getOutputStream()));
		ClientInHandler in=new ClientInHandler(controller,new ObjectInputStream(socket.getInputStream()));
		executor.submit(out);
		executor.submit(in);
		controller.setOutView(out);
		logger.info("Connection created");
	}
	
}