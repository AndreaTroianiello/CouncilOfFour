package it.polimi.ingsw.cg23.server.view;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.server.controller.action.Action;
import it.polimi.ingsw.cg23.server.controller.change.Change;
import it.polimi.ingsw.cg23.server.model.Board;


public class ServerSocketView extends View implements Runnable {

	private Socket socket;
	private ObjectInputStream socketIn;
	private ObjectOutputStream socketOut;
	private Board model;
	
	private static Logger logger;

	public ServerSocketView(Socket socket, Board model) throws IOException {
		logger = Logger.getLogger(ServerSocketView.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");
		this.socket = socket;
		this.socketIn = new ObjectInputStream(socket.getInputStream());
		this.socketOut = new ObjectOutputStream(socket.getOutputStream());
		this.model=model;
	}

	@Override
	public void update(Change change) {
		logger.error("Sending to the client " + change);
		try {
			this.socketOut.writeObject(change);
			this.socketOut.flush();
			this.socketOut.reset();

		} catch (IOException e) {
			logger.error(e);
		}
	}

	@Override
	public void run() {
		boolean run=true;
		while (run) {

			try {

				Object object = socketIn.readObject();
				if (object instanceof Action) {
					Action action = (Action) object;
					action.setLogger(Logger.getLogger(Action.class));
					action.setPlayer(this);
					logger.error("VIEW: received the action " + action);
					action.registerObserver(this);
					this.notifyObserver(action);
				}
				
				/*if(object instanceof Query){
					System.out.println("SERVER VIEW: received query " + object);
					Query query=(Query) object;
					this.socketOut.writeObject(query.perform(model));
					this.socketOut.flush();
				}*/

			} catch (ClassNotFoundException e){
				logger.error(e);
			} catch (IOException e) {
				logger.error(e);
				run=false;
			}
		}
	}

}
