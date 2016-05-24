package it.polimi.ingsw.cg23.model.action;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.components.NobilityTrack;

public class HireAssistantTest {
	
	private Player player;
	private Board board;
	
	@Before
	public void setUp(){
		player = new Player("player1",10,100,new NobilityTrack(3));
		board = new Board(null, null, null, null, null);
	}

	@Test
	public void ifThePlayerHas10AssistantAnd100CoinsThemShouldBeChangesTo11And97() {
		HireAssistant action = new HireAssistant();
		action.runAction(player, board);
		int coin = player.getRichness().getCoins();
		int assistants = player.getAssistantsPool().getAssistants();
		assertEquals(coin, 97);
		assertEquals(assistants, 11);
	}
	
	@Test
	public void isMainSouldReturnTheMain(){
		HireAssistant action = new HireAssistant();
		boolean main = action.isMain();
		assertEquals(main, action.isMain());
	}
	
	@Test
	public void toStringShouldReturnTheNameOfTheClass(){
		HireAssistant action = new HireAssistant();
		String name = action.toString();
		assertEquals(name, "HireAssistant []");
	}

}
