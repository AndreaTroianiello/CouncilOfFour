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

import it.polimi.ingsw.cg23.client.ClientModel;

public class ClientSocket {

	private final static int PORT = 29999;
	private final static String IP = "127.0.0.1";
	
	private static Logger logger;

	public ClientSocket(){
		logger = Logger.getLogger(ClientSocket.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");
	}
	
	@SuppressWarnings("resource")
	public void startClient() throws UnknownHostException, IOException {
		ClientModel clientModel=new ClientModel();
		Socket socket = new Socket(IP, PORT);

		ExecutorService executor = Executors.newFixedThreadPool(2);
		
		executor.submit(new ClientOutHandler(clientModel,
				new ObjectOutputStream(socket.getOutputStream())));

		executor.submit(new ClientInHandler(clientModel,
				new ObjectInputStream(socket.getInputStream())));
		
		logger.info("Connection created");
	}
	
}