package it.polimi.ingsw.cg23.client.cli;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.client.rmi.ClientRMI;
import it.polimi.ingsw.cg23.client.socket.ClientSocket;

/**
 * The class that start the CLI client.
 * @author Andrea
 *
 */
public class CommandLine {
	
	private static Logger logger;
	public CommandLine(){
		logger = Logger.getLogger(ControllerCLI.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");
	}
	
	private void run(ControllerCLI controller,Scanner stdIn){
		boolean run=true;
		logger.info("RUNNING");
		while (run) {
			try {
				controller.updateController(stdIn.nextLine());
			} catch (IOException e) {
				logger.error(e);
				run=false;
			}
		}
	}
	
	public static void main(String[] args){
		CommandLine cli=new CommandLine();
		boolean run=true;
		logger.info("Welcome to Council of Four game!");
		logger.info("Choose the type of connection. (SOCKET or RMI)");
		Scanner stdIn = new Scanner(System.in);
		while(run){
		String inputLine=stdIn.nextLine();
			switch (inputLine) {
			case "SOCKET":
				try {
					ClientSocket clientSocket=new ClientSocket();
					ControllerCLI controller=new ControllerCLI();
					clientSocket.startClient(controller);
					cli.run(controller, stdIn);
					run=false;
				} catch (IOException e) {
					logger.error(e);
				}
				break;
			case "RMI":
				try {
					ClientRMI clientRMI=new ClientRMI();
					ControllerCLI controller=new ControllerCLI();
					clientRMI.startClient(controller);
					cli.run(controller, stdIn);
					run=false;
				} catch (RemoteException | NotBoundException e) {
					logger.error(e);
				}
				break;
			default:
				break;
			}
		}
		stdIn.close();
	}
}
