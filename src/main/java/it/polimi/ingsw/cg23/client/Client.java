package it.polimi.ingsw.cg23.client;

import java.io.IOException;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.client.socket.ClientSocket;
public class Client {

	private static Logger logger;

	public Client() {
		logger= Logger.getLogger(Client.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");
	}
	
	@SuppressWarnings("resource")
	public static void main(String[] args){
		Scanner stdIn = new Scanner(System.in);
		String inputLine=stdIn.nextLine();
		switch (inputLine) {
		case "SOCKET":
			try {
				ClientSocket clientSocket=new ClientSocket();
				clientSocket.startClient();
			} catch (IOException e) {
				logger.error(e);
			}
			break;
		case "RMI":
			break;
		default:
			break;
		}
	}
}
