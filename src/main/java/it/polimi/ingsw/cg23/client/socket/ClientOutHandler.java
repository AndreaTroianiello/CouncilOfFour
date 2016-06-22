package it.polimi.ingsw.cg23.client.socket;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.apache.log4j.Logger;

import it.polimi.ingsw.cg23.client.ClientViewOut;
import it.polimi.ingsw.cg23.server.controller.action.Action;

/**
 * The ClientOutHandler manages the objects  to send at the server, that uses socket connection.
 * @author Andrea
 *
 */
public class ClientOutHandler implements Runnable,ClientViewOut {

	private ObjectOutputStream socketOut;
	boolean run;
	private Socket socket;
	private Logger logger;

	/**
	 * The constructor of ClientOutHandler.
	 * @param socketOut the socket connection output.
	 */
	public ClientOutHandler(Socket socket,ObjectOutputStream socketOut) {
		this.socketOut = socketOut;
		this.socket=socket;
		this.run=true;
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
	 * Closes the Socket connection.
	 * @throws IOException if the Socket connection has problems.
	 */
	public void close() throws IOException{
		this.run=false;
		socket.close();
	}
	
	/**
	 * The run of the thread.
	 */
	@Override
	public void run() {
		while(run){
		}
	}
}

