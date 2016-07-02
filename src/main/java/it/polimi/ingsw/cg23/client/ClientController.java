package it.polimi.ingsw.cg23.client;

import it.polimi.ingsw.cg23.server.controller.change.Change;
import it.polimi.ingsw.cg23.server.model.bonus.Bonus;

/**
 * The ClientController manages the flow of client-server information.
 * @author Andrea
 *
 */
public interface ClientController {
	
	/**
	 * Notifies the controller.
	 * @param change The change to be communicated.
	 */
	public void updateController(Change change);
	
	/**
	 * Sets the out view of the client.
	 * @param out The class that manages the objects to send to the server.
	 */
	public void setOutView(ClientViewOut out);
	
	/**
	 * Returns the client's model.
	 * @return ClientModel
	 */
	public ClientModel getModel();
	
	public void setBonus(Bonus bonus);
}
