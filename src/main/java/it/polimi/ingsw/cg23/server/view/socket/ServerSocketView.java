package it.polimi.ingsw.cg23.server.view.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


import org.apache.log4j.Logger;

import it.polimi.ingsw.cg23.server.Chat;
import it.polimi.ingsw.cg23.server.controller.action.Action;
import it.polimi.ingsw.cg23.server.controller.action.SendMessage;
import it.polimi.ingsw.cg23.server.controller.change.Change;
import it.polimi.ingsw.cg23.server.view.View;

/**
 * The view that manages the connections of the socket clients.
 * @author Andrea
 *
 */
public class ServerSocketView extends View implements Runnable {

	private ObjectInputStream socketIn;
	private ObjectOutputStream socketOut;
	private Chat chat;
	private Socket socket;

	/**
	 * The constructor of ServerSocketView.
	 * @param socket The socket of the connection.
	 * @param model The board of the game.
	 * @throws IOException if the socket connection has problems.
	 */
	public ServerSocketView(Socket socket,Chat chat) throws IOException {
		this.chat=chat;
		this.chat.addView(this);
		this.socket=socket;
		this.socketIn = new ObjectInputStream(socket.getInputStream());
		this.socketOut = new ObjectOutputStream(socket.getOutputStream());
	}

	/**
	 * Notify the client with the incoming change.
	 * @param change the change to be sent to the client.
	 */
	@Override
	public void update(Change change) {
		getLogger().error("Sending to the client " + change);
		try {
			this.socketOut.writeObject(change);
			this.socketOut.flush();
			this.socketOut.reset();

		} catch (IOException e) {
			getLogger().error(e);
			setSuspended(true);
		}
	}
	
	/**
	 * Manages the object received.
	 * @param object the object received.
	 */

	private void performUpdate(Object object){
		if(object==null)
			return;
		setSuspended(false);
		Action action = (Action) object;
		action.setLogger(Logger.getLogger(Action.class));
		action.setPlayer(this);
		if(action instanceof SendMessage){
			chat.update((SendMessage)action);
		}
		else{
			getLogger().info("VIEW: received the action " + action);
			action.registerObserver(this);
			this.notifyObserver(action);
		}
	}
	/**
	 * The run of the thread. When receives a action,notify the controller with the incoming action.
	 */
	@Override
	public void run() {
		boolean run=true;
		while (run) {

			try {

				Object object = socketIn.readObject();
				performUpdate(object);
				
			} catch (ClassNotFoundException e){
				getLogger().error(e);
			} catch (IOException e) {
				getLogger().error(e);
				run=false;
			}
		}
	}
	
	/**
	 * Close the Socket connection.
	 */
	@Override
	public void close() {
		try {
			socket.close();
			chat.resetViews();
			chat=null;
		} catch (IOException e) {
			getLogger().error(e);
		}
	}
}
