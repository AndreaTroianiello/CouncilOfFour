package it.polimi.ingsw.cg23.gui;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.client.ClientController;
import it.polimi.ingsw.cg23.client.ClientModel;
import it.polimi.ingsw.cg23.client.ClientViewOut;
import it.polimi.ingsw.cg23.client.cli.ControllerCLI;
import it.polimi.ingsw.cg23.server.controller.change.BoardChange;
import it.polimi.ingsw.cg23.server.controller.change.Change;
import it.polimi.ingsw.cg23.server.controller.change.PlayerChange;
import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Player;


public class ControllerGUI implements ClientController {

	private Logger logger;
	private ClientModel clientModel;
	private ClientViewOut out;
	
	public ControllerGUI() {
		logger = Logger.getLogger(ControllerCLI.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");
		clientModel= new ClientModel();
	}

	@Override
	public void updateController(Change change) {
		if(change instanceof PlayerChange && clientModel.getModel()==null){
			clientModel.setPlayer(((PlayerChange)change).getPlayer());
			return;
		}
		if(change instanceof BoardChange){
			Board model=((BoardChange)change).getBoard();
			List<Player> players=model.getPlayers();
			for(Player player: players)
				if(clientModel.getPlayer().getUser().equals(player.getUser()))
					clientModel.setPlayer(player);
			clientModel.setModel(model);
		}

	}

	@Override
	public void setOutView(ClientViewOut out) {
		this.out=out;
	}

}
