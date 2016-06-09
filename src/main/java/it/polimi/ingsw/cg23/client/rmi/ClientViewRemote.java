package it.polimi.ingsw.cg23.client.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import it.polimi.ingsw.cg23.server.controller.change.Change;

@FunctionalInterface
public interface ClientViewRemote extends Remote {
	public void updateClient(Change c) throws RemoteException;
}
