package it.polimi.ingsw.cg23.client.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.client.ClientController;
import it.polimi.ingsw.cg23.server.view.rmi.RMIViewRemote;

/**
 * ClientRMI manages the creation of RMI connection.
 * 
 * @author Andrea
 *
 */
public class ClientRMI {

	private static final int RMI_PORT = 52365;
	private static final String NAME = "council";
	private static Logger logger;

	/**
	 * The constructor of ClientRMI.
	 */
	public ClientRMI() {
		logger = Logger.getLogger(ClientRMI.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");
	}

	/**
	 * Starts the RMI connection. Initializes the out/in handler objects.
	 * 
	 * @param controller
	 *            The client's controller.
	 * @throws RemoteException
	 *             if the RMI connection has problems.
	 * @throws NotBoundException
	 *             if the view doesn't find a bind object.
	 */
	public void startClient(ClientController controller, String address) throws RemoteException, NotBoundException {
		Registry registry = LocateRegistry.getRegistry(address, RMI_PORT);
		RMIViewRemote serverStub = (RMIViewRemote) registry.lookup(NAME);
		ClientRMIView rmiView = new ClientRMIView(controller);
		ClientViewRemote view = (ClientViewRemote) UnicastRemoteObject.exportObject(rmiView, 0);
		RMIViewRemote rmiServerView = (RMIViewRemote) registry.lookup(serverStub.registerClient(view));
		rmiView.setRMIServerView(rmiServerView);
		logger.info("Connection created");
	}
}
