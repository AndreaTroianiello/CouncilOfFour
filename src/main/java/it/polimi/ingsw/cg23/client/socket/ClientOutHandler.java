package it.polimi.ingsw.cg23.client.socket;

import java.io.IOException;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.client.ClientViewOut;
import it.polimi.ingsw.cg23.server.controller.action.Action;


public class ClientOutHandler implements Runnable,ClientViewOut {

	private ObjectOutputStream socketOut;
	private Logger logger;

	public ClientOutHandler(ObjectOutputStream socketOut) {
		logger = Logger.getLogger(ClientOutHandler.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");
		this.socketOut = socketOut;
	}
	
	@Override
	public void update(Action action) throws IOException{
		socketOut.writeObject(action);
		socketOut.flush();
		socketOut.reset();
	}
	@Override
	public void run() {
		boolean run=true;
		while(run){
		}
	}
}

