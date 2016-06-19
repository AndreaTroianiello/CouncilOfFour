package it.polimi.ingsw.cg23.client.cli;

import java.io.IOException;
import java.util.List;

import it.polimi.ingsw.cg23.client.ClientController;
import it.polimi.ingsw.cg23.client.ClientModel;
import it.polimi.ingsw.cg23.client.ClientViewOut;
import it.polimi.ingsw.cg23.server.controller.action.Action;
import it.polimi.ingsw.cg23.server.controller.change.BoardChange;
import it.polimi.ingsw.cg23.server.controller.change.BonusChange;
import it.polimi.ingsw.cg23.server.controller.change.Change;
import it.polimi.ingsw.cg23.server.controller.change.PlayerChange;
import it.polimi.ingsw.cg23.server.controller.change.RankChange;
import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.bonus.Bonus;
import it.polimi.ingsw.cg23.utility.CreateMap;
import it.polimi.ingsw.cg23.utility.Print;

/**
 * The client Command Line Interface's controller.
 * @author Andrea
 *
 */
public class ControllerCLI implements ClientController{

	private ClientModel clientModel;
	private ClientViewOut out;
	private Print cli;
	private CommandController commandController;
	private Bonus bonus;
	private BonusController bonusController;

	/**
	 * The constructor of ControllerCLI.
	 * @param cli The Print class that prints to video.
	 */
	public ControllerCLI(Print cli){
		this.out=null;
		this.clientModel=new ClientModel();
		this.cli=cli;
		commandController=new CommandController(this,cli);
		bonusController=new BonusController(this,cli);
		this.bonus=null;
	}

	/**
	 * Sets the out view of the client.
	 * @param out The class that manages the objects to send at the server.
	 */
	@Override
	public void setOutView(ClientViewOut out){
		this.out=out;
	}
	
	/**
	 * Returns the out view of the client.
	 * @return The class that manages the objects to send at the server.
	 */
	public ClientViewOut getOutView(){
		return out;
	}
	
	/**
	 * Sets the bonus to elaborate.
	 * @param bonus If the bonus is null the controller is in normal mode, else if isn't null elaborate the bonus during the update.
	 */
	@Override
	public void setBonus(Bonus bonus) {
		this.bonus=bonus;	
	}
	
	/**
	 * Returns the client's model.
	 */
	@Override
	public ClientModel getModel() {
		return clientModel;
	}

	/**
	 * Notifies the controller with a string.
	 * @param string The string to be elaborated.
	 * @throws IOException If the connection has problems.
	 */
	public void updateController(String string) throws IOException{
		if(bonus==null)
			commandController.updateCommand(string);
		else
			bonusController.updateCommand(string,bonus);
	}
	
	/**
	 * Notifies the controller with a string.
	 * @param string The action to be communicated
	 * @throws IOException If the connection has problems.
	 */
	public void updateController(Action action)throws IOException{
		out.update(action);
	}


	/**
	 * Notifies the controller with a change object.
	 * @param change The object to control.
	 */
	@Override
	public void updateController(Change change) {
		if(change instanceof PlayerChange && clientModel.getModel()==null){
			clientModel.setPlayer(((PlayerChange)change).getPlayer());
			return;
		}
		if(change instanceof RankChange){
			cli.print(new CreateMap().createPlayerInfo(((RankChange)change).getRank()),"");
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
		if(change instanceof BonusChange){
			cli.print("","You have activated the "+ ((BonusChange)change).getBonus().toString() +". Choose the appropriate parameters. ");
			setBonus(((BonusChange)change).getBonus());
		}else
			cli.print(change,"");
	}
}
