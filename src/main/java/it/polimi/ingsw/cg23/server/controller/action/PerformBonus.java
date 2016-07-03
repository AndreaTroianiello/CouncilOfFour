package it.polimi.ingsw.cg23.server.controller.action;

import java.util.List;

import it.polimi.ingsw.cg23.observer.Observable;
import it.polimi.ingsw.cg23.server.controller.change.Change;
import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.bonus.Bonus;

/**
 * PerformBonus allows the player to perform the bonus.
 * @author Andrea
 *
 */
public class PerformBonus extends Action {

	private static final long serialVersionUID = 8543622884209194036L;
	private Bonus bonus;
	private Player player;
	
	/**
	 * The constructor of PerformBonus.
	 * @param player The owner of the bonus.
	 * @param bonus The bonus activated.
	 * @throws NullPointerException if the parameters are null.
	 */
	public PerformBonus(Player player,Bonus bonus) {
		if(bonus!=null && player!=null){
			this.bonus=bonus;
			this.player=player;
		}
		else 
			throw new NullPointerException();
	}
	
	/**
	 * Runs the action.
	 * @param model The model of the game.
	 */
	@SuppressWarnings("unchecked")
	public void runAction(Board model){
		Player realPlayer=searchPlayer(model);
		bonus.setBoard(model);
		((Observable<Change>)bonus).registerObserver(getPlayer());
		bonus.giveBonus(realPlayer);
		((Observable<Change>)bonus).registerObserver(getPlayer());
	}

	/**
	 * Search the player in the model
	 * @param board the model of the game
	 * @return the player if it's in the model, null if it's not
	 */
	private Player searchPlayer(Board board) {
		List<Player> players=board.getPlayers();
		for(Player p: players)
			if(p.getUser().equals(player.getUser()))
					return p;
		return null;
	}
}
