package it.polimi.ingsw.cg23.client.socket;

import java.io.IOException;
import java.io.ObjectInputStream;

import it.polimi.ingsw.cg23.controller.change.Change;


public class ClientInHandler implements Runnable {
	
	private ObjectInputStream socketIn;
	
	public ClientInHandler(ObjectInputStream socketIn) {
		this.socketIn=socketIn;
	}

	@Override
	public void run() {
		while(true){
			
			try {
				Change object=(Change) socketIn.readObject();
				System.out.println(object);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
