package it.polimi.ingsw.cg23.server.view;

import java.rmi.Remote;
import java.rmi.RemoteException;

import it.polimi.ingsw.cg23.client.rmi.ClientViewRemote;
import it.polimi.ingsw.cg23.server.controller.action.Action;


public interface RMIViewRemote extends Remote {
		public String registerClient(ClientViewRemote clientStub) throws RemoteException;
		public void performAction(Action action) throws RemoteException;
}
