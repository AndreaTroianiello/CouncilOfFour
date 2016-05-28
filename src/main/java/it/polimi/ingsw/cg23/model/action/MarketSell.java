package it.polimi.ingsw.cg23.model.action;

import java.util.List;

import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.components.AssistantsPool;
import it.polimi.ingsw.cg23.model.components.BusinessPermitTile;
import it.polimi.ingsw.cg23.model.components.PoliticCard;
import it.polimi.ingsw.cg23.model.exception.NegativeNumberException;
import it.polimi.ingsw.cg23.model.marketplace.CanBeSold;
import it.polimi.ingsw.cg23.model.marketplace.Item;

public class MarketSell extends GameAction {

	private static final long serialVersionUID = 7634075876339346475L;
	private CanBeSold item;
	private int coins;
	
	public MarketSell(CanBeSold item,int coins) {
		super(false);
		this.item=item;
		this.coins=coins;
	}
	
	public CanBeSold searchItem(CanBeSold item,Player player){
		
		//Se è una tessera
		if(item instanceof BusinessPermitTile){
			List<BusinessPermitTile> playerTiles=player.getAvailableBusinessPermits();
			for(int index=0;index<playerTiles.size();index++){
				if(playerTiles.get(index).getCitiesId().equals(((BusinessPermitTile) item).getCitiesId()))
					return playerTiles.remove(index);
			}
		}
		
		//Una carta politica
		if(item instanceof PoliticCard){
			List<PoliticCard> playerHand=player.getHand();
			for(int index=0;index<playerHand.size();index++){
				if(playerHand.get(index).getColor().equals(((PoliticCard) item).getColor()))
					return playerHand.remove(index);
			}
		}
		
		//Assistenti
		if(item instanceof AssistantsPool){
			AssistantsPool playerAssistants=player.getAssistantsPool();
			int assistants= playerAssistants.getAssistants()-((AssistantsPool)item).getAssistants();
			if(assistants>=0){
				try {
					playerAssistants.setAssistants(assistants);
				} catch (NegativeNumberException e) {
					return null;
				}
			 	return item;
			}
		}
		return null;
	}
	
	
	public void runAction(Player player,Board board){
		item=searchItem(item, player);
		if(item!=null){
			board.getMarket().addItemToSell(new Item(item,player,coins));
		}
		
	}

}
