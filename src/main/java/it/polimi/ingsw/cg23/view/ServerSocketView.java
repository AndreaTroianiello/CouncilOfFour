package it.polimi.ingsw.cg23.view;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.apache.log4j.Logger;

import it.polimi.ingsw.cg23.controller.action.Action;
import it.polimi.ingsw.cg23.controller.change.Change;
import it.polimi.ingsw.cg23.model.Board;


public class ServerSocketView extends View implements Runnable {

	private Socket socket;
	private ObjectInputStream socketIn;
	private ObjectOutputStream socketOut;
	private Board model;
	
	private static Logger logger;

	public ServerSocketView(Socket socket, Board model) throws IOException {
		this.logger = Logger.getLogger(ServerSocketView.class);
		this.socket = socket;
		this.socketIn = new ObjectInputStream(socket.getInputStream());
		this.socketOut = new ObjectOutputStream(socket.getOutputStream());
		this.model=model;
	}

	@Override
	public void update() {
	}

	@Override
	public void update(Change o) {
		logger.error("Sending to the client " + o);
		try {
			this.socketOut.writeObject(o);
			this.socketOut.flush();

		} catch (IOException e) {
			logger.error(e);
		}
	}

	@Override
	public void run() {
		while (true) {

			try {

				Object object = socketIn.readObject();
				if (object instanceof Action) {
					Action action = (Action) object;
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

			} catch (ClassNotFoundException e) {
				logger.error(e);
			} catch (IOException e) {
				logger.error(e);
			}
		}
	}

}
