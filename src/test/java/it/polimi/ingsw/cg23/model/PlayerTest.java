package it.polimi.ingsw.cg23.model;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.bonus.BonusVictoryPoints;
import it.polimi.ingsw.cg23.server.model.components.BusinessPermitTile;
import it.polimi.ingsw.cg23.server.model.components.Emporium;
import it.polimi.ingsw.cg23.server.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.server.model.components.PoliticCard;

/**
 * Tests the Player class.
 * 
 * @author Andrea
 *
 */
public class PlayerTest {
	Player p;
	List<PoliticCard> cards;
	BusinessPermitTile tile;
	/**
	 * initialization of the test's items.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		cards=new ArrayList<>();
		cards.add(new PoliticCard(Color.BLACK, false));
		cards.add(new PoliticCard(Color.RED, false));
		cards.add(new PoliticCard(Color.YELLOW, false));
		cards.add(new PoliticCard(null, true));
		cards.add(new PoliticCard(Color.BLUE, false));
		cards.add(new PoliticCard(Color.PINK, false));
		List<Character> citiesId=new ArrayList<>();
		citiesId.add('A');
		citiesId.add('B');
		citiesId.add('C');
		tile=new BusinessPermitTile(citiesId, "collina" );
		tile.addBonus(new BonusVictoryPoints(3));
		p=new Player("player1",10,0,new NobilityTrack(3));
	}

	/**
	 * Tests the username of the player.
	 */
	@Test
	public void testUser(){
		assertNotNull(p.getUser());
		assertNotEquals(p.getUser(),"");
		assertEquals(p.getUser(),"player1");
	}

	/**
	 * Tests the assistants pool of the player.
	 */
	@Test
	public void testAssistants() {
		assertNotNull(p.getAssistantsPool());
		assertEquals(p.getAssistantsPool().getAssistants(), 10);
		try {
			p.getAssistantsPool().setAssistants(-1);
			assertEquals(p.getAssistantsPool().getAssistants(), -1);
		} catch (Exception e) {
			assertNotEquals(p.getAssistantsPool().getAssistants(), -1);
			assertEquals(p.getAssistantsPool().getAssistants(), 10);
		}
		
		try {
			p.getAssistantsPool().setAssistants(11);
			assertEquals(p.getAssistantsPool().getAssistants(), 11);
			p.getAssistantsPool().setAssistants(0);
		} catch (Exception e) {
			assertNotEquals(p.getAssistantsPool().getAssistants(), 11);
			assertNotEquals(p.getAssistantsPool().getAssistants(), 0);
			assertEquals(p.getAssistantsPool().getAssistants(), 10);
		}
	}
	
	/**
	 * Tests the richness of the player.
	 */
	@Test
	public void testRichness() {
		assertNotNull(p.getRichness());
		assertEquals(p.getRichness().getCoins(),0);
		try {
			p.getRichness().setCoins(-1);
			assertEquals(p.getRichness().getCoins(),-1);
		} catch (Exception e) {
			assertNotEquals(p.getRichness().getCoins(), -1);
			assertEquals(p.getRichness().getCoins(), 0);
		}
		
		try {
			p.getRichness().setCoins(11);
			assertEquals(p.getRichness().getCoins(), 11);
			p.getRichness().setCoins(0);
		} catch (Exception e) {
			assertNotEquals(p.getRichness().getCoins(), 11);
			assertNotEquals(p.getRichness().getCoins(), 0);
			assertEquals(p.getRichness().getCoins(), 10);
		}
	}
	
	/**
	 * Tests the victory points of the player.
	 */
	@Test
	public void testVictoryPoints() {
		assertNotNull(p.getVictoryTrack());
		assertEquals(p.getVictoryTrack().getVictoryPoints(),0);
		p.getVictoryTrack().setVictoryPoints(-10);
		assertEquals(p.getVictoryTrack().getVictoryPoints(), -10);
		p.getVictoryTrack().setVictoryPoints(10);
		assertEquals(p.getVictoryTrack().getVictoryPoints(), 10);
	}
	
	/**
	 * Tests the hand of the player.
	 */
	@Test
	public void testHand() {
		assertEquals(p.getHand().size(),0);
		for(PoliticCard card:cards)
			p.addPoliticCard(card);
		assertEquals(p.getHand().size(), cards.size());
	}
	
	/**
	 * Tests the emporiums of the player.
	 */
	@Test
	public void testEmporiums() {
		assertFalse(p.isAvailableEmporiumEmpty());
		Emporium e=p.getAvailableEmporium();
		assertNotNull(e);
		assertEquals(e.getPlayer(), p);
		assertNull(e.getCity());
		while(!p.isAvailableEmporiumEmpty())
			p.setEmporium(p.getAvailableEmporium());
		assertTrue(p.isAvailableEmporiumEmpty());
		assertNotEquals(p.getEmporiums().size(),0);
	}
	
	/**
	 * Tests the business permit tiles of the player.
	 */
	@Test
	public void testBusinessPermitTiles() {
		assertEquals(p.getAvailableBusinessPermits().size(),0);
		assertEquals(p.getUsedBusinessPermit().size(),0);
		p.setUsedBusinessPermit(tile);
		p.addAvailableBusinessPermit(tile);
		assertEquals(p.getAvailableBusinessPermits().size(),1);
		assertEquals(p.getUsedBusinessPermit().size(),1);
	}
	
	/**
	 * Tests the additional action of the player.
	 */
	@Test
	public void testAdditionalAction() {
		assertFalse(p.isAdditionalAction());
		p.switchAdditionalAction();
		assertTrue(p.isAdditionalAction());
		p.switchAdditionalAction();
		assertFalse(p.isAdditionalAction());
	}
	
	/**
	 * Tests the nobility track of the player.
	 */
	@Test
	public void testNobilityTrack() {
		assertNotNull(p.getNobilityTrack());
		assertEquals(p.getNobilityBoxPosition(),0);
		p.setNobilityBoxPoistion(1);
		assertEquals(p.getNobilityBoxPosition(),1);
	}
	
	
	

}
