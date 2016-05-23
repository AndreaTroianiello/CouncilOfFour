package it.polimi.ingsw.cg23.view;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import it.polimi.ingsw.cg23.controller.Turn;
import it.polimi.ingsw.cg23.controller.change.Change;
import it.polimi.ingsw.cg23.model.action.Action;


public class ServerSocketView extends View implements Runnable {

	private Socket socket;
	private ObjectInputStream socketIn;
	private ObjectOutputStream socketOut;
	private Turn turn;

	public ServerSocketView(Socket socket, Turn turn) throws IOException {
		this.socket = socket;
		this.socketIn = new ObjectInputStream(socket.getInputStream());
		this.socketOut = new ObjectOutputStream(socket.getOutputStream());
		this.turn=turn;
	}

	@Override
	public void update() {
	}

	@Override
	public void update(Change o) {
		System.out.println("Sending to the client " + o);
		try {
			this.socketOut.writeObject(o);
			this.socketOut.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {

			try {

				Object object = socketIn.readObject();
				if (object instanceof Action) {
					Action action = (Action) object;
					System.out.println("VIEW: received the action " + action);
					this.notifyObserver(action);
				}
				/*if(object instanceof Query){
					System.out.println("SERVER VIEW: received query " + object);
					Query query=(Query) object;
					this.socketOut.writeObject(query.perform(model));
					this.socketOut.flush();
				}*/

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
