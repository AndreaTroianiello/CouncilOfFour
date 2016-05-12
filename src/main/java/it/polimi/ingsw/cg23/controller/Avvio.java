package it.polimi.ingsw.cg23.controller;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.City;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.Region;
import it.polimi.ingsw.cg23.model.Turn;
import it.polimi.ingsw.cg23.model.components.Deck;
import it.polimi.ingsw.cg23.model.components.King;
import it.polimi.ingsw.cg23.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.model.components.PoliticCard;
import it.polimi.ingsw.cg23.view.CliInterface;
/**
 * class that start the game
 *
 */
public class Avvio {
	CliInterface cl=new CliInterface();
	Controller c=new Controller();
	Setting s=new Setting();
	List <Player> giocatori;//lista giocatori
	List <City> citta;//lista giocatori
	List <Region> regions;//lista giocatori
	List <PoliticCard> politcards;//lista giocatori
	Board board;

	public Avvio(){
		this.giocatori=new ArrayList<>();
		this.board=null;
	}
	
	/**
	 * to start the game
	 */
	public void startPartita(){//metodo per avviare la partita
		cl.print("", "Benvenuti a cof!");
		int playerNumber=numeroGiocatori();//numero di giocatori della partita (richiesto per ora da cl)
		for(int i=0; i<playerNumber; i++){//ciclo per creare i giocatori
			c.createPlayer();
		}
		setGiocatori(c.getGiocatori());//recupero la lista dei giocatori dal controller
		c.createRegions();//crea le regioni e le citta'
		cl.createMap(cl.leggiXml("ConfigurazionePartita.xml"), giocatori);//stampa la plancia di gioco
		setRegioni(c.getRegioni());//recupero la lista delle regioni dal controller
		s.cityList(regions);//setta i vicini delle citta
		setCitta(c.getCitta());
		politcards=s.politicList(13,12);//crea le carte politiche e le mette in una lista
		King k=s.king(citta);//creato il re
		Deck dec=new Deck(politcards);//creato il deck
		Board bord=new Board(dec, regions, new NobilityTrack(20), k);//creata la board
		
		
		// DA FARE creazione elementi di gioco (balconi, bonus, cartepermesso, azioni)
		
		new Turn(bord);//creato il turno
		cl.createMap(citta, giocatori,k);//stampa la plancia di gioco dalla lista
		}
	
	/**
	 * 
	 * @return the number of players
	 */
	public int numeroGiocatori(){
		int playerNumber=0;
		while(playerNumber==0){//si continua a ciclare finche' non e' stato inserito un numero valido
			try{
			playerNumber=Integer.parseInt(cl.writeReturnValue("Quanti giocatori siete?", null).toString());
			}catch(NumberFormatException e){
				cl.print(null, "devi inserire un numero");
				playerNumber=0;
			}
		}
		return playerNumber;
	}
	
	/**
	 * @return the giocatori
	 */
	public List<Player> getGiocatori() {
		return giocatori;
	}

	/**
	 * @return the regioni
	 */
	public List<Region> getRegioni() {
		return regions;
	}
	
	/**
	 * @param giocatori the giocatori to set
	 */
	public void setGiocatori(List<Player> giocatori) {
		this.giocatori = giocatori;
	}

	/**
	 * @param cittas the city to set
	 */
	public void setCitta(List<City> cittas) {
		this.citta = cittas;
	}
	
	/**
	 * @return the bo
	 */
	public Board getBo() {
		return board;
	}

	/**
	 * @param bo the board to set
	 */
	public void setBo(Board bo) {
		this.board = bo;
	}
	
	/**
	 * @param region the region to set
	 */
	public void setRegioni(List<Region> region) {
		this.regions = region;
	}
}