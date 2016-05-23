package it.polimi.ingsw.cg23.controller;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.City;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.Region;
import it.polimi.ingsw.cg23.model.Type;
import it.polimi.ingsw.cg23.model.bonus.Bonus;
import it.polimi.ingsw.cg23.model.components.BusinessPermitTile;
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
	Setting s=new Setting();

	List <Player> giocatori;//lista giocatori
	List <City> citta;//lista giocatori
	List <Region> regions;//lista giocatori
	List <PoliticCard> politcards;//lista giocatori
	List <Bonus> bonusList;//lista dei bonus
	List <BusinessPermitTile> costructionCard;//lista dei bonus
	List <Type> tipi;//lista dei bonus
	Board board;

	public Avvio(){
		this.board=null;
	}

	/**
	 * to start the game
	 */
	public void startPartita(){//metodo per avviare la partita
		cl.print("", "Benvenuti a cof!");

		//----------giocatori----------
		int playerNumber=numeroGiocatori();//numero di giocatori della partita (richiesto per ora da cl)
		for(int i=0; i<playerNumber; i++){//ciclo per creare i giocatori
			s.createPlayer();//creo i giocatori
		}
		cl.print("", "\nCreo gli elementi di gioco:");
		cl.print("", "-Creo i giocatori");

		//----------creo i type----------
		tipi=s.createType();//creo i type (colori) delle citta'
		cl.print("", "-Creo i type");
		
		//----------board creazione----------
		board=new Board(null, new ArrayList<>(), new ArrayList<>(), new NobilityTrack(20), null);//creata la board
		cl.print("", "-Creo la board");

		//----------bonus----------
		bonusList=s.bonusList(board);//recupero la lista con tutti i bonus
		cl.print("", "-Creo i bonus");

		//----------regioni----------
		giocatori=s.getGiocatori();//recupero la lista dei giocatori dal controller
		regions=s.createRegions();//crea le regioni e ne ritorna la lista
		cl.print("", "-Creo le regioni");

		//----------citta'----------
		for(int i=0; i<regions.size(); i++){//ciclo che scorre le regioni
			citta=s.createCities(i, regions.get(i));//recupero le citta' della regione
		}
		s.addNeighbors();//aggiungo i vicini alle citta'
		cl.print("", "-Creo le citta'");
		
		//----------king----------
		King king=s.king(citta);//creato il re
		cl.print("", "-Creo il re");

		//----------carte politiche----------
		politcards=s.politicList(13,12);//crea le carte politiche e le mette in una lista
		cl.print("", "-Creo le carte politiche"); 

		//----------deck----------
		Deck dec=new Deck(politcards);//creato il deck
		s.pesca(dec, giocatori, 4);//i giocatori pescano 4 carte
		cl.print("", "-Creo il deck");

		//----------board settaggio----------
		board.setDeck(dec);
		board.setKing(king);
		board.setRegions(regions);
		board.setTypes(tipi);
		cl.print("", "-Setto la board");

		//----------carte permesso di costruzione----------
		costructionCard=s.createCardCostruction();//crea le carte costruzione e le metto nella lista
		s.createRegionDeck(regions);//riempie i regiondeck delle regioni
		cl.print("", "-Creo le carte permesso di costruzione");

		//----------consiglieri e balconi----------
		s.createCouncillor(4, board);//crea i consiglieri
		for(int i=0; i<regions.size(); i++){//scorro il numero di regioni
			s.setBalconi(board, regions.get(i));//crea i balconi delle regioni
		}
		s.setBalconi(board, king);//crea il balcone del re
		cl.print("", "-Creo i consiglieri");

		//----------nobility track----------
		s.nobilityTrackFill();//riempio il nobility track
		cl.print("", "-Setto il Nobility track");
		
		//----------plancia----------
		cl.print("", "-Creo la plancia di gioco\n");
		cl.createMap(citta, giocatori,king);//stampa la plancia di gioco dalla lista
		//printAll();//stampa tutte le liste
		
	}

	/**
	 * print all the list
	 */
	public void printAll(){
		cl.print("", "STAMPO TUTTO");
		s.printList(bonusList);
		s.printList(regions);
		s.printList(citta);
		s.printList(giocatori);
		s.printList(politcards);
		s.printList(costructionCard);
		s.printList(tipi);
	}

	public Board getBoard(){
		return board;
	}

	/**
	 * 
	 * @return the number of players
	 */
	public int numeroGiocatori(){
		int playerNumber=0;
		while(playerNumber==0){//si continua a ciclare finche' non e' stato inserito un numero valido
			try{//provo a recuperare il numero di giocatori
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
}
