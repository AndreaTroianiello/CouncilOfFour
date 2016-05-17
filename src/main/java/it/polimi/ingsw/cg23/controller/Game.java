package it.polimi.ingsw.cg23.controller;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.cg23.model.*;

public class Game {
	private final Turn turn;
	private final Board board;
	private final List<Player> players;
	
	public Game(){
		this.board=null; //richiamo metodo per creare la board
		this.players=new ArrayList<>();//creo i player
		this.turn=new Turn(players,board);//creo il turn
	}
	//metodi per cambiare i giocatori
	
	
	//bubble sort per ordinare
	public void orderPlayer(){
		for(int index = 0; index < players.size(); index++) {
            boolean flag = false;
            for(int j = 0; j < players.size()-1; j++) {

                //Se il punteggio è minore del successivo allora scambiamo i valori
                if(players.get(j).getVictoryPoints()<players.get(j+1).getVictoryPoints()) {
                    players.add(j,players.get(j+1));
                    flag=true; //Lo setto a true per indicare che é avvenuto uno scambio
                }
                else
                	//se hanno lo stesso puntaggio guardo gli aiutanti e le carte politiche
                	if(players.get(j).getVictoryPoints()==players.get(j+1).getVictoryPoints()
                		&& players.get(j).getAssistants()+players.get(j).getHand().size()<players.get(j+1).getAssistants()+players.get(j+1).getHand().size()){
                		players.add(j,players.get(j+1));
                        flag=true; //Lo setto a true per indicare che é avvenuto uno scambio
                	}
                		
            }
            if(!flag)
            	return; //Se flag=false allora vuol dire che nell' ultima iterazione non ci sono stati scambi, quindi il metodo può terminare
        }

	}
	
	//aggiorna i punteggi e crea una classifica
	public List<Player> finalScore(){
		if(turn.changePlayer()){
			//altro
			orderPlayer();
		}
		return players;
	}
}
