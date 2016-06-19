package it.polimi.ingsw.cg23.client;


import java.io.IOException;

import it.polimi.ingsw.cg23.server.controller.change.Change;
import it.polimi.ingsw.cg23.server.model.bonus.Bonus;

/**
 * The ClientController manages the flow of client-server information.
 * @author Andrea
 *
 */
public interface ClientController {
	
	/**
	 * Notifies the controller with a string.
	 * @param string The string to be communicated
	 * @throws IOException If the connection has problems.
	 */
	public void updateController(Change change);
	
	/**
	 * Sets the out view of the client.
	 * @param out The class that manages the objects to send at the server.
	 */
	public void setOutView(ClientViewOut out);
	
	/**
	 * Returns the client's model.
	 * @return ClientModel
	 */
	public ClientModel getModel();
	
	public void setBonus(Bonus bonus);
}
