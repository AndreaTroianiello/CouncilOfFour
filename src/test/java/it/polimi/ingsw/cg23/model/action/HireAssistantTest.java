package it.polimi.ingsw.cg23.model.action;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.action.HireAssistant;
import it.polimi.ingsw.cg23.server.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.server.model.exception.NegativeNumberException;

public class HireAssistantTest {
	
	private Player player;
	private Board board;
	
	@Before
	public void setUp(){
		player = new Player("player1",new NobilityTrack(3));
		board = new Board(null, null, null, null, null);
	}

	@Test
	public void ifThePlayerHas10AssistantAnd100CoinsThemShouldBeChangesTo11And97() throws NegativeNumberException {
		HireAssistant action = new HireAssistant();
		this.player.getRichness().setCoins(100);
		this.player.getAssistantsPool().setAssistants(10);
		action.runAction(player, board);		
		int coin = player.getRichness().getCoins();
		int assistants = player.getAssistantsPool().getAssistants();
		assertEquals(coin, 97);
		assertEquals(assistants, 11);
	}

	
	@Test
	public void ifThePlayerDoesntHaveEnoughMoneyItShouldntChangeAnithing() throws NegativeNumberException {
		HireAssistant action = new HireAssistant();
		player.getAssistantsPool().setAssistants(10);
		player.getRichness().setCoins(0);
		action.runAction(player, board);
		int coin = player.getRichness().getCoins();
		int assistants = player.getAssistantsPool().getAssistants();
		assertEquals(coin, 0);
		assertEquals(assistants, 10);
	}
	
		
	@Test
	public void toStringShouldReturnTheNameOfTheClass(){
		HireAssistant action = new HireAssistant();
		String name = action.toString();
		assertEquals(name, "HireAssistant []");
	}

}
