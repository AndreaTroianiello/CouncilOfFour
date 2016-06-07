package it.polimi.ingsw.cg23.client;

import java.io.IOException;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.client.socket.ClientSocket;
public class Client {

	private static Logger logger;
	
	@SuppressWarnings("resource")
	public static void main(String[] args){
		logger= Logger.getLogger(Client.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");
		boolean run=true;
		logger.info("Welcome to Council of Four game!");
		logger.info("Choose the type of connection. (SOCKET or RMI)");
		while(run){
		Scanner stdIn = new Scanner(System.in);
		String inputLine=stdIn.nextLine();
			switch (inputLine) {
			case "SOCKET":
				try {
					ClientSocket clientSocket=new ClientSocket();
					clientSocket.startClient();
					run=false;
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
}
