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

/**
 * The view that manages the requests of the RMI clients.
 * @author Andrea
 *
 */
public class RMIView extends View implements RMIViewRemote {

	private Server server;
	private Registry registry;
	private static Logger logger;
	
	/**
	 * The contructor of RMIView.
	 * @param server the class that manages connections.
	 * @param registry the registry created for RMI connections.
	 */
	public RMIView(Server server,Registry registry) {
		logger = Logger.getLogger(RMIView.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");
		this.server=server;
		this.registry=registry;
	}
	
	/**
	 * Returns the name for lookup the view.
	 * @param clientStub the stub of the client.
	 * @throws RemoteException if RMI connection has problems.
	 */
	@Override
	public String registerClient(ClientViewRemote clientStub) throws RemoteException {
		System.out.println("RMI Client Registrato");
		server.incrementIndex();
		Controller controller=server.getController();
		Board model=server.getModel();
		String name=generateName();
		RMIServerView viewRMI=new RMIServerView(clientStub, model,name);
		viewRMI.registerObserver(controller);
		model.registerObserver(viewRMI);
		try {
			registry.bind(name,(RMIViewRemote) UnicastRemoteObject.exportObject(viewRMI, 1));
		} catch (AlreadyBoundException e) {
			logger.error(e);
		}
		return name;
	}
	
	/**
	 * Generates the name to bind the view.
	 * @return the string of the name.
	 */
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
	
	/**
	 * Notify the controller with the incoming action. Not implemented.
	 * @param action the action to run.
	 * @throws RemoteException if RMI connection has problems.
	 */
	@Override
	public void performAction(Action action) throws RemoteException {
		return;	
	}
}

