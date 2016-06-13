package it.polimi.ingsw.cg23.server.view;


import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.client.rmi.ClientViewRemote;
import it.polimi.ingsw.cg23.server.Server;
import it.polimi.ingsw.cg23.server.controller.Controller;
import it.polimi.ingsw.cg23.server.controller.action.Action;
import it.polimi.ingsw.cg23.server.model.Board;

public class RMIView extends View implements RMIViewRemote {

	//private Set<ClientViewRemote> clients;
	private Server server;
	private Registry registry;
	private static Logger logger;
	
	public RMIView(Server server,Registry registry) {
		logger = Logger.getLogger(RMIView.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");
		this.server=server;
		this.registry=registry;
	}
	
	@Override
	public String registerClient(ClientViewRemote clientStub) throws RemoteException {
		System.out.println("RMI Client Registrato");
		server.incrementIndex();
		Controller controller=server.getController();
		Board model=server.getModel();
		RMIServerView viewRMI=new RMIServerView(clientStub, model);
		viewRMI.registerObserver(controller);
		model.registerObserver(viewRMI);
		String name=generateName();
		try {
			registry.bind(name,(RMIViewRemote) UnicastRemoteObject.exportObject(viewRMI, 1));
		} catch (AlreadyBoundException e) {
			logger.error(e);
		}
		return name;
	}
	
	private String generateName(){
		String string=null;
		try {
			String[] strings=registry.list();
			string="client"+strings.length;
		} catch (RemoteException e) {
			logger.error(e);
		}
		return string;
	}
	@Override
	public void performAction(Action action) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
}

