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

public class ClientSocket {

	private final static int PORT = 29999;
	private final static String IP = "127.0.0.1";
	
	private static Logger logger;
	
	public ClientSocket(){
		ClientSocket.logger = Logger.getLogger(ClientSocket.class);
	}

	public void startClient() throws UnknownHostException, IOException {

		Socket socket = new Socket(IP, PORT);
		PropertyConfigurator.configure("src/main/resources/logger.properties");
		logger.error("Connection created");

		ExecutorService executor = Executors.newFixedThreadPool(2);

		executor.submit(new ClientOutHandler(
				new ObjectOutputStream(socket.getOutputStream())));

		executor.submit(new ClientInHandler(
				new ObjectInputStream(socket.getInputStream())));
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException{
		ClientSocket client=new ClientSocket();
		client.startClient();
	}
}