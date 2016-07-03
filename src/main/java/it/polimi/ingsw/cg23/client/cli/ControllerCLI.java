package it.polimi.ingsw.cg23.client.cli;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

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
 * The client Command Line Interface's controller. This manages the inputs of the user.
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
		bonusController=new BonusController(this);
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
		StringTokenizer tokenizer=new StringTokenizer(string," ");
		switch (tokenizer.nextToken()) {
		case "QUIT":
			cli.print("", "Bye.");
			out.close();
			break;
		case "HELP":
			new ReaderTextFile(cli).readFile();
			break;
		case "SHOW":
			parseShowCommand(tokenizer);
			break;
		default:
			if(bonus!=null)
				bonusController.updateCommand(string,bonus);
			else
				commandController.updateCommand(string);
			break;
		}
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
	 * Manages the string if contains a show command.
	 * @param tokizer The string that contains the commands.
	 * @throws NoSuchElementException if the string doesn't contain many parameters.
	 */
	private void parseShowCommand(StringTokenizer tokenizer){
		Board model=clientModel.getBoard();
		if(model==null){
			cli.print("", "Command refused.");
			return;
		}
		switch(tokenizer.nextToken()){
		case "BOARD":
			cli.createMap(model);
			cli.print("","Available councillors:");
			cli.printList(model.getCouncillorPool());
			break;
		case "HAND":
			cli.print("", "Your hand is:");
			cli.printList(clientModel.getPlayer().getHand());
			cli.print("", "The chosen cards for the action are:");
			cli.printList(clientModel.getCards());
			break;
		case "TILES":
			cli.print("","Available tiles:");
			cli.printList(clientModel.getPlayer().getAvailableBusinessPermits());
			cli.print("","Used tiles:");
			cli.printList(clientModel.getPlayer().getUsedBusinessPermit());
			break;
		case "NEIGHBORS":
			cli.print("","City's neighbors:");
			cli.printList(clientModel.findCity(tokenizer.nextToken()).getNeighbors());
			break;
		default:
			cli.print("", "Command not found.");
			break;
		}
	}
	
	/**
	 * Notifies the controller with a change object.
	 * @param change The object to control.
	 */
	@Override
	public void updateController(Change change) {
		if(change instanceof PlayerChange && clientModel.getBoard()==null){
			clientModel.setPlayer(((PlayerChange)change).getPlayer());
			return;
		}
		if(change instanceof RankChange){
			cli.print(new CreateMap().createPlayerInfo(((RankChange)change).getRank()),"");
			return;
		}
		if(change instanceof BoardChange){
			replaceBoard((BoardChange)change);
			return;
		}
		if(change instanceof BonusChange){
			cli.print("","You have activated the "+ ((BonusChange)change).getBonus().toString() +". Choose the appropriate parameters. ");
			setBonus(((BonusChange)change).getBonus());
		}else
			cli.print(change,"");
	}

	/**
	 * Replaces the board when the client receive a BoardChange.
	 * @param change The board change received.
	 */
	private void replaceBoard(BoardChange change) {
		Board model=change.getBoard();
		List<Player> players=model.getPlayers();
		for(Player player: players)
			if(clientModel.getPlayer().getUser().equals(player.getUser()))
				clientModel.setPlayer(player);
		clientModel.setBoard(model);
		
	}
}
