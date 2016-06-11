package it.polimi.ingsw.cg23.client.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import it.polimi.ingsw.cg23.client.ClientController;
import it.polimi.ingsw.cg23.server.controller.change.Change;


public class ClientRMIView extends UnicastRemoteObject implements ClientViewRemote {

	private static final long serialVersionUID = 1191452922375955484L;
	private transient ClientController controller;
	
	public ClientRMIView(ClientController controller) throws RemoteException {
		super();
		this.controller=controller;
	}
	
	@Override
	public void  updateClient(Change change) throws RemoteException{
		if(change!=null)
			controller.updateController(change);
	}
}

