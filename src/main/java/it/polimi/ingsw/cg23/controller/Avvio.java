package it.polimi.ingsw.cg23.controller;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.cg23.controller.creation.*;
import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.City;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.Region;
import it.polimi.ingsw.cg23.model.Type;
import it.polimi.ingsw.cg23.model.bonus.Bonus;
import it.polimi.ingsw.cg23.model.components.BonusKing;
import it.polimi.ingsw.cg23.model.components.BusinessPermitTile;
import it.polimi.ingsw.cg23.model.components.Deck;
import it.polimi.ingsw.cg23.model.components.King;
import it.polimi.ingsw.cg23.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.model.components.PoliticCard;
import it.polimi.ingsw.cg23.view.Print;

/**
 * class that start the game
 *
 */
public class Avvio {
	Print cl;
	Setting s;
	CreateBonus cb;
	CreateCostruction cc;
	CreateCouncillor cco;
	CreatePlayer cp;
	CreateRegionCity crc;

	private List <Player> giocatori;//lista giocatori
	private List <City> citta;//lista giocatori
	private List <Region> regions;//lista giocatori
	private List <PoliticCard> politcards;//lista giocatori
	private List <Bonus> bonusList;//lista dei bonus
	private List <BusinessPermitTile> costructionCard;//lista dei bonus
	private List <Type> tipi;//lista dei bonus
	
	private Board board;
	private BonusKing bk;

	public Avvio(){
		cc=new CreateCostruction();
		cco=new CreateCouncillor();
		cp=new CreatePlayer();
		crc=new CreateRegionCity("ConfigurazionePartita.xml");
		cl=new Print();
		s=new Setting();
		cb=new CreateBonus("ConfigurazionePartita.xml");
		
		this.board=null;
		this.bk=cb.bonusKing();
		this.citta=new ArrayList<>();
	}

	/**
	 * to start the game
	 */
	public void startPartita(){//metodo per avviare la partita
		cl.print("", "Benvenuti a cof!");

		//----------giocatori----------
		int playerNumber=numeroGiocatori();//numero di giocatori della partita (richiesto per ora da cl)
		for(int i=0; i<playerNumber; i++){//ciclo per creare i giocatori
			cp.createPlayer();//creo i giocatori
		}
		cl.print("", "\nCreo gli elementi di gioco:");
		cl.print("", "-Creo i giocatori");

		//----------creo i type----------
		tipi=s.createType(bk);//creo i type (colori) delle citta'
		cl.print("", "-Creo i type");
		
		//----------board creazione----------
		board=new Board(null, new ArrayList<>(), new ArrayList<>(), new NobilityTrack(20), null);//creata la board
		cl.print("", "-Creo la board");

		//----------bonus----------
		bonusList=cb.bonusList(board);//recupero la lista con tutti i bonus
		cl.print("", "-Creo i bonus");

		//----------regioni----------
		giocatori=cp.getGiocatori();//recupero la lista dei giocatori dal controller
		regions=crc.createRegions(bk);//crea le regioni e ne ritorna la lista
		cl.print("", "-Creo le regioni");
		
		//----------citta'----------
		for(int i=0; i<regions.size(); i++){//ciclo che scorre le regioni
			citta.addAll(crc.createCities(i, regions.get(i), bk));//recupero le citta' della regione
		}
		
		for(int j=0; j<citta.size(); j++){//ciclo che scorre le citta'
			cb.getCityBonus(j, citta.get(j));//aggiungo alla citta' i bonus
		}
		crc.addNeighbors(citta);//aggiungo i vicini alle citta'
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
		costructionCard=cc.createCardCostruction(board);//crea le carte costruzione e le metto nella lista
		cc.createRegionDeck(regions);//riempie i regiondeck delle regioni
		cl.print("", "-Creo le carte permesso di costruzione");

		//----------consiglieri e balconi----------
		cco.createCouncillor(4, board);//crea i consiglieri
		for(int i=0; i<regions.size(); i++){//scorro il numero di regioni
			cco.setBalconi(board, regions.get(i));//crea i balconi delle regioni
		}
		cco.setBalconi(board, king);//crea il balcone del re
		cl.print("", "-Creo i consiglieri");

		//----------nobility track----------
		s.nobilityTrackFill();//riempio il nobility track
		cl.print("", "-Setto il Nobility track");
		
		//----------plancia----------
		cl.print("", "-Creo la plancia di gioco");
		cl.createMap(regions, giocatori, king);//stampa la plancia di gioco dalla lista
		//printAll();//stampa tutte le liste
	}
	
	/**
	 * print all the list
	 */
	public void printAll(){
		cl.print("", "STAMPO TUTTO");
		cl.print(bonusList.size(),"");
		cl.printList(bonusList);
		cl.print(regions.size(),"");
		cl.printList(regions);
		cl.print(citta.size(),"");
		cl.printList(citta);
		cl.print(giocatori.size(),"");
		cl.printList(giocatori);
		cl.print(politcards.size(),"");
		cl.printList(politcards);
		cl.print(costructionCard.size(),"");
		cl.printList(costructionCard);
		cl.print(tipi.size(),"");
		cl.printList(tipi);
		cl.print(bk.toString(), "");
	}

	/**
	 * 
	 * @return the board
	 */
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
