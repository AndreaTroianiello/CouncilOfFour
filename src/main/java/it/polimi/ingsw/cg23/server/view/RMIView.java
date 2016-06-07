package it.polimi.ingsw.cg23.server.view;


import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Set;

import it.polimi.ingsw.cg23.client.rmi.ClientViewRemote;
import it.polimi.ingsw.cg23.server.controller.action.Action;
import it.polimi.ingsw.cg23.server.controller.change.Change;

public class RMIView extends View implements RMIViewRemote {

	private Set<ClientViewRemote> clients;
	
	public RMIView() {
		clients=new HashSet<>();
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub

	}
	@Override
	public void update(Change change) {
		System.out.println("SENDING THE CHANGE TO THE CLIENTS");
		for(ClientViewRemote clientStub:this.clients){
			try {
				clientStub.updateClient(change);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@Override
	public void registerClient(ClientViewRemote clientStub) throws RemoteException {
		System.out.println("CLIENT REGISTRATO");
		this.clients.add(clientStub);
	}
	
	@Override
	public void eseguiAzione(Action action) throws RemoteException {
		this.notifyObserver(action);
	}

}

