package it.polimi.ingsw.cg23.client.socket;

import java.io.IOException;
import java.io.ObjectOutputStream;

import it.polimi.ingsw.cg23.client.ClientViewOut;
import it.polimi.ingsw.cg23.server.controller.action.Action;


public class ClientOutHandler implements Runnable,ClientViewOut {

	private ObjectOutputStream socketOut;

	public ClientOutHandler(ObjectOutputStream socketOut) {
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

