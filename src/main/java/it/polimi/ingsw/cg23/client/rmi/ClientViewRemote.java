package it.polimi.ingsw.cg23.client.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import it.polimi.ingsw.cg23.server.controller.change.Change;

/**
 * The ClientViewRemote is the remote interface of the incoming RMI connection.
 * @author Andrea
 *
 */
public interface ClientViewRemote extends Remote {
	
	/**
	 * Updates the client with the change.
	 * @param c The incoming object.
	 * @throws RemoteException if the RMI connection has problems.
	 */
	public void updateClient(Change c) throws RemoteException;
	
	/**
	 * Closes the remote connection on the client.
	 * @throws RemoteException
	 */
	public void close() throws RemoteException;
}
