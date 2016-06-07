package it.polimi.ingsw.cg23.client.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import it.polimi.ingsw.cg23.server.controller.change.Change;


public class ClientRMIView extends UnicastRemoteObject implements ClientViewRemote {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1191452922375955484L;
	public ClientRMIView() throws RemoteException {
		super();
	}
	public void  updateClient(Change c) throws RemoteException{
		System.out.println(c);
	}
}

