package it.polimi.ingsw.cg23.client.socket;

import java.io.IOException;
import java.io.ObjectInputStream;

import org.apache.log4j.Logger;

import it.polimi.ingsw.cg23.controller.change.Change;


public class ClientInHandler implements Runnable {
	
	private ObjectInputStream socketIn;
	
	private static Logger logger;
	
	public ClientInHandler(ObjectInputStream socketIn) {
		ClientInHandler.logger = Logger.getLogger(ClientInHandler.class);
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
