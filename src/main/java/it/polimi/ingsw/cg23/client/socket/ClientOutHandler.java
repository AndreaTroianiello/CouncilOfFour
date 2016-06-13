package it.polimi.ingsw.cg23.client.socket;

import java.io.IOException;
import java.io.ObjectOutputStream;

import it.polimi.ingsw.cg23.client.ClientViewOut;
import it.polimi.ingsw.cg23.server.controller.action.Action;

/**
 * The ClientOutHandler manages the objects  to send at the server, that uses socket connection.
 * @author Andrea
 *
 */
public class ClientOutHandler implements Runnable,ClientViewOut {

	private ObjectOutputStream socketOut;

	/**
	 * The constructor of ClientOutHandler.
	 * @param socketOut the socket connection output.
	 */
	public ClientOutHandler(ObjectOutputStream socketOut) {
		this.socketOut = socketOut;
	}
	
	/**
	 * Sends the action to the server.
	 * @param action The action to send.
	 * @throws IOException if the socket connection has problems.	
	 */
	@Override
	public void update(Action action) throws IOException{
		socketOut.writeObject(action);
		socketOut.flush();
		socketOut.reset();
	}
	
	/**
	 * The run of the thread.
	 */
	@Override
	public void run() {
		boolean run=true;
		while(run){
		}
	}
}

