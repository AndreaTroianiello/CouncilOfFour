package it.polimi.ingsw.cg23.client.cli;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.client.rmi.ClientRMI;
import it.polimi.ingsw.cg23.client.socket.ClientSocket;
import it.polimi.ingsw.cg23.utility.Print;

/**
 * The class that start the CLI client and manages the user input.
 * @author Andrea
 *
 */
public class CommandLine {
	
	private static Logger logger;
	private Print cli;
	
	/**
	 * The constructor of CommandLine.
	 * @param cli The Print class that prints to video.
	 */
	public CommandLine(Print cli){
		logger = Logger.getLogger(CommandLine.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");
		this.cli=cli;
	}
	
	/**
	 * Manages the user information flow
	 * @param controller The client's controller
	 * @param stdIn The scanner of the client.
	 */
	private void run(ControllerCLI controller,Scanner stdIn){
		boolean run=true;
		cli.print("","RUNNING");
		while (run) {
			try {
				controller.updateController(stdIn.nextLine());
			} catch (IOException e) {
				logger.error(e);
				run=false;
			}
		}
	}
	
	/**
	 * Starts the Socket connection.
	 * @param tokenizer the string to elaborate.
	 * @param stdIn the scanner.
	 * @param cli The class that print to video.
	 * @return If the true the connection isn't created.
	 */
	public boolean startSocket(StringTokenizer tokenizer, Scanner stdIn, Print cli){
		if(!tokenizer.hasMoreTokens())
			return true;
		try {
			ClientSocket clientSocket=new ClientSocket();
			ControllerCLI controller=new ControllerCLI(cli);
			clientSocket.startClient(controller,tokenizer.nextToken());
			run(controller, stdIn);
			return false;
		} catch (IOException e) {
			logger.error(e);
			return true;
		}
	}
	
	/**
	 * Starts the RMI connection.
	 * @param tokenizer the string to elaborate.
	 * @param stdIn the scanner.
	 * @param cli The class that print to video.
	 * @return If the true the connection isn't created.
	 */
	public boolean startRMI(StringTokenizer tokenizer, Scanner stdIn, Print cli){
		if(!tokenizer.hasMoreTokens())
			return true;
		
		try {
			ClientRMI clientRMI=new ClientRMI();
			ControllerCLI controller=new ControllerCLI(cli);
			clientRMI.startClient(controller,tokenizer.nextToken());
			run(controller, stdIn);
			return false;
		} catch (RemoteException | NotBoundException e) {
			logger.error(e);
			return true;
		}
	}
}
