package it.polimi.ingsw.cg23.gui;

import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.client.ClientController;
import it.polimi.ingsw.cg23.client.ClientModel;
import it.polimi.ingsw.cg23.client.ClientViewOut;
import it.polimi.ingsw.cg23.gui.mapframe.FrameMap;
import it.polimi.ingsw.cg23.server.controller.action.Action;
import it.polimi.ingsw.cg23.server.controller.change.BoardChange;
import it.polimi.ingsw.cg23.server.controller.change.Change;
import it.polimi.ingsw.cg23.server.controller.change.InfoChange;
import it.polimi.ingsw.cg23.server.controller.change.PlayerChange;
import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.bonus.Bonus;

/**
 * The client GUI's controller. This manages the inputs of the user.
 * @author Andrea
 *
 */
public class ControllerGUI implements ClientController {

	private Logger logger;
	private ClientModel clientModel;
	private ClientViewOut out;
	private Bonus bonus;
	private FrameMap map;
	private HomeFrame home;
	private SelectedElements selectedElements;
	
	/**
	 * The constructor of the ControllerGUI. Initializes the variables at the default values.
	 * @param home
	 */
	public ControllerGUI(HomeFrame home) {
		logger = Logger.getLogger(this.getClass());
		PropertyConfigurator.configure("src/main/resources/logger.properties");
		clientModel= new ClientModel();
		this.home=home;
		this.bonus=null;
		this.selectedElements=new SelectedElements();
	}
 
	/**
	 * Updates the frame map. If the frame map doesn't exit, initializes it.
	 */
	private void updateFrameMap(){
		if(map!=null){
			map.update();
		}else
			if(clientModel.getBoard()!=null){
				map=new FrameMap(this);
				map.setVisible(true);
				home.setVisible(false);
		}
	}
	
	/**
	 * Updates the current frame with the informations received.
	 * @param change The change received.
	 */
	private void updateInfo(Change change){
		if(map!=null){
			map.updateInfo(change);
		}else{
			home.updateInfo((InfoChange)change);
		}
	}
	
	 /**
	 * Notifies the controller.
	 * @param change The change to be communicated.
	 */
	@Override
	public void updateController(Change change) {
		if(change instanceof PlayerChange && clientModel.getBoard()==null){
			clientModel.setPlayer(((PlayerChange)change).getPlayer());
			return;
		}
		if(change instanceof BoardChange){
			selectedElements.resetAll();
			Board model=((BoardChange)change).getBoard();
			List<Player> players=model.getPlayers();
			for(Player player: players)
				if(clientModel.getPlayer().getUser().equals(player.getUser()))
					clientModel.setPlayer(player);
			clientModel.setBoard(model);
			updateFrameMap();
			return;
		}else
			updateInfo(change);
	}

	/**
	 * Sets the out view of the client.
	 * @param out The class that manages the objects to send to the server.
	 */
	@Override
	public void setOutView(ClientViewOut out) {
		this.out=out;
	}
	
	/**
	 * Closes the connection and close the FrameMap.
	 */
	public void closeAll(){
		try {
			out.close();
		} catch (IOException e) {
			logger.error(e);
		}
		map.dispatchEvent(new WindowEvent(map, WindowEvent.WINDOW_CLOSING));
	}
	
	/**
	 * Returns the SelectedElements object of the controller.
	 * @return SelectedElements object.
	 */
	public SelectedElements getSelectedElements(){
		return selectedElements;
	}
	
	/**
	 * Updates the controller with the action to send.
	 * @param action The action to send.
	 */
	public void updateController(Action action){
		try {
			out.update(action);
		} catch (IOException e) {
			logger.error(e);
		}
	}
	
	/**
	 * Returns the client's model.
	 * @return ClientModel
	 */
	@Override
	public ClientModel getModel() {
		return clientModel;
	}
	
	/**
	 * 
	 * @param cModel
	 */
	public void setModel(ClientModel cModel) {
		this.clientModel=cModel;
	}

	/**
	 * Sets the bonus received.
	 * @param bonus The bonus received.
	 */
	@Override
	public void setBonus(Bonus bonus) {
		this.bonus=bonus;	
	}

}
