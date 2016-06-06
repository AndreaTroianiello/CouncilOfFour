package it.polimi.ingsw.cg23.client.socket;

import java.io.IOException;
import java.io.ObjectInputStream;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.client.ClientModel;
import it.polimi.ingsw.cg23.server.controller.change.Change;


public class ClientInHandler implements Runnable {
	
	private ObjectInputStream socketIn;
	private final ClientModel clientModel;
	private static Logger logger;
	
	public ClientInHandler(ClientModel clientModel,ObjectInputStream socketIn) {
		logger = Logger.getLogger(ClientInHandler.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");
		this.socketIn=socketIn;
		this.clientModel=clientModel;
	}

	@Override
	public void run() {
		boolean run=true;
		while(run){
			try {
				Change object=(Change) socketIn.readObject();
				logger.info(object);
			} catch (ClassNotFoundException e){
				logger.error(e);
			} catch (IOException e) {
				logger.error(e);
				run=false;
			} 
		}

	}

}
