package it.polimi.ingsw.cg23.server.view;

import java.rmi.Remote;
import java.rmi.RemoteException;

import it.polimi.ingsw.cg23.client.rmi.ClientViewRemote;
import it.polimi.ingsw.cg23.server.controller.action.Action;
import it.polimi.ingsw.cg23.server.model.Board;

public class RMIServerView extends View implements RMIViewRemote {

	private ClientViewRemote clientStub;
	
	public RMIServerView(ClientViewRemote clientStub,Board model) {
		this.clientStub=clientStub;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}


	public void eseguiAzione(Action action) throws RemoteException{
		this.notifyObserver(action);
	}

	@Override
	public String registerClient(ClientViewRemote clientStub) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
}
