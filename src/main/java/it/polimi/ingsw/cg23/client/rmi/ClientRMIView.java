package it.polimi.ingsw.cg23.client.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.client.ClientController;
import it.polimi.ingsw.cg23.server.controller.change.Change;


public class ClientRMIView extends UnicastRemoteObject implements ClientViewRemote {

	private static final long serialVersionUID = 1191452922375955484L;
	private static Logger logger;
	private ClientController controller;
	
	public ClientRMIView(ClientController controller) throws RemoteException {
		super();
		logger = Logger.getLogger(ClientRMI.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");
		this.controller=controller;
	}
	
	@Override
	public void  updateClient(Change change) throws RemoteException{
		if(change!=null)
			controller.updateController(change);
	}
}

