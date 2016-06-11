package it.polimi.ingsw.cg23.client;

import java.io.IOException;
import java.rmi.RemoteException;

import it.polimi.ingsw.cg23.server.controller.action.Action;

public interface ClientViewOut {
	public void update(Action action) throws IOException,RemoteException;
}
