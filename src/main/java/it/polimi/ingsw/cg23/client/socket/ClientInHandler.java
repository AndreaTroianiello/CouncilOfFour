package it.polimi.ingsw.cg23.client.socket;

import java.io.IOException;
import java.io.ObjectInputStream;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.client.ClientController;
import it.polimi.ingsw.cg23.server.controller.change.Change;


public class ClientInHandler implements Runnable {
	
	private ObjectInputStream socketIn;
	private ClientController controller;
	private static Logger logger;
	
	public ClientInHandler(ClientController controller,ObjectInputStream socketIn) {
		logger = Logger.getLogger(ClientInHandler.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");
		this.socketIn=socketIn;
		this.controller=controller;
	}

	@Override
	public void run() {
		boolean run=true;
		while(run){
			try {
				Change object=(Change) socketIn.readObject();
				controller.updateController(object);
			} catch (ClassNotFoundException e){
				logger.error(e);
			} catch (IOException e) {
				logger.error(e);
				run=false;
			} 
		}
	}
}
