package it.polimi.ingsw.cg23.client.socket;

import java.io.IOException;
import java.io.ObjectInputStream;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.controller.change.Change;


public class ClientInHandler implements Runnable {
	
	private ObjectInputStream socketIn;
	
	private static Logger logger;
	
	public ClientInHandler(ObjectInputStream socketIn) {
		logger = Logger.getLogger(ClientInHandler.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");
		this.socketIn=socketIn;
	}

	@Override
	public void run() {
		while(true){
			
			try {
				Change object=(Change) socketIn.readObject();
				logger.error(object);
			} catch (ClassNotFoundException | IOException e) {
				logger.error(e);
			} 
		}

	}

}
